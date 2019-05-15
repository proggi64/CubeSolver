package de.webkasi;

import java.io.PrintStream;

public class CubeTextDisplay implements CubeDisplay {
    private PrintStream screen;
    private Cube _cube;

    CubeTextDisplay(Cube cube) {
        _cube = cube;
        screen = System.out;
    }

    @Override
    public void display() {
        displaySingleSide(0);
        displayFourSides();
        displaySingleSide(5);
    }

    private void displaySingleSide(int sideIndex) {
        for (int layer=0; layer<_cube.getDimension(); layer++) {
            displayEmptyLayer();
            displaySingleLayer(sideIndex, layer);
            screen.println();
        }
    }

    private void displayFourSides() {
        for (int layer=0; layer<_cube.getDimension(); layer++) {
            for (int sideIndex=1; sideIndex<5; sideIndex++)
                displaySingleLayer(sideIndex, layer);
            screen.println();
        }
    }

    private void displaySingleLayer(int sideIndex, int layer) {
        screen.print(" ");
        for (int i=0; i<_cube.getDimension(); i++)
            screen.printf("%1$c ", _cube.getSides()[sideIndex].getFields()[layer][i].toString().charAt(0));
    }

    private void displayEmptyLayer() {
        screen.print(" ");
        for (int i=0; i<_cube.getDimension(); i++)
            screen.print("  ");
    }
}
