package de.webkasi.cube;

/**
 * Represents one face of a magic cube with a matrix of CubeColor fields.
 */
class CubeFace {
    private final int _dimension;
    private final CubeColor[][] _fields;

    /**
     * Initializes a new instance of the CubeFace class.
     *
     * @param dimension The count of rows and columns of each face of the cube
     */
    CubeFace(int dimension) {
        _dimension = dimension;
        _fields = new CubeColor[dimension][dimension];
    }

    /**
     * Initializes a new instance of the CubeFace class with a copy
     * of the specified instance.
     *
     * The copy constructor is used for several rotation operations.
     *
     * @param copy The source of the copy of the new instance of CubeFace
     */
    @SuppressWarnings("CopyConstructorMissesField")
    CubeFace(CubeFace copy) {
        this(copy._dimension);
        for (int row = 0; row < _dimension; row++)
            System.arraycopy(copy._fields[row], 0, _fields[row], 0, _dimension);
    }

    /**
     * Initializes this face with the specified color.
     *
     * @param color The CubeColor each field of the cube face will get
     */
    void setFaceColor(CubeColor color) {
        for (int row = 0; row < _dimension; row++)
            for (int column = 0; column < _dimension; column++)
                _fields[row][column] = color;
    }

    /**
     * Sets the CubeColor of the specified field of the face.
     *
     * @param row Index of the row of the field
     * @param column Index of the column of the field
     * @param color New CubeColor of the specified field
     */
    void setField(final int row, final int column, CubeColor color) {
        _fields[row][column] = color;
    }

    /**
     * Gets the CubeColor of the specified field of the face.
     *
     * @param row Index of the row of the field
     * @param column Index of the column of the field
     * @return Current CubeColor of the specified field
     */
    CubeColor getField(final int row, final int column) {
        return _fields[row][column];
    }
}
