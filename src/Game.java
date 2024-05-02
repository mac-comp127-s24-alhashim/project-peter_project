import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;

public class Game {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 500;
    private CanvasWindow canvas;
    private static Game game;
    private Deck deck;
    private Image faceDown = new Image("back_of_card/cardback.png");
    private Button hitButton = new Button("Hit");
    private Button standButton = new Button("Stand");
    private Player player = new Player();
    private Button dealButton = new Button("Deal");
    private GraphicsText playerTotal = new GraphicsText();
    private Button doubleButton = new Button("Double!!!");
    private Dealer dealer = new Dealer();
    private GraphicsText dealerTotal = new GraphicsText();
    private GraphicsText whoWins = new GraphicsText();
    private boolean doubleOnScreen = true;
    private ArrayList<Chip> chips = new ArrayList<>();
    private GraphicsText amountBet = new GraphicsText();
    private double totalBet;
    private double currentBet;
    private double amountLeftInBank;
    private GraphicsText moneyPersonHas = new GraphicsText();
    private boolean dealOnScreen = true;
    private Image backGround = new Image("felt.jpg");


    /**
    * Constructs a new game instance.
    */
    public Game(){
        canvas = new CanvasWindow("Blackjack", CANVAS_WIDTH, CANVAS_HEIGHT);
        deck = new Deck(4);  
        hitButton.onClick(()->{hit();});
        standButton.onClick(()->stand());
        doubleButton.onClick(()->doubleChosen());
        dealButton.onClick(()->{
        if(totalBet !=0){
            playRound();}});
        canvas.add(backGround,0,0);
        backGround.setScale(2);
        canvas.onClick(event -> {
        checkIfCanBet(event.getPosition());}); 
        amountLeftInBank=1000; 
        String totalPersonTotalString = "You have " + amountLeftInBank + "$";
        moneyPersonHas.setText(totalPersonTotalString);
        moneyPersonHas.setFont(FontStyle.BOLD,16);
        moneyPersonHas.setFillColor(Color.WHITE);
        amountBet.setFont(FontStyle.BOLD,16);
        amountBet.setFillColor(Color.WHITE);
        canvas.add(moneyPersonHas,150,350);
        totalBet=0;
    }

    public static void main(String[] args) {
        game = new Game();
        game.addChips();
        game.startGame();    
    }

    /**
    * Checks if the player can place a bet based on their available funds and the selected chip.
    *
    * @param point the point representing the location where the chip was pressed
    */
    public void  checkIfCanBet(Point point){
        for(Chip chip:chips){
            if(chip.getEllipse() == canvas.getElementAt(point) && dealOnScreen == true && chip.getValue()<amountLeftInBank){
                currentBet= chip.getValue();
                totalBet = totalBet + currentBet;
                addBet();
                canvas.draw();
            }
            else{
            }
        }
    }

    /**
    * Adds a bet to the screen and updates the counter in the instance variables.
    */
    public void addBet(){
        amountLeftInBank = amountLeftInBank - currentBet;
         String totalPersonTotalString = "You have " + amountLeftInBank + "$";
        moneyPersonHas.setText(totalPersonTotalString);
        String betString = totalBet + "$";
        amountBet.setText("Your Bet Total "+ betString);
        amountBet.setFont(FontStyle.BOLD,16);
        canvas.add(amountBet,150,200);
        canvas.draw();
    }

    /**
    * Adds chips to the screen.
    */
    public void addChips(){
        double[] valuesOfChips = {1,5,10,25};
        double x=100;
        double y = 400;
        for(double value:valuesOfChips){
            Chip chip = new Chip(x,y,80,80,value);
            canvas.add(chip.getEllipse());
            canvas.add(chip.getText());
            chips.add(chip);
            chip.setColorBasedOnValue();
            x=x+100;
        }
    }

    /**
    * Resets the current round of the game.
    */
    public void resetRound(){
        if(deck.getNumberOfCardsInDeck()<20){
            deck = new Deck(4);
        }
        canvas.draw();
        canvas.pause(1000);
        removeCards(player.getCards());
        removeCards(dealer.getDealerHand());
        player.resetHand();
        dealer.resetHand();
        canvas.remove(playerTotal);
        canvas.remove(dealerTotal);
        canvas.remove(whoWins);
        totalBet=0;
        String totalPersonTotalString = "You have " + amountLeftInBank + "$";
        moneyPersonHas.setText(totalPersonTotalString);
        canvas.remove(amountBet);
        startGame();
        
    }

    /**
    * Starts a new game by shuffling the deck and adding the deal button to the screen.
    */
    public void startGame(){
        deck.shuffleDeck();
        canvas.add(dealButton);
        dealOnScreen = true;
        dealButton.setCenter(700,400);
        
    }

    /**
    * Plays a round of the game by setting up the initial game state and displaying player and dealer cards.
    */
    public void playRound(){
        canvas.remove(dealButton);
        dealOnScreen = false;
        faceDown.setScale(.2);
        canvas.add(faceDown,355,-350); 
        canvas.add(hitButton);
        hitButton.setCenter(670,320);
        canvas.add(standButton);
        standButton.setCenter(600,320);
        givePlayerCardsFirst();
        addPlayerImage(player.getCards());
        canvas.add(doubleButton);
        doubleOnScreen=true;
        doubleButton.setCenter(630,350);
        displayPlayerTotal();
        Image dealerCardUp = deck.getCard(2).getImage();
        canvas.add(dealerCardUp);
        dealerCardUp.setCenter(550,140);
        dealer.dealerGetsCard(deck.getCard(2));
        deck.removeCard(2);
        displayDealerTotal();
        if(player.checkForBJ()==true){
            whoWins.setText("BlackJack You WIN!!!");
            canvas.add(whoWins);
            canvas.remove(hitButton);
            canvas.remove(standButton);
            canvas.remove(doubleButton);
            canvas.draw();
            amountLeftInBank = amountLeftInBank + totalBet + totalBet;
            totalBet=0;
            canvas.pause(3000);
            resetRound();
        }
        canvas.draw();
    }

    /**
    * Doubles the current bet and gives the player one card.
    */
    public void doubleChosen(){
        amountLeftInBank = amountLeftInBank - totalBet;
        totalBet = totalBet *2;
         String totalPersonTotalString = "You have " + amountLeftInBank + "$";
        moneyPersonHas.setText(totalPersonTotalString);
        String betString = totalBet + "$";
        amountBet.setText("Your Bet Total "+ betString);
        canvas.remove(hitButton);
        canvas.remove(standButton);
        canvas.remove(doubleButton);
        Image cardImag = deck.getCard(1).getImage();
        canvas.add(cardImag,550,300);
        cardImag.rotateBy(90);
        player.playerGetsCard(deck.getCard(1));
        deck.removeCard(1);
        displayPlayerTotal();
        dealersTurn();
    }

    /**
    * Handles the player choosing to stand, removes command buttons, and proceeds to the dealer's turn.
    */
    public void stand(){
        canvas.remove(hitButton);
        canvas.remove(standButton);
        if(doubleOnScreen==true){
            canvas.remove(doubleButton);
        }
        dealersTurn();

    }
    
    /**
    * Handles the dealer's turn by removing the face-down card and giving the dealer a card until the total is at least 17.
    */
    public void dealersTurn(){
        canvas.remove(faceDown);
        while(dealer.dealerTotal()<=16){
            dealer.dealerGetsCard(deck.getCard(1));
            deck.removeCard(1);
            displayDealerTotal();
            dealerImages();
            canvas.draw();
            canvas.pause(1000);
        }
        whoWinsGame();
    }

    /**
    * Handles the player choosing to hit, gives the player a card, and checks if they can still hit.
    * If the player can hit, adds hit and stand buttons again; otherwise, proceeds to the dealer's turn.
    */
    public void hit(){
        canvas.remove(hitButton);
        canvas.remove(standButton);
        if(doubleOnScreen==true){
            canvas.remove(doubleButton);
            doubleOnScreen=false;
        }
        player.playerGetsCard(deck.getCard(1));
        deck.removeCard(1);
        displayPlayerTotal();
        addPlayerImage(player.getCards());
        if(player.canPlayerHit()==true){
            canvas.add(hitButton);
            canvas.add(standButton);
        }
        if(player.canPlayerHit()==false){
            dealersTurn();
        }
    }

    /**
     * Gives player starting two hands
     */
    public void givePlayerCardsFirst(){
        for(int i=0;i<2;i++){
            Card card = deck.getCard(i);
            player.playerGetsCard(card);
            deck.removeCard(i);
            }
    }

    /**
    * Displays the total value of the dealer's hand on the screen.
    */
    public void displayDealerTotal(){
        int dealerTotall = dealer.dealerTotal();
        String dealerTotalString = Integer.toString(dealerTotall);
        dealerTotal.setText("Dealer Total = " + dealerTotalString);
        dealerTotal.setCenter(350,150);
        canvas.add(dealerTotal);
        dealerTotal.setFillColor(Color.WHITE);
        dealerTotal.setFont(FontStyle.BOLD,16);
        
    }

    /**
    * Displays the total value of the player's hand on the screen.
    */
    public void displayPlayerTotal(){
        int playerTotalHigh = player.highHandTotal();
        String playerTotalString = Integer.toString(playerTotalHigh);
        int playerTotalLow = player.lowHandTotal();
        String playerTotalLowString = Integer.toString(playerTotalLow);
        playerTotal.setFillColor(Color.WHITE);
        playerTotal.setFont(FontStyle.BOLD,16);
        if(playerTotalLow == playerTotalHigh){
            playerTotal.setText("player total = "+playerTotalString);
            canvas.add(playerTotal,300,100);
        }
        else{
            playerTotal.setText("player total = " +playerTotalString+" or "+playerTotalLowString);
            canvas.add(playerTotal,300,100);
        }
    }

    /**
    * Displays the images of the cards in the dealer's hand on the screen.
    */
    public void dealerImages(){
        double xLocation = 490;
        double yLocation = 60;
        ArrayList<Card> dealerHand = dealer.getDealerHand();
        for(Card card : dealerHand){
            canvas.add(card.getImage(),xLocation,yLocation);
            xLocation+=130;
        }
    }

    /**
    * Displays the images of the cards in the player's hand on the screen.
    */
    public void addPlayerImage(ArrayList<Card> cards){
        double xLoc =500;
        double yLoc = 400;
        for(Card card :cards){
            canvas.add(card.getImage(),xLoc,yLoc);
            xLoc+=130;
        }
    }

    /**
    * Removes cards based on list of cards given.
    */
    public void removeCards(ArrayList<Card> cards){
        for(Card card : cards){
            canvas.remove(card.getImage());
        }
    }

    /**
    * Calculates and displays the outcome of the game.
    */
    public void whoWinsGame(){
        int playerHighTotal = player.highHandTotal();
        int dealerHighTotal = dealer.dealerTotal();
        whoWins.setFont(FontStyle.BOLD, 50);
        if(playerHighTotal > 21){
            whoWins.setText("You Lose");
            canvas.add(whoWins);
            whoWins.setCenter(630,300);
            totalBet=0;
            resetRound();
        }
        if(playerHighTotal > dealerHighTotal && playerHighTotal <= 21){
            whoWins.setText("You WIN!!!");
            canvas.add(whoWins);
            whoWins.setCenter(630,300);
            amountLeftInBank = amountLeftInBank + totalBet + totalBet;
            totalBet=0;
            resetRound();
        }
        if(playerHighTotal==dealerHighTotal && playerHighTotal<=21){
            whoWins.setText("Push (Tie)");
            canvas.add(whoWins);
            whoWins.setCenter(630,300);
            amountLeftInBank = amountLeftInBank + totalBet;
            totalBet=0;
            resetRound();
        }
        if(playerHighTotal<dealerHighTotal &&dealerHighTotal<=21){
            whoWins.setText("You Lose");
            canvas.add(whoWins);
            whoWins.setCenter(630,300);
            totalBet=0;
            resetRound();
        }
        if(playerHighTotal<=21 && dealerHighTotal>21){
            whoWins.setText("You WIN!!!");
            canvas.add(whoWins);
            whoWins.setCenter(630,300);
            amountLeftInBank = amountLeftInBank + totalBet + totalBet;
            totalBet=0;
            resetRound();
        }
    }
}
