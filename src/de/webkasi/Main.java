package de.webkasi;

class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(3);
        CubeDisplay display = new CubeTextDisplay(cube);
        display.display();
    }
}
