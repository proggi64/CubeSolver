package de.webkasi.cube;

import org.junit.jupiter.api.Test;

class CubeFaceRotatorTest {

    @Test
    void rotateFace_ClockwiseWhiteOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.White, 1);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "GGG OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "RRR GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "BBB RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "OOO BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void rotateFace_CounterclockwiseWhiteOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.White, 1);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "BBB OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "OOO GGG GGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "GGG RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "RRR BBB BBB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void rotateFace_ClockwiseOrangeOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Orange, 1);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "BWW BWW BWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "WGG WGG WGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBY BBY BBY");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "GYY GYY GYY");
    }

    @Test
    void rotateFace_CounterclockwiseOrangeOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.Orange, 1);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "GWW GWW GWW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "YGG YGG YGG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BBW BBW BBW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "BYY BYY BYY");
    }

    @Test
    void rotateFace_Pattern() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.Orange, 1);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.Orange, 1);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.Red, 1);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.Red, 1);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Blue, 1);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Blue, 1);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Green, 1);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Green, 1);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.White, 1);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.White, 1);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Yellow, 1);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Yellow, 1);

        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }
}