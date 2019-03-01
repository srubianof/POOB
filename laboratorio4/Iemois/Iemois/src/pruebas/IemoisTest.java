package pruebas;

import java.util.*;
import java.util.LinkedList;
import java.util.ArrayList;
import excepcion.*;
import java.lang.Object;
import registro.*;
import excepcion.*;
import aplicacion.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

    
    public class IemoisTest{
    public IemoisTest(){
    }


    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }
    @Test
    public void deberiaAdicionar() throws IemoisExcepcion{
        
        Iemois s= new Iemois();
        int size= s.numerocursos();
        s.adicione("Machine Learning","Inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
        assertTrue(size+1== s.numerocursos());
    }
    @Test
    public void deberiaListar() throws IemoisExcepcion{
        Iemois s= new Iemois();
        String consulta1=s.toString();
        s.adicione("Machine Learning","Inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
        String consulta2=s.toString();
        assertFalse(consulta1==consulta2);
    }
    @Test
    public void deberiaFallarSinDistribuidor(){
        Iemois s= new Iemois();
        try {
             s.adicione("Machine Learning","Inteligencia Artificial","Coursera","","9");
             fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) { 
             assertEquals(e.getMessage(), IemoisExcepcion.VACIO);
        }
    }
    @Test
    public void deberiaFallarConAreaRepetida(){
        Iemois s= new Iemois();
        try {
            s.adicione("Machine Learning","Inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
            s.adicione("Machine Learning","Inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
            fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) {
            assertEquals(e.getMessage(), IemoisExcepcion.REPETIDO);
        }
    }
    @Test()
    public void deberiaFallarConSemanasIncorrectas(){
        Iemois s= new Iemois();
        try {
            s.adicione("Q","Q","Q","Q","Q");
            fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) {
            assertEquals(e.getMessage(), IemoisExcepcion.INT);
        }
    }
    @Test
    public void deberiaFallarSinNombre(){
        Iemois s= new Iemois();
        try {
             s.adicione("","Inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
             fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) { 
             //System.out.println(e.getClass());
             assertEquals(e.getMessage(), IemoisExcepcion.VACIO);
        }
    }
    @Test
    public void deberiaFallarSinArea(){
        Iemois s= new Iemois();
        try {
             s.adicione("Machine Learning","","Coursera","Este curso proporciona una amplia introduccion","8");
             fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) { 
             //System.out.println(e.getClass());
             assertEquals(e.getMessage(), IemoisExcepcion.VACIO);
        }
    }
    @Test
    public void deberiaFallarSinOjetivo(){
        Iemois s= new Iemois();
        try {
             s.adicione("Machine Learning","Inteligencia Artificial","","Este curso proporciona una amplia introduccion","8");
             fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) { 
             //System.out.println(e.getClass());
             assertEquals(e.getMessage(), IemoisExcepcion.VACIO);
        }
    }
    @Test
    public void deberiaFallarSiAreaMinuscula(){
        Iemois s= new Iemois();
        try {
             s.adicione("Machine Learning","inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
             fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) { 
             //System.out.println(e.getClass());
             assertEquals(e.getMessage(),IemoisExcepcion.MAYSTRI);
        }
    }
    @Test
    public void deberiaFallarSiObjetivoMinuscula(){
        Iemois s= new Iemois();
        try {
             s.adicione("Machine Learning","Inteligencia Artificial","coursera","Este curso proporciona una amplia introduccion","8");
             fail("No lanzo excepcion");
        }
        catch(IemoisExcepcion e) { 
             //System.out.println(e.getClass());
             assertEquals(e.getMessage(),IemoisExcepcion.MAYSTRI);
        }
    }
    @Test
    public void pruebaDeUnidad() throws IemoisExcepcion{
        Iemois s= new Iemois();
        int size= s.numerocursos();
        s.adicione("Machine Learning","Inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
        s.adicione("Q","Q","Q","Q","1");
        s.adicione("A","Q","Q","Q","1");
        s.adicione("B","Q","Q","Q","1");
        s.adicione("C","Q","Q","Q","1");
        s.adicione("D","Q","Q","Q","1");
        s.adicione("E","Q","Q","Q","1");
        s.adicione("F","Q","Q","Q","1");

        assertTrue(size+8== s.numerocursos());
        
    }
    @Test 
    public void deberiaFallarBusque() throws IemoisExcepcion{
	Iemois s= new Iemois();
	ArrayList<Mooc> resultados=new ArrayList<Mooc>();
	s.adicione("Machine Learning","Inteligencia Artificial","Coursera","Este curso proporciona una amplia introduccion","8");
        resultados=s.busque("M");	
	assertEquals(resultados.size(),1);
    }
}