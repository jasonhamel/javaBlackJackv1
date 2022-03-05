import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        // set Scanner to myObj
        Scanner myObj = new Scanner(System.in);
        //create a list to store drawn cards in
        List<Integer> drawnCards = new ArrayList<Integer>();
        //create a list to store player score
        List<Integer> playerCards = new ArrayList<Integer>();
        //create a list to store dealer score
        List<Integer> dealerCards = new ArrayList<Integer>();


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
        playerCards.add(drawCard(drawnCards));
        playerCards.add(drawCard(drawnCards));
        System.out.println(playerCards); //debug collecting card numbers/values to sum cards for score
        /*
            CARDS WILL NEED TO BE SUMMED HERE AFTER CONVERTED TO FACE
         */

        // draw two cards for the dealer, only display one. THESE STILL NEED TO BE STORE IN VARIABLES FOR FUTURE USE
        dealerCards.add(drawCard(drawnCards));
        dealerCards.add(drawCard(drawnCards));
        System.out.println("The dealer has a score of "+dealerCards.get(0) +" with another card still hidden");
        System.out.println(dealerCards); //debug collecting dealer card numbers/values to sum for score

        }
        /*the draw card method will be called anytime a new card should be added to the game. it will be a
        self-contained way to generate a random number from 1-51, check if that number has been drawn, re-draw
        if it has, and convert that number to a face card. it will then return the face card.
         */
        public static int drawCard(List<Integer> drawnCards) {
            int [] cards = {0, 1, 2, 3};//, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}; // create arrays of cards
            int min = 0;
            int max = 3;
            int reDraw = 0;
            int arraySelectCard = cards[(int)Math.floor(Math.random()*(max-min+1)+min)];
            // check if current random card has already been drawn. re-draw if yes
            if (drawnCards.contains(arraySelectCard)) {
                System.out.println("This happened"); //test line to debug dupes
                reDraw = drawCard(drawnCards);
            }
            else {
                drawnCards.add(arraySelectCard); // add current selected card to drawnCards List to prevent drawing again
               // System.out.println("Drawn cards are " + drawnCards); //test line to debug dupes
                int convertToFace = arraySelectCard;
                createFace(convertToFace);
                return arraySelectCard;//return validated card for display to user

            }

            return reDraw;

        }
        public static String createFace(int convertToFace) {
            String nameOfCard = "";
            if (convertToFace == 0) {
                nameOfCard = "Ace of Hearts";
            }
            else if (convertToFace == 1) {
                nameOfCard = "2 of Hearts";
            }
            else if (convertToFace == 2) {
                nameOfCard = "3 of Hearts";
            }
            else if (convertToFace == 3) {
                nameOfCard = "4 of Hearts";
            }
            else if (convertToFace == 4) {
                nameOfCard = "5 of Hearts";
            }
            else if (convertToFace == 5) {
                nameOfCard = "6 of Hearts";
            }
            else if (convertToFace == 6) {
                nameOfCard = "7 of Hearts";
            }
            else if (convertToFace == 7) {
                nameOfCard = "8 of Hearts";
            }
            else if (convertToFace == 8) {
                nameOfCard = "9 of Hearts";
            }
            else if (convertToFace == 9) {
                nameOfCard = "10 of Hearts";
            }
            else if (convertToFace == 10) {
                nameOfCard = "Jack of Hearts";
            }
            else if (convertToFace == 11) {
                nameOfCard = "Queen of Hearts";
            }
            else if (convertToFace == 12) {
                nameOfCard = "King of Hearts";
            }
            else if (convertToFace == 13) {
                nameOfCard = "Ace of Diamonds";
            }
            else if (convertToFace == 14) {
                nameOfCard = "2 of Diamonds";
            }
            else if (convertToFace == 15) {
                nameOfCard = "3 of Diamonds";
            }
            else if (convertToFace == 16) {
                nameOfCard = "4 of Diamonds";
            }
            else if (convertToFace == 17) {
                nameOfCard = "5 of Diamonds";
            }
            else if (convertToFace == 18) {
                nameOfCard = "6 of Diamonds";
            }
            else if (convertToFace == 19) {
                nameOfCard = "7 of Diamonds";
            }
            else if (convertToFace == 20) {
                nameOfCard = "8 of Diamonds";
            }
            else if (convertToFace == 21) {
                nameOfCard = "9 of Diamonds";
            }
            else if (convertToFace == 22) {
                nameOfCard = "10 of Diamonds";
            }
            else if (convertToFace == 23) {
                nameOfCard = "Jack of Diamonds";
            }
            else if (convertToFace == 24) {
                nameOfCard = "Queen of Diamonds";
            }
            else if (convertToFace == 25) {
                nameOfCard = "King of Diamonds";
            }
            else if (convertToFace == 26) {
                nameOfCard = "Ace of Clubs";
            }
            else if (convertToFace == 27) {
                nameOfCard = "2 of Clubs";
            }
            else if (convertToFace == 28) {
                nameOfCard = "3 of Clubs";
            }
            else if (convertToFace == 29) {
                nameOfCard = "4 of Clubs";
            }
            else if (convertToFace == 30) {
                nameOfCard = "5 of Clubs";
            }
            else if (convertToFace == 31) {
                nameOfCard = "6 of Clubs";
            }
            else if (convertToFace == 32) {
                nameOfCard = "7 of Clubs";
            }
            else if (convertToFace == 33) {
                nameOfCard = "8 of Clubs";
            }
            else if (convertToFace == 34) {
                nameOfCard = "9 of Clubs";
            }
            else if (convertToFace == 35) {
                nameOfCard = "10 of Clubs";
            }
            else if (convertToFace == 36) {
                nameOfCard = "Jack of Clubs";
            }
            else if (convertToFace == 37) {
                nameOfCard = "Queen of Clubs";
            }
            else if (convertToFace == 38) {
                nameOfCard = "King of Clubs";
            }
            else if (convertToFace == 39) {
                nameOfCard = "Ace of Spades";
            }
            else if (convertToFace == 40) {
                nameOfCard = "2 of Spades";
            }
            else if (convertToFace == 41) {
                nameOfCard = "3 of Spades";
            }
            else if (convertToFace == 42) {
                nameOfCard = "4 of Spades";
            }
            else if (convertToFace == 43) {
                nameOfCard = "5 of Spades";
            }
            else if (convertToFace == 44) {
                nameOfCard = "6 of Spades";
            }
            else if (convertToFace == 45) {
                nameOfCard = "7 of Spades";
            }
            else if (convertToFace == 46) {
                nameOfCard = "8 of Spades";
            }
            else if (convertToFace == 47) {
                nameOfCard = "9 of Spades";
            }
            else if (convertToFace == 48) {
                nameOfCard = "10 of Spades";
            }
            else if (convertToFace == 49) {
                nameOfCard = "Jack of Spades";
            }
            else if (convertToFace == 50) {
                nameOfCard = "Queen of Spades";
            }
            else if (convertToFace == 51) {
                nameOfCard = "King of Spades";
            }
            System.out.println("The card is " + nameOfCard);
            //System.out.println(nameOfCard); //test line to debug above if statements
            return nameOfCard;
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


