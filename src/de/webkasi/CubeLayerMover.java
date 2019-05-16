package de.webkasi;

class CubeLayerMover {
    private final Cube _cube;
    private final CubeSide[][] _topToSides;
    private static final int[][] _sideRotations = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 1, 2},
            {1, 1, 1, 1},
            {1, 2, 1, 0},
            {2, 2, 2, 2},
    };
    private static final CubeLayerMoveDirection[][] _sideRotationDirections = new CubeLayerMoveDirection[][]{
            // White (top left 0 0)
            {
                    CubeLayerMoveDirection.None,                // Orange (left)
                    CubeLayerMoveDirection.None,                // Green
                    CubeLayerMoveDirection.None,                // Red
                    CubeLayerMoveDirection.None                 // Blue
            },
            // Orange (top left 0 0)
            {
                    CubeLayerMoveDirection.Counterclockwise,    // Blue (left)
                    CubeLayerMoveDirection.Clockwise,           // Yellow
                    CubeLayerMoveDirection.Clockwise,           // Green
                    CubeLayerMoveDirection.Clockwise            // White
            },
            // Green (top left 0 0)
            {
                    CubeLayerMoveDirection.Counterclockwise,    // Orange (left)
                    CubeLayerMoveDirection.None,                // Yellow
                    CubeLayerMoveDirection.Clockwise,           // Red
                    CubeLayerMoveDirection.Clockwise            // White
            },
            // Red (top left 0 0)
            {
                    CubeLayerMoveDirection.Counterclockwise,    // Green (left)
                    CubeLayerMoveDirection.Counterclockwise,    // Yellow
                    CubeLayerMoveDirection.Clockwise,           // Blue
                    CubeLayerMoveDirection.Counterclockwise     // White
            },
            // Blue (top left 0 0)
            {
                    CubeLayerMoveDirection.Counterclockwise,    // Red (left)
                    CubeLayerMoveDirection.Clockwise,           // Yellow
                    CubeLayerMoveDirection.Clockwise,           // Orange
                    CubeLayerMoveDirection.Counterclockwise     // White
            },
            // Yellow (top left 0 0)
            {
                    CubeLayerMoveDirection.Clockwise,           // Orange (left)
                    CubeLayerMoveDirection.Clockwise,           // Blue
                    CubeLayerMoveDirection.Clockwise,           // Red
                    CubeLayerMoveDirection.Clockwise            // Green
            }
    };

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
                { sides[Blue], sides[Yellow], sides[Green], sides[White] }, // Orange
                { sides[Orange], sides[Yellow], sides[Red], sides[White] }, // Green
                { sides[Green], sides[Yellow], sides[Blue], sides[White] }, // Red
                { sides[Red], sides[Yellow], sides[Orange], sides[White] }, // Blue
                { sides[Orange], sides[Blue], sides[Red], sides[Green] }    // Yellow
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
        NormalizeSides(side, sidesToShift);
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
        CubeSide[] sources = new CubeSide[] {
                new CubeSide(sidesToShift[0]),
                new CubeSide(sidesToShift[1]),
                new CubeSide(sidesToShift[2]),
                new CubeSide(sidesToShift[3])
        };
        NormalizeSides(side, sidesToShift);
        NormalizeSides(side, sources);
        int maxIndex = _cube.getDimension() - 1;

        for (int sourceSideIndex = 0; sourceSideIndex <= 3; sourceSideIndex++) {
            int targetSideIndex = (sourceSideIndex + 1) % 4;
            CubeSide targetSide = sidesToShift[targetSideIndex];
            final CubeSide sourceSide = sources[sourceSideIndex];

            for (int column = 0; column <= maxIndex; column++)
                targetSide.setField(row, column, sourceSide.getField(row, column));
        }
        DenormalizeSides(side, sidesToShift);
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
