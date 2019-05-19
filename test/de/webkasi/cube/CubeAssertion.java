package de.webkasi.cube;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CubeAssertion {
    /**
     * Asserts the expected colors of the cube's face by specifying
     * a String with one character for each field.
     *
     * The valid characters are: W for white, O for orange,
     * G for green, R for red, B for blue, and Y for yellow.
     * The count of valid characters must match the dimension
     * of the cube. A 3x3 cube's face may look like "WYW GBG RRO".
     * Invalid characters are ignored and may be used for
     * formatting.
     *
     * @param cube The Cube to test.
     * @param face The CubeColor specifiying the face to test.
     * @param colors A String that encodes the exppected colors of the
     *               cube's face.
     */
    public static void assertFace(Cube cube, CubeColor face, String colors) {
        int dimension = cube.getDimension();
        CubeFace cubeFace = cube.getFace(face);
        int row = 0;
        int column = 0;
        CubeColor[][] expectedColors = new CubeColor[dimension][dimension];
        for (int i = 0; i < colors.length(); i++) {
            switch (colors.charAt(i)) {
                case 'W':
                    expectedColors[row][column] = CubeColor.White;
                    break;
                case 'O':
                    expectedColors[row][column] = CubeColor.Orange;
                    break;
                case 'G':
                    expectedColors[row][column] = CubeColor.Green;
                    break;
                case 'R':
                    expectedColors[row][column] = CubeColor.Red;
                    break;
                case 'B':
                    expectedColors[row][column] = CubeColor.Blue;
                    break;
                case 'Y':
                    expectedColors[row][column] = CubeColor.Yellow;
                    break;
                default:
                    continue;
            }
            column++;
            if (column >= dimension) {
                column = 0;
                row++;
            }
        }
        int dim = expectedColors.length;
        for (row = 0; row < dim; row++)
            for (column = 0; column < dim; column++)
                assertEquals(expectedColors[row][column], cubeFace.getField(row, column),
                        String.format("Mismatch Row %d Column %d, Expected: %s, found: %s", row, column,
                                expectedColors[row][column].toString(), cubeFace.getField(row, column).toString()));
    }
}
