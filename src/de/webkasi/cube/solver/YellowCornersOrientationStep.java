package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Represents the last step of the Layer-By-Layer solution that turns the
 * corners of the yellow face into their correct orientations.
 */
class YellowCornersOrientationStep {
    private final Cube _cube;
    private final SpeedCubeNotationInterpreter _interpreter;

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
    YellowCornersOrientationStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _interpreter = new SpeedCubeNotationInterpreter(records);
    }

    /**
     * The coordinates (row, column) of the four corners of the yellow face.
     */
    private static final int[][] cornerCoordinates = {
            { 2, 2 },   // upper left
            { 0, 2 },   // upper right
            { 2, 2 },   // lower right
            { 2, 0 }    // lower left
    };

    private static final int rowIndex = 0;
    private static final int columnIndex = 1;

    /*
    In der aktuellen Orientierung muss die Ecke korrekt ausgerichtet sein. Dann die gelbe Fläche zur nächsten
    verdrehten Ecke rechts vorne drehen und wieder Algorithmus anwenden.

    Die Prüfung muss also auf die aktuell rechts vorne stehende Ecke orüfen! Dafür die passenden drei Farben
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
        for (int i = 0; i < cornerCoordinates.length; i++) {
            Cube solvedCube = CubeFactory.create(cube, records);

            // Ganzen Würfel drehen bis erste verdrehte Ecke rechts vorne ist.
            // Diesen Dreh-Präfix immer voranstellen!

            // Farben der aktuell rechts vorne stehenden Ecke ermitteln.
            // Die Prüfung muss gelb oben plus die zwei ermittelten Farben haben!

            while (!isCornerSolved(solvedCube, i)) {
                YellowCornersOrientationStep step =
                        new YellowCornersOrientationStep(solvedCube, records);
                step.solve(i);
                solvedCube = CubeFactory.create(cube, records);

                // gelbe Fläche weiterdrehen!
            }
        }
        // Am Ende gelbe Fläche in korrekte Position drehen
    }

    /**
     * The sequence to move the corners one position further when the
     * correct one is in the lower left position.
     */
    private static final String turnCorner = "x2 R' D' R D ";

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
        _interpreter.addMoves(turnCorner);
    }

    /**
     * Tests whether the corner with the specified index has the correct orientation.
     *
     * @param cube The cube to test.
     * @param i Index of the corner to test
     * @return true if the corner has the correct orientation.
     */
    static boolean isCornerSolved(Cube cube, int i) {
        CubeFace yellowFace = cube.getFace(CubeColor.Yellow);
        CubeColor yellowField = yellowFace.getField(cornerCoordinates[i][rowIndex], cornerCoordinates[i][columnIndex]);
        return yellowField == CubeColor.Yellow;
    }
}
