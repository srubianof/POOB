package shapes;

/**
 * Cash background so the cash can be drawn
 * 
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */
public class fondoCash
{
    
    public fondoCash(int maxX) {
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, "red",(new java.awt.Rectangle(0, 0,maxX,10)));
    }
}
