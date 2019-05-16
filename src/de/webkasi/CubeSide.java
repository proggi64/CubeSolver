package de.webkasi;

class CubeSide {
    private final int _dimension;
    private final CubeColor[][] _fields;

    CubeSide(int dimension) {
        _dimension = dimension;
        _fields = new CubeColor[dimension][dimension];
    }

    @SuppressWarnings("CopyConstructorMissesField")
    CubeSide(CubeSide copy) {
        this(copy._dimension);
        for (int row = 0; row < _dimension; row++)
            System.arraycopy(copy._fields[row], 0, _fields[row], 0, _dimension);
    }

    void setSideColor(CubeColor color) {
        for (int row = 0; row < _dimension; row++)
            for (int column = 0; column < _dimension; column++)
                _fields[row][column] = color;
    }

    void setField(final int row, final int column, CubeColor color) {
        _fields[row][column] = color;
    }

    CubeColor getField(final int row, final int column) {
        return _fields[row][column];
    }
}
