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
    /**
     * Current index of the upper face.
     */
    int up = CubeColor.White.ordinal();
    /**
     * Current index of the left face.
     */
    int left = CubeColor.Orange.ordinal();
    /**
     * Current index of the front face.
     */
    int front = CubeColor.Green.ordinal();
    /**
     * Current index of the right face.
     */
    int right = CubeColor.Red.ordinal();
    /**
     * Current index of the back face.
     */
    int back = CubeColor.Blue.ordinal();
    /**
     * Current index of the down face.
     */
    int down = CubeColor.Yellow.ordinal();

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
        int oldUp = up;
        up = back;
        back = down;
        down = front;
        front = oldUp;
    }

    /**
     * Turns the orientation of the cube 90 degrees around the y-axis.
     */
    private void y() {
        int oldLeft = left;
        left = front;
        front = right;
        right = back;
        back = oldLeft;
    }

    /**
     * Turns the orientation of the cube 90 degrees around the z-axis.
     */
    private void z() {
        int oldLeft = left;
        left = down;
        down = right;
        right = up;
        up = oldLeft;
    }
}
