package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionFinderTest {

    private static final int Up = 0;
    private static final int Left = 1;
    private static final int Front = 2;
    private static final int Right = 3;
    private static final int Back = 4;
    private static final int Down = 5;

    @Test
    void findEdge_UpFront() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.White, CubeColor.Green);
        assertPosition(position, Up, 2, 1);
    }

    @Test
    void findEdge_UpRight() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.White, CubeColor.Red);
        assertPosition(position, Up, 1, 2);
    }

    @Test
    void findEdge_DownBack() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.Yellow, CubeColor.Blue);
        assertPosition(position, Down, 2, 1);
    }

    @Test
    void findEdge_DownFront() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.Yellow, CubeColor.Green);
        assertPosition(position, Down, 0, 1);
    }

    @Test
    void findEdge_RightFront() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.Red, CubeColor.Green);
        assertPosition(position, Right, 1, 0);
    }

    @Test
    void findEdge_FrontRight() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.Green, CubeColor.Red);
        assertPosition(position, Front, 1, 2);
    }

    @Test
    void findEdge_WhiteGreenAfterCounterclockwiseRotation() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.White.ordinal(), 1);
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.White, CubeColor.Green);
        assertPosition(position, Up, 1, 2);
    }

    @Test
    void findEdge_WhiteGreenAfterClockwiseRotation() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.White.ordinal(), 1);
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.White, CubeColor.Green);
        assertPosition(position, Up, 1, 0);
    }

    @Test
    void findEdge_WhiteGreenAfterDoubleRotation() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.White.ordinal(), 1);
        rotator.rotateFace(RotationDirection.Clockwise, CubeColor.White.ordinal(), 1);
        PartPosition position = PositionFinder.findEdge(cube, CubeColor.White, CubeColor.Green);
        assertPosition(position, Up, 0, 1);
    }

    @Test
    void findCorner_DefaultWhiteBlueOrange() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.White, CubeColor.Blue);
        assertPosition(position, Up, 0, 0);
    }

    @Test
    void findCorner_DefaultWhiteRedBlue() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.White, CubeColor.Red);
        assertPosition(position, Up, 0, 2);
    }

    @Test
    void findCorner_DefaultWhiteGreenRed() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.White, CubeColor.Green);
        assertPosition(position, Up, 2, 2);
    }

    @Test
    void findCorner_DefaultWhiteOrangeGreen() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.White, CubeColor.Orange);
        assertPosition(position, Up, 2, 0);
    }

    @Test
    void findCorner_DefaultOrangeWhiteBlue() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Orange, CubeColor.White);
        assertPosition(position, Left, 0, 0);
    }

    @Test
    void findCorner_DefaultOrangeGreenWhite() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Orange, CubeColor.Green);
        assertPosition(position, Left, 0, 2);
    }

    @Test
    void findCorner_DefaultOrangeYellowGreen() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Orange, CubeColor.Yellow);
        assertPosition(position, Left, 2, 2);
    }

    @Test
    void findCorner_DefaultOrangeBlueYellow() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Orange, CubeColor.Blue);
        assertPosition(position, Left, 2, 0);
    }

    @Test
    void findCorner_DefaultGreenWhiteOrange() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Green, CubeColor.White);
        assertPosition(position, Front, 0, 0);
    }

    @Test
    void findCorner_DefaultGreenRedWhite() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Green, CubeColor.Red);
        assertPosition(position, Front, 0, 2);
    }

    @Test
    void findCorner_DefaultGreenYellowRed() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Green, CubeColor.Yellow);
        assertPosition(position, Front, 2, 2);
    }

    @Test
    void findCorner_DefaultGreenOrangeYellow() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Green, CubeColor.Orange);
        assertPosition(position, Front, 2, 0);
    }

    @Test
    void findCorner_DefaultRedWhiteGreen() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Red, CubeColor.White);
        assertPosition(position, Right, 0, 0);
    }

    @Test
    void findCorner_DefaultRedBlueWhite() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Red, CubeColor.Blue);
        assertPosition(position, Right, 0, 2);
    }

    @Test
    void findCorner_DefaultRedYellowBlue() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Red, CubeColor.Yellow);
        assertPosition(position, Right, 2, 2);
    }

    @Test
    void findCorner_DefaultRedGreenYellow() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Red, CubeColor.Green);
        assertPosition(position, Right, 2, 0);
    }

    @Test
    void findCorner_DefaultBlueWhiteRed() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Blue, CubeColor.White);
        assertPosition(position, Back, 0, 0);
    }

    @Test
    void findCorner_DefaultBlueOrangeWhite() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Blue, CubeColor.Orange);
        assertPosition(position, Back, 0, 2);
    }

    @Test
    void findCorner_DefaultBlueYellowOrange() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Blue, CubeColor.Yellow);
        assertPosition(position, Back, 2, 2);
    }

    @Test
    void findCorner_DefaultBlueRedYellow() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Blue, CubeColor.Red);
        assertPosition(position, Back, 2, 0);
    }

    @Test
    void findCorner_DefaultYellowGreenOrange() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Yellow, CubeColor.Green);
        assertPosition(position, Down, 0, 0);
    }

    @Test
    void findCorner_DefaultYellowRedGreen() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Yellow, CubeColor.Red);
        assertPosition(position, Down, 0, 2);
    }

    @Test
    void findCorner_DefaultYellowBlueRed() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Yellow, CubeColor.Blue);
        assertPosition(position, Down, 2, 2);
    }

    @Test
    void findCorner_DefaultYellowOrangeBlue() {
        Cube cube = new Cube();
        PartPosition position = PositionFinder.findCorner(cube, CubeColor.Yellow, CubeColor.Orange);
        assertPosition(position, Down, 2, 0);
    }

    static void assertPosition(PartPosition position, int face, int row, int column) {
        assertEquals(face, position.getFace(), "Face");
        assertEquals(row, position.getRow(), "Row");
        assertEquals(column, position.getColumn(), "Column");
    }
}