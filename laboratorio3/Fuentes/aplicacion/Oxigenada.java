package aplicacion;
import java.util.*;
import java.io.*;
import java.awt.Color;        
/**
* Write a description of class Oxigenada here.
*
* @author (your name)
* @version (a version number or a date)
*/
public class Oxigenada extends  Celula {    
    private int numVecinos;    
    /**
     * Constructor for objects of class Oxigenada
     */
    public Oxigenada(AutomataCelular ac,int fila, int columna)
    { 
        super(ac,fila,columna);
        this.color = Color.green;
        numVecinos = 0;
    }        
    /**Decide cual va a ser su  siguiente estado 
    */
    public void decida(){
       if(edad>=1 && edad <=2){
           this.color = Color.yellow;
       }
       else if(edad>=3 && edad <=4){
           this.color = Color.cyan;
       }
       if (edad>=5 ){
           this.color = Color.orange;
           estadoSiguiente=MUERTA;
       }
       int[] x = {0, -1, -1, -1, 0, 1, 1, 1};
       int[] y = {-1,-1,  0,  1, 1, 1, 0, -1};
       int contV = 0;
       for(int i =0;i<8;i++){
            if( fila+x[i] >=0 && fila+x[i] <19  && columna+y[i]>=0 && columna+y[i]<19){
                if(automata.getElemento( fila+x[i] ,columna+y[i] ) != null) contV++;
            }
       }
       if(contV==8){
            this.edad+=5;
            estadoSiguiente = MUERTA;
       }
    }
}
