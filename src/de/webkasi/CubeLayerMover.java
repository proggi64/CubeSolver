package de.webkasi;

class CubeLayerMover {
    private Cube _cube;

    CubeLayerMover(Cube cube) {
        _cube = cube;
    }

    void move(CubeLayerMoveDirection direction, CubeColor side, int startLayer, int countOfLayers) {

    }

    private void shiftSideLayerRight(CubeColor side, int layer) {

    }

    private void shiftSideLayerLeft(CubeColor side, int layer) {

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
