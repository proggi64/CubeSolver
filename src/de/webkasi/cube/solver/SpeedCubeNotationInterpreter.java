package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides adding move sequences to a CubeFaceRotationRecords object
 * that must be specified by using the SpeedCube string notation.
 *
 * This notation is valid only for 3x3 or 2x2 cubes.
 */
public class SpeedCubeNotationInterpreter {

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
        CubeOrientation orientation = new CubeOrientation();

        RotationDirection cubeRotationDirection = RotationDirection.Clockwise;
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
                case 'M':
                    i = middle(moves, ++i, orientation);
                    record = null;
                    break;
                case 'E':
                    i = equator(moves, ++i, orientation);
                    record = null;
                    break;
                case 'S':
                    i = stand(moves, ++i, orientation);
                    record = null;
                    break;
                case '\'':
                    if (record != null)
                        record.setDirection(RotationDirection.Counterclockwise);
                    else
                        cubeRotationDirection = RotationDirection.Counterclockwise;
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

    /**
     * Moves the vertical middle layer around the x axis.
     *
     * The direction and count of moves are determined by additional characters
     * beginning at the specified index i.
     *
     * @param moves The current notation string that is interpreted.
     * @param i The index of the next character in moves.
     * @param orientation The current CubeOrientation that may be changed.
     * @return The index of the last character that has been interpreted by this method.
     */
    private int middle(String moves, int i, CubeOrientation orientation) {
        return rotateMiddleLayer(moves, i, orientation, 'x', orientation.left, orientation.right);
    }

    /**
     * Moves the horizontal middle layer around the y axis.
     *
     * The direction and count of moves are determined by additional characters
     * beginning at the specified index i.
     *
     * @param moves The current notation string that is interpreted.
     * @param i The index of the next character in moves.
     * @param orientation The current CubeOrientation that may be changed.
     * @return The index of the last character that has been interpreted by this method.
     */
    private int equator(String moves, int i, CubeOrientation orientation) {
        return rotateMiddleLayer(moves, i, orientation, 'y', orientation.up, orientation.down);
    }

    /**
     * Moves the frontal middle layer around the z axis.
     *
     * The direction and count of moves are determined by additional characters
     * beginning at the specified index i.
     *
     * @param moves The current notation string that is interpreted.
     * @param i The index of the next character in moves.
     * @param orientation The current CubeOrientation that may be changed.
     * @return The index of the last character that has been interpreted by this method.
     */
    private int stand(String moves, int i, CubeOrientation orientation) {
        return rotateMiddleLayer(moves, i, orientation, 'z', orientation.front, orientation.back);
    }

    /**
     * Translates the rotation of a specified middle layer of the cube into
     * side face and complete cube rotations.
     *
     * This is necessary because the CubeFaceRotationRecord class does not
     * support middle layer rotations directly.
     *
     * @param moves The current notation string that is interpreted.
     * @param i The index of the next character in moves.
     * @param orientation The current CubeOrientation that may be changed.
     * @param axis A character specifying the axis (x, y, or z)
     * @param faceA The color of the first face to rotate.
     * @param faceB The color of the secons face to rotate.
     * @return The index of the last character that has been interpreted by this method.
     */
    private int rotateMiddleLayer(
            String moves,
            int i,
            CubeOrientation orientation,
            char axis,
            CubeColor faceA,
            CubeColor faceB) {

        CubeFaceRotationRecord recordA = new CubeFaceRotationRecord(faceA);
        CubeFaceRotationRecord recordB = new CubeFaceRotationRecord(faceB);
        boolean ready = false;
        boolean reverse = false;
        int count = 1;

        while (!ready && moves.length() > i) {
            char c = moves.charAt(i++);
            switch (c) {
                case '\'':
                    reverse = true;
                    break;
                case '2':
                    count = 2;
                    break;
                case ' ':
                    ready = true;
                    break;
            }
        }

        recordA.setDirection(reverse ? RotationDirection.Clockwise : RotationDirection.Counterclockwise);
        recordB.setDirection(reverse ? RotationDirection.Counterclockwise : RotationDirection.Clockwise);

        for (int c = 0; c < count; c++) {
            records.add(recordA);
            records.add(recordB);
        }
        orientation.rotate(axis, reverse ? RotationDirection.Counterclockwise : RotationDirection.Clockwise, count);

        return i - 1;
    }
}
