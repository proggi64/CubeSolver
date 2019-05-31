package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiteCrossStepTest {

    @Test
    void solve_WhiteGreenUpFront() {
        Cube cube = new Cube();
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenUpRight() {
        Cube cube = prepareCube("U'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenUpBack() {
        Cube cube = prepareCube("U2");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenUpLeft() {
        Cube cube = prepareCube("U");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenLeftUp() {
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
    void solve_WhiteGreenFrontUp() {
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
    void solve_WhiteGreenRightUp() {
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
    void solve_WhiteGreenBackUp() {
        Cube cube = prepareCube("F R U'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenBackLeft() {
        // Do not use E, M or S for preparation, because it rotates the default
        // positions of the center parts and the cube should has to be rotated back
        Cube cube = prepareCube("F'2 D' L' B L B'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_WhiteGreenBackRight() {
        Cube cube = prepareCube("F2 D R' B R B'");
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

    @Test
    void solve_RandomManual01() {
        Cube cube = prepareCube("U B' L");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_RandomManual02() {
        Cube cube = prepareCube("U R' D'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_RandomManual03() {
        Cube cube = prepareCube("U' L D'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_RandomManual04() {
        Cube cube = prepareCube("L' U R'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_RandomManual05() {
        Cube cube = prepareCube("U F L'");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_RandomManual06() {
        Cube cube = prepareCube("U B U");
        solveAndAssertEdges(cube);
    }

    @Test
    void solve_RandomCubes() {
        for (int i = 0; i < 100; i++) {
            Cube cube = new Cube();
            CubeFaceRotationRecords records = CubeScrambler.scrambleCube(5 + i % 10, cube.getDimension());
            CubeFaceRotator rotator = new CubeFaceRotator(cube);
            CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
            player.play(records);
            solveAndAssertEdgesWithRecordsReport(cube, records);
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

    // Row at the white face corresponding to the side face that is tested
    private static final int[] rows = {0, 1, 2, 1, 0, 0};

    // Column at the white face corresponding to the side face that is tested
    private static final int[] columns = {0, 0, 1, 2, 1, 0};

    private static void solveAndAssertEdges(Cube cube) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        Cube solvedCube = CubeFactory.create(cube, records);
        for (int face = 1; face < 5; face++) {
            assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(
                    rows[face], columns[face]), String.format("White Side %d Row %d Column %d", face, rows[face], columns[face]));
            CubeFace cubeFace = solvedCube.getFaceByIndex(face);
            assertEquals(face, cubeFace.getField(0, 1).ordinal(), String.format("Face: %d, 0,1", face));
            assertEquals(face, cubeFace.getField(1, 1).ordinal(), String.format("Face: %d, 1,1", face));
        }
    }

    private static void solveAndAssertEdgesWithRecordsReport(Cube cube, CubeFaceRotationRecords scrambleRecords) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        WhiteCrossStep.solve(cube, records);

        String scrambleMoves = SpeedCubeNotationWriter.write(scrambleRecords);

        Cube solvedCube = CubeFactory.create(cube, records);
        for (int face = 1; face < 5; face++) {
            assertEquals(CubeColor.White, solvedCube.getFace(CubeColor.White).getField(
                    rows[face], columns[face]), String.format("White Side %d Row %d Column %d: %s", face, rows[face], columns[face], scrambleMoves));
            CubeFace cubeFace = solvedCube.getFaceByIndex(face);
            assertEquals(face, cubeFace.getField(0, 1).ordinal(), String.format("Face: %d, 0,1: %s", face, scrambleMoves));
            assertEquals(face, cubeFace.getField(1, 1).ordinal(), String.format("Face: %d, 1,1: %s", face, scrambleMoves));
        }
    }
}