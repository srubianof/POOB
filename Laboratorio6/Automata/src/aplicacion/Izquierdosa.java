package aplicacion;

import java.awt.Color;
import java.io.Serializable;

/**
 * Write a description of class Izquierdosa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Izquierdosa extends Celula implements Serializable
{
    public Izquierdosa(AutomataCelular ac,int fila, int columna){
        super(ac,fila,columna);
        this.color = Color.red;
    }
    
    public void decida(){
        super.decida();
        if(columna+1 < 19 ){
            if(automata.getElemento(fila,columna+1) != null && automata.getElemento(fila,columna+1).isVivo() ){
                estadoSiguiente = MUERTA;
            }
        }
    }    
}
