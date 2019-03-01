package shapes;
import conquerWorld.*;
import java.awt.*;

/**
 * Manage the cash of the game.
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */
public class Cash
{
    // instance variables - replace the example below with your own
    private int cash;
    private long contCash;
    private int height;
    private long width;
    private long maxweight;
    private long xPosition;
    private long yPosition;
    private boolean isVisible;
    private String color;
    private Graphics g;
    private ConquerWorld c;
    

    /**
     * Constructor for objects of class Presupuesto
     */
    public Cash(int cash,int maxweight)
    {
        // initialise instance variables
        height = 10;
        width = maxweight;
        contCash = 0;
        xPosition = 0;
        yPosition = 0;
        isVisible = true;
        color = "blue";
        draw();
    }
    
    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,(new java.awt.Rectangle(0, 0,cash,height)));
        }
    }
    
    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void addCash(long newWidth) {
        erase();
        contCash+=newWidth;
        double x = (width*contCash)/1000000000;
        cash = (int)x;
        
        draw();
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public long getCash()
    {
        // put your code here
        return this.contCash;
    }



}
