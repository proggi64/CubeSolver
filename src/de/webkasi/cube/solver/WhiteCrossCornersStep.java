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
    private WhiteCrossCornersStep(final Cube cube, final CubeFaceRotationRecords records) {
        super(cube, records, solutions);
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
    public static void solve(final Cube cube, final CubeFaceRotationRecords records) {
        WhiteCrossCornersStep step = new WhiteCrossCornersStep(cube, records);
        step.solve();
    }

    /**
     * Algorithms for moving a corner to the upper right position.
     *
     * Each possible position and orientation of the corner is specified
     * as a PartPosition object. The PositionFinder.findCorner() method
     * finds the actual position of a corner and the corresponding
     * algorithm can be found in this array. All positions in the array
     * are valid for the default orientation of the cube with the green
     * face as the front and the white face as the top.
     *
     * To use the algorithms with each white corner, the orientation
     * of the cube must be rotated so that the target face is at the
     * front. The PositionTranslator class translates the current position
     * that contains absolute coordinates based on the default orientation
     * to the coordinates when the target face is the front face. So
     * the positions that are used as the key of the solution can be used
     * for all four orientations.
     */
    private static final Solution[] solutions = {
            // main color found at the up face
            new Solution(new PartPosition(up, 0, 0),
                    "B' D2 B R' D' R D "),
            new Solution(new PartPosition(up, 0, 2),
                    "B' D B D' R' D R "),
            new Solution(new PartPosition(up, 2, 2), ""),
            new Solution(new PartPosition(up, 2, 0),
                    "L D L' R' D' R D "),

            // main color found at the left face
            new Solution(new PartPosition(left, 0, 0),
                    "L' D2 L R' D' R D "),
            new Solution(new PartPosition(left, 0, 2),
                    "L D L' D' R' D R "),
            new Solution(new PartPosition(left, 2, 2),
                    "R' D R "),
            new Solution(new PartPosition(left, 2, 0),
                    "D2 R' D' R D "),

            // main color found at the front face
            new Solution(new PartPosition(front, 0, 0),
                    "L D L' R' D2 R D R' D' R "),
            new Solution(new PartPosition(front, 0, 2),
                    "R' D' R D R' D2 R D R' D' R "),
            new Solution(new PartPosition(front, 2, 2),
                    "D' R' D R "),
            new Solution(new PartPosition(front, 2, 0),
                    "D R' D' R D "),

            // main color found at the right face
            new Solution(new PartPosition(right, 0, 0),
                    "R' D' R D R' D' R D "),
            new Solution(new PartPosition(right, 0, 2),
                    "R D R' D2 R' D2 R D R' D' R "),
            new Solution(new PartPosition(right, 2, 2),
                    "D R' D2 R "),
            new Solution(new PartPosition(right, 2, 0),
                    "R' D' R D "),

            // main color found at the back face
            new Solution(new PartPosition(back, 0, 0),
                    "B' D' B R' D' R D "),
            new Solution(new PartPosition(back, 0, 2),
                    "B D2 B' D' R' D R "),
            new Solution(new PartPosition(back, 2, 2),
                    "R' D2 R "),
            new Solution(new PartPosition(back, 2, 0),
                    "D' R' D' R D "),

            // main color found at the down face
            new Solution(new PartPosition(down, 0, 0),
                    "D R' D2 R D R' D' R "),
            new Solution(new PartPosition(down, 0, 2),
                    "R' D2 R D R' D' R "),
            new Solution(new PartPosition(down, 2, 2),
                    "D' R' D2 R D R' D' R "),
            new Solution(new PartPosition(down, 2, 0),
                    "D2 R' D2 R D R' D' R "),
    };

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
    protected PartPosition findPosition(final Cube steppedCube, final CubeColor face) {
        return PositionFinder.findCorner(steppedCube, CubeColor.White, face);
    }
}
