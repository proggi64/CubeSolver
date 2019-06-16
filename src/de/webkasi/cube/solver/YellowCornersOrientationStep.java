package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Represents the last step of the Layer-By-Layer solution that turns the
 * cornerColors of the yellow face into their correct orientations.
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
     * The coordinates (row, column) of the four cornerColors of the yellow face.
     *
     * The cornerIndex is used as the index to access this array to get the
     * coordinates of the yellow corner.
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
     * Count of cube rotations that have been applied to position the
     * wron orientated corner to the lower right.
     */
    private static final int[] rotationOffsets = new int[] { 2, 1, 0, 3 };

    /**
     * Turns the cornerColors of the yellow face into the right orientation if any corner is not already
     * in its correct position.
     *
     * Internally, the solve(Cube, CubeFaceRotationRecords) method has a loop that
     * repeats the solution steps until all cornerColors are in their correct orientations.
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
        // The coordinateIndex stays unchanged for the complete solution, because
        // the yellow face is rotated and the corner to be solved has always the same
        // coordinates (lower right).
        final int coordinateIndex = getUnresolvedCornerIndex(solvedCube, 0);
        String cubeRotationPrefix = prefixes[coordinateIndex % 4];
        int colorIndex = coordinateIndex;

        // Count of rotations of the yellow face
        while (colorIndex < 4) {
            // Apply solution steps until the current corner is solved
            while (!isCornerSolved(solvedCube, coordinateIndex, colorIndex)) {
                YellowCornersOrientationStep step =
                        new YellowCornersOrientationStep(solvedCube, records, cubeRotationPrefix);
                step.solve();

                // Apply the steps to the cube's copy to test again
                solvedCube = CubeFactory.create(cube, records);
            }

            int oldColorIndex = colorIndex;
            colorIndex = getUnresolvedCornerIndex(solvedCube, colorIndex);
            int countOfYellowFaceRotations = ((oldColorIndex + 4) - colorIndex) % 4;
            rotateYellowFaceForNextCorner(solvedCube, countOfYellowFaceRotations, records);

            // Apply the rotation steps to the cube's copy
            solvedCube = CubeFactory.create(cube, records);
        }
        // Am Ende gelbe Fläche in korrekte Position drehen
    }

    /**
     * Returns the index of the first corner that has not the correct orientation.
     *
     * @param solvedCube The Cube to test.
     * @param colorIndex The index of the left and right colors array for the test.
     * @return The index of the first corner that has not the correct orientation.
     * 0 = upper left, 1 = upper right, 2 = lower right, and 3 = lower left.
     * 4 means that all cornerColors are solved.
     */
    private static int getUnresolvedCornerIndex(
            final Cube solvedCube,
            final int colorIndex) {
        int i = 0;
        while (i < cornerCoordinates.length && isCornerSolved(solvedCube, i, colorIndex)) {
            i++;
        }
        return i;
    }

    /**
     * Rotates the yellow face the specified count of rotations.
     *
     * @param solvedCube The Cube with the current solution state.
     * @param count Count of clockwise rotations of the yellow face.
     * @param records The CubeFaceRotationRecords receiving the rotation.
     */
    private static void rotateYellowFaceForNextCorner(
            final Cube solvedCube,
            final int count,
            final CubeFaceRotationRecords records) {
        CubeFaceRotationRecord record = new CubeFaceRotationRecord(CubeColor.Yellow);
        for (int c = 0; c < count; c++)
            records.add(record);
    }

    /**
     * The sequence to move the cornerColors one position further when the
     * correct one is in the lower left position.
     */
    private static final String turnCorner = "R' D' R D ";

    /**
     * Tries to turn the corner into the correct orientation.
     *
     * The surrounding loop tests whether it was succesful. If not, the
     * method is called again. At most two calls are enough to turn the
     * corner correctly.
     */
    private void solve() {
        _interpreter.addMoves("x2 " + _rotationPrefix + turnCorner);
    }

    private static final int leftSideIndex = 1;
    private static final int rightSideIndex = 0;

    /**
     * The colors of the two sides of each corner at the yellow face.
     *
     * The indexes of the array correspond to the indexes of the cornerCoordinates
     * array.
     */
    private static final CubeColor[][] cornerColors = {
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
     * @param cube The cube to test. The state of the cube will change during the
     *             solution steps, because after each step the movements will be applied
     *             to test the cubes state.
     * @param coordinatesIndex Index of the cornerCoordinates array to get the
     *                         coordinates of the yellow face color field, as well
     *                         as the actual left and right face indexes (color)
     *                         where the color fields are that should be tested.
     *                         These faces stay the same during the solution steps.
     * @param colorIndex Index of the cornerColors array to get the destination left and right
     *                   side color of the yellow face corner and the
     * @return true if the corner has the correct orientation.
     */
    static boolean isCornerSolved(
            final Cube cube,
            final int coordinatesIndex,
            final int colorIndex) {
        CubeColor leftFaceColor = cornerColors[coordinatesIndex][leftSideIndex];
        CubeColor rightFaceColor = cornerColors[coordinatesIndex][rightSideIndex];

        // The rotation of the yellow face has to be taken into account:
        CubeFace leftFace = cube.getFace(leftFaceColor);
        CubeFace rightFace = cube.getFace(rightFaceColor);

        // Coordinates of the left and right part
        // of the cornerColors are the same for all faces!
        CubeColor actualLeftColor = leftFace.getField(2, 0);
        CubeColor actualRightColor = rightFace.getField(2, 2);

        CubeFace yellowFace = cube.getFace(CubeColor.Yellow);
        CubeColor actualUpColor = yellowFace.getField(
                cornerCoordinates[coordinatesIndex][rowIndex], cornerCoordinates[coordinatesIndex][columnIndex]);

        CubeColor leftTargetColor = cornerColors[colorIndex][leftSideIndex];
        CubeColor rightTargetColor = cornerColors[colorIndex][rightSideIndex];
        return actualUpColor == CubeColor.Yellow &&
                actualLeftColor == leftTargetColor &&
                actualRightColor == rightTargetColor;
    }
}
