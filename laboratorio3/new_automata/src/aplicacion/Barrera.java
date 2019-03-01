package aplicacion;

import java.awt.*;
public class Barrera implements Elemento{
    private AutomataCelular automata;
    private int fila,columna;
    protected Color color;
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public  Barrera(AutomataCelular ac,int fila, int columna)
    {
        automata = ac;
        this.fila=fila;
        this.columna=columna;
        automata.setElemento(fila,columna,(Elemento)this);
        color = Color.black;
    }
    /**Retorna el color de  la célula
    @return 
    */
    public final Color getColor(){        
        return color;        
    }
   /**Actualiza su estado actual considerando lo definido como siguiente estado
    */
    public void cambie(){
        assert true;
    }
   /**Decide cual va a ser su  siguiente estado 
    */
    public void decida(){
       assert true;
    }
}    

