package de.webkasi.cube;

/**
 * Represents a cube rotation for playing rotations.
 */
class CubeFaceRotationRecord {
    private CubeColor _face;
    private CubeFaceRotationDirection _direction;
    private int _countOfLayers;

    /**
     * @param face The CubeColor specifying the face to rotate.
     * @param direction The CubeRotationDirection value specifying the rotation direction.
     * @param countOfLayers The count of layers to rotate.
     */
    CubeFaceRotationRecord(CubeColor face, CubeFaceRotationDirection direction, int countOfLayers) {
        _face = face;
        _direction = direction;
        _countOfLayers = countOfLayers;
    }

    /**
     * Gets the CubeColor specifying the face to rotate.
     *
     * @return The CubeColor specifying the face to rotate.
     */
    CubeColor getFace() {
        return _face;
    }

    /**
     * Gets the CubeRotationDirection value specifying the rotation direction.
     *
     * @return The CubeRotationDirection value specifying the rotation direction.
     */
    CubeFaceRotationDirection getDirection() {
        return _direction;
    }

    /**
     * Gets the count of layers to rotate.
     *
     * @return The count of layers to rotate.
     */
    int getCountOfLayers() {
        return _countOfLayers;
    }
}
