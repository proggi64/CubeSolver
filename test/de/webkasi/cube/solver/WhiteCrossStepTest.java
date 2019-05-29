package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteCrossStepTest {

    @Test
    void solve_WhiteGreenTopFront() {
        Cube cube = new Cube();
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenTopRight() {
        Cube cube = prepareCube("U'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenTopBack() {
        Cube cube = prepareCube("U2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenTopLeft() {
        Cube cube = prepareCube("U");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenLeftTop() {
        Cube cube = prepareCube("F' L'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenLeftFront() {
        Cube cube = prepareCube("F'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenLeftDown() {
        Cube cube = prepareCube("F' L");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenLeftBack() {
        Cube cube = prepareCube("F' L2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenFrontTop() {
        Cube cube = prepareCube("F' U L' U'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenFrontLeft() {
        Cube cube = prepareCube("F' U L' U' F'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenFrontRight() {
        Cube cube = prepareCube("F' U L' U' F");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenFrontDown() {
        Cube cube = prepareCube("F' U L' U' F2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenRightTop() {
        Cube cube = prepareCube("F' U L' U'2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenRightFront() {
        Cube cube = prepareCube("F' U L' U' F'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenRightBack() {
        Cube cube = prepareCube("F' U L' U' F");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenRightDown() {
        Cube cube = prepareCube("F' U L' U' F2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenBackTop() {
        Cube cube = prepareCube("F R U'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenBackLeft() {
        Cube cube = prepareCube("F' E'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenBackRight() {
        Cube cube = prepareCube("F E");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenBackDown() {
        Cube cube = prepareCube("F R' D");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenDownFront() {
        Cube cube = prepareCube("F2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenDownRight() {
        Cube cube = prepareCube("F2 D");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenDownBack() {
        Cube cube = prepareCube("F2 D2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenDownLeft() {
        Cube cube = prepareCube("F2 D'");
        solveAndAssertEdges(cube);
    }

    private static Cube prepareCube(String moves) {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);
        interpreter.addMoves(moves);
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
        player.play(records);
        return cube;
    }

    // Row at the white face corresponding to the side face that is tested
    private static final int rows[] = { 0, 1, 2, 1, 0, 0 };

    // Column at the white face corresponding to the side face that is tested
    private static final int columns[] = { 0, 0, 1, 2, 1, 0 };

    private static void solveAndAssertEdges(Cube cube) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        for (int front = 1; front < 5; front++) {
            assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(
                    rows[front], columns[front]));
            assertEquals(front, solvedCube.getFaceByIndex(front).getField(0, 1).ordinal());
        }
    }
}