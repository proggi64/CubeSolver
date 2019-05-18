package de.webkasi.cube;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest {

    @Test
    void constructor_defaultColors3x3() {
        Cube cube = new Cube();
        CubeAssertion.assertFace(cube, CubeColor.White, "WWW WWW WWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "OOO OOO OOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "GGG GGG GGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "RRR RRR RRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BBB BBB BBB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YYY YYY YYY");

    }

    @Test
    void constructor_defaultColors4x4() {
        Cube cube = new Cube(4);
        CubeAssertion.assertFace(cube, CubeColor.White, "WWWW WWWW WWWW WWWW");
        CubeAssertion.assertFace(cube, CubeColor.Orange, "OOOO OOOO OOOO OOOO");
        CubeAssertion.assertFace(cube, CubeColor.Green, "GGGG GGGG GGGG GGGG");
        CubeAssertion.assertFace(cube, CubeColor.Red, "RRRR RRRR RRRR RRRR");
        CubeAssertion.assertFace(cube, CubeColor.Blue, "BBBB BBBB BBBB BBBB");
        CubeAssertion.assertFace(cube, CubeColor.Yellow, "YYYY YYYY YYYY YYYY");
    }

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