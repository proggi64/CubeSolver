package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the translation of CubeFaceRotationRecords into
 * a SpeedCube notation string.
 *
 * This implementation does not use complete cube rotations.
 * Instead, the cube's orientation must always be the default
 * orientation with the initial white face up and the orange face left.
 */
class SpeedCubeNotationWriter {
    private final StringBuilder _builder;
    private final CubeFaceRotationRecords _records;
    private int _recordIndex;
    private final static char[] notationFaceCommands = { 'U', 'L', 'F', 'R', 'B', 'D' };
    private final static char[] notationMiddleCommands = { ' ', 'M', ' ', ' ', 'S', 'E' };

    /**
     * Initializes a new instance of the SpeedCubeNotationWriter class
     * with the specified rotation records.
     *
     * @param records The SpeedCubeNotationRecords collection of moves that
     *                is translated into a SpeedCube notation string.
     */
    private SpeedCubeNotationWriter(final CubeFaceRotationRecords records) {
        _builder = new StringBuilder(1024);
        _records = records;
        _recordIndex = 0;
    }

    /**
     * Writes the specified cube rotation records into a String
     * in SpeedCube notation syntax.
     *
     * @param records The CubeFaceRotationRecords object containing
     *                the moves to be written.
     * @return A String containing the records in SpeedCube notation
     * syntax.
     */
    static String write(final CubeFaceRotationRecords records) {
        SpeedCubeNotationWriter writer = new SpeedCubeNotationWriter(records);

        for (writer._recordIndex = 0; writer._recordIndex < writer._records.size(); writer._recordIndex++) {
            writer.writeRecord();
        }
        return writer._builder.toString();
    }

    /**
     * Writes the current record as SpeedCube notation into the
     * result.
     *
     * writeRecord optimizes if the current and the following records
     * can be aggregated to a shorter notation. This may be two identical
     * rotations, or two rotations of side faces that can be expressed
     * as a single rotation of the middle layer.
     */
    private void writeRecord() {
        CubeFaceRotationRecord record = _records.get(_recordIndex);
        writeSingleRotation(record);
        writeTwoIfDoubleRotation(record);
        _builder.append(' ');
    }

    /**
     * Writes a single record into the string result.
     *
     * @param record The CubeFaceRotationRecord to write into the result.
     */
    private void writeSingleRotation(final CubeFaceRotationRecord record) {
        final char face = getCommand(record);
        _builder.append(record.getCountOfLayers() == 2 ? Character.toLowerCase(face) : face);
        writeDirection(record.getDirection());
     }

    /**
     * Gets the command character for the record.
     *
     * @param record The CubeFaceRotationRecord to write into the result.
     * @return The command character that corresponds to the record.
     */
    private char getCommand(final CubeFaceRotationRecord record) {
        int faceIndex = record.getFace().ordinal();
        if (record.getStartRow() == 0)
            return notationFaceCommands[faceIndex];
        return notationMiddleCommands[faceIndex];
    }

    /**
     * Writes the character '2' into the string result if the
     * current record folows again.
     *
     * The index of records is incremented if the next record conatins the same
     * data agin.
     *
     * @param record The record to test.
     *
     */
    private void writeTwoIfDoubleRotation(final CubeFaceRotationRecord record)  {
        if (_records.size() <= _recordIndex + 1)
            return;

        CubeFaceRotationRecord followingRecord = _records.get(_recordIndex + 1);
        if (!record.equals(followingRecord))
            return;
        _recordIndex++;
        _builder.append('2');
    }

    /**
     * Writes a ' into the result if the rotation direction is counterclockwise.
     *
     * @param direction The RotationDirection that should be encoded into the result.
     */
    private void writeDirection (final RotationDirection direction) {
        if (direction == RotationDirection.Counterclockwise)
            _builder.append('\'');
    }
}
