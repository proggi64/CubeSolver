package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for completing the white top layer on a
 * scrambled 3x3 Magic Cube.
 *
 * The solution rotations already added to the records will be executed on a working
 * copy of the cube and the missing corners are placed. After this step is
 * applied the first top layer (white) will be complete, as well as the middle
 * parts of the side faces.
 */
class WhiteCrossCornersStep extends AbstractSolutionStep {
    /**
     * Initializes a new instance of the WhiteCrossCornersStep class with the
     * specified Cube and CubeFaceRotationsRecords objects.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain the previous steps of the WhiteCrossStep.solve()
     *                method.
     */
    private WhiteCrossCornersStep(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
    }

    /**
     * Creates upper cube layer as described for the Layer-by-Layer algorithm.
     *
     * solve() scans the cube to determine which algorithms are needed to
     * get the upper layer. The orientation of the corners may be still wrong
     * after the call. This orientation should be corrected by another step class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps
     *                and containing the steps necessary to get the white cross. See
     *                WhiteCrossStep.solve().
     */
    public static void solve(Cube cube, CubeFaceRotationRecords records) {
        WhiteCrossCornersStep step = new WhiteCrossCornersStep(cube, records);
        step.solve();
    }

    /**
     * Finds the solution for a specific corner position.
     *
     * For each possible corner position exists a sequence of moves.
     * These moves are stored in an internal array of Solution
     * objects. The array is searched for the position and the
     * corresponding solution ist returned.
     *
     * The position refers to absolute coordinates of the cube.
     * The top (white) face has index 0 and its row 0 and column 0 is in the
     * upper left of the face. Each solution has a position as its key.
     * Each solution describes how to bring the part at the position
     * to the front up position with the white face up.
     *
     * If the cube is rotated to a new front face, the actual part position
     * must be translated to the position orientation of the keys to find
     * the correct solution.
     *
     * @param position The PartPosition to find the solution for.
     * @return A String with the solution steps in SpeedCube syntax.
     */
    protected String findSolutionFor(PartPosition position) {
        int i = 0;
        PartPosition translatedPosition = PositionTranslator.translate(position, _orientation);
        //while (true) {
            //if (translatedPosition.isEqual(solutions[i].position))
            //    return solutions[i].moves;

            // TODO Steht die Ecke im oberen Layer? Dann erst in unteren bringen
            // TODO Jetzt abhängig von der Ausrichtung die Sequenz addieren
            i++;
        //}
        return "";
    }

    /**
     * Finds the position of the specified corner.
     *
     * @param steppedCube The Cube with the applied current found solutions steps.
     * @param face The cube's face that is currently the front face. The second color
     *             is always white. The third color is determined by the orientation
     *             as front and up of the two given colors. For example: face is green,
     *             so for this algorithm the corner is always at the right of the front
     *             and the third color can only be red.
     * @return A PartPosition object with the absolute coordinates of the searched part.
     */
    protected PartPosition findPosition(Cube steppedCube, CubeColor face) {
        return PositionFinder.FindCorner(steppedCube, CubeColor.White, face);
    }
}
