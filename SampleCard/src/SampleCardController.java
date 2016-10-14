import java.awt.*;  // for Graphics and Container
import java.awt.event.*; // for mouse
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This a class to set up for playing the game Set
 * This will create a mouse listener and a repainting function
 * All nine cards are painted every time.
 * @author Steve Harper
 * @version 1.1
 */

public class SampleCardController extends JFrame  implements MouseListener {
	private static final long serialVersionUID = 1L;
	// may need other variables storing placement of buttons and cards in % of window size
    final public int NUMBER_OF_CARDS = 9;
    SampleCardForSet[] testCard;
   int cardXPosition[] = new int[NUMBER_OF_CARDS];
   int cardYPosition[] = new int[NUMBER_OF_CARDS];
   int cardMargin = 20;  //arbitrary guess
   int titleBarOffset = 20;  //arbitrary guess
   int cardWidth;
   int cardHeight;

   public SampleCardController(){
   		setSize(800,800);
        setBackground(Color.white);
        cardWidth = (getSize().width-4*cardMargin)/3; // for 3 cards, 4 margins between
        cardHeight = (getSize().height-4*cardMargin-titleBarOffset)/3; // for 3 cards, 4 margins between
        // put any setup code here, such as:
        cardXPosition[0] = cardXPosition[3] = cardXPosition[6] = 0+cardMargin;  // first column
        cardXPosition[1] = cardXPosition[4] = cardXPosition[7] = cardWidth + 2*cardMargin; //2nd column
        cardXPosition[2] = cardXPosition[5] = cardXPosition[8] = cardWidth * 2 + 3*cardMargin; // 3rd column
        cardYPosition[0] = cardYPosition[1] = cardYPosition[2] = 0 +cardMargin +titleBarOffset;  //1st row
        cardYPosition[3] = cardYPosition[4] = cardYPosition[5] = cardHeight + 2*cardMargin +titleBarOffset; // 2nd row
        cardYPosition[6] = cardYPosition[7] = cardYPosition[8] = cardHeight * 2 + 3*cardMargin +titleBarOffset; //3rd row

        testCard = new SampleCardForSet[NUMBER_OF_CARDS];

        testCard[0] = new SampleCardForSet(Color.green, SampleCardForSet.RECTANGLE, SampleCardForSet.SOLID, 1); //color,shape,fill,# of shapes
        testCard[1] = new SampleCardForSet(Color.green, SampleCardForSet.RECTANGLE, SampleCardForSet.HALF_FULL, 2); //color,shape,fill,# of shapes
        testCard[2] = new SampleCardForSet(Color.green, SampleCardForSet.RECTANGLE, SampleCardForSet.DASHES, 3); //color,shape,fill,# of shapes
        testCard[3] = new SampleCardForSet(Color.blue, SampleCardForSet.STAR, SampleCardForSet.EMPTY, 2); //color,shape,fill,# of shapes
        testCard[4] = new SampleCardForSet(Color.blue, SampleCardForSet.OVAL, SampleCardForSet.HALF_FULL, 3); //color,shape,fill,# of shapes
        testCard[5] = new SampleCardForSet(Color.blue, SampleCardForSet.OVAL, SampleCardForSet.STRIPES, 1); //color,shape,fill,# of shapes
        testCard[6] = new SampleCardForSet(Color.red, SampleCardForSet.STAR, SampleCardForSet.SOLID, 3); //color,shape,fill,# of shapes
        testCard[7] = new SampleCardForSet(Color.red, SampleCardForSet.STAR, SampleCardForSet.HALF_FULL, 2); //color,shape,fill,# of shapes
        testCard[8] = new SampleCardForSet(Color.red, SampleCardForSet.STAR, SampleCardForSet.STRIPES, 1); //color,shape,fill,# of shapes
        addMouseListener(this);
        setVisible(true);
        repaint();

        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint(); // repaint function eventually calls paint(Graphics g) below
           }
        };
   new Timer(delay, taskPerformer).start();

      }


 


      public void paint(Graphics g) { // called evertime need to repaint JFrame
    	  //should put in code to check size of window, and change positions
        for (int i=0; i < NUMBER_OF_CARDS; i++){ // use TicTacToeSquares 0 to 8   top row: 0, 1, 2
                testCard[i].draw(g, cardXPosition[i], cardYPosition[i], cardWidth, cardHeight);
        }
        g.drawString("Try to avoid clicking",getSize().width/2-40,getSize().height/2+20); // arbitrary placement

      }

      public void mousePressed(MouseEvent evt) {
        // in this case, I move card 4 no matter where clicked in the JFrame
          int c = 4;

          java.util.Random r = new java.util.Random();
          int randomX = (int) (r.nextFloat()*40); // abitrary movement
          int randomY = (int) (r.nextFloat()*30) ;  // abitrary movement
          cardXPosition[c] = (cardXPosition[c] + randomX) % (getSize().width-cardWidth);
          cardYPosition[c] = (cardYPosition[c] + randomY) % (getSize().height-cardHeight);
          repaint();  // eventually calls the paint function which calls paintComponent( ) above
      }


      // Empty methods, required by the MouseEvent and MouseListener intefaces.
      // you can code these if desired
      public void mouseClicked(MouseEvent evt) {  }
      public void mouseReleased(MouseEvent evt) {  }
      public void mouseEntered(MouseEvent evt) { }
      public void mouseExited(MouseEvent evt) { }
      
         public static void main(String [] args){
	   SampleCardController myGame = new SampleCardController();
         }

}  // end class DrawCards2