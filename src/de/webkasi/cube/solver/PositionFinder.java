package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides serach methods to find the positions of edge and corner parts
 * on a Cube.
 */
public class PositionFinder {

    private static final int faceIndex = 0;
    private static final int rowIndex = 1;
    private static final int columnIndex = 2;
    private static final int sideFaceIndex = 3;
    private static final int sideRowIndex = 4;
    private static final int sideColumnIndex = 5;

    /**
     * Search positions for all edges as an two dimensional int array:
     * face, row, column, sideFace, sideRow, sideColumn.
     *
     * face is the index of the face where the search is running.
     * The row and column are the four possible positions of edges
     * at each face. The second color position is at the sideFace at
     * the corresponding sideRow and SideColumn position.
     */
    private static final int[][] edgeIndexes = new int[][] {
            { 0, 0, 1,  4, 0, 1 },  // top back - initial white
            { 0, 1, 0,  1, 0, 1 },  // top left
            { 0, 1, 2,  3, 0, 1 },  // top right
            { 0, 2, 1,  2, 0, 1 },  // top front

            { 1, 0, 1,  0, 1, 0 },  // left top - initial orange
            { 1, 1, 0,  4, 1, 2 },  // left back
            { 1, 1, 2,  2, 1, 0 },  // left front
            { 1, 2, 1,  5, 1, 0 },  // left down

            { 2, 0, 1,  0, 2, 1 },  // front top - initial green
            { 2, 1, 0,  1, 1, 2 },  // front left
            { 2, 1, 2,  3, 1, 0 },  // front right
            { 2, 2, 1,  5, 0, 1 },  // front down

            { 3, 0, 1,  0, 1, 2 },  // right top - initial red
            { 3, 1, 0,  2, 1, 2 },  // right front
            { 3, 1, 2,  4, 1, 0 },  // right back
            { 3, 2, 1,  5, 1, 2 },  // right down

            { 4, 0, 1,  0, 0, 1 },  // back top - initial blue
            { 4, 1, 0,  3, 1, 2 },  // back right
            { 4, 1, 2,  1, 1, 0 },  // back left
            { 4, 2, 1,  5, 2, 1 },  // back down

            { 5, 0, 1,  2, 2, 1 },  // down front - initial yellow
            { 5, 1, 0,  1, 2, 1 },  // back right
            { 5, 1, 2,  3, 2, 1 },  // back left
            { 5, 2, 1,  4, 2, 1 }   // back down
    };

    /**
     * Searches the specified edge position on the specified cube.
     *
     * @param cube The Cube where to search.
     * @param mainColor The CubeColor that should be the main color of the part to search.
     *                  This is the color of the face that the solution is currently working
     *                  with. For example, when the White Cross step is executed then the
     *                  white color is the main color.
     * @param sideColor The CubeColor that should be the front color of the part to search.
     * @return An PartPosition object containing the exact position of the mainColor with
     * the corresponding topColor on the cube.
     */
    public static PartPosition FindEdge(Cube cube, CubeColor mainColor, CubeColor sideColor) {
        boolean found = false;
        int face = 0;
        int row = 0;
        int column = 0;

        for (int i = 0; !found && i < edgeIndexes.length; i++) {
            face = edgeIndexes[i][faceIndex];
            row = edgeIndexes[i][rowIndex];
            column = edgeIndexes[i][columnIndex];

            CubeFace cubeFace = cube.getFaceByIndex(face);
            CubeFace sideFace = cube.getFaceByIndex(edgeIndexes[i][sideFaceIndex]);
            found = (cubeFace.getField(row, column) == mainColor &&
                sideFace.getField(edgeIndexes[i][sideRowIndex], edgeIndexes[i][sideColumnIndex]) == sideColor);
        }
        PartPosition position =  new PartPosition();
        position.setFace(face);
        position.setColumn(column);
        position.setRow(row);

        return position;
    }

    public static PartPosition FindCorner(Cube cube, CubeColor upColor, CubeColor leftColor, CubeColor frontColor) {
        return null;
    }
}
