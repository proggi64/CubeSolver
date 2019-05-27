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

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
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

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY WYW YWY");
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

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY WYW YWY");
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

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void addMoves_PatternWithCubeRotation() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("L2 R2 z L2 R2 y' L2 R2");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }

    @Test
    void addMoves_MoveM() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("M");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WBW WBW WBW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GWG GWG GWG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BYB BYB BYB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YGY YGY YGY");
    }

    @Test
    void addMoves_MoveM2() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("M2");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW WYW WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG GBG GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB BGB BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY YWY YWY");
    }

    @Test
    void addMoves_MoveE() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("E");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO BBB OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG OOO GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR GGG RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB RRR BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void addMoves_MoveE2() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("E2");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO RRR OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG BBB GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR OOO RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB GGG BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void addMoves_MoveS() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("S");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW RRR WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OWO OWO OWO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RYR RYR RYR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY OOO YYY");
    }

    @Test
    void addMoves_MoveS2() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("S2");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW YYY WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ORO ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ROR ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY WWW YYY");
    }

    @Test
    void addMoves_MoveSReverse() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("S'");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW OOO WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OYO OYO OYO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RWR RWR RWR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY RRR YYY");
    }

    @Test
    void addMoves_PatternWithMiddleLayerMoves() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);

        interpreter.addMoves("M2 S2 E2");

        CubeFaceRotationPlayer player = new CubeFaceRotationPlayer(rotator);

        player.play(records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }
}