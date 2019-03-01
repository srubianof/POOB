

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EquipoTest{
   

    
    public EquipoTest(){
    }
    
    @Test
    public void deberiaCalcularElValorDeUnEquipo(){
        String [] nombres={"Pedro","Judas"};
        Equipo eq= new Equipo(nombres);
        try {
           assertEquals(60000,eq.valorHora());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }    
    }    
    
    @Test
    public void deberiaLanzarExcepcionSiElEquipoNoTienePersonas(){
        String [] nombres={};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanzó excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.EQUIPO_VACIO,e.getMessage());
        }    
    }    
  
   @Test
    public void deberiaLanzarExcepcionSiNoSeConoceElValorDeUnaPersona(){
        String [] nombres={"Pedro","Garcia","Juan"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_DESCONOCIDO,e.getMessage());
        }    
    }      
    
   @Test
    public void deberiaLanzarExcepcionSiNoSeConoceUnaPersona(){
        String [] nombres={"Pedro","Carlos","Juan"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.PERSONA_DESCONOCIDA,e.getMessage());
        }    
    }  
    
   @Test
   public void DeberiaAgregarValorPromedioAHoraEquipo(){
       String [] nombres={"Pedro","Santiago","Marcos","Juan","Judas","Garcia"};
       Equipo eq= new Equipo(nombres);
       try{
           int res = eq.valorHoraEstimado();
           assertEquals(res,180000);;
        }
       catch (EquipoExcepcion e) {
           System.out.println(e.toString());
        }
    }
   
   @Test
   public void DeberiaFallarPorPersonaDesconocida(){
       String [] nombres={"Pedro","Santiago","Marcos","Juan","Judas","Camilo"};
       Equipo eq= new Equipo(nombres);
       try{
           int res = eq.valorHoraEstimado();
           assertEquals(res,180000);;
        }
       catch (EquipoExcepcion e) {
           assertTrue(EquipoExcepcion.PERSONA_DESCONOCIDA.equals(e.getMessage()));
        }
    }
   @Test
   public void NoDeberiaCalcularValorEstimado(){
       String [] nombres={"Garcia","Ospina","Guarin","Judas"};
       Equipo eq= new Equipo(nombres);
       try{
           int res = eq.valorHoraEstimado();
           assertEquals(res,180000);;
        }
       catch (EquipoExcepcion e) {
           assertTrue(EquipoExcepcion.VALOR_IMPOSIBLE.equals(e.getMessage()));
        }
    }
}
