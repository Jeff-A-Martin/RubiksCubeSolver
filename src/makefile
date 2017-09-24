# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ #
# ~~~~~ Jeffrey Martin                                                                      ~~~~~ #
# ~~~~~ CS4341 Introduction to Artificial Intelligence                                      ~~~~~ #
# ~~~~~ Professor Neil Heffernan                                                            ~~~~~ #
# ~~~~~ October 24th, 2016                                                                  ~~~~~ #
# ~~~~~                                                                                     ~~~~~ #
# ~~~~~ BSMS_project : Goal Oriented Rubiks Cube Solver with IDA*                           ~~~~~ #
# ~~~~~ File: Makefile                                                                      ~~~~~ #
# ~~~~~~~ This file allows the user to easily build this project. It stores all class files ~~~~~ #
# ~~~~~~~ in the directory 'bin'. Simply Type 'make' within the src directory to build.     ~~~~~ #
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ #

#Build all components of the project
all: Main.class

Main.class: Main.java Rubiks_Cube_V2.class Goal_Oriented_IDAStar_Solver.class Goal.class
	javac -g -d ../bin/ Main.java

Rubiks_Cube_V2.class: Rubiks_Cube_V2.java Rubiks_Cube_Interface.class
	javac -g -d ../bin/ Rubiks_Cube_V2.java

Rubiks_Cube_Interface.class: Rubiks_Cube_Interface.java
	javac -g -d ../bin/ Rubiks_Cube_Interface.java

Rubiks_Cube_Goals_V2.class: Rubiks_Cube_Goals_V2.java
	javac -g -d ../bin/ Rubiks_Cube_Goals_V2.java

Goal_Oriented_IDAStar_Solver.class: Goal_Oriented_IDAStar_Solver.java Rubiks_Cube_Goals_V2.class Rubiks_Cube_Interface.class Goal.class
	javac -g -d ../bin/ Goal_Oriented_IDAStar_Solver.java

Goal.class: Goal.java
	javac -g -d ../bin/ Goal.java

run: Main.class
	java -cp ../bin Main