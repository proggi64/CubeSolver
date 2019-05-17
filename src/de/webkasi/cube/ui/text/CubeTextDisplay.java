package de.webkasi.cube.ui.text;

import de.webkasi.cube.Cube;
import de.webkasi.cube.CubeDisplay;

import java.io.PrintStream;

public class CubeTextDisplay implements CubeDisplay {
    private final PrintStream screen;
    private final Cube _cube;

    CubeTextDisplay(Cube cube) {
        _cube = cube;
        screen = System.out;
    }

    @Override
    public void display() {
        displaySingleSide(0);
        displayFourSides();
        displaySingleSide(5);
        screen.println();
    }

    private void displaySingleSide(int sideIndex) {
        for (int row=0; row<_cube.getDimension(); row++) {
            displayEmptyLayer();
            displaySingleLayer(sideIndex, row);
            screen.println();
        }
    }

    private void displayFourSides() {
        for (int row=0; row<_cube.getDimension(); row++) {
            for (int sideIndex=1; sideIndex<5; sideIndex++)
                displaySingleLayer(sideIndex, row);
            screen.println();
        }
    }

    private void displaySingleLayer(int sideIndex, int row) {
        screen.print(" ");
        for (int column=0; column<_cube.getDimension(); column++)
            screen.printf("%1$c ", _cube.getFace(sideIndex).getField(row, column).toString().charAt(0));
    }

    private void displayEmptyLayer() {
        screen.print(" ");
        for (int column=0; column<_cube.getDimension(); column++)
            screen.print("  ");
    }
}
