package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Represents the last step of the Layer-By-Layer solution that turns the
 * corners of the yellow face into their correct orientations.
 */
class YellowCornersOrientationStep {
    /**
     * The Cube to solve.
     */
    private final Cube _cube;
    /**
     * The SpeedCubeNotationInterpreter used to translate the SpeedCube
     * notation sequences for the solution steps into CubeFaceRotationRecords.
     */
    private final SpeedCubeNotationInterpreter _interpreter;
    private final String _rotationPrefix;

    /**
     * Initializes a new instance of the YellowCornersOrientationStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross edges step.
     * @params rotationPrefix The SpeedCube notation prefix for rotating the cube
     *                        into the orientation to apply the solution steps. This
     *                        must be always the initial orientation for the first
     *                        corner that has to be in the lower right corner of the
     *                        yellow face when this face is the upper face with the 0,0
     *                        position in the upper left.
     */
    YellowCornersOrientationStep(Cube cube, CubeFaceRotationRecords records, String rotationPrefix) {
        _cube = cube;
        _interpreter = new SpeedCubeNotationInterpreter(records);
        _rotationPrefix = rotationPrefix;
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
     * The second index value of the cornerCoordinates array to access
     * the row coordinate.
     */
    private static final int rowIndex = 0;
    /**
     * The second index value of the cornerCoordinates array to access
     * the column coordinate.
     */
    private static final int columnIndex = 1;

    /**
     * Cube rotation commands around the y axis to rotate a specific yellow
     * corner to the lower right position.
     *
     * The index of the command corresponds to the cornerCoordinates array.
     * The coordinates there will be at the lower right position when the
     * command is executed. The yellow face must be the upper face in order
     * to work correctly ("x2" command is previously required!).
     */
    private static final String[] prefixes = new String[] {
            "y2 ",      // upper left (0) to lower right
            "y' ",      // upper right (1) to lower right
            "",         // lower right stays
            "y "        // lower left to lower right
    };

    /*
    In der aktuellen Orientierung muss die Ecke korrekt ausgerichtet sein. Dann die gelbe Fläche zur nächsten
    verdrehten Ecke rechts vorne drehen und wieder Algorithmus anwenden.

    Die Prüfung muss also auf die aktuell rechts vorne stehende Ecke prüfen! Dafür die passenden drei Farben
    prüfen!

    Wenn der restliche Würfel kaputt ist, ist das richtig, solange der Algorithmus mit der nächsten verdrehten Ecke
    ausgeführt wird.

    Es kann sein, dass die Ebene am Ende vollständig ist, aber noch 1-2 mal gedreht werden muss!
     */

    /**
     * Turns the corners of the yellow face into the right orientation if any corner is not already
     * in its correct position.
     *
     * Internally, the solve(Cube, CubeFaceRotationRecords) method has a loop that
     * repeats the solution steps until all corners are in their correct orientations.
     * For this strategy the steps has to be applied on the cube to test whether
     * the cube is solved. For this tests a copy of the real cube is used. The actual
     * cube will not be changed.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {

        // Create cube with all solution steps until now
        Cube solvedCube = CubeFactory.create(cube, records);

        // Get the index (0-3) of the first wrong orientated corner; or 4 if already solved
        int wrongCornerIndex = getUnresolvedCornerIndex(solvedCube, 0);
        int i = wrongCornerIndex;
        while (i < 4) {
            // Apply solution steps until the current corner is solved
            while (!isRightLowerCornerSolved(solvedCube, i)) {
                YellowCornersOrientationStep step =
                        new YellowCornersOrientationStep(solvedCube, records, prefixes[wrongCornerIndex]);
                step.solve(i);

                // Apply the steps to the cube's copy to test again
                solvedCube = CubeFactory.create(cube, records);
            }
            // Rotate the yellow face to the next wrong corner and get its index for the next round
            i = rotateYellowFaceForNextCorner(solvedCube, i, records);
        }
        // Am Ende gelbe Fläche in korrekte Position drehen
    }

    /**
     * Rotates the yellow face until the next unresolved corner is at the
     * lower right position.
     *
     * If all corners are solved the yellow face is not rotated.
     *
     * @param solvedCube The Cube with the current solution state.
     * @param i The current index of the corner to solve (0 to 3).
     * @param records The CubeFaceRotationRecords receiving the rotation.
     * @return Index of the next wrong orientated corner. 4 if all corners are solved.
     */
    private static int rotateYellowFaceForNextCorner(Cube solvedCube, int i, CubeFaceRotationRecords records) {
        int nextIndex = getUnresolvedCornerIndex(solvedCube, i);
        if (nextIndex == 4)
            return 4;

        int count = nextIndex - i;
        CubeFaceRotationRecord record = new CubeFaceRotationRecord(CubeColor.Yellow);
        for (int c = 0; c < count; c++)
            records.add(record);
        return nextIndex;
    }

    /**
     * Returns the index of the first corner that has not the correct orientation.
     *
     * @param solvedCube The Cube to test.
     * @return The index of the first corner that has not the correct orientation.
     * 0 = upper left, 1 = upper right, 2 = lower right, and 3 = lower left.
     * 4 means that all corners are solved.
     */
    private static int getUnresolvedCornerIndex(Cube solvedCube, int offset) {
        int i = offset;

        while (i < cornerCoordinates.length && isCornerSolved(solvedCube, i)) {
            i++;
        }
        return i;
    }

    /**
     * The sequence to move the corners one position further when the
     * correct one is in the lower left position.
     */
    private static final String turnCorner = "R' D' R D ";

    /**
     * Tries to turn the corner into the correct orientation.
     *
     * The surrounding loop tests whether it was succesful. If not, the
     * method is called again. At most two calls are enough to turn the
     * corner correctly.
     *
     * @param i The index of the corner to solve.
     */
    private void solve(int i) {
        _interpreter.addMoves("x2 " + _rotationPrefix + turnCorner);
    }

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

    /**
     * Tests whether the lower right corner with the specified index has the correct
     * orientation during the solution steps.
     *
     * The yellow face is rotated during the solution steps so that always the corner
     * to be rotated is in the right lower position. When testing whether the corner is
     * in the right orientation we must not test the corner in each right position,
     * but in the lower right position.
     *
     * @param cube The cube to test.
     * @param i Index of the corner to test.
     * @return true if the corner has the correct orientation.
     */
    static boolean isRightLowerCornerSolved(Cube cube, int i) {
        CubeFace yellowFace = cube.getFace(CubeColor.Yellow);
        CubeColor leftFaceColor = corners[i][leftSideIndex];
        CubeColor rightFaceColor = corners[i][rightSideIndex];
        CubeFace leftFace = cube.getFace(leftFaceColor);
        CubeFace rightFace = cube.getFace(rightFaceColor);

        // Coordinates of the left and right part
        // of the corners are the same for all faces!
        // TODO Drehung der gelben Fläche ist nicht berücksichtigt!!!! Falsche Faces!
        CubeColor actualLeftColor = leftFace.getField(2, 0);
        CubeColor actualRightColor = rightFace.getField(2, 2);

        CubeColor actualUpColor = yellowFace.getField(
                cornerCoordinates[i][rowIndex], cornerCoordinates[i][columnIndex]);

        return actualUpColor == CubeColor.Yellow &&
                actualLeftColor == leftFaceColor &&
                actualRightColor == rightFaceColor;
    }

    /**
     * Tests whether the corner with the specified index has the correct orientation.
     *
     * Tests all three colors of the corner. It tests the corner in its correct end position.
     *
     * @param cube The cube to test.
     * @param i Index of the corner to test.
     * @return true if the corner has the correct orientation.
     */
    static boolean isCornerSolved(Cube cube, int i) {
        CubeFace yellowFace = cube.getFace(CubeColor.Yellow);
        CubeColor leftFaceColor = corners[i][leftSideIndex];
        CubeColor rightFaceColor = corners[i][rightSideIndex];
        CubeFace leftFace = cube.getFace(leftFaceColor);
        CubeFace rightFace = cube.getFace(rightFaceColor);

        CubeColor actualLeftColor = leftFace.getField(2, 0);
        CubeColor actualRightColor = rightFace.getField(2, 2);
        CubeColor actualUpColor = yellowFace.getField(
                cornerCoordinates[i][rowIndex],
                cornerCoordinates[i][columnIndex]);

        return actualUpColor == CubeColor.Yellow &&
                actualLeftColor == leftFaceColor &&
                actualRightColor == rightFaceColor;
    }
}
