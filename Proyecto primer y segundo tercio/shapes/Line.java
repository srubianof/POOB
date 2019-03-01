package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * A pentagon that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Yeisson Gualdron and Santiago Rubiano
 * @version 9.3 
 */

public class Line extends Figure{
    public String color;
    private int x1,y1,x2,y2;

    /**
     * Create a new pentagon at default position with default color.
     */
    public Line(int x1,int y1,int x2,int y2){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        
        color = "black";
        isVisible = false;
    }
    
    /**
     * Draw the line with current specifications on screen.
     */
    public void draw(){
        if(isVisible) {
            int[] xpoints = { x1, x2, x2, x1};
            int[] ypoints = { y1, y2, y2+5, y1+5};
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 4));
            canvas.wait(10);
            
        }
    }
    public void resize(double n){
        ;
    }
    public void changeSize(int newHeight, int newWidth){
        ;
    }
}
