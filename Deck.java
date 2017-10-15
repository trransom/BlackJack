package blackjack;

import acm.util.RandomGenerator;

/**
 * A deck of 52 playing cards 
 * 
 * @author trran
 */
class Deck {
    
    private Card [] deck;
    private int nextCard;
    
    /**
     * Declares the deck and calls the shuffle method.
     */
    public Deck(){
        deck = new Card[52];
        int i = 0;
        for(Card.Suit suit : Card.Suit.values()){
            for(Card.Face face : Card.Face.values()){
                deck[i++] = new Card(face, suit);
            }
        }
        shuffle();
    }    
    
    /**
     * Shuffles the cards by switching the positions of all 52 cards.
     */
    private void shuffle(){
        for(int i=0;i<deck.length;++i){
            int p = RandomGenerator.getInstance().nextInt(52);
            Card temp = deck[i];
            deck[i] = deck[p];
            deck[p] = temp;
        }
        nextCard = 0;
    }
        
    /**
     * Deals the cards.
     * @return 
     */
    public Card deal() {
        if(nextCard>=52){
            shuffle();
        }
        return deck[nextCard++];
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Card card : deck){
            sb.append(card);
            sb.append("\n");
        }
        return sb.toString();
    }
}
