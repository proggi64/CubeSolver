package de.webkasi;

import java.util.stream.IntStream;

class Cube {
    private final int _dimension;
    private final CubeSide[] _sides;

    Cube() {
        this(3);
    }

    Cube(int dimension) {
        _dimension = dimension;
        _sides = new CubeSide[6];
        IntStream.rangeClosed(0, 5).forEach(side ->
        {
            _sides[side] = new CubeSide(_dimension);
            _sides[side].setSideColor(CubeColor.values()[side]);
        });
    }

    int getDimension() {
        return _dimension;
    }

    CubeSide[] getSides() {
        return _sides;
    }
}
