package de.webkasi.cube;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.webkasi.cube.ui.text.CubeTextDescriptor;

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
    public static void assertCubeFace(Cube cube, CubeColor face, String colors) {
        int dimension = cube.getDimension();
        CubeFace cubeFace = cube.getFace(face);
        assertFace(cubeFace, colors);
    }

    /**
     * Asserts the expected colors of the specified CubeFace object.
     *
     * @param cubeFace The CubeFace the contains the color feilds to test.
     * @param colors The String with the expected color field descritions.
     */
    public static void assertFace(CubeFace cubeFace, String colors) {
        int row = 0;
        int column = 0;
        int dimension = cubeFace.getDimension();
        CubeTextDescriptor descriptor = new CubeTextDescriptor(dimension);
        CubeFace expectedColors = descriptor.describeFace(colors);
        for (row = 0; row < dimension; row++)
            for (column = 0; column < dimension; column++)
                assertEquals(expectedColors.getField(row, column), cubeFace.getField(row, column),
                        String.format("Mismatch Row %d Column %d, Expected: %s, found: %s", row, column,
                                expectedColors.getField(row, column).toString(), cubeFace.getField(row, column).toString()));

    }

}
