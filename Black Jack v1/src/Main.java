import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        // set Scanner to myObj
        Scanner myObj = new Scanner(System.in);
        //create a list to store drawn cards in
        List<Integer> drawnCards = new ArrayList<>();
        //create a list to store player score
        List<Integer> playerCards = new ArrayList<>();
        //create a list to store dealer score
        List<Integer> dealerCards = new ArrayList<>();
        List<Integer> playerValue = new ArrayList<>();
        List<Integer> dealerValue = new ArrayList<>();
        int playerScore = 0;
        int dealerScore;
        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}; // create arrays of cards
        int min = 0;
        int max = 51;
        int convertToFace;
        String hitOrStay = "y";
        int dealerSecondCardDontShow = 1;

        // Set up the beginning of the game. Display rules
        System.out.println("Welcome to Black Jack!");
        System.out.println("The rules are simple! You will be handed to cards. So will the dealer.");
        System.out.println("You will only see one of the dealer's cards, they will see all of yours.");
        System.out.println("Your score is the total from adding your cards up.");
        System.out.println("The goal is for you to have a higher number than the dealer.");
        System.out.println("You can request more cards, but careful not to go over 21!");
        System.out.println("The closest without going over wins!");
        System.out.println("If you get an ace, it will either count as 11 or 1, whichever is better for you.");

        // draw two cards for the user.
        for (int i = 0; i < 2; i++) {
            playerCards.add(drawCard(drawnCards, cards, min, max));
            convertToFace = playerCards.get(i);
            playerValue.add(createFace(convertToFace, dealerSecondCardDontShow));
            playerScore = playerScore + playerValue.get(i);
        }

        //print user score
        System.out.println("Your score is: " + playerScore);


        // draw one card to display to user
        dealerCards.add(drawCard(drawnCards, cards, min, max));
        convertToFace = dealerCards.get(0);
        dealerValue.add(createFace(convertToFace, dealerSecondCardDontShow));
        dealerScore = dealerValue.get(0);

        //draw another to hide from user
        System.out.println("The dealer's score is: " + dealerScore);
        dealerCards.add(drawCard(drawnCards, cards, min, max));
        convertToFace = dealerCards.get(1);
        dealerValue.add(createFace(convertToFace, dealerSecondCardDontShow));
        dealerScore = dealerScore + dealerValue.get(1);

        System.out.println(""); //for formatting

        //Offers user the chance to select another card and add it to their total
        while (hitOrStay.equals("y") || !hitOrStay.equals("n")) {
            System.out.println("Would you like to risk another card? y/n");
            hitOrStay = myObj.nextLine();
            if (hitOrStay.equals("y")) {

                System.out.println("You've selected yes");
                playerCards.add(drawCard(drawnCards, cards, min, max));
                convertToFace = playerCards.get(playerCards.size() - 1);
                playerValue.add(createFace(convertToFace, dealerSecondCardDontShow));
                playerScore = playerScore + playerValue.get(playerCards.size() - 1);
                System.out.println("Your score is: " + playerScore);
                System.out.println(""); //formatting
                if (playerScore > 21) {

                    if (playerValue.contains(11)) {
                        int replaceAceValue;
                        replaceAceValue = playerValue.indexOf(11);
                        playerValue.set(replaceAceValue, 1);
                        playerScore = playerScore - 10;
                        System.out.println("You went over 21, BUT you had an Ace. Your Ace was flipped to a score of 1");
                        System.out.println("You're updated score is " + playerScore);
                        if (playerScore > 21) {
                            System.out.println("Still a bust though");
                        }
                    } else {
                        System.out.println("That's a bust friendo");
                        break;
                    }
                }
            } else if (hitOrStay.equals("n")) {
                System.out.println("You've selected no");
                System.out.println("You're staying at " + playerScore + ". Now it's the dealer's turn");
            } else {
                System.out.println("Invalid selection. Please press the 'y' key for yes, or the 'n' key for no :)");
            }
        }
        if (dealerScore == playerScore){
            System.out.print("The dealer also had" + playerScore + " and a tie goes to them. You Lose :(");
        }
        while (dealerScore < playerScore) {
            dealerCards.add(drawCard(drawnCards, cards, min, max));
            convertToFace = dealerCards.get(dealerCards.size() - 1);
            dealerValue.add(createFace(convertToFace, dealerSecondCardDontShow));
            dealerScore = dealerScore + dealerValue.get(dealerCards.size() - 1);
            System.out.print("The dealer's score is now " + dealerScore);
            if (dealerScore > 21) {

                if (dealerValue.contains(11)) {
                    int replaceAceValue;
                    replaceAceValue = dealerValue.indexOf(11);
                    dealerValue.set(replaceAceValue, 1);
                    dealerScore = dealerScore - 10;
                    System.out.println("The dealer over 21, BUT they had an Ace. Their Ace was flipped to a score of 1");
                    System.out.println("They're updated score is " + dealerScore);
                    if (dealerScore > 21) {
                        System.out.println("Still a bust though. YOU WIN");
                    }
                } else {
                    System.out.println("That's a bust friendo. YOU WIN");
                    break;
                }
            }
        }

    }

    /*the draw card method will be called anytime a new card should be added to the game. it will be a
    self-contained way to generate a random number from 1-51, check if that number has been drawn, re-draw
    if it has, and convert that number to a face card. it will then return the face card.
     */
    public static int drawCard(List<Integer> drawnCards, int[] cards, int min, int max) {
        int reDraw;
        int arraySelectCard = cards[(int) Math.floor(Math.random() * (max - min + 1) + min)];
        // check if current random card has already been drawn. re-draw if yes
        if (drawnCards.contains(arraySelectCard)) {
            //System.out.println("This happened"); //test line to debug dupes
            reDraw = drawCard(drawnCards, cards, min, max);
        } else {
            drawnCards.add(arraySelectCard); // add current selected card to drawnCards List to prevent drawing again
            // System.out.println("Drawn cards are " + drawnCards); //test line to debug dupes
            return arraySelectCard;//return validated card for display to user
        }
        return reDraw;
    }

    public static Integer createFace(int convertToFace, int dealerSecondCardDontShow) {
        String nameOfCard = "";
        dealerSecondCardDontShow = dealerSecondCardDontShow + 1;
        int valueOfCard = 0;
        if (convertToFace == 0) {
            nameOfCard = "Ace of Hearts";
            valueOfCard = 11;
        } else if (convertToFace == 1) {
            nameOfCard = "2 of Hearts";
            valueOfCard = 2;
        } else if (convertToFace == 2) {
            nameOfCard = "3 of Hearts";
            valueOfCard = 3;
        } else if (convertToFace == 3) {
            nameOfCard = "4 of Hearts";
            valueOfCard = 4;
        } else if (convertToFace == 4) {
            nameOfCard = "5 of Hearts";
            valueOfCard = 5;
        } else if (convertToFace == 5) {
            nameOfCard = "6 of Hearts";
            valueOfCard = 6;
        } else if (convertToFace == 6) {
            nameOfCard = "7 of Hearts";
            valueOfCard = 7;
        } else if (convertToFace == 7) {
            nameOfCard = "8 of Hearts";
            valueOfCard = 8;
        } else if (convertToFace == 8) {
            nameOfCard = "9 of Hearts";
            valueOfCard = 9;
        } else if (convertToFace == 9) {
            nameOfCard = "10 of Hearts";
            valueOfCard = 10;
        } else if (convertToFace == 10) {
            nameOfCard = "Jack of Hearts";
            valueOfCard = 10;
        } else if (convertToFace == 11) {
            nameOfCard = "Queen of Hearts";
            valueOfCard = 10;
        } else if (convertToFace == 12) {
            nameOfCard = "King of Hearts";
            valueOfCard = 10;
        } else if (convertToFace == 13) {
            nameOfCard = "Ace of Diamonds";
            valueOfCard = 11;
        } else if (convertToFace == 14) {
            nameOfCard = "2 of Diamonds";
            valueOfCard = 2;
        } else if (convertToFace == 15) {
            nameOfCard = "3 of Diamonds";
            valueOfCard = 3;
        } else if (convertToFace == 16) {
            nameOfCard = "4 of Diamonds";
            valueOfCard = 4;
        } else if (convertToFace == 17) {
            nameOfCard = "5 of Diamonds";
            valueOfCard = 5;
        } else if (convertToFace == 18) {
            nameOfCard = "6 of Diamonds";
            valueOfCard = 6;
        } else if (convertToFace == 19) {
            nameOfCard = "7 of Diamonds";
            valueOfCard = 7;
        } else if (convertToFace == 20) {
            nameOfCard = "8 of Diamonds";
            valueOfCard = 8;
        } else if (convertToFace == 21) {
            nameOfCard = "9 of Diamonds";
            valueOfCard = 9;
        } else if (convertToFace == 22) {
            nameOfCard = "10 of Diamonds";
            valueOfCard = 10;
        } else if (convertToFace == 23) {
            nameOfCard = "Jack of Diamonds";
            valueOfCard = 10;
        } else if (convertToFace == 24) {
            nameOfCard = "Queen of Diamonds";
            valueOfCard = 10;
        } else if (convertToFace == 25) {
            nameOfCard = "King of Diamonds";
            valueOfCard = 10;
        } else if (convertToFace == 26) {
            nameOfCard = "Ace of Clubs";
            valueOfCard = 11;
        } else if (convertToFace == 27) {
            nameOfCard = "2 of Clubs";
            valueOfCard = 2;
        } else if (convertToFace == 28) {
            nameOfCard = "3 of Clubs";
            valueOfCard = 3;
        } else if (convertToFace == 29) {
            nameOfCard = "4 of Clubs";
            valueOfCard = 4;
        } else if (convertToFace == 30) {
            nameOfCard = "5 of Clubs";
            valueOfCard = 5;
        } else if (convertToFace == 31) {
            nameOfCard = "6 of Clubs";
            valueOfCard = 6;
        } else if (convertToFace == 32) {
            nameOfCard = "7 of Clubs";
            valueOfCard = 7;
        } else if (convertToFace == 33) {
            nameOfCard = "8 of Clubs";
            valueOfCard = 8;
        } else if (convertToFace == 34) {
            nameOfCard = "9 of Clubs";
            valueOfCard = 9;
        } else if (convertToFace == 35) {
            nameOfCard = "10 of Clubs";
            valueOfCard = 10;
        } else if (convertToFace == 36) {
            nameOfCard = "Jack of Clubs";
            valueOfCard = 10;
        } else if (convertToFace == 37) {
            nameOfCard = "Queen of Clubs";
            valueOfCard = 10;
        } else if (convertToFace == 38) {
            nameOfCard = "King of Clubs";
            valueOfCard = 10;
        } else if (convertToFace == 39) {
            nameOfCard = "Ace of Spades";
            valueOfCard = 11;
        } else if (convertToFace == 40) {
            nameOfCard = "2 of Spades";
            valueOfCard = 2;
        } else if (convertToFace == 41) {
            nameOfCard = "3 of Spades";
            valueOfCard = 3;
        } else if (convertToFace == 42) {
            nameOfCard = "4 of Spades";
            valueOfCard = 4;
        } else if (convertToFace == 43) {
            nameOfCard = "5 of Spades";
            valueOfCard = 5;
        } else if (convertToFace == 44) {
            nameOfCard = "6 of Spades";
            valueOfCard = 6;
        } else if (convertToFace == 45) {
            nameOfCard = "7 of Spades";
            valueOfCard = 7;
        } else if (convertToFace == 46) {
            nameOfCard = "8 of Spades";
            valueOfCard = 8;
        } else if (convertToFace == 47) {
            nameOfCard = "9 of Spades";
            valueOfCard = 9;
        } else if (convertToFace == 48) {
            nameOfCard = "10 of Spades";
            valueOfCard = 10;
        } else if (convertToFace == 49) {
            nameOfCard = "Jack of Spades";
            valueOfCard = 10;
        } else if (convertToFace == 50) {
            nameOfCard = "Queen of Spades";
            valueOfCard = 10;
        } else if (convertToFace == 51) {
            nameOfCard = "King of Spades";
            valueOfCard = 10;
        }


        System.out.println("The card is " + nameOfCard);

        return valueOfCard;


    }

}


// offer user the option to hit or stay
// draw a single card for user each time they select hit
// sum user's cards, check if they've gone over, if yes, check if there's an ace, if yes convert to a 1
// check if user has exactly 21, switch to dealer
// have dealer auto-draw cards until they exceed user score. if dealer goes over 21 they lose
// offer to play again


