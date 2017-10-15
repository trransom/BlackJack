package blackjack;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import svu.csc213.Dialog;

/**
 * This program creates the card game 'Black Jack'. 
 * @author trran
 */

public class BlackJack extends GraphicsProgram {
    
    private static final int INIT_BALANCE = 1000;
    
    private JButton wagerButton;
    private JButton playButton;
    private JButton hitButton;
    private JButton stayButton;
    private JButton exitButton;
    private GLabel welcome;
    private GLabel wagerLabel;
    private GLabel balanceLabel;
    private int wager = 10;
    private int balance = INIT_BALANCE;
    private Deck deck = new Deck(); 
    private GHand player; 
    private GHand dealer;
     

        /**
         * Initializes the Wager, Play, Hit, Stay, and Exit buttons. Sets the 
         * size and placement of the wager and balance labels. 
         */
    @Override
        public void init(){
           wagerButton = new JButton("Wager");
           add(wagerButton,SOUTH);
           playButton = new JButton("Play");
           add(playButton,SOUTH);
           hitButton = new JButton("Hit");
           add(hitButton,SOUTH);
           stayButton = new JButton("Stay");
           add(stayButton,SOUTH);
           exitButton = new JButton("Exit");
           add(exitButton,SOUTH);
           wagerLabel = new GLabel("Wager: $" + wager);
           wagerLabel.setFont("times-bold-20");                                        
           add(wagerLabel, 50, getHeight()-50);
           balanceLabel = new GLabel("Balance: $" + balance);
           balanceLabel.setFont("times-bold-20");
           add(balanceLabel, getWidth()-balanceLabel.getWidth()-50,getHeight()-50);
           setBackground(Color.green);
           addActionListeners();
           hitButton.setEnabled(false);
           stayButton.setEnabled(false);
           
           Font d = new Font("SansSerif", Font.BOLD, 64);
           welcome = new GLabel("BlackJack");
           welcome.setFont(d);
           add(welcome, 260, 0);
           for(int i=0; i<100; i++){
               add(welcome, 240, -20+(i*3));
               pause(10);
           }
           
          
           
        }   
       
       
    @Override
      /*
       * Activates the wager, play, hit, stay, and quit methods if the 
       * user presses the buttons. 
       */
       public void actionPerformed(ActionEvent ae){
           switch(ae.getActionCommand()){
               case "Wager":
                   remove(welcome);
                   wager();
                   break;
               case "Play":
                   remove(welcome);
                   play();
                   break;
               case "Hit":
                   hit();
                   break;
               case "Stay":
                   stay();
                   break;
               case "Exit":
                   quit();
                   break;
           }
       }
       
       /*
        * Initializes the play by dealing hands for the dealer and the player. 
        */
       public void play(){
          wagerButton.setEnabled(false);
          playButton.setEnabled(false);
          hitButton.setEnabled(true);
          stayButton.setEnabled(true);
          exitButton.setEnabled(false);
          paint(getGraphics()); 
          if(dealer!=null){
              this.remove(dealer);
          }
          if(player!=null){
              this.remove(player);
          }
          deck = new Deck();
          dealer = new GHand(new Hand(deck));
          dealer.getCard(0).setFaceUp(false);
          add(dealer, 25, 50);
          player = new GHand(new Hand(deck));
          add(player, 25, 200);
          if(player.getTotal()==21 || dealer.getTotal()==21){
              resolve();
          }
          
       }
       
      /**
       * Adds a card to the player's hand.
       */
       private void hit(){
          if(player.getCount()<7){
          player.hit();
       }
       if(player.getCount()>=7 || player.getTotal()>21){
           resolve();
       }        
    }
       
       /**
        * Deals cards to the dealer when the user presses 'Stay'
        */
       private void stay(){
          dealer.getCard(0).setFaceUp(true);
          paint(this.getGraphics());
          pause(1000);
          while(dealer.getTotal()<16){
              dealer.hit();
              pause(1000);                                            
          }                                                           
          resolve();                                                  
       }
       
       /**
        * Exits the program and displays how much the player won or lost.
        */
       private void quit(){
           if(balance>INIT_BALANCE){
               Dialog.showMessage(this,"Congratulations, you won $" +(balance-INIT_BALANCE));
           }  else if(balance<INIT_BALANCE){
               Dialog.showMessage(this,"Too bad! You lost $" + (INIT_BALANCE - balance));
           } else {
              Dialog.showMessage("See you next time!");
           }
           System.exit(0);
       }
       /**
        * Calls the win, loss, or tie methods depending on the total count in
        * the player's and dealer's hands.
        */
       private void resolve(){
          //figure out who won
          //resolve the bet
          wagerButton.setEnabled(true);
          playButton.setEnabled(true);
          hitButton.setEnabled(false);
          stayButton.setEnabled(false);
          exitButton.setEnabled(true);
          paint(this.getGraphics());
          if (player.getTotal()>21){
              loss();
             } else if (dealer.getTotal()>21){
               win();
             } else if(player.getCount()>=7){
               win();
             }else if(player.getTotal()>dealer.getTotal()){
               win();
             }else if(dealer.getTotal()>player.getTotal()){
               loss();
             }else if(dealer.getTotal()==player.getTotal()){
               tie();
          }
          }
                    
       /**
        * Displays the message 'It's a tie!' if it's a tie.
        */   
       private void tie(){
           Dialog.showMessage(this, "It's a tie!");
           reset();
       }
       
       /**
        * Displays the message "You win!" if the player wins and updates the 
        * balance label.
        */
       private void win() {
           Dialog.showMessage(this, "You win!");
           balance += wager;
           updateBalanceLabel();
           balanceLabel.setLocation(getWidth()-balanceLabel.getWidth()-50,getHeight()-10);
           reset();
       }
       
       /**
        * Displays the message "You lose!" if the player loses and updates the 
        * balance label.
        */
       private void loss() {
           Dialog.showMessage(this, "You lose!");
           balance -= wager;
           updateBalanceLabel();
           balanceLabel.setLocation(getWidth()-balanceLabel.getWidth()-30,getHeight()-10);
           reset();
       }
       
       /**
        * Sets the Wager and Play button back to being activated.
        */
       private void reset() {
           remove(player);
           remove(dealer);
           playButton.setEnabled(true);
           hitButton.setEnabled(false);
           stayButton.setEnabled(false);
           wagerButton.setEnabled(true);
           paint(this.getGraphics()); //?????
       }
       
       /**
        * Asks the user to put in a value between 1 and their current balance.
        */
       private void wager(){
           boolean goodInput = false;
           while(!goodInput){
               int theWager = Dialog.getInteger(this, "What do you wager?");
               if(theWager>0 && theWager<=balance){
                   wager = theWager;
                   updateWagerLabel();
                   goodInput = true;
               } else {
                   Dialog.showMessage(this, "Please enter a value %n between 1 and " + balance);
               }
           }
       }
       
       /**
        * Sets the wager label according to the amount entered by the user.
        */
       private void updateWagerLabel(){
           wagerLabel.setLabel("Wager: $" + wager);
       }
       
       /**
        * Sets the balance label according to the input of the user. 
        */
       private void updateBalanceLabel(){
               balanceLabel.setLabel("Balance: $" + balance);
               balanceLabel.setLocation(
                       getWidth()-balanceLabel.getWidth()-50, 
                       balanceLabel.getHeight());
                       repaint();    
           }
       
       private void horizontalLights(int i, int j) {
           GOval light1 = new GOval(20,20);
               add(light1, i, j);
               GOval light2 = new GOval(20,20);
               add(light2, i+20, j);
               GOval light3 = new GOval(20,20);
               add(light3, i+40, j);
               
               boolean lights = true;
               while(lights){
               
               light1.setFilled(true);
               light1.setColor(Color.yellow);
               pause(50);
               light1.setFilled(false);
               pause(70);
               
               light2.setFilled(true);
               light2.setColor(Color.yellow);
               pause(50);
               light2.setFilled(false);
               pause(70);
               
               light3.setFilled(true);
               light3.setColor(Color.yellow);
               pause(50);
               light3.setFilled(false);
               pause(70);
               }
       }
           
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new BlackJack().start();
    }
    
 }
