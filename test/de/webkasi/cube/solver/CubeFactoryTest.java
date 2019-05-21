package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeFactoryTest {

    @Test
    void create_NonDefaultWithEmptyRecords() {
        Cube baseCube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(baseCube);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Orange, 1);
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();

        Cube cube = CubeFactory.create(baseCube, records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "BWW BWW BWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "WGG WGG WGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBY BBY BBY");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "GYY GYY GYY");
    }

    @Test
    void create_WithPlayedRecords() {
        Cube baseCube = new Cube();
        CubeFaceRotationRecords records = new CubeFaceRotationRecords();
        SpeedCubeNotationInterpreter interpreter = new SpeedCubeNotationInterpreter(records);
        interpreter.addMoves("M2 S2 E2");

        Cube cube = CubeFactory.create(baseCube, records);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }
}