package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteCrossStepTest {

    @Test
    void solve_WhiteGreenFrontTop() {
        Cube cube = new Cube();
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenRightTop() {
        Cube cube = prepareCube("U'");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenBackTop() {
        Cube cube = prepareCube("U2");
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(2, 1));
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(0, 1));
    }

    @Test
    void solve_WhiteGreenLeftTop() {
        Cube cube = prepareCube("U");
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