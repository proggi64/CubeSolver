package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Represents the last step of the Layer-By-Layer solution that turns the
 * corners of the yellow face into their correct orientations.
 */
class YellowCornersOrientationStep {
    private final Cube _cube;
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
     * Cube rotation commands around the y axis to rotate a yellow
     * corner to the lower right position.
     *
     * The index of the command corresponds to the cornerCoordinates array.
     * The coordinates there will be at the lower right position when the
     * command is executed.
     */
    private static final String[] prefixes = new String[] {
            "y2 ",
            "y ",
            "",
            "y' "
    };

    private static final int rowIndex = 0;
    private static final int columnIndex = 1;

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
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {

        // Aktueller Stand des Würfels nach allen Drehungen bisher
        Cube solvedCube = CubeFactory.create(cube, records);

        // Ganzen Würfel drehen bis erste verdrehte Ecke rechts vorne ist.
        // Diesen Dreh-Präfix immer voranstellen!
        int wrongCornerIndex = getUnresolvedCornerIndex(solvedCube, 0);

        for (int i = wrongCornerIndex; i < cornerCoordinates.length; i++) {

            // Farben der aktuell rechts vorne stehenden Ecke ermitteln.
            // Die Prüfung muss gelb oben plus die zwei ermittelten Farben haben!

            while (!isRightLowerCornerSolved(solvedCube, i)) {
                YellowCornersOrientationStep step =
                        new YellowCornersOrientationStep(solvedCube, records, prefixes[wrongCornerIndex]);
                step.solve(i);
                solvedCube = CubeFactory.create(cube, records);

            }
            rotateYellowFaceForNextCorner(solvedCube, i, records);
            // gelbe Fläche weiterdrehen bis zum !
        }
        // Am Ende gelbe Fläche in korrekte Position drehen
    }

    private static void rotateYellowFaceForNextCorner(Cube solvedCube, int i, CubeFaceRotationRecords records) {
        int nextIndex = getUnresolvedCornerIndex(solvedCube, i);
        if (nextIndex == 4)
            return;

        int count = nextIndex - i;
        CubeFaceRotationRecord record = new CubeFaceRotationRecord(CubeColor.Yellow);
        for (int c = 0; c < count; c++)
            records.add(record);
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
        CubeFace leftFace = cube.getFace(CubeColor.Blue);
        CubeFace rightFace = cube.getFace(CubeColor.Red);

        CubeColor actualLeftColor = leftFace.getField(2, 0);
        CubeColor actualRightColor = rightFace.getField(2, 2);
        CubeColor actualUpColor = yellowFace.getField(2, 2);

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
