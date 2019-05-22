package de.webkasi.cube;

/**
 * Provides playing lists of rotation records stored in a CubeFaceRotationRecords
 * object.
 */
public class CubeFaceRotationPlayer {
    private final CubeFaceRotator _rotator;

    /**
     * Initializes a new instance of the CubeFaceRotationPlayer class.
     *
     * @param rotator The CubeFaceRotator used to play the records.
     */
    public CubeFaceRotationPlayer(CubeFaceRotator rotator) {
        _rotator = rotator;
    }

    /**
     * Executes the specified rotation records on the attached Cube.
     *
     * @param records A CubeFaceRotationRecords object containing the
     *                rotations to replay.
     */
    public void play(CubeFaceRotationRecords records) {
        records.forEach(
                (v) -> _rotator.rotateFace(v.getDirection(), v.getFace(), v.getCountOfLayers())
        );
    }
}
