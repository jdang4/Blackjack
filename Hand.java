import java.util.LinkedList;

public class Hand {

    LinkedList<Card> cards;

    public Hand() {
        this.cards = new LinkedList<Card>();
    }

    // this returns the score of the hand
    public int score(boolean aceForOne) {
        int sum = 0;
        for (Card card : cards) {
            switch (card.value) {
                case ("Ace") :
                    if (aceForOne) {
                        sum += 1;
                    }

                    else {
                        sum += 11;
                    }

                    break;

                case ("2") :
                    sum += 2;
                    break;
                case ("3") :
                    sum += 3;
                    break;
                case ("4") :
                    sum += 4;
                    break;
                case ("5") :
                    sum += 5;
                    break;
                case ("6") :
                    sum += 6;
                    break;
                case ("7") :
                    sum += 7;
                    break;
                case ("8") :
                    sum += 8;
                    break;
                case ("9") :
                    sum += 9;
                    break;
                case ("10") : case ("Jack") : case ("Queen") : case ("King") :
                    sum += 10;
                    break;
            }

        }

        return sum;
    }

    // this adds a card to the hand
    public void addCard(Card card) {
        this.cards.add(card);
    }

    // this prints out the cards within the hand
    public void printHand() {
        for (Card card : cards) {
            System.out.print(card.type + ", ");
            System.out.println(card.value + " ");
        }
    }

    // this checks to see if there is an ace card within the hand
    public boolean containsAce() {
        for (Card card : cards) {
            if (card.value.equals("Ace")) {
                return true;
            }
        }

        return false;
    }

}
