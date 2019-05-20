package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the translation of CubeFaceRotationRecords into
 * a SpeedCube notation syntax string.
 *
 * This implementat‚ion does not use complete cube rotations.
 * Instead, the cube's orientation must always be the default
 * orientation with the white face up and the orange face left.
 */
public class SpeedCubeNotationWriter {
    private StringBuilder _builder;
    private CubeFaceRotationRecords _records;
    private int _recordIndex;

    private SpeedCubeNotationWriter(CubeFaceRotationRecords records) {
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
    public static String Write(CubeFaceRotationRecords records) {
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
        final char[] notationCommands = { 'U', 'L', 'F', 'R', 'B', 'D' };
        final char face = notationCommands[record.getFace().ordinal()];
        _builder.append(record.getCountOfLayers() == 2 ? Character.toLowerCase(face) : face);
        writeDirection(record.getDirection());
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
        if (_records.size() > _recordIndex + 1) {
            CubeFaceRotationRecord followingRecord = _records.get(_recordIndex + 1);
            if (!record.equals(followingRecord))
                return;
            _recordIndex++;
            _builder.append('2');
        }
    }

    /**
     * Writes a ' into the result if the rotation direction is counterclockwise.
     *
     * @param direction The RotationDirection that should be encoded into the result.
     */
    private void writeDirection (RotationDirection direction) {
        if (direction == RotationDirection.Counterclockwise)
            _builder.append('\'');
    }
}
