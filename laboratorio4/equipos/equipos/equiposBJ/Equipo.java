import java.util.ArrayList;

public class Equipo{
    private ArrayList<Persona> personas = new ArrayList<Persona>();
    
    /**
     * Crea un equipo dado el nombre de sus miembros
     * @param nombres nombres de los miembros del equipo
     */    
    public Equipo(String [] nombres){
        personas= new ArrayList<Persona>();
        for (int i=0; i< nombres.length;i++){
            personas.add(new Persona(nombres[i]));
        }    
    }

    /**
     * Calcula el valor hora de un equipo
     */
    public int valorHora() throws EquipoExcepcion{
        int valor  = 0;
        if(personas.size()>0){
            for(Persona p: personas ){
                try{
                    valor += p.valorHora();   
                    //System.out.println(p.getName());
                }
                catch(EquipoExcepcion e){
                    // System.out.println(e.toString());
                    throw e;
                }
            }
            return valor;
        }
        else {
            throw new EquipoExcepcion(EquipoExcepcion.EQUIPO_VACIO);
        }                    
    }
    
    /**
     * Calcula el valor hora estimado de un equipo.
     * El valor estimado de una persona a la que no se conoce su valor es la
     * media de los valores conocidos
     * Más del 75% del equipo debe tener valores conocidos 
     * @return el valor hora del equipo
     * @throws EquipoException si en el equipo hay una persona desconocida
     * o si no es posible calular el valor estimado
     */
    public int valorHoraEstimado() throws EquipoExcepcion{
        int value  = 0;
        int cp,nElements=0;
        int vConocido=0;
        double promedio=0;
        
        for(Persona p: personas){
            try{
               cp = p.valorHora();
               value += cp;
               nElements+=1;
               vConocido+=1;
            }
            catch( EquipoExcepcion e ){
                if (EquipoExcepcion.VALOR_DESCONOCIDO.equals(e.getMessage())){
                    try{
                        value+=(value/nElements);
                        nElements+=1;
                    }
                    catch(Exception ex){
                        nElements+=1;
                        continue;
                    }
                }
                if (EquipoExcepcion.PERSONA_DESCONOCIDA.equals(e.getMessage())){
                    throw e;
                }
            }
        }
        int porcentaje = (vConocido*100)/nElements;
        if(porcentaje>75){
            return value;
        }
        else{
            throw new EquipoExcepcion(EquipoExcepcion.VALOR_IMPOSIBLE);
        }
    }
    
    
    

    
    
}
