package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for creating a white cross on a
 * scrambled 3x3 Magic Cube.
 */
class WhiteCrossStep {

    private final Cube _cube;
    private final CubeFaceRotationRecords _records;

    /**
     * Sequence that turns the edge of the cross.
     *
     * This sequence turns the edge if it is in the right position, but has the wrong
     * orientation. No other edge of the white cross will be effected. The sequence
     * is valid when the edge is at the front face. When using for other edges, the cube
     * must be rotated into the right position first.
     */
    private static final String turnEdge = "F' U L' U'";

    /**
     * Sequence that positions the edge from the front left to the top.
     *
     * This sequence positions the edge from the left side of the front
     * to the top.
     */
    private static final String frontLeftToFrontUp = "F";

    /**
     * Sequence that positions the edge from the front right to the top.
     *
     * This sequence positions the edge from the right side of the front
     * to the top.
     */
    private static final String frontRightToFrontUp = "F'";

    /**
     * Sequence that positions the edge from the front bottom to the top.
     *
     * This sequence positions the edge from th bottom of the front
     * to the top.
     */
    private static final String frontDownToFrontUp = "F2";

    /**
     * Sequence that positions the edge from the back middle left to the left front.
     */
    private static final String backEquatorLeftToFrontLeft = "E";

    /**
     * Sequence that positions the edge from the back middle right to the right front.
     */
    private static final String backEquatorRightToFrontRight = "E'";

    /**
     * Sequence that positions the edge from the down left to the down front.
     */
    private static final String downLeftToFrontDown = "D";

    /**
     * Sequence that positions the edge from the down right to the down front.
     */
    private static final String downRightToFrontDown = "D'";

    /**
     * Sequence that positions the edge from the down back to the down front.
     */
    private static final String downBackToFrontDown = "D2";

    /**
     * Sequence that positions the edge from the back up to the back left position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * Then the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    private static final String upBackToBackLeft = "B";

    /**
     * Sequence that positions the edge from the up left to the front left position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * Then the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    private static final String upLeftToFrontLeft = "L";

    /**
     * Sequence that positions the edge from the up right to the front right position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * The the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    private static final String upRightToFrontRight = "R'";

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
    }

    /**
     * Encapsulates the EdgePosition and the solution for this position
     * as a String of SpeedCube notations steps.
     *
     * Solution is used for a state-solution pairs for the WhiteCrossStep
     * class. Each position of an edge has a specific solution. When the
     * position is known, the solution can be found.
     */
    static class Solution {
        final EdgePosition position;
        final String moves;

        /**
         * @param position the position of an edge.
         * @param moves The moves in SpeedCube syntax.
         */
        Solution(EdgePosition position, String moves) {
            this.position = position;
            this.moves = moves;
        }
    }

    private static final String space = " ";
    private static final int up = CubeColor.White.ordinal();
    private static final int left = CubeColor.Orange.ordinal();

    private static final Solution[] solutions = {
            // found at the top face
            new Solution(new EdgePosition(up, 2, 1), ""),
            new Solution(new EdgePosition(up, 1, 2),
                    upRightToFrontRight + space +
                    frontRightToFrontUp + space + turnEdge),
            new Solution(new EdgePosition(up, 0, 1),
                    upBackToBackLeft + space +
                    backEquatorLeftToFrontLeft + space +
                    frontLeftToFrontUp + space + turnEdge),
            new Solution(new EdgePosition(up, 1, 0),
                    upLeftToFrontLeft + space +
                    frontLeftToFrontUp + space + turnEdge),

            // found at the left face
            new Solution(new EdgePosition(left, 0, 1),
                    upLeftToFrontLeft + space +
                    frontLeftToFrontUp),
            new Solution(new EdgePosition(left, 1, 0),
                    backEquatorLeftToFrontLeft + space +
                    frontLeftToFrontUp + space + turnEdge),
            new Solution(new EdgePosition(left, 1, 2),
                    frontLeftToFrontUp),
            new Solution(new EdgePosition(left, 2, 1),
                    downLeftToFrontDown + space +
                    frontDownToFrontUp + space + turnEdge),
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
        // TODO für alle weißen Randsteine:
        // TODO Situationsanalyse: Wo ist der weiße Randstein?
        // TODO Drehe den Würfel so, dass der jeweilige Ziel-Randstein vorne oben sein soll (CubeOrientation)
        // TODO Weißen Randstein ohne Orientierung zwischen Weiß und passende Farbe
        // TODO Bewegungsalgorithmus für jede Situation anwenden: Stringsequenzen für die jeweilige Position zusammenstellen
        // TODO Prüfen, ob Orientierung passt
        // TODO Falls nicht: turnEdge für betroffenen Randstein

        // 1. WG 2. Y WR 3. Y WB 4. Y WO

        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        EdgePosition whiteGreenPosition = PositionFinder.FindEdge(cube, CubeColor.White, CubeColor.Green);
        String whiteGreenSolutionMoves = step.findSolution(whiteGreenPosition);

        interpreter.addMoves(whiteGreenSolutionMoves);

        // TODO Züge ermitteln und auf dem temporären Cube ausführen
        Cube temporaryCube = CubeFactory.create(cube, records);
        // TODO Front auf Rot drehen und nächsten Rand lösen

    }

    /**
     * Finds the solution for a specific edge position.
     *
     * For each possible edge position exists a sequence of moves.
     * These moves are stored in an internal array of Solution
     * objects. The array is searches for the position and the
     * corresponding solution ist returned.
     *
     * @param position The EdgePosition to find the solution for.
     * @return A String with the solution steps in SpeedCube syntax.
     */
    private String findSolution(EdgePosition position) {
        int i = 0;
        while (true) {
            if (solutions[i].position.isEqual(position))
                return solutions[i].moves;
            i++;
        }
    }


}
