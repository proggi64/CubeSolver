package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Step for yellow edges that are in place in a linear formation.
 */
class YellowCrossLinearEdgesStep extends AbstractYellowCrossEdgesStep {
    private final SpeedCubeNotationInterpreter _interpreter;

    /**
     * Initializes a new instance of the YellowCrossLinearEdgesStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross step.
     */
    private YellowCrossLinearEdgesStep(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
        _interpreter = new SpeedCubeNotationInterpreter(_records);
    }

    /**
     * Makes the fist step when yellow edges are in a linear position.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {
        YellowCrossLinearEdgesStep step = new YellowCrossLinearEdgesStep(CubeFactory.create(cube, records), records);
        step.solve();
    }

    /**
     * Contains the matching solution steps in SpeedCube syntax for
     * each possible state of the yellow part at the yellow face to
     * get the yellow cross.
     *
     * Each array element has a numerical value of the YellowCrossState
     * as its index that is the solution for this state.
     */
    private static final String[] solutions = new String[] {
            noMove,     // impossible!

            turn + "y " + solutionStep,     // repeat after apply
            turn + solutionStep,            // repeat after apply

            noMove,
            noMove,
            noMove,
            noMove,

            noMove
    };

    /**
     * Solves the edges of the yellow cross at the down side and last layer of the cube.
     *
     * In some cases the solution steps must be applied in a loop of two runs in this
     * method.
     */
    private void solve() {
        rotateYellowFaceIfNeeded();
        String moves = solutions[getState().ordinal()];
        _interpreter.addMoves(moves);
    }
}
