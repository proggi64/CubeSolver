package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for creating a white cross on a
 * scrambled 3x3 Magic Cube.
 *
 * The White Cross Step is used by the LayerByLayerSolver class.
 * The Layer-By-Layer algorithm is the simplest algorithm for
 * a 3x3 cube and intended to be used by beginners.
 *
 * The white cross includes the mid-parts of each side face. The
 * sequences that position the parts from their current position
 * to the right position are non-destructive for any already existing
 * parts of the white cross.
 */
class WhiteCrossStep extends AbstractSolutionStep {
    /**
     * Sequence that turns the upper front edge of the cross.
     *
     * This sequence turns the edge if it is in the right position, but has the wrong
     * orientation. No other edge of the white cross will be effected. The sequence
     * is valid when the edge is at the front face.
     */
    private static final String turnEdge = "F' U L' U' ";

    /**
     * Sequence that positions the edge from the front left to the top.
     *
     * This sequence positions the edge from the left side of the front
     * to the top.
     */
    private static final String frontLeftToFrontUp = "F ";

    /**
     * Sequence that positions the edge from the front right to the top.
     *
     * This sequence positions the edge from the right side of the front
     * to the top.
     */
    private static final String frontRightToFrontUp = "F' ";

    /**
     * Sequence that positions the edge from the front bottom to the top.
     *
     * This sequence positions the edge from the bottom of the front
     * to the top.
     */
    private static final String frontDownToFrontUp = "F2 ";

    /**
     * Sequence that positions the edge from the down left to the down front.
     */
    private static final String downLeftToFrontDown = "D ";

    /**
     * Sequence that positions the edge from the down right to the down front.
     */
    private static final String downRightToFrontDown = "D' ";

    /**
     * Sequence that positions the edge from the upper back to the down back.
     */
    private static final String upBackToDownBack = "B2 ";

    /**
     * Sequence that positions the edge from the down back to the down front.
     */
    private static final String downBackToDownFront = "D2 ";

    /**
     * Sequence that positions the edge from the down front to the up front.
     */
    private static final String downFrontToUpFront = "F2 ";

    /**
     * Sequence that positions the edge from the left back to the down front.
     *
     * The sequence is non-destructive for parts of the white cross that already
     * exist.
     */
    private static final String leftBackToDownFront = "L' D L ";

    /**
     * Sequence that positions the edge from the right back to the down front.
     *
     * The sequence is non-destructive for parts of the white cross that already
     * exist.
     */
    private static final String rightBackToDownFront = "R D' R' ";

    /**
     * Sequence that positions the edge from the upper back to the front down.
     *
     * The sequence is non-destructive for parts of the white cross that already
     * exist.
     */
    private static final String backUpToFrontDown = "B2 D2 ";

    /**
     * Sequence that positions the edge from the back right to the front down.
     *
     * The sequence is non-destructive for parts of the white cross that already
     * exist.
     */
    private static final String backRightToFrontDown = "B' D2 B ";

    /**
     * Sequence that positions the edge from the back left to the front down.
     *
     * The sequence is non-destructive for parts of the white cross that already
     * exist.
     */
    private static final String backLeftToFrontDown = "B D2 B' ";

    /**
     * Sequence that positions the edge from the up left to the front left position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * Then the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    private static final String upLeftToFrontLeft = "L ";

    /**
     * Sequence that positions the edge from the up right to the front right position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * The the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    private static final String upRightToFrontRight = "R' ";

    /**
     * Initializes a new instance of the WhiteCrossStep class with the
     * specified Cube and CubeFaceRotationsRecords objects.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps. This
     *                must be empty.
     */
    private WhiteCrossStep(final Cube cube, final CubeFaceRotationRecords records) {
        super(cube, records, solutions);
    }

    /**
     * Algorithms for moving an edge to the front upper position.
     *
     * Each possible position and orientation of the edge is specified
     * as a PartPosition object. The PositionFinder.findEdge() method
     * finds the actual position of an edge and the corresponding
     * algorithm can be found in this array. All positions in the array
     * are valid for the default orientation of the cube with the green
     * face as the front and the white face as the top.
     *
     * To use the algorithms with each white edge, the orientation
     * of the cube must be rotated so that the target face is at the
     * front. The PositionTranslator class translates the current position
     * that contains absolute coordinates based on the default orientation
     * to the coordinates when the target face is the front face. So
     * the positions that are used as the key of the solution can be used
     * for all four orientations.
     */
    private static final Solution[] solutions = {
            // main color found at the up face
            new Solution(new PartPosition(up, 2, 1), ""),
            new Solution(new PartPosition(up, 1, 2),
                    upRightToFrontRight +
                    frontRightToFrontUp + turnEdge),
            new Solution(new PartPosition(up, 0, 1),
                    upBackToDownBack +
                    downBackToDownFront +
                    downFrontToUpFront),
            new Solution(new PartPosition(up, 1, 0),
                    upLeftToFrontLeft +
                    frontLeftToFrontUp + turnEdge),

            // main color found at the left face
            new Solution(new PartPosition(left, 0, 1),
                    upLeftToFrontLeft +
                    frontLeftToFrontUp),
            new Solution(new PartPosition(left, 1, 0),
                    leftBackToDownFront +
                    frontDownToFrontUp + turnEdge),
            new Solution(new PartPosition(left, 1, 2),
                    frontLeftToFrontUp),
            new Solution(new PartPosition(left, 2, 1),
                    downLeftToFrontDown +
                    frontDownToFrontUp + turnEdge),

            // main color found at the front face
            new Solution(new PartPosition(front, 0, 1),
                    turnEdge),
            new Solution(new PartPosition(front, 1, 0),
                    frontLeftToFrontUp + turnEdge),
            new Solution(new PartPosition(front, 1, 2),
                    frontRightToFrontUp + turnEdge),
            new Solution(new PartPosition(front, 2, 1),
                    frontDownToFrontUp + turnEdge),

            // main color found at the right face
            new Solution(new PartPosition(right, 0, 1),
                    upRightToFrontRight +
                    frontRightToFrontUp),
            new Solution(new PartPosition(right, 1, 0),
                    frontRightToFrontUp),
            new Solution(new PartPosition(right, 1, 2),
                    rightBackToDownFront +
                    frontDownToFrontUp + turnEdge),
            new Solution(new PartPosition(right, 2, 1),
                    downRightToFrontDown +
                    frontDownToFrontUp + turnEdge),

            // main color found at the back face
            new Solution(new PartPosition(back, 0, 1),
                    backUpToFrontDown +
                     frontDownToFrontUp + turnEdge),
            new Solution(new PartPosition(back, 1, 0),
                    backRightToFrontDown +
                     frontDownToFrontUp + turnEdge),
            new Solution(new PartPosition(back, 1, 2),
                    backLeftToFrontDown +
                     frontDownToFrontUp + turnEdge),
            new Solution(new PartPosition(back, 2, 1),
                    downBackToDownFront  +
                     frontDownToFrontUp + turnEdge),

            // main color found at the down face
            new Solution(new PartPosition(down, 0, 1),
                    frontDownToFrontUp),
            new Solution(new PartPosition(down, 1, 0),
                    downLeftToFrontDown + frontDownToFrontUp),
            new Solution(new PartPosition(down, 1, 2),
                    downRightToFrontDown + frontDownToFrontUp),
            new Solution(new PartPosition(down, 2, 1),
                    downBackToDownFront +
                            frontDownToFrontUp),
    };

    /**
     * Creates a white cross as described for the Layer-by-Layer algorithm.
     *
     * solve() scans the cube to determine which algorithms are needed to
     * get the white cross. The orientation of the corners may be still wrong
     * after the call. This orientation should be corrected by another step class.
     *
     * solve() rotates the cube by the y axis with the white face up from the green front
     * clockwise to the red front in four steps. At each step the
     * corresponding white edge with the front color is located and moved to the correct
     * position. At the end of the method the CubeFaceRotationRecords collection
     * contain all moves to create a white cross at the top of the cube with
     * its correct side edges.
     *
     * The last move is the return to the default orientation of the cube: Green
     * front and white up.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     */
    public static void solve(final Cube cube, final CubeFaceRotationRecords records) {
        WhiteCrossStep step = new WhiteCrossStep(cube, records);
        step.solve();
    }

    /**
     * Finds the position of the specified edge.
     *
     * @param steppedCube The Cube with the applied current found solutions steps.
     * @param face The cube's face that is currently the front face. The second color
     *             is always white.
     * @return A PartPosition object with the absolute coordinates of the searched part.
     */
    protected PartPosition findPosition(final Cube steppedCube, final CubeColor face) {
        return PositionFinder.findEdge(steppedCube, CubeColor.White, face);
    }
}
