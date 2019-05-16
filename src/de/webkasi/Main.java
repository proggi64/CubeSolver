package de.webkasi;

class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(4);
        CubeFaceRotater mover = new CubeFaceRotater(cube);

        CubeDisplay display = new CubeTextDisplay(cube);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Clockwise, CubeColor.Yellow, 1);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Counterclockwise, CubeColor.Blue, 1);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Clockwise, CubeColor.White, 1);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Clockwise, CubeColor.Green, 2);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Counterclockwise, CubeColor.Green, 2);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Counterclockwise, CubeColor.White, 1);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Clockwise, CubeColor.Blue, 1);
        display.display();

        mover.rotateFace(CubeLayerMoveDirection.Counterclockwise, CubeColor.Yellow, 1);
        display.display();
    }
}
