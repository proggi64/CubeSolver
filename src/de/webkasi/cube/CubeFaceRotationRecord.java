package de.webkasi.cube;

/**
 * Represents a cube rotation for playing rotations.
 */
public class CubeFaceRotationRecord {
    private int _face;
    private RotationDirection _direction;
    private int _countOfLayers;

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with default values.
     *
     * The default direction is clockwise,
     * and the count of layers is one.
     *
     * @param face The index of the face to rotate.
     */
    public CubeFaceRotationRecord(int face) {
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
     * @param face The index of the face to rotate.
     */
    public CubeFaceRotationRecord(int face, int countOfLayers) {
        _face = face;
        _direction = RotationDirection.Clockwise;
        _countOfLayers = countOfLayers;
    }

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with the specified values.
     *
     * @param face The index of the face to rotate.
     * @param direction The CubeRotationDirection value specifying the rotation direction.
     * @param countOfLayers The count of layers to rotate.
     */
    public CubeFaceRotationRecord(int face, RotationDirection direction, int countOfLayers) {
        _face = face;
        _direction = direction;
        _countOfLayers = countOfLayers;
    }

    /**
     * Sets the index of the face to rotate.
     *
     * @param face The index of the face to rotate.
     */
    public void setFace(int face) {
        _face = face;
    }

    /**
     * Gets the index of the face to rotate.
     *
     * @return The index of the face to rotate.
     */
    public int getFace() {
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
