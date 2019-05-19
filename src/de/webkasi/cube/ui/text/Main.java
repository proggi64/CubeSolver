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

        mover.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Yellow, 1);
        display.display();

        mover.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Blue, 1);
        display.display();

        mover.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.White, 1);
        display.display();

        mover.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Green, 2);
        display.display();

        mover.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Green, 2);
        display.display();

        mover.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.White, 1);
        display.display();

        mover.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Blue, 1);
        display.display();

        mover.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Yellow, 1);
        display.display();
    }

}
