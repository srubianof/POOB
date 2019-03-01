import java.util.HashMap;

public class Persona{
    private static HashMap <String,Integer> datos;
    
    static{
        datos=new HashMap<String,Integer>();
        datos.put("Pedro",10000);
        datos.put("Santiago",20000);
        datos.put("Marcos",30000);
        datos.put("Juan",40000);
        datos.put("Judas",50000); 
        
        datos.put("Garcia",0);
        datos.put("Ospina",0);
        datos.put("Guarin",0);
    }    
    
    private String nombre;

    /**
     * Crea una persona
     * 
     */
    public Persona(String nombre){
        this.nombre=nombre;
    }    
    
    /**
     * Modifica el valor de una persona
     *
     * @param  persona y su valor nuevo
     */
    public void setValor(String x,int y)
    {
        datos.replace(x,y);
    }
    
    /**
     * retorna el nombre
     * @return    name
     */
    public String getName()
    {
        // put your code here
        return nombre;
    }
    /**
     * retorna la hora
     * @return    valor
     */
    public int getHora()
    {
        Integer valor=datos.get(nombre);
        return valor;
    }


    /**
     * Retorna el valor hora de un equipo
     * @return valor
     * @throws EquipoExcepcion si no se conoce la persona o su valor hora
     */
    public int valorHora() throws EquipoExcepcion{
       Integer valor=datos.get(nombre);
       if (valor==null) throw new EquipoExcepcion(EquipoExcepcion.PERSONA_DESCONOCIDA);
       if (valor==0) throw new EquipoExcepcion(EquipoExcepcion.VALOR_DESCONOCIDO);
       return valor;
    }
}
