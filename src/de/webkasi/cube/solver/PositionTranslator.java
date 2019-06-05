package de.webkasi.cube.solver;

import de.webkasi.cube.CubeColor;

/**
 * Provides a method to translate position on a Cube to positions for
 * another orientation.
 */
class PositionTranslator {
    private final PartPosition _originalPosition;
    private final CubeOrientation _orientation;

    /**
     * Initializes a new instance of the PositionTranslator class with the
     * specified values.
     *
     * @param position The original PartPosition that shoild be translated.
     * @param orientation The CubeOrientation that is the base for the translation.
     */
    private PositionTranslator(final PartPosition position, final CubeOrientation orientation) {
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
    static PartPosition translate(final PartPosition position, final CubeOrientation orientation) {
        PartPosition translated = new PartPosition(position);
        PositionTranslator translator = new PositionTranslator(position, orientation);

        int translatedFace = translator.translateFace();
        translated.setFace(translatedFace);
        translated.setRow(translator.translateRow(translatedFace));
        translated.setColumn(translator.translateColumn(translatedFace));

        return translated;
    }

    private static final CubeColor white = CubeColor.White;
    private static final CubeColor orange = CubeColor.Orange;
    private static final CubeColor green = CubeColor.Green;
    private static final CubeColor red = CubeColor.Red;
    private static final CubeColor blue = CubeColor.Blue;
    private static final CubeColor yellow = CubeColor.Yellow;

    // up, front
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
    private final static CubeColor[][] faceOrientations = {
            { white,    green   },
            { white,    orange  },
            { white,    blue    },
            { white,    red     },

            { orange,   white   },
            { orange,   green   },
            { orange,   yellow  },
            { orange,   blue    },

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

    // white, orange, green, red, blue, yellow
    /**
     * Lookup table of the necessary rotations of the faces for each orientation.
     *
     * These rotations are necessary to translate the row/column coordinates
     * of the six faces to the new orientation.
     */
    private final static int[][] faceRotations = {
            { none,             none,             none,              none,             none,             none },
            { counterClockwise, none,             none,              none,             none,             clockwise },
            { turn,             none,             none,              none,             none,             turn },
            { clockwise,        none,             none,              none,             none,             counterClockwise },

            { clockwise,        turn,             clockwise,         none,             counterClockwise, clockwise },
            { clockwise,        clockwise,        clockwise,         clockwise,        counterClockwise, clockwise },
            { clockwise,        none,             clockwise,         turn,             counterClockwise, clockwise },
            { clockwise,        counterClockwise, clockwise,         counterClockwise, counterClockwise, clockwise },
    };
    // 0  1    2     3     4    5
    // W  O    G     R     B    Y
    // up left front right back down
    /**
     * Table of face index transformations for each orientation.
     *
     * Contains the transformed face indexes for each orientation.
     * Used by the translateFace() method to translate the actual front face
     * to the green reference face and all other faces in relation to this
     * reference face.
     */
    private final static int[][] faceTransformations = {
            { 0, 1, 2, 3, 4, 5 },       // default: front is green (2), up is white
            { 0, 2, 3, 4, 1, 5 },       // front is red (3)
            { 0, 3, 4, 1, 2, 5 },       // front is blue (4)
            { 0, 4, 1, 2, 3, 5 },       // front is orange (1)

            { 2, 0, 1, 5, 3, 4 },       // front is white (0), up is orange
            { 3, 0, 2, 5, 4, 1 },       // front is green (2), up is orange
            { 4, 0, 3, 5, 1, 2 },       // front is yellow (5), up is orange
            { 1, 0, 4, 5, 2, 3 },       // front is blue (4), up is orange
    };

    /**
     * Translates the column of the position based on the cube's orientation.
     *
     * @param translatedFace The translated face index used to find the rotation rule
     *                       for this face and the orientation.
     * @return The translated column of the position.
     */
    private int translateColumn(final int translatedFace) {
        switch (faceRotations[_orientationIndex][translatedFace]) {
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
     * @param translatedFace The translated face index used to find the rotation rule
     *                       for this face and the orientation.
     * @return The translated row of the position.
     */
    private int translateRow(final int translatedFace) {
        switch (faceRotations[_orientationIndex][translatedFace]) {
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
