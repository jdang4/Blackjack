
public class Card {

    String type;
    String value;

    public Card (String type, String value) {
        this.type = type;
        this.value = value;
    }

    // this function is in charge of translating the card value to
    // its corresponding integer value
    public int getActualScore(boolean aceForOne) {
        int sum = 0;
        switch (this.value) {
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
        return sum;
    }

    // this function checks to see if the card is an ace
    public boolean isAce() {
        if (this.value.equals("Ace")) {
            return true;
        }

        return false;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

}
