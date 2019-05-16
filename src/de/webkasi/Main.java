package de.webkasi;

class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(3);
        CubeLayerMover mover = new CubeLayerMover(cube);

        CubeDisplay display = new CubeTextDisplay(cube);
        display.display();

        cube.getSides()[CubeColor.White.ordinal()].setField(0, 0, CubeColor.Green);

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.Orange, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.Orange, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.Orange, 1);
        display.display();

        mover.move(CubeLayerMoveDirection.Clockwise, CubeColor.Orange, 1);
        display.display();
    }
}
