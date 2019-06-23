package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Base class for the yellow cross edges steps.
 */
abstract class AbstractYellowCrossEdgesStep extends AbstractYellowCrossStep {
    private final SpeedCubeNotationInterpreter _interpreter;

    /**
     * Initializes a new instance of the AbstractYellowCrossEdgesStep class.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain all previous steps until and including the
     *                yellow cross step.
     */
    AbstractYellowCrossEdgesStep(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
        _interpreter = new SpeedCubeNotationInterpreter(_records);
    }

    /**
     * Sequence that moves two yellow edges in an angle at the back and the right
     * that their sides get the right position.
     *
     * This move can be repeated to solve a linear orientation of the parts to be moved.
     */
    static final String solutionStep = "R U R' U R U2 R' ";

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
    YellowCrossState getState() {
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
    void rotateYellowFaceIfNeeded() {
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
