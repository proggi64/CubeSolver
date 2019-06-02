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
     * more complex will the solution be.
     *
     * @param cube The Cube to scramble.
     * @param depth Count of random rotations.
     */
    public static void scrambleCube(Cube cube, int depth) {
        Random generator = new Random(System.nanoTime());
        CubeFaceRotator rotater = new CubeFaceRotator(cube);
        int dimension = cube.getDimension();

        for (int i = 0; i < depth; i++) {
            RotationDirection direction = generator.nextInt(2) == 0 ?
                    RotationDirection.Clockwise : RotationDirection.Counterclockwise;
            int face = generator.nextInt(CubeColor.values().length);
            int countOfLayers = dimension <= 3 ? 1 : generator.nextInt(dimension / 2) + 1;

            rotater.rotateFace(direction, face, countOfLayers);
        }
    }

    /**
     * Generates a CubeFaceRotationRecords object with random rotations.
     *
     * @param depth Count of random rotations.
     * @param dimension Count of dimensions of the cube to scramble,
     * @return A CubeFaceRotationRecords object containing the random moves
     * that has been generated.
     */
    public static CubeFaceRotationRecords scrambleCube(int depth, int dimension) {
        Random generator = new Random(System.nanoTime());
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        for (int i = 0; i < depth; i++) {
            RotationDirection direction = generator.nextInt(2) == 0 ?
                    RotationDirection.Clockwise : RotationDirection.Counterclockwise;
            int face = generator.nextInt(CubeColor.values().length);
            int countOfLayers = dimension <= 3 ? 1 : generator.nextInt(dimension / 2) + 1;

            CubeFaceRotationRecord record = new CubeFaceRotationRecord(face, direction, countOfLayers);
            records.add(record);
        }
        return records;
    }
}
