package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Adds the steps to position the corners of the yellow face toward their correct
 * position.
 *
 * The step must be called two times after the YellowCornersPositionFirstStep. It
 * may do nothing if the positions are already correct. To be sure that tehy are,
 * the solve(Cube, CubeFaceRotationRecords) must be called twice.
 */
class YellowCornersPositionStep extends AbstractYellowCornersPositionStep {

    /**
     * Initializes a new instance of the YellowCornersPositionStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross edges step.
     */
    private YellowCornersPositionStep(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
    }

    /**
     * Positions the corners of the yellow face if any corner is not already
     * in its correct position.
     *
     * The corners are moved one time per call. It may be that the corners are not in
     * the correct position after the first call. Then it must be called another time again.
     * At least after the second call all corners must be in their correct position.
     * Calling it when the corners are already correct will do nothing.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {
        YellowCornersPositionStep step = new YellowCornersPositionStep(cube, records);
        step.solve();
    }

    /**
     * The sequence to move the corners one position further.
     *
     * The lower right corner must be already in its correct position. Call
     * YellowCornersPositionFirstStep.solve() before.
     */
    private static final String positionCorner = "L' U R U' L U R' U' ";

    private static final byte upperLeftIsCorrect = 1;
    private static final byte upperRightIsCorrect = 2;
    private static final byte lowerRightIsCorrect = 4;
    private static final byte lowerLeftIsCorrect = 8;

    /**
     * Moves the three corners that are not already correct one step further,
     * if necessary.
     *
     * solve() does nothing if all corners are already in their correct position.
     */
    protected void solve() {
        String steps = "";
        switch (getCornerPositionStates()) {
            case upperLeftIsCorrect:
                steps = "x2 U2 " + positionCorner;
                break;
            case upperRightIsCorrect:
                steps = "x2 U " + positionCorner;
                break;
            case lowerRightIsCorrect:
                steps = "x2 " + positionCorner;
                break;
            case lowerLeftIsCorrect:
                steps = "x2 U' " + positionCorner;
                break;
        }
        _interpreter.addMoves(steps);
    }
}
