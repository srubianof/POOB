package aplicacion;


import java.awt.*;
import java.io.Serializable;
public class Manzana implements Elemento,Serializable{
    private AutomataCelular automata;
    private int fila,columna;
    protected Color color;
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public  Manzana(AutomataCelular ac,int fila, int columna)
    {
        automata = ac;
        this.fila=fila;
        this.columna=columna;
        automata.setElemento(fila,columna,(Elemento)this);
        color = Color.orange;
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
       int[] x = {0, -1, -1, -1, 0, 1, 1, 1};
       int[] y = {-1,-1,  0,  1, 1, 1, 0, -1};
       int contV = 0;
       for(int i =0;i<8;i++){
            if( fila+x[i] >=0 && fila+x[i] <19  && columna+y[i]>=0 && columna+y[i]<19){
                if(automata.getElemento( fila+x[i] ,columna+y[i] ) != null) contV++;
            }
       }
       if (contV>2)this.color = Color.red;
       else if (contV>3) this.color=Color.green;
       else if (contV>4) this.color=Color.black;
       
    }
    
}    

