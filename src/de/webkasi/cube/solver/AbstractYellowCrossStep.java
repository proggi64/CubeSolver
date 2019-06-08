package de.webkasi.cube.solver;

import de.webkasi.cube.Cube;
import de.webkasi.cube.CubeFaceRotationRecords;

/**
 * Base class for yellow cross solution steps of the Magic Cube.
 */
abstract class AbstractYellowCrossStep {
    final Cube _cube;
    final CubeFaceRotationRecords _records;

    /**
     * No move necessary.
     */
    static final String noMove = "";

    /**
     * Turns the cube that the blue face is the front and the yellow is up.
     *
     * The coordinates of the yellow face are oriented correctly so that
     * the sequences match the orientation.
     */
    static final String turn = "x2 ";

    /**
     * Represents the state of the yellow edge fields at the yellow face.
     */
    protected enum YellowCrossState {
        /**
         * No edge part is at the right place.
         */
        None,
        /**
         * Two yellow parts create a horizontal line at the row 1.
         */
        LineHorizontal,
        /**
         * Two yellow parts create a vertical line at the column 1.
         */
        LineVertical,
        /**
         * Yellow parts are at 1,0 and 0,1 in correct position.
         */
        AngleLeftBack,
        /**
         * Yellow parts are at 1,2 and 0,1 in correct position.
         */
        AngleBackRight,
        /**
         * Yellow parts are at 1,2 and 2,1 in correct position.
         */
        AngleRightFront,
        /**
         * Yellow parts are at 1,0 and 2,1 in correct position.
         */
        AngleFrontLeft,
        /**
         * All yellow parts are already at the correct position.
         */
        All,
        /**
         * Undefined: This combination cannot occur unless the cube is inconsistent.
         */
        Undefined
    }

    /**
     * Initializes a new instance of the AbstractYellowCrossStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps before the yellow cross step.
     */
    AbstractYellowCrossStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _records = records;
    }

    /**
     * Solves the yellow cross at the down side and last layer of the cube.
     */
    abstract void solve();
}
