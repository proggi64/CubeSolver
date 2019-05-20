package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Provides the translation of CubeFaceRotationRecords into
 * a SpeedCube notation syntax string.
 */
public class SpeedCubeNotationWriter {
    private CubeOrientation _orientation;
    StringBuilder _builder;
    CubeFaceRotationRecords _records;
    int _recordIndex;

    private SpeedCubeNotationWriter(CubeFaceRotationRecords records) {
        _orientation = new CubeOrientation();
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
        return writer.getResult();
    }

    /**
     * Writes the current record as SpeedCube notation into the
     * result.
     *
     * writeRecord optimizes if the current and the following record
     * can be aggregated to a shorter notation. This may be two identical
     * rotations, or two rotations of side faces that can be expressed
     * as a single rotation of the middle layer.
     */
    private void writeRecord() {
        rotateCubeIfNecessary();
        // TODO implement!
    }

    /**
     * Changes the current orientation of the cube to simplify the operations
     * for real humans.
     *
     * rotateCubeIfNecessary()
     */
    private void rotateCubeIfNecessary() {

    }

    private String getResult() {
        return _builder.toString();
    }
}
