/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
 * ~~~~~ Jeffrey Martin                                                                    ~~~~~ *
 * ~~~~~ CS4341 Introduction to Artifical Intelligence                                     ~~~~~ *
 * ~~~~~ Professor Neil Heffernan                                                          ~~~~~ *
 * ~~~~~ October 24th, 2016                                                                ~~~~~ *
 * ~~~~~                                                                                   ~~~~~ *
 * ~~~~~ BSMS Project : Goal Oriented Rubiks Cube Solver with IDA*                         ~~~~~ *
 * ~~~~~ File: Rubiks_Cube_Interface.java                                                  ~~~~~ *
 * ~~~~~ File Description                                                                  ~~~~~ *
 * ~~~~~~~ This interface provides a template for a 3x3 Rubik's Cube implementation. It    ~~~~~ *
 * ~~~~~~~ outlines the behavior that a Rubik's Cube must support.                         ~~~~~ *                                                                              ~~~~~ *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public interface Rubiks_Cube_Interface {
	/* ~~~~~ Method Signatures                                                             ~~~~~ */

	/* Front 90 degrees, Front 180 degrees, and Front 270 degrees (respectively)           ~~~~~ *
	 * It is assumed that the front face will be implemented as the green face.            ~~~~~ */
	void Front1();
	void Front2();
	void Front3();

	/* Back 90 degrees, Back 180 degrees, and Back 270 degrees (respectively)              ~~~~~ *
	 * It is assumed that the back face will be implemented as the blue face.              ~~~~~ */
	void Back1();
	void Back2();
	void Back3();

	/* Left 90 degrees, Left 180 degrees, and Left 270 degrees (respectively)              ~~~~~ *
	 * It is assumed that the left face will be implemented as the orange face.            ~~~~~ */
	void Left1();
	void Left2();
	void Left3();

	/* Right 90 degrees, Right 180 degrees, and Right 270 degrees (respectively)           ~~~~~ *
	 * It is assumed that the right face will be impllemented as the red face.             ~~~~~ */
	void Right1();
	void Right2();
	void Right3();

	/* Top 90 degrees, Top 180 degrees, and Top 270 degrees (respectively)                 ~~~~~ *
	 * It is assumed that the top face will be implimented as the white face.              ~~~~~ */
	void Top1();
	void Top2();
	void Top3();

	/* Bottom 90 degrees, Bottom 180 degrees, and Bottom 270 degrees (respectively)        ~~~~~ *
	 * It is assumed that the bottom face will be implemented as the yellow face.          ~~~~~ */
	void Bottom1();
	void Bottom2();
	void Bottom3();

	/* Display the Cube to standard out                                                    ~~~~~ */
	void printCube();

	/* Return the color of a specific facelet. The facelets are ordered from 0-53 as shown ~~~~~ *
	 * below. This function accepts an integer representing the index of the faclet        ~~~~~ */
	char getFacletColor(int faclet_index);

	/* This function scrambles the rubiks cube                                             ~~~~~ */
	void scramble();
}