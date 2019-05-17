package de.webkasi.cube;

import java.util.Random;

/**
 * Scrambles a Cube based on random values.
 */
public class CubeScrambler {

    /**
     * Scrambles the specified Cube by a specified number of random rotations
     * of random faces.
     *
     * If the cube has more than 3 rows and columns then a random number
     * of layers are rotated with each rotation. The higher the depth the
     * more complex is the solution.
     *
     * @param cube The Cube to scramble
     * @param depth Count of random rotations
     */
    public static void scrambleCube(Cube cube, int depth) {
        Random generator = new Random(System.nanoTime());
        CubeFaceRotater rotater = new CubeFaceRotater(cube);
        int dimension = cube.getDimension();

        for (int i = 0; i < depth; i++) {
            CubeFaceRotationDirection direction = generator.nextInt(2) == 0 ?
                    CubeFaceRotationDirection.Clockwise : CubeFaceRotationDirection.Counterclockwise;
            CubeColor faceColor = CubeColor.values()[generator.nextInt(CubeColor.values().length)];
            int countOfLayers = dimension <= 3 ? 1 : generator.nextInt(dimension / 2) + 1;

            rotater.rotateFace(direction, faceColor, countOfLayers);
        }
    }
}
