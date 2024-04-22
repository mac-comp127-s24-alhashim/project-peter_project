import java.util.ArrayList;
import java.util.Collections;
import edu.macalester.graphics.Image;

public class Deck {

    private ArrayList<Card>deck = new ArrayList<Card>();

    String[] suits = {"hearts", "diamonds", "clubs", "spades"};
    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    public Deck(int numDeck) {
        for (int decks = 0;decks<=numDeck;decks++){
            for (String suit : suits) {
                for (int i = 0; i < ranks.length; i++) {
                    Image image = new Image(0,0,"resized_card_images/"+ranks[i]+"_of_"+suit+".png");
                    Card card = new Card(suit, ranks[i],image);
                    deck.add(card);
                }
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public double getNumberOfCardsInDeck(){
        return deck.size();
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    public void removeCard(int i) {
        deck.remove(i);
    }
}