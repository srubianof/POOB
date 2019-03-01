package shapes;


/**
 * Abstract class Figure - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Figure
{
    // instance variables - replace the example below with your own
    protected int diameter;
    protected int xPosition;
    protected int yPosition;
    public String color;
    protected boolean isVisible = false;
    protected double area;
    protected double radius;
    protected int height;
    protected int width;
 
   /**
     * Deletes the current shape
     *
     */
    public void delete()
    {
        erase();
    }

    /**
     * Make this figure visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this figure invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        
        erase();
        isVisible = false;
    }  
    
    /**
     * Change the old position to a new position
     */
    public void changePos(int newX, int newY) {
        erase();
        xPosition = newX;
        yPosition = newY;
        draw();
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    /**
     * Erase the figure on screen.
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
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
    /**
     * Draw the figure with current specifications on screen.
     */
    protected abstract void draw();
    /**
     * Change the size of the figure from the area
     */
    public abstract void resize(double n);
}

