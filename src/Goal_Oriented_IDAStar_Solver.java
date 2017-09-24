/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
 * ~~~~~ Jeffrey Martin                                                                    ~~~~~ *
 * ~~~~~ CS4341 Introduction to Artifical Intelligence                                     ~~~~~ *
 * ~~~~~ Professor Neil Heffernan                                                          ~~~~~ *
 * ~~~~~ October 24th, 2016                                                                ~~~~~ *
 * ~~~~~                                                                                   ~~~~~ *
 * ~~~~~ BSMS Project : Goal Oriented Rubiks Cube Solver with IDA*                         ~~~~~ *
 * ~~~~~ File: Goal_Oriented_IDAStar_Solver.java                                           ~~~~~ *
 * ~~~~~ File Description                                                                  ~~~~~ *
 * ~~~~~~~ This class attempts to solve the a 3x3 rubiks cube using goal oriented IDA*     ~~~~~ *
 * ~~~~~~~ Search. The Major goal(solved) can be broken down into a number of sub goals,   ~~~~~ *
 * ~~~~~~~ this class implements a number of these decompositions. IDA* is performed on    ~~~~~ *
 * ~~~~~~~ cube to navigate from the starting scramble to the various goals until the      ~~~~~ *
 * ~~~~~~~ cube is ultimatly solved.                                                       ~~~~~ *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;



public class Goal_Oriented_IDAStar_Solver{

	private static boolean VERBOSE  = false;
	private static boolean ADMISSABLE = true;

	/* This function controls the verbose and admissable constants.                        ~~~~~ */
	public static void setConstants(boolean verbose, boolean admissable){
		VERBOSE = verbose;
		ADMISSABLE = admissable;
	}

	private static final int MAX_STATE_COUNT = 1000000000;

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * ~~~~~ Goal Decompositions                                                           ~~~~~ *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

	/* Beginners Method Decomposition                                                      ~~~~~ */
	public static final Goal[] BEGINNERS_METHOD_DECOMPOSITION = {
		Goal.CROSS_W,                       
		Goal.FIRST_LAYER_1_W,
		Goal.FIRST_LAYER_2_W,
		Goal.FIRST_LAYER_3_W,
		Goal.FIRST_LAYER_4_W,
		Goal.SECOND_LAYER_1_W,
		Goal.SECOND_LAYER_2_W,
		Goal.SECOND_LAYER_3_W,
		Goal.SECOND_LAYER_4_W,
		Goal.THIRD_LAYER_INCONSISTENT_CROSS_W,
		Goal.THIRD_LAYER_CROSS_W,
		Goal.THIRD_LAYER_CORNER_POSITION_1_W,
		Goal.THIRD_LAYER_CORNER_POSITIONS,
		Goal.SOLVED
	};

	/* Parallel Piece Placing Decomposition                                                ~~~~~ */
	public static final Goal[] PPP_DECOMPOSITION  = {
		Goal.PPP1,
		Goal.PPP2,
		Goal.PPP3,
		Goal.PPP4,
		Goal.PPP5,
		Goal.PPP6,
		Goal.PPP7,
		Goal.PPP8,
		Goal.PPP9,
		Goal.PPP10,
		Goal.PPP11,
		Goal.PPP12,
		Goal.PPP13,
		Goal.PPP14,
		Goal.PPP15,
		Goal.PPP16,
		Goal.PPP17,
		Goal.ALL_EDGES,
		Goal.ALL_CORNER_POSITIONS,
		Goal.SOLVED
	};

	/* Impoved Beginners Method Decomposition.                                             ~~~~~ */
	public static final Goal[] IMPROVED_DECOMPOSITION = {
		Goal.CROSS_W,
		Goal.F2L1,
		Goal.F2L2,
		Goal.F2L3,
		Goal.F2L4,
		Goal.THIRD_LAYER_PARTIAL_INCONSISTENT_CROSS,
		Goal.THIRD_LAYER_INCONSISTENT_CROSS_W,
		Goal.THIRD_LAYER_PARTIAL_CONSISTENT_CROSS,
		Goal.THIRD_LAYER_CROSS_W,
		Goal.THIRD_LAYER_CORNER_POSITION_1_W,
		Goal.THIRD_LAYER_CORNER_POSITIONS,
		Goal.THIRD_LAYER_1_CORNER,
		Goal.SOLVED
	};

	/* This function returns a random decomposition                                        ~~~~~ */
	public static Goal[] getRandomDecomposition(){
		ArrayList<Goal> all_goals = new ArrayList<Goal>();

		all_goals.add(Goal.PIECE1);
		all_goals.add(Goal.PIECE2);
		all_goals.add(Goal.PIECE3);
		all_goals.add(Goal.PIECE4);
		all_goals.add(Goal.PIECE5);
		all_goals.add(Goal.PIECE6);
		all_goals.add(Goal.PIECE7);
		all_goals.add(Goal.PIECE8);
		all_goals.add(Goal.PIECE9);
		all_goals.add(Goal.PIECE10);
		all_goals.add(Goal.PIECE11);
		all_goals.add(Goal.PIECE12);
		all_goals.add(Goal.PIECE13);
		all_goals.add(Goal.PIECE14);
		all_goals.add(Goal.PIECE15);
		all_goals.add(Goal.PIECE16);
		all_goals.add(Goal.PIECE17);
		all_goals.add(Goal.PIECE18);
		all_goals.add(Goal.PIECE19);
		all_goals.add(Goal.PIECE20);

		Random random = new Random();

		Goal[] random_decomp = new Goal[20];

		for(int i = 0; i < 20; i++){
			int rand = random.nextInt(all_goals.size());
			random_decomp[i] = all_goals.get(rand);
			all_goals.remove(rand);
		}
		return random_decomp;
	}

	/* Varaiables used to store the amount of states that were checked                     ~~~~~ */

	private static int state_count = 0;
	

	/* This function solves a 3x3 rubiks cube using goal decomposition and A*. A* is used  ~~~~~ *
	 * to find a path from one goal to the next. The last goal is always 'solved' thus the ~~~~~ *
	 * algorithm always solves the cube. If however, the alogorithm checks more than       ~~~~~ *
	 * MAX_STATE_COUNT number of states, the algorithm will abort and no solution will be  ~~~~~ *
	 * found. If VERBOSE is true, the algorithm will output reports. Otherwise, only the   ~~~~~ *
	 * solution will be displayed.                                                         ~~~~~ */
	public static void  solveWithDecomposition(Rubiks_Cube_Interface cube, Goal[] goals){
		ArrayList<String> turns_list = new ArrayList<String>(); //list to remember turns
		int last_move = -1; //store the previously made move so the branching factor becomes 15.
		double bound = 0; //current path cost bound for IDA*

		for(int i = 0; i < goals.length; i++){ //for every subgoal
			if(VERBOSE) System.out.println("Starting Goal: " + goals[i]);

			Stack<String> turn_stack = new Stack<String>(); //stack for IDA* search moves
			bound = heuristic(cube); //set initial bound to heuristic value

			while(true){ //search for solution to this subgoal
				//start search, initial cost is zero, previous move is undefined,
				double t = IDAStarSearch(cube, turn_stack, 0, bound, goals, i, last_move);
				if(t == -1) //solution is found
					break;
				bound = t; //else increase bound and search again.
				if(t == -2) //error occured (to many state checks);
					break;
			}
			if(bound == -2) break; //exit because of state overflow

			//create arraylist to transfer moves from the stack.
			ArrayList<String> this_goals_turns = new ArrayList<String>();

			while(!turn_stack.isEmpty()){ //copy each move used to solve this subgoal
				this_goals_turns.add(0, turn_stack.pop()); //add to front to invert stack order
			}
			turns_list.addAll(this_goals_turns); //add all moves from this goal to the master list
			//Display report
			if(VERBOSE){
				System.out.println("Solved Goal: " + goals[i]);
				System.out.println("Current Number of States searched: " + state_count);
				System.out.println("Current Number of Moves: " + turns_list.size());
				System.out.println("This Goals Move: " + this_goals_turns);
				cube.printCube();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
		}
		if(bound == -2){ //if state search overflow
			System.out.println("The Search explored " + MAX_STATE_COUNT + " states and did not");
			System.out.println("find a solution. Decomposition aborted");
			return;	
		}
		//Some goals use algorithms as a single move duiring IDA*. Convert these back to
		//orignal 18 moves. 
		replaceAlgorithms(turns_list);
		
		if(VERBOSE)System.out.println("This Search explored " + state_count + " states.");
		//Display Solution
		System.out.println("SOLUTION: ("+ turns_list.size() + " moves)\n");
		for(int i = 0; i < turns_list.size() / 10; i++){
			turns_list.add(10 + (i * 10), "\n");
		}
		for (String s : turns_list){
			System.out.print(s);
			if(s != "\n") System.out.print(" ");
		}
		System.out.println("\n");

	}

	/* This function performs IDA* search on the given cube for the given goal. It uses    ~~~~~ *
	 * recursion to explore the state space and a heurisitic to explore efficiently.       ~~~~~ */
	public static double IDAStarSearch(Rubiks_Cube_Interface cube, Stack<String> turns,
		int cost, double bound, Goal[] goals, int goal_index, int last_move){
		
		//Determine if the current goal, and all past goals are satisfied by this cube. 
		if(satisfiesGoal(cube, goals, goal_index)) return -1;

		//get the cost.
		double f = cost + heuristic(cube); //Get the estimated cost of this cube
		//abort if the path appears unlikely
		if(f > bound) return f;            //If the estimated cost is too large return. depth limit;

		//Special Goals have access to algorithms in the IDA* search. See performMove2 & undoMove2
		boolean special_goal = 
			goals[goal_index] == Goal.SOLVED || 
			goals[goal_index] == Goal.THIRD_LAYER_1_CORNER;

		double min = -1; //want to maintain the smallest increase that could be made to 'bound' 

		//Try every possible move. (if special goal, need extra 'moves')
		for (int i = 0; i < (special_goal ? 26 : 18); i++){
			//if this move turns the same face as the last move, dont perform it. 
			//Branching factor 18 -> 15
			if(i >= 0 && (i/3 == last_move/3)) continue;
			//increment the number of states checked.
			state_count++;
			if(state_count >= MAX_STATE_COUNT) return -2; //signal error;

			if(special_goal) //perform Special move
				turns.push(performMove2(cube, i));
			else //perform regular move
				turns.push(performMove(cube, i));
			//perform search recursively on the new cube. 
			double t = IDAStarSearch(cube, turns, cost + 1, bound, goals, goal_index, i);

			if(t == -1) return -1; //if solution found
			if(t == -2) return -2; //if state space overflow
			if(t < min || min == -1) min = t; //update min if applicable

			turns.pop(); //remove this turn from the stack of turns 

			if(special_goal) //undo special move
				undoMove2(cube, i);
			else //undo regular move
				undoMove(cube, i); 
		}
		return min; //return the minimum bound increase from this branch of IDA*
	}

	/* This function performs one of the 18 moves based on the value of i. This action is  ~~~~~ *
	 * un-done by the function undoMove(). It returns the string representing the turn     ~~~~~ *
	 * that was made.                                                                      ~~~~~ */
	private static String performMove(Rubiks_Cube_Interface cube, int i){
		switch(i){
			case 0:
				cube.Front1();
				return "Front1";
			case 1:
				cube.Front2();
				return "Front2";
			case 2:
				cube.Front3();
				return "Front3";
			case 3:
				cube.Back1();
				return "Back1";
			case 4:
				cube.Back2();
				return "Back2";
			case 5:
				cube.Back3();
				return "Back3";
			case 6:
				cube.Left1();
				return "Left1";
			case 7:
				cube.Left2();
				return "Left2";
			case 8:
				cube.Left3();
				return "Left3";
			case 9:
				cube.Right1();
				return "Right1";
			case 10:
				cube.Right2();
				return "Right2";
			case 11:
				cube.Right3();
				return "Right3";
			case 12:
				cube.Top1();
				return "Top1";
			case 13:
				cube.Top2();
				return "Top2";
			case 14:
				cube.Top3();
				return "Top3";
			case 15:
				cube.Bottom1();
				return "Bottom1";
			case 16:
				cube.Bottom2();
				return "Bottom2";
			case 17:
				cube.Bottom3();
				return "Bottom3";

				
		}
		return "";
	}

	/* This function performs one of the 18 moves based on the value of i. This action     ~~~~~ *
	 * un-does any work made by PerformMove                                                ~~~~~ */
	private static void undoMove(Rubiks_Cube_Interface cube, int i){
		switch(i){
			case 0:
				cube.Front3();
				break;
			case 1:
				cube.Front2();
				break;
			case 2:
				cube.Front1();
				break;
			case 3:
				cube.Back3();
				break;
			case 4:
				cube.Back2();
				break;
			case 5:
				cube.Back1();
				break;
			case 6:
				cube.Left3();
				break;
			case 7:
				cube.Left2();
				break;
			case 8:
				cube.Left1();
				break;
			case 9:
				cube.Right3();
				break;
			case 10:
				cube.Right2();
				break;
			case 11:
				cube.Right1();
				break;
			case 12:
				cube.Top3();
				break;
			case 13:
				cube.Top2();
				break;
			case 14:
				cube.Top1();
				break;
			case 15:
				cube.Bottom3();
				break;
			case 16:
				cube.Bottom2();
				break;
			case 17:
				cube.Bottom1();
				break;
				
		}
	}

	/* This function performs one of the 18 moves based on the value of i. It also         ~~~~~ *
	 * supports a useful algoirthm for the end of the cube. This action is                 ~~~~~ *
	 * un-done by the function undoMove2(). It returns the string representing the turn    ~~~~~ *
	 * that was made or the algorithm that was used.                                       ~~~~~ */
	private static String performMove2(Rubiks_Cube_Interface cube, int i){
		switch(i){
			case 0:
				cube.Front1();
				return "Front1";
			case 1:
				cube.Front2();
				return "Front2";
			case 2:
				cube.Front3();
				return "Front3";
			case 3:
				cube.Back1();
				return "Back1";
			case 4:
				cube.Back2();
				return "Back2";
			case 5:
				cube.Back3();
				return "Back3";
			case 6:
				cube.Left1();
				return "Left1";
			case 7:
				cube.Left2();
				return "Left2";
			case 8:
				cube.Left3();
				return "Left3";
			case 9:
				cube.Right1();
				return "Right1";
			case 10:
				cube.Right2();
				return "Right2";
			case 11:
				cube.Right3();
				return "Right3";
			case 12:
				cube.Top1();
				return "Top1";
			case 13:
				cube.Top2();
				return "Top2";
			case 14:
				cube.Top3();
				return "Top3";
			case 15:
				cube.Bottom1();
				return "Bottom1";
			case 16:
				cube.Bottom2();
				return "Bottom2";
			case 17:
				cube.Bottom3();
				return "Bottom3";
			/* Add algorithms */
			case 18: 
				cube.Right3();
				cube.Bottom3();
				cube.Right1();
				cube.Bottom1();
				cube.Right3();
				cube.Bottom3();
				cube.Right1();
				cube.Bottom1();
				return "A1";
			case 19:
				cube.Back3();
				cube.Bottom3();
				cube.Back1();
				cube.Bottom1();
				cube.Back3();
				cube.Bottom3();
				cube.Back1();
				cube.Bottom1();
				return "A2";
			case 20:
				cube.Left3();
				cube.Bottom3();
				cube.Left1();
				cube.Bottom1();
				cube.Left3();
				cube.Bottom3();
				cube.Left1();
				cube.Bottom1();
				return "A3";
			case 21:
				cube.Front3();
				cube.Bottom3();
				cube.Front1();
				cube.Bottom1();
				cube.Front3();
				cube.Bottom3();
				cube.Front1();
				cube.Bottom1();
				return "A4";
			case 22: 
				cube.Right3();
				cube.Top3();
				cube.Right1();
				cube.Top1();
				cube.Right3();
				cube.Top3();
				cube.Right1();
				cube.Top1();
				return "A5";
			case 23:
				cube.Back3();
				cube.Top3();
				cube.Back1();
				cube.Top1();
				cube.Back3();
				cube.Top3();
				cube.Back1();
				cube.Top1();
				return "A6";
			case 24:
				cube.Left3();
				cube.Top3();
				cube.Left1();
				cube.Top1();
				cube.Left3();
				cube.Top3();
				cube.Left1();
				cube.Top1();
				return "A7";
			case 25:
				cube.Front3();
				cube.Top3();
				cube.Front1();
				cube.Top1();
				cube.Front3();
				cube.Top3();
				cube.Front1();
				cube.Top1();
				return "A8";				
		}
		return "";
	}


	/* This function performs one of the 18 moves based on the value of i. It also         ~~~~~ *
	 * supports a useful algoirthm for the end cube. This action                           ~~~~~ *
	 * un-does any work made by PerformMove2                                               ~~~~~ */
	private static void undoMove2(Rubiks_Cube_Interface cube, int i){
		switch(i){
			case 0:
				cube.Front3();
				break;
			case 1:
				cube.Front2();
				break;
			case 2:
				cube.Front1();
				break;
			case 3:
				cube.Back3();
				break;
			case 4:
				cube.Back2();
				break;
			case 5:
				cube.Back1();
				break;
			case 6:
				cube.Left3();
				break;
			case 7:
				cube.Left2();
				break;
			case 8:
				cube.Left1();
				break;
			case 9:
				cube.Right3();
				break;
			case 10:
				cube.Right2();
				break;
			case 11:
				cube.Right1();
				break;
			case 12:
				cube.Top3();
				break;
			case 13:
				cube.Top2();
				break;
			case 14:
				cube.Top1();
				break;
			case 15:
				cube.Bottom3();
				break;
			case 16:
				cube.Bottom2();
				break;
			case 17:
				cube.Bottom1();
				break;

			/* Add algorithms */
			case 18: 
				cube.Bottom3();
				cube.Right3();
				cube.Bottom1();
				cube.Right1();
				cube.Bottom3();
				cube.Right3();
				cube.Bottom1();
				cube.Right1();
				break;
			case 19:
				cube.Bottom3();
				cube.Back3();
				cube.Bottom1();
				cube.Back1();
				cube.Bottom3();
				cube.Back3();
				cube.Bottom1();
				cube.Back1();
				break;
			case 20:
				cube.Bottom3();
				cube.Left3();
				cube.Bottom1();
				cube.Left1();
				cube.Bottom3();
				cube.Left3();
				cube.Bottom1();
				cube.Left1();
				break;
			case 21:
				cube.Bottom3();
				cube.Front3();
				cube.Bottom1();
				cube.Front1();
				cube.Bottom3();
				cube.Front3();
				cube.Bottom1();
				cube.Front1();
				break;
			case 22: 
				cube.Top3();
				cube.Right3();
				cube.Top1();
				cube.Right1();
				cube.Top3();
				cube.Right3();
				cube.Top1();
				cube.Right1();
				break;
			case 23:
				cube.Top3();
				cube.Back3();
				cube.Top1();
				cube.Back1();
				cube.Top3();
				cube.Back3();
				cube.Top1();
				cube.Back1();
				break;
			case 24:
				cube.Top3();
				cube.Left3();
				cube.Top1();
				cube.Left1();
				cube.Top3();
				cube.Left3();
				cube.Top1();
				cube.Left1();
				break;
			case 25:
				cube.Top3();
				cube.Front3();
				cube.Top1();
				cube.Front1();
				cube.Top3();
				cube.Front3();
				cube.Top1();
				cube.Front1();
				break;
				
		}
	}











	/* This function determines if the cube satisfies the given goal.                      ~~~~~ *
	 * It is simply a very large switch statement that directs the function call to the    ~~~~~ *
	 * proper function in Rubiks_Cube_Goals_V2.java. It tests that all goals Prior to this ~~~~~ *
	 * this goal in the goal set are also satisfied. (do not want to lose progress)        ~~~~~ */
	public static boolean satisfiesGoal(Rubiks_Cube_Interface cube, Goal[] goals, int goal_index){
		for(int i = 0; i <= goal_index; i++){ //For all goals up to and including this goal
			Goal goal = goals[i];
			switch(goal){
				case PIECE1:
					if(!Rubiks_Cube_Goals_V2.piece1(cube)) return false;
					break;
				case PIECE2:
					if(!Rubiks_Cube_Goals_V2.piece2(cube)) return false;
					break;
				case PIECE3:
					if(!Rubiks_Cube_Goals_V2.piece3(cube)) return false;
					break;
				case PIECE4:
					if(!Rubiks_Cube_Goals_V2.piece4(cube)) return false;
					break;
				case PIECE5:
					if(!Rubiks_Cube_Goals_V2.piece5(cube)) return false;
					break;
				case PIECE6:
					if(!Rubiks_Cube_Goals_V2.piece6(cube)) return false;
					break;
				case PIECE7:
					if(!Rubiks_Cube_Goals_V2.piece7(cube)) return false;
					break;
				case PIECE8:
					if(!Rubiks_Cube_Goals_V2.piece8(cube)) return false;
					break;
				case PIECE9:
					if(!Rubiks_Cube_Goals_V2.piece9(cube)) return false;
					break;
				case PIECE10:
					if(!Rubiks_Cube_Goals_V2.piece10(cube)) return false;
					break;
				case PIECE11:
					if(!Rubiks_Cube_Goals_V2.piece11(cube)) return false;
					break;
				case PIECE12:
					if(!Rubiks_Cube_Goals_V2.piece12(cube)) return false;
					break;
				case PIECE13:
					if(!Rubiks_Cube_Goals_V2.piece13(cube)) return false;
					break;
				case PIECE14:
					if(!Rubiks_Cube_Goals_V2.piece14(cube)) return false;
					break;
				case PIECE15:
					if(!Rubiks_Cube_Goals_V2.piece15(cube)) return false;
					break;
				case PIECE16:
					if(!Rubiks_Cube_Goals_V2.piece16(cube)) return false;
					break;
				case PIECE17:
					if(!Rubiks_Cube_Goals_V2.piece17(cube)) return false;
					break;
				case PIECE18:
					if(!Rubiks_Cube_Goals_V2.piece18(cube)) return false;
					break;
				case PIECE19:
					if(!Rubiks_Cube_Goals_V2.piece19(cube)) return false;
					break;
				case PIECE20:
					if(!Rubiks_Cube_Goals_V2.piece20(cube)) return false;
					break;


				case CROSS_W:
					if(!Rubiks_Cube_Goals_V2.crossW(cube)) return false;
					break;
				case FIRST_LAYER_1_W:
					if(!Rubiks_Cube_Goals_V2.firstLayerW1(cube)) return false;
					break;
				case FIRST_LAYER_2_W:
					if(!Rubiks_Cube_Goals_V2.firstLayerW2(cube)) return false;
					break;
				case FIRST_LAYER_3_W:
					if(!Rubiks_Cube_Goals_V2.firstLayerW3(cube)) return false;
					break;
				case FIRST_LAYER_4_W:
					if(!Rubiks_Cube_Goals_V2.firstLayerW4(cube)) return false;
					break;
				case SECOND_LAYER_1_W:
					if(!Rubiks_Cube_Goals_V2.secondLayerW1(cube)) return false;
					break;
				case SECOND_LAYER_2_W:
					if(!Rubiks_Cube_Goals_V2.secondLayerW2(cube)) return false;
					break;
				case SECOND_LAYER_3_W:
					if(!Rubiks_Cube_Goals_V2.secondLayerW3(cube)) return false;
					break;
				case SECOND_LAYER_4_W:
					if(!Rubiks_Cube_Goals_V2.secondLayerW4(cube)) return false;
					break;
				case THIRD_LAYER_INCONSISTENT_CROSS_W:
					if(!Rubiks_Cube_Goals_V2.inconsistentThirdLayerCrossW(cube)) return false;
					break;
				case THIRD_LAYER_CROSS_W:
					if(!Rubiks_Cube_Goals_V2.ThirdLayerCrossW(cube)) return false;
					break;
				case THIRD_LAYER_CORNER_POSITION_1_W:
					if(!Rubiks_Cube_Goals_V2.ThirdLayerCornerPositionW1(cube)) return false;
					break;
				case THIRD_LAYER_CORNER_POSITIONS:
					if(!Rubiks_Cube_Goals_V2.ThirdLayerCornerPositions(cube)) return false;
					break;
				


				case PPP1:
					if(!Rubiks_Cube_Goals_V2.PPP1(cube)) return false;
					break;
				case PPP2:
					if(!Rubiks_Cube_Goals_V2.PPP2(cube)) return false;
					break;
				case PPP3:
					if(!Rubiks_Cube_Goals_V2.PPP3(cube)) return false;
					break;
				case PPP4:
					if(!Rubiks_Cube_Goals_V2.PPP4(cube)) return false;
					break;
				case PPP5:
					if(!Rubiks_Cube_Goals_V2.PPP5(cube)) return false;
					break;
				case PPP6:
					if(!Rubiks_Cube_Goals_V2.PPP6(cube)) return false;
					break;
				case PPP7:
					if(!Rubiks_Cube_Goals_V2.PPP7(cube)) return false;
					break;
				case PPP8:
					if(!Rubiks_Cube_Goals_V2.PPP8(cube)) return false;
					break;
				case PPP9:
					if(!Rubiks_Cube_Goals_V2.PPP9(cube)) return false;
					break;
				case PPP10:
					if(!Rubiks_Cube_Goals_V2.PPP10(cube)) return false;
					break;
				case PPP11:
					if(!Rubiks_Cube_Goals_V2.PPP11(cube)) return false;
					break;
				case PPP12:
					if(!Rubiks_Cube_Goals_V2.PPP12(cube)) return false;
					break;
				case PPP13:
					if(!Rubiks_Cube_Goals_V2.PPP13(cube)) return false;
					break;
				case PPP14:
					if(!Rubiks_Cube_Goals_V2.PPP14(cube)) return false;
					break;
				case PPP15:
					if(!Rubiks_Cube_Goals_V2.PPP15(cube)) return false;
					break;
				case PPP16:
					if(!Rubiks_Cube_Goals_V2.PPP16(cube)) return false;
					break;
				case PPP17:
					if(!Rubiks_Cube_Goals_V2.PPP17(cube)) return false;
					break;
				case PPP18:
					if(!Rubiks_Cube_Goals_V2.PPP18(cube)) return false;
					break;
				case ALL_EDGES:
					if(!Rubiks_Cube_Goals_V2.allEdges(cube)) return false;
					break;
				case ALL_CORNER_POSITIONS:
					if(!Rubiks_Cube_Goals_V2.allCornerPositions(cube)) return false;
					break;


				case F2L1:
					if(!Rubiks_Cube_Goals_V2.F2L1(cube)) return false;
					break;
				case F2L2:
					if(!Rubiks_Cube_Goals_V2.F2L2(cube)) return false;
					break;
				case F2L3:
					if(!Rubiks_Cube_Goals_V2.F2L3(cube)) return false;
					break;
				case F2L4:
					if(!Rubiks_Cube_Goals_V2.F2L4(cube)) return false;
					break;
				case THIRD_LAYER_PARTIAL_CONSISTENT_CROSS:
					if(!Rubiks_Cube_Goals_V2.thirdLayerPartialConsistentCross(cube)) return false;
					break;
				case THIRD_LAYER_PARTIAL_INCONSISTENT_CROSS:
					if(!Rubiks_Cube_Goals_V2.thirdLayerPartialInconsistentCross(cube)) return false;
					break;
				case THIRD_LAYER_1_CORNER:
					if(!Rubiks_Cube_Goals_V2.thirdLayer1Corner(cube)) return false;
					break;
				case THIRD_LAYER_2_CORNER:
					if(!Rubiks_Cube_Goals_V2.thirdLayer2Corner(cube)) return false;

				case SOLVED:
					if(!Rubiks_Cube_Goals_V2.solved(cube)) return false;
					break;
			}
		}
		return true;
	}

	/* This function estaimates the cost of getting the current cube to the solved state.  ~~~~~ *
	 * It determines the number of pieces that are unsolved. If an admissable heuristic is ~~~~~ *
	 * desired, this value is divided by 8 (because a move can (although unlikely) solve 8 ~~~~~ *
	 * pieces at once.). however, this is often not the reality... thus adding             ~~~~~ *
	 * admissability to this heurisitc takes away alot of the A* algorithms power.         ~~~~~ */
	private static double heuristic(Rubiks_Cube_Interface cube){
		double val = 20;
		if(Rubiks_Cube_Goals_V2.piece1(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece2(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece3(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece4(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece5(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece6(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece7(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece8(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece9(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece10(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece11(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece12(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece13(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece14(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece15(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece16(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece17(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece18(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece19(cube)) val--;
		if(Rubiks_Cube_Goals_V2.piece20(cube)) val--;

		//uncomment to make admissable
		if(ADMISSABLE) val = val / 8.0;
		return val;
	}


	/* This function replaces an Algorithm in the ArrayList of strings with all of the     ~~~~~ *
	 * turns.                                                                              ~~~~~ */
	private static void replaceAlgorithms(ArrayList<String> turns){
		String[] A1 = {"Right3","Bottom3","Right1","Bottom1","Right3","Bottom3","Right1","Bottom1"};
		String[] A2 = {"Back3","Bottom3","Back1","Bottom1","Back3","Bottom3","Back1","Bottom1"};
		String[] A3 = {"Left3","Bottom3","Left1","Bottom1","Left3","Bottom3","Left1","Bottom1"};
		String[] A4 = {"Front3","Bottom3","Front1","Bottom1","Front3","Bottom3","Front1","Bottom1"};
		String[] A5 = {"Right3","Top3","Right1","Top1","Right3","Top3","Right1","Top1"};
		String[] A6 = {"Back3","Top3","Back1","Top1","Back3","Top3","Back1","Top1"};
		String[] A7 = {"Left3","Top3","Left1","Top1","Left3","Top3","Left1","Top1"};
		String[] A8 = {"Front3","Top3","Front1","Top1","Front3","Top3","Front1","Top1"};



		while(turns.contains("A1")){
			int index = turns.indexOf("A1");
			turns.remove(index);
			for (int i = 0; i < A1.length; i++){
				turns.add(i+index, A1[i]);
			}
		}

		while(turns.contains("A2")){
			int index = turns.indexOf("A2");
			turns.remove(index);
			for (int i = 0; i < A2.length; i++){
				turns.add(i+index, A2[i]);
			}
		}

		while(turns.contains("A3")){
			int index = turns.indexOf("A3");
			turns.remove(index);
			for (int i = 0; i < A3.length; i++){
				turns.add(i+index, A3[i]);
			}
		}

		while(turns.contains("A4")){
			int index = turns.indexOf("A4");
			turns.remove(index);
			for (int i = 0; i < A4.length; i++){
				turns.add(i+index, A4[i]);
			}
		}

		while(turns.contains("A5")){
			int index = turns.indexOf("A5");
			turns.remove(index);
			for (int i = 0; i < A5.length; i++){
				turns.add(i+index, A5[i]);
			}
		}

		while(turns.contains("A6")){
			int index = turns.indexOf("A6");
			turns.remove(index);
			for (int i = 0; i < A6.length; i++){
				turns.add(i+index, A6[i]);
			}
		}

		while(turns.contains("A7")){
			int index = turns.indexOf("A7");
			turns.remove(index);
			for (int i = 0; i < A7.length; i++){
				turns.add(i+index, A7[i]);
			}
		}

		while(turns.contains("A8")){
			int index = turns.indexOf("A8");
			turns.remove(index);
			for (int i = 0; i < A8.length; i++){
				turns.add(i+index, A8[i]);
			}
		}
	}



}