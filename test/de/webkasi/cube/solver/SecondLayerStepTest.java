package de.webkasi.cube.solver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import de.webkasi.cube.*;

class SecondLayerStepTest {

    private static final int front = CubeColor.Green.ordinal();
    private static final int left = CubeColor.Orange.ordinal();
    private static final int back = CubeColor.Blue.ordinal();
    private static final int right = CubeColor.Red.ordinal();
    private static final int down = CubeColor.Yellow.ordinal();

    @Test
    void solve_Default() {
        Cube cube = new Cube();
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_FrontLeft() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, front, 1, 0);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_FrontRight() {
        Cube cube = new Cube();
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_FrontDown() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, front, 2, 1);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_LeftBack() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, left, 1, 0);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_LeftFront() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, left, 1, 2);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_LeftDown() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, left, 2, 1);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_BackRight() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, back, 1, 0);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_BackLeft() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, back, 1, 2);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_BackDown() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, back, 2, 1);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_RightFront() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, right, 1, 0);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_RightBack() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, right, 1, 2);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_RightDown() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, right, 2, 1);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_DownFront() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, down, 0, 1);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_DownLeft() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, down, 1, 0);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_DownBack() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, down, 2, 1);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_DownRight() {
        Cube cube = new Cube();
        swapRightEdgeWith(cube, down, 1, 2);
        solveAndAssertUpperTwoLayers(cube);
    }

    @Test
    void solve_RandomCubes() {
        for (int i = 0; i < 100; i++) {
            Cube cube = new Cube();
            CubeFaceRotationRecords records = CubeScrambler.scrambleCube(5 + i % 10, cube.getDimension());
            CubeFaceRotator rotator = new CubeFaceRotator(cube);
            CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
            player.play(records);
            solveAndAssertUpperTwoLayerWithRecordsReport(cube, records);
        }
    }

    @Test
    void findPosition() {
    }

    private static void solveAndAssertUpperTwoLayers(final Cube cube) {
        CubeFaceRotationRecords empty = new CubeFaceRotationRecords();
        solveAndAssertUpperTwoLayerWithRecordsReport(cube, empty);
    }

    private static void solveAndAssertUpperTwoLayerWithRecordsReport(
            final Cube cube,
            final CubeFaceRotationRecords scrambleRecords) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        WhiteCrossStep.solve(cube, records);
        WhiteCornersStep.solve(cube, records);

        Cube steppedCube = CubeFactory.create(cube, records);
        records.clear();

        solveAndAssertSecondLayerWithRecordsReport(steppedCube, scrambleRecords);
    }

    private static void solveAndAssertSecondLayerWithRecordsReport(
            final Cube cube,
            final CubeFaceRotationRecords scrambleRecords) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SecondLayerStep.solve(cube, records);

        String scrambleMoves = scrambleRecords.size() > 0 ? " Moves " + SpeedCubeNotationWriter.write(scrambleRecords) : "";

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
            String faceOutput = String.format("Face %d ", face);
            assertEquals(face, cubeFace.getField(0, 0).ordinal(), faceOutput + "0,0" + scrambleMoves);
            assertEquals(face, cubeFace.getField(0, 1).ordinal(), faceOutput + "0,1" + scrambleMoves);
            assertEquals(face, cubeFace.getField(0, 2).ordinal(), faceOutput + "0,2" + scrambleMoves);
            assertEquals(face, cubeFace.getField(1, 0).ordinal(), faceOutput + "1,0" + scrambleMoves);
            assertEquals(face, cubeFace.getField(1, 1).ordinal(), faceOutput + "1,1" + scrambleMoves);
            assertEquals(face, cubeFace.getField(1, 2).ordinal(), faceOutput + "1,2" + scrambleMoves);
        }
    }

    static final int faceIndex = 3;
    static final int rowIndex = 4;
    static final int columnIndex = 5;

    /**
     * Places the green/red edge with its green field to the specified position
     * and swaps it with the destination fields.
     *
     * @param cube The Cube to change.
     * @param face The destination face of the green field.
     * @param row The row of the green field.
     * @param column The column iof the green field.
     */
    private static void swapRightEdgeWith(Cube cube, int face, int row, int column) {
        CubeFace greenFace = cube.getFace(CubeColor.Green);
        CubeFace redFace = cube.getFace(CubeColor.Red);

        int[] edgeCoordinates = PositionFinder.getEdgeNeighbor(face, row, column);
        CubeFace destinationFace = cube.getFaceByIndex(face);
        CubeFace destinationNeighborFace = cube.getFaceByIndex(edgeCoordinates[faceIndex]);

        greenFace.setField(1, 2, destinationFace.getField(row, column));
        redFace.setField(1, 0,
                destinationNeighborFace.getField(edgeCoordinates[rowIndex], edgeCoordinates[columnIndex]));

        destinationFace.setField(row, column, CubeColor.Green);
        destinationNeighborFace.setField(edgeCoordinates[rowIndex], edgeCoordinates[columnIndex], CubeColor.Red);
    }
}