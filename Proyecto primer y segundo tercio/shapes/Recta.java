package shapes;

import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Recta extends Figure{

    /**
     * Create a new rectangle at default position with default color.
     */
    public Recta(){
        height = 30;
        width = 70;
        xPosition = 10;
        yPosition = 10;
        color = "green";
        isVisible = false;
    }
       
    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    /*
     * Draw the rectangle with current specifications on screen.
     */
    protected void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, width, height));
            canvas.wait(10);
        }
    }
    /**
     * Change the size of the circle from the area
     */
    public void resize(double area){
        changeSize((int)Math.sqrt(2*area),(int)(Math.sqrt(2*area))/2);
            
    }
}

