import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        // set Scanner to myObj
        Scanner myObj = new Scanner(System.in);
        //create a list to store drawn cards in
        List<Integer> drawnCards = new ArrayList<Integer>();

        // Set up the beginning of the game. Display rules
        System.out.println("Welcome to Black Jack!");
        System.out.println("The rules are simple! You will be handed to cards. So will the dealer.");
        System.out.println("You will only see one of the dealer's cards, they will see all of yours.");
        System.out.println("Your score is the total from adding your cards up.");
        System.out.println("The goal is for you to have a higher number than the dealer.");
        System.out.println("You can request more cards, but careful not to go over 21!");
        System.out.println("The closest without going over wins!");
        System.out.println("If you get an ace, it will either count as 11 or 1, whichever is better for you.");

        // draw two cards for the user. THESE STILL NEED TO BE STORE IN VARIABLES FOR FUTURE USE
        System.out.println("Your first card is "+drawCard(drawnCards));
        System.out.println("Your second card is "+drawCard(drawnCards));
        /*
            CARDS WILL NEED TO BE SUMMED HERE AFTER CONVERTED TO FACE
         */

        // draw two cards for the dealer, only display one. THESE STILL NEED TO BE STORE IN VARIABLES FOR FUTURE USE
        System.out.println("The dealer has a score of "+drawCard(drawnCards)+" with another card still hidden");
        int dealerCard2 = drawCard(drawnCards);

        }
        /*the draw card method will be called anytime a new card should be added to the game. it will be a
        self-contained way to generate a random number from 1-51, check if that number has been drawn, re-draw
        if it has, and convert that number to a face card. it will then return the face card.
         */
        public static int drawCard(List<Integer> drawnCards) {
            int [] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}; // create arrays of cards
            int min = 0;
            int max = 51;
            int arraySelectCard = cards[(int)Math.floor(Math.random()*(max-min+1)+min)];
            // check if current random card has already been drawn. re-draw if yes
            if (drawnCards.contains(arraySelectCard)) {
                System.out.println("This happened");
                drawCard(drawnCards);
            }
            else {
                drawnCards.add(arraySelectCard); // add current selected card to drawnCards List to prevent drawing again
                System.out.println("Drawn cards are " + drawnCards); //test line to debug dupes
            }
            return arraySelectCard; //return validated card for display to user

        }

    }



        // draw and display two cards to user
        // draw two cards and display one for dealer
        // sum both sets of cards and show scores
        // offer user the option to hit or stay
        // draw a single card for user each time they select hit
        // sum user's cards, check if they've gone over, if yes, check if there's an ace, if yes convert to a 1
        // check if user has exactly 21, switch to dealer
        // have dealer auto-draw cards until they exceed user score. if dealer goes over 21 they lose
        // offer to play again


