package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for creating a white cross on a
 * scrambled 3x3 Magic Cube.
 */
class WhiteCrossStep {

    private final Cube _cube;
    private final CubeFaceRotationRecords _records;
    private final CubeOrientation _orientation;

    /**
     * Sequence that turns the edge of the cross.
     *
     * This sequence turns the edge if it is in the right position, but has the wrong
     * orientation. No other edge of the white cross will be effected. The sequence
     * is valid when the edge is at the front face. When using for other edges, the cube
     * must be rotated into the right position first.
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
     * This sequence positions the edge from th bottom of the front
     * to the top.
     */
    private static final String frontDownToFrontUp = "F2 ";

    /**
     * Sequence that positions the edge from the back middle left to the left front.
     */
    private static final String backEquatorLeftToFrontLeft = "E ";

    /**
     * Sequence that positions the edge from the back middle right to the right front.
     */
    private static final String backEquatorRightToFrontRight = "E' ";

    /**
     * Sequence that positions the edge from the down left to the down front.
     */
    private static final String downLeftToFrontDown = "D ";

    /**
     * Sequence that positions the edge from the down right to the down front.
     */
    private static final String downRightToFrontDown = "D' ";

    /**
     * Sequence that positions the edge from the down back to the down front.
     */
    private static final String downBackToFrontDown = "D2 ";

    /**
     * Sequence that positions the edge from the back up to the back left position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * Then the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    private static final String upBackToBackLeft = "B ";

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
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps. This
     *                must be empty.
     */
    private WhiteCrossStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _records = records;
        _orientation = new CubeOrientation();
    }

    /**
     * Encapsulates the PartPosition and the solution for this position
     * as a String of SpeedCube notations steps.
     *
     * Solution is used for a state-solution pairs for the WhiteCrossStep
     * class. Each position of an edge has a specific solution. When the
     * position is known, the solution can be found.
     */
    static class Solution {
        final PartPosition position;
        final String moves;

        /**
         * @param position the position of an edge.
         * @param moves The moves in SpeedCube syntax.
         */
        Solution(PartPosition position, String moves) {
            this.position = position;
            this.moves = moves;
        }
    }

    private static final int up = CubeColor.White.ordinal();
    private static final int left = CubeColor.Orange.ordinal();
    private static final int front = CubeColor.Green.ordinal();
    private static final int right = CubeColor.Red.ordinal();
    private static final int back = CubeColor.Blue.ordinal();
    private static final int down = CubeColor.Yellow.ordinal();

    /**
     * Algorithms for moving an edge to the front upper position.
     *
     * Each possible position and orientation of the edge is specified
     * as an PartPosition object. The PositionFinder.FindEdge() method
     * finds the actual position of an edge and the corresponding
     * algorithm can be found in this array.
     *
     * To use the algorithms with each white edge the orientation
     * of the cube must be rotated  so that the target position is at the
     * upper front.
     */
    private static final Solution[] solutions = {
            // main color found at the top face
            new Solution(new PartPosition(up, 2, 1), ""),
            new Solution(new PartPosition(up, 1, 2),
                    upRightToFrontRight +
                    frontRightToFrontUp + turnEdge),
            new Solution(new PartPosition(up, 0, 1),
                    upBackToBackLeft +
                    backEquatorLeftToFrontLeft +
                    frontLeftToFrontUp + turnEdge),
            new Solution(new PartPosition(up, 1, 0),
                    upLeftToFrontLeft +
                    frontLeftToFrontUp + turnEdge),

            // main color found at the left face
            new Solution(new PartPosition(left, 0, 1),
                    upLeftToFrontLeft +
                    frontLeftToFrontUp),
            new Solution(new PartPosition(left, 1, 0),
                    backEquatorLeftToFrontLeft +
                    frontLeftToFrontUp + turnEdge),
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
                    backEquatorRightToFrontRight +
                    frontRightToFrontUp + turnEdge),
            new Solution(new PartPosition(right, 2, 1),
                    downRightToFrontDown +
                    frontDownToFrontUp + turnEdge),

            // main color found at the back face
            new Solution(new PartPosition(back, 0, 1),
                    upBackToBackLeft +
                     backEquatorLeftToFrontLeft + frontLeftToFrontUp),
            new Solution(new PartPosition(back, 1, 0),
                    backEquatorRightToFrontRight + frontRightToFrontUp),
            new Solution(new PartPosition(back, 1, 2),
                    backEquatorLeftToFrontLeft +
                     frontLeftToFrontUp),
            new Solution(new PartPosition(back, 2, 1),
                    downBackToFrontDown  +
                     frontDownToFrontUp + turnEdge),

            // main color found at the down face
            new Solution(new PartPosition(down, 0, 1),
                    frontDownToFrontUp),
            new Solution(new PartPosition(down, 1, 0),
                    downLeftToFrontDown + frontDownToFrontUp),
            new Solution(new PartPosition(down, 1, 2),
                    downRightToFrontDown + frontDownToFrontUp),
            new Solution(new PartPosition(down, 2, 1),
                    downBackToFrontDown +
                            frontDownToFrontUp),
    };

    /**
     * Creates a white cross as described for the Layer-by-Layer algorithm.
     *
     * solve() scans the cube to determine which algorithms are needed to
     * get the white cross. The orientation of the corners may be still wrong
     * after the call. This orientation should be corrected by another step class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     */
    public static void solve(Cube cube, CubeFaceRotationRecords records) {
        WhiteCrossStep step = new WhiteCrossStep(cube, records);
        step.solve();
    }

    private static final CubeColor[] faces = { CubeColor.Green, CubeColor.Red, CubeColor.Blue, CubeColor.Orange };

    /**
     * Solves the White Cross Step for the given cube.
     *
     * solve() rotates the cube with the white face up from the green front
     * counterclockwise to the orange front in four steps. At each step the
     * white edge with the front color is loacted and moved to the correct
     * position. At the end of the method the CubeFaceRotationRecords collection
     * contain all moves to create a white cross at the top of the cube with
     * its correct side edges.
     *
     * The last move is the return to the default orientation of the cube: Green
     * front and white up.
     */
    private void solve() {
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(_records);
        String cubeRotations = "";

        for (int i = 0; i < 4; i++) {
            Cube steppedCube = CubeFactory.create(_cube, _records);

            PartPosition edgePosition = PositionFinder.FindEdge(steppedCube, CubeColor.White, faces[i]);
            String solutionMoves = cubeRotations + findSolutionFor(edgePosition);
            interpreter.addMoves(solutionMoves);

            // Go to the next front face
            _orientation.rotate('y', RotationDirection.Clockwise, 1);
            cubeRotations += "y ";
        }
    }

    /**
     * Finds the solution for a specific edge position.
     *
     * For each possible edge position exists a sequence of moves.
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
    private String findSolutionFor(PartPosition position) {
        int i = 0;
        PartPosition translatedPosition = PositionTranslator.translate(position, _orientation);
        while (true) {
            if (translatedPosition.isEqual(solutions[i].position))
                return solutions[i].moves;
            i++;
        }
    }


}
