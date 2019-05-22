package de.webkasi.cube.ui.text;

import de.webkasi.cube.*;

/**
 * Provides a method to describe the state of a Cube as
 * a sequence of characters that represent the colors of its fields.
 */
public class CubeTextDescriptor {
    private final int _dimension;

    /**
     * Initializes a new instance of the CubeTextDescriptor class
     * for a 3x3 cube.
     */
    public CubeTextDescriptor() {
        this(3);
    }

    /**
     * Initializes a new instance of the CubeTextDescriptor class
     * for a cube with the specified dimension.
     *
     * @param dimension The dimension of the cube.
     */
    public CubeTextDescriptor(int dimension) {
        _dimension = dimension;
    }

    /**
     * Sets the complete specified Cube to the colors that are specified
     * in the faces String array.
     *
     * @param cube The Cube where the face colors should be set.
     * @param faces An array of six String that contain the descriptions
     *              of the faces of the cube as upper-case characters.
     *              Each color is described a single upper-case character
     *              that is the first characzer of the color's name.
     *              (W)hite, (O)range, (G)reen, (R)ed, (B)lue, and (Y)ellow.
     *              The configuration must match the possible color combinations
     *              of a magic cube in order to be solved. Each other character
     *              is ignored an can be used for formatting.
     */
    public void describeCube(Cube cube, String[] faces) {
        for (int i = 0; i < faces.length; i++) {
            CubeFace describedFace = describeFace(faces[i]);
            CubeFace cubeFace = cube.getFaceByIndex(i);
            cubeFace.setFace(describedFace);
        }
    }

    /**
     * Generates a single face description from the specified String.
     *
     * @param faceColors A String containing the field colors of a cube's face.
     *                   Each color is described a single upper-case character
     *                   that is the first characzer of the color's name.
     *                   (W)hite, (O)range, (G)reen, (R)ed, (B)lue, and (Y)ellow.
     *                   The configuration must match the possible color combinations
     *                   of a magic cube in order to be solved. Each other character
     *                   is ignored an can be used for formatting.
     * @return A two-dimensional array of CubeColor values describing the cube's face.
     */
    public CubeFace describeFace(String faceColors) {
        int row = 0;
        int column = 0;
        CubeFace face = new CubeFace(_dimension);
        for (int i = 0; i < faceColors.length() && row < _dimension; i++) {
            switch (faceColors.charAt(i)) {
                case 'W':
                    face.setField(row, column, CubeColor.White);
                    break;
                case 'O':
                    face.setField(row, column, CubeColor.Orange);
                    break;
                case 'G':
                    face.setField(row, column, CubeColor.Green);
                    break;
                case 'R':
                    face.setField(row, column, CubeColor.Red);
                    break;
                case 'B':
                    face.setField(row, column, CubeColor.Blue);
                    break;
                case 'Y':
                    face.setField(row, column, CubeColor.Yellow);
                    break;
                default:
                    continue;
            }
            column++;
            if (column >= _dimension) {
                column = 0;
                row++;
            }
        }
        return face;
    }
}
