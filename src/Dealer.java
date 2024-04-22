import java.util.ArrayList;

public class Dealer {
    private ArrayList<Card> hand= new ArrayList<>();
    
    public Dealer(){}

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

    public void dealerGetsCard(Card card){
        hand.add(card);
    }

    public ArrayList<Card> getDealerHand(){
        return hand;
    }

    public void resetHand(){
        hand.clear();
    }
}