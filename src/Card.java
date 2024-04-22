import edu.macalester.graphics.Image;

public class Card {
    
    private String suit;
    private String rank;
    private Image image;

    public Card(String suit, String rank,Image image) {
        this.suit = suit;
        this.rank = rank;
        this.image = image;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String getRank() {
        return rank;
    }
    
    public Image getImage(){
        return image;
    }
}