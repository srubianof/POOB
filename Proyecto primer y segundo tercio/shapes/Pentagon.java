package shapes;

import java.awt.*;

/**
 * A pentagon that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Yeisson Gualdron and Santiago Rubiano
 * @version 9.3
 */

public class Pentagon extends Figure{


    /**
     * Create a new pentagon at default position with default color.
     */
    public Pentagon(){
        height = 30;
        width = 40;
        xPosition = 140;
        yPosition = 150;
        color = "green";
        isVisible = false;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /**
     * Draw the pentagon with current specifications on screen.
     */
    protected void draw(){
        if(isVisible) {

            
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition-width, xPosition, xPosition + width,xPosition + (width/2),xPosition-(width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition,yPosition-height,yPosition-height};
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 5));
            canvas.wait(10);
            
        }
    }
    /**
     * Change the size of the circle from the area
     */
    public void resize(double area){
        changeSize((int)Math.sqrt(2*area),(int)Math.sqrt(2*area));
            
    }
}
