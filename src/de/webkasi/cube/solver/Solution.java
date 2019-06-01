package de.webkasi.cube.solver;

/**
 * Encapsulates the PartPosition and the solution for this position
 * as a String of SpeedCube notations steps.
 *
 * Solution is used for a state-solution pairs for the WhiteCrossStep
 * class. Each position of an edge has a specific solution. When the
 * position is known, the solution can be found.
 */
class Solution {
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
