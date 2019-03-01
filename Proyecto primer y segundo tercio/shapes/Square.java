package shapes;

import java.awt.*;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Square extends Figure{

    /**
     * Create a new square at default position with default color.
     */
    public Square(){
        height = 50;
        width = 50;
        xPosition = 10;
        yPosition = 10;
        color = "green";
        isVisible = false;
    }
       /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void delete()
    {
        // put your code here
        erase();
    }
    /*
     * Draw the square with current specifications on screen.
     */

    protected void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
            new java.awt.Rectangle(xPosition, yPosition, width, height));
            canvas.wait(10);
            
        }
        
    }
    public void resize(double n){
        ;}
}

