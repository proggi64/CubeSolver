package de.webkasi;

public class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(3);
        CubeTextDisplay display = new CubeTextDisplay(cube);
        display.display();
    }
}
