package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteCrossCornersStepTest {

    @Test
    void solve_AllUp01() {
        Cube cube = prepareCube("");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllUp02() {
        Cube cube = prepareCube("U");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllUp03() {
        Cube cube = prepareCube("U2");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllUp04() {
        Cube cube = prepareCube("U'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllBack01() {
        Cube cube = prepareCube("L R'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllBack02() {
        Cube cube = prepareCube("L R' B");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllBack03() {
        Cube cube = prepareCube("L R' B2");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllBack04() {
        Cube cube = prepareCube("L R' B'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllLeft01() {
        Cube cube = prepareCube("F' B");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllLeft02() {
        Cube cube = prepareCube("F' B L");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllLeft03() {
        Cube cube = prepareCube("F' B L2");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllLeft04() {
        Cube cube = prepareCube("F' B L'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllFront01() {
        Cube cube = prepareCube("L R'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllFront02() {
        Cube cube = prepareCube("L R' F");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllFront03() {
        Cube cube = prepareCube("L R' F2");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllFront04() {
        Cube cube = prepareCube("L R' F'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllRight01() {
        Cube cube = prepareCube("F B'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllRight02() {
        Cube cube = prepareCube("F B' R");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllRight03() {
        Cube cube = prepareCube("F B' R2");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllRight04() {
        Cube cube = prepareCube("F B' R'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllDown01() {
        Cube cube = prepareCube("F2 B2");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllDown02() {
        Cube cube = prepareCube("F2 B2 D");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllDown03() {
        Cube cube = prepareCube("F2 B2 D2");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_AllDown04() {
        Cube cube = prepareCube("F2 B2 D'");
        solveAndAssertUpLayer(cube);
    }

    @Test
    void solve_RandomCubes() {
        for (int i = 0; i < 100; i++) {
            Cube cube = new Cube();
            CubeFaceRotationRecords records = CubeScrambler.scrambleCube(5 + i % 10, cube.getDimension());
            CubeFaceRotator rotator = new CubeFaceRotator(cube);
            CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
            player.play(records);
            solveAndAssertWhiteLayerWithRecordsReport(cube, records);
        }
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

    private static void solveAndAssertWhiteCorners(Cube cube) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossCornersStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        CubeFace upFace = solvedCube.getFace(CubeColor.White);

        int expectedColor = CubeColor.White.ordinal();
        CubeFace cubeFace = solvedCube.getFaceByIndex(expectedColor);
        assertEquals(expectedColor, cubeFace.getField(0, 0).ordinal(), "0,0");
        assertEquals(expectedColor, cubeFace.getField(0, 1).ordinal(), "0,1");
        assertEquals(expectedColor, cubeFace.getField(0, 2).ordinal(), "0,2");
        assertEquals(expectedColor, cubeFace.getField(1, 1).ordinal(), "1,1");
    }

    private static void solveAndAssertWhiteLayerWithRecordsReport(Cube cube, CubeFaceRotationRecords scrambleRecords) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        WhiteCrossStep.solve(cube, records);
        cube = CubeFactory.create(cube, records);
        records.clear();

        WhiteCrossCornersStep.solve(cube, records);

        String scrambleMoves = SpeedCubeNotationWriter.write(scrambleRecords);

        Cube solvedCube = CubeFactory.create(cube, records);
        CubeFace upFace = solvedCube.getFace(CubeColor.White);
        for (int row = 0; row < cube.getDimension(); row++) {
            for (int column = 0; column < cube.getDimension(); column++) {
                assertEquals(CubeColor.White, upFace.getField(
                        row, column), String.format("White Row %d Column %d Moves %s", row, column, scrambleMoves));
            }
        }

        for (int face = 1; face < 5; face++) {
            CubeFace cubeFace = solvedCube.getFaceByIndex(face);
            assertEquals(face, cubeFace.getField(0, 0).ordinal(), "0,0 Moves " + scrambleMoves);
            assertEquals(face, cubeFace.getField(0, 1).ordinal(), "0,1 Moves " + scrambleMoves);
            assertEquals(face, cubeFace.getField(0, 2).ordinal(), "0,2 Moves " + scrambleMoves);
            assertEquals(face, cubeFace.getField(1, 1).ordinal(), "1,1 Moves " + scrambleMoves);
        }
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