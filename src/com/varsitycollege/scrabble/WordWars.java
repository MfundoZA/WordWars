/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.varsitycollege.scrabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The WordWars class is a special class that holds most of the game's
 functionality and logic. Unlike the player class, all of the methods and
 fields in the WordWars class are static. Therefore it's not necessary to
 instantiate a WordWars object.
 *
 * @author Mfundo
 */
public class WordWars {

    //gameState holds the current available characters
    static ArrayList<Character> gameState = new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    static Scanner kb = new Scanner(System.in);
    static int playerNumber = 1; //Number used to track who the current player is

    //Two Player objects for the game
    static Player player1 = new Player();
    static Player player2 = new Player();

    //Called after every turn in order to display the remaining characters
    public static String displayGameState() {
        String remainingCharacters = "";
        System.out.println("Alphabet letters left: ");

        for (int i = 0; i < gameState.size(); ++i) {
            remainingCharacters += gameState.get(i) + " ";
        }

        return remainingCharacters;
    }

    public static String getCurrentPlayerName() {
        String currentPlayerName = "";

        switch (playerNumber) {
            case 1:
                currentPlayerName = player1.getPlayerName();
                break;
            case 2:
                currentPlayerName = player2.getPlayerName();
                break;
        }
        return currentPlayerName;
    }

    public static Player getCurrentPlayerObj() {
        Player currentPlayer;

        if (playerNumber == 1) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }

        return currentPlayer;
    }

    // This method prompts the current player for a valid word
    // until both players agree on the word's validity by entering (y) or (Y)
    public static boolean validateWord(String userWord) {
        String response;
        boolean isWordValid = true;

        System.out.print("Enter \"y\" or \"Y\" if both players agree on the word >>> ");

        response = kb.nextLine().toLowerCase();
        System.out.println();

        if (response.equals("y") == false) {
            System.out.println("An invalid word was entered! Please try again!");
            System.out.println();
            isWordValid = false;
        }
        
        return isWordValid;
    }

    // This method loops through a letter in a given word and searches for the
    // letter in gameState.
    public static boolean isWordAvailable(String userWord) {
        boolean isWordAvaliable = false;
        int counter = 0;
        int i;

        //Labelled loop used to stop searching using a labelled break statement
        outerLoop:
        for (i = 0; i < userWord.length(); i++) {
            for (int j = 0; j < gameState.size(); j++) {

                // If the current letter is found in gameState; increase the counter and move to the next letter
                // of userWord
                if (userWord.charAt(i) == gameState.get(j)) {
                    counter++;
                    break;
                }

                if (j + 1 == gameState.size()) {
                    break outerLoop;
                    // if this block of code executes then that means
                    // that a letter could not be found and the word is not avaliable
                    // the program breaks out of both loops using the outerLoop label
                }
            }
        }

        if (counter < userWord.length()) {
            System.out.println(userWord + " cannot be used as the required letters are not avaliable. Please try again.");
            System.out.println();
        } else if (counter == userWord.length()) {
            isWordAvaliable = true;
        }

        return isWordAvaliable;
    }

    public static void removeUsedLetters(String validWord) {
        boolean letterFound;

        for (int i = 0; i < validWord.length(); i++) { //going through each letter of validWord
            letterFound = false;
            
            // Checking if user the current letter is a vowel.
            // If the letter is a vowel then the if statement is skipped and
            // the next letter is checked. This ensures that vowels are never removed
            // from the game.
            if ("aeiou".indexOf(validWord.charAt(i)) < 0) {
                for (int j = 0; j < gameState.size() && letterFound == false; j++) {
                    if (validWord.charAt(i) == (char) gameState.get(j)) {
                        gameState.remove(j);
                        letterFound = true;
                    }
                }
            }
        }
    }

    public static void giveCurrentPlayerPoints(String userWord) {
        int points = userWord.length();

        getCurrentPlayerObj().setScore(points);
    }

    public static void nextTurn() {
        if (playerNumber == 1) {
            playerNumber++;
        } else {

            playerNumber--;
        }

    }

    public static void declareWinner() {
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();

        if (player1Score > player2Score) {
            System.out.println(player1.getPlayerName() + " is the winner with a score of " + player1.getScore() + "!");
        } else if (player1Score < player2Score) {
            System.out.println(player1.getPlayerName() + " is the winner with a score of " + player2.getScore() + "!");
        } else {
            System.out.println("Looks like it's a tie of " + player1.getScore() + " points! Better luck next time!");
        }
        
        // gameState is reinistalized in case the players want to play again
        gameState = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    }

    //This method prints the name of the game
    public static void displayGameBanner() {
        System.out.println("          _____                   _______                   _____                    _____                            _____                    _____                    _____                    _____          ");
        System.out.println("         /\\    \\                 /::\\    \\                 /\\    \\                  /\\    \\                          /\\    \\                  /\\    \\                  /\\    \\                  /\\    \\         ");
        System.out.println("        /::\\____\\               /::::\\    \\               /::\\    \\                /::\\    \\                        /::\\____\\                /::\\    \\                /::\\    \\                /::\\    \\        ");
        System.out.println("       /:::/    /              /::::::\\    \\             /::::\\    \\              /::::\\    \\                      /:::/    /               /::::\\    \\              /::::\\    \\              /::::\\    \\       ");
        System.out.println("      /:::/   _/___           /::::::::\\    \\           /::::::\\    \\            /::::::\\    \\                    /:::/   _/___            /::::::\\    \\            /::::::\\    \\            /::::::\\    \\      ");
        System.out.println("     /:::/   /\\    \\         /:::/~~\\:::\\    \\         /:::/\\:::\\    \\          /:::/\\:::\\    \\                  /:::/   /\\    \\          /:::/\\:::\\    \\          /:::/\\:::\\    \\          /:::/\\:::\\    \\     ");
        System.out.println("    /:::/   /::\\____\\       /:::/    \\:::\\    \\       /:::/__\\:::\\    \\        /:::/  \\:::\\    \\                /:::/   /::\\____\\        /:::/__\\:::\\    \\        /:::/__\\:::\\    \\        /:::/__\\:::\\    \\    ");
        System.out.println("   /:::/   /:::/    /      /:::/    / \\:::\\    \\     /::::\\   \\:::\\    \\      /:::/    \\:::\\    \\              /:::/   /:::/    /       /::::\\   \\:::\\    \\      /::::\\   \\:::\\    \\       \\:::\\   \\:::\\    \\   ");
        System.out.println("  /:::/   /:::/   _/___   /:::/____/   \\:::\\____\\   /::::::\\   \\:::\\    \\    /:::/    / \\:::\\    \\            /:::/   /:::/   _/___    /::::::\\   \\:::\\    \\    /::::::\\   \\:::\\    \\    ___\\:::\\   \\:::\\    \\  ");
        System.out.println(" /:::/___/:::/   /\\    \\ |:::|    |     |:::|    | /:::/\\:::\\   \\:::\\____\\  /:::/    /   \\:::\\ ___\\          /:::/___/:::/   /\\    \\  /:::/\\:::\\   \\:::\\    \\  /:::/\\:::\\   \\:::\\____\\  /\\   \\:::\\   \\:::\\    \\ ");
        System.out.println("|:::|   /:::/   /::\\____\\|:::|____|     |:::|    |/:::/  \\:::\\   \\:::|    |/:::/____/     \\:::|    |        |:::|   /:::/   /::\\____\\/:::/  \\:::\\   \\:::\\____\\/:::/  \\:::\\   \\:::|    |/::\\   \\:::\\   \\:::\\____\\");
        System.out.println("|:::|__/:::/   /:::/    / \\:::\\    \\   /:::/    / \\::/   |::::\\  /:::|____|\\:::\\    \\     /:::|____|        |:::|__/:::/   /:::/    /\\::/    \\:::\\  /:::/    /\\::/   |::::\\  /:::|____|\\:::\\   \\:::\\   \\::/    /");
        System.out.println(" \\:::\\/:::/   /:::/    /   \\:::\\    \\ /:::/    /   \\/____|:::::\\/:::/    /  \\:::\\    \\   /:::/    /          \\:::\\/:::/   /:::/    /  \\/____/ \\:::\\/:::/    /  \\/____|:::::\\/:::/    /  \\:::\\   \\:::\\   \\/____/ ");
        System.out.println("  \\::::::/   /:::/    /     \\:::\\    /:::/    /          |:::::::::/    /    \\:::\\    \\ /:::/    /            \\::::::/   /:::/    /            \\::::::/    /         |:::::::::/    /    \\:::\\   \\:::\\    \\     ");
        System.out.println("   \\::::/___/:::/    /       \\:::\\__/:::/    /           |::|\\::::/    /      \\:::\\    /:::/    /              \\::::/___/:::/    /              \\::::/    /          |::|\\::::/    /      \\:::\\   \\:::\\____\\    ");
        System.out.println("    \\:::\\__/:::/    /         \\::::::::/    /            |::| \\::/____/        \\:::\\  /:::/    /                \\:::\\__/:::/    /               /:::/    /           |::| \\::/____/        \\:::\\  /:::/    /    ");
        System.out.println("     \\::::::::/    /           \\::::::/    /             |::|  ~|               \\:::\\/:::/    /                  \\::::::::/    /               /:::/    /            |::|  ~|               \\:::\\/:::/    /     ");
        System.out.println("      \\::::::/    /             \\::::/    /              |::|   |                \\::::::/    /                    \\::::::/    /               /:::/    /             |::|   |                \\::::::/    /      ");
        System.out.println("       \\::::/    /               \\::/____/               \\::|   |                 \\::::/    /                      \\::::/    /               /:::/    /              \\::|   |                 \\::::/    /       ");
        System.out.println("        \\::/____/                 ~~                      \\:|   |                  \\::/____/                        \\::/____/                \\::/    /                \\:|   |                  \\::/    /        ");
        System.out.println("         ~~                                                \\|___|                   ~~                               ~~                       \\/____/                  \\|___|                   \\/____/         ");
        System.out.println();
        System.out.println();

    }
    /*
              _____                   _______                   _____                    _____                            _____                    _____                    _____                    _____          
             /\    \                 /::\    \                 /\    \                  /\    \                          /\    \                  /\    \                  /\    \                  /\    \         
            /::\____\               /::::\    \               /::\    \                /::\    \                        /::\____\                /::\    \                /::\    \                /::\    \        
           /:::/    /              /::::::\    \             /::::\    \              /::::\    \                      /:::/    /               /::::\    \              /::::\    \              /::::\    \       
          /:::/   _/___           /::::::::\    \           /::::::\    \            /::::::\    \                    /:::/   _/___            /::::::\    \            /::::::\    \            /::::::\    \      
         /:::/   /\    \         /:::/~~\:::\    \         /:::/\:::\    \          /:::/\:::\    \                  /:::/   /\    \          /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
        /:::/   /::\____\       /:::/    \:::\    \       /:::/__\:::\    \        /:::/  \:::\    \                /:::/   /::\____\        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
       /:::/   /:::/    /      /:::/    / \:::\    \     /::::\   \:::\    \      /:::/    \:::\    \              /:::/   /:::/    /       /::::\   \:::\    \      /::::\   \:::\    \       \:::\   \:::\    \   
      /:::/   /:::/   _/___   /:::/____/   \:::\____\   /::::::\   \:::\    \    /:::/    / \:::\    \            /:::/   /:::/   _/___    /::::::\   \:::\    \    /::::::\   \:::\    \    ___\:::\   \:::\    \  
     /:::/___/:::/   /\    \ |:::|    |     |:::|    | /:::/\:::\   \:::\____\  /:::/    /   \:::\ ___\          /:::/___/:::/   /\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\____\  /\   \:::\   \:::\    \ 
    |:::|   /:::/   /::\____\|:::|____|     |:::|    |/:::/  \:::\   \:::|    |/:::/____/     \:::|    |        |:::|   /:::/   /::\____\/:::/  \:::\   \:::\____\/:::/  \:::\   \:::|    |/::\   \:::\   \:::\____\
    |:::|__/:::/   /:::/    / \:::\    \   /:::/    / \::/   |::::\  /:::|____|\:::\    \     /:::|____|        |:::|__/:::/   /:::/    /\::/    \:::\  /:::/    /\::/   |::::\  /:::|____|\:::\   \:::\   \::/    /
    \:::\/:::/   /:::/    /   \:::\    \ /:::/    /   \/____|:::::\/:::/    /  \:::\    \   /:::/    /          \:::\/:::/   /:::/    /  \/____/ \:::\/:::/    /  \/____|:::::\/:::/    /  \:::\   \:::\   \/____/ 
     \::::::/   /:::/    /     \:::\    /:::/    /          |:::::::::/    /    \:::\    \ /:::/    /            \::::::/   /:::/    /            \::::::/    /         |:::::::::/    /    \:::\   \:::\    \     
      \::::/___/:::/    /       \:::\__/:::/    /           |::|\::::/    /      \:::\    /:::/    /              \::::/___/:::/    /              \::::/    /          |::|\::::/    /      \:::\   \:::\____\    
       \:::\__/:::/    /         \::::::::/    /            |::| \::/____/        \:::\  /:::/    /                \:::\__/:::/    /               /:::/    /           |::| \::/____/        \:::\  /:::/    /    
        \::::::::/    /           \::::::/    /             |::|  ~|               \:::\/:::/    /                  \::::::::/    /               /:::/    /            |::|  ~|               \:::\/:::/    /     
         \::::::/    /             \::::/    /              |::|   |                \::::::/    /                    \::::::/    /               /:::/    /             |::|   |                \::::::/    /      
          \::::/    /               \::/____/               \::|   |                 \::::/    /                      \::::/    /               /:::/    /              \::|   |                 \::::/    /       
           \::/____/                 ~~                      \:|   |                  \::/____/                        \::/____/                \::/    /                \:|   |                  \::/    /        
            ~~                                                \|___|                   ~~                               ~~                       \/____/                  \|___|                   \/____/         
     */

}
