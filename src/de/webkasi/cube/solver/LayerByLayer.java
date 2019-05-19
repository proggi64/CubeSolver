package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides a LbL-solution (Layer-by-Layer) for a given Cube.
 */
public class LayerByLayer implements CubeSolver {
    /**
     * Solves the specified cube and describes the solution
     * in the returned String.
     *
     * @param cube The cube that should be solved
     * @return A CubeFaceRotationRecords object containing the
     * moves for the solution.
     */
    @Override
    public CubeFaceRotationRecords solve(Cube cube) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        solve(cube, records);
        return records;
    }

    private static void solve(Cube cube, CubeFaceRotationRecords records) {
        // TODO implement!
    }
}
