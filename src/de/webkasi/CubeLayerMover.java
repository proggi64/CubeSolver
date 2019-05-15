package de.webkasi;

class CubeLayerMover {
    private Cube _cube;
    private CubeSide[][] topToSides;

    CubeLayerMover(Cube cube) {
        _cube = cube;
        CubeSide[] sides = _cube.getSides();
        final int White     = CubeColor.White.ordinal();
        final int Orange    = CubeColor.Orange.ordinal();
        final int Green     = CubeColor.Green.ordinal();
        final int Red       = CubeColor.Red.ordinal();
        final int Blue      = CubeColor.Blue.ordinal();
        final int Yellow    = CubeColor.Yellow.ordinal();

        topToSides = new CubeSide[][] {
                { sides[Orange], sides[Green], sides[Red], sides[Blue], sides[Yellow] },    // White
                { sides[White], sides[Blue], sides[Yellow], sides[Green], sides[Red] },     // Orange
                { sides[White], sides[Orange], sides[Yellow], sides[Red], sides[Blue] },    // Green
                { sides[White], sides[Green], sides[Yellow], sides[Blue], sides[Orange] },  // Red
                { sides[White], sides[Red], sides[Yellow], sides[Orange], sides[Green] },   // Blue
                { sides[Orange], sides[Blue], sides[Red], sides[Green], sides[White] }      // Yellow
        };
    }

    void move(CubeLayerMoveDirection direction, CubeColor side, int startLayer, int countOfLayers) {

    }

    private void shiftSideLayerRight(CubeColor side, int layer) {

    }

    private void shiftSideLayerLeft(CubeColor side, int layer) {
        CubeSide[] sidesToShift = topToSides[side.ordinal()];
        CubeSide[] originals = new CubeSide[] {
                new CubeSide(sidesToShift[0]),
                new CubeSide(sidesToShift[1]),
                new CubeSide(sidesToShift[2]),
                new CubeSide(sidesToShift[3]),
                new CubeSide(sidesToShift[4])
        };
        int maxIndex = _cube.getDimension();

        for (int sideIndex = 0; sideIndex <= 3; sideIndex++) {
            int targetSideIndex = (sideIndex + 1) % 3;
            CubeSide targetSide = sidesToShift[targetSideIndex];
            CubeColor[] targetColorRow = targetSide.getFields()[layer];
            final CubeSide sourceSide = originals[sideIndex];
            final CubeColor[] sourceColorRow = targetSide.getFields()[layer];

            for (int fieldIndex = 0; fieldIndex < maxIndex; fieldIndex++) {
                
            }
        }
    }

    private void rotateSideRight(CubeColor side) {
        CubeSide sideToRotate = _cube.getSides()[side.ordinal()];
        CubeColor[][] fieldsToRotate = sideToRotate.getFields();

        final CubeSide originalSide = new CubeSide(sideToRotate);
        final CubeColor[][] originalFields = originalSide.getFields();

        int maxIndex = _cube.getDimension();

        int targetRowIndex = 0;
        int targetColumnIndex = maxIndex - 1;
        for (int originalRowIndex = 0; originalRowIndex < maxIndex; originalRowIndex++) {
            for (int originalColumnIndex = 0; originalColumnIndex < maxIndex; originalColumnIndex++) {
                fieldsToRotate[targetColumnIndex][targetRowIndex] =
                        originalFields[originalColumnIndex][originalRowIndex];
                targetColumnIndex--;
            }
            targetRowIndex++;
            targetColumnIndex = maxIndex - 1;
        }
    }

    private void rotateSideLeft(CubeColor side) {
        CubeSide sideToRotate = _cube.getSides()[side.ordinal()];
        CubeColor[][] fieldsToRotate = sideToRotate.getFields();

        final CubeSide originalSide = new CubeSide(sideToRotate);
        final CubeColor[][] originalFields = originalSide.getFields();

        int maxIndex = _cube.getDimension();

        int targetRowIndex = maxIndex - 1;
        int targetColumnIndex = 0;
        for (int originalRowIndex = 0; originalRowIndex < maxIndex; originalRowIndex++) {
            for (int originalColumnIndex = 0; originalColumnIndex < maxIndex; originalColumnIndex++) {
                fieldsToRotate[targetColumnIndex][targetRowIndex] =
                        originalFields[originalColumnIndex][originalRowIndex];
                targetRowIndex--;
            }
            targetRowIndex = maxIndex - 1;
            targetColumnIndex++;
        }
    }
}
