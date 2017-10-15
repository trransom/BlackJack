package blackjack;

import acm.graphics.GCompound;

/**
 * Creates the graphical representation of the hand of cards.
 * @author trran
 */
public class GHand extends GCompound {
    
    private Hand hand;
    private GCard [] gcards;
    
    /**
     * Uses graphics code to set the cards in the hand next to each other.
     * @param hand 
     */
    public GHand(Hand hand){
        this.hand = hand;
        gcards = new GCard[7];
        for(int i=0;i<hand.getCount();++i){
            Card c = hand.getCard(i);
            GCard g = new GCard(c);
            add(g, i*(g.getWidth()+(g.getWidth()/4.0)), 0);
            gcards[i] = g;
        }
    }
    
    /**
     * Returns the count of the hand.
     * @return 
     */
    public int getCount(){
       return hand.getCount();
    }
    
    /**
     * Returns the index number of the card.
     * @param index
     * @return 
     */
    public GCard getCard(int index){
        return gcards[index];
    }
    
    /**
     * Adds a card when the user presses 'hit'.
     */
    public void hit(){
        hand.hit();
        //graphics code goes here
        Card c = hand.getCard(getCount()-1);
        GCard g = new GCard(c);
        add(g, (getCount()-1)*(g.getWidth()+(g.getWidth()/4.0)), 0);
        gcards[getCount()-1] = g;
            
    }
    
    /**
     * Returns the total of the hand.
     * @return 
     */
    public int getTotal(){
        return hand.getTotal();
    }
}
