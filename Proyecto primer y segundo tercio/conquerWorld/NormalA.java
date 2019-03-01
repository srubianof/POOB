package conquerWorld;

import shapes.*;
/**
 * The army that belongs to a specific Nation
 * 
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */
public class NormalA extends Army
{
    /**
     * Constructor for objects of class NormalA
     */
    public NormalA(int[] armies,int[] positions,Canvas mundo)
    {
        super(armies,positions,mundo);
    }
    /**
     * Constructor for objects of class NormalA
     */
    public NormalA(int[] armies,int[] positions,int width,int height,Canvas mundo)
    {
        super(armies,positions,width,height,mundo);
    }
}
