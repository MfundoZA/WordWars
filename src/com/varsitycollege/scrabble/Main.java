/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.varsitycollege.scrabble;

import java.util.Scanner;


/**
 * The Main class contains this project's main method, its responsible for
 using the player objects and WordWars methods.
 * 
 * @author Mfundo
 */
public class Main {
    static Scanner kb = new Scanner(System.in);
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WordWars.displayGameBanner();
        
        System.out.println("Welcome to the WORD WARS game");
        System.out.println();
        System.out.println("Press (1) to start the game");
        System.out.println();
        System.out.println("Press any other key to exit the game");
        System.out.print("Enter your selection: ");
        
        String selection = kb.nextLine();
        
        //Loops the entire game. 
        while (selection.equals("1")) {
            startGame();
            
            System.out.println("Would you like to play again? Enter (1)");
            System.out.println("Any other key will close the game");
            System.out.println("******************************************");
            System.out.print("Play again? >>> ");
            selection = kb.nextLine() ;
        }   
    }
 
    
    public static void startGame() {
        String playerName;
        String currentPlayerName;
        String userWord = "";
        
        System.out.println("***********************");
        System.out.print("Enter player 1 name: ");
        playerName = kb.nextLine();
        WordWars.player1.setPlayerName(playerName);
        
        
        System.out.print("Enter player 2 name: ");
        playerName = kb.nextLine();
        WordWars.player2.setPlayerName(playerName);
        System.out.println();
        
        System.out.println("LETS PLAY WORD WARS!!!");
        System.out.println();
        
        //The players can continue to adds words until they type "???"
        while (userWord.equals("???") == false) {
            currentPlayerName = WordWars.getCurrentPlayerName();
            
            System.out.println(WordWars.displayGameState());
            System.out.println();
            
            System.out.print(currentPlayerName + " enter your word: ");
            userWord = kb.nextLine().toLowerCase();
            
            if (userWord.equals("???") == false) {
                
                // If the current player enter a word that cannot be used
                // then the continue statement is used to go back to the 
                // start of the while loop and reprompt the player for another word
                if (WordWars.isWordAvailable(userWord) == false) {
                    continue;
                }
                
                // Works in the same way as the if statment above
                // however this statement checks if both players 
                // agree the the entered word is a real word
                if (WordWars.validateWord(userWord) == false) {
                    continue;
                }
                
                WordWars.giveCurrentPlayerPoints(userWord);
                WordWars.removeUsedLetters(userWord);
                WordWars.nextTurn();
            }
        }
        
        WordWars.declareWinner();    
    }
}
