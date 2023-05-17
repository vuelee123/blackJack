package blackJack;

public class Card {
    private Suits suit;
    private Values value;

    public Card(Suits suit, Values value) {
        this.suit= suit;
        this.value = value;
    }
    public String toString() {
        return this.suit.toString() + "-" + this.value.toString();
    }
    public Values getValue(){
        return this.value;
    }
}
