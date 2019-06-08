package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides an LbL-solution (Layer-by-Layer) for a given 3x3 Cube.
 *
 * This solution uses additional classes that encapsulate the different
 * steps of the solution. The solution is not optimized to reduce the
 * moves. Instead it uses the beginner's simplest and safest way to solve
 * the cube.
 */
public class LayerByLayerSolver {

    private final Cube _cube;
    private final CubeFaceRotationRecords _records;

    /**
     * Initializes a new instance of the LayerByLayerSolver class.
     *
     * @param cube The cube that should be solved.
     * @param records A CubeFaceRotationRecords object that receives the
     *                moves to solve the cube. This should initially be empty.
     */
    private LayerByLayerSolver(final Cube cube, final CubeFaceRotationRecords records) {
        _cube = cube;
        _records = records;
    }

    /**
     * Solves the specified cube and describes the solution
     * in the returned CubeFaceRotationRecords object move by move.
     *
     * solve() uses the LbL (Layer by Layer) strategy.
     *
     * @param cube The cube that should be solved.
     * @return A CubeFaceRotationRecords object containing the
     * moves for the solution.
     */
    public static CubeFaceRotationRecords solve(final Cube cube) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        LayerByLayerSolver lbl = new LayerByLayerSolver(cube, records);
        lbl.solve();
        return records;
    }

    /**
     * Solves the cube and fills the CubeFaceRotationRecords object with the
     * necessary moves.
     */
    private void solve() {
        // white cross
        createWhiteCross();
        setWhiteCorners();

        // second layer's edges
        setSecondLayer();

        // yellow cross and last layer
        createYellowCross();
        setYellowCrossEdges();
        setYellowCorners();
        turnYellowCorners();
    }

    /**
     * Creates a cross at the white face.
     *
     * The ends of the cross are correctly oriented at the orange, green,
     * red, and blue middle fields after the cross has been created.
     */
    private void createWhiteCross() {
        WhiteCrossStep.solve(_cube, _records);
    }

    /**
     * Positions the corners of the white layer at the correct place
     * in the correct orientation.
     *
     * The white layer is completely ready after the call.
     */
    private void setWhiteCorners() {
        WhiteCornersStep.solve(_cube, _records);
    }

    /**
     * Positions the edges of the middle layer.
     *
     * The complete white and middle layers are ready after the call.
     */
    private void setSecondLayer() { SecondLayerStep.solve(_cube, _records); }

    /**
     * Creates a yellow cross.
     *
     * The yellow cross' edges may still have a wrong orientation
     * after the call.
     */
    private void createYellowCross() { YellowCrossStep.solve(_cube, _records); }

    /**
     * Sets the orientation of the edges of the yellow cross.
     *
     * The yellow cross and its edges are correctly set after
     * the call.
     */
    private void setYellowCrossEdges() {
        YellowCrossLinearEdgesStep.solve(_cube, _records);
        YellowCrossAngleEdgesStep.solve(_cube, _records);
    }

    /**
     * Sets the position of the yellow corners.
     *
     * The corners of the yellow layer are in the right position,
     * but may still have the wrong orientation after the call.
     */
    private void setYellowCorners() {
        YellowCornersPositionFirstStep.solve(_cube, _records);
        YellowCornersPositionStep.solve(_cube, _records);
        YellowCornersPositionStep.solve(_cube, _records);
    }

    /**
     * Turns the corners of the yellow layer to the right orientation.
     *
     * The cube should be solved completely after the call.
     */
    private void turnYellowCorners() { YellowCornersOrientationStep.solve(_cube, _records); }
}
