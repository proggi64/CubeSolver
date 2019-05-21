package de.webkasi.cube.ui.text;

import org.junit.jupiter.api.Test;

import de.webkasi.cube.*;

import static org.junit.jupiter.api.Assertions.*;

class CubeTextDescriptorTest {

    @Test
    void describeCube_3x3DifferentColors() {
        CubeTextDescriptor descriptor = new CubeTextDescriptor();
        Cube cube = new Cube();
        descriptor.describeCube( cube, new String[] {
                "WYW YWY WYW", "ORO ROR ORO", "GBG BGB GBG", "ROR ORO ROR", "BGB GBG BGB", "YWY WYW YWY" });
        CubeAssertion.assertCubeFace(cube, CubeColor.White, "WYW YWY WYW");
        CubeAssertion.assertCubeFace(cube, CubeColor.Orange, "ORO ROR ORO");
        CubeAssertion.assertCubeFace(cube, CubeColor.Green, "GBG BGB GBG");
        CubeAssertion.assertCubeFace(cube, CubeColor.Red, "ROR ORO ROR");
        CubeAssertion.assertCubeFace(cube, CubeColor.Blue, "BGB GBG BGB");
        CubeAssertion.assertCubeFace(cube, CubeColor.Yellow, "YWY WYW YWY");
    }

    @Test
    void describeFace_3x3DifferentColors() {
        CubeTextDescriptor descriptor = new CubeTextDescriptor();
        CubeFace face = descriptor.describeFace("WYW GBG YRY");
        CubeAssertion.assertFace(face, "WYW GBG YRY");
    }
}