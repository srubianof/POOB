package excepcion;


/**
 * Write a description of class IemoisExcepcion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IemoisExcepcion extends Exception
{
    public static final String VACIO = "No puede ser un campo vacio!";
    public static final String  REPETIDO= "El curso ya se ha agregado!";
    public static final String MAYSTRI="La primera letra debe ser mayuscula!";
    public static final String INT="Debe ser entero!";
    
    /**
     * Constructor for objects of class IemoisExcepcion
     */
    public IemoisExcepcion(String message){
        super(message);
    }
}
