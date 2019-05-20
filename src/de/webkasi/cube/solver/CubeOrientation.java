package de.webkasi.cube.solver;

import de.webkasi.cube.*;

/**
 * Represents the orientation of the cube.
 *
 * The orientation determines which face is on which side when interpreting
 * or writing the moves to simplify the move notation. The CubeOrientation
 * class provides the rotate() method to change the orientation as needed.
 */
class CubeOrientation {
    CubeColor up = CubeColor.White;
    CubeColor left = CubeColor.Orange;
    CubeColor front = CubeColor.Green;
    CubeColor right = CubeColor.Red;
    CubeColor back = CubeColor.Blue;
    CubeColor down = CubeColor.Yellow;

    /**
     * Rotates the orientation of the cube as specified.
     *
     * The default orientation is that the white face is the top (Up)
     * and the orange face is left (Left).
     *
     * @param axis Axis character: 'x', 'y', or 'z'. Determines the rotation axis
     *             of the cube.
     * @param direction The RotationDirection that determines whether to rotate clockwise
     *                  or counterclockwise.
     * @param count The count of 90 degree rotations.
     */
    void rotate(char axis, RotationDirection direction, int count)
    {
        if (direction == RotationDirection.Counterclockwise && count == 1)
            count = 3;
        for (int i = 0; i < count; i++)
            rotate(axis);
    }

    /**
     * Rotates the cube 90 degrees around the specified axis.
     *
     * @param axis Axis character: 'x', 'y', or 'z'. Determines the rotation axis
     *             of the cube.
     */
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
     * Turns the orientation of the cube 90 degrees around the x-axis.
     */
    private void x() {
        CubeColor oldUp = up;
        up = back;
        back = down;
        down = front;
        front = oldUp;
    }

    /**
     * Turns the orientation of the cube 90 degrees around the y-axis.
     */
    private void y() {
        CubeColor oldLeft = left;
        left = front;
        front = right;
        right = back;
        back = oldLeft;
    }

    /**
     * Turns the orientation of the cube 90 degrees around the z-axis.
     */
    private void z() {
        CubeColor oldLeft = left;
        left = down;
        down = right;
        right = up;
        up = oldLeft;
    }
}
