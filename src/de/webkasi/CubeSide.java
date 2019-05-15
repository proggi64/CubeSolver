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
        for (int i = 0; i < _dimension; i++)
            System.arraycopy(copy._fields[i], 0, _fields[i], 0, _dimension);
    }

    void setSideColor(CubeColor color) {
        for (int i = 0; i < _dimension; i++)
            for (int j = 0; j < _dimension; j++)
                _fields[i][j] = color;
    }

    CubeColor[][] getFields() {
        return _fields;
    }
}
