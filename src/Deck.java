import java.util.ArrayList;
import java.util.Collections;
import edu.macalester.graphics.Image;

/**
 * Represents a deck of playing cards.
 */
public class Deck {

    /** The collection of cards in the deck. */
    private ArrayList<Card>deck = new ArrayList<Card>();

     /** The array of possible suits in a deck of cards. */
    String[] suits = {"hearts", "diamonds", "clubs", "spades"};

    /** The array of possible ranks in a deck of cards. */
    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    /**
     * Constructs a deck object with the specified number of decks.
     *
     * @param numDeck the number of decks to be included in the deck object
     */
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

    /**
     * Shuffles the deck of cards.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * Retrieves the number of cards in the deck.
     */
    public double getNumberOfCardsInDeck(){
        return deck.size();
    }

    /**
     * Retrieves the card at the specified index in the deck.
     *
     * @param i the index of the card to retrieve
     * @return the card at the specified index
     */
    public Card getCard(int i) {
        return deck.get(i);
    }

    /**
     * Removes the card at the specified index from the deck.
     *
     * @param i the index of the card to remove
     */
    public void removeCard(int i) {
        deck.remove(i);
    }
}