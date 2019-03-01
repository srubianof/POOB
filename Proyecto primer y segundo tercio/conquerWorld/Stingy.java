package conquerWorld;
import shapes.*;
import java.util.*;

/**
 * The army that belongs to a specific Nation
 * 
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */
public class Stingy extends Army
{
    
    /**
     * Constructor for objects of class NormalA
     */
    public Stingy(int[] armies,int[] positions,Canvas mundo)
    {
        super(armies,positions,mundo);
    }
    /**
     * Constructor for objects of class S
     */
    public Stingy(int[] armies,int[] positions,int width,int height,Canvas mundo)
    {
        super(armies,positions,width,height,mundo);
        
    }
    /**
     * @return si es posible que la nacion realice ese comportamiento
     */
    public boolean canRemoveB(ArrayList<Route> routes, String fromNation, String toNation,Route route){
        int min=429496729;
        for(Route r: routes){
            if(r.getFrom().equals(fromNation)){
                if(r.getCost()<min){ min = r.getCost();}
            }
        }
        return (min ==route.getCost())? true: false;
    } 

}
