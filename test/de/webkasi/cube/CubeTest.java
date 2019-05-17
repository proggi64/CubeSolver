package de.webkasi.cube;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest {

    @Test
    void getDimension_is2() {
        Cube cube = new Cube(2);
        assertEquals(2, cube.getDimension());
    }

    @Test
    void getDimension_isDefault3() {
        Cube cube = new Cube();
        assertEquals(3, cube.getDimension());
    }

    @Test
    void getDimension_is3() {
        Cube cube = new Cube(3);
        assertEquals(3, cube.getDimension());
    }

    @Test
    void getDimension_is4() {
        Cube cube = new Cube(4);
        assertEquals(4, cube.getDimension());
    }

    @Test
    void getDimension_is5() {
        Cube cube = new Cube(5);
        assertEquals(5, cube.getDimension());
    }

    @Test
    void getFaces_isLength6() {
        Cube cube = new Cube();
        CubeFace[] faces = cube.getFaces();
        assertEquals(6, faces.length);
    }

    @Test
    void getFaces_notNull() {
        Cube cube = new Cube();
        CubeFace[] faces = cube.getFaces();
        for (CubeFace face : faces) assertNotNull(face);
    }

    @Test
    void getFaces_expectedColorsDefaultDimension() {
        Cube cube = new Cube();
        CubeFace[] faces = cube.getFaces();
        for (int index = 0; index < faces.length; index++) {
            CubeFace face = faces[index];
            for (int row = 0; row < cube.getDimension(); row++) {
                for (int column = 0; column < cube.getDimension(); column++) {
                    assertEquals(index, face.getField(row, column).ordinal());
                }
            }
        }
    }

    @Test
    void getFaces_expectedColors5x5() {
        Cube cube = new Cube(5);
        CubeFace[] faces = cube.getFaces();
        for (int index = 0; index < faces.length; index++) {
            CubeFace face = faces[index];
            for (int row = 0; row < cube.getDimension(); row++) {
                for (int column = 0; column < cube.getDimension(); column++) {
                    assertEquals(index, face.getField(row, column).ordinal());
                }
            }
        }
    }
}