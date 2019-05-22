package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for completing a white cross on a
 * scrambled 3x3 Magic Cube.
 */
class WhiteCrossCornersStep {
    /**
     * Rotates the corners of the white cross of the cube so that they have
     * the right orientation.
     *
     * solve() scans the cube to determine which algorithms are needed. For this
     * scan a copy of the cube will be created internally and the steps already
     * contained in the records are executed on it to get the cube's current state
     * after the preceeding steps.
     *
     * @param cube The Cube to solve. The cube must be configured that all needed
     *             pieces of the white cross are already in place. Just the
     *             orientation of the corners may be wrong.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {
        Cube currentCube = CubeFactory.create(cube, records);


    }
}
