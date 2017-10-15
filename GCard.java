package blackjack;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;
import java.awt.Color;

/**
 * Creates a graphical representation of 52 playing cards.
 * @author trran
 */
public class GCard extends GCompound {
    
   private Card card;
   private GRect back;
   
   /**
    * Uses the file 'cardgifs' to get the images of the cards and then sets the 
    * color of the back of the cards.
    * @param card 
    */
   public GCard(Card card){
       this.card = card;
       String fileName = "cardgifs/" + 
            card.getSuit().toString().substring(0, 1).toLowerCase()+
            (card.getFace().ordinal()+2)+
            ".gif";
       GImage image = new GImage(fileName);
       add(image, 1,1);
       GRect border = new GRect(109, 152);
       add(border);
       back = new GRect(107, 150);
       back.setFilled(true);
       back.setFillColor(Color.CYAN);
       add(back, 1, 1);
       back.setVisible(!card.isFaceUp());
       this.scale(.65);
   }
   
   /**
    * returns the card face up.
    * @return 
    */
   public boolean isFaceUp(){
       return card.isFaceUp();  
   }
   
   /**
    * Makes the card visible.
    * @param faceUp 
    */
   public void setFaceUp(boolean faceUp){
       card.setFaceUp(faceUp);
       back.setVisible(!card.isFaceUp());
   }
   
   /**
    * Gets the value of the card.
    * @return 
    */
   public int getValue(){
       return card.getValue();
   }
    
}
