package de.webkasi;

class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(4);
        CubeLayerMover mover = new CubeLayerMover(cube);

        CubeDisplay display = new CubeTextDisplay(cube);
        display.display();

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.Yellow, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Counterclockwise, CubeColor.Blue, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.White, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.Green, 2);
        display.display();

        mover.move(CubeLayerMoveDirection.Counterclockwise, CubeColor.Green, 2);
        display.display();

        mover.move(CubeLayerMoveDirection.Counterclockwise, CubeColor.White, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.Blue, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Counterclockwise, CubeColor.Yellow, 1);
        display.display();
    }
}
