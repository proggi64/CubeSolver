package de.webkasi.cube;

/**
 * Represents a cube rotation for playing rotations.
 */
public class CubeFaceRotationRecord {
    private CubeColor _face;
    private RotationDirection _direction;
    private int _countOfLayers;

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with default values.
     *
     * The default direction is clockwise,
     * and the count of layers is one.
     *
     * @param face The CubeColor specifying the face to rotate.
     */
    public CubeFaceRotationRecord(CubeColor face) {
        _face = face;
        _direction = RotationDirection.Clockwise;
        _countOfLayers = 1;
    }

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with a default value for the direction.
     *
     * The default direction is clockwise.
     *
     * @param face The CubeColor specifying the face to rotate.
     */
    public CubeFaceRotationRecord(CubeColor face, int countOfLayers) {
        _face = face;
        _direction = RotationDirection.Clockwise;
        _countOfLayers = countOfLayers;
    }

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with the specified values.
     *
     * @param face The CubeColor specifying the face to rotate.
     * @param direction The CubeRotationDirection value specifying the rotation direction.
     * @param countOfLayers The count of layers to rotate.
     */
    public CubeFaceRotationRecord(CubeColor face, RotationDirection direction, int countOfLayers) {
        _face = face;
        _direction = direction;
        _countOfLayers = countOfLayers;
    }

    /**
     * Sets the CubeColor specifying the face to rotate.
     *
     * @param face The CubeColor of the face to rotate.
     */
    public void setFace(CubeColor face) {
        _face = face;
    }

    /**
     * Gets the CubeColor specifying the face to rotate.
     *
     * @return The CubeColor specifying the face to rotate.
     */
    public CubeColor getFace() {
        return _face;
    }

    /**
     * Sets the CubeRotationDirection value specifying the rotation direction.
     *
     * @param direction The CubeRotationDirection value specifying the
     *                  rotation direction.
     */
    public void setDirection(RotationDirection direction) {
        _direction = direction;
    }

    /**
     * Gets the CubeRotationDirection value specifying the rotation direction.
     *
     * @return The CubeRotationDirection value specifying the rotation direction.
     */
    public RotationDirection getDirection() {
        return _direction;
    }

    /**
     * Sets the count of layers to rotate.
     *
     * @param countOfLayers The count of layers to rotate.
     */
    public void setCountOfLayers(int countOfLayers) {
        _countOfLayers = countOfLayers;
    }

    /**
     * Gets the count of layers to rotate.
     *
     * @return The count of layers to rotate.
     */
    public int getCountOfLayers() {
        return _countOfLayers;
    }
}
