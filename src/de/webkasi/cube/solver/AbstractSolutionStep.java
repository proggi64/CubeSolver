package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Base class for the solution step classes.
 *
 * AbstractSolutionStep provides the base algorithm and methods that are common for
 * the cube solution step classes.
 */
abstract class AbstractSolutionStep {
    private final Cube _cube;
    private final CubeFaceRotationRecords _records;
    private final CubeOrientation _orientation;
    private final Solution[] _solutions;

    /**
     * Index of the upper face.
     */
    protected static final int up = CubeColor.White.ordinal();
    /**
     * Index of the left face.
     */
    protected static final int left = CubeColor.Orange.ordinal();
    /**
     * Index of the front face.
     */
    protected static final int front = CubeColor.Green.ordinal();
    /**
     * Index of the right face.
     */
    protected static final int right = CubeColor.Red.ordinal();
    /**
     * Index of the back face.
     */
    protected static final int back = CubeColor.Blue.ordinal();
    /**
     * Index of the down face.
     */
    protected static final int down = CubeColor.Yellow.ordinal();

    /**
     * Initializes a new instance of the AbstractSolutionStep class with the
     * specified Cube and CubeFaceRotationsRecords objects. Call this by the
     * inherited constructor with the super keyword.
     *
     * @param cube The Cube to solve. The cube may be completely scrambled.
     *             There is is no need of any previous step to pass a Cube
     *             to this method.
     * @param records The CubeFaceRotationRecords object receiving the solution steps.
     *                This must contain the previous steps of the WhiteCrossStep.solve()
     *                method.
     */
    protected AbstractSolutionStep(
            final Cube cube,
            final CubeFaceRotationRecords records,
            final Solution[] solutions) {
        _cube = cube;
        _records = records;
        _orientation = new CubeOrientation();
        _solutions = solutions;
    }

    /**
     * Faces in the order how they are processed by the solve() method.
     */
    private static final CubeColor[] faceSteps = { CubeColor.Green, CubeColor.Orange, CubeColor.Blue, CubeColor.Red };

    /**
     * Solves the White Cross Corners Step for the given cube.
     *
     * solve() brings each side face to the front and solves its right top corner
     * before it advances to the next face.
     * It brings the corner to the down face exactly under its destination position
     * without destroying anything of the already solved parts of the upper layer.
     *
     * The last move is the return to the default orientation of the cube: Green
     * front and white up.
     */
    protected void solve() {
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(_records);
        StringBuilder cubeRotations = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            Cube steppedCube = CubeFactory.create(_cube, _records);

            PartPosition cornerPosition = findPosition(steppedCube, faceSteps[i]);
            String solutionMoves = cubeRotations + findSolutionFor(cornerPosition);
            interpreter.addMoves(solutionMoves);

            // Go to the next front face: The y-rotations are only interpreted by each
            // addMoves call, so we have to append one "y" per rotation.
            _orientation.rotate('y', RotationDirection.Clockwise, 1);
            cubeRotations.append("y ");
        }
    }

    /**
     * Finds the solution for a specific position.
     *
     * For each possible part position exists a sequence of moves.
     * These moves are stored in an internal array of Solution
     * objects. The array is searched for the position and the
     * corresponding solution ist returned.
     *
     * The position refers to absolute coordinates of the cube.
     * The top (white) face has index 0 and its row 0 and column 0 is in the
     * upper left of the face. Each solution has a position as its key.
     * Each solution describes how to bring the part at the position
     * to the front up position with the white face up.
     *
     * If the cube is rotated to a new front face, the actual part position
     * must be translated to the position orientation of the keys to find
     * the correct solution.
     *
     * @param position The PartPosition to find the solution for.
     * @return A String with the solution steps in SpeedCube syntax.
     */
    private String findSolutionFor(final PartPosition position) {
        int i = 0;
        PartPosition translatedPosition = PositionTranslator.translate(position, _orientation);
        while (true) {
            if (translatedPosition.isEqual(_solutions[i].position))
                return _solutions[i].moves;
            i++;
        }
    }

    /**
     * Finds the position of the specified part.
     *
     * Each implementor calls a specific method of the PositionFinder class,
     * depending on whether an edge or a corner is searched.
     *
     * @param steppedCube The Cube with the applied current found solutions steps.
     * @param face The cube's face that is currently the front face.
     * @return A PartPosition object with the absolute coordinates of the searched part.
     */
    abstract protected PartPosition findPosition(final Cube steppedCube, final CubeColor face);
}
