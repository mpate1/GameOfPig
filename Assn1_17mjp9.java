package assignment1CISC124;
// Mudra Patel
// student #: 20117291 netId: 17mjp9
// In this game, a human player will be paired with a computer player.
// Both players will be rolling two dies until one of them reaches a total score of 100.


import java.util.*;
import java.util.Scanner;
public class Assn1_17mjp9 {

	static final Scanner scan = new Scanner(System.in);
	// a while loop is created within this method as the turns keep occurring until one of the players reaches the score of 100 or more.
		// once this while loop breaks out, the console prints out who the winner is based on conditions that were passed.
	// this is the main method where the humanTotalPoints, computerTotalPoints, and turnCount are declared
	// the turnCount is used to determine whose turn will be in the game.
	// Once a players turn is over, a counter is added so that the turnCount can be incremented by 1.
		// if the number is divisible by 2 and has a remainder of 0, it's the human's turn, if this condition is not met, it is the computer's turn
	// this method keeps track of the players most current score
		// this is done through calling the method doHumanTurn and doComputerTurn


	public static void main(String[]args) {

		int humanTotalPoints = 0;
		int computerTotalPoints = 0;	
		System.out.println("Welcome to the Game of Pig! You will be playing this game with the computer. Let's get started!");
		int turnCount = 0;

		while(humanTotalPoints < 100 && computerTotalPoints < 100) {
			if (turnCount % 2 == 0) {
				System.out.println("Human's turn to roll the dice.");
				humanTotalPoints = doHumanTurn(humanTotalPoints);
			}else {
				System.out.println("Computer's turn to roll the dice.");
				computerTotalPoints = doComputerTurn(computerTotalPoints); 
			}	
			System.out.println("Human Score: " + humanTotalPoints + " Computer Score: " + computerTotalPoints);
			turnCount++;
			System.out.println();

		}if (humanTotalPoints > 100) {
			System.out.println("Human wins the game!");
		}else {
			System.out.println("Computer wins the game!");
		}

	}
	// this method essentially converts the integer rolled into a word. 
	// ie. 1 turns into one
	public static String getWord(int dice) {
		if (dice == 1) {
			return "one";
		}else if (dice == 2) {
			return "two";
		}else if (dice == 3) {
			return "three";
		}else if (dice == 4) {
			return "four";
		}else if (dice == 5) {
			return "five";
		}else {
			return "six";
		}
	}
	// this method takes in the numbers rolled from the dies and completes the calculations and returns the turnScore if the player chose to roll again
	// the two numbers rolled from the dies and the turnScore from their current turn gets passed as a parameter
	public static int getTurnScore(int dice1, int dice2, int turnScore) {
		System.out.println("Rolled " + getWord(dice1) +" and " + getWord(dice2));
		if (dice1 == 6 && dice2 == 6) {
			return -1; // negative = lose all points
		} else if (dice1 == 6 || dice2 == 6) {
			return 0; // 0 = lose turn points
		} else if (dice1 == dice2 ) {
			return turnScore + (dice1+dice2)*2;
		} else {
			return turnScore + dice1+dice2;
		}
	}
	// this method takes in user input for if the want to roll again or hold their turn. 
	// no parameters are passed through the function
	// Scanner is used in order to scan and read the user input.
	public static boolean playerRollAgain() {
		System.out.println("Enter 'r' to roll or 'h' to hold: "); 
		String input = scan.nextLine();
		if (input.equals("r")) {
			return true;
		}else if (input.equals("h")) {
			return false;
		}else {
			return playerRollAgain(); // recursion is used if the player enters something besides an "r" or an "h"
		}
	}
	// this method was created so the computer is able to have the option the roll again or hold.
	// however, this method forces the computer to hold their turn.
	public static boolean computerRollAgain(int turnScore) {
		return false;  // Computer can only roll once
	}

	// this method returns new total score for the human
	// it takes in the totalScore as a parameter and rolls the dice for the player
	// once random numbers are rolled, this method adds the turn points to the totalScore that was passed in and returns the new total
	// a while loop is created so that if the player does choose to/ have to roll again, this function can continue until the loop breaks out
	public static int doHumanTurn(int totalScore) {
		int turnScore = 0;
		while (true) {
			int dice1 = (int)(Math.random()*6)+1;
			int dice2 = (int)(Math.random()*6)+1;
			int newScore = getTurnScore(dice1, dice2, turnScore);
			if (newScore < 0) { 
				return 0; // new total score is 0 if the player were to roll double sixes.
			} else if (newScore == 0) { // if the players roll one 6, their total score remains the same
				return totalScore;
			} else {
				turnScore = newScore;
			}
			// now check if roll again

			System.out.println("Turn score is: " + turnScore);
			System.out.println("Total score is: " + (turnScore + totalScore));
			if (dice1 == dice2 || playerRollAgain()) {
				continue;
			}
			return turnScore + totalScore;	

		}
	}
	// this method returns new total score for the computer
	// it takes in the totalScore as a parameter and rolls the dice for the player
	// once random numbers are rolled, this method adds the turn points to the totalScore that was passed in and returns the new total
	public static int doComputerTurn(int totalScore) {
		int turnScore = 0;
		while (true) {
			int dice1 = (int)(Math.random()*6)+1;
			int dice2 = (int)(Math.random()*6)+1;
			int newScore = getTurnScore(dice1, dice2, turnScore);
			if (newScore < 0) {
				return 0;
			} else if (newScore == 0) {
				return totalScore;
			} else {
				turnScore = newScore;
			}
			// now check if roll again

			System.out.println("Turn score is: " + turnScore);
			System.out.println("Total score is: " + (turnScore + totalScore));
			if (dice1 == dice2 || computerRollAgain(turnScore)) {
				continue;
			}
			return turnScore + totalScore;

		}
	}
}	

