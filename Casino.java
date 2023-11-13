package pgdp.casino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

	public static void penguBlackJack() {
		CardDeck deck = CardDeck.getDeck();
		int Punktestand;
		int balance = 1000;
		System.out.println("Welcome to Pengu-BlackJack!");
		while (balance > 0) {
			System.out.println("(1) Start a game or (2) exit");
			int input = readInt();

			while (input > 2 || input < 1) {
				System.out.println("What?!");
				System.out.println("(1) Start a game or (2) exit");
				input = readInt();

			}
			if (input == 1) {
				System.out.println("Your current balance: " + balance);
				System.out.println("How much do you want to bet?");
				int bet = readInt();
				while (bet > balance || bet < 1) {
					System.out.println("How much do you want to bet?");
					bet = readInt();
				}
				System.out.println("Player cards:");
				int card1 = deck.drawCard();
				int card2 = deck.drawCard();
				System.out.println("1 : " + card1);
				System.out.println("2 : " + card2);
				Punktestand = card1 + card2;

				System.out.println("Current standing: " + Punktestand);

				int cardNumber = 3;

				while (Punktestand < 21) {
					System.out.println("(1) draw another card or (2) stay");
					int decide = readInt();

					while (decide > 2 || decide < 1) {
						System.out.println("What?!");
						System.out.println("(1) draw another card or (2) stay");

						decide = readInt();
					}
					if (decide == 1) {
						int card3 = deck.drawCard();
						System.out.println(cardNumber + " : " + card3);
						Punktestand += card3;
						System.out.println("Current standing: " + Punktestand);
						cardNumber++;

					} else {
						break;
					}
				}
				int dealerCardNumber = 1;
				if (Punktestand > 21) {
					System.out.println("You lost " + bet + " tokens.");
					balance -= bet;
					if (balance == 0) {
						System.out.println("Sorry, you are broke. Better Luck next time.");
					}
				} else if (Punktestand == 21) {
					System.out.println("Blackjack! You won " + bet * 2 + " tokens.");
					balance += bet * 2;
				} else {
					System.out.println("Dealer cards:");
					int dealerPunktestand = 0;
					while (dealerPunktestand < Punktestand) {
						int dealerCard1 = deck.drawCard();
						System.out.println(dealerCardNumber + " : " + dealerCard1);
						dealerPunktestand += dealerCard1;
						dealerCardNumber++;
					}
					System.out.println("Dealer: " + dealerPunktestand);
					if (dealerPunktestand > Punktestand && dealerPunktestand < 22) {
						System.out.println("Dealer wins. You lost " + bet + " tokens.");
						balance -= bet;
					} else if (dealerPunktestand > 21) {
						System.out.println("You won " + bet + " tokens.");
						balance += bet;
					}
				}
			}
			if (input == 2 || balance == 0) {
				System.out.println("Your final balance: " + balance);
				if (balance > 1000) {
					System.out.println("Wohooo! Ez profit! :D");
				} else if (balance <= 1000) {
					System.out.println("That's very very sad :(");
				}

				System.out.println("Thank you for playing. See you next time.");
				break;
			}
		}
	}














	public static String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int readInt() {
		while (true) {
			String input = readString();
			try {
				return Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("This was not a valid number, please try again.");
			}
		}
	}

	public static void main(String[] args) {
		penguBlackJack();

	}


}
