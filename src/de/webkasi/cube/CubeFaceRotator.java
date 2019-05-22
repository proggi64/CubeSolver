package de.webkasi.cube;

/**
 * Provides the rotateFace() method to rotate any face of the Cube
 * in any direction.
 *
 * CubeFaceRotator supports cubes with multiple dimensions and rotates
 * multiple layers in one step.
 */
public class CubeFaceRotator {
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
    private static final RotationDirection[][] _sideRotationDirections = new RotationDirection[][]{
            // White (top left 0 0)
            {
                    RotationDirection.None,                // Orange (left)
                    RotationDirection.None,                // Green
                    RotationDirection.None,                // Red
                    RotationDirection.None                 // Blue
            },
            // Orange (top left 0 0)
            {
                    RotationDirection.Counterclockwise,    // Blue (left)
                    RotationDirection.Clockwise,           // Yellow
                    RotationDirection.Clockwise,           // Green
                    RotationDirection.Clockwise            // White
            },
            // Green (top left 0 0)
            {
                    RotationDirection.Counterclockwise,    // Orange (left)
                    RotationDirection.None,                // Yellow
                    RotationDirection.Clockwise,           // Red
                    RotationDirection.Clockwise            // White
            },
            // Red (top left 0 0)
            {
                    RotationDirection.Counterclockwise,    // Green (left)
                    RotationDirection.Counterclockwise,    // Yellow
                    RotationDirection.Clockwise,           // Blue
                    RotationDirection.Counterclockwise     // White
            },
            // Blue (top left 0 0)
            {
                    RotationDirection.Counterclockwise,    // Red (left)
                    RotationDirection.Clockwise,           // Yellow
                    RotationDirection.Clockwise,           // Orange
                    RotationDirection.Counterclockwise     // White
            },
            // Yellow (top left 0 0)
            {
                    RotationDirection.Clockwise,           // Orange (left)
                    RotationDirection.Clockwise,           // Blue
                    RotationDirection.Clockwise,           // Red
                    RotationDirection.Clockwise            // Green
            }
    };

    /**
     * Initializes a new CubeFaceRotator instance.
     *
     * @param cube Cube where a face should be rotated
     */
    public CubeFaceRotator(Cube cube) {
        _cube = cube;
        CubeFace[] sides = _cube.getFaces();
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
     * one. It is not supported to rotate a specific layer. Instead, you have to rotate
     * two faces to achieve the same effect.
     *
     * @param direction The RotationDirection that specifies the direction of the rotation
     * @param faceIndex The index of the middle field of the face that is rotated.
     * @param countOfLayers The count of layers of the side faces to rotate with the face
     */
    public void rotateFace(final RotationDirection direction, final int faceIndex, final int countOfLayers) {
        if (direction == RotationDirection.Clockwise)
            rotateClockwise(faceIndex, countOfLayers);
        else
            rotateCounterclockwise(faceIndex, countOfLayers);
    }

    /**
     * Rotates the specified face clockwise with the specified count of layers
     * of the connected side faces.
     *
     * @param faceIndex The index of the middle field of the face that is rotated.
     * @param rows The count of layers of the side faces to rotate with the face
     */
    private void rotateClockwise(final int faceIndex, final int rows) {
        rotateTopClockwise(_cube.getFaces()[faceIndex]);

        for (int row = 0; row < rows; row++) {
            shiftSideLayerClockwise(faceIndex, row);
        }
    }

    /**
     * Rotates the specified face of the cube clockwise without shifting the
     * connected side faces.
     * rotateTopClockwise() is also used for the normalization of the index
     * positions of the side faces before shifting them with the roting face.
     *
     * @param face The CubeFace object that represents the face to rotate
     */
    private void rotateTopClockwise(CubeFace face) {
        final CubeFace sourceSide = new CubeFace(face);
        int maxIndex = _cube.getDimension() - 1;

        int targetColumnIndex = maxIndex;
        for (int sourceRowIndex = 0; sourceRowIndex <= maxIndex; sourceRowIndex++) {
            int targetRowIndex = 0;
            for (int sourceColumnIndex = 0; sourceColumnIndex <= maxIndex; sourceColumnIndex++) {
                face.setField(targetRowIndex, targetColumnIndex,
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
     * @param faceIndex The index of the middle field of the face that is rotated.
     * @param rows The count of layers of the side faces to rotate with the face
     */
    private void rotateCounterclockwise(final int faceIndex, final int rows) {
        rotateTopCounterclockwise(_cube.getFaces()[faceIndex]);

        for (int row = 0; row < rows; row++) {
            shiftSideLayerCounterclockwise(faceIndex, row);
        }
    }

    /**
     * Rotates the specified face of the cube counterclockwise without shifting the
     * connected side faces.
     * rotateTopCounterclockwise() is also used for the normalization of the index
     * positions of the side faces before shifting them with the roting face.
     *
     * @param face The CubeFace object that represents the face to rotate
     */
    private void rotateTopCounterclockwise(CubeFace face) {
        final CubeFace sourceSide = new CubeFace(face);
        int maxIndex = _cube.getDimension() - 1;

        int targetColumnIndex = 0;
        for (int sourceRowIndex = 0; sourceRowIndex <= maxIndex; sourceRowIndex++) {
            int targetRowIndex = maxIndex;
            for (int sourceColumnIndex = 0; sourceColumnIndex <= maxIndex; sourceColumnIndex++) {
                face.setField(targetRowIndex, targetColumnIndex,
                        sourceSide.getField(sourceRowIndex, sourceColumnIndex));
                targetRowIndex--;
            }
            targetColumnIndex++;
        }
    }

    /**
     * Shifts the specified layer of the four sides of the specified face clockwise.
     *
     * A rotation of a face will result in shifting fields of the four side
     * faces. The shifting is done in a circular way from
     * one face to its neighbor in the direction of the rotation.
     *
     * For more than a three layer cube it is needed to be able to shift more
     * than one layer. The row argument determines which layer is shifted.
     *
     * @param sideFaceIndex The index of the side face where the
     *                      row is shifted
     * @param row The row number to be shifted. 0 is the upper one.
     */
    private void shiftSideLayerClockwise(final int sideFaceIndex, final int row) {
        CubeFace[] sidesToShift = _faceToSides[sideFaceIndex];
        CubeFace[] sources = new CubeFace[] {
                new CubeFace(sidesToShift[0]),
                new CubeFace(sidesToShift[1]),
                new CubeFace(sidesToShift[2]),
                new CubeFace(sidesToShift[3])
        };
        normalizeSides(sideFaceIndex, sidesToShift);
        normalizeSides(sideFaceIndex, sources);
        int maxIndex = _cube.getDimension() - 1;

        for (int targetSideIndex = 3; targetSideIndex >= 0; targetSideIndex--) {
            int sourceSideIndex = (targetSideIndex + 1) % 4;
            CubeFace targetSide = sidesToShift[targetSideIndex];
            final CubeFace sourceSide = sources[sourceSideIndex];

            for (int column = 0; column <= maxIndex; column++)
                targetSide.setField(row, column, sourceSide.getField(row, column));
        }
        denormalizeSides(sideFaceIndex, sidesToShift);
    }

    /**
     * Shifts the specified layer of the four sides of the specified face counterclockwise.
     *
     * A rotation of a face will result in shifting fields of the four side
     * faces. The shifting is done in a circular way from
     * one face to its neighbor in the direction of the rotation.
     *
     * For more than a three layer cube it is needed to be able to shift more
     * than one layer. The row argument determines which layer is shifted.
     *
     * @param sideFaceIndex The index of the side face where the
     *                      row is shifted
     * @param row The row number to be shifted. 0 is the upper one.
     */
    private void shiftSideLayerCounterclockwise(final int sideFaceIndex, final int row) {
        CubeFace[] sidesToShift = _faceToSides[sideFaceIndex];
        CubeFace[] sources = new CubeFace[] {
                new CubeFace(sidesToShift[0]),
                new CubeFace(sidesToShift[1]),
                new CubeFace(sidesToShift[2]),
                new CubeFace(sidesToShift[3])
        };
        normalizeSides(sideFaceIndex, sidesToShift);
        normalizeSides(sideFaceIndex, sources);
        int maxIndex = _cube.getDimension() - 1;

        for (int sourceSideIndex = 0; sourceSideIndex <= 3; sourceSideIndex++) {
            int targetSideIndex = (sourceSideIndex + 1) % 4;
            CubeFace targetSide = sidesToShift[targetSideIndex];
            final CubeFace sourceSide = sources[sourceSideIndex];

            for (int column = 0; column <= maxIndex; column++)
                targetSide.setField(row, column, sourceSide.getField(row, column));
        }
        denormalizeSides(sideFaceIndex, sidesToShift);
    }

    /**
     * Normalizes the four side faces of the rotating cube face to use
     * a standard indexing for the color fields for shift operations.
     *
     * The normalization rotates alle four side faces so that the row 0 and column 0
     * is always to the upper left corner toward the rotating face. The shift algorithms
     * can always use the same field indexes for the operations when the faces are normalized.
     * After the operations, the faces are de-normalized (rotated back to their original
     * position).
     *
     * @param side The face's index that is rotated.
     * @param sides The CubeFace objects that are rotated to normalize
     *              the indexes for the shift operations.
     */
    private void normalizeSides(int side, CubeFace[] sides) {
        int sideIndex = side;
        int[] countOfRotations = _sideRotations[sideIndex];
        RotationDirection[] directions = _sideRotationDirections[sideIndex];

        for (int shiftSideIndex = 0; shiftSideIndex < 4; shiftSideIndex++) {
            for (int count = 0; count < countOfRotations[shiftSideIndex]; count++) {
                if (directions[shiftSideIndex] == RotationDirection.Clockwise)
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
     * @param side The face's index that is rotated
     * @param sides The CubeFace objects that are rotated back to thier original index
     *              positions
     */
    private void denormalizeSides(int side, CubeFace[] sides) {
        int sideIndex = side;
        int[] countOfRotations = _sideRotations[sideIndex];
        RotationDirection[] directions = _sideRotationDirections[sideIndex];

        for (int shiftSideIndex = 0; shiftSideIndex < 4; shiftSideIndex++) {
            for (int count = 0; count < countOfRotations[shiftSideIndex]; count++) {
                if (directions[shiftSideIndex] == RotationDirection.Clockwise)
                    rotateTopCounterclockwise(sides[shiftSideIndex]);
                else
                    rotateTopClockwise(sides[shiftSideIndex]);
            }
        }
    }
}
