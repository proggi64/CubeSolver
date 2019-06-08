package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Positions the first corner of the yellow face if no corner is already
 * in its correct position.
 */
public class YellowCornersPositionFirstStep extends AbstractYellowCornersPositionStep {

    /**
     * Initializes a new instance of the YellowCornersPositionFirstStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross edges step.
     */
    YellowCornersPositionFirstStep(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
    }

    /**
     * Positions the first corner of the yellow face if no corner is already
     * in its correct position.
     *
     * If any corner is already in its correct position then no move will be added.
     * This step must be applied before the YellowCrossCornersPositionStep is performed.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                If any steps are already in the records then they will be applied
     *                to an internal copy of the cube before this step is solved for
     *                this internal copy. The state of the cube used internally is the state after
     *                the records initially passed to solve() have been applied.
     */
    static void solve(Cube cube, CubeFaceRotationRecords records) {
        YellowCornersPositionFirstStep step = new YellowCornersPositionFirstStep(cube, records);
        step.solve();
    }

    /**
     * The sequence of moves to position the first corner at the yellow face
     * in SpeedCube notation syntax.
     */
    private static final String positionFirstCorner = "x2 R U' L' U R' U' L U ";

    /**
     * Positions the first corner of the yellow face if no corner is already
     * in its correct position.
     */
    protected void solve() {
        byte cornerPositionStates = getCornerPositionStates();
        if (Integer.bitCount((int)cornerPositionStates) == 0)
            _interpreter.addMoves(positionFirstCorner);
    }
}
