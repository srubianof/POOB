package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.geom.*;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6 (shapes)
 */
public class Canvas{
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static Canvas canvasSingleton;
    /**
     * Factory method to get the canvas singleton object.
     */
    
    public static Canvas getCanvas(){
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Shapes Demo", 300, 300,Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }
    /**
     * Factory method to get the canvas singleton object.
     */
    public static Canvas getCanvas(int maxX,int maxY){
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Shapes Demo", maxX, maxY,Color.green);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    //  ----- instance part -----
    
    private JFrame frame;
    private CanvasPane canvas;
    public Graphics2D graphic;
    private Color backgroundColour;
    private Image canvasImage;
    private List <Object> objects;

    private HashMap <Object,ShapeDescription> shapes;
    private double zoomfactor=1;
    private double prevZoomFactor=1;
    private boolean zoomer;
    
    public static final Color VERY_LIGHT_RED = new Color(255,102,102);
    public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);
    public static final Color VERY_LIGHT_GREEN = new Color(102,255,102);
    public static final Color LIGHT_YELLOW = new Color(255,255,153);
    public static final Color LIGHT_ORANGE = new Color(255,102,0);
    public static final Color GOLD = new Color(255,204,51);
    public static final Color GREY = new Color(255,102,102);
    public static final Color CHERRY = new Color(204,0,102);
    public static final Color VERY_LIGHT_PURPLE = new Color(203,153,255);
    public static final Color DARK_PURPLE = new Color(76,0,153);
    public static final Color darkgreen = new Color(34,139,39);

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void sign(String a)
    {
        zoomer = true;
        if (a.equals("+")){
            zoomfactor *= 1.5;
            canvas.repaint();
        }
        if (a.equals("-")){
            
            zoomfactor/=1.5;
            canvas.repaint();
        }
        erase();
        graphic.scale(zoomfactor,zoomfactor);
        redraw();
    }
                
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    private Canvas(String title, int width, int height, Color bgColour){
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = bgColour;
        frame.pack();
        objects = new ArrayList <Object>();
        shapes = new HashMap <Object,ShapeDescription>();
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible){
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object referenceObject, String color, Shape shape){
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object referenceObject){
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    public void setForegroundColor(String colorString){
        
        if(colorString.equals("red"))
            graphic.setColor(Color.red);
        else if(colorString.equals("black"))
            graphic.setColor(Color.black);
        else if(colorString.equals("blue"))
            graphic.setColor(Color.blue);
        else if(colorString.equals("yellow"))
            graphic.setColor(Color.yellow);
        else if(colorString.equals("green"))
            graphic.setColor(Color.green);
        else if(colorString.equals("magenta"))
            graphic.setColor(Color.magenta);
        else if(colorString.equals("white"))
            graphic.setColor(Color.white);
            
        else if(colorString.equals("cyan"))
            graphic.setColor(Color.cyan);
        else if(colorString.equals("lightgray"))
            graphic.setColor(Color.lightGray);
        else if(colorString.equals("orange"))
            graphic.setColor(Color.orange);
        else if(colorString.equals("very light red"))
            graphic.setColor(VERY_LIGHT_RED);
        else if(colorString.equals("very light blue"))
            graphic.setColor(VERY_LIGHT_BLUE);
        else if(colorString.equals("very light green"))
            graphic.setColor(VERY_LIGHT_GREEN);
        else if(colorString.equals("light yellow"))
            graphic.setColor(LIGHT_YELLOW);
        else if(colorString.equals("light orange"))
            graphic.setColor(LIGHT_ORANGE);
        else if(colorString.equals("gold"))
            graphic.setColor(GOLD);
        else if(colorString.equals("grey"))
            graphic.setColor(GREY);
        else if(colorString.equals("cherry"))
            graphic.setColor(CHERRY);
        else if(colorString.equals("very light purple"))
            graphic.setColor(VERY_LIGHT_PURPLE);
        else if(colorString.equals("dark purple"))
            graphic.setColor(DARK_PURPLE);
        else if(colorString.equals("dark green"))
            graphic.setColor(darkgreen);
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e){
            // ignoring exception at the moment
        }
    }
    
    /**
     * Redraw ell shapes currently on the Canvas.
     */
    private void redraw(){
        // erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
                       shapes.get(i.next()).draw(graphic);
        }
        canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    public void erase(){
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = canvas.getSize();
        graphic.fill(new java.awt.Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
        // for(Object ss: objects){
            // erase(ss);
        // }
        
    }



    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    public class CanvasPane extends JPanel{
        public void paint(Graphics g){
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            if (zoomer){
                AffineTransform at = new AffineTransform();
                at.scale(zoomfactor,zoomfactor);
                prevZoomFactor = zoomfactor;
                g2.transform(at);
                zoomer=false;
            }
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription{
        private Shape shape;
        private String colorString;

        public ShapeDescription(Shape shape, String color){
            this.shape = shape;
            colorString = color;
        }

        public void draw(Graphics2D graphic){
            setForegroundColor(colorString);
            graphic.fill(shape);
            graphic.setStroke(new BasicStroke(10));
        }
    }
    
        /**
     * Draws a String on the Canvas.
     * @param  text   the String to be displayed 
     * @param  x      x co-ordinate for text placement 
     * @param  y      y co-ordinate for text placement
     */
    public void drawString(String text, int x, int y)
    {
        graphic.setColor(Color.black);  
        graphic.setFont(new Font("Arial",Font.PLAIN,10));
        graphic.drawString(text, x, y);   
        canvas.repaint();
    }
    
    /**
     * Erases a String on the Canvas.
     * @param  text     the String to be displayed 
     * @param  x        x co-ordinate for text placement 
     * @param  y        y co-ordinate for text placement
     */
    public void eraseString(String text, int x, int y)
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        graphic.drawString(text, x, y);   
        graphic.setColor(original);
        canvas.repaint();
    }

}