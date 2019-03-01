package conquerWorld;

import shapes.*;
/**
 * The army that belongs to a specific Nation
 * 
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */
public class SteadyA extends Army
{
    /**
     * Constructor for objects of class NormalA
     */
    public SteadyA(int[] armies,int[] positions,Canvas mundo)
    {
        super(armies,positions,mundo);
    }
    /**
     * Constructor for objects of class NormalA
     */
    public SteadyA(int[] armies,int[] positions,int width,int height,Canvas mundo)
    {
        super(armies,positions,width,height,mundo);
    }
    /**
     * @return si es posible que la nacion realice ese comportamiento
     */
    public boolean canRemoveN(){
        return false;
    } 
}
