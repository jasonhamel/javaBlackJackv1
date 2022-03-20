/*
    Version 1 of a text based Black Jack game. The purpose of this was to get familiar with Java and to also
    prove I still remember basic concepts. Using a card constructor would have made this just an insane amount easier
    and less gross to look at... but I didn't want to look up how to use them. That felt like dangerously close to
    just looking up for to make a deck of cards, or how to make a Black Jack game. And that wasn't the point of
    this project.

    Happy with how most of this turned out. The absolute crapfest that is my if statements starting at line 183,
    and my non-use of a method to initiate the drawing of a card are the biggest issues. I'd like to revisit this
    project at some point and improve those, as well as add a constructor for cards. Maybe one day :)
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Set Scanner to myObj
        Scanner myObj = new Scanner(System.in);
        //Create a list to store drawn cards in. This is used to ensure once a card is drawn, it cannot be redrawn.
        List<Integer> drawnCards = new ArrayList<>();
        //Create a list to store player's cards. This is used to hold which cards the user has, does not track score.
        List<Integer> playerCards = new ArrayList<>();
        //Create a list to store dealer score. This is used to hold which cards the dealer has, does not track score.
        List<Integer> dealerCards = new ArrayList<>();
        //Create a list to store the numerical value of the user's cards. Summing the items gives the user's score.
        List<Integer> playerValue = new ArrayList<>();
        //Create a list to store the numerical value of the dealer's cards. Summing the items gives the dealer's score.
        List<Integer> dealerValue = new ArrayList<>();
        //Holds the player's score.
        int playerScore = 0;
        //Holds the dealer's score.
        int dealerScore;
        //Represents the cards in the deck. Passed into the method drawCard to choose a random number.
        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}; // create arrays of cards
        //Floor for random card selection.
        int min = 0;
        //Ceiling for random card selection.
        int max = 51;
        //Holds the value of the current drawn card. This gets passed into the method CreateFace to assign the random number to a card.
        int convertToFace;
        //Captures user's choice on whether to take another card. Set to y as default to correctly prompt the user the first time.
        String hitOrStay = "y";
        //Literally just keeps track of total number of cards drawn and prevents the displaying of the 4th card (the dealer's hidden card).
        int dealerSecondCardHide = 0;

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
            playerValue.add(createFace(convertToFace, dealerSecondCardHide));
            playerScore = playerScore + playerValue.get(i);
            dealerSecondCardHide++;
        }

        //print user score
        System.out.println("Your score is: " + playerScore);

        // draw the dealer's first card and display to player
        dealerCards.add(drawCard(drawnCards, cards, min, max));
        convertToFace = dealerCards.get(0);
        dealerValue.add(createFace(convertToFace, dealerSecondCardHide));
        dealerScore = dealerValue.get(0);
        dealerSecondCardHide++;

        //draw the dealer's second card and hide from user
        System.out.println("The dealer's score is: " + dealerScore);
        dealerCards.add(drawCard(drawnCards, cards, min, max));
        convertToFace = dealerCards.get(1);
        dealerValue.add(createFace(convertToFace, dealerSecondCardHide));
        dealerScore = dealerScore + dealerValue.get(1);
        dealerSecondCardHide++;

        //Offers user the chance to select another card and add it to their total
        while (hitOrStay.equals("y") || !hitOrStay.equals("n")) {
            System.out.println("Would you like to risk another card? y/n");
            hitOrStay = myObj.nextLine();
            if (hitOrStay.equals("y")) {
                System.out.println("You've selected yes");
                //Draws another card, adds it to the score.
                playerCards.add(drawCard(drawnCards, cards, min, max));
                convertToFace = playerCards.get(playerCards.size() - 1);
                playerValue.add(createFace(convertToFace, dealerSecondCardHide));
                playerScore = playerScore + playerValue.get(playerCards.size() - 1);
                System.out.println("Your score is: " + playerScore);
                //Checks if the user has gone over 21
                if (playerScore > 21) {
                    //Checks if the user has an ace and reduces its value to 1 instead of 11
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

        //Checks if the dealer has a tying score. Which ends the game without the dealer risking a bust
        if (dealerScore == playerScore) {
            System.out.print("The dealer also had" + playerScore + " and a tie goes to them. You Lose :(");
        } else if (dealerScore > playerScore){
            System.out.println("The dealer flipped their second card. You should have hit. They had " + dealerScore);
        }

        //Draws cards for the dealer until they either win or bust
        while (dealerScore < playerScore && playerScore < 22) {
            System.out.println("The dealer reveals their second card, bringing them to a score of " + dealerScore);
            dealerCards.add(drawCard(drawnCards, cards, min, max));
            convertToFace = dealerCards.get(dealerCards.size() - 1);
            dealerValue.add(createFace(convertToFace, dealerSecondCardHide));
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
            if (dealerScore <= 21 && dealerScore >= playerScore) {
                System.out.println("The dealer fucked all your shit up. Your " + playerScore + " was no match for their " + dealerScore + ".");
            }
        }

    }

    /*the draw card method will be called anytime a new card should be added to the game. It will be a
    self-contained way to generate a random number from 1-51, check if that number has been drawn, re-draw
    if it has, and return that value. The value will then be passed into the createFace method to assign an
    actual value and name.
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

    //ugh. I hate this code. Takes in the random number generated from drawCard and assigns it a value and title
    public static Integer createFace(int convertToFace, int dealerSecondCardHide) {

        String nameOfCard = "";
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
        //checks if this is the dealer's hidden card. if it is, doesn't display it
        if (dealerSecondCardHide != 3) {
            System.out.println("The card is " + nameOfCard);
            return valueOfCard;
        }


        return valueOfCard;


    }

}


