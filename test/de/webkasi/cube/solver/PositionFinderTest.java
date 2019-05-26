package de.webkasi.cube.solver;

import de.webkasi.cube.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionFinderTest {

    private static final int Up = 0;
    private static final int Front = 2;
    private static final int Right = 3;
    private static final int Down = 5;

    @Test
    void findEdge_UpFront() {
        Cube cube = new Cube();
        EdgePosition position = PositionFinder.FindEdge(cube, CubeColor.White, CubeColor.Green);
        assertEquals(Up, position.getFace());
        assertEquals(2, position.getRow());
        assertEquals(1, position.getColumn());
    }

    @Test
    void findEdge_DownBack() {
        Cube cube = new Cube();
        EdgePosition position = PositionFinder.FindEdge(cube, CubeColor.Yellow, CubeColor.Blue);
        assertEquals(Down, position.getFace());
        assertEquals(2, position.getRow());
        assertEquals(1, position.getColumn());
    }

    @Test
    void findEdge_DownFront() {
        Cube cube = new Cube();
        EdgePosition position = PositionFinder.FindEdge(cube, CubeColor.Yellow, CubeColor.Green);
        assertEquals(Down, position.getFace());
        assertEquals(0, position.getRow());
        assertEquals(1, position.getColumn());
    }

    @Test
    void findEdge_RightFront() {
        Cube cube = new Cube();
        EdgePosition position = PositionFinder.FindEdge(cube, CubeColor.Red, CubeColor.Green);
        assertEquals(Right, position.getFace());
        assertEquals(1, position.getRow());
        assertEquals(0, position.getColumn());
    }

    @Test
    void findEdge_FrontRight() {
        Cube cube = new Cube();
        EdgePosition position = PositionFinder.FindEdge(cube, CubeColor.Green, CubeColor.Red);
        assertEquals(Front, position.getFace());
        assertEquals(1, position.getRow());
        assertEquals(2, position.getColumn());
    }

    @Test
    void findEdge_GreenWhiteAfterCounterclockwiseRotation() {
        Cube cube = new Cube();
        CubeFaceRotator rotator = new CubeFaceRotator(cube);
        rotator.rotateFace(RotationDirection.Counterclockwise, CubeColor.White.ordinal(), 1);
        EdgePosition position = PositionFinder.FindEdge(cube, CubeColor.White, CubeColor.Green);
        assertEquals(Up, position.getFace());
        assertEquals(1, position.getRow());
        assertEquals(0, position.getColumn());
    }

    @Test
    void findCorner() {
    }
}