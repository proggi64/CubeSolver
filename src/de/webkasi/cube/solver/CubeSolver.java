package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides a method to solve a magic cube.
 */
public interface CubeSolver {
    /**
     * Solves the specified scrambled magic cube.
     *
     * @param cube The Cube to be solved.
     * @return A CubeFaceRotationRecords object containing the
     * moves for the solution.
     */
    CubeFaceRotationRecords solve(Cube cube);
}
