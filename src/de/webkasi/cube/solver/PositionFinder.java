package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides serach methods to find the positions of edge and corner parts
 * on a Cube.
 */
class PositionFinder {

    private static final int upFaceIndex = 0;
    private static final int upRowIndex = 1;
    private static final int upColumnIndex = 2;
    private static final int frontFaceIndex = 3;
    private static final int frontRowIndex = 4;
    private static final int frontColumnIndex = 5;

    /**
     * Search positions for all edges as a two dimensional int array:
     * face, row, column, sideFace, sideRow, sideColumn.
     *
     * The array contains all possible positions of edges combined with
     * their two orientations. These positions are used to get the
     * actual color. If both match with the arguments of the findEdge()
     * method then the edge is found.
     *
     * face is the index of the face where the search is running.
     * The row and column are the four possible positions of edges
     * at each face. The second color position is at the sideFace at
     * the corresponding sideRow and SideColumn position.
     */
    private static final int[][] edgeCoordinates = new int[][] {
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
     * Gets the coordinates of the neighbor field of
     * the specified field of an edge.
     *
     * @param face Face index of the main color.
     * @param row Row of the main color.
     * @param column Column of the main color.
     * @return An array of six coordinate values. The first three are the
     * arguments that have been passed to the method, the second three
     * are the coordinates of the second field of the edge.
     * Index 3 is the face index, index 4 the row, and index 5 the column.
     */
    static int[] getEdgeNeighbor(int face, int row, int column) {
        int i = 0;

        while (true) {
            if (edgeCoordinates[i][upFaceIndex] == face &&
                    edgeCoordinates[i][upRowIndex] == row &&
                    edgeCoordinates[i][upColumnIndex] == column)
                return edgeCoordinates[i];
            i++;
        }
    }

    /**
     * Searches the position of the specified edge on the cube.
     *
     * @param cube The Cube where to search.
     * @param upColor The CubeColor that should be the up color of the part to search.
     *                This is the color of the face that the solution is currently working
     *                with. For example, when the White Cross step is executed then the
     *                white color is the top color.
     * @param frontColor The CubeColor that should be the front color of the part to search.
     * @return A PartPosition object containing the exact position of the topColor with
     * the corresponding sideColor on the cube. This position contains the absolute
     * coordinates based on the default orientation of the cube. the top face is white,
     * the front is green. The face indexes are up 0, left 1, front 2, right 3, back 4,
     * and down 5. The row 0 and column 0 are the top-left fields on the face. The white
     * face has its row 0 towards the blue face on the back. All side faces have their
     * row 0 towards the white face. The yellow face has its row 0 towards the green face.
     */
    static PartPosition findEdge(final Cube cube, final CubeColor upColor, final CubeColor frontColor) {
        return find(cube, upColor, frontColor, edgeCoordinates);
    }

    /**
     * Search positions for all corners as a two dimensional int array:
     * face, row, column, sideFace, sideRow, sideColumn.
     *
     * The array contains all possible positions of corners combined with
     * their two orientations. These positions are used to get the
     * actual color. If both match with the arguments of the findCorner()
     * method then the edge is found.
     *
     * face is the index of the face where the search is running.
     * The row and column are the four possible positions of corners
     * at each face. The second color position is at the sideFace at
     * the corresponding sideRow and SideColumn position.
     */
    private static final int[][] cornerCoordinates = new int[][] {
            { 0, 0, 0,  4, 0, 2 },  // up back left
            { 0, 0, 2,  3, 0, 2 },  // up right back
            { 0, 2, 2,  2, 0, 2 },  // up front right
            { 0, 2, 0,  1, 0, 2 },  // up left front

            { 1, 0, 0,  0, 0, 0 },  // left up back
            { 1, 0, 2,  2, 0, 0 },  // left front up
            { 1, 2, 2,  5, 0, 0 },  // left down front
            { 1, 2, 0,  4, 2, 2 },  // left back down

            { 2, 0, 0,  0, 2, 0 },  // front up left
            { 2, 0, 2,  3, 0, 0 },  // front right up
            { 2, 2, 2,  5, 0, 2 },  // front down right
            { 2, 2, 0,  1, 2, 2 },  // front left down

            { 3, 0, 0,  0, 2, 2 },  // right up front
            { 3, 0, 2,  4, 0, 0 },  // right back up
            { 3, 2, 2,  5, 2, 2 },  // right down back
            { 3, 2, 0,  2, 2, 2 },  // right front down

            { 4, 0, 0,  0, 0, 2 },  // back up right
            { 4, 0, 2,  1, 0, 0 },  // back left up
            { 4, 2, 2,  5, 2, 0 },  // back down left
            { 4, 2, 0,  3, 2, 2 },  // back right down

            { 5, 0, 0,  2, 2, 0 },  // down front left
            { 5, 0, 2,  3, 2, 0 },  // down right front
            { 5, 2, 2,  4, 2, 0 },  // down back right
            { 5, 2, 0,  1, 2, 0 }   // down left back
    };

    /**
     * Gets the coordinates of the left down neighbor field of
     * the specified field of a corner.
     *
     * The second color of the right down neigbor is not necessary,
     * because these two colors specify the corner uniquely.
     *
     * @param face Face index of the main color.
     * @param row Row of the main color.
     * @param column Column of the main color.
     * @return An array of six coordinate values. The first three are the
     * arguments that have been passed to the method, the second three
     * are the coordinates of the left down neighbor of the corner.
     * Index 3 is the face index, index 4 the row, and index 5 the column.
     */
    static int[] getCornerNeighbor(int face, int row, int column) {
        int i = 0;

        while (true) {
            if (cornerCoordinates[i][upFaceIndex] == face &&
                cornerCoordinates[i][upRowIndex] == row &&
                cornerCoordinates[i][upColumnIndex] == column)
                return cornerCoordinates[i];
            i++;
        }
    }

    /**
     * Searches the position of the specified corner on the cube.
     *
     * The frontColor of a corner is always to the left of the topColor. These two colors are
     * sufficient for specifying a corner.
     *
     * @param cube The Cube where to search.
     * @param upColor The CubeColor that should be the up color of the part to search.
     *                This is the color of the face that the solution is currently working
     *                with. For example, when the White Cross step is executed then the
     *                white color is the main color.
     * @param frontColor The CubeColor that should be the front color of the part to search.
     * @return A PartPosition object containing the exact position of the topColor with
     * the corresponding sideColor on the cube. This position contains the absolute
     * coordinates based on the default orientation of the cube. the top face is white,
     * the front is green. The face indexes are up 0, left 1, front 2, right 3, back 4,
     * and down 5. The row 0 and column 0 are the top-left fields on the face. The white
     * face has its row 0 towards the blue face on the back. All side faces have their
     * row 0 towards the white face. The yellow face has its row 0 towards the green face.
     */
    static PartPosition findCorner(final Cube cube, final CubeColor upColor, final CubeColor frontColor) {
        return find(cube, upColor, frontColor, cornerCoordinates);
    }

    /**
     * Finds the up and front color part on the cube.
     *
     * The find() method gets the array of search positions. These arrays are different for
     * edges and corners, but the algorithm is identical.
     *
     * @param cube The Cube where to search.
     * @param upColor The CubeColor that should be the up color of the part to search.
     *                This is the color of the face that the solution is currently working
     *                with. For example, when the White Cross step is executed then the
     *                white color is the main color.
     * @param frontColor The CubeColor that should be the front color of the part to search.
     * @param indexes The array of coordinates for the both color faces. The first three values
     *                are face index, row, and column for the upColor; the second three value
     *                are the same for the frontColor.
     * @return A PartPosition object containing the exact position of the topColor with
     * the corresponding sideColor on the cube. This position contains the absolute
     * coordinates based on the default orientation of the cube. the top face is white,
     * the front is green. The face indexes are up 0, left 1, front 2, right 3, back 4,
     * and down 5. The row 0 and column 0 are the top-left fields on the face. The white
     * face has its row 0 towards the blue face on the back. All side faces have their
     * row 0 towards the white face. The yellow face has its row 0 towards the green face.
     */
    private static PartPosition find(
            final Cube cube,
            final CubeColor upColor,
            final CubeColor frontColor,
            final int[][] indexes) {
        boolean found = false;
        int face = 0;
        int row = 0;
        int column = 0;
        int i = 0;

        while (!found) {
            face = indexes[i][upFaceIndex];
            row = indexes[i][upRowIndex];
            column = indexes[i][upColumnIndex];

            CubeFace cubeFace = cube.getFaceByIndex(face);
            CubeFace sideFace = cube.getFaceByIndex(indexes[i][frontFaceIndex]);
            found = (cubeFace.getField(row, column) == upColor &&
                    sideFace.getField(indexes[i][frontRowIndex], indexes[i][frontColumnIndex]) == frontColor);
            i++;
        }
        PartPosition position =  new PartPosition();
        position.setFace(face);
        position.setColumn(column);
        position.setRow(row);

        return position;
    }
}
