package de.webkasi.cube.solver;

import de.webkasi.cube.*;

final class YellowCrossEdgesStep extends AbstractYellowCrossStep {
    private final SpeedCubeNotationInterpreter _interpreter;

    /**
     * Initializes a new instance of the YellowCrossEdgesStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross step.
     */
    private YellowCrossEdgesStep(Cube cube, CubeFaceRotationRecords records) {
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
        YellowCrossEdgesStep step = new YellowCrossEdgesStep(CubeFactory.create(cube, records), records);
        step.solve();
    }

    /**
     * Sequence that moves two yellow edges in an angle at the back and the right
     * that their sides get the right position.
     *
     * This move can be repeated to solve a linear orientation of the parts to be moved.
     */
    private static final String solutionStep = "R U R' U R U2 R' ";

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
    protected void solve() {
        rotateYellowFaceIfNeeded();
        YellowCrossState state;

        // Only one additional loop can occur when a horizontal or vertical row occurs
        do {
            state = getState();
            String moves = solutions[state.ordinal()];
            _interpreter.addMoves(moves);
            addYellowFaceRotationsIfNeeded(moves);
        } while (state == YellowCrossState.LineHorizontal || state == YellowCrossState.LineVertical);
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

    /**
     * Analyzes the orientation of all four yellow edge parts and returns an enumeration
     * value that specifies this state.
     *
     * The enumeration value is used as an array index to get the matching sequence
     * of solution steps to create the yellow cross. The state determines which
     * side color of the yellow edges are already in their correct position. For this step,
     * only a maximum of two may not be in the right position. So the
     * value YellowCrossState.None cannot occur.
     *
     * @return A YellowCrossState value describing the current state of the side colors
     * of the yellow edges.
     */
    private YellowCrossState getState() {
        byte bits = getSideColorState(_cube);

        switch (bits) {
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

    /**
     * Rotates the cube's yellow face until at least two edges are at the correct position.
     *
     * The yellow face in the current state can be rotated until at least two edges
     * have their correct side color. Another state is not possible.
     */
    private void rotateYellowFaceIfNeeded() {
        CubeFaceRotator rotator = new CubeFaceRotator(_cube);

        while (Integer.bitCount(getSideColorState(_cube)) < 2) {
            // record the rotation
            _interpreter.addMoves("x2 U");
            // rotate the temporary _cube to get correct results in getEdgeBits()
            rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Yellow, 1);
        }
    }

    /**
     * Gets the state of each of the four yellow edges whether it has the
     * correct color.
     *
     * @param cube The Cube to test.
     * @return A byte value with 4 bits representing whether the corresponding side
     * of a yellow edge has the right color. Orange is bit 0, Green is bit 1, Red
     * is bit 2, and Blue is bit 3. A vlue of 1 means that the color is correct.
     */
    private static byte getSideColorState(Cube cube) {
        byte bits = 0;
        bits += cube.getFace(CubeColor.Orange).getField(2, 1) == CubeColor.Orange ? 1 : 0;
        bits += cube.getFace(CubeColor.Green).getField(2, 1) == CubeColor.Green ? 2 : 0;
        bits += cube.getFace(CubeColor.Red).getField(2, 1) == CubeColor.Red ? 4 : 0;
        bits += cube.getFace(CubeColor.Blue).getField(2, 1) == CubeColor.Blue ? 8 : 0;
        return bits;
    }
}
