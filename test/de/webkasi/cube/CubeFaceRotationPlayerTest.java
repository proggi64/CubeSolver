package de.webkasi.cube;

import org.junit.jupiter.api.Test;

class CubeFaceRotationPlayerTest {


    @Test
    void play_EmptyRecords() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void play_SingleRecord() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        records.add(new CubeFaceRotationRecord(
                CubeColor.White.ordinal(),
                RotationDirection.Clockwise,
                1)
        );
        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "GGG OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "RRR GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "BBB RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "OOO BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }
}