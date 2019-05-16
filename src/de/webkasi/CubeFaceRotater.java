package de.webkasi;

/**
 * Provides a the rotateFace() method to rotate any face of the Cube
 * in any direction.
 *
 * CubeFaceRotater supports cubes with multiple dimensions and rotates
 * multiple layers in one step.
 */
class CubeFaceRotater {
    private final Cube _cube;
    private final CubeFace[][] _faceToSides;
    private static final int[][] _sideRotations = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 1, 2},
            {1, 1, 1, 1},
            {1, 2, 1, 0},
            {2, 2, 2, 2},
    };
    private static final CubeFaceRotationDirection[][] _sideRotationDirections = new CubeFaceRotationDirection[][]{
            // White (top left 0 0)
            {
                    CubeFaceRotationDirection.None,                // Orange (left)
                    CubeFaceRotationDirection.None,                // Green
                    CubeFaceRotationDirection.None,                // Red
                    CubeFaceRotationDirection.None                 // Blue
            },
            // Orange (top left 0 0)
            {
                    CubeFaceRotationDirection.Counterclockwise,    // Blue (left)
                    CubeFaceRotationDirection.Clockwise,           // Yellow
                    CubeFaceRotationDirection.Clockwise,           // Green
                    CubeFaceRotationDirection.Clockwise            // White
            },
            // Green (top left 0 0)
            {
                    CubeFaceRotationDirection.Counterclockwise,    // Orange (left)
                    CubeFaceRotationDirection.None,                // Yellow
                    CubeFaceRotationDirection.Clockwise,           // Red
                    CubeFaceRotationDirection.Clockwise            // White
            },
            // Red (top left 0 0)
            {
                    CubeFaceRotationDirection.Counterclockwise,    // Green (left)
                    CubeFaceRotationDirection.Counterclockwise,    // Yellow
                    CubeFaceRotationDirection.Clockwise,           // Blue
                    CubeFaceRotationDirection.Counterclockwise     // White
            },
            // Blue (top left 0 0)
            {
                    CubeFaceRotationDirection.Counterclockwise,    // Red (left)
                    CubeFaceRotationDirection.Clockwise,           // Yellow
                    CubeFaceRotationDirection.Clockwise,           // Orange
                    CubeFaceRotationDirection.Counterclockwise     // White
            },
            // Yellow (top left 0 0)
            {
                    CubeFaceRotationDirection.Clockwise,           // Orange (left)
                    CubeFaceRotationDirection.Clockwise,           // Blue
                    CubeFaceRotationDirection.Clockwise,           // Red
                    CubeFaceRotationDirection.Clockwise            // Green
            }
    };

    /**
     * Initializes a new CubeFaceRotater instance.
     *
     * @param cube Cube that should moved
     */
    CubeFaceRotater(Cube cube) {
        _cube = cube;
        CubeFace[] sides = _cube.getSides();
        final int White     = CubeColor.White.ordinal();
        final int Orange    = CubeColor.Orange.ordinal();
        final int Green     = CubeColor.Green.ordinal();
        final int Red       = CubeColor.Red.ordinal();
        final int Blue      = CubeColor.Blue.ordinal();
        final int Yellow    = CubeColor.Yellow.ordinal();

        _faceToSides = new CubeFace[][] {
                { sides[Orange], sides[Green], sides[Red], sides[Blue] },   // White
                { sides[Blue], sides[Yellow], sides[Green], sides[White] }, // Orange
                { sides[Orange], sides[Yellow], sides[Red], sides[White] }, // Green
                { sides[Green], sides[Yellow], sides[Blue], sides[White] }, // Red
                { sides[Red], sides[Yellow], sides[Orange], sides[White] }, // Blue
                { sides[Orange], sides[Blue], sides[Red], sides[Green] }    // Yellow
        };
    }

    /**
     * Rotates the specified face of the cube in the specified direction.
     *
     * For cubes with more than 3x3 fields it is needed to be able to rotate more than the
     * upper-most layer with the rotating face. In this cases countOfLayers is greater than
     * one.
     *
     * @param direction The CubeFaceRotationDirection that specifies the direction of the rotation
     * @param face The CubeColor of the middle field of the face that is rotated
     * @param countOfLayers The count of layers of the side faces to rotate with the face
     */
    void rotateFace(CubeFaceRotationDirection direction, final CubeColor face, final int countOfLayers) {
        if (direction == CubeFaceRotationDirection.Clockwise)
            rotateClockwise(face, countOfLayers);
        else
            rotateCounterclockwise(face, countOfLayers);
    }

    /**
     * Rotates the specified face clockwise with the specified count of layers
     * of the connected side faces.
     *
     * @param side The CubeColor of the middle field of the side that is rotated
     * @param rows The count of layers of the side faces to rotate with the face
     */
    private void rotateClockwise(final CubeColor side, final int rows) {
        rotateTopClockwise(_cube.getSides()[side.ordinal()]);

        for (int row = 0; row < rows; row++) {
            shiftSideLayerClockwise(side, row);
        }
    }

    /**
     * Rotates the specified face of the cube clockwise without shifting the
     * connected side faces.
     * rotateTopClockwise() is also used for the normalization of the index
     * positions of the side faces before shifting them with the roting face.
     *
     * @param targetSide The CubeFace object that represents the face to rotate
     */
    private void rotateTopClockwise(CubeFace targetSide) {
        final CubeFace sourceSide = new CubeFace(targetSide);
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

    /**
     * Rotates the specified face counterclockwise with the specified count of layers
     * of the connected side faces.
     *
     * @param side The CubeColor of the middle field of the side that is rotated
     * @param rows The count of layers of the side faces to rotate with the face
     */
    private void rotateCounterclockwise(final CubeColor side, final int rows) {
        rotateTopCounterclockwise(_cube.getSides()[side.ordinal()]);

        for (int row = 0; row < rows; row++) {
            shiftSideLayerCounterclockwise(side, row);
        }
    }

    /**
     * Rotates the specified face of the cube counterclockwise without shifting the
     * connected side faces.
     * rotateTopCounterclockwise() is also used for the normalization of the index
     * positions of the side faces before shifting them with the roting face.
     *
     * @param targetSide The CubeFace object that represents the face to rotate
     */
    private void rotateTopCounterclockwise(CubeFace targetSide) {
        final CubeFace sourceSide = new CubeFace(targetSide);
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

    /**
     * Rotates the specified layer of the four sides of the specified face clockwise.
     *
     * For more than a three layer cube it is needed to be able to rotate more
     * than one layer. The row argument determines which layer is rotated.
     *
     * @param side The CubeColor of the middle field of the side that is rotated
     * @param row The row number to be shifted. 0 is the upper one.
     */
    private void shiftSideLayerClockwise(final CubeColor side, final int row) {
        CubeFace[] sidesToShift = _faceToSides[side.ordinal()];
        CubeFace[] sources = new CubeFace[] {
                new CubeFace(sidesToShift[0]),
                new CubeFace(sidesToShift[1]),
                new CubeFace(sidesToShift[2]),
                new CubeFace(sidesToShift[3])
        };
        NormalizeSides(side, sidesToShift);
        NormalizeSides(side, sources);
        int maxIndex = _cube.getDimension() - 1;

        for (int targetSideIndex = 3; targetSideIndex >= 0; targetSideIndex--) {
            int sourceSideIndex = (targetSideIndex + 1) % 4;
            CubeFace targetSide = sidesToShift[targetSideIndex];
            final CubeFace sourceSide = sources[sourceSideIndex];

            for (int column = 0; column <= maxIndex; column++)
                targetSide.setField(row, column, sourceSide.getField(row, column));
        }
        DenormalizeSides(side, sidesToShift);
    }

    /**
     * Rotates the specified layer of the four sides of the specified face counterclockwise.
     *
     * @param side The CubeColor of the middle field of the side that is rotated
     * @param row The row number to be shifted. 0 is the upper one.
     */
    private void shiftSideLayerCounterclockwise(final CubeColor side, final int row) {
        CubeFace[] sidesToShift = _faceToSides[side.ordinal()];
        CubeFace[] sources = new CubeFace[] {
                new CubeFace(sidesToShift[0]),
                new CubeFace(sidesToShift[1]),
                new CubeFace(sidesToShift[2]),
                new CubeFace(sidesToShift[3])
        };
        NormalizeSides(side, sidesToShift);
        NormalizeSides(side, sources);
        int maxIndex = _cube.getDimension() - 1;

        for (int sourceSideIndex = 0; sourceSideIndex <= 3; sourceSideIndex++) {
            int targetSideIndex = (sourceSideIndex + 1) % 4;
            CubeFace targetSide = sidesToShift[targetSideIndex];
            final CubeFace sourceSide = sources[sourceSideIndex];

            for (int column = 0; column <= maxIndex; column++)
                targetSide.setField(row, column, sourceSide.getField(row, column));
        }
        DenormalizeSides(side, sidesToShift);
    }

    /**
     * Normalizes the four side faces of the rotating cube face to use
     * a standard indexing four the color fields for shift operations.
     *
     * The normalization rotates alle four side faces so that the row 0 and column 0
     * is always to the upper left corner toward the rotating face. The shift algorithms
     * can always use the same field indexes for the operations when the faces are normalized.
     * After the operations, the faces are de-normalized (rotated back to their original
     * position).
     *
     * @param side The face that is rotated
     * @param sides The CubeFace objects that are rotated to normalize
     *              the indexes for the shift operations
     */
    private void NormalizeSides(CubeColor side, CubeFace[] sides) {
        int sideIndex = side.ordinal();
        int[] countOfRotations = _sideRotations[sideIndex];
        CubeFaceRotationDirection[] directions = _sideRotationDirections[sideIndex];

        for (int shiftSideIndex = 0; shiftSideIndex < 4; shiftSideIndex++) {
            for (int count = 0; count < countOfRotations[shiftSideIndex]; count++) {
                if (directions[shiftSideIndex] == CubeFaceRotationDirection.Clockwise)
                    rotateTopClockwise(sides[shiftSideIndex]);
                else
                    rotateTopCounterclockwise(sides[shiftSideIndex]);
            }
        }
    }

    /**
     * De-Normalizes the four side faces of the rotating cube face after
     * a shift operation.
     *
     * The normalization rotates alle four side faces so that the row 0 and column 0
     * is always to the upper left corner toward the rotating face. The shift algorithms
     * can always use the same field indexes for the operations when the faces are normalized.
     * After the operations, the faces are de-normalized (rotated back to their original
     * position).
     *
     * @param side The face that is rotated
     * @param sides The CubeFace objects that are rotated back to thier original index
     *              positions
     */
    private void DenormalizeSides(CubeColor side, CubeFace[] sides) {
        int sideIndex = side.ordinal();
        int[] countOfRotations = _sideRotations[sideIndex];
        CubeFaceRotationDirection[] directions = _sideRotationDirections[sideIndex];

        for (int shiftSideIndex = 0; shiftSideIndex < 4; shiftSideIndex++) {
            for (int count = 0; count < countOfRotations[shiftSideIndex]; count++) {
                if (directions[shiftSideIndex] == CubeFaceRotationDirection.Clockwise)
                    rotateTopCounterclockwise(sides[shiftSideIndex]);
                else
                    rotateTopClockwise(sides[shiftSideIndex]);
            }
        }
    }
}
