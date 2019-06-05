package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for completing the middle layer on a
 * scrambled 3x3 Magic Cube.
 *
 * The solution rotations already added to the records will be executed on a working
 * copy of the cube and the missing edges are placed. After this step is
 * applied the top and middle layers will be complete. The previous steps
 * must create the white cross and the white layer, otherwise, this solution
 * will not work.
 */
class SecondLayerStep extends AbstractSolutionStep {

    /**
     * Initializes a new instance of the SecondLayerStep class with the
     * specified Cube and CubeFaceRotationsRecords objects.
     *
     * @param cube The Cube to solve. The cube must have the same state
     *             that it had the the previous steps have been called.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain the previous steps of the WhiteCrossStep.solve()
     *                and WhiteCornersStep.solve() methods.
     */
    private SecondLayerStep(final Cube cube, final CubeFaceRotationRecords records) {
        super(cube, records, solutions);
    }

    /**
     * Creates the middle cube layer as described for the Layer-by-Layer algorithm.
     *
     * solve() scans the cube to determine which algorithms are needed to
     * position the middle layer edges. The algotithm will position the green/blue
     * edge first, than the ornage/green and so on until all four edges are at
     * the correct position.
     *
     * @param cube The Cube to solve. The cube must have the same state
     *             that it had when the previous steps have been called.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain the previous steps created by the WhiteCrossStep.solve()
     *                and WhiteCornersStep.solve() methods previously.
     */
    static void solve(final Cube cube, final CubeFaceRotationRecords records) {
        SecondLayerStep step = new SecondLayerStep(cube, records);
        step.solve();
    }

    /**
     * Sequence that moves the front/up edge color to the front/left edge.
     */
    private final static String moveFrontUpToFrontLeft = "U' L' U L U F U' F' ";

    /**
     * Sequence that moves the front/up edge color to the front/right edge.
     */
    private final static String moveFrontUpToFrontRight = "U R U' R' U' F' U F ";

    /**
     * Sequence that moves the front/right edge color to the up/back edge.
     */
    private final static String moveFrontRightToUpBack = moveFrontUpToFrontRight;

    /**
     * Sequence that moves the front/left edge color to the up/back edge.
     */
    private final static String moveFrontLeftToUpBack = moveFrontUpToFrontLeft;

    /**
     * Sequence that turns the cube upside down around the z axis.
     */
    private final static String turnZ = "z2 ";

    /**
     * Algorithms for moving an edge to the right middle position of the front.
     *
     * Each possible position and orientation of the edge is specified
     * as a PartPosition object. The PositionFinder.findEdge() method
     * finds the actual position of an edge and the corresponding
     * algorithm can be found in this array. All positions in the array
     * are valid for the default orientation of the cube with the green
     * face as the front and the white face as the top. The position
     * specifies not the current front color, but its neighbor color.
     * So down, 1, 0 means for a green front that the red color is
     * at the down side towards the green face.
     *
     * Because the upper layer is already solved, the count of possible
     * edge positions is reduced to three per face. Also the upper white
     * face is no possible position for the searched edge, so we have
     * to search only five faces.
     *
     * To use the algorithms with the other three faces, the orientation
     * of the cube must be rotated so that the target face is at the
     * front. The PositionTranslator class translates the current position
     * to match the key of the array for each face.
     */
    private static final Solution[] solutions = {
            // right side color found at the left face
            new Solution(new PartPosition(left, 1, 0),
                    turnZ + "y' " + moveFrontRightToUpBack + "y U' " + moveFrontUpToFrontLeft),
            new Solution(new PartPosition(left, 1, 2),
                    turnZ + moveFrontRightToUpBack + "y U' " + moveFrontUpToFrontRight),
            new Solution(new PartPosition(left, 2, 1),
                    turnZ + "y U2 " + moveFrontUpToFrontRight),

            // right side color found at the front face
            new Solution(new PartPosition(front, 1, 0),
                    turnZ + moveFrontRightToUpBack + "U2 " + moveFrontUpToFrontLeft),
            new Solution(new PartPosition(front, 1, 2),
                    turnZ + moveFrontLeftToUpBack + "U2 " + moveFrontUpToFrontLeft),
            new Solution(new PartPosition(front, 2, 1),
                    turnZ + "y U " + moveFrontUpToFrontRight),

            // right side color found at the right face
            new Solution(new PartPosition(right, 1, 0),
                    noMove),
            new Solution(new PartPosition(right, 1, 2),
                    turnZ + "y " + moveFrontLeftToUpBack + "y' U " + moveFrontUpToFrontLeft),
            new Solution(new PartPosition(right, 2, 1),
                    turnZ + "y " + moveFrontUpToFrontRight),

            // right side color found at the back face
            new Solution(new PartPosition(back, 1, 0),
                    turnZ + "y " + moveFrontLeftToUpBack + "U2 " + moveFrontUpToFrontRight),
            new Solution(new PartPosition(back, 1, 2),
                    turnZ + "y' " + moveFrontRightToUpBack + "y2 " + moveFrontUpToFrontRight),
            new Solution(new PartPosition(back, 2, 1),
                    turnZ + "y U' " + moveFrontUpToFrontRight),

            // right side color found at the down face
            new Solution(new PartPosition(down, 0, 1),
                    turnZ + moveFrontUpToFrontLeft),
            new Solution(new PartPosition(down, 1, 0),
                    turnZ + "U " + moveFrontUpToFrontLeft),
            new Solution(new PartPosition(down, 1, 2),
                    turnZ + "U' " + moveFrontUpToFrontLeft),
            new Solution(new PartPosition(down, 2, 1),
                    turnZ + "U2 " + moveFrontUpToFrontLeft),
    };

    /**
     * Defines the corresponding second edge color for a given face color.
     *
     * The algorithm places always the right edge for each of the four faces
     * before it is rotated to the next face. This array defines the side color
     * of the right edge of each face. It is used to call PositionFinder.findEdge()
     * with the second color as an argument.
     */
    private static final CubeColor[] correspondingEdgeColors = {
            CubeColor.Green, CubeColor.Red, CubeColor.Blue, CubeColor.Orange
    };

    /**
     * Finds the position of the specified middle layer edge.
     *
     * @param steppedCube The Cube with the applied current found solutions steps.
     * @param face The cube's face that is currently the front face. The second color
     *             is always the right neighbor color of the edge. For example: face is green,
     *             so for this algorithm the edge is always at the right of the front
     *             and the second color can only be red.
     * @return A PartPosition object with the absolute coordinates of the searched part.
     */
    protected PartPosition findPosition(Cube steppedCube, CubeColor face) {
        return PositionFinder.findEdge(steppedCube, correspondingEdgeColors[face.ordinal()-1], face);
    }
}
