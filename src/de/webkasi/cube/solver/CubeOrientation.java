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
    CubeColor up = CubeColor.White;
    /**
     * Current index of the left face.
     */
    CubeColor left = CubeColor.Orange;
    /**
     * Current index of the front face.
     */
    CubeColor front = CubeColor.Green;
    /**
     * Current index of the right face.
     */
    CubeColor right = CubeColor.Red;
    /**
     * Current index of the back face.
     */
    CubeColor back = CubeColor.Blue;
    /**
     * Current index of the down face.
     */
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
     *                  or counterclockwise. The direction of the axis is always in
     *                  positive direction, i.e. the x axis starts at the orange face from
     *                  the left side and goes to the red face at the right side. Clockwise
     *                  means in this case that the blue face is rotated to the upper side.
     * @param count The count of 90 degree rotations. A value of 2 means that the cube is
     *              rotated by 180 degrees.
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
     *
     * To determine the direction of the rotation one looks at the left
     * face of the cube. Clockwise will make the previous front face the
     * new down face.
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
     *
     * To determine the direction of the rotation one looks at the bottom
     * of the cube. Clockwise will make the previous left face the new front.
     */
    private void y() {
        CubeColor oldRight = right;
        right = front;
        front = left;
        left = back;
        back = oldRight;
    }

    /**
     * Turns the orientation of the cube 90 degrees around the z-axis.
     *
     * To determine the direction of the rotation one looks at the front
     * of the cube. Clockwise means that the previous down face will become
     * the new left face.
     */
    private void z() {
        CubeColor oldLeft = left;
        left = down;
        down = right;
        right = up;
        up = oldLeft;
    }
}
