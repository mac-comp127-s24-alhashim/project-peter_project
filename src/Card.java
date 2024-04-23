import edu.macalester.graphics.Image;

/**
 * Represents a playing card.
 */
public class Card {
    
    /** The suit of the card (e.g., hearts, diamonds, clubs, spades). */
    private String suit;

    /** The rank of the card (e.g., ace, 2, 3, ..., 10, jack, queen, king). */
    private String rank;

    /** The graphical representation of the card. */
    private Image image;

    /**
     * Constructs a card object with the given suit, rank, and image.
     *
     * @param suit the suit of the card
     * @param rank the rank of the card
     * @param image the graphical representation of the card
     */
    public Card(String suit, String rank,Image image) {
        this.suit = suit;
        this.rank = rank;
        this.image = image;
    }
    
    /**
     * Retrieves the suit of the card.
     */
    public String getSuit() {
        return suit;
    }
    
    /**
     * Retrieves the rank of the card.
     */
    public String getRank() {
        return rank;
    }
    
    /**
     * Retrieves the graphical representation of the card.
     */
    public Image getImage(){
        return image;
    }
}