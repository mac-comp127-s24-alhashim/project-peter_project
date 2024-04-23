import java.util.ArrayList;

public class Dealer {
    /** The hand of cards held by the dealer. */
    private ArrayList<Card> hand= new ArrayList<>();
    
    public Dealer(){}

    /**
     * Calculates the total value of the dealer's hand.
     *
     * @return the total value of the dealer's hand
     */
    public int dealerTotal(){
        int numAces = 0;
        int handTotal = 0;
        for(Card card: hand){
            if(card.getRank().equals("ace")){
                handTotal = handTotal +11;
                numAces = numAces +1;
            }
            if(card.getRank().equals("2")){
                handTotal=handTotal+2;
            }
            if(card.getRank().equals("3")){
                handTotal=handTotal+3;
            }
            if(card.getRank().equals("4")){
                handTotal=handTotal+4;
            }
            if(card.getRank().equals("5")){
                handTotal=handTotal+5;
            }
            if(card.getRank().equals("6")){
                handTotal=handTotal+6;
            }
            if(card.getRank().equals("7")){
                handTotal=handTotal+7;
            }
            if(card.getRank().equals("8")){
                handTotal=handTotal+8;
            }
            if(card.getRank().equals("9")){
                handTotal=handTotal+9;
            }
            if(card.getRank().equals("10") || card.getRank().equals("jack") ||card.getRank().equals("queen") ||card.getRank().equals("king")){
                handTotal=handTotal+10;
            }
        }
        while(handTotal>21 && numAces!=0){
            handTotal = handTotal -10;
        }
        return handTotal;
    }

     /**
     * Gives a card to the dealer and adds it to the hand.
     */
    public void dealerGetsCard(Card card){
        hand.add(card);
    }

    /**
     * Retrieves the dealer's hand of cards.
     */
    public ArrayList<Card> getDealerHand(){
        return hand;
    }

    /**
     * Resets the dealer's hand by clearing it.
     */
    public void resetHand(){
        hand.clear();
    }
}