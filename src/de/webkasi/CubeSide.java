package de.webkasi;

class CubeSide {
    private int _dimension;
    private CubeColor[][] _fields;

    CubeSide(int dimension) {
        _dimension = dimension;
        _fields = new CubeColor[dimension][dimension];
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
