package de.webkasi.cube.solver;

import de.webkasi.cube.*;

final class YellowCrossAngleEdgesStep extends AbstractYellowCrossEdgesStep {
    private final SpeedCubeNotationInterpreter _interpreter;

    /**
     * Initializes a new instance of the YellowCrossAngleEdgesStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross step.
     */
    private YellowCrossAngleEdgesStep(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
        _interpreter = new SpeedCubeNotationInterpreter(_records);
    }

    /**
     * Solves the matching edges of the yellow cross at the down side and last
     * layer of the cube.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {
        YellowCrossAngleEdgesStep step = new YellowCrossAngleEdgesStep(CubeFactory.create(cube, records), records);
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

            noMove,
            noMove,

            turn + "y' " + solutionStep,
            turn + solutionStep,
            turn + "y " + solutionStep,
            turn + "y2 " + solutionStep,

            noMove
    };

    /**
     * Solves the edges of the yellow cross at the down side and last layer of the cube.
     *
     * In some cases the solution steps must be applied in a loop of two runs in this
     * method.
     */
    void solve() {
        rotateYellowFaceIfNeeded();
        YellowCrossState state;

        state = getState();
        String moves = solutions[state.ordinal()];
        _interpreter.addMoves(moves);
        addYellowFaceRotationsIfNeeded(moves);
    }

    /**
     * Tests whether a yellow face rotation is needed and adds it.
     *
     * @param moves The solutions moves of this step that should be applied before
     *              the test.
     */
    private void addYellowFaceRotationsIfNeeded(final String moves) {
        applyMoves(moves);
        rotateYellowFaceIfNeeded();
    }

    /**
     * Applies the specified solutioon moves to the internal temporary Cube object.
     *
     * This is needed to check the state after some solution steps have been added.
     *
     * @param moves The solutions moves of this step that should be applied before
     *              the test.
     */
    private void applyMoves(final String moves) {
        CubeFaceRotator rotator = new CubeFaceRotator(_cube);
        CubeFaceRotationRecords temporaryRecords = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter temporaryInterpreter = new SpeedCubeNotationInterpreter(temporaryRecords);
        temporaryInterpreter.addMoves(moves);
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
        player.play(temporaryRecords);
    }
}
