package de.webkasi.cube.solver;

/**
 * Represents the position of a specific cube part on a
 * Cube.
 *
 * The Position class is used for the analysis of the state
 * of the Cube to find a matching rotation sequences for a
 * solution. When an edge is searched the main color and the side
 * color of the edge is specified. The main color determines which face
 * index is used for getFace(). So the orientation of the edge can be determined.
 *
 */
class PartPosition {
    private int _face;
    private int _row;
    private int _column;

    /**
     * Initializes a new instance of the PartPosition class with the default values.
     *
     * The values should be set by using the property setters.
     */
    PartPosition() {
        _face = -1;
        _row = -1;
        _column = -1;
    }

    /**
     * Copies the specified PartPosition objct into the new instance.
     *
     * @param copy The source object to copy.
     */
    PartPosition(PartPosition copy) {
        _face = copy._face;
        _row = copy._row;
        _column = copy._column;
    }

    /**
     * Initializes a new instance of the PartPosition class with the specified values.
     *
     * @param face Index of the face where for the edge's main color.
     * @param row Row of the edge.
     * @param column Column of the edge.
     */
    PartPosition(int face, int row, int column) {
        _face = face;
        _row = row;
        _column = column;
    }

    /**
     * Gets the index of the face where the edge is currently.
     *
     * @return Index of the face where the edge is currently.
     */
    public int getFace() {
        return _face;
    }

    /**
     * Sets the index of the face where the edge is currently.
     *
     * The index 0 is the white face when the Cube is created and represents the top
     * face of the cube. Orange is 1 and left,
     * green is 2, red is 3, blue is 4, and yellow is 5. These indexes
     * never change. Rotating the whole cube is only valid for some
     * notations and the indexes are mapped in these cases.
     *
     * @param face Index of the face where the edge is currently.
     */
    public void setFace(int face) {
        this._face = face;
    }

    /**
     * Gets the row of the face where the edge is currently.
     *
     * @return The row index where the edge is currently.
     */
    public int getRow() {
        return _row;
    }

    /**
     * Sets the row of the face where the edge is currently.
     *
     * Row 0 is the upper row of the face. The orientations of row 0 of
     * the initial white face (face index 0) is that the orange face is left
     * of the white face. All side faces of the white face have their upper
     * row towards the white face. The yellow face has also the orange face to
     * its left.
     *
     * @param row Row index where the edge is currently.
     */
    public void setRow(int row) {
        this._row = row;
    }

    /**
     * Gets the column index where the edge is currently.
     *
     * Row 0 is the upper row of the face. The orientations of row 0 of
     * the initial white face (face index 0) is that the orange face is left
     * of the white face. All side faces of the white face have their upper
     * row towards the white face. The yellow face has also the orange face to
     * its left.
     *
     * @return The column index where the edge is currently.
     */
    public int getColumn() {
        return _column;
    }

    /**
     * Sets the column index where the edge is currently.
     *
     * Column 0 is the left column of the face. The orientations of column 0 of
     * the initial white face (face index 0) is that the orange face is left
     * of the white face. All side faces of the white face have their left
     * column in relation to the top white face. The yellow face has its left
     * column towards the orange face.
     *
     * @param column The column index where the edge is currently.
     */
    public void setColumn(int column) {
        this._column = column;
    }

    /**
     * Compares this instance to the specified position.
     *
     * Used to find a specific position to get the appropriate algorithm
     * for a solution.
     *
     * @param position The PartPosition that ist compared to this instance.
     * @return true if position is equal; otherwise, false.
     */
    public boolean isEqual(PartPosition position) {
        return _face == position._face && _row == position._row && _column == position._column;
    }
}
