package de.webkasi.cube;

/**
 * Represents a cube rotation for playing rotations.
 */
public class CubeFaceRotationRecord {
    private final CubeColor _face;
    private RotationDirection _direction;
    private int _startRow;
    private int _countOfLayers;

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with default values.
     *
     * The default direction is clockwise,
     * and the count of layers is one.
     *
     * @param face The CubeColor of the face to rotate.
     */
    public CubeFaceRotationRecord(CubeColor face) {
        _face = face;
        _direction = RotationDirection.Clockwise;
        _startRow = 0;
        _countOfLayers = 1;
    }

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with a default value for the direction.
     *
     * The default direction is clockwise.
     *
     * @param face The CubeColor of the face to rotate.
     */
    public CubeFaceRotationRecord(CubeColor face, int countOfLayers) {
        _face = face;
        _direction = RotationDirection.Clockwise;
        _startRow = 0;
        _countOfLayers = countOfLayers;
    }

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with the specified values.
     *
     * @param face The CubeColor of the face to rotate.
     * @param direction The CubeRotationDirection value specifying the rotation direction.
     * @param countOfLayers The count of layers to rotate.
     */
    public CubeFaceRotationRecord(CubeColor face, RotationDirection direction, int countOfLayers) {
        _face = face;
        _direction = direction;
        _startRow = 0;
        _countOfLayers = countOfLayers;
    }

    /**
     * Initializes a new instance of the CubeFaceRotationRecord class
     * with the specified values.
     *
     * @param face The CubeColor of the face to rotate.
     * @param direction The CubeRotationDirection value specifying the rotation direction.
     * @param startRow The first layer index which is rotated
     * @param countOfLayers The count of layers to rotate.
     */
    public CubeFaceRotationRecord(
            final CubeColor face,
            final RotationDirection direction,
            final int startRow,
            final int countOfLayers) {
        _face = face;
        _direction = direction;
        _startRow = startRow;
        _countOfLayers = countOfLayers;
    }

    /**
     * Gets the index of the face to rotate.
     *
     * @return The index of the face to rotate.
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
     * Gets the index of the first row to rotate.
     *
     * @return Index of the first row to rotate.
     * When greater zero then the to face is not rotated.
     */
    public int getStartRow() { return _startRow; }

    /**
     * Sets the index of the first row to rotate.
     * When greater zero then the face will not be rotated.
     *
     * @param startRow Index of the first row to rotate.
     */
    public void setStartRow(int startRow) { this._startRow = startRow; }

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
