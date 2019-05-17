package de.webkasi.cube.ui.text;

import de.webkasi.cube.*;
import de.webkasi.cube.ui.text.CubeTextDisplay;

class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(4);
        CubeFaceRotater mover = new CubeFaceRotater(cube);

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
