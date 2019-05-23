package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for creating a white cross on a
 * scrambled 3x3 Magic Cube.
 */
class WhiteCrossStep {

    private final Cube _cube;
    private final CubeFaceRotationRecords _records;

    /**
     * Sequence that turns the edge of the cross.
     *
     * This sequence turns the edge if it is in the right position, but has the wrong
     * orientation. No other edge of the white cross will be effected. The sequence
     * is valid when the edge is at the front face. When using for other edges, the cube
     * must be rotated into the right position first.
     */
    static final String turnEdge = "F' U L' U";

    /**
     * Sequence that positions the edge from the front left to the top.
     *
     * This sequence positions the edge from the left side of the front
     * to the top.
     */
    static final String frontLeftToFrontUp = "F";

    /**
     * Sequence that positions the edge from the front right to the top.
     *
     * This sequence positions the edge from the right side of the front
     * to the top.
     */
    static final String frontRightToFrontUp = "F'";

    /**
     * Sequence that positions the edge from the front bottom to the top.
     *
     * This sequence positions the edge from th bottom of the front
     * to the top.
     */
    static final String frontDownToFrontUp = "F2";

    /**
     * Sequence that positions the edge from the back middle left to the left front.
     */
    static final String backEquatorLeftToFrontLeft = "M";

    /**
     * Sequence that positions the edge from the back middle right to the right front.
     */
    static final String backEquatorRightToFrontRight = "M'";

    /**
     * Sequence that positions the edge from the down left to the down front.
     */
    static final String downLeftToDownFront = "D";

    /**
     * Sequence that positions the edge from the down right to the down front.
     */
    static final String downRightToDownFront = "D'";

    /**
     * Sequence that positions the edge from the down back to the down front.
     */
    static final String downBackToDownFront = "D2";

    /**
     * Sequence that positions the edge from the back up to the back left position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * Then the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    static final String upBackToBackLeft = "B";

    /**
     * Sequence that positions the edge from the up left to the front left position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * Then the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    static final String upLeftToFrontLeft = "L";

    /**
     * Sequence that positions the edge from the up right to the front right position.
     *
     * This is needed when the edge is already at the up face, but at the wrong position.
     * The the edge must be moved to the front up position. This is the first step for
     * this sequence.
     */
    static final String upRightToFrontRight = "R'";

    /**
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     */
    private WhiteCrossStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _records = records;
    }

    /**
     * Creates a white cross as described for the Layer-by-Layer algorithm.
     *
     * solve() scans the cube to determine which algorithms are needed to
     * get the white cross. The orientation of the corners may be still wrong
     * after the call. This orientation should be corrected by another step class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     */
    static public void solve(Cube cube, CubeFaceRotationRecords records) {
        WhiteCrossStep step = new WhiteCrossStep(cube, records);
        // TODO für alle weißen Randsteine:
        // TODO Situationsanalyse: Wo ist der weiße Randstein?
        // TODO Drehe den Würfel so, dass der jeweilige Ziel-Randstein vorne oben sein soll (CubeOrientation)
        // TODO Weißen Randstein ohne Orientierung zwischen Weiß und passende Farbe
        // TODO Bewegungsalgorithmus für jede Situation anwenden: Stringsequenzen für die jeweilige Position zusammenstellen
        // TODO Prüfen, ob Orientierung passt
        // TODO Falls nicht: turnEdge für betroffenen Randstein

        // 1. WG 2. Y WR 3. Y WB 4. Y WO
    }


}
