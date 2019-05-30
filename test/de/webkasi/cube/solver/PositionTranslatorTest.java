package de.webkasi.cube.solver;

import org.junit.jupiter.api.Test;

import de.webkasi.cube.*;

import static org.junit.jupiter.api.Assertions.*;

class PositionTranslatorTest {

    private final static int maxDimension = 3;

    @Test
    void translate_FrontGreenAll() {
        CubeOrientation orientation = new CubeOrientation();
        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < maxDimension; row++) {
                for (int column = 0; column < maxDimension; column++) {
                    PartPosition position = new PartPosition(face, row, column);
                    PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
                    assertPosition(position, translatedPosition);
                }
            }
        }
    }

    @Test
    void translate_FrontRedUpWhite_BackTopRight() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Orange.ordinal(), 0, 0);
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Blue.ordinal(), 0, 0);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontRedUpWhite_LeftDownEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Green.ordinal(), 0, 0);
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Orange.ordinal(), 0, 0);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontRedUpWhite_TopRightEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Blue.ordinal(), 1, 2);
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Red.ordinal(), 1, 2);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontRedUpWhite_UpRightEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 1, 2);
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 2, 1);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontRedUpWhite_DownLeftEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Yellow.ordinal(), 1, 0);
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Yellow.ordinal(), 2, 1);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontRedUpWhite_UpBackEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 0, 1);
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 1, 2);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontBlueUpWhite_BackTopRight() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Orange.ordinal(), 0, 0);
        orientation.rotate('y', RotationDirection.Counterclockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Blue.ordinal(), 0, 0);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontBlueUpWhite_UpRightEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 1, 2);
        orientation.rotate('y', RotationDirection.Counterclockwise, 2);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 1, 0);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontBlueUpWhite_DownMidLeft() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Yellow.ordinal(), 1, 0);
        orientation.rotate('y', RotationDirection.Counterclockwise, 2);
        PartPosition expected = new PartPosition(CubeColor.Yellow.ordinal(), 1, 2);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontOrangeUpWhite_BackTopRight() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Red.ordinal(), 0, 0);
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Blue.ordinal(), 0, 0);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontOrangeUpWhite_RightBackEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Red.ordinal(), 1, 0);
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Blue.ordinal(), 1, 0);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontOrangeUpWhite_RightUpEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.White.ordinal(), 1, 2);
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.White.ordinal(), 0, 1);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontOrangeUpWhite_DownLeftEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Yellow.ordinal(), 1, 0);
        orientation.rotate('y', RotationDirection.Clockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Yellow.ordinal(), 0, 1);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    @Test
    void translate_FrontWhiteUpOrange_DownLeftEdge() {
        CubeOrientation orientation = new CubeOrientation();
        PartPosition position = new PartPosition(CubeColor.Red.ordinal(), 1, 0);
        orientation.rotate('x', RotationDirection.Clockwise, 1);
        orientation.rotate('z', RotationDirection.Clockwise, 1);
        PartPosition expected = new PartPosition(CubeColor.Yellow.ordinal(), 0, 1);

        PartPosition translatedPosition = PositionTranslator.translate(position, orientation);
        assertPosition(expected, translatedPosition);
    }

    void assertPosition(PartPosition expected, PartPosition position) {
        assertEquals(expected.getFace(), position.getFace(), "Face");
        assertEquals(expected.getRow(), position.getRow(), "Row");
        assertEquals(expected.getColumn(), position.getColumn(), "Column");
    }
}