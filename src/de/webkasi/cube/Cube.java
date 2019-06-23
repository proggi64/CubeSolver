package de.webkasi.cube;

/**
 * Represents a magic cube.
 *
 * The default dimensions are 3 as the original Rubik's Cube.
 * You can specify any other number of dimensions as an constructor
 * argument. The six faces are initialized with the original colors
 * defined in the CubeColor enumeration.
 */
public class Cube {
    private final int _dimension;
    private final CubeFace[] _faces = new CubeFace[6];

    /**
     * Initializes a 3x3 magic cube.
     */
    public Cube() {
        this(3);
    }

    /**
     * Initializes a magic cube with the specified count of rows and columns.
     *
     * Creates all six faces and sets all fields of each face to its initial color.
     *
     * @param dimension Count of rows and columns of each face of the
     *                  cube.
     */
    public Cube(int dimension) {
        _dimension = dimension;
        CubeColor[] colors = CubeColor.values();
        for (int faceIndex = 0; faceIndex < _faces.length; faceIndex++) {
            _faces[faceIndex] = new CubeFace(_dimension);
            _faces[faceIndex].setFaceColor(colors[faceIndex]);
        }
    }

    /**
     * Creates a copy of the specified Cube.
     *
     * The new instance uses a copy of all internal data, so it is completely
     * independent after the copy and can be manipulated without any effect
     * to the source.
     *
     * @param copy The source of the copy.
     */
    public Cube(Cube copy) {
        _dimension = copy._dimension;
        for (int faceIndex = 0; faceIndex < _faces.length; faceIndex++) {
            _faces[faceIndex] = new CubeFace(copy._faces[faceIndex]);
        }

    }

    /**
     * Gets the count of rows and columns of the magic cube.
     *
     * That value cannot be changed during the lifetime of the Cube's instance.
     * It can only be passed to the constructor.
     *
     * @return The count of rows and columns of the magic cube.
     */
    public int getDimension() {
        return _dimension;
    }

    /**
     * Gets the six CubeFace objects of the cube.
     *
     * For the initial colors the following values are valid:
     * Index 0 is white, 1 is orange, 2 is green, 3 is red, 4 is blue,
     * and 5 is yellow. When specifying a face as an argument then it
     * is done by the CubeColor enumeration value that corresponds to
     * these index values, even if the actual colors are rotated away
     * (only for even dimensions possible).
     *
     * @return An array of six CubeFace objects representing the magic cube's
     * faces.
     */
    CubeFace[] getFaces() {
        return _faces;
    }

    /**
     * Gets the specified CubeFace by its numeric index.
     *
     * For the initial orientation of the cube the index values mean:
     * 0 is white, 1 is orange, 2 is green, 3 is red, 4 is blue,
     * and 5 is yellow.
     *
     * @param faceIndex The index (0 to 5) of the face to get. The color
     *                  of the face corresponds to CubeColor values.
     * @return A CubeFace object representing the current matrix of
     * color fields of the face.
     */
    public CubeFace getFaceByIndex(int faceIndex) {
        return _faces[faceIndex];
    }

    /**
     * Gets the specified CubeFace by its color.
     *
     * The color specifies the initial color of the face when the
     * cube has been created.
     *
     * @param face The CubeColor that represents the cube's face.
     * @return A CubeFace object representing the current matrix of
     * color fields of the face.
     */
    public CubeFace getFace(CubeColor face) { return _faces[face.ordinal()]; }
}
