/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
 * ~~~~~ Jeffrey Martin                                                                    ~~~~~ *
 * ~~~~~ CS4341 Introduction to Artifical Intelligence                                     ~~~~~ *
 * ~~~~~ Professor Neil Heffernan                                                          ~~~~~ *
 * ~~~~~ October 24th, 2016                                                                ~~~~~ *
 * ~~~~~                                                                                   ~~~~~ *
 * ~~~~~ BSMS Project : Goal Oriented Rubiks Cube Solver with IDA*                         ~~~~~ *
 * ~~~~~ File: Rubiks_Cube_V2.java                                                         ~~~~~ *
 * ~~~~~ File Description                                                                  ~~~~~ *
 * ~~~~~~~ This class implements the Rubiks_Cube_Interface. It supports all 18 turns       ~~~~~ *
 * ~~~~~~~ (6 faces, each can be turned 90 degrees, 180 degrees, or 270 degrees).          ~~~~~ *
 * ~~~~~~~ The Rubiks Cube hs 54 faclets that need to be represented. This implementation  ~~~~~ *
 * ~~~~~~~ Stores the color of each faclet in an array containing 54 elements. The mapping ~~~~~ *
 * ~~~~~~~ of faclets to indicies in the array is shown below.                             ~~~~~ *
 * ~~~~~~~ letters and                                                                     ~~~~~ *
 *                                                                                               *
 *          Solved Cube                              Order Mapping of Pieces                     *
 *                                                                                               *
 *           [W][W][W]                     |              [00][01][02]                           *
 *           [W][W][W]                     |              [03][04][05]                           *
 *           [W][W][W]                     |              [06][07][08]                           *
 *                                         |                                                     *
 * [O][O][O] [G][G][G] [R][R][R] [B][B][B] | [09][10][11] [18][19][20] [27][28][29] [36][37][38] *
 * [O][O][O] [G][G][G] [R][R][R] [B][B][B] | [12][13][14] [21][22][23] [30][31][32] [39][40][41] *
 * [O][O][O] [G][G][G] [R][R][R] [B][B][B] | [15][16][17] [24][25][26] [33][34][35] [42][43][44] *
 *                                         |                                                     *
 *           [Y][Y][Y]                     |              [45][46][47]                           *
 *           [Y][Y][Y]                     |              [48][49][50]                           *
 *           [Y][Y][Y]                     |              [51][52][53]                           *
 *                                                                                               *
 * ~~~~~~~ The Colors will be represented as follows:                                      ~~~~~ *
 * ~~~~~~~ White  (W): 1   (top)                                                           ~~~~~ *
 * ~~~~~~~ Orange (O): 2   (left)                                                          ~~~~~ *
 * ~~~~~~~ Green  (G): 3   (front)                                                         ~~~~~ *
 * ~~~~~~~ Red    (R): 4   (right)                                                         ~~~~~ *
 * ~~~~~~~ Blue   (B): 5   (back)                                                          ~~~~~ *
 * ~~~~~~~ Yellow (Y): 6   (bottom)                                                        ~~~~~ *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Random;
public class Rubiks_Cube_V2 implements Rubiks_Cube_Interface{
	/* Define the representation of each color in binary                                   ~~~~~ */
	private static final char white  = 'W';
	private static final char orange = 'O';
	private static final char green  = 'G';
	private static final char red    = 'R';
	private static final char blue   = 'B';
	private static final char yellow = 'Y';

	/* Define the arrray to store the cube. 54 bytes are needed to store each faclet       ~~~~~ */
	private char[] cube; 

	/* Random object used for generating cube states.                                      ~~~~~ */
	private Random random;

	/* Default Constructor method a Rubiks_Cube_V1                                         ~~~~~ */
	public Rubiks_Cube_V2(){
		this.cube = new char[54];
		initializeCube();
		this.random = new Random();
	}

	/* Constructor that allows the starting state to be specified. Will allow invalid      ~~~~~ *
	 * cube states.                                                                        ~~~~~ */
	public Rubiks_Cube_V2(char[] facelets){
		this.random = new Random();
		this.cube = facelets;
	}

	/* Turn the Front face by 90 degrees                                                   ~~~~~ */
	public void Front1(){
		char temp     = this.cube[6];
		this.cube[6]  = this.cube[17];
		this.cube[17] = this.cube[47];
		this.cube[47] = this.cube[27];
		this.cube[27] = temp;

		temp          = this.cube[7];
		this.cube[7]  = this.cube[14];
		this.cube[14] = this.cube[46];
		this.cube[46] = this.cube[30];
		this.cube[30] = temp;

		temp          = this.cube[8];
		this.cube[8]  = this.cube[11];
		this.cube[11] = this.cube[45];
		this.cube[45] = this.cube[33];
		this.cube[33] = temp;	

		temp          = this.cube[18];
		this.cube[18] =	this.cube[24];
		this.cube[24] = this.cube[26];
		this.cube[26] = this.cube[20];
		this.cube[20] = temp;

		temp          = this.cube[19];
		this.cube[19] = this.cube[21];
		this.cube[21] = this.cube[25];
		this.cube[25] = this.cube[23];
		this.cube[23] = temp;
	}

	/* Turn the Front face by 180 degrees                                                  ~~~~~ */
	public void Front2(){
		char temp     = this.cube[6];
		this.cube[6]  = this.cube[47];
		this.cube[47] = temp;

		temp          = this.cube[7];
		this.cube[7]  = this.cube[46];
		this.cube[46] = temp;

		temp          = this.cube[8];
		this.cube[8]  = this.cube[45];
		this.cube[45] = temp;

		temp          = this.cube[11];
		this.cube[11] = this.cube[33];
		this.cube[33] = temp;

		temp          = this.cube[14];
		this.cube[14] = this.cube[30];
		this.cube[30] = temp;

		temp          = this.cube[17];
		this.cube[17] = this.cube[27];
		this.cube[27] = temp;

		temp          = this.cube[18];
		this.cube[18] =	this.cube[26];
		this.cube[26] = temp;

		temp          = this.cube[19];
		this.cube[19] = this.cube[25];
		this.cube[25] = temp;

		temp          =	this.cube[24];
		this.cube[24] = this.cube[20];
		this.cube[20] = temp;

		temp          = this.cube[21];
		this.cube[21] = this.cube[23];
		this.cube[23] = temp;
	}

	/* Turn the Front face by 270 degrees                                                  ~~~~~ */
	public void Front3(){
		char temp     = this.cube[6];
		this.cube[6]  = this.cube[27];
		this.cube[27] = this.cube[47];
		this.cube[47] = this.cube[17];
		this.cube[17] = temp;

		temp          = this.cube[7];
		this.cube[7]  = this.cube[30];
		this.cube[30] = this.cube[46];
		this.cube[46] = this.cube[14];
		this.cube[14] = temp;

		temp          = this.cube[8];
		this.cube[8]  = this.cube[33];
		this.cube[33] = this.cube[45];
		this.cube[45] = this.cube[11];
		this.cube[11] = temp;

		temp          = this.cube[18];
		this.cube[18] =	this.cube[20];
		this.cube[20] = this.cube[26];
		this.cube[26] = this.cube[24];
		this.cube[24] = temp;

		temp          = this.cube[19];
		this.cube[19] = this.cube[23];
		this.cube[23] = this.cube[25];
		this.cube[25] = this.cube[21];
		this.cube[21] = temp;
	}

	/* Turn the Back face by 90 degrees                                                    ~~~~~ */
	public void Back1(){
		char temp     = this.cube[0];
		this.cube[0]  = this.cube[29];
		this.cube[29] = this.cube[53];
		this.cube[53] = this.cube[15];
		this.cube[15] = temp;

		temp          = this.cube[1];
		this.cube[1]  = this.cube[32];
		this.cube[32] = this.cube[52];
		this.cube[52] = this.cube[12];
		this.cube[12] = temp;

		temp          = this.cube[2];
		this.cube[2]  = this.cube[35];
		this.cube[35] = this.cube[51];
		this.cube[51] = this.cube[9];
		this.cube[9] = temp;

		temp          = this.cube[36];
		this.cube[36] =	this.cube[42];
		this.cube[42] = this.cube[44];
		this.cube[44] = this.cube[38];
		this.cube[38] = temp;

		temp          = this.cube[37];
		this.cube[37] = this.cube[39];
		this.cube[39] = this.cube[43];
		this.cube[43] = this.cube[41];
		this.cube[41] = temp;
	}

	/* Turn the Back face by 180 degrees                                                   ~~~~~ */
	public void Back2(){
		char temp     = this.cube[0];
		this.cube[0]  = this.cube[53];
		this.cube[53] = temp;

		temp          = this.cube[1];
		this.cube[1]  = this.cube[52];
		this.cube[52] = temp;

		temp          = this.cube[2];
		this.cube[2]  = this.cube[51];
		this.cube[51] = temp;

		temp          = this.cube[29];
		this.cube[29] = this.cube[15];
		this.cube[15] = temp;

		temp          = this.cube[32];
		this.cube[32] = this.cube[12];
		this.cube[12] = temp;

		temp          = this.cube[35];
		this.cube[35] = this.cube[9];
		this.cube[9] = temp;

		temp          = this.cube[37];
		this.cube[37] =	this.cube[43];
		this.cube[43] = temp;

		temp          = this.cube[39];
		this.cube[39] = this.cube[41];
		this.cube[41] = temp;

		temp          =	this.cube[36];
		this.cube[36] = this.cube[44];
		this.cube[44] = temp;

		temp          = this.cube[38];
		this.cube[38] = this.cube[42];
		this.cube[42] = temp;
	}

	/* Turn the Back face by 270 degrees                                                   ~~~~~ */
	public void Back3(){
		char temp     = this.cube[0];
		this.cube[0]  = this.cube[15];
		this.cube[15] = this.cube[53];
		this.cube[53] = this.cube[29];
		this.cube[29] = temp;

		temp          = this.cube[1];
		this.cube[1]  = this.cube[12];
		this.cube[12] = this.cube[52];
		this.cube[52] = this.cube[32];
		this.cube[32] = temp;

		temp          = this.cube[2];
		this.cube[2]  = this.cube[9];
		this.cube[9]  = this.cube[51];
		this.cube[51] = this.cube[35];
		this.cube[35] = temp;

		temp          = this.cube[36];
		this.cube[36] =	this.cube[38];
		this.cube[38] = this.cube[44];
		this.cube[44] = this.cube[42];
		this.cube[42] = temp;

		temp          = this.cube[37];
		this.cube[37] = this.cube[41];
		this.cube[41] = this.cube[43];
		this.cube[43] = this.cube[39];
		this.cube[39] = temp;
	}

	/* Turn the Left face by 90 degrees                                                    ~~~~~ */
	public void Left1(){
		char temp     = this.cube[0];
		this.cube[0]  = this.cube[44];
		this.cube[44] = this.cube[45];
		this.cube[45] = this.cube[18];
		this.cube[18] = temp;

		temp          = this.cube[3];
		this.cube[3]  = this.cube[41];
		this.cube[41] = this.cube[48];
		this.cube[48] = this.cube[21];
		this.cube[21] = temp;

		temp          = this.cube[6];
		this.cube[6]  = this.cube[38];
		this.cube[38] = this.cube[51];
		this.cube[51] = this.cube[24];
		this.cube[24] = temp;

		temp          = this.cube[9];
		this.cube[9]  = this.cube[15];
		this.cube[15] = this.cube[17];
		this.cube[17] = this.cube[11];
		this.cube[11] = temp;

		temp          = this.cube[10];
		this.cube[10] = this.cube[12];
		this.cube[12] = this.cube[16];
		this.cube[16] = this.cube[14];
		this.cube[14] = temp;
	}

	/* Turn the Left face by 180 degrees                                                   ~~~~~ */
	public void Left2(){
		char temp     = this.cube[0];
		this.cube[0]  = this.cube[45];
		this.cube[45] = temp;

		temp          = this.cube[3];
		this.cube[3]  = this.cube[48];
		this.cube[48] = temp;

		temp          = this.cube[6];
		this.cube[6]  = this.cube[51];
		this.cube[51] = temp;

		temp          = this.cube[44];
		this.cube[44] = this.cube[18];
		this.cube[18] = temp;

		temp          = this.cube[41];
		this.cube[41] = this.cube[21];
		this.cube[21] = temp;

		temp          = this.cube[38];
		this.cube[38] = this.cube[24];
		this.cube[24] = temp;

		temp          = this.cube[9];
		this.cube[9]  = this.cube[17];
		this.cube[17] = temp;

		temp          = this.cube[11];
		this.cube[11] = this.cube[15];
		this.cube[15] = temp;

		temp          = this.cube[10];
		this.cube[10] = this.cube[16];
		this.cube[16] = temp;

		temp          = this.cube[12];
		this.cube[12] = this.cube[14];
		this.cube[14] = temp;
	}

	/* Turn the Left face by 270 degrees                                                   ~~~~~ */
	public void Left3(){
		char temp     = this.cube[0];
		this.cube[0]  = this.cube[18];
		this.cube[18] = this.cube[45];
		this.cube[45] = this.cube[44];
		this.cube[44] = temp;

		temp          = this.cube[3];
		this.cube[3]  = this.cube[21];
		this.cube[21] = this.cube[48];
		this.cube[48] = this.cube[41];
		this.cube[41] = temp;

		temp          = this.cube[6];
		this.cube[6]  = this.cube[24];
		this.cube[24] = this.cube[51];
		this.cube[51] = this.cube[38];
		this.cube[38] = temp;

		temp          = this.cube[9];
		this.cube[9]  = this.cube[11];
		this.cube[11] = this.cube[17];
		this.cube[17] = this.cube[15];
		this.cube[15] = temp;

		temp          = this.cube[10];
		this.cube[10] = this.cube[14];
		this.cube[14] = this.cube[16];
		this.cube[16] = this.cube[12];
		this.cube[12] = temp;
	}

	/* Turn the Right face by 90 degrees                                                   ~~~~~ */
	public void Right1(){
		char temp     = this.cube[2];
		this.cube[2]  = this.cube[20];
		this.cube[20] = this.cube[47];
		this.cube[47] = this.cube[42];
		this.cube[42] = temp;

		temp          = this.cube[5];
		this.cube[5]  = this.cube[23];
		this.cube[23] = this.cube[50];
		this.cube[50] = this.cube[39];
		this.cube[39] = temp;

		temp          = this.cube[8];
		this.cube[8]  = this.cube[26];
		this.cube[26] = this.cube[53];
		this.cube[53] = this.cube[36];
		this.cube[36] = temp;

		temp          = this.cube[27];
		this.cube[27] = this.cube[33];
		this.cube[33] = this.cube[35];
		this.cube[35] = this.cube[29];
		this.cube[29] = temp;

		temp          = this.cube[28];
		this.cube[28] = this.cube[30];
		this.cube[30] = this.cube[34];
		this.cube[34] = this.cube[32];
		this.cube[32] = temp;
	}

	/* Turn the Right face by 180 degrees                                                  ~~~~~ */
	public void Right2(){
		char temp     = this.cube[2];
		this.cube[2]  = this.cube[47];
		this.cube[47] = temp;

		temp          = this.cube[5];
		this.cube[5]  = this.cube[50];
		this.cube[50] = temp;

		temp          = this.cube[8];
		this.cube[8]  = this.cube[53];
		this.cube[53] = temp;

		temp          = this.cube[20];
		this.cube[20] = this.cube[42];
		this.cube[42] = temp;

		temp          = this.cube[23];
		this.cube[23] = this.cube[39];
		this.cube[39] = temp;

		temp          = this.cube[26];
		this.cube[26] = this.cube[36];
		this.cube[36] = temp;

		temp          = this.cube[28];
		this.cube[28] = this.cube[34];
		this.cube[34] = temp;

		temp          = this.cube[32];
		this.cube[32] = this.cube[30];
		this.cube[30] = temp;

		temp          = this.cube[27];
		this.cube[27] = this.cube[35];
		this.cube[35] = temp;

		temp          = this.cube[33];
		this.cube[33] = this.cube[29];
		this.cube[29] = temp;
	}

	/* Turn the Right face by 270 degrees                                                  ~~~~~ */
	public void Right3(){
		char temp     = this.cube[2];
		this.cube[2]  = this.cube[42];
		this.cube[42] = this.cube[47];
		this.cube[47] = this.cube[20];
		this.cube[20] = temp;

		temp          = this.cube[5];
		this.cube[5]  = this.cube[39];
		this.cube[39] = this.cube[50];
		this.cube[50] = this.cube[23];
		this.cube[23] = temp;

		temp          = this.cube[8];
		this.cube[8]  = this.cube[36];
		this.cube[36] = this.cube[53];
		this.cube[53] = this.cube[26];
		this.cube[26] = temp;

		temp          = this.cube[27];
		this.cube[27] = this.cube[29];
		this.cube[29] = this.cube[35];
		this.cube[35] = this.cube[33];
		this.cube[33] = temp;

		temp          = this.cube[28];
		this.cube[28] = this.cube[32];
		this.cube[32] = this.cube[34];
		this.cube[34] = this.cube[30];
		this.cube[30] = temp;
	}

	/* Turn the Top face by 90 degrees                                                     ~~~~~ */
	public void Top1(){
		char temp     = this.cube[9];
		this.cube[9]  = this.cube[18];
		this.cube[18] = this.cube[27];
		this.cube[27] = this.cube[36];
		this.cube[36] = temp;

		temp          = this.cube[10];
		this.cube[10] = this.cube[19];
		this.cube[19] = this.cube[28];
		this.cube[28] = this.cube[37];
		this.cube[37] = temp;

		temp          = this.cube[11];
		this.cube[11] = this.cube[20];
		this.cube[20] = this.cube[29];
		this.cube[29] = this.cube[38];
		this.cube[38] = temp;

		temp          = this.cube[0];
		this.cube[0]  = this.cube[6];
		this.cube[6]  = this.cube[8];
		this.cube[8]  = this.cube[2];
		this.cube[2]  = temp;

		temp          = this.cube[1];
		this.cube[1]  = this.cube[3];
		this.cube[3]  = this.cube[7];
		this.cube[7]  = this.cube[5];
		this.cube[5]  = temp;
	}

	/* Turn the Top face by 180 degrees                                                    ~~~~~ */
	public void Top2(){
		char temp     = this.cube[9];
		this.cube[9]  = this.cube[27];
		this.cube[27] = temp;

		temp          = this.cube[10];
		this.cube[10] = this.cube[28];
		this.cube[28] = temp;

		temp          = this.cube[11];
		this.cube[11] = this.cube[29];
		this.cube[29] = temp;

		temp          = this.cube[18];
		this.cube[18] = this.cube[36];
		this.cube[36] = temp;

		temp          = this.cube[19];
		this.cube[19] = this.cube[37];
		this.cube[37] = temp;

		temp          = this.cube[20];
		this.cube[20] = this.cube[38];
		this.cube[38] = temp;

		temp          = this.cube[1];
		this.cube[1]  = this.cube[7];
		this.cube[7]  = temp;

		temp          = this.cube[3];
		this.cube[3]  = this.cube[5];
		this.cube[5]  = temp;

		temp          = this.cube[0];
		this.cube[0]  = this.cube[8];
		this.cube[8]  = temp;

		temp          = this.cube[2];
		this.cube[2]  = this.cube[6];
		this.cube[6]  = temp;
	}

	/* Turn the Top face by 270 degrees                                                    ~~~~~ */
	public void Top3(){
		char temp     = this.cube[9];
		this.cube[9]  = this.cube[36];
		this.cube[36] = this.cube[27];
		this.cube[27] = this.cube[18];
		this.cube[18] = temp;

		temp          = this.cube[10];
		this.cube[10] = this.cube[37];
		this.cube[37] = this.cube[28];
		this.cube[28] = this.cube[19];
		this.cube[19] = temp;

		temp          = this.cube[11];
		this.cube[11] = this.cube[38];
		this.cube[38] = this.cube[29];
		this.cube[29] = this.cube[20];
		this.cube[20] = temp;

		temp          = this.cube[0];
		this.cube[0]  = this.cube[2];
		this.cube[2]  = this.cube[8];
		this.cube[8]  = this.cube[6];
		this.cube[6]  = temp;

		temp          = this.cube[1];
		this.cube[1]  = this.cube[5];
		this.cube[5]  = this.cube[7];
		this.cube[7]  = this.cube[3];
		this.cube[3]  = temp;
	}

	/* Turn the Bottom face by 90 degrees                                                  ~~~~~ */
	public void Bottom1(){
		char temp     = this.cube[15];
		this.cube[15] = this.cube[42];
		this.cube[42] = this.cube[33];
		this.cube[33] = this.cube[24];
		this.cube[24] = temp;

		temp          = this.cube[16];
		this.cube[16] = this.cube[43];
		this.cube[43] = this.cube[34];
		this.cube[34] = this.cube[25];
		this.cube[25] = temp;

		temp          = this.cube[17];
		this.cube[17] = this.cube[44];
		this.cube[44] = this.cube[35];
		this.cube[35] = this.cube[26];
		this.cube[26] = temp;

		temp          = this.cube[45];
		this.cube[45] = this.cube[51];
		this.cube[51] = this.cube[53];
		this.cube[53] = this.cube[47];
		this.cube[47] = temp;

		temp          = this.cube[46];
		this.cube[46] = this.cube[48];
		this.cube[48] = this.cube[52];
		this.cube[52] = this.cube[50];
		this.cube[50] = temp;
	}

	/* Turn the Bottom face by 180 degrees                                                 ~~~~~ */
	public void Bottom2(){
		char temp     = this.cube[15];
		this.cube[15]  = this.cube[33];
		this.cube[33] = temp;

		temp          = this.cube[16];
		this.cube[16] = this.cube[34];
		this.cube[34] = temp;

		temp          = this.cube[17];
		this.cube[17] = this.cube[35];
		this.cube[35] = temp;

		temp          = this.cube[42];
		this.cube[42] = this.cube[24];
		this.cube[24] = temp;

		temp          = this.cube[43];
		this.cube[43] = this.cube[25];
		this.cube[25] = temp;

		temp          = this.cube[44];
		this.cube[44] = this.cube[26];
		this.cube[26] = temp;


		temp          = this.cube[46];
		this.cube[46] = this.cube[52];
		this.cube[52] = temp;

		temp          = this.cube[48];
		this.cube[48] = this.cube[50];
		this.cube[50] = temp;

		temp          = this.cube[45];
		this.cube[45] = this.cube[53];
		this.cube[53] = temp;

		temp          = this.cube[47];
		this.cube[47] = this.cube[51];
		this.cube[51] = temp;
	}

	/* Turn the Bottom face by 270 degrees                                                 ~~~~~ */
	public void Bottom3(){
		char temp     = this.cube[15];
		this.cube[15] = this.cube[24];
		this.cube[24] = this.cube[33];
		this.cube[33] = this.cube[42];
		this.cube[42] = temp;

		temp          = this.cube[16];
		this.cube[16] = this.cube[25];
		this.cube[25] = this.cube[34];
		this.cube[34] = this.cube[43];
		this.cube[43] = temp;

		temp          = this.cube[17];
		this.cube[17] = this.cube[26];
		this.cube[26] = this.cube[35];
		this.cube[35] = this.cube[44];
		this.cube[44] = temp;

		temp          = this.cube[45];
		this.cube[45] = this.cube[47];
		this.cube[47] = this.cube[53];
		this.cube[53] = this.cube[51];
		this.cube[51] = temp;

		temp          = this.cube[46];
		this.cube[46] = this.cube[50];
		this.cube[50] = this.cube[52];
		this.cube[52] = this.cube[48];
		this.cube[48] = temp;
	}
 
	/* This function sets the cube to the initial solved state                             ~~~~~ */
	private void initializeCube(){
		for(int i = 0; i < 6; i++){ //For each Face
			for(int j = 0; j < 9; j++){ //For each Faclet on this Face
				char faclet_color; //determine this color
				switch(i){
					case 0:
						faclet_color = white;
						break;
					case 1: 
						faclet_color = orange;
						break;
					case 2:
						faclet_color = green;
						break;
					case 3:
						faclet_color = red;
						break;
					case 4:
						faclet_color = blue;
						break;
					case 5:
						faclet_color = yellow;
						break;
					default:
						faclet_color = '?';
						break;
				}
				this.cube[(i*9) + j] = faclet_color; //Store faclet in array
			}
		}
	}

	/* This function displays the cube to standard out                                     ~~~~~ */ 
	public void printCube(){
		System.out.println("");
		for(int i = 0; i < 3; i++){ //Print the white face
			char val1 = this.cube[(i * 3) + 0];
			char val2 = this.cube[(i * 3) + 1];
			char val3 = this.cube[(i * 3) + 2];
			System.out.println("           ["+val1+"]["+val2+"]["+val3+"]");
		}
		System.out.println("");
		for(int i = 0; i < 3; i++){ //Print the middle faces (orange, green, red, blue)
			for(int j = 0; j < 4; j++){
				char val1 = this.cube[9 + (i * 3) + (j*9) + 0];
				char val2 = this.cube[9 + (i * 3) + (j*9) + 1];
				char val3 = this.cube[9 + (i * 3) + (j*9) + 2];
				System.out.print(" ["+val1+"]["+val2+"]["+val3+"]");
			}
			System.out.print("\n");
		}
		System.out.println(""); //Print the white face
		for(int i = 0; i < 3; i++){
			char val1 = this.cube[45 + (i * 3) + 0];
			char val2 = this.cube[45 + (i * 3) + 1];
			char val3 = this.cube[45 + (i * 3) + 2];
			System.out.println("           ["+val1+"]["+val2+"]["+val3+"]");
		}
		System.out.println("");
	}
	
	/* This function returns the color of the faclet at the given index.                   ~~~~~ */
	public char getFacletColor(int faclet_index){
		if (faclet_index >= 0 && faclet_index < 54){
			return this.cube[faclet_index];
		}
		return '?';
	}

	/* This function scrambles the cube.                                                   ~~~~~ */
	public void scramble(){
		int number_of_turns = this.random.nextInt(20) + 20;
		number_of_turns = 20;
		for(int i = 0; i < number_of_turns; i++){
			int turn = this.random.nextInt(17) + 1;
			switch(turn){
				case 1:
					System.out.println("Front1");
					Front1();
					break;
				case 2:
					System.out.println("Front2");
					Front2();
					break;
				case 3:
					System.out.println("Front3");
					Front3();
					break;
				case 4:
					System.out.println("Back1");
					Back1();
					break;
				case 5:
					System.out.println("Back2");
					Back2();
					break;
				case 6:
					System.out.println("Back3");
					Back3();
					break;
				case 7:
					System.out.println("Left1");
					Left1();
					break;
				case 8:
					System.out.println("Left2");
					Left2();
					break;
				case 9:
					System.out.println("Left3");
					Left3();
					break;
				case 10:
					System.out.println("Right1");
					Right1();
					break;
				case 11:
					System.out.println("Right2");
					Right2();
					break;
				case 12:
					System.out.println("Right3");
					Right3();
					break;
				case 13:
					System.out.println("Top1");
					Top1();
					break;
				case 14:
					System.out.println("Top2");
					Top2();
					break;
				case 15:
					System.out.println("Top3");
					Top3();
					break;
				case 16:
					System.out.println("Bottom1");
					Bottom1();
					break;
				case 17:
					System.out.println("Bottom2");
					Bottom2();
					break;
				case 18:
					System.out.println("Bottom3");
					Bottom3();
					break;
				default:
					System.out.println("Error: Rubiks_Cube_V2.scramble()");
					break;
			}
		}
	}



}