/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
 * ~~~~~ Jeffrey Martin                                                                    ~~~~~ *
 * ~~~~~ CS4341 Introduction to Artifical Intelligence                                     ~~~~~ *
 * ~~~~~ Professor Neil Heffernan                                                          ~~~~~ *
 * ~~~~~ October 24th, 2016                                                                ~~~~~ *
 * ~~~~~                                                                                   ~~~~~ *
 * ~~~~~ BSMS Project : Goal Oriented Rubiks Cube Solver with IDA*                         ~~~~~ *
 * ~~~~~ File: Main.java                                                                   ~~~~~ *
 * ~~~~~ File Description                                                                  ~~~~~ *
 * ~~~~~~~ This program solves a 3x3 rubiks cube using subgoal decomposition and IDA*      ~~~~~ *
 * ~~~~~~~ search. The user can enter in a specific cube state, or the program can solve   ~~~~~ *
 * ~~~~~~~ a randomly scrambled cube. There are for different sub goal decompositions:     ~~~~~ *
 * ~~~~~~~ A beginners method (how most cubers first learn to solve it), a parallel piece  ~~~~~ *
 * ~~~~~~~ piece placement decomposition, a random decomposition, and lastly and improved  ~~~~~ *
 * ~~~~~~~ 'beginners method' decomposition, which performs the best.                      ~~~~~ *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.ArrayList;
import java.util.Scanner;
public class Main{
	public static void main(String args[]){
		/* This variable allows the user to specify which mode to operate the program in.  ~~~~~ *
		 * true:  User input Cube.                                                         ~~~~~ *
		 * false: Randomly Scrambled Cube.                                                 ~~~~~ */
		boolean mode_select = false;

		/* This variable allows the user to specify which goal decomposition to use.       ~~~~~ *
		 * 0: Improved Decomposition                                                       ~~~~~
		 * 1: Random Decomposition                                                         ~~~~~ *
		 * 2: Parrallel Piece Placing Decomposition                                        ~~~~~
		 * 3: Beginners Method Decomposition                                               ~~~~~ */
		int decomp = 0;

		boolean verbose = false;
		boolean admissable = false;
		/* Parse command line arguments to change the mode and decomposition               ~~~~~ */
		for (int i = 0; i < args.length; i++){
			String argument = args[i];
			if(argument.equals("-u"))
				mode_select = true; //set mode to user specified cube.
			else if(argument.equals("-r"))
				decomp = 1;
			else if(argument.equals("-p"))
				decomp = 2;
			else if(argument.equals("-b"))
				decomp = 3;
			else if(argument.equals("-v"))
				verbose = true;
			else if(argument.equals("-a"))
				admissable = true;
		}

		Goal_Oriented_IDAStar_Solver.setConstants(verbose, admissable);

		System.out.println("\nWelcome to Jeff's Rubik's Cube Solver.");
		System.out.println("Using a " + (mode_select? "user specified" : "randomly scrambled") + 
			" Rubik's Cube.");

		/* This vairable represents the goal Decomposition to use for the IDA* search.     ~~~~~ */
		Goal[] decomposition = {};

		/* Generate Decomposition based on user selection                                  ~~~~~ */
		switch(decomp){
			case 0:
				decomposition = Goal_Oriented_IDAStar_Solver.IMPROVED_DECOMPOSITION;
				System.out.println("The solver will use the Improved Goal Decomposition.");
				break;
			case 1:
				decomposition = Goal_Oriented_IDAStar_Solver.getRandomDecomposition();
				System.out.println("The solver will use a Randomly Generated Goal Decomposition.");
				break;
			case 2:
				decomposition = Goal_Oriented_IDAStar_Solver.PPP_DECOMPOSITION;
				System.out.println("The solver will use the Parrallel Piece Placement Goal Decomposition");
				break;
			case 3:
				decomposition = Goal_Oriented_IDAStar_Solver.BEGINNERS_METHOD_DECOMPOSITION;
				System.out.println("The solver will use the Beginners Method Goal Decomposition");
				break;
		}
		if(admissable)System.out.println("The solver will use an admissable heuristic.");
		else System.out.println("The solver will not use an admissable heuristic.");


		System.out.println(""); //privide a line buffer

		System.out.println("Notation:");
		System.out.println("There are 6 faces, each of which can be turned 3 different ways,");
		System.out.println("thus, there are 18 different moves. Moves are specified by the ");
		System.out.println("face followed by a number. 1 means a clockwise rotation. 2 means ");
		System.out.println("a 180 degree rotation. 3 means a counter clockwise rotation.");
		System.out.println("For consistency throughout the solve, the cube should always be ");
		System.out.println("Held with the white face on top with the green face on the front.");

		System.out.println("");

		/* Build Rubiks Cube                                                               ~~~~~ */
		Rubiks_Cube_V2 cube = null;
		if(mode_select){ // Get the user to input a cube. 
			Scanner scanner = new Scanner(System.in);

			System.out.println("Please enter the piece colors in the order shown below. (with spaces between faces):");
			System.out.println(" *                                                                                               *");
			System.out.println(" *          Solved Cube                              Order Mapping of Pieces                     *");
 			System.out.println(" *                                                                                               *");
			System.out.println(" *           [W][W][W]                     |              [00][01][02]                           *");
 			System.out.println(" *           [W][W][W]                     |              [03][04][05]                           *");
 			System.out.println(" *           [W][W][W]                     |              [06][07][08]                           *");
 			System.out.println(" *                                         |                                                     *");
 			System.out.println(" * [O][O][O] [G][G][G] [R][R][R] [B][B][B] | [09][10][11] [18][19][20] [27][28][29] [36][37][38] *");
 			System.out.println(" * [O][O][O] [G][G][G] [R][R][R] [B][B][B] | [12][13][14] [21][22][23] [30][31][32] [39][40][41] *");
 			System.out.println(" * [O][O][O] [G][G][G] [R][R][R] [B][B][B] | [15][16][17] [24][25][26] [33][34][35] [42][43][44] *");
			System.out.println(" *                                         |                                                     *");
 			System.out.println(" *           [Y][Y][Y]                     |              [45][46][47]                           *");
 			System.out.println(" *           [Y][Y][Y]                     |              [48][49][50]                           *");
 			System.out.println(" *           [Y][Y][Y]                     |              [51][52][53]                           *");
 			System.out.println(" *                                                                                               *");
			System.out.println("EX: solved_cube = \"WWWWWWWWW OOOOOOOOO GGGGGGGGG RRRRRRRRR BBBBBBBBB YYYYYYYYY\"" );
			System.out.println("COLORS MUST BE CAPITAL LETTERS!");

			boolean user_done = false;
			while(!user_done){

				System.out.println("");
				System.out.println("  face1     face2     face3     face4     face5     face6");
				System.out.println("_________ _________ _________ _________ _________ _________");
				String cube_str = scanner.nextLine();
				cube_str = cube_str.replace(" ", "");
				if(cube_str.length() != 54){
					System.out.println("Invalid cube format. Try again.");
					continue;
				}
				cube = new Rubiks_Cube_V2(cube_str.toCharArray());
				System.out.println("The cube you have entered is:");
				cube.printCube();
				System.out.println("Is this correct? (Y/N)");
				String response = scanner.nextLine();
				if(response.equals("y") || response.equals("Y") || response.equals("Yes") || response.equals("yes"))
					user_done = true;
				else 
					System.out.println("Please try again,");

			}
		}else{ //use a randomly scrambled cube. 
			cube = new Rubiks_Cube_V2(); //initialize cube.
			System.out.println("The following turns are applied to a solved cube.\n");
			cube.scramble();
			System.out.println("\nThe cube is now in the following state:");
			cube.printCube();
		}


		System.out.println("\nBeginning Solve!\n");


		Goal_Oriented_IDAStar_Solver.solveWithDecomposition(cube, decomposition);
	}


}