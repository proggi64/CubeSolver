package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteCrossStepTest {

    @Test
    void solve_WhiteGreenTopFront() {
        Cube cube = new Cube();
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenTopRight() {
        Cube cube = prepareCube("U'");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenTopBack() {
        Cube cube = prepareCube("U2");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenTopLeft() {
        Cube cube = prepareCube("U");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenLeftTop() {
        Cube cube = prepareCube("F' L'");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenLeftFront() {
        Cube cube = prepareCube("F'");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenLeftDown() {
        Cube cube = prepareCube("F' L");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenLeftBack() {
        Cube cube = prepareCube("F' L2");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    Cube prepareCube(String moves) {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);
        interpreter.addMoves(moves);
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
        player.play(records);
        return cube;
    }
}