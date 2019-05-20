package de.webkasi.cube;

import org.junit.jupiter.api.Test;

class CubeFaceRotatorTest {

    @Test
    void rotateFace_ClockwiseWhiteOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.White, 1);

        CubeAssertion.assertFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "GGG OOO OOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "RRR GGG GGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "BBB RRR RRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "OOO BBB BBB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void rotateFace_CounterclockwiseWhiteOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.White, 1);

        CubeAssertion.assertFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "BBB OOO OOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "OOO GGG GGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "GGG RRR RRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "RRR BBB BBB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YYY YYY YYY");
    }

    @Test
    void rotateFace_ClockwiseOrangeOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.Orange, 1);

        CubeAssertion.assertFace(cube, CubeColor.White, "BWW BWW BWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "WGG WGG WGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BBY BBY BBY");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "GYY GYY GYY");
    }

    @Test
    void rotateFace_CounterclockwiseOrangeOne() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);

        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.Orange, 1);

        CubeAssertion.assertFace(cube, CubeColor.White, "GWW GWW GWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "YGG YGG YGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BBW BBW BBW");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "BYY BYY BYY");
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

        CubeAssertion.assertFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }
}