package aplicacion;


import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AutomataCelularTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AutomataCelularTest
{
    /**
     * Default constructor for test class AutomataCelularTest
     */
    public AutomataCelularTest()
    {
        AutomataCelular at = new AutomataCelular();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Test
    public void deberiaHaberUnaCelula()
    {
        AutomataCelular at = new AutomataCelular();
        Celula a_cel = new Celula(at,1,1);
        assertTrue("deberia haber celula",(at.getM()[1][1] instanceof Celula));
    }
    
    @Test
    public void deberiaHacerTicTac(){
        AutomataCelular at = new AutomataCelular();
        Celula the_cel = new Celula(at,1,1);
        at.ticTac();
        assertTrue("deberia estar vivo",at.getElemento(1,1).isVivo());
        at.ticTac();
        assertTrue("deberia estar vivo",at.getElemento(1,1).isVivo());
        at.ticTac();
        assertFalse("deberia estar muerto",at.getElemento(1,1).isVivo());
    }
    
    @Test
    public void noDeberianMorirBarreras(){
        AutomataCelular at = new AutomataCelular();
        Barrera the_bar = new Barrera(at,17,17);
        Barrera other_bar = new Barrera(at,1,17);
        at.ticTac();
        assertTrue("deberia estar vivo",at.getElemento(17,17).isVivo());
        assertTrue("deberia estar vivo",at.getElemento(1,17).isVivo());
        for(int i = 0;i < 100;i++){
            at.ticTac();
            assertTrue("deberia estar vivo",at.getElemento(17,17).isVivo());
            assertTrue("deberia estar vivo",at.getElemento(1,17).isVivo());
        }            
    }
    
    @Test
    public void celulasIzquierdosas(){
        AutomataCelular at = new AutomataCelular();
        Izquierdosa  first = new Izquierdosa(at,5,5);
        Izquierdosa  second = new Izquierdosa(at,5,6);
        Izquierdosa  third = new Izquierdosa(at,5,7);
        Izquierdosa  fourth = new Izquierdosa(at,5,8);
        
        at.ticTac();
        assertTrue("deberia ser de color roja",at.getElemento(5,5).getColor() == Color.red);
        assertTrue("deberia ser de color roja",at.getElemento(5,6).getColor() == Color.red);
        assertTrue("deberia ser de color roja",at.getElemento(5,7).getColor() == Color.red);
        assertTrue("deberia ser de color roja",at.getElemento(5,8).getColor() == Color.red);
        
        assertTrue("deberia estar viva",at.getElemento(5,5).isVivo());
        assertTrue("deberia estar viva",at.getElemento(5,6).isVivo());
        assertTrue("deberia estar viva",at.getElemento(5,7).isVivo());
        assertTrue("deberia estar viva",at.getElemento(5,8).isVivo());
        at.ticTac();    
        assertFalse("deberia estar muerta",at.getElemento(5,5).isVivo());
        assertFalse("deberia estar muerta",at.getElemento(5,6).isVivo());
        assertFalse("deberia estar muerta",at.getElemento(5,7).isVivo());
    }
    
    @Test
    public void manzanas(){
     AutomataCelular at = new AutomataCelular();
     Manzana  apple = new Manzana(at,0,0);
     
     assertFalse("No deberia ser roja",at.getElemento(0,0).getColor() == Color.red);
     assertFalse("No deberia ser verde",at.getElemento(0,0).getColor() == Color.green);
     
    }
    
    @Test
    public void celulasOxigenada(){
        AutomataCelular at = new AutomataCelular();
        Oxigenada  ox = new Oxigenada(at,10,10);
        
        Oxigenada  ox1 = new Oxigenada(at,1,1);
        Oxigenada  ox2 = new Oxigenada(at,1,0);
        Oxigenada  ox3 = new Oxigenada(at,0,0);
        Oxigenada  ox4 = new Oxigenada(at,0,1);
        Oxigenada  ox5 = new Oxigenada(at,0,2);
        Oxigenada  ox7 = new Oxigenada(at,1,2);
        Oxigenada  ox8 = new Oxigenada(at,2,2);
        Oxigenada  ox9 = new Oxigenada(at,2,1);
        Oxigenada  ox10 = new Oxigenada(at,2,0);
        
        at.ticTac();
        assertTrue("deberia ser de color verde",at.getElemento(10,10).getColor() == Color.green);
        assertTrue("deberia estar viva",at.getElemento(10,10).isVivo());
        assertTrue("deberia estar viva",at.getElemento(1,1).isVivo());
        at.ticTac();
        
        assertTrue("deberia ser de color yelow",at.getElemento(10,10).getColor() == Color.yellow);
        assertTrue("deberia estar viva",at.getElemento(10,10).isVivo());
        assertFalse("deberia estar muerta",at.getElemento(1,1).isVivo());
        at.ticTac();
        at.ticTac();        
        assertTrue("deberia ser de color azul-claro",at.getElemento(10,10).getColor() == Color.cyan);
        assertTrue("deberia estar viva",at.getElemento(10,10).isVivo());
        at.ticTac();
        at.ticTac();
        assertFalse("deberia estar muerta",at.getElemento(10,10).isVivo());
        
       
    }
    
    @Test
    public void gameOfLife1(){
        AutomataCelular at = new AutomataCelular();
        Conway yeisson = new Conway(at,2,2);
        Conway santiago = new Conway(at,2,3);
        
        at.ticTac();
        assertTrue("LA celula yeisson deberia estar viva",at.getElemento(2,2).isVivo());
        assertTrue("LA celula santiago deberia estar viva",at.getElemento(2,3).isVivo());
        at.ticTac();
        assertFalse("LA celula yeisson deberia estar muerta",at.getElemento(2,2).isVivo());
        assertFalse("LA celula santiago deberia estar muerta",at.getElemento(2,3).isVivo());
        at.ticTac();
        assertFalse("LA celula yeisson deberia estar muerta",at.getElemento(2,2).isVivo());
        assertFalse("LA celula santiago deberia estar muerta",at.getElemento(2,3).isVivo());    
    }    
}