package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the translation of CubeFaceRotationRecords into
 * a SpeedCube notation syntax string.
 */
public class SpeedCubeNotationWriter {
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
        StringBuilder builder = new StringBuilder(1024);
        for (int i = 0; i < records.size(); i++) {
            writeRecord(records, i, builder);
        }
        return builder.toString();
    }

    /**
     * Writes the specified record as SpeedCube notation into the
     * specified StringBuilder.
     *
     * writeRecord optimizes if the current and the following record
     * can be aggregated to a shorter notation. This may be two identical
     * rotations, or two rotations of side faces that can be expressed
     * as a single rotation of the middle layer.
     *
     * @param records The CubeFaceRotationRecords object where the current
     *                move with the specified index is.
     * @param index The current record's index.
     * @param builder A StringBuilder where the record is written to.
     * @return The new index if more than the current record has been written.
     */
    private static int writeRecord(CubeFaceRotationRecords records, int index, StringBuilder builder) {
        // TODO implement!
        return index;
    }
}
