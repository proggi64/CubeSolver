package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteCrossCornersStepTest {

    @Test
    void solve_Default() {
        Cube cube = prepareCube("");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_Down() {
        Cube cube = prepareCube("D");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_Up() {
        Cube cube = prepareCube("U");
        solveAndAssertUpLayer(cube);
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

    private static void solveAndAssertUpLayer(Cube cube) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        WhiteCrossStep.solve(cube, records);
        cube = CubeFactory.create(cube, records);
        records.clear();

        WhiteCrossCornersStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        CubeFace upFace = solvedCube.getFace(CubeColor.White);
        for (int row = 0; row < cube.getDimension(); row++) {
            for (int column = 0; column < cube.getDimension(); column++) {
                assertEquals(CubeColor.White, upFace.getField(
                        row, column), String.format("White Row %d Column %d", row, column));
            }
        }

        for (int face = 1; face < 5; face++) {
            CubeFace cubeFace = solvedCube.getFaceByIndex(face);
            assertEquals(face, cubeFace.getField(0, 0).ordinal(), "0,0");
            assertEquals(face, cubeFace.getField(0, 1).ordinal(), "0,1");
            assertEquals(face, cubeFace.getField(0, 2).ordinal(), "0,2");
            assertEquals(face, cubeFace.getField(1, 1).ordinal(), "1,1");
        }
    }
}