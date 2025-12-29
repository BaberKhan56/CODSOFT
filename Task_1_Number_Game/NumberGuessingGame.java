// Import required classes for input and random number generation
import java.util.Random;
import java.util.Scanner;

// Main class - file name must be NumberGuessingGame.java
public class NumberGuessingGame {

    // Main method - program execution starts here
    public static void main(String[] args) {

        // Scanner object to take input from user
        Scanner sc = new Scanner(System.in);

        // Random object to generate random numbers
        Random random = new Random();

        // Variables to track total rounds and rounds won
        int totalRounds = 0;
        int roundsWon = 0;

        // Variable to store user's choice for replaying the game
        char playAgain;

        // Welcome message
        System.out.println(" WELCOME TO NUMBER GUESSING GAME ");

        // Loop to allow multiple rounds
        do {
            totalRounds++; // Increment round count

            // Generate a random number between 1 and 100
            int numberToGuess = random.nextInt(100) + 1;

            // Maximum number of attempts allowed
            int maxAttempts = 7;

            // Counter for number of attempts used
            int attempts = 0;

            // Flag to check if user guessed correctly
            boolean guessedCorrectly = false;

            // Display round information
            System.out.println("\nRound " + totalRounds);
            System.out.println("I have generated a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            // Loop for guessing attempts
            while (attempts < maxAttempts) {

                // Prompt user for their guess
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();

                // Increase attempt count
                attempts++;

                // Check if guess is correct
                if (userGuess == numberToGuess) {
                    System.out.println(" Correct! You guessed the number in " 
                                       + attempts + " attempts.");
                    guessedCorrectly = true;
                    roundsWon++; // Increase win count
                    break; // Exit the guessing loop
                }
                // If guess is higher than the number
                else if (userGuess > numberToGuess) {
                    System.out.println(" Too High!");
                }
                // If guess is lower than the number
                else {
                    System.out.println(" Too Low!");
                }

                // Display remaining attempts
                System.out.println("Attempts left: " 
                                   + (maxAttempts - attempts));
            }

            // If user failed to guess the number
            if (!guessedCorrectly) {
                System.out.println(" You've used all attempts!");
                System.out.println("The correct number was: " 
                                   + numberToGuess);
            }

            // Ask user if they want to play another round
            System.out.print("\nDo you want to play another round? (y/n): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'y' || playAgain == 'Y'); // Continue if user chooses yes

        // Display final game statistics
        System.out.println("\n GAME OVER ");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Rounds Lost: " 
                           + (totalRounds - roundsWon));

        // Thank the user
        System.out.println("Thank you for playing!");

        // Close scanner resource
        sc.close();
    }
}
