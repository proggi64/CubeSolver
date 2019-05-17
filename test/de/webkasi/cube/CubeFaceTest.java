package de.webkasi.cube;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeFaceTest {

    @Test
    void setFaceColor_hasColor() {
        CubeFace face = new CubeFace(2);
        face.setFaceColor(CubeColor.Blue);

        assertEquals(CubeColor.Blue, face.getField(0, 0));
        assertEquals(CubeColor.Blue, face.getField(1, 0));
        assertEquals(CubeColor.Blue, face.getField(0, 1));
        assertEquals(CubeColor.Blue, face.getField(1, 1));
    }

    @Test
    void setFaceColor_changeColor() {
        CubeFace face = new CubeFace(2);
        face.setFaceColor(CubeColor.Blue);
        face.setFaceColor(CubeColor.Green);

        assertEquals(CubeColor.Green, face.getField(0, 0));
        assertEquals(CubeColor.Green, face.getField(1, 0));
        assertEquals(CubeColor.Green, face.getField(0, 1));
        assertEquals(CubeColor.Green, face.getField(1, 1));
    }

    @Test
    void setFaceColor_hasColor5x5() {
        CubeFace face = new CubeFace(5);
        face.setFaceColor(CubeColor.Red);

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                assertEquals(CubeColor.Red, face.getField(row, column));
            }
        }
    }

    @Test
    void setField_hasColor() {
        CubeFace face = new CubeFace(6);
        face.setField(4, 3, CubeColor.Blue);

        assertEquals(CubeColor.Blue, face.getField(4, 3));
    }

    @Test
    void setField_differentColors() {
        CubeFace face = new CubeFace(3);
        face.setField(0, 0, CubeColor.White);
        face.setField(0, 1, CubeColor.Orange);
        face.setField(0, 2, CubeColor.Green);
        face.setField(1, 0, CubeColor.Red);
        face.setField(1, 1, CubeColor.Blue);
        face.setField(1, 2, CubeColor.Yellow);

        assertEquals(CubeColor.White, face.getField(0, 0));
        assertEquals(CubeColor.Orange, face.getField(0, 1));
        assertEquals(CubeColor.Green, face.getField(0, 2));
        assertEquals(CubeColor.Red, face.getField(1, 0));
        assertEquals(CubeColor.Blue, face.getField(1, 1));
        assertEquals(CubeColor.Yellow, face.getField(1, 2));
    }

    @Test
    void setField_changeColor() {
        CubeFace face = new CubeFace(6);
        face.setField(4, 3, CubeColor.Blue);
        face.setField(4, 3, CubeColor.Orange);

        assertEquals(CubeColor.Orange, face.getField(4, 3));
    }

    @Test
    void getField_getsColor() {
        CubeFace face = new CubeFace(6);
        face.setField(4, 3, CubeColor.White);

        assertEquals(CubeColor.White, face.getField(4, 3));
    }
}