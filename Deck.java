import java.util.Collections;
import java.util.LinkedList;

public class Deck {
	
	LinkedList<Card> deck;
	
	public Deck () { 
		this.deck = new LinkedList<Card>();
	}
	
	// puts the cards into the deck
	public void initialize() {
		
		String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"Jack", "Queen", "King"};
		
		// creating each card and putting it inside the deck
		for (int i = 0; i < 13; i++) {
			deck.add(new Card("Hearts", ranks[i]) );
			deck.add(new Card("Diamond", ranks[i] ));
			deck.add(new Card("Spades", ranks[i]) );
			deck.add(new Card("Club", ranks[i]) );
		}
		
	}
	
	// this will print out the entire deck
	public void printDeck() {
		for (Card c : deck) {
			System.out.print(c.type + ", ");
			System.out.println(c.value + " ");
		}
	}
	
	// this will randomize the deck
	public void shuffle() {
		// using built in library to shuffle my deck
		Collections.shuffle(deck);
		
	}
	
	// this draws the card on top of the deck
	public Card draw() {
		Card result = deck.getFirst();
		if (deck.contains(result)) {
			// because the card was drawn, it will be removed from the deck
			deck.remove(result);
		}
		
		try {
			if (deck.contains(result)) {
				throw new DeckContainsException();
			}
		}
		
		catch (DeckContainsException ex) {
			System.out.println("ERROR: The card that was drew was not removed from the deck");
			System.exit(0);
		}
			
		return result;

	}
	
	// gets the size of the deck
	public int getSizeOfDeck() {
		return deck.size();
	}
	
	
}
