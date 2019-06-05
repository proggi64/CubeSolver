package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for creating a yellow cross at the yellow face
 * of a 3x3 Magic Cube.
 *
 * The previous steps must be already done and the passed CubeFaceRotationRecords
 * has to contains all the previous moves.
 */
class YellowCrossStep {
    private final Cube _cube;
    private final CubeFaceRotationRecords _records;

    /**
     * Initializes a new instance of the YellowCrossStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps before the yellow cross step.
     */
    YellowCrossStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _records = records;
    }

    /**
     * Solves the yellow cross at the down side and last layer of the cube.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {
        YellowCrossStep step = new YellowCrossStep(CubeFactory.create(cube, records), records);
        step.solve();
    }

    /**
     * Represents the state of the yellow fields at the yellow face.
     */
    private enum YellowCrossState {
        /**
         * No yellow side is up on the yellow face.
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
         * Yellow parts are at 1,0 and 0,1.
         */
        AngleLeftBack,
        /**
         * Yellow parts are at 1,2 and 0,1.
         */
        AngleBackRight,
        /**
         * Yellow parts are at 1,2 and 2,1.
         */
        AngleRightFront,
        /**
         * Yellow parts are at 1,0 and 2,1.
         */
        AngleFrontLeft,
        /**
         * All yellow parts are already at the yellow face.
         */
        All
    }

    private static final String noMove = "";
    private static final String turn = "z2 ";
    private static final String solutionStep = "F R U R' U' F' ";

    /**
     * Contains the matching solution steps in SpeedCube syntax for
     * each possible state of the yellow part at the yellow face to
     * get the yellow cross.
     */
    private static final String solutions[] = new String[] {
            turn + solutionStep + solutionStep + solutionStep,

            turn + solutionStep,
            turn + "U " + solutionStep,

            turn + solutionStep + solutionStep,
            turn + "U' " + solutionStep + solutionStep,
            turn + "U2 " + solutionStep + solutionStep,
            turn + "U " +solutionStep +solutionStep,

            noMove
    };

    /**
     * Solves the yellow cross at the down side and last layer of the cube.
     */
    private void solve() {
        String moves = solutions[getState().ordinal()];
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(_records);
        interpreter.addMoves(moves);
    }

    /**
     * Analyzes the orientation of all four yellow edge parts and returns an enumeration
     * value that specifies this state.
     *
     * The enumeration value is used as an array index to get the matching sequence
     * of solution steps to create the yellow cross.
     *
     * @return A YellowCrossState value describing the current state of the yellow fields
     * at the yellow face.
     */
    private YellowCrossState getState() {
        // TODO Analyse der gelben Felder
        return YellowCrossState.All;
    }
}
