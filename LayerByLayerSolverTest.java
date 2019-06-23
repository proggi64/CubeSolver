package de.webkasi.cube.solver;

import de.webkasi.cube.*;
import org.junit.jupiter.api.Test;

class LayerByLayerSolverTest {
    @Test
    void solve_RandomCubes() {
        for (int i = 0; i < 500; i++) {
            Cube cube = new Cube();
            CubeFaceRotationRecords records = CubeScrambler.scrambleCube(15 + i % 10, cube.getDimension());
            CubeFaceRotator rotator = new CubeFaceRotator(cube);
            CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
            player.play(records);

            CubeFaceRotationRecords solution = LayerByLayerSolver.solve(cube);
            Cube solvedCube = CubeFactory.create(cube, solution);

            assertCube(solvedCube);
        }
    }

    /**
     * Tests whether a cube is solved correctly.
     *
     * @param solvedCube The Cube to test.
     */
    private static void assertCube(
            final Cube solvedCube) {
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Yellow, "YYY YYY YYY");
    }
}