package blackjack;

/**
 * Represents a collection of cards (2 to 7).
 * 
 * @author trran
 */
public class Hand {
    
    private Card[]cards;
    private Deck deck;
    private int count;
    
    /**
     * Deals the deck of cards. 
     * @param deck 
     */
    public Hand(Deck deck){
        cards = new Card[7];
        this.deck = deck;
        count = 0;
        // deal two cards
        cards[count++]= deck.deal();
        cards[count++] = deck.deal();
    }
    
    /**
     * Gets a card.
     * @param index
     * @return 
     */
    public Card getCard(int index){
        return cards[index];
    }
    
    /**
     * Counts the cards in the hand.
     * @return 
     */
    public int getCount(){
        return count;
    }
    
    /**
     * Adds a card to the hand if the player presses 'hit'.
     */
    public void hit(){
        cards[count++]=deck.deal();
    }
    
    /**
     * Gets the total of the number of the hand.
     * @return 
     */
    public int getTotal(){
    int total = 0;
    int aceCount=0;
    for(int i=0; i<count; ++i){
       total += cards[i].getValue();
       if(cards[i].getFace()==Card.Face.ACE){
           ++aceCount;
       }
    }    
    while(total>21 && aceCount>0){
        --aceCount;
        total-=10;
    }
    return total;    
    }
}    
    

