package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides a factory for Cube objects with specific states.
 */
class CubeFactory {
    /**
     * Creates a new Cube object based on the specified cube and with
     * all modifications of the specified records.
     *
     * This method is used by the solution steps to create a cube with
     * the state of the previous solution steps. This is needed to scan
     * for the next needed rotations.
     *
     * @param cube The Cube object that is the base for the new object.
     * @param records The CubeFaceRotationRecords object that is played
     *                on the cube's copy before it is returned.
     * @return A new Cube object based on the specified cube with all
     * rotations that are stored in the specified records.
     */
    static Cube create(final Cube cube, final CubeFaceRotationRecords records) {
        Cube cubeCopy = new Cube(cube);
        CubeFaceRotator rotator = new CubeFaceRotator(cubeCopy);
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
        player.play(records);
        return cubeCopy;
    }
}
