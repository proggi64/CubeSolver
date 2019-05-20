package de.webkasi.cube.solver;

import org.junit.jupiter.api.Test;

import de.webkasi.cube.*;

import static org.junit.jupiter.api.Assertions.*;

class SpeedCubeNotationWriterTest {

    @Test
    void write_InEqualsOut() {
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);
        final String moves = "R L2 U' d B2 R' D' f'2 ";
        interpreter.addMoves(moves);

        assertEquals(moves, SpeedCubeNotationWriter.Write(records));
    }

    @Test
    void write_PatternResultsEqual() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords recordsSource = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreterSource = new SpeedCubeNotationInterpreter(recordsSource);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        // This syntax is not supported by the Writer, but the result must be the same
        interpreterSource.addMoves("M2 S2 E2");

        interpreter.addMoves(SpeedCubeNotationWriter.Write(recordsSource));

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }
}