package de.webkasi.cube;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeFaceRotationPlayerTest {


    @Test
    void play_EmptyRecords() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void play_SingleRecord() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        records.add(new CubeFaceRotationRecord(
                CubeColor.White,
                CubeFaceRotationDirection.Clockwise,
                1)
        );
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "GGG OOO OOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "RRR GGG GGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "BBB RRR RRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "OOO BBB BBB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }
}