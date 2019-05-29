package de.webkasi.cube.solver;

import org.junit.jupiter.api.Test;

import de.webkasi.cube.*;

import static org.junit.jupiter.api.Assertions.*;

class PositionTranslatorTest {

    private final int maxDimension = 3;

    @Test
    void translate_FrontGreenAll() {
        CubeOrientation orientation = new CubeOrientation();
        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < maxDimension; row++) {
                for (int column = 0; column < maxDimension; column++) {
                    PartPosition position = new PartPosition(face, row, column);
                    PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
                    assertTrue(position.isEqual(translatedPosition));
                }
            }
        }
    }

    @Test
    void translate_FrontRed_BackTopRight() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition position = new PartPosition(CubeColor.Blue.ordinal(), 0, 0);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Orange.ordinal(), 0, 0);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontRed_LeftDownEdge() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition position = new PartPosition(CubeColor.Orange.ordinal(), 2, 1);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Green.ordinal(), 2, 1);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontRed_TopRightMid() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition position = new PartPosition(CubeColor.Red.ordinal(), 1, 2);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Blue.ordinal(), 1, 2);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontRed_UpMidRight() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 1, 2);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 2, 1);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontRed_DownMidLeft() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition position = new PartPosition(CubeColor.Yellow.ordinal(), 1, 0);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Yellow.ordinal(), 2, 1);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontRed_UpMidBack() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 0, 1);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 1, 2);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontBlue_BackTopRight() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 2);
        PartPosition position = new PartPosition(CubeColor.Orange.ordinal(), 0, 0);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Red.ordinal(), 0, 0);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontBlue_UpMidRight() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 2);
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 1, 2);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 1, 0);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontBlue_DownMidLeft() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Clockwise, 2);
        PartPosition position = new PartPosition(CubeColor.Yellow.ordinal(), 1, 0);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Yellow.ordinal(), 1, 2);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontOrange_BackTopRight() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition position = new PartPosition(CubeColor.Orange.ordinal(), 0, 0);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Blue.ordinal(), 0, 0);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontOrange_RightMidBack() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition position = new PartPosition(CubeColor.Red.ordinal(), 1, 2);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Green.ordinal(), 1, 2);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontOrange_UpMidRight() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 1, 2);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 0, 1);
        assertTrue(expected.isEqual(translatedPosition));
    }

    @Test
    void translate_FrontOrange_DownMidLeft() {
        CubeOrientation orientation = new CubeOrientation();
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition position = new PartPosition(CubeColor.Yellow.ordinal(), 1, 0);
        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        PartPosition expected = new PartPosition(CubeColor.Yellow.ordinal(), 0, 1);
        assertTrue(expected.isEqual(translatedPosition));
    }
}