import java.awt.*;  // for Graphics and Container
import java.awt.geom.Ellipse2D; // for Gradient
/**
 * This creates a card for the 'Set' game (and draws it)
 *
 * @author Steve Harper
 * @version 1.2
 */

public class SampleCardForSet {
   // easier to use words than number codes
    final public static int OVAL = 0;
    final public static int STAR = 1;
    final public static int RECTANGLE = 2;
    //final public static int SQUIGGLY = 3;
    final public static int SOLID = 11;
    final public static int EMPTY = 12;
    final public static int HALF_FULL =13;
    final public static int STRIPES =14;
    final public static int DASHES = 15;

    final public static double marginPct = 0.10; // marginPct is 10% of card
    final public static double shapeSizePct = (1 - 4*marginPct)/3.0; // up to 3 shapes with margins between

    private Color cardColor;
    private int cardShape;
    private int cardFill;
    private int cardNumberOfShapes;

    /**
     * Constructor for objects of class CardForSet
     */
    public SampleCardForSet(Color MyColor, int shape, int fill, int numberOfShapes)
    {
       cardColor = MyColor;
       cardShape = shape;
       cardFill = fill;
       cardNumberOfShapes = numberOfShapes;
    }   //added texgt for commit

    public void draw(java.awt.Graphics gameGraphics, int topLeftCornerOfCardX, int topLeftCornerOfCardY, int widthOfCard, int heightOfCard )
    {   // draw border
        gameGraphics.setColor(Color.black);
        gameGraphics.drawRoundRect(topLeftCornerOfCardX, topLeftCornerOfCardY, widthOfCard, heightOfCard,10,10 );  // (x,y,w,h)
        int i,j;

        gameGraphics.setColor(cardColor);
        double yOffset = (2 - .5*(cardNumberOfShapes-1))*marginPct  + (1 - .5*(cardNumberOfShapes-1))*shapeSizePct;
        //********************* oval
        //********************* oval
        if (cardShape == OVAL){
            if (cardFill == EMPTY){
                for (i = 0; i < cardNumberOfShapes; i++){
                    gameGraphics.drawOval((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                }
            }else if (cardFill == SOLID){
               for (i = 0; i < cardNumberOfShapes; i++){
                    gameGraphics.fillOval((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                }
            }else if (cardFill == HALF_FULL){
               GradientPaint gp;
               Graphics2D g2d = (Graphics2D) gameGraphics;
               for (i = 0; i < cardNumberOfShapes; i++){
                    gp = new GradientPaint((float)(topLeftCornerOfCardX+widthOfCard*marginPct), (float) (topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)), cardColor, (float)(topLeftCornerOfCardX+.7*(1-2*marginPct)*widthOfCard), (float) (topLeftCornerOfCardY+.7*heightOfCard), Color.white) ;
                    g2d.setPaint(gp);
                    g2d.fill(new Ellipse2D.Float((float)(topLeftCornerOfCardX+widthOfCard*marginPct),(float)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(float)((1-2*marginPct)*widthOfCard),(float)(shapeSizePct*heightOfCard))); // (x,y,w,h)
                }
            } else if (cardFill == STRIPES)  {
               int numberOfBars = 5; // arbitrarily put 5 bars (or lines) on each oval
                for (i = 0; i < cardNumberOfShapes; i++){
                    gameGraphics.drawOval((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                    for (int barCounter=0; barCounter < numberOfBars; barCounter++){
                           gameGraphics.drawLine((int)(topLeftCornerOfCardX+widthOfCard*(marginPct+Math.abs(barCounter-2)/(2.0*numberOfBars+2.0)*(1-2*marginPct))),
                                (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+((barCounter+1.0)/(numberOfBars+1.0)*shapeSizePct))),
                            (int)(topLeftCornerOfCardX+widthOfCard*(1-marginPct-Math.abs(barCounter-2)/(2.0*numberOfBars+2.0)*(1-2*marginPct))),
                                (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+((barCounter+1.0)/(numberOfBars+1.0)*shapeSizePct))) );  // (x1,y1,x2,y2)
                    }
                }
            } else {// if (cardFill == DASHES) {
                Graphics2D g2d = (Graphics2D) gameGraphics;
                Stroke currentStroke = g2d.getStroke();  // save the current pen (regular line)
                float [] myDash = {1.0f,5.0f};
                g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,myDash,1.0f));
                for (i = 0; i < cardNumberOfShapes; i++) {
                    g2d.drawOval((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                }
                g2d.setStroke(currentStroke); // always reset the stroke since it is global
            }
        //********************* rectangle
        //********************* rectangle
        }else if (cardShape == RECTANGLE){
           if (cardFill == EMPTY){
                for (i = 0; i < cardNumberOfShapes; i++){
                    gameGraphics.drawRect((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                }
            }else if (cardFill == SOLID){
               for (i = 0; i < cardNumberOfShapes; i++){
                    gameGraphics.fillRect((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                }
            }else if (cardFill == HALF_FULL){
               GradientPaint gp;
               Graphics2D g2d = (Graphics2D) gameGraphics;
               for (i = 0; i < cardNumberOfShapes; i++){
                    gp = new GradientPaint((float)(topLeftCornerOfCardX+widthOfCard*marginPct), (float) (topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)), cardColor, (float)(topLeftCornerOfCardX+.7*(1-2*marginPct)*widthOfCard), (float) (topLeftCornerOfCardY+.7*heightOfCard), Color.white) ;
                    g2d.setPaint(gp);
                    g2d.fill(new Rectangle.Float((float)(topLeftCornerOfCardX+widthOfCard*marginPct),(float)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(float)((1-2*marginPct)*widthOfCard),(float)(shapeSizePct*heightOfCard))); // (x,y,w,h)
                }
            } else if (cardFill == STRIPES){
                int numberOfBars = 5; // arbitrarily put 5 bars (or lines) on each rectangle
                for (i = 0; i < cardNumberOfShapes; i++){
                    gameGraphics.drawRect((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                    for (int barCounter=0; barCounter < numberOfBars; barCounter++){
                           gameGraphics.drawLine((int)(topLeftCornerOfCardX+widthOfCard*marginPct),
                                (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+((barCounter+1.0)/(numberOfBars+1.0)*shapeSizePct))),
                            (int)(topLeftCornerOfCardX+(1-marginPct)*widthOfCard),
                                (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+((barCounter+1.0)/(numberOfBars+1.0)*shapeSizePct))) );  // (x1,y1,x2,y2)
                    }
                }
            } else {// if (cardFill == DASHES)
                  Graphics2D g2d = (Graphics2D) gameGraphics;
                  Stroke currentStroke = g2d.getStroke();  // save the current pen (regular line)
                  float [] myDash = {1.0f,5.0f};
                  g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,myDash,1.0f));
                  for (i = 0; i < cardNumberOfShapes; i++) {
                    g2d.drawRect((int)(topLeftCornerOfCardX+widthOfCard*marginPct),(int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)),(int)((1-2*marginPct)*widthOfCard),(int)(shapeSizePct*heightOfCard)); // (x,y,w,h)
                    }
                  g2d.setStroke(currentStroke); // always reset the stroke since it is global
            }
        //********************* star
        //********************* star
        }else {// cardShape == STAR
            int sides = 5;
            int [] myStarArrayX = new int[sides];
            int [] myStarArrayY = new int[sides];

            GradientPaint gp;  // for solid and half_full
            Graphics2D g2d = (Graphics2D) gameGraphics; // for solid, half_full and dashes
            Stroke currentStroke = g2d.getStroke();  // save the current pen (regular line)
            float [] myDash = {1.0f,5.0f};

            for (i = 0; i < cardNumberOfShapes; i++){ // note i goes 0 to 2 if 3 shapes
                myStarArrayX[0] = (int)(topLeftCornerOfCardX+.5*widthOfCard);
                myStarArrayX[1] = (int)(topLeftCornerOfCardX+widthOfCard*(1-marginPct));
                myStarArrayX[2] = (int)(topLeftCornerOfCardX+widthOfCard*marginPct);
                myStarArrayX[3] = (int)(topLeftCornerOfCardX+widthOfCard*(1-marginPct));
                myStarArrayX[4] = (int)(topLeftCornerOfCardX+widthOfCard*marginPct);
                myStarArrayY[0] = (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct));
                myStarArrayY[1] = (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+shapeSizePct));
                myStarArrayY[2] = (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+shapeSizePct*.5));
                myStarArrayY[3] = (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+shapeSizePct*.5));
                myStarArrayY[4] = (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+shapeSizePct));
                if (cardFill == EMPTY){
                    for (j=0; j < sides; j++){
                        gameGraphics.drawLine((int)myStarArrayX[j],(int)myStarArrayY[j],
                         (int)myStarArrayX[(j+1)%sides],(int)myStarArrayY[(j+1)%sides]);
                     }
                }else if (cardFill == SOLID){
                    gp = new GradientPaint((float)(topLeftCornerOfCardX), (float) (topLeftCornerOfCardY), cardColor, (float)(topLeftCornerOfCardX+widthOfCard), (float) (topLeftCornerOfCardY+heightOfCard), cardColor) ;
                    g2d.setPaint(gp);
                    g2d.fill(new Polygon(myStarArrayX,myStarArrayY,sides)); // (x[],y[],n)
                }else if (cardFill == HALF_FULL) {
                   //not use black:  gp = new GradientPaint((float)(topLeftCornerOfCardX+widthOfCard*marginPct), (float) (topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)), cardColor, (float)(topLeftCornerOfCardX+.7*(1-2*marginPct)*widthOfCard), (float) (topLeftCornerOfCardY+.7*heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+heightOfCard*(1-marginPct))), Color.black) ;
                    gp = new GradientPaint((float)(topLeftCornerOfCardX+widthOfCard*marginPct), (float) (topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct)), cardColor, (float)(topLeftCornerOfCardX+.6*widthOfCard), (float) (topLeftCornerOfCardY+.6*heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+shapeSizePct)), Color.white) ;
                    g2d.setPaint(gp);
                    g2d.fill(new Polygon(myStarArrayX,myStarArrayY,sides)); // (x[],y[],n)

                } else if (cardFill == STRIPES) {
                    int numberOfBars = 5; // arbitrarily put 5 bars (or lines) on each star
                    for (j=0; j < sides; j++){
                        gameGraphics.drawLine((int)myStarArrayX[j],(int)myStarArrayY[j],
                         (int)myStarArrayX[(j+1)%sides],(int)myStarArrayY[(j+1)%sides]);
                         for (int barCounter=0; barCounter < numberOfBars; barCounter++){
                              gameGraphics.drawLine((int)(topLeftCornerOfCardX+widthOfCard*(marginPct+(5-barCounter)/(2.0*numberOfBars+2.0)*(1-2*marginPct))),
                                    (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+((barCounter+1.0)/(numberOfBars+1.0)*shapeSizePct))),
                                (int)(topLeftCornerOfCardX+widthOfCard*(1-marginPct-(5-barCounter)/(2.0*numberOfBars+2.0)*(1-2*marginPct))),
                                    (int)(topLeftCornerOfCardY+heightOfCard*(yOffset+i*marginPct+i*shapeSizePct+((barCounter+1.0)/(numberOfBars+1.0)*shapeSizePct))) );  // (x1,y1,x2,y2)
                          }
                     }
                } else {// if (cardFill == DASHES)
                     g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,myDash,1.0f));
                     for (j = 0; j < sides; j++) {
                        g2d.drawLine((int)myStarArrayX[j],(int)myStarArrayY[j],
                         (int)myStarArrayX[(j+1)%sides],(int)myStarArrayY[(j+1)%sides]);
                    }
                    g2d.setStroke(currentStroke); // always reset the stroke since it is global
               }
            } // for (i = 0; i < cardNumberOfShapes; i++)// note i goes 0 to 2 if 3 shapes
        }
        return;
    }

}