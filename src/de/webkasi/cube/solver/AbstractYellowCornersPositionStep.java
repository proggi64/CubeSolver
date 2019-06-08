package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Base class for the yellow face corners steps.
 */
abstract class AbstractYellowCornersPositionStep {
    private final Cube _cube;
    protected final SpeedCubeNotationInterpreter _interpreter;

    /**
     * Initializes a new instance of the AbstractYellowCornersPositionStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross edges step.
     */
    AbstractYellowCornersPositionStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _interpreter = new SpeedCubeNotationInterpreter(records);
    }

    private static final int rowIndex = 0;
    private static final int columnIndex = 1;

    /**
     * The coordinates (row, column) of the four corners of the yellow face.
     */
    private static final int[][] cornerCoordinates = {
            { 0, 0 },
            { 0, 2 },
            { 2, 0 },
            { 2, 2 }
    };

    private static final int leftSideIndex = 1;
    private static final int rightSideIndex = 0;

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

    abstract void solve();

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
