package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YellowCrossStepTest {

    @Test
    void solve_RandomCubes() {
        for (int i = 0; i < 20; i++) {
            Cube cube = new Cube();
            CubeFaceRotationRecords records = CubeScrambler.scrambleCube(5 + i % 10, cube.getDimension());
            CubeFaceRotator rotator = new CubeFaceRotator(cube);
            CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
            player.play(records);
            solveAndAssertYellowCrossWithRecordsReport(cube, records);
        }
    }

    private static void solveAndAssertYellowCrossWithRecordsReport(
            final Cube cube,
            final CubeFaceRotationRecords scrambleRecords) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        WhiteCrossStep.solve(cube, records);
        WhiteCornersStep.solve(cube, records);
        SecondLayerStep.solve(cube, records);

        YellowCrossStep.solve(cube, records);

        String scrambleMoves = scrambleRecords.size() > 0 ? " Moves " + SpeedCubeNotationWriter.write(scrambleRecords) : "";

        Cube solvedCube = CubeFactory.create(cube, records);
        CubeFace upFace = solvedCube.getFace(CubeColor.White);

        // Up
        for (int row = 0; row < cube.getDimension(); row++) {
            for (int column = 0; column < cube.getDimension(); column++) {
                assertEquals(CubeColor.White, upFace.getField(
                        row, column), String.format("White Row %d Column %d Moves %s", row, column, scrambleMoves));
            }
        }

        // Left, Back, Right, Front
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

        // Yellow cross
        CubeFace yellowFace = solvedCube.getFace(CubeColor.Yellow);
        assertEquals(CubeColor.Yellow, yellowFace.getField(0, 1), "Yellow 0,1" + scrambleMoves);
        assertEquals(CubeColor.Yellow, yellowFace.getField(1, 2), "Yellow 1,2" + scrambleMoves);
        assertEquals(CubeColor.Yellow, yellowFace.getField(2, 1), "Yellow 2,1" + scrambleMoves);
        assertEquals(CubeColor.Yellow, yellowFace.getField(1, 0), "Yellow 1,0" + scrambleMoves);
    }

}