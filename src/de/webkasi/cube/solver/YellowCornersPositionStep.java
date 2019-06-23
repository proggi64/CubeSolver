package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Adds the steps to position the corners of the yellow face toward their correct
 * position.
 *
 * The step must be called after the YellowCrossStep. It
 * may do nothing if the positions are already correct.
 */
class YellowCornersPositionStep {
    private final Cube _cube;
    private final SpeedCubeNotationInterpreter _interpreter;

    /**
     * Initializes a new instance of the YellowCornersPositionStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross edges step.
     */
    YellowCornersPositionStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _interpreter = new SpeedCubeNotationInterpreter(records);
    }

    /**
     * Positions the corners of the yellow face if any corner is not already
     * in its correct position.
     *
     * Internally, the solve(Cube, CubeFaceRotationRecords) method has a loop that
     * repeats the solution steps until all corners are in their correct positions.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {
        boolean ready;
        do {
            YellowCornersPositionStep step = new YellowCornersPositionStep(CubeFactory.create(cube, records), records);
            step.solve();
            ready = step.areAllPositionsCorrect();
        } while (!ready);
    }

    /**
     * The sequence to move the corners one position further when the
     * correct one is in the lower left position.
     */
    private static final String lowerLeftMoves = "R U' L' U R' U' L U ";
    /**
     * The sequence to move the corners one position further when the
     * correct one is in the lower right position.
     */
    private static final String lowerRightMoves = "L' U R U' L U R' U' ";

    /**
     * Solution moves for the differen states of the yellow corners.
     *
     * There are 16 possible combinations aof positions. For each one
     * exists a matching solution. The indexes correspond to the
     * results of the getCornerPostionStates() method.
     */
    private static final String[] solutions = new String[] {
            "x2 " + lowerLeftMoves,         // 0
            "x2 y " + lowerLeftMoves,      // 1
            "x2 y' " + lowerRightMoves,      // 2
            "x2 y " + lowerLeftMoves,
            "x2 " + lowerRightMoves,        // 4
            "x2 " + lowerRightMoves,        // 5
            "x2 " + lowerRightMoves,        // 6
            "x2 " + lowerRightMoves,
            "x2 " + lowerLeftMoves,         // 8
            "x2 " + lowerLeftMoves,
            "x2 " + lowerLeftMoves,
            "x2 " + lowerLeftMoves,
            "x2 " + lowerRightMoves,        // 12
            "x2 " + lowerRightMoves,
            "x2 " + lowerRightMoves,
            ""                              // 15 (all)
    };

    /**
     * Moves the three corners that are not already correct one step further,
     * if necessary.
     *
     * solve() does nothing if all corners are already in their correct position.
     */
    protected void solve() {
        byte states = getCornerPositionStates();
        String steps = solutions[states];
        _interpreter.addMoves(steps);
    }

    private static final byte allCornersInCorrectPositionStates = 0x0f;

    /**
     * Tests whether all yellow corners are in their correct positions.
     *
     * @return true if all yellow corners are in the correct positions.
     */
    private boolean areAllPositionsCorrect() {
        return getCornerPositionStates() == allCornersInCorrectPositionStates;
    }

    /**
     * The coordinates (row, column) of the four corners of the yellow face.
     */
    private static final int[][] cornerCoordinates = {
            { 0, 0 },   // upper left
            { 0, 2 },   // upper right
            { 2, 2 },   // lower right
            { 2, 0 }    // lower left
    };

    /**
     * Returns a value that contains informations about all four corners
     * of the yellow face.
     *
     * A bit set to one means that the corner is at the correct position.
     *
     * @return A byte value containing one bit per corner position state:
     * Bit 0 is the left upper corner (0,0), Bit 1 is the right upper corner (0,2),
     * Bit 2 is the right lower corner (2,2), and Bit 3 is the left lower corner (2,0).
     */
    protected byte getCornerPositionStates() {
        byte cornerPositionStates = 0;
        byte value = 1;
        CubeFace yellowFace = _cube.getFace(CubeColor.Yellow);

        for (int i = 0; i < cornerCoordinates.length; i++) {
            cornerPositionStates += isPositionCorrect(yellowFace, i) ? value : 0;
            value = (byte)(value << 1);
        }
        return cornerPositionStates;
    }

    private static final int leftSideIndex = 1;
    private static final int rightSideIndex = 0;
    private static final int rowIndex = 0;
    private static final int columnIndex = 1;

    /**
     * The colors of the two sides of each corner at the yellow face.
     *
     * The indexes of the array correspond to the indexes of the coornerCoordinates
     * array.
     */
    private static final CubeColor[][] corners = {
            { CubeColor.Orange, CubeColor.Green },
            { CubeColor.Green, CubeColor.Red },
            { CubeColor.Red, CubeColor.Blue },
            { CubeColor.Blue, CubeColor.Orange }
    };

    /**
     * Tests whether the specified corner has the three correct colors
     * at its position.
     *
     * It is not necessary that the corner has already the correct orientation.
     *
     * @param yellowFace The current yellow CubeFace object.
     * @param i The index of the corner. 0 is left/up, 1 is right/up, 2 is right down,
     *          and 3 is left down.
     * @return true if the corner with index i has the correct colors.
     */
    private boolean isPositionCorrect(CubeFace yellowFace, int i) {
        CubeColor leftFaceColor = corners[i][leftSideIndex];
        CubeColor rightFaceColor = corners[i][rightSideIndex];
        CubeFace leftFace = _cube.getFace(leftFaceColor);
        CubeFace rightFace = _cube.getFace(rightFaceColor);

        CubeColor actualLeftColor = leftFace.getField(2, 0);
        CubeColor actualRightColor = rightFace.getField(2, 2);
        CubeColor actualUpColor = yellowFace.getField(
                cornerCoordinates[i][rowIndex],
                cornerCoordinates[i][columnIndex]);

        return isColorOneOf(actualLeftColor, leftFaceColor, rightFaceColor) &&
                isColorOneOf(actualRightColor, leftFaceColor, rightFaceColor) &&
                isColorOneOf(actualUpColor, leftFaceColor, rightFaceColor);
    }

    /**
     * Tests whether the test color is one of the specified CubeClor values
     * or yellow.
     *
     * isColorOneOf() is used to test whether a corner has all three of the
     * needed colors to be in the right place. The right orientation is not
     * necessary, yet. The method is calles for each of the three sides of
     * the corner. All calls must return true when the corner is in the right
     * position.
     *
     * @param test The CubeColor to test.
     * @param left One of the CubeColor that is allowed.
     * @param right One of the CubeColor that is allowed.
     * @return true if test is one of the specified CubeColor values or CubeColor.Yellow.
     */
    private boolean isColorOneOf(CubeColor test, CubeColor left, CubeColor right) {
        return test == left || test == right || test == CubeColor.Yellow;
    }
}
