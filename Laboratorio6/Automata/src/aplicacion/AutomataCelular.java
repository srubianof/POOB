package aplicacion;
import java.util.*;
import java.*;
import java.io.*;

public class AutomataCelular implements Serializable{
	private static final long serialVersionUID = 1L;
    static private int LONGITUD=20;
    private Elemento[][] automata;
    private int nTictac;
     
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        algunosElementos();
        nTictac=0;
    }
    
    public void opcionIniciar() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        algunosElementos();
        nTictac=0;
    }
    
    public Elemento[][] getM(){
        return automata;
    }
    private int[] listar() {
    	int[] lista = {1,2,3,4,5};
    	return lista;
    }
    
    public int  getLongitud(){
        return LONGITUD;
    } 
    
    public Elemento getElemento(int f,int c){
         return automata[f][c];
    }
    
    public void setElemento(int f, int c, Elemento nueva){
       automata[f][c]=nueva;
    }
   
    public void algunosElementos(){
        
        // //barreras
        // Barrera noreste = new Barrera(this,19,19);
        // Barrera sureste = new Barrera(this,0,19);
        // //celulas
        // Celula cerocerosiete = new Celula(this,1,1);
        // Celula indiana = new Celula(this,2,2);
        
        // // //celulas izquierdosas
        // Izquierdosa otra = new Izquierdosa(this,5,5);
        // Izquierdosa celula = new Izquierdosa(this,5,6);
        // Izquierdosa otra1 = new Izquierdosa(this,5,7);
        // Izquierdosa celula1 = new Izquierdosa(this,5,8);
       
        // Oxigenada ox1 = new Oxigenada(this,10,10);
        // Oxigenada ox2 = new Oxigenada(this,10,11);
       
        
        // Manzana m = new Manzana(this,0,0);
       
        Conway uno= new Conway(this,8,4);
        Conway dos= new Conway(this,9,5);
        Conway tres= new Conway(this,10,3);
        Conway cuatro= new Conway(this,10,4);
        Conway cinco= new Conway(this,10,5);
        
    }
    
    public int nVecinos(int fila,int columna){
        int[] x = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] y = {-1,-1,  0,  1, 1, 1, 0, -1};
        int contV = 0;
        for(int i =0;i<8;i++){
            if( fila+x[i] >=0 && fila+x[i] <19  && columna+y[i]>=0 && columna+y[i]<19
               && automata[fila+x[i]][columna+y[i]]  != null &&
               automata[fila+x[i]][columna+y[i]].isVivo() 
               && automata[fila+x[i]][columna+y[i]] instanceof Conway ){                     
                contV++;                                      
            }
        }
        return contV;
    }
    
    public void ticTac(){       
        if(nTictac==0){
            dosFor("cambie");
        }
        else{
            dosFor("decida");        
            dosFor("nace");            
            dosFor("cambie");
        }
        nTictac++;
    }
    
    public FileOutputStream salve() {
    	try {
    		
    	    Elemento[][] elementos = getM();
    		//int[] elementos = listar();
    	    //Write Elements array to file.
    	    FileOutputStream fos = new FileOutputStream("elementos.dat");
    	    ObjectOutputStream oos = new ObjectOutputStream(fos);
    	    oos.writeObject("Hola mundo");
    	    oos.writeObject(elementos);
    	    oos.close();
    	    System.out.println("ok file and object outputStream");
    	    return fos;
//    	    //Read Element array from file.
//    	    FileInputStream fis = new FileInputStream("elementos.ser");
//    	    ObjectInputStream ois = new ObjectInputStream(fis);
//    	    Elemento[][] elementosFromSavedFile = (Elemento[][]) ois.readObject();
//    	    ois.close();
    	}
    	catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	}
    	catch (IOException e) {
    	    e.printStackTrace();
    	}
		return null;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void dosFor(String action)
    {
        for(int f=0;f<19;f++){
            for (int c=0;c<19;c++){               
                if(action.equals("cambie") && automata[f][c] != null){                    
                    automata[f][c].cambie();
                }
                else if(action.equals("decida") && automata[f][c] != null){
                    automata[f][c].decida();                    
                }
                else if(action.equals("nace")){                    
                    if(automata[f][c] == null  && nVecinos(f,c)==3){
                        automata[f][c] = new Conway(this,f,c);
                    }
                }
            }
        }   
    }
}
