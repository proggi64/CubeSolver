package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the solution steps for creating a yellow cross at the yellow face
 * of a 3x3 Magic Cube.
 *
 * The yellow edges of the cross will not always be in their correct position after
 * this step.
 *
 * The previous steps must be already done and the passed CubeFaceRotationRecords
 * has to contains all the previous moves.
 */
final class YellowCrossStep extends AbstractYellowCrossStep {
    /**
     * Initializes a new instance of the YellowCrossStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps before the yellow cross step.
     */
    private YellowCrossStep(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
    }

    /**
     * Solves the yellow cross at the down side and last layer of the cube.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
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
     * Sequence that moves two yellow sides in an angle at the back and the right
     * to the yellow face.
     *
     * This move can be repeated to solve a linear orientation of the parts to be moved.
     */
    private static final String solutionStep = "F R U R' U' F' ";

    /**
     * Contains the matching solution steps in SpeedCube syntax for
     * each possible state of the yellow part at the yellow face to
     * get the yellow cross.
     *
     * Each array element has a numerical value of the YellowCrossState
     * as its index that is the solution for this state.
     */
    private static final String[] solutions = new String[] {
            turn + solutionStep + "U2 " + solutionStep + solutionStep,

            turn + solutionStep,
            turn + "U " + solutionStep,

            turn + solutionStep + solutionStep,
            turn + "U' " + solutionStep + solutionStep,
            turn + "U2 " + solutionStep + solutionStep,
            turn + "U " + solutionStep + solutionStep,

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
        CubeFace yellowFace = _cube.getFace(CubeColor.Yellow);
        byte bits = 0;
        bits += yellowFace.getField(1, 0) == CubeColor.Yellow ? 1 : 0;
        bits += yellowFace.getField(0, 1) == CubeColor.Yellow ? 2 : 0;
        bits += yellowFace.getField(1, 2) == CubeColor.Yellow ? 4 : 0;
        bits += yellowFace.getField(2, 1) == CubeColor.Yellow ? 8 : 0;

        switch (bits) {
            case 0:
                return YellowCrossState.None;
            case 3:
                return YellowCrossState.AngleLeftBack;
            case 5:
                return YellowCrossState.LineHorizontal;
            case 9:
                return YellowCrossState.AngleFrontLeft;
            case 6:
                return YellowCrossState.AngleBackRight;
            case 10:
                return YellowCrossState.LineVertical;
            case 12:
                return YellowCrossState.AngleRightFront;
            case 15:
                return YellowCrossState.All;
        }
        return YellowCrossState.Undefined;
    }
}
