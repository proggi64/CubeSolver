package de.webkasi.cube.ui.text;

import de.webkasi.cube.*;

@SuppressWarnings("ALL")
class Main {

    public static void main(String[] args) {
        testScramble(3, 25);
    }

    private static void testScramble(int dimension, int depth)
    {
        Cube cube = new Cube(dimension);
        CubeScrambler.scrambleCube(cube, depth);

        CubeDisplay display = new CubeTextDisplay(cube);
        display.display();
    }

    private static void testForthBack() {
        Cube cube = new Cube(4);
        CubeFaceRotator mover = new CubeFaceRotator(cube);

        CubeDisplay display = new CubeTextDisplay(cube);
        display.display();

        mover.rotateFace(RotationDirection.Clockwise, CubeColor.Yellow.ordinal(), 1);
        display.display();

        mover.rotateFace(RotationDirection.Counterclockwise, CubeColor.Blue.ordinal(), 1);
        display.display();

        mover.rotateFace(RotationDirection.Clockwise, CubeColor.White.ordinal(), 1);
        display.display();

        mover.rotateFace(RotationDirection.Clockwise, CubeColor.Green.ordinal(), 2);
        display.display();

        mover.rotateFace(RotationDirection.Counterclockwise, CubeColor.Green.ordinal(), 2);
        display.display();

        mover.rotateFace(RotationDirection.Counterclockwise, CubeColor.White.ordinal(), 1);
        display.display();

        mover.rotateFace(RotationDirection.Clockwise, CubeColor.Blue.ordinal(), 1);
        display.display();

        mover.rotateFace(RotationDirection.Counterclockwise, CubeColor.Yellow.ordinal(), 1);
        display.display();
    }

}
