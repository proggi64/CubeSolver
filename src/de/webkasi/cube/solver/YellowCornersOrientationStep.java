package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Represents the last step of the Layer-By-Layer solution that turns the
 * cornerColors of the yellow face into their correct orientations.
 *
 * This is the most complex step, because it is necessary to test
 * the current state of the cube after each movement. It is also complex
 * because the yellow face may be rotated during the steps and the
 * coordinates have to be mapped to new positions.
 */
class YellowCornersOrientationStep {
    /**
     * The SpeedCubeNotationInterpreter used to translate the SpeedCube
     * notation sequences for the solution steps into CubeFaceRotationRecords.
     */
    private final SpeedCubeNotationInterpreter _interpreter;
    /**
     * The cube rotation that is necessary to apply the moves with the correct orientation.
     *
     * This is calculated when the first corner is found that has no correct orientation.
     * The cube is rotated to move this corner to the lower right corner. Each additional
     * corner is moved to this position by rotating only the yellow face.
     */
    private final String _rotationPrefix;

    /**
     * Initializes a new instance of the YellowCornersOrientationStep class.
     *
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross edges step.
     * @param rotationPrefix The SpeedCube notation prefix for rotating the cube
     *                        into the orientation to apply the solution steps. This
     *                        must be always the initial orientation for the first
     *                        corner that has to be in the lower right corner of the
     *                        yellow face when this face is the upper face with the 0,0
     *                        position in the upper left.
     */
    private YellowCornersOrientationStep(CubeFaceRotationRecords records, String rotationPrefix) {
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
        // The rightDownIndex stays unchanged for the complete solution, because
        // the yellow face is rotated and the corner to be solved has always the same
        // coordinates (lower right).
        final int rightDownIndex = getFirstUnresolvedCornerIndex(solvedCube);
        String cubeRotationPrefix = prefixes[rightDownIndex % 4];
        int colorIndex = rightDownIndex;
        int rotationOffset = 0;

        // Count of rotations of the yellow face
        while (colorIndex < 4) {
            // Apply solution steps until the current corner is solved
            while (!isCornerSolved(solvedCube, rightDownIndex, colorIndex)) {
                solvedCube = applySteps(cube, records, cubeRotationPrefix);
            }

            int oldColorIndex = colorIndex;
            colorIndex = getNextUnresolvedCornerIndex(solvedCube, rightDownIndex, colorIndex, rotationOffset);
            if (colorIndex < 4) {
                int countOfYellowFaceRotations = ((oldColorIndex + 4) - colorIndex) % 4;
                rotateYellowFaceForNextCorner(countOfYellowFaceRotations, records);
                rotationOffset = (rotationOffset + countOfYellowFaceRotations) % 4;
            }
            else {
                // Ready? Rotate into the right position!
                rotateYellowFaceForNextCorner((4 - rotationOffset), records);
            }
            // Apply the rotation steps to the cube's copy
            solvedCube = CubeFactory.create(cube, records);
        }
    }

    /**
     * @param cube The Cube to solve with no solutions steps applied, yet.
     * @param records The collection with solutions steps that contains the solution so far;
     *                and that receives the additional steps of this part solution.
     * @param cubeRotationPrefix A string containing the SpeedCube notation for rotating
     *                           the complete cube to get the first unresolved corner
     *                           to the lower right corner of the solver.
     * @return A Cube object with all solution steps applied so far.
     */
    private static Cube applySteps(Cube cube, CubeFaceRotationRecords records, String cubeRotationPrefix) {
        YellowCornersOrientationStep step =
                new YellowCornersOrientationStep(records, cubeRotationPrefix);
        step.solve();

        // Apply the steps to the cube's copy to test again
        return  CubeFactory.create(cube, records);
    }

    /**
     * Corner indexes in the order or clockwise rotation of the yellow face.
     *
     * These indexes are the corner indexes (0 = upper left and clockwise numerated)
     * in the order when the yellow face is rotated clockwise. This is needed to get
     * the next corner index that is positioned to the right lower corner when
     * the face is rotated.
     */
    private static final int[] _rotationOrder = new int[] {
            0, 3, 2, 1
    };

    /**
     * Returns the index of the first corner that has not the correct orientation.
     *
     * The complete cube has to be rotated to position this corner to the lower right
     * of the yellow face. The _rotationPrefix is created based on the result of
     * this method.
     *
     * @param solvedCube The Cube to test.
     * @return The index of the first corner that has not the correct orientation.
     * 0 = upper left, 1 = upper right, 2 = lower right, and 3 = lower left.
     * 4 means that all cornerColors are solved.
     */
    private static int getFirstUnresolvedCornerIndex(
            final Cube solvedCube) {
        int i = 0;
        while (i < cornerCoordinates.length && isCornerSolved(solvedCube, i, i)) {
            i++;
        }
        return i;
    }

    /**
     * Returns the index of the next corner that has not the correct orientation.
     *
     * @param solvedCube The Cube to test.
     * @param currentColorIndex The index of the current resolved corner used as
     *                          starting point backwards.
     * @param rotationOffset The count of rotations of the yellow face
     *                       during the solution steps until now. This is necessary
     *                       for calling the isCornerSolved() method with the
     *                       correct colorIndex argument in the loop.
     * @return The index of the next corner that has not the correct orientation.
     * 0 = upper left, 1 = upper right, 2 = lower right, and 3 = lower left.
     * 4 means that all cornerColors are solved. The yellow face will be
     * rotated until this corner is at the lower right position by the caller.
     */
    private static int getNextUnresolvedCornerIndex(
            final Cube solvedCube,
            final int rightDownIndex,
            int currentColorIndex,
            final int rotationOffset) {
        int i = 0;
        int currentCornerIndex = decrementCornerIndex(rightDownIndex);
        currentColorIndex = decrementCornerIndex(currentColorIndex);
        while (i < cornerCoordinates.length && isCornerSolved(solvedCube, currentCornerIndex, (currentColorIndex + _rotationOrder[rotationOffset]) % 4)) {
            i++;
            currentCornerIndex = decrementCornerIndex(currentCornerIndex);
            currentColorIndex = decrementCornerIndex(currentColorIndex);
        }
        if (i == 4)
            return 4;
        return currentColorIndex;
    }

    /**
     * Decrement the given corner index by one.
     *
     * decrementCornerIndex() returns always a value between 0 and 3. When
     * decrementing 0, a value of 3 is returned.
     *
     * @param cornerIndex The index of the current processed corner.
     * @return New index of the corner.
     */
    private static int decrementCornerIndex(int cornerIndex) {
        return (cornerIndex == 0) ? 3 : --cornerIndex;
    }

    /**
     * Rotates the yellow face the specified count of rotations.
     *
     * @param count Count of clockwise rotations of the yellow face.
     * @param records The CubeFaceRotationRecords receiving the rotation.
     */
    private static void rotateYellowFaceForNextCorner(
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
    private static boolean isCornerSolved(
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
