package de.webkasi;

class CubeLayerMover {
    private Cube _cube;
    private CubeSide[][] _topToSides;
    private int _sideRotations[][];
    private CubeLayerMoveDirection _sideRotationDirections[][];

    CubeLayerMover(Cube cube) {
        _cube = cube;
        CubeSide[] sides = _cube.getSides();
        final int White     = CubeColor.White.ordinal();
        final int Orange    = CubeColor.Orange.ordinal();
        final int Green     = CubeColor.Green.ordinal();
        final int Red       = CubeColor.Red.ordinal();
        final int Blue      = CubeColor.Blue.ordinal();
        final int Yellow    = CubeColor.Yellow.ordinal();

        _topToSides = new CubeSide[][] {
                { sides[Orange], sides[Green], sides[Red], sides[Blue] },   // White
                { sides[White], sides[Blue], sides[Yellow], sides[Green] }, // Orange
                { sides[White], sides[Orange], sides[Yellow], sides[Red] }, // Green
                { sides[White], sides[Green], sides[Yellow], sides[Blue] }, // Red
                { sides[White], sides[Red], sides[Yellow], sides[Orange] }, // Blue
                { sides[Orange], sides[Blue], sides[Red], sides[Green] }    // Yellow
        };

        _sideRotations = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
        };

        _sideRotationDirections = new CubeLayerMoveDirection[][] {
                {
                    CubeLayerMoveDirection.Clockwise,
                        CubeLayerMoveDirection.Clockwise,
                        CubeLayerMoveDirection.Clockwise,
                        CubeLayerMoveDirection.Clockwise
                },
                {
                    CubeLayerMoveDirection.Clockwise,
                        CubeLayerMoveDirection.Counterclockwise,
                        CubeLayerMoveDirection.Clockwise,
                        CubeLayerMoveDirection.Clockwise
                },
        };
    }

    void move(CubeLayerMoveDirection direction, final CubeColor side, final int countOfLayers) {
        if (direction == CubeLayerMoveDirection.Clockwise)
            rotateClockwise(side, countOfLayers);
        else
            rotateCounterclockwise(side, countOfLayers);
    }

    private void rotateClockwise(final CubeColor side, final int rows) {
        rotateTopClockwise(_cube.getSides()[side.ordinal()]);

        for (int row = 0; row < rows; row++) {
            shiftSideLayerClockwise(side, row);
        }
    }

    private void rotateTopClockwise(CubeSide targetSide) {
        final CubeSide sourceSide = new CubeSide(targetSide);

        int maxIndex = _cube.getDimension() - 1;

        int targetColumnIndex = maxIndex;
        for (int sourceRowIndex = 0; sourceRowIndex <= maxIndex; sourceRowIndex++) {
            int targetRowIndex = 0;
            for (int sourceColumnIndex = 0; sourceColumnIndex <= maxIndex; sourceColumnIndex++) {
                targetSide.setField(targetRowIndex, targetColumnIndex,
                        sourceSide.getField(sourceRowIndex, sourceColumnIndex));
                targetRowIndex++;
            }
            targetColumnIndex--;
        }
    }

    private void rotateCounterclockwise(final CubeColor side, final int rows) {
        rotateTopCounterclockwise(_cube.getSides()[side.ordinal()]);

        for (int row = 0; row < rows; row++) {
            shiftSideLayerCounterclockwise(side, row);
        }
    }

    private void rotateTopCounterclockwise(CubeSide targetSide) {
        final CubeSide sourceSide = new CubeSide(targetSide);

        int maxIndex = _cube.getDimension() - 1;

        int targetColumnIndex = 0;
        for (int sourceRowIndex = 0; sourceRowIndex <= maxIndex; sourceRowIndex++) {
            int targetRowIndex = maxIndex;
            for (int sourceColumnIndex = 0; sourceColumnIndex <= maxIndex; sourceColumnIndex++) {
                targetSide.setField(targetRowIndex, targetColumnIndex,
                        sourceSide.getField(sourceRowIndex, sourceColumnIndex));
                targetRowIndex--;
            }
            targetColumnIndex++;
        }
    }

    private void shiftSideLayerClockwise(final CubeColor side, final int row) {
        CubeSide[] sidesToShift = _topToSides[side.ordinal()];
        CubeSide[] sources = new CubeSide[] {
                new CubeSide(sidesToShift[0]),
                new CubeSide(sidesToShift[1]),
                new CubeSide(sidesToShift[2]),
                new CubeSide(sidesToShift[3])
        };
        NormalizeSides(side, sources);

        int maxIndex = _cube.getDimension() - 1;

        for (int targetSideIndex = 3; targetSideIndex >= 0; targetSideIndex--) {
            int sourceSideIndex = (targetSideIndex + 1) % 4;
            CubeSide targetSide = sidesToShift[targetSideIndex];
            final CubeSide sourceSide = sources[sourceSideIndex];

            for (int column = 0; column <= maxIndex; column++)
                targetSide.setField(row, column, sourceSide.getField(row, column));
        }

        DenormalizeSides(side, sidesToShift);
    }

    private void shiftSideLayerCounterclockwise(final CubeColor side, final int row) {
        CubeSide[] sidesToShift = _topToSides[side.ordinal()];
        CubeSide[] originals = new CubeSide[] {
                new CubeSide(sidesToShift[0]),
                new CubeSide(sidesToShift[1]),
                new CubeSide(sidesToShift[2]),
                new CubeSide(sidesToShift[3])
        };
        int maxIndex = _cube.getDimension() - 1;

        for (int sourceSideIndex = 0; sourceSideIndex <= 3; sourceSideIndex++) {
            int targetSideIndex = (sourceSideIndex + 1) % 4;
            CubeSide targetSide = sidesToShift[targetSideIndex];
            final CubeSide sourceSide = originals[sourceSideIndex];

            for (int column = 0; column <= maxIndex; column++)
                targetSide.setField(row, column, sourceSide.getField(row, column));
        }
    }

    private void NormalizeSides(CubeColor side, CubeSide[] sides) {
        int sideIndex = side.ordinal();
        int[] countOfRotations = _sideRotations[sideIndex];
        CubeLayerMoveDirection[] directions = _sideRotationDirections[sideIndex];

        for (int shiftSideIndex = 0; shiftSideIndex < 4; shiftSideIndex++) {
            for (int count = 0; count < countOfRotations[shiftSideIndex]; count++) {
                if (directions[shiftSideIndex] == CubeLayerMoveDirection.Clockwise)
                    rotateTopClockwise(sides[shiftSideIndex]);
                else
                    rotateTopCounterclockwise(sides[shiftSideIndex]);
            }
        }
    }

    private void DenormalizeSides(CubeColor side, CubeSide[] sides) {
        int sideIndex = side.ordinal();
        int[] countOfRotations = _sideRotations[sideIndex];
        CubeLayerMoveDirection[] directions = _sideRotationDirections[sideIndex];

        for (int shiftSideIndex = 0; shiftSideIndex < 4; shiftSideIndex++) {
            for (int count = 0; count < countOfRotations[shiftSideIndex]; count++) {
                if (directions[shiftSideIndex] == CubeLayerMoveDirection.Clockwise)
                    rotateTopCounterclockwise(sides[shiftSideIndex]);
                else
                    rotateTopClockwise(sides[shiftSideIndex]);
            }
        }
    }
}
