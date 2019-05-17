package de.webkasi.cube.ui.text;

import de.webkasi.cube.Cube;
import de.webkasi.cube.CubeDisplay;

import java.io.PrintStream;

/**
 * Displays the Cube in a terminal window as matrices of characters.
 *
 * The object is initialized with the Cube's instance that should be
 * displayed. It displays the cube at the System.out text device.
 */
public class CubeTextDisplay implements CubeDisplay {
    private final PrintStream screen;
    private final Cube _cube;

    /**
     * Initializes a new instance of CubeTextDisplay with System.out
     * as the output destination.
     *
     * @param cube The Cube object to display.
     */
    CubeTextDisplay(Cube cube) {
        _cube = cube;
        screen = System.out;
    }

    /**
     * Displays the Cube object as matrices of characters in the
     * output console.
     *
     * Each cube color is represented as the first character of the
     * color's name. See the CubeColor enumeration for these characters.
     */
    @Override
    public void display() {
        displayTopOrBottomFace(0);
        displayFourFaces();
        displayTopOrBottomFace(5);
        screen.println();
    }

    /**
     * Displays the specified top or bottom face of the Cube
     * as an indented matrix of characters.
     *
     * The indentation is used to reserve the space for the left
     * side face of the top or bottom face for displayFourFaces().
     *
     * @param faceIndex The index of the cube's face to be displayed.
     */
    private void displayTopOrBottomFace(int faceIndex) {
        for (int row=0; row<_cube.getDimension(); row++) {
            displayEmptyLayer();
            displaySingleLayer(faceIndex, row);
            screen.println();
        }
    }

    /**
     * Displays the middle four faces of the cube as matrices of
     * characters.
     */
    private void displayFourFaces() {
        for (int row=0; row<_cube.getDimension(); row++) {
            for (int sideIndex=1; sideIndex<5; sideIndex++)
                displaySingleLayer(sideIndex, row);
            screen.println();
        }
    }

    /**
     * Displays the fields of the specified layer of the specified face.
     *
     * Each field is represented by the first character of the color
     * name. A layer consists of the number of fields that is equal
     * to the cube's dimension. Each face has also the same count of
     * layers.
     *
     * @param faceIndex The index of the face of the fields to display.
     * @param row The row index of the layer to display.
     */
    private void displaySingleLayer(int faceIndex, int row) {
        screen.print(" ");
        for (int column=0; column<_cube.getDimension(); column++)
            screen.printf("%1$c ", _cube.getFace(faceIndex).getField(row, column).toString().charAt(0));
    }

    /**
     * Displays an empty space as large as a complete face's layer.
     *
     * displayEmptyLayer() is used to create the needed indent for
     * the top and bottom faces.
     */
    private void displayEmptyLayer() {
        screen.print(" ");
        for (int column=0; column<_cube.getDimension(); column++)
            screen.print("  ");
    }
}
