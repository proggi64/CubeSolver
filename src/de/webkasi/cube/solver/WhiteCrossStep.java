package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for creating a white cross on a
 * scrambled 3x3 Magic Cube.
 */
public class WhiteCrossStep {

    static final String turnEdge = "F U' R U";

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
        // TODO Für diese Schritte eigene Klasse WhiteCrossCreator schreiben
        // TODO für alle weißen Randsteine:
        // TODO Situationsanalyse: Wo ist der weiße Randstein?
        // TODO Bewegungsalgorithmus für jede Situation anwenden
        // TODO Ggfs. Drehung des Randsteins
    }
}
