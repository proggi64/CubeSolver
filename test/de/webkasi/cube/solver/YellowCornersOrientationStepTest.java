package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import de.webkasi.cube.ui.text.CubeTextDescriptor;
import org.junit.jupiter.api.Test;

class YellowCornersOrientationStepTest {

    @Test
    void solve_AllCornersCorrect() {
        CubeTextDescriptor descriptor = new CubeTextDescriptor();
        Cube cube = new Cube();
        descriptor.describeCube(cube, new String[] {
                "WWW WWW WWW", "OOO OOO OOO", "GGG GGG GGG", "RRR RRR RRR", "BBB BBB BBB", "YYY YYY YYY" });

        solveAndAssertCube(cube);
    }

    @Test
    void solve_SpecificSituation01() {
        CubeTextDescriptor descriptor = new CubeTextDescriptor();
        Cube cube = new Cube();
        descriptor.describeCube(cube, new String[] {
                "WWW WWW WWW", "OOO OOO YOY", "GGG GGG BGY", "RRR RRR GRG", "BBB BBB YBB", "OYR YYY RYO" });

        solveAndAssertCube(cube);
    }

    @Test
    void solve_SpecificSituation02() {
        CubeTextDescriptor descriptor = new CubeTextDescriptor();
        Cube cube = new Cube();
        descriptor.describeCube(cube, new String[] {
                "GGW RWW OOR", "RWB OOR OOW", "YYW GGW GGG", "GBR GRR YRR", "BYY BBW BBW", "OOO BYY BYY" });

        solveAndAssertCube(cube);
    }

    @Test
    void solve_RandomCubes() {
        for (int i = 0; i < 100; i++) {
            Cube cube = new Cube();
            CubeFaceRotationRecords records = CubeScrambler.scrambleCube(15 + i % 10, cube.getDimension());
            CubeFaceRotator rotator = new CubeFaceRotator(cube);
            CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
            player.play(records);
            solveAndAssertCube(cube);
        }
    }

    /**
     * Solves a scrambled cube and tests whether it is solved correctly.
     *
     * Tests whether the solution steps of the Layer-by-Layer solution
     * solve any given cube. The steps are applied in the correct order
     * to the given cube and the six faces are tested at the end.
     * @param cube The Cube to solve.
     */
    private static void solveAndAssertCube(
            final Cube cube) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        WhiteCrossStep.solve(cube, records);
        WhiteCornersStep.solve(cube, records);
        SecondLayerStep.solve(cube, records);
        YellowCrossStep.solve(cube, records);
        YellowCrossLinearEdgesStep.solve(cube, records);
        YellowCrossAngleEdgesStep.solve(cube, records);
        YellowCornersPositionStep.solve(cube, records);
        YellowCornersOrientationStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);

        CubeAssertion.assertCubeFace(solvedCube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(solvedCube, CubeColor.Yellow, "YYY YYY YYY");
    }

}