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
    public static void solve(Cube cube, CubeFaceRotationRecords records) {
        WhiteCrossCornersStep step = new WhiteCrossCornersStep(cube, records);
        step.solve();
    }

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
                    "L' D2 R' D' R D "),
            new Solution(new PartPosition(left, 0, 2),
                    "L R' D L' R' "),
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
                    "L' D' L D R' D' R D "),
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
    protected PartPosition findPosition(Cube steppedCube, CubeColor face) {
        return PositionFinder.findCorner(steppedCube, CubeColor.White, face);
    }
}
