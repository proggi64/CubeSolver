package de.webkasi.cube.solver;

import de.webkasi.cube.*;
import de.webkasi.cube.ui.text.CubeTextDescriptor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Only used for calling the test method getCornerPositionStates for the unit tests
class CornerTest extends YellowCornersPositionStep {
    CornerTest(Cube cube, CubeFaceRotationRecords records) {
        super(cube, records);
    }

    byte getState() { return getCornerPositionStates(); }
}

class YellowCornersPositionFirstStepTest {

    @Test
    void solve_SpecificSituation01() {
        CubeTextDescriptor descriptor = new CubeTextDescriptor();
        Cube cube = new Cube();
        descriptor.describeCube(cube, new String[] {
                "WWW WWW WWW", "OOO OOO YOY", "GGG GGG BGY", "RRR RRR GRG", "BBB BBB YBB", "OYR YYY RYO" });

        solveAndAssertFirstYellowCornerPositionWithRecordsReport(cube, new CubeFaceRotationRecords());
    }

    @Test
    void solve_RandomCubes() {
        for (int i = 0; i < 100; i++) {
            Cube cube = new Cube();
            CubeFaceRotationRecords records = CubeScrambler.scrambleCube(5 + i % 10, cube.getDimension());
            CubeFaceRotator rotator = new CubeFaceRotator(cube);
            CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);
            player.play(records);
            solveAndAssertFirstYellowCornerPositionWithRecordsReport(cube, records);
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

    private static void solveAndAssertFirstYellowCornerPositionWithRecordsReport(
            final Cube cube,
            final CubeFaceRotationRecords scrambleRecords) {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        WhiteCrossStep.solve(cube, records);
        WhiteCornersStep.solve(cube, records);
        SecondLayerStep.solve(cube, records);
        YellowCrossStep.solve(cube, records);
        YellowCrossLinearEdgesStep.solve(cube, records);
        YellowCrossAngleEdgesStep.solve(cube, records);

        YellowCornersPositionStep.solve(cube, records);

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

        // Side colors of the edges
        assertEquals(CubeColor.Orange, solvedCube.getFace(CubeColor.Orange).getField(2, 1), "Orange 2,1" + scrambleMoves);
        assertEquals(CubeColor.Green, solvedCube.getFace(CubeColor.Green).getField(2, 1), "Green 2,1" + scrambleMoves);
        assertEquals(CubeColor.Red, solvedCube.getFace(CubeColor.Red).getField(2, 1), "Red 2,1" + scrambleMoves);
        assertEquals(CubeColor.Blue, solvedCube.getFace(CubeColor.Blue).getField(2, 1), "Blue 2,1" + scrambleMoves);

        assertYellowCornerPositions(solvedCube, scrambleMoves);
    }

    private static void assertYellowCornerPositions(Cube solvedCube, String scrambleMoves) {
        CornerTest test = new CornerTest(solvedCube, new CubeFaceRotationRecords());
        byte state = test.getState();
        assertEquals(0x0f, (int)state, "Corner in the correct position! " + scrambleMoves);
    }
}