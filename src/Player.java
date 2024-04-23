import java.util.ArrayList;

public class Player {
    /** The hand of cards held by the player. */
    private ArrayList<Card> hand= new ArrayList<>();
    
    public Player(){}

     /**
     * Checks if the player has a blackjack (an Ace and a 10-value card).
     *
     * @return true if the player has a blackjack, otherwise false
     */
    public boolean checkForBJ(){
        int numAces =0;
        int numTens =0;
        for(Card card :hand){
            if(card.getRank().equals("ace")){
                numAces = numAces+1;
            }
            if(card.getRank().equals("10") || card.getRank().equals("jack") || card.getRank().equals("queen") ||card.getRank().equals("king")){
                numTens=numTens+1;
            }
        }
        if(numAces == 1 && numTens == 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Calculates the total value of the player's hand with Ace counted as 1.
     *
     * @return the low total value of the player's hand
     */
    public int lowHandTotal(){
        int handTotal = 0;
        for(Card card: hand){
            if(card.getRank().equals("ace")){
                handTotal = handTotal +1;
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
        return handTotal;
    }
    
    /**
     * Calculates the total value of the player's hand with Ace counted as 11 or 1 to minimize busting.
     *
     * @return the high total value of the player's hand
     */
    public int highHandTotal(){
        int numAces = 0;
        int handTotal = 0;
        for(Card card: hand){
            if(card.getRank().equals("ace")){
                handTotal = handTotal +11;
                numAces = numAces +1;
            }
            if(card.getRank().equals("2")){
                handTotal= handTotal + 2;
            }
            if(card.getRank().equals("3")){
                handTotal=handTotal+3;
            }
            if(card.getRank().equals("4")){
                handTotal= handTotal+4;
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
        numAces= numAces-1;
    }
    return handTotal;
}
    
    /**
     * Retrieves the cards held by the player.
     */
    public ArrayList<Card> getCards(){
        return hand;
    }
    
    /**
     * Gives a card to the player and adds it to the hand.
     */
    public void playerGetsCard(Card card){
        hand.add(card);
    }
    
    /**
     * Resets the player's hand by clearing it.
     */
    public void resetHand(){
        hand.clear();
    }
    
    /**
     * Checks if the player can hit (take another card).
     *
     * @return true if the player's total value is less than 21, otherwise false
     */
    public boolean canPlayerHit(){
        if(lowHandTotal()>=21){
            return false;
        }
        else{
            return true;
        }
    }
}