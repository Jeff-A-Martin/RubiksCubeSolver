/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
 * ~~~~~ Jeffrey Martin                                                                    ~~~~~ *
 * ~~~~~ CS4341 Introduction to Artifical Intelligence                                     ~~~~~ *
 * ~~~~~ Professor Neil Heffernan                                                          ~~~~~ *
 * ~~~~~ October 24th, 2016                                                                ~~~~~ *
 * ~~~~~                                                                                   ~~~~~ *
 * ~~~~~ BSMS Project : Goal Oriented Rubiks Cube Solver with IDA*                         ~~~~~ *
 * ~~~~~ File: Rubiks_Cube_Goals_V2.java                                                   ~~~~~ *
 * ~~~~~ File Description                                                                  ~~~~~ *
 * ~~~~~~~ This class defines all the goal states of the rubiks cube. All methods are      ~~~~~ *
 * ~~~~~~~ static and accept an object that implements Rubiks_Cube_Interface. They all     ~~~~~ *
 * ~~~~~~~ return a boolean indicating if the current cube satisfies the goal.             ~~~~~ *
 * ~~~~~~~ The simplest subgoal is getting 1 piece to its final location and proper        ~~~~~ *
 * ~~~~~~~ orientation. All goals are a joining of these simple subgoals with the 'and' or ~~~~~ *
 * ~~~~~~~ 'or' operators. (Ex: the solved cube is the and of all simple subgoals).        ~~~~~ *
 * ~~~~~~~ Because the center of each face can not change, we do not need to consider it   ~~~~~ *
 * ~~~~~~~ as a goal. This leaves 20 pieces remaining to consider. The pieces are ordered  ~~~~~ *
 * ~~~~~~~ as follows:                                                                     ~~~~~ *
 * ~~~~~~~ 1:  WOB corner                                                                  ~~~~~ *
 * ~~~~~~~ 2:  WRB corner                                                                  ~~~~~ *
 * ~~~~~~~ 3:  WRG corner                                                                  ~~~~~ *
 * ~~~~~~~ 4:  WOG corner                                                                  ~~~~~ *
 * ~~~~~~~ 5:  YOB corner                                                                  ~~~~~ *
 * ~~~~~~~ 6:  YRB corner                                                                  ~~~~~ *
 * ~~~~~~~ 7:  YRG corner                                                                  ~~~~~ *
 * ~~~~~~~ 8:  YOG corner                                                                  ~~~~~ *
 * ~~~~~~~ 9:  WO  edge                                                                    ~~~~~ *
 * ~~~~~~~ 10: WB  edge                                                                    ~~~~~ *
 * ~~~~~~~ 11: WR  edge                                                                    ~~~~~ *
 * ~~~~~~~ 12: WG  edge                                                                    ~~~~~ *
 * ~~~~~~~ 13: OB  edge                                                                    ~~~~~ *
 * ~~~~~~~ 14: BR  edge                                                                    ~~~~~ *
 * ~~~~~~~ 15: RG  edge                                                                    ~~~~~ *
 * ~~~~~~~ 16: GO  edge                                                                    ~~~~~ *
 * ~~~~~~~ 17: YO  edge                                                                    ~~~~~ *
 * ~~~~~~~ 18: YB  edge                                                                    ~~~~~ *
 * ~~~~~~~ 19: YR  edge                                                                    ~~~~~ *
 * ~~~~~~~ 20: YG  edge                                                                    ~~~~~ *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Rubiks_Cube_Goals_V2 {

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * ~~~~~ Solved Goal                                                                   ~~~~~ *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	public static boolean solved(Rubiks_Cube_Interface cube){
		return 
			piece1(cube) &&
			piece2(cube) &&
			piece3(cube) &&
			piece4(cube) &&
			piece5(cube) &&
			piece6(cube) &&
			piece7(cube) &&
			piece8(cube) &&
			piece9(cube) &&
			piece10(cube) &&
			piece11(cube) &&
			piece12(cube) &&
			piece13(cube) &&
			piece14(cube) &&
			piece15(cube) &&
			piece16(cube) &&
			piece17(cube) &&
			piece18(cube) &&
			piece19(cube) &&
			piece20(cube);
	}

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * ~~~~~ Piece Goals                                                                   ~~~~~ *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

	public static boolean piece1(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(0)  == 'W' &&
			cube.getFacletColor(9)  == 'O' &&
			cube.getFacletColor(38) == 'B';
	}

	public static boolean piece2(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(2)  == 'W' &&
			cube.getFacletColor(29) == 'R' &&
			cube.getFacletColor(36) == 'B';
	}

	public static boolean piece3(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(8)  == 'W' &&
			cube.getFacletColor(27) == 'R' &&
			cube.getFacletColor(20) == 'G';
	}

	public static boolean piece4(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(6)  == 'W' &&
			cube.getFacletColor(11) == 'O' &&
			cube.getFacletColor(18) == 'G';
	}

	public static boolean piece5(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(51) == 'Y' &&
			cube.getFacletColor(15) == 'O' &&
			cube.getFacletColor(44) == 'B';
	}

	public static boolean piece6(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(53) == 'Y' &&
			cube.getFacletColor(35) == 'R' &&
			cube.getFacletColor(42) == 'B';
	}

	public static boolean piece7(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(47) == 'Y' &&
			cube.getFacletColor(33) == 'R' &&
			cube.getFacletColor(26) == 'G';
	}

	public static boolean piece8(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(45) == 'Y' &&
			cube.getFacletColor(17) == 'O' &&
			cube.getFacletColor(24) == 'G';
	}

	public static boolean piece9(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(3)  == 'W' &&
			cube.getFacletColor(10) == 'O';
	}

	public static boolean piece10(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(1)  == 'W' &&
			cube.getFacletColor(37) == 'B';
	}

	public static boolean piece11(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(5)  == 'W' &&
			cube.getFacletColor(28) == 'R';
	}

	public static boolean piece12(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(7)  == 'W' &&
			cube.getFacletColor(19) == 'G';
	}

	public static boolean piece13(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(12) == 'O' &&
			cube.getFacletColor(41) == 'B';
	}

	public static boolean piece14(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(39) == 'B' &&
			cube.getFacletColor(32) == 'R';
	}

	public static boolean piece15(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(30) == 'R' &&
			cube.getFacletColor(23) == 'G';
	}

	public static boolean piece16(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(21) == 'G' &&
			cube.getFacletColor(14) == 'O';
	}

	public static boolean piece17(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(48) == 'Y' &&
			cube.getFacletColor(16) == 'O';
	}

	public static boolean piece18(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(52) == 'Y' &&
			cube.getFacletColor(43) == 'B';
	}

	public static boolean piece19(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(50) == 'Y' &&
			cube.getFacletColor(34) == 'R';
	}

	public static boolean piece20(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(46) == 'Y' &&
			cube.getFacletColor(25) == 'G';
	}

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * ~~~~~ Beginners Method Goals                                                        ~~~~~ *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

	/* This function determines if the rubiks cube has a white cross                       ~~~~~ */
	public static boolean crossW(Rubiks_Cube_Interface cube){
		return 
			piece9(cube) &&
			piece10(cube) &&
			piece11(cube) &&
			piece12(cube);
	}

	/* This function determines if one corner piece has been placed in the rubiks cube's   ~~~~~ *
	 * first layer (white)                                                                 ~~~~~ */
	public static boolean firstLayerW1(Rubiks_Cube_Interface cube){
		return 
			piece1(cube) ||
			piece2(cube) ||
			piece3(cube) ||
			piece4(cube);
	}

	/* This function determines if two corner pieces have been placed in the rubiks cube's ~~~~~ *
	 * first layer (white).                                                                ~~~~~ */
	public static boolean firstLayerW2(Rubiks_Cube_Interface cube){
		int corners = 0;
		if(piece1(cube)) corners++;
		if(piece2(cube)) corners++;
		if(piece3(cube)) corners++;
		if(piece4(cube)) corners++;		
		return corners >= 2;
	}

	/* This function determines if three corner pieces have been placed in the rubik's     ~~~~~ *
	 * cube's first layer (white).                                                         ~~~~~ */
	public static boolean firstLayerW3(Rubiks_Cube_Interface cube){
		int corners = 0;
		if(piece1(cube)) corners++;
		if(piece2(cube)) corners++;
		if(piece3(cube)) corners++;
		if(piece4(cube)) corners++;		
		return corners >= 3;
	}

	/* This function determines if the first layer of the rubiks cube is solved            ~~~~~ */
	public static boolean firstLayerW4(Rubiks_Cube_Interface cube){
		return
		piece1(cube) &&
		piece2(cube) &&
		piece3(cube) &&
		piece4(cube);
	}

	/* This function determines if 1 edge piece are placed in the second layer(white)      ~~~~~ */
	public static boolean secondLayerW1(Rubiks_Cube_Interface cube){
		return 
			piece13(cube) ||
			piece14(cube) ||
			piece15(cube) ||
			piece16(cube);
	}

	/* This function determines if 2 edge pieces are placed in the second layer(white)     ~~~~~ */
	public static boolean secondLayerW2(Rubiks_Cube_Interface cube){
		int edges = 0;
		if(piece13(cube)) edges++;
		if(piece14(cube)) edges++;
		if(piece15(cube)) edges++;
		if(piece16(cube)) edges++;
		return edges >= 2;
		
	}

	/* This function determines if 3 edge pieces are placed in the second layer(white)     ~~~~~ */
	public static boolean secondLayerW3(Rubiks_Cube_Interface cube){
		int edges = 0;
		if(piece13(cube)) edges++;
		if(piece14(cube)) edges++;
		if(piece15(cube)) edges++;
		if(piece16(cube)) edges++;
		return edges >= 3;
	}

	/* This function determines if 4 edge pieces are placed in the second layer(white)     ~~~~~ */
	public static boolean secondLayerW4(Rubiks_Cube_Interface cube){
		return
			piece13(cube) &&
			piece14(cube) &&
			piece15(cube) &&
			piece16(cube);
	}

	/* This function determines if the yellow cross(not consistent with layer) has         ~~~~~ *
	 * been formed                                                                         ~~~~~ */
	public static boolean inconsistentThirdLayerCrossW(Rubiks_Cube_Interface cube){
		return 
			cube.getFacletColor(46) == 'Y' &&
			cube.getFacletColor(48) == 'Y' &&
			cube.getFacletColor(50) == 'Y' &&
			cube.getFacletColor(52) == 'Y';
	}

	/* This function determines if the yellow cross(consistent with the layer) has         ~~~~~ *
	 * been formed.                                                                        ~~~~~ */
	public static boolean ThirdLayerCrossW(Rubiks_Cube_Interface cube){
		return 
			piece17(cube) &&
			piece18(cube) &&
			piece19(cube) &&
			piece20(cube);
	}


	/* This function determines if one of the yellow corners is in in the right position.  ~~~~~ */
	public static boolean ThirdLayerCornerPositionW1(Rubiks_Cube_Interface cube){
		/* Check Corner 5                                                                  ~~~~~ */
		//faclets of corner 5 (YOB)
		char F1 = cube.getFacletColor(51);
		char F2 = cube.getFacletColor(15);
		char F3 = cube.getFacletColor(44);

		/* If Corner 5 is not in the right location. */
		if(((F1 == 'Y' && F2 == 'O' && F3 == 'B') ||
			(F1 == 'B' && F2 == 'Y' && F3 == 'O') ||
			(F1 == 'O' && F2 == 'B' && F3 == 'Y')))
			return true;

		/* Check Corner 6                                                                  ~~~~~ */
		//faclets of corner 6 (YRB)
		F1 = cube.getFacletColor(53);
		F2 = cube.getFacletColor(35);
		F3 = cube.getFacletColor(42);

		/* If Corner 6 is not in the right location. */
		if(((F1 == 'Y' && F2 == 'R' && F3 == 'B') ||
			(F1 == 'B' && F2 == 'Y' && F3 == 'R') ||
			(F1 == 'R' && F2 == 'B' && F3 == 'Y')))
			return true;

		/* Check Corner 7                                                                  ~~~~~ */
		//faclets of corner 7 (YRG)
		F1 = cube.getFacletColor(47);
		F2 = cube.getFacletColor(33);
		F3 = cube.getFacletColor(26);

		/* If Corner 7 is not in the right location. */
		if(((F1 == 'Y' && F2 == 'R' && F3 == 'G') ||
			(F1 == 'G' && F2 == 'Y' && F3 == 'R') ||
			(F1 == 'R' && F2 == 'G' && F3 == 'Y')))
			return true;

		/* Check Corner 8                                                                  ~~~~~ */
		//faclets of corner 8 (Y)G)
		F1 = cube.getFacletColor(45);
		F2 = cube.getFacletColor(17);
		F3 = cube.getFacletColor(24);

		/* If Corner 8 is not in the right location. */
		if(((F1 == 'Y' && F2 == 'O' && F3 == 'G') ||
			(F1 == 'G' && F2 == 'Y' && F3 == 'O') ||
			(F1 == 'O' && F2 == 'G' && F3 == 'Y')))
			return true;


		return false;
	}

	/* This function determines if the 4 corners of the last layer have been placed in the ~~~~~ *
	 * position, but not necessarily the proper orientation                                ~~~~~ */
	public static boolean ThirdLayerCornerPositions(Rubiks_Cube_Interface cube){
		/* Check Corner 5                                                                  ~~~~~ */
		//faclets of corner 5 (YOB)
		char F1 = cube.getFacletColor(51);
		char F2 = cube.getFacletColor(15);
		char F3 = cube.getFacletColor(44);

		/* If Corner 5 is not in the right location. */
		if(!((F1 == 'Y' && F2 == 'O' && F3 == 'B') ||
			(F1 == 'B' && F2 == 'Y' && F3 == 'O') ||
			(F1 == 'O' && F2 == 'B' && F3 == 'Y')))
			return false;

		/* Check Corner 6                                                                  ~~~~~ */
		//faclets of corner 6 (YRB)
		F1 = cube.getFacletColor(53);
		F2 = cube.getFacletColor(35);
		F3 = cube.getFacletColor(42);

		/* If Corner 6 is not in the right location. */
		if(!((F1 == 'Y' && F2 == 'R' && F3 == 'B') ||
			(F1 == 'B' && F2 == 'Y' && F3 == 'R') ||
			(F1 == 'R' && F2 == 'B' && F3 == 'Y')))
			return false;

		/* Check Corner 7                                                                  ~~~~~ */
		//faclets of corner 7 (YRG)
		F1 = cube.getFacletColor(47);
		F2 = cube.getFacletColor(33);
		F3 = cube.getFacletColor(26);

		/* If Corner 7 is not in the right location. */
		if(!((F1 == 'Y' && F2 == 'R' && F3 == 'G') ||
			(F1 == 'G' && F2 == 'Y' && F3 == 'R') ||
			(F1 == 'R' && F2 == 'G' && F3 == 'Y')))
			return false;

		/* Check Corner 8                                                                  ~~~~~ */
		//faclets of corner 8 (Y)G)
		F1 = cube.getFacletColor(45);
		F2 = cube.getFacletColor(17);
		F3 = cube.getFacletColor(24);

		/* If Corner 8 is not in the right location. */
		if(!((F1 == 'Y' && F2 == 'O' && F3 == 'G') ||
			(F1 == 'G' && F2 == 'Y' && F3 == 'O') ||
			(F1 == 'O' && F2 == 'G' && F3 == 'Y')))
			return false;


		return true;
	}

	/* This function determines if the 4 corners of the last layer have been placed in the ~~~~~ *
	 * position, but not necessarily the proper orientation                                ~~~~~ */
	public static boolean FirstLayerCornerPositions(Rubiks_Cube_Interface cube){
		/* Check Corner 1                                                                  ~~~~~ */
		//faclets of corner 1 (WOB)
		char F1 = cube.getFacletColor(0);
		char F2 = cube.getFacletColor(9);
		char F3 = cube.getFacletColor(38);

		/* If Corner 1 is not in the right location. */
		if(!((F1 == 'W' && F2 == 'O' && F3 == 'B') ||
			(F1 == 'B' && F2 == 'W' && F3 == 'O') ||
			(F1 == 'O' && F2 == 'B' && F3 == 'W')))
			return false;

		/* Check Corner 2                                                                  ~~~~~ */
		//faclets of corner 2 (WRB)
		F1 = cube.getFacletColor(2);
		F2 = cube.getFacletColor(29);
		F3 = cube.getFacletColor(36);

		/* If Corner 2 is not in the right location. */
		if(!((F1 == 'W' && F2 == 'R' && F3 == 'B') ||
			(F1 == 'B' && F2 == 'W' && F3 == 'R') ||
			(F1 == 'R' && F2 == 'B' && F3 == 'W')))
			return false;

		/* Check Corner 3                                                                  ~~~~~ */
		//faclets of corner 3 (WRG)
		F1 = cube.getFacletColor(8);
		F2 = cube.getFacletColor(27);
		F3 = cube.getFacletColor(20);

		/* If Corner 3 is not in the right location. */
		if(!((F1 == 'W' && F2 == 'R' && F3 == 'G') ||
			(F1 == 'G' && F2 == 'W' && F3 == 'R') ||
			(F1 == 'R' && F2 == 'G' && F3 == 'W')))
			return false;

		/* Check Corner 4                                                                  ~~~~~ */
		//faclets of corner 4 (WOG)
		F1 = cube.getFacletColor(6);
		F2 = cube.getFacletColor(11);
		F3 = cube.getFacletColor(18);

		/* If Corner 8 is not in the right location. */
		if(!((F1 == 'W' && F2 == 'O' && F3 == 'G') ||
			(F1 == 'G' && F2 == 'W' && F3 == 'O') ||
			(F1 == 'O' && F2 == 'G' && F3 == 'W')))
			return false;


		return true;
	}


	/* This function determines if n pieces are solved                                     ~~~~~ */
	private static boolean nPiecesSolved(Rubiks_Cube_Interface cube, int n){
		int count = 0;
		if(piece1(cube)) count++;
		if(count >= n) return true;
		if(piece2(cube)) count++;
		if(count >= n) return true;
		if(piece3(cube)) count++;
		if(count >= n) return true;
		if(piece4(cube)) count++;
		if(count >= n) return true;
		if(piece5(cube)) count++;
		if(count >= n) return true;
		if(piece6(cube)) count++;
		if(count >= n) return true;
		if(piece7(cube)) count++;
		if(count >= n) return true;
		if(piece8(cube)) count++;
		if(count >= n) return true;
		if(piece9(cube)) count++;
		if(count >= n) return true;
		if(piece10(cube)) count++;
		if(count >= n) return true;
		if(piece11(cube)) count++;
		if(count >= n) return true;
		if(piece12(cube)) count++;
		if(count >= n) return true;
		if(piece13(cube)) count++;
		if(count >= n) return true;
		if(piece14(cube)) count++;
		if(count >= n) return true;
		if(piece15(cube)) count++;
		if(count >= n) return true;
		if(piece16(cube)) count++;
		if(count >= n) return true;
		if(piece17(cube)) count++;
		if(count >= n) return true;
		if(piece18(cube)) count++;
		if(count >= n) return true;
		if(piece19(cube)) count++;
		if(count >= n) return true;
		if(piece20(cube)) count++;
		if(count >= n) return true;
		return false;
	}

	public static boolean PPP1(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 1);
	}

	public static boolean PPP2(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 2);
	}

	public static boolean PPP3(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 3);
	}

	public static boolean PPP4(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 4);
	}

	public static boolean PPP5(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 5);
	}

	public static boolean PPP6(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 6);
	}

	public static boolean PPP7(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 7);
	}

	public static boolean PPP8(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 8);
	}

	public static boolean PPP9(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 9);
	}

	public static boolean PPP10(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 10);
	}

	public static boolean PPP11(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 11);
	}

	public static boolean PPP12(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 12);
	}

	public static boolean PPP13(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 13);
	}

	public static boolean PPP14(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 14);
	}

	public static boolean PPP15(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 15);
	}

	public static boolean PPP16(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 16);
	}

	public static boolean PPP17(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 17);
	}

	public static boolean PPP18(Rubiks_Cube_Interface cube){
		return nPiecesSolved(cube, 18);
	}

	/* This function determines if all the edge pieces are solved.                         ~~~~~ */
	public static boolean allEdges(Rubiks_Cube_Interface cube){
		return
			piece9(cube) &&
			piece10(cube) &&
			piece11(cube) &&
			piece12(cube) &&
			piece13(cube) &&
			piece14(cube) &&
			piece15(cube) &&
			piece16(cube) &&
			piece17(cube) &&
			piece18(cube) &&
			piece19(cube) &&
			piece20(cube);

	}
	/* This funciton determines if all of the corners are in their proper location.        ~~~~~ */
	public static boolean allCornerPositions(Rubiks_Cube_Interface cube){
		return ThirdLayerCornerPositions(cube) && FirstLayerCornerPositions(cube);
	}


	/* This function determines if one F2L pair is solved                                  ~~~~~ */
	public static boolean F2L1(Rubiks_Cube_Interface cube){
		return 
			(piece1(cube) && piece13(cube)) ||
			(piece2(cube) && piece14(cube)) ||
			(piece3(cube) && piece15(cube)) ||
			(piece4(cube) && piece16(cube));
	}

	/* This function determines if two F2L pairs are solved                                ~~~~~ */
	public static boolean F2L2(Rubiks_Cube_Interface cube){
		int count = 0;
		if(piece1(cube) && piece13(cube)) count++;
		if(piece2(cube) && piece14(cube)) count++;
		if(piece3(cube) && piece15(cube)) count++;
		if(piece4(cube) && piece16(cube)) count++;
		return count >= 2;
	}

	/* This function determines if three F2L pairs are solved                              ~~~~~ */
	public static boolean F2L3(Rubiks_Cube_Interface cube){
		int count = 0;
		if(piece1(cube) && piece13(cube)) count++;
		if(piece2(cube) && piece14(cube)) count++;
		if(piece3(cube) && piece15(cube)) count++;
		if(piece4(cube) && piece16(cube)) count++;
		return count >= 3;
	}

	/* This function determines if four F2L pairs are solved                               ~~~~~ */
	public static boolean F2L4(Rubiks_Cube_Interface cube){
		int count = 0;
		if(piece1(cube) && piece13(cube)) count++;
		if(piece2(cube) && piece14(cube)) count++;
		if(piece3(cube) && piece15(cube)) count++;
		if(piece4(cube) && piece16(cube)) count++;
		return count >= 4;
	}

	/* This function determines if the there are two adjacent edges in the third layer that~~~~~ *
	 * have the yellow faclet correct.                                                     ~~~~~ */
	public static boolean thirdLayerPartialInconsistentCross(Rubiks_Cube_Interface cube){
		return 
			(cube.getFacletColor(46) == 'Y' && cube.getFacletColor(50) == 'Y') ||
			(cube.getFacletColor(50) == 'Y' && cube.getFacletColor(52) == 'Y') ||
			(cube.getFacletColor(52) == 'Y' && cube.getFacletColor(48) == 'Y') ||
			(cube.getFacletColor(48) == 'Y' && cube.getFacletColor(46) == 'Y');
	}

	/* This function determines if two adjacent edges are solved in the third layer.       ~~~~~ */
	public static boolean thirdLayerPartialConsistentCross(Rubiks_Cube_Interface cube){
		return
			(piece17(cube) && piece18(cube)) ||
			(piece18(cube) && piece19(cube)) ||
			(piece19(cube) && piece20(cube)) ||
			(piece20(cube) && piece17(cube));
	}

	/* This function determines if one corner has been solved in the third layer           ~~~~~ */
	public static boolean thirdLayer1Corner(Rubiks_Cube_Interface cube){
		return piece5(cube) || piece6(cube) || piece7(cube) || piece8(cube);
	}

	/* This function determines if two corners have been solved in the third layer.        ~~~~~ */
	public static boolean thirdLayer2Corner(Rubiks_Cube_Interface cube){
		int count = 0;
		if(piece5(cube)) count++;
		if(piece6(cube)) count++;
		if(piece7(cube)) count++;
		if(piece8(cube)) count++;
		return count >= 2;
	}







}