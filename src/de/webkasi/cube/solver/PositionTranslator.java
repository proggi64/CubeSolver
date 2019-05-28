package de.webkasi.cube.solver;

import de.webkasi.cube.CubeColor;

/**
 * Provides a method to translate position on a Cube to positions for
 * another orientation.
 */
class PositionTranslator {
    PartPosition _originalPosition;
    CubeOrientation _orientation;

    /**
     * Initializes a new instance of the PositionTranslator class with the
     * specified values.
     *
     * @param position The original PartPosition that shoild be translated.
     * @param orientation The CubeOrientation that is the base for the translation.
     */
    private PositionTranslator(PartPosition position, CubeOrientation orientation) {
        _orientation = orientation;
        _originalPosition = position;
        findOrientationIndex();
    }

    /**
     * Transforms the specified position that is related to the default
     * orientation to a corresponding position taht is related to the
     * specified orientation.
     *
     * This is necessary to find a solution by a specific position as a
     * key. This should work for any orientation, but the search arrays
     * are defined only for the default orientation. To make it work for any
     * orientation each position has to be tranformed first.
     *
     * @param position The original PartPosition object where the orientation is
     *                 the devfault orientation of the Cube (White up, Orange left).
     * @param orientation The CubeOrientation that is the base for the translation.
     * @return A new PartPosition object that is translated into the given
     * CubeOrientation. The coordinates will be rotated with the given orientation.
     */
    static PartPosition translate(PartPosition position, CubeOrientation orientation) {
        PartPosition translated = new PartPosition(position);
        PositionTranslator translator = new PositionTranslator(position, orientation);

        translated.setFace(translator.translateFace());
        translated.setRow(translator.translateRow());
        translated.setColumn(translator.translateColumn());

        return translated;
    }

    static final int white = CubeColor.White.ordinal();
    static final int orange = CubeColor.Orange.ordinal();
    static final int green = CubeColor.Green.ordinal();
    static final int red = CubeColor.Red.ordinal();
    static final int blue = CubeColor.Blue.ordinal();
    static final int yellow = CubeColor.Yellow.ordinal();

    /**
     * Array of all possible up/front orientations.
     *
     * The array is used to find an orientation and its corresponding
     * array index (first dimension). This index is used for
     * the lookup arrays where the transformation data for each
     * orientation is stored.
     *
     * The two face indexes per orientation are encoded in the colors
     * for better readability of the code. The default
     * up/front orientation is white/green. The actual values are int
     * values.
     */
    private final static int[][] faceOrientations = {
            { white,    green   },
            { white,    red     },
            { white,    blue    },
            { white,    orange  },
            { orange,   white   },
            { orange,   blue    },
            { orange,   yellow  },
            { orange,   green   },
            { green,    white   },
            { green,    orange  },
            { green,    yellow  },
            { green,    red     },
            { red,      white   },
            { red,      green   },
            { red,      yellow  },
            { red,      blue    },
            { blue,     white   },
            { blue,     red     },
            { blue,     yellow  },
            { blue,     orange  },
            { yellow,   orange  },
            { yellow,   blue    },
            { yellow,   red     },
            { yellow,   green   }
    };

    /**
     * The index for the lookup tables of the transformation data.
     *
     * This index is the matching index of the faceOrientations array where
     * all possible orientations are stored. The orientation of the current
     * class instance determines this value.
     */
    private int _orientationIndex;

    private final static int none = 0;
    private final static int clockwise = 1;
    private final static int counterClockwise = 2;
    private final static int turn = 3;

    // TODO Legt 3x3 Cube fest!
    private final static int maxDimensionIndex = 3 - 1;

    /**
     * Lookup table of the necessary rotations of the faces for each orientation.
     */
    private final static int[][] faceRotations = {
            { none,             none,           none,           none,           none,           none             },
            { clockwise,        none,           none,           none,           none,           counterClockwise },
            { turn,             none,           none,           none,           none,           turn             },
            { counterClockwise, none,           none,           none,           none,           clockwise        },
    };
    private final static int[][] faceTransformations = {
            { 0, 1, 2, 3, 4, 5 },
            { 0, 4, 1, 2, 3, 5 },
            { 0, 3, 4, 1, 2, 5 },
            { 0, 2, 3, 4, 1, 5 },
    };

    /**
     * Translates the column of the position based on the cube's orientation.
     *
     * @return The translated column of the position.
     */
    private int translateColumn() {
        switch (faceRotations[_orientationIndex][_originalPosition.getFace()]) {
            case none:
                return _originalPosition.getColumn();
            case clockwise:
                return maxDimensionIndex - _originalPosition.getRow();
            case counterClockwise:
                return _originalPosition.getRow();
            case turn:
                return maxDimensionIndex - _originalPosition.getColumn();
        }
        return 0;
    }

    /**
     * Translates the row of the position based on the cube's orientation.
     *
     * @return The translated row of the position.
     */
    private int translateRow() {
        switch (faceRotations[_orientationIndex][_originalPosition.getFace()]) {
            case none:
                return _originalPosition.getRow();
            case clockwise:
                return _originalPosition.getColumn();
            case counterClockwise:
                return maxDimensionIndex - _originalPosition.getColumn();
            case turn:
                return maxDimensionIndex - _originalPosition.getRow();
        }
        return 0;
    }

    private final static int up = 0;
    private final static int front = 1;

    /**
     * Translates the face index of the position  based on the orientation.
     *
     * @return The translated face index.
     */
    private int translateFace() {
        return faceTransformations[_orientationIndex][_originalPosition.getFace()];
    }

    /**
     * Finds and stores the index of the transformation arrays for the orientation.
     */
    private void findOrientationIndex() {
        boolean found = false;
        int i = 0;
        while (!found) {
            found = _orientation.up == faceOrientations[i][up] &&
                    _orientation.front == faceOrientations[i][front];
            i++;
        }
        _orientationIndex = i - 1;
    }
}
