package de.webkasi.cube.solver;

/**
 * Encapsulates the PartPosition and the solution for this position
 * as a String of SpeedCube notations steps.
 *
 * Solution is used as position-solution pairs for some solution step
 * classes. Each position of a cube part has a specific solution. When the
 * position is known, the solution can be found.
 */
class Solution {
    final PartPosition position;
    final String moves;

    /**
     * Initializes a new instance of the Solution class.
     *
     * @param position the position of an edge.
     * @param moves The moves in SpeedCube syntax.
     */
    Solution(PartPosition position, String moves) {
        this.position = position;
        this.moves = moves;
    }
}
