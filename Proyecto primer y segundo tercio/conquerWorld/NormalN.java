package conquerWorld;


/**
 * A nation that contains a determined shape and a determined color.
 * 
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */
public class NormalN extends Nation
{
    /**
     * Constructor for objects of class Normal
     */
    public NormalN(String shape,int area,String color,int[] pos,int[] armies,shapes.Canvas mundo)
    {
        // initialise instance variables
        super(shape,area,color,pos,armies,mundo);
    }
}
