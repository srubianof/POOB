package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Figure{

    public static double PI=3.1416;
    
    public String color;
    private double area;
    private double radius;
    
    /**
     * Create a new circle at default position with default color.
     */
    public Circle(){
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
    /**
     * Deletes the current shape
     *
     */
    public void delete()
    {
        // put your code here
        erase();
    }
   
    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter,int n){
        erase();
        diameter = newDiameter;
        draw();
    }
        
    /**
     * Draw the circle with current specifications on screen.
     */
    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    /**
     * Change the size of the circle from the area
     */
    public void resize(double area){
        changeSize((int)(2* Math.pow(area/PI,0.5)),0);
    }
}
