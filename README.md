# CubeSolver
Java code for solving a Magic Cube. Currently no user interface, just JUnit tests.

The code consists of two main parts:
* The cube simulation with the __Cube__ class and the __CubeFaceRotator__ class that implements the movements
* The solution steps with the different __Step__ classes that should be applied to the scrambled cube in a given order

Their is also a __CubeScrambler__ class that scrambles a simulated cube. This is currently used for unit tests. The __YellowCornersOrientationStepTest__ is the unit test that applies all solution steps and tests the complete solution.

The idea is to implement code for solving a magic cube for different purposes, for example to be used for a cube solving robot. The solution does not optimize anything, their may be more than a hundred steps to solve a cube. It uses the Layer-by-Layer algorithm that is the simplest one.

This is my first Java code since about 2004. I used this hobby project for getting used to Java again for possible future projects.
