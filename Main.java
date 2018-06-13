import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Deck deck = new Deck();
		
		deck.initialize();
		deck.shuffle();
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome! This is a game of Blackjack.");


		Hand dealer = new Hand();
		Hand user = new Hand();
		
		///////////****** INITIALIZATION PHASE ******///////////
		
		// both the user and dealer get 2 cards at the beginning
		user.addCard(deck.draw());
		dealer.addCard(deck.draw());
		user.addCard(deck.draw());
		dealer.addCard(deck.draw());
		
		System.out.println("User Hand:");
		user.printHand();
		System.out.println("");
		
		boolean aceValueOne = true;
		boolean dealer_aceValueOne = false;
		if (user.containsAce()) {
			System.out.println("Do you want to treat the ace as 11? [Y/N]");
			String aceDecision = reader.nextLine();
			if (aceDecision.toUpperCase().equals("Y")) {
				aceValueOne = false;
			}
		}
		
		// want to separate the score from the user's hand to handle
		// the case if the user wants to have different values of aces within
		// one hand
		int userScore = user.score(aceValueOne);
		int dealerScore = dealer.score(dealer_aceValueOne);
		
		///////////****** GAME PHASE ******///////////
		while (dealerScore < 17) {
			Card newCard = deck.draw();
			if (newCard.isAce()) {
				
				int eleven_diff_21 = 21 - (dealerScore + 11); 
				int one_diff_21 = 21 - (dealerScore + 1);
				
				/* which ever one is the lowest will decide the value of the ace */
				// first need to make sure none have a negative value as the difference
				if (eleven_diff_21 >= 0 && one_diff_21 >= 0) {
					if (eleven_diff_21 < one_diff_21) {
						dealerScore += 11;
					}
					
					else if (eleven_diff_21 > one_diff_21) {
						dealerScore += 1;
					}
				}
			
			}
			
			else {
				if ((dealerScore + newCard.getActualScore(dealer_aceValueOne)) > 21) {
					break;
				}
			}
		}
		
		
		while (true) {
			System.out.println("");
			System.out.println("User score: " + userScore);
			System.out.println("Do you want to hit or stay?");
			System.out.println("Type in 'H' if you want to hit and 'S' if you want to stay [H/S]");
			// try to add an exception handler case here
			String input = reader.nextLine();

			if (input.toUpperCase().equals("H")) { 
				Card newCard = deck.draw();
				System.out.print("User drew: ");
				System.out.print(newCard.type + ", ");
				System.out.println(newCard.value);
				if (newCard.isAce()) {
					System.out.println("Do you want to treat the ace as 11? [Y/N]");
					String userDecision = reader.nextLine();
					if (userDecision.toUpperCase().equals("Y")) {
						userScore += 11; 
					}
					
					else {
						userScore += 1;
					}
				}
				
				else {
					userScore += newCard.getActualScore(aceValueOne);
				}
				
				// still need to add the card to the hand
				user.addCard(newCard);
				
				if (userScore > 21) {
					System.out.println("Sorry, but the total sum of your cards have exceeded 21. Good luck next time!");
					break;
				}
			}
			
			else if (input.toUpperCase().equals("S")) {
				break;
			}
			
			else {
				System.out.println("Sorry that option isn't available. Program will terminate");
				System.exit(0);
			}

		}
		
		if (dealerScore > userScore) {
			System.out.println("Sorry, but it looks like you lost to the dealer");
			System.out.print("Dealer's Score: " + dealerScore + "   ");
			System.out.println("User's Score: " + userScore);
			
		}
		
		else if (dealerScore < userScore && (userScore <= 21)) {
			System.out.println("Congratulations!!! You won!");
			System.out.print("User's Score: " + userScore + "   ");
			System.out.println("Dealer's Score: " + dealerScore);
		}
		
	}

}
