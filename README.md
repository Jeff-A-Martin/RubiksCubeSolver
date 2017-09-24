# Goal Oriented Rubik's Cube Solver with IDA*
## Jeffrey Martin
### CS 4341 Introduction to Artificial Intelligence
### Professor Neil Heffernan
### October 24th, 2016
### Project to be completed for BSMS credit.

This project solves a 3x3 rubiks cube using sub goal decomposition and IDA* search. 

It can be run by navigating to the bin directory and typing the following command
```bash
java Main [command line arguments]
```

The command line arguments are as follows:
```bash
-u : Allow the user to specify which cube state to solve 
     (otherwise a randomly scrambled cube will be solved)

-b : Decompose the goals according to the beginners method

-r : Decompose the goals randomly (one piece at a time)

-p : Decompose the goals according to the parallel piece placement method

-a : IDA* search will use an admissable heruisitic. (default is no)

-v : The program will print reports to the commmand line. (default is no);
```
*NOTE* If no decomposition argument (-b, -r, -p) is provided, the program will default to the 
       Improved Decomposition Method.

Examples:
```bash
java Main -v -a -u -b (run the program with verbosity, an admissable heruisitic, with a user
                       specified cube, and using the beginners method decomposition)

java Main             (run the program without admissability or verbosity, using a randomly
                       scrambled cube and the improved goal decomposition)
```

The program can be built by navigating to the source folder and running 'make'

The report for this project is available [here](report.pdf)
