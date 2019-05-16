package de.webkasi;

import java.util.stream.IntStream;

class Cube {
    private final int _dimension;
    private final CubeFace[] _sides;

    Cube() {
        this(3);
    }

    Cube(int dimension) {
        _dimension = dimension;
        _sides = new CubeFace[6];
        IntStream.rangeClosed(0, 5).forEach(side ->
        {
            _sides[side] = new CubeFace(_dimension);
            _sides[side].setSideColor(CubeColor.values()[side]);
        });
    }

    int getDimension() {
        return _dimension;
    }

    CubeFace[] getSides() {
        return _sides;
    }
}
