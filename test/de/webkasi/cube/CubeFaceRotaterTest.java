package de.webkasi.cube;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeFaceRotaterTest {

    class CubeTestFieldExpectation {
        final CubeFace face;
        CubeColor[][] expectedColors;

        CubeTestFieldExpectation(CubeFace face) {
            this.face = face;
        }
    }

    @Test
    void rotateFace_ClockwiseWhiteOne() {
        Cube cube = new Cube();
        CubeFaceRotater rotater = new CubeFaceRotater(cube);

        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.White, 1);

        CubeFace[] faces = cube.getFaces();

        CubeTestFieldExpectation whiteFace = new CubeTestFieldExpectation(faces[CubeColor.White.ordinal()]);
        whiteFace.expectedColors = new CubeColor[][]{
                { CubeColor.White, CubeColor.White, CubeColor.White },
                { CubeColor.White, CubeColor.White, CubeColor.White },
                { CubeColor.White, CubeColor.White, CubeColor.White }
        };

        CubeTestFieldExpectation orangeFace = new CubeTestFieldExpectation(faces[CubeColor.Orange.ordinal()]);
        orangeFace.expectedColors = new CubeColor[][]{
                { CubeColor.Green, CubeColor.Green, CubeColor.Green },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange }
        };
        CubeTestFieldExpectation greenFace = new CubeTestFieldExpectation(faces[CubeColor.Green.ordinal()]);
        greenFace.expectedColors = new CubeColor[][]{
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Green, CubeColor.Green, CubeColor.Green },
                { CubeColor.Green, CubeColor.Green, CubeColor.Green }
        };
        CubeTestFieldExpectation redFace = new CubeTestFieldExpectation(faces[CubeColor.Red.ordinal()]);
        redFace.expectedColors = new CubeColor[][]{
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Blue },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red }
        };
        CubeTestFieldExpectation blueFace = new CubeTestFieldExpectation(faces[CubeColor.Blue.ordinal()]);
        blueFace.expectedColors = new CubeColor[][]{
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Blue },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Blue }
        };

        CubeTestFieldExpectation yellowFace = new CubeTestFieldExpectation(faces[CubeColor.Yellow.ordinal()]);
        yellowFace.expectedColors = new CubeColor[][]{
                { CubeColor.Yellow, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Yellow, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Yellow, CubeColor.Yellow, CubeColor.Yellow }
        };

        assertFace(whiteFace);
        assertFace(orangeFace);
        assertFace(greenFace);
        assertFace(redFace);
        assertFace(blueFace);
        assertFace(yellowFace);
    }

    @Test
    void rotateFace_CounterclockwiseWhiteOne() {
        Cube cube = new Cube();
        CubeFaceRotater rotater = new CubeFaceRotater(cube);

        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.White, 1);

        CubeFace[] faces = cube.getFaces();

        CubeTestFieldExpectation whiteFace = new CubeTestFieldExpectation(faces[CubeColor.White.ordinal()]);
        whiteFace.expectedColors = new CubeColor[][]{
                { CubeColor.White, CubeColor.White, CubeColor.White },
                { CubeColor.White, CubeColor.White, CubeColor.White },
                { CubeColor.White, CubeColor.White, CubeColor.White }
        };

        CubeTestFieldExpectation orangeFace = new CubeTestFieldExpectation(faces[CubeColor.Orange.ordinal()]);
        orangeFace.expectedColors = new CubeColor[][]{
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Blue },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange }
        };
        CubeTestFieldExpectation greenFace = new CubeTestFieldExpectation(faces[CubeColor.Green.ordinal()]);
        greenFace.expectedColors = new CubeColor[][]{
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Green, CubeColor.Green, CubeColor.Green },
                { CubeColor.Green, CubeColor.Green, CubeColor.Green }
        };
        CubeTestFieldExpectation redFace = new CubeTestFieldExpectation(faces[CubeColor.Red.ordinal()]);
        redFace.expectedColors = new CubeColor[][]{
                { CubeColor.Green, CubeColor.Green, CubeColor.Green },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red }
        };
        CubeTestFieldExpectation blueFace = new CubeTestFieldExpectation(faces[CubeColor.Blue.ordinal()]);
        blueFace.expectedColors = new CubeColor[][]{
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Blue },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Blue }
        };

        CubeTestFieldExpectation yellowFace = new CubeTestFieldExpectation(faces[CubeColor.Yellow.ordinal()]);
        yellowFace.expectedColors = new CubeColor[][]{
                { CubeColor.Yellow, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Yellow, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Yellow, CubeColor.Yellow, CubeColor.Yellow }
        };

        assertFace(whiteFace);
        assertFace(orangeFace);
        assertFace(greenFace);
        assertFace(redFace);
        assertFace(blueFace);
        assertFace(yellowFace);
    }

    @Test
    void rotateFace_ClockwiseOrangeOne() {
        Cube cube = new Cube();
        CubeFaceRotater rotater = new CubeFaceRotater(cube);

        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Orange, 1);

        CubeFace[] faces = cube.getFaces();

        CubeTestFieldExpectation whiteFace = new CubeTestFieldExpectation(faces[CubeColor.White.ordinal()]);
        whiteFace.expectedColors = new CubeColor[][]{
                { CubeColor.Blue, CubeColor.White, CubeColor.White },
                { CubeColor.Blue, CubeColor.White, CubeColor.White },
                { CubeColor.Blue, CubeColor.White, CubeColor.White }
        };

        CubeTestFieldExpectation orangeFace = new CubeTestFieldExpectation(faces[CubeColor.Orange.ordinal()]);
        orangeFace.expectedColors = new CubeColor[][]{
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange }
        };
        CubeTestFieldExpectation greenFace = new CubeTestFieldExpectation(faces[CubeColor.Green.ordinal()]);
        greenFace.expectedColors = new CubeColor[][]{
                { CubeColor.White, CubeColor.Green, CubeColor.Green },
                { CubeColor.White, CubeColor.Green, CubeColor.Green },
                { CubeColor.White, CubeColor.Green, CubeColor.Green }
        };
        CubeTestFieldExpectation redFace = new CubeTestFieldExpectation(faces[CubeColor.Red.ordinal()]);
        redFace.expectedColors = new CubeColor[][]{
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red }
        };
        CubeTestFieldExpectation blueFace = new CubeTestFieldExpectation(faces[CubeColor.Blue.ordinal()]);
        blueFace.expectedColors = new CubeColor[][]{
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Yellow },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Yellow },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.Yellow }
        };

        CubeTestFieldExpectation yellowFace = new CubeTestFieldExpectation(faces[CubeColor.Yellow.ordinal()]);
        yellowFace.expectedColors = new CubeColor[][]{
                { CubeColor.Green, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Green, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Green, CubeColor.Yellow, CubeColor.Yellow }
        };

        assertFace(whiteFace);
        assertFace(orangeFace);
        assertFace(greenFace);
        assertFace(redFace);
        assertFace(blueFace);
        assertFace(yellowFace);
    }

    @Test
    void rotateFace_CounterclockwiseOrangeOne() {
        Cube cube = new Cube();
        CubeFaceRotater rotater = new CubeFaceRotater(cube);

        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Orange, 1);

        CubeFace[] faces = cube.getFaces();

        CubeTestFieldExpectation whiteFace = new CubeTestFieldExpectation(faces[CubeColor.White.ordinal()]);
        whiteFace.expectedColors = new CubeColor[][]{
                { CubeColor.Green, CubeColor.White, CubeColor.White },
                { CubeColor.Green, CubeColor.White, CubeColor.White },
                { CubeColor.Green, CubeColor.White, CubeColor.White }
        };

        CubeTestFieldExpectation orangeFace = new CubeTestFieldExpectation(faces[CubeColor.Orange.ordinal()]);
        orangeFace.expectedColors = new CubeColor[][]{
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange },
                { CubeColor.Orange, CubeColor.Orange, CubeColor.Orange }
        };
        CubeTestFieldExpectation greenFace = new CubeTestFieldExpectation(faces[CubeColor.Green.ordinal()]);
        greenFace.expectedColors = new CubeColor[][]{
                { CubeColor.Yellow, CubeColor.Green, CubeColor.Green },
                { CubeColor.Yellow, CubeColor.Green, CubeColor.Green },
                { CubeColor.Yellow, CubeColor.Green, CubeColor.Green }
        };
        CubeTestFieldExpectation redFace = new CubeTestFieldExpectation(faces[CubeColor.Red.ordinal()]);
        redFace.expectedColors = new CubeColor[][]{
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red },
                { CubeColor.Red, CubeColor.Red, CubeColor.Red }
        };
        CubeTestFieldExpectation blueFace = new CubeTestFieldExpectation(faces[CubeColor.Blue.ordinal()]);
        blueFace.expectedColors = new CubeColor[][]{
                { CubeColor.Blue, CubeColor.Blue, CubeColor.White },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.White },
                { CubeColor.Blue, CubeColor.Blue, CubeColor.White }
        };

        CubeTestFieldExpectation yellowFace = new CubeTestFieldExpectation(faces[CubeColor.Yellow.ordinal()]);
        yellowFace.expectedColors = new CubeColor[][]{
                { CubeColor.Blue, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Blue, CubeColor.Yellow, CubeColor.Yellow },
                { CubeColor.Blue, CubeColor.Yellow, CubeColor.Yellow }
        };

        assertFace(whiteFace);
        assertFace(orangeFace);
        assertFace(greenFace);
        assertFace(redFace);
        assertFace(blueFace);
        assertFace(yellowFace);
    }

    @Test
    void rotateFace_Pattern() {
        Cube cube = new Cube();
        CubeFaceRotater rotater = new CubeFaceRotater(cube);
        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Orange, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Orange, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Red, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.Red, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Blue, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Blue, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Green, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Green, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.White, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Counterclockwise, CubeColor.White, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Yellow, 1);
        rotater.rotateFace(CubeFaceRotationDirection.Clockwise, CubeColor.Yellow, 1);

        CubeFace[] faces = cube.getFaces();

        CubeTestFieldExpectation whiteFace = new CubeTestFieldExpectation(faces[CubeColor.White.ordinal()]);
        whiteFace.expectedColors = new CubeColor[][]{
                { CubeColor.White, CubeColor.Yellow, CubeColor.White },
                { CubeColor.Yellow, CubeColor.White, CubeColor.Yellow },
                { CubeColor.White, CubeColor.Yellow, CubeColor.White }
        };

        CubeTestFieldExpectation orangeFace = new CubeTestFieldExpectation(faces[CubeColor.Orange.ordinal()]);
        orangeFace.expectedColors = new CubeColor[][]{
                { CubeColor.Orange, CubeColor.Red, CubeColor.Orange },
                { CubeColor.Red, CubeColor.Orange, CubeColor.Red },
                { CubeColor.Orange, CubeColor.Red, CubeColor.Orange }
        };
        CubeTestFieldExpectation greenFace = new CubeTestFieldExpectation(faces[CubeColor.Green.ordinal()]);
        greenFace.expectedColors = new CubeColor[][]{
                { CubeColor.Green, CubeColor.Blue, CubeColor.Green },
                { CubeColor.Blue, CubeColor.Green, CubeColor.Blue },
                { CubeColor.Green, CubeColor.Blue, CubeColor.Green }
        };
        CubeTestFieldExpectation redFace = new CubeTestFieldExpectation(faces[CubeColor.Red.ordinal()]);
        redFace.expectedColors = new CubeColor[][]{
                { CubeColor.Red, CubeColor.Orange, CubeColor.Red },
                { CubeColor.Orange, CubeColor.Red, CubeColor.Orange },
                { CubeColor.Red, CubeColor.Orange, CubeColor.Red }
        };
        CubeTestFieldExpectation blueFace = new CubeTestFieldExpectation(faces[CubeColor.Blue.ordinal()]);
        blueFace.expectedColors = new CubeColor[][]{
                { CubeColor.Blue, CubeColor.Green, CubeColor.Blue },
                { CubeColor.Green, CubeColor.Blue, CubeColor.Green },
                { CubeColor.Blue, CubeColor.Green, CubeColor.Blue }
        };

        CubeTestFieldExpectation yellowFace = new CubeTestFieldExpectation(faces[CubeColor.Yellow.ordinal()]);
        yellowFace.expectedColors = new CubeColor[][]{
                { CubeColor.Yellow, CubeColor.White, CubeColor.Yellow },
                { CubeColor.White, CubeColor.Yellow, CubeColor.White },
                { CubeColor.Yellow, CubeColor.White, CubeColor.Yellow }
        };

        assertFace(whiteFace);
        assertFace(orangeFace);
        assertFace(greenFace);
        assertFace(redFace);
        assertFace(blueFace);
        assertFace(yellowFace);
    }

    void assertFace(CubeTestFieldExpectation expected) {
        int dim = expected.expectedColors.length;
        for (int row = 0; row < dim; row++)
            for (int column = 0; column < dim; column++)
                assertEquals(expected.expectedColors[row][column], expected.face.getField(row, column));
    }
}