package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides adding move sequences to a CubeFaceRotationRecords object
 * that must be specified by using the SpeedCube string notation.
 *
 * This notation is valid only for 3x3 or 2x2 cubes.
 */
public class SpeedCubeNotationInterpreter {

    /**
     * Represents the orientation of the cube.
     *
     * The orientation determines which face is on which side when interpreting
     * the moves.
     */
    class Orientation {
        CubeColor up = CubeColor.White;
        CubeColor left = CubeColor.Orange;
        CubeColor front = CubeColor.Green;
        CubeColor right = CubeColor.Red;
        CubeColor back = CubeColor.Blue;
        CubeColor down = CubeColor.Yellow;

        void rotate(char axis, CubeFaceRotationDirection direction, int count)
        {
            if (direction == CubeFaceRotationDirection.Counterclockwise && count == 1)
                count = 3;
            for (int i = 0; i < count; i++)
                rotate(axis);
        }

        private void rotate(char axis)
        {
            switch (axis) {
                case 'x':
                    x();
                    break;
                case 'y':
                    y();
                    break;
                case 'z':
                    z();
                    break;
            }
        }

        /**
         * Turns the orientation of the cube around the x-axis.
         */
        private void x() {
            CubeColor oldLeft = left;
            left = front;
            front = right;
            right = back;
            back = oldLeft;
        }

        /**
         * Turns the orientation of the cube around the y-axis.
         */
        private void y() {
            CubeColor oldUp = up;
            up = back;
            back = down;
            down = front;
            front = oldUp;
        }

        /**
         * Turns the orientation of the cube around the z-axis.
         */
        private void z() {
            CubeColor oldLeft = left;
            left = down;
            down = right;
            right = up;
            up = oldLeft;
        }
    }

    private CubeFaceRotationRecords records;

    /**
     * Initializes a new instance of the SpeedCubeNotationInterpreter class.
     *
     * @param records The CubeFaceRotationRecords object that receives
     *                the move records of the addMoves() method.
     */
    public SpeedCubeNotationInterpreter(CubeFaceRotationRecords records) {
        this.records = records;
    }

    /**
     * Adds the specified sequence of cube moves to the rotation record.
     *
     * U (Up) is ínitially always the white face, L (Left) is orange. Each single move
     * must be closed by a blank or the end of the string. Unknown characters
     * will be ignored. Turning the cube is valid only for the current moves
     * sequence. Each further call of addMoves() uses initially the default
     * orientation.
     *
     * @param moves A String containing a sequence of moves in
     *              Speedcube.de notation. See https://speedcube.de/notation.php.
     */
    public void addMoves(String moves) {
        CubeFaceRotationRecord record = null;
        Orientation orientation = new Orientation();

        CubeFaceRotationDirection cubeRotationDirection = CubeFaceRotationDirection.Clockwise;
        int countOfCubeRotations = 1;
        char cubeRotationAxis = '\0';

        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            switch (c) {
                case 'U':
                    record = new CubeFaceRotationRecord(orientation.up);
                    break;
                case 'u':
                    record = new CubeFaceRotationRecord(orientation.up, 2);
                    break;
                case 'L':
                    record = new CubeFaceRotationRecord(orientation.left);
                    break;
                case 'l':
                    record = new CubeFaceRotationRecord(orientation.left, 2);
                    break;
                case 'F':
                    record = new CubeFaceRotationRecord(orientation.front);
                    break;
                case 'f':
                    record = new CubeFaceRotationRecord(orientation.front, 2);
                    break;
                case 'R':
                    record = new CubeFaceRotationRecord(orientation.right);
                    break;
                case 'r':
                    record = new CubeFaceRotationRecord(orientation.right, 2);
                    break;
                case 'B':
                    record = new CubeFaceRotationRecord(orientation.back);
                    break;
                case 'b':
                    record = new CubeFaceRotationRecord(orientation.back, 2);
                    break;
                case 'D':
                    record = new CubeFaceRotationRecord(orientation.down);
                    break;
                case 'd':
                    record = new CubeFaceRotationRecord(orientation.down, 2);
                    break;
                case '\'':
                    if (record != null)
                        record.setDirection(CubeFaceRotationDirection.Counterclockwise);
                    else
                        cubeRotationDirection = CubeFaceRotationDirection.Counterclockwise;
                    break;
                case '2':
                    if (record != null)
                        records.add(record);
                    else
                        countOfCubeRotations = 2;
                    break;
                case 'w':
                    if (record != null)
                        record.setCountOfLayers(2);
                    break;
                case 'x':
                case 'y':
                case 'z':
                    cubeRotationAxis = c;
                    break;
                case ' ':
                    if (record != null) {
                        records.add(record);
                        record = null;
                    }
                    else if (cubeRotationAxis != '\0'){
                        orientation.rotate(cubeRotationAxis, cubeRotationDirection, countOfCubeRotations);
                        cubeRotationAxis = '\0';
                    }
                    break;
            }
        }
        if (record != null) {
            records.add(record);
        }
    }
}
