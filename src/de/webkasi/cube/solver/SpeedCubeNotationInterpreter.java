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
     * @param records The CubeFaceRotationRecords object that receives
     *                the move records of the addMoves() method.
     */
    public SpeedCubeNotationInterpreter(CubeFaceRotationRecords records) {
        this.records = records;
    }

    /**
     * Adds the specified sequence of cube moves to the rotation record.
     *
     * U (Up) is always the white face, L (Left) is orange. Each single move
     * must be closed by a blank or the end of the string. Unknown characters
     * will be ignored.
     *
     * @param moves A String containing a sequence of moves in
     *              Speedcube.de notation. See https://speedcube.de/notation.php.
     */
    public void addMoves(String moves) {
        CubeFaceRotationRecord record = null;
        for (int i = 0; i < moves.length(); i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    record = new CubeFaceRotationRecord(CubeColor.White);
                    break;
                case 'u':
                    record = new CubeFaceRotationRecord(CubeColor.White, 2);
                    break;
                case 'L':
                    record = new CubeFaceRotationRecord(CubeColor.Orange);
                    break;
                case 'l':
                    record = new CubeFaceRotationRecord(CubeColor.Orange, 2);
                    break;
                case 'F':
                    record = new CubeFaceRotationRecord(CubeColor.Green);
                    break;
                case 'f':
                    record = new CubeFaceRotationRecord(CubeColor.Green, 2);
                    break;
                case 'R':
                    record = new CubeFaceRotationRecord(CubeColor.Red);
                    break;
                case 'r':
                    record = new CubeFaceRotationRecord(CubeColor.Red, 2);
                    break;
                case 'B':
                    record = new CubeFaceRotationRecord(CubeColor.Blue);
                    break;
                case 'b':
                    record = new CubeFaceRotationRecord(CubeColor.Blue, 2);
                    break;
                case 'D':
                    record = new CubeFaceRotationRecord(CubeColor.Yellow);
                    break;
                case 'd':
                    record = new CubeFaceRotationRecord(CubeColor.Yellow, 2);
                    break;
                case '\'':
                    if (record != null)
                        record.setDirection(CubeFaceRotationDirection.Counterclockwise);
                    break;
                case '2':
                    records.add(record);
                    break;
                case 'w':
                    if (record != null)
                        record.setCountOfLayers(2);
                    break;
                case ' ':
                    if (record != null) {
                        records.add(record);
                        record = null;
                    }
                    break;
            }
        }
        if (record != null) {
            records.add(record);
        }
    }
}
