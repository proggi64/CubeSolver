package de.webkasi.cube.solver;

import de.webkasi.cube.*;
import org.junit.jupiter.api.Test;

class SpeedCubeNotationInterpreterTest {

    @Test
    void addMoves_Empty() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("");

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
    void addMoves_Pattern() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("U2 D'2 F2 B'2 L2 R'2");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }

    @Test
    void addMoves_PatternTwoLayers() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("u2 Dw'2 f2 b'2 l2 r'2");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }

    @Test
    void addMoves_MultipleForthAndReverse() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("R2 U R U R' U' R' U' R' U R'");
        interpreter.addMoves("R U' R U R U R U' R' U' R2");

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
    void addMoves_PatternWithCubeRotation() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("L2 R2 z L2 R2 y z' L2 R2");

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