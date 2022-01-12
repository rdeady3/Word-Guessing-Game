//Russell Deady
//A game I created where the user can try to guess a random word
//1/9/22

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

public class wordGuessGame implements ActionListener {

   //member data of class, made private
   private static JFrame frame;
   private static JPanel panel;
   
   private static JLabel label;
   private static JLabel label2;
   private static JLabel label3;
   private static JLabel result;
   private static JLabel progressLabel;
   private static JLabel incorrectLettersLabel;
   private static JLabel attemptLabel;
   private static JLabel hintLabel;
   private static JTextField userText;
   
   private static JButton guessLetterButton;
   private static JButton guessWordButton;
   private static JButton restartButton;
   
   private static String secretWord;
   private static String[] wordBank = 
                        {"bees", "bored", "dogs",
                        "dumbbell", "empty", "jazz", 
                        "game", "garbage", "guitar",
                        "round", "scale", "soccer"};
   private static String[] hintBank = 
                        {"they make honey", "not interested", "man's best friend",
                        "lift these in the gym", "containing nothing", "type of music",
                        "something you play", "what you throw out", "instrument you strum",
                        "no edges", "measures weight", "game where you kick a ball"};
   
   private static int attempt = 0;
   
   private static String input = "";
   private static String progress = "";
   private static String incorrectLetters = "";
   private static String hint = "";
   private static String guess = "";
   
   private static int randomNumber;
   private static int length;
   private static int guessesLeft = 10;
   
   public static void main(String[] args){
   
      //creating a Scanner object
      Scanner scan = new Scanner(System.in);
      //creating a random object
      Random rand = new Random();
      
      //setting the random word
      randomNumber = rand.nextInt(wordBank.length);
      secretWord = wordBank[randomNumber];
      length = secretWord.length();
      
      //setting the corresponding hint
      hint = hintBank[randomNumber];
    
      //creating a new frame and a new panel
      frame = new JFrame();
      //JPanel panel = new JPanel();
      panel = new JPanel();
      
      //setting up the frame
      frame.setSize(500,500);
      frame.setTitle("Word Guessing Game!");
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(panel);
      frame.setResizable(false);
      
      panel.setLayout(null);
      
      //setting position of the layout
      label = new JLabel("Can you figure out the word?"); 
      label2 = new JLabel("You can guess 10 letters and try guessing the word 1 time.");
      label3 = new JLabel("The word is " + length + " letters long.");
      label.setBounds(20, 20, 450, 100);
      label2.setBounds(20, 50, 450, 100);
      label3.setBounds(20, 80, 450, 100);
      panel.add(label);
      panel.add(label2);
      panel.add(label3);
      
      //setting up the attempt label
      attemptLabel = new JLabel("You have 10 guesses left"); 
      attemptLabel.setBounds(15, 400, 250, 100);
      panel.add(attemptLabel);
      
      //setting up the incorrect attempt label
      incorrectLettersLabel = new JLabel("Incorrect Letters: ");
      incorrectLettersLabel.setBounds(270, 400, 250, 100);
      panel.add(incorrectLettersLabel);
      
      //setting up the hint label 
      hintLabel = new JLabel("Hint: " + hint);
      hintLabel.setBounds(275, 110, 450, 100);
      panel.add(hintLabel);
      
      //setting up where user will type in their guess
      userText = new JTextField(20);
      userText.setBounds(150, 205, 160, 25);
      panel.add(userText);
   
      //setting up the button to guess a letter
      guessLetterButton = new JButton("Guess Letter");
      guessLetterButton.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 10, 100, 100);
      guessLetterButton.addActionListener(new wordGuessGame());
      panel.add(guessLetterButton);
      
      //setting up the play again button
      restartButton = new JButton("Restart");
      restartButton.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() / 2 + 90, 100, 100);
      restartButton.addActionListener(new wordGuessGame());
      panel.add(restartButton);
      
      //setting up the button to guess the word
      guessWordButton = new JButton("Guess Word");
      guessWordButton.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 - 10, 100, 100);
      guessWordButton.addActionListener(new wordGuessGame());
      panel.add(guessWordButton);
      
      result = new JLabel();
      result.setBounds(20, 110, 300, 100);
      panel.add(result);
      
      progressLabel = new JLabel();
      progressLabel.setBounds(20, 140, 300, 100);
      panel.add(progressLabel);
      
      frame.setVisible(true);
   
      return;
   }
   
   public void actionPerformed(ActionEvent e) {
      
      Random rand = new Random();
      
      //if play again button is pressed, reset everything
      if (e.getSource() == restartButton) {
         randomNumber = rand.nextInt(wordBank.length);
         secretWord = wordBank[randomNumber];
         hint = hintBank[randomNumber];
         length = secretWord.length();
         attempt = 0;
         guessesLeft = 10;
         progress = "";
         incorrectLetters = "";
         result.setText("");
         progressLabel.setText("");
         hintLabel.setText("Hint: " + hint);
         incorrectLettersLabel.setText("Incorrect Letters: ");
         attemptLabel.setText("You have 10 guesses left");
         label3.setText("The word is " + length + " letters long.");
         userText.setEditable(true);
         panel.repaint();
         
      }
      else if (e.getSource() == guessLetterButton) {
         
         //making sure the user has enough guesses left
         if (attempt == 10){
            return;
         }
         
         //gathering input from the textfield 
         input = userText.getText();                  
         
         //checks if the inputted letter is in the word or not
         if (secretWord.indexOf(input) != -1){
            result.setText("That letter is in the word at position " + (secretWord.indexOf(input) + 1));
            if (progress.indexOf(input) != -1){
               attempt = attempt + 1;
               if (attempt == 10){
                  result.setText("You are out of letter guesses!");
                  attemptLabel.setText("You have to guess the word now!");
               }
               else {
                  guessesLeft = 10 - attempt;
                  attemptLabel.setText("You have " + guessesLeft + " guesses left.");
               }
               return;
            }
            else {
               progress = progress + input;
            }
            progressLabel.setText("The letters you have are " + progress);
         }
         //else statement adds incorrect letters to the incorrectLetters string
         else {
            if (incorrectLetters.indexOf(input.charAt(0)) == -1){
               incorrectLetters = incorrectLetters + input.charAt(0);
            }
            result.setText("That letter is not in the word");
            incorrectLettersLabel.setText("Incorrect Letters: " + incorrectLetters);
         }
         
         attempt = attempt + 1;
         
         if (attempt == 10){
            result.setText("You are out of letter guesses!");
            attemptLabel.setText("You have to guess the word now!");
         }
         else {
            guessesLeft = 10 - attempt;
            attemptLabel.setText("You have " + guessesLeft + " guesses left.");
         }
         
      } 
      else if (e.getSource() == guessWordButton){
         //collects the guess the user inputted
         guess = userText.getText();
         //compares the users guess with the actual word
         if (secretWord.equals(guess)){
            result.setText("You got the word, you win!");
            progressLabel.setText("");
            userText.setEditable(false);
         }
         else {
            result.setText("You word was " + secretWord + ", you lose!");
            progressLabel.setText("");
            userText.setEditable(false);
         }
      }
   }

}
