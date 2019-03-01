package conquerWorld;
import shapes.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.*;

/**
 * A route connects two nations with a cost to move armies between them.
 * 
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */

public class Route
{
    // instance variables - replace the example below with your own
    private int[] aPosition, bPosition;
    private int cost;
    private boolean isVisible = false;
    private String color;
    private String nationA,nationB;
    private int x1,y1,x2,y2;
    private Line line;

     /**
     // * Constructor for objects of class Ruta
     // */
     public Route(int[] aPos,int[] bPos,int cost,String from,String to)
     {
        // initialise instance variables
        this.nationA=from;
        this.nationB=to;
        this.cost=cost;
        color = "black";
        
        x1=aPos[0];
        y1=aPos[1];
        x2=bPos[0];
        y2=bPos[1];
        line = new Line(x1,y1,x2,y2);
        
    }        
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void makeVisible(){
        // put your code here
        line.makeVisible();
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void makeInvisible(){
        // put your code here
        line.makeInvisible();
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void removeR(int[] aPos,int[] bPos)
    {
        // put your code here
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public String getFrom()
    {
        return nationA;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public String getTo()
    {
        // put your code here
        return nationB;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getCost()
    {
        // put your code here
        return this.cost;
    }

}