package blackJack;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.Shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        while (playerMoney > 0) {
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have. Good Bye!");
                break;
            }

            boolean gameRound = false;

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);
            if (playerDeck.cardsValue() == 21 || dealerDeck.cardsValue() == 21) {
                System.out.println("BLACKJACK!");
            }
            while (true) {
                System.out.println("<--Your Hand-->");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand value is at: " + playerDeck.cardsValue());
                if (playerDeck.cardsValue() == 21) {
                    System.out.println("You got BLACKJACK!");
                }
                System.out.println("<--Dealer's hand-->");
                System.out.println(dealerDeck.getCard(0).toString());
                System.out.println("Do you want to (1) Hit or (2) Stand?");
                int response = userInput.nextInt();

                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());

                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust! Your hand value is : " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        gameRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }
            System.out.println("Dealer's hand");
            System.out.println(dealerDeck.toString());
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && gameRound == false) {
                System.out.println("Dealer Won!");
                playerMoney -= playerBet;
                gameRound = true;
            }
            while ((dealerDeck.cardsValue() < 21) && gameRound == false) {
                dealerDeck.draw(playerDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
                System.out.println("Dealer's hand value: " + dealerDeck.cardsValue());
                if (dealerDeck.cardsValue() == 21) {
                    System.out.println("Dealer got BLACKJACK!");
                }
            }
            if ((dealerDeck.cardsValue() > 21) && gameRound == false) {
                System.out.println("Dealer BUST. You Win!");
                playerMoney += playerBet;
                gameRound = true;
            }
            if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && gameRound == false) {
                System.out.println("Push! No one wins!");
                gameRound = true;
            }
            if ((dealerDeck.cardsValue() < playerDeck.cardsValue()) && gameRound == false) {
                System.out.println("You Won!");
                playerMoney += playerBet;
                gameRound = true;
            } else if (gameRound == false) {
                System.out.println("You lose!");
                playerMoney -= playerBet;
                gameRound = true;
            }
            playerDeck.moveToDeck(playingDeck);
            dealerDeck.moveToDeck(playingDeck);
        }
        System.out.println("Game over! You are out of money.");

    }
}