package de.webkasi.cube.solver;

import org.junit.jupiter.api.Test;

import de.webkasi.cube.*;

import static org.junit.jupiter.api.Assertions.*;

class CubeOrientationTest {
    private static final int white = CubeColor.White.ordinal();
    private static final int orange = CubeColor.Orange.ordinal();
    private static final int green = CubeColor.Green.ordinal();
    private static final int red = CubeColor.Red.ordinal();
    private static final int blue = CubeColor.Blue.ordinal();
    private static final int yellow = CubeColor.Yellow.ordinal();

    @Test
    void orientation_Default() {
        CubeOrientation orientation = new CubeOrientation();
        assertEquals(white, orientation.up);
        assertEquals(orange, orientation.left);
        assertEquals(green, orientation.front);
        assertEquals(red, orientation.right);
        assertEquals(blue, orientation.back);
        assertEquals(yellow, orientation.down);
    }

    @Test
    void rotate_xClockwiseOne() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('x', RotationDirection.Clockwise, 1);
        assertEquals(blue, orientation.up);
        assertEquals(orange, orientation.left);
        assertEquals(white, orientation.front);
        assertEquals(red, orientation.right);
        assertEquals(yellow, orientation.back);
        assertEquals(green, orientation.down);
    }

    @Test
    void rotate_xCounterclockwiseOne() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('x', RotationDirection.Counterclockwise, 1);
        assertEquals(green, orientation.up);
        assertEquals(orange, orientation.left);
        assertEquals(yellow, orientation.front);
        assertEquals(red, orientation.right);
        assertEquals(white, orientation.back);
        assertEquals(blue, orientation.down);
    }

    @Test
    void rotate_xClockwiseTwo() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('x', RotationDirection.Clockwise, 2);
        assertEquals(yellow, orientation.up);
        assertEquals(orange, orientation.left);
        assertEquals(blue, orientation.front);
        assertEquals(red, orientation.right);
        assertEquals(green, orientation.back);
        assertEquals(white, orientation.down);
    }

    @Test
    void rotate_xCounterclockwiseTwo() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('x', RotationDirection.Counterclockwise, 2);
        assertEquals(yellow, orientation.up);
        assertEquals(orange, orientation.left);
        assertEquals(blue, orientation.front);
        assertEquals(red, orientation.right);
        assertEquals(green, orientation.back);
        assertEquals(white, orientation.down);
    }

    @Test
    void rotate_yClockwiseOne() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        assertEquals(white, orientation.up);
        assertEquals(blue, orientation.left);
        assertEquals(orange, orientation.front);
        assertEquals(green, orientation.right);
        assertEquals(red, orientation.back);
        assertEquals(yellow, orientation.down);
    }

    @Test
    void rotate_yCounterclockwiseOne() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        assertEquals(white, orientation.up);
        assertEquals(green, orientation.left);
        assertEquals(red, orientation.front);
        assertEquals(blue, orientation.right);
        assertEquals(orange, orientation.back);
        assertEquals(yellow, orientation.down);
    }

    @Test
    void rotate_yClockwiseTwo() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 2);
        assertEquals(white, orientation.up);
        assertEquals(red, orientation.left);
        assertEquals(blue, orientation.front);
        assertEquals(orange, orientation.right);
        assertEquals(green, orientation.back);
        assertEquals(yellow, orientation.down);
    }

    @Test
    void rotate_yCounterclockwiseTwo() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Counterclockwise, 2);
        assertEquals(white, orientation.up);
        assertEquals(red, orientation.left);
        assertEquals(blue, orientation.front);
        assertEquals(orange, orientation.right);
        assertEquals(green, orientation.back);
        assertEquals(yellow, orientation.down);
    }

    @Test
    void rotate_zClockwiseOne() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('z', RotationDirection.Clockwise, 1);
        assertEquals(orange, orientation.up);
        assertEquals(yellow, orientation.left);
        assertEquals(green, orientation.front);
        assertEquals(white, orientation.right);
        assertEquals(blue, orientation.back);
        assertEquals(red, orientation.down);
    }

    @Test
    void rotate_zCounterclockwiseOne() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('z', RotationDirection.Counterclockwise, 1);
        assertEquals(red, orientation.up);
        assertEquals(white, orientation.left);
        assertEquals(green, orientation.front);
        assertEquals(yellow, orientation.right);
        assertEquals(blue, orientation.back);
        assertEquals(orange, orientation.down);
    }

    @Test
    void rotate_zClockwiseTwo() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('z', RotationDirection.Clockwise, 2);
        assertEquals(yellow, orientation.up);
        assertEquals(red, orientation.left);
        assertEquals(green, orientation.front);
        assertEquals(orange, orientation.right);
        assertEquals(blue, orientation.back);
        assertEquals(white, orientation.down);
    }

    @Test
    void rotate_zCounterclockwiseTwo() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('z', RotationDirection.Counterclockwise, 2);
        assertEquals(yellow, orientation.up);
        assertEquals(red, orientation.left);
        assertEquals(green, orientation.front);
        assertEquals(orange, orientation.right);
        assertEquals(blue, orientation.back);
        assertEquals(white, orientation.down);
    }

    @Test
    void rotate_xyzClockwise() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('x', RotationDirection.Clockwise, 1);
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        orientation.rotate('z', RotationDirection.Clockwise, 1);
        assertEquals(yellow, orientation.up);
        assertEquals(green, orientation.left);
        assertEquals(orange, orientation.front);
        assertEquals(blue, orientation.right);
        assertEquals(red, orientation.back);
        assertEquals(white, orientation.down);
    }
}