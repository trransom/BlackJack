package blackjack;

/**
 * Represents a card in a deck of playing cards.
 *
 * @author trran
 */
public class Card {

    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    };

    enum Face {
        DUECE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    };

    private Suit suit;
    private Face face;
    private boolean faceUp;

    /**
     * Creates a method to enter the face and suit of a card.
     * @param face
     * @param suit 
     */
    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
        faceUp = true;
    }

    /**
     * Returns the Suit of the card.
     * @return 
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the face of the card.
     * @return 
     */
    public Face getFace() {
        return face;
    }

    /**
     * Makes the card faceup. 
     * @return 
     */
    public boolean isFaceUp() {
        return faceUp;
    }
    
   
    public void setFaceUp(boolean faceUp){
        this.faceUp = faceUp;
    }

    /**
     * Returns the value of 11 for an Ace, 10 for kings, queens, and jacks,
     * and the correct number for any other card by adding 2 to the ordinal.
     * @return 
     */
    public int getValue() {
        switch (face) {
            case ACE:
                return 11;
            case KING:
            case QUEEN:
            case JACK:
                return 10;
            default:
                return face.ordinal() + 2;
        }
    }

    /**
     * Returns a string displaying the correct name of the card.
     * @return 
     */
    @Override
    public String toString() {
        return face + " of " + suit;
    }

}
