package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Base class for the solution step classes.
 *
 * AbstractSolutionStep provides the base algorithm and methods that are common for
 * the cube solution step classes.
 */
abstract class AbstractSolutionStep {
    protected final Cube _cube;
    protected final CubeFaceRotationRecords _records;
    protected final CubeOrientation _orientation;

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
    protected AbstractSolutionStep(Cube cube, CubeFaceRotationRecords records) {
        _cube = cube;
        _records = records;
        _orientation = new CubeOrientation();
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
     * Finds the solution for specified part positioon and orientation.
     *
     * @param position The PartPosition to find the solution for.
     * @return A String with the solution steps in SpeedCube syntax.
     */
    abstract protected String findSolutionFor(PartPosition position);

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
    abstract protected PartPosition findPosition(Cube steppedCube, CubeColor face);
}
