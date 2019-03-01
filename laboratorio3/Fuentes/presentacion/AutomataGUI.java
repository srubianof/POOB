package presentacion;
import aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutomataGUI extends JFrame{    

    private JButton botonReloj;
    private JLabel lFila;
    private JLabel lColumna;
    private JTextField tFila;
    private JTextField tColumna;
    private JPanel panelControl;
    private JPanel panelNueva;
    private JPanel panelBNueva;
    private JButton botonViva;
    private JButton botonLatente;

    private FotoAutomata foto;
    private AutomataCelular automata=null;

    public AutomataGUI(AutomataCelular ac) {
        super("Automata celular");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        automata=ac;
        foto=new FotoAutomata(automata);
        setSize(new Dimension(700,770)); 
        prepareElementos();
        prepareAcciones();

    }

    private void prepareElementos() {
        setResizable(false);

        botonReloj=new JButton("Tic-tac");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(foto,BorderLayout.NORTH);
        getContentPane().add(botonReloj,BorderLayout.SOUTH);

        foto.repaint();
    }

    private void prepareAcciones(){

        botonReloj.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    botonRelojAccion();
                }
            });

    }

    private void botonRelojAccion() {
        automata.ticTac();
        foto.repaint();
    }

    public static void main(String[] args) {
        AutomataCelular ac=new AutomataCelular();
        AutomataGUI ca=new AutomataGUI(ac);
        ca.setVisible(true);

    }  
}

class FotoAutomata extends JPanel{
    public static int TAMANO=40;
    private AutomataCelular automata;

    public FotoAutomata(AutomataCelular ac) {
        setBackground(Color.white);
        automata=ac;
        setPreferredSize(new Dimension(800,800));       

    }

    public void setAutomata(AutomataCelular automata){
        this.automata=automata;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int f=0;f<=automata.getLongitud();f++){
            g.drawLine(f*TAMANO,0,f*TAMANO,automata.getLongitud()*TAMANO);
        }
        for (int c=0;c<=automata.getLongitud();c++){
            g.drawLine(0,c*TAMANO,automata.getLongitud()*TAMANO,c*TAMANO);
        }       
        for (int f=0;f<automata.getLongitud();f++){
            for(int c=0;c<automata.getLongitud();c++){
                
                if (automata.getElemento(f,c)!=null){
                    g.setColor(automata.getElemento(f,c).getColor());
                    if (automata.getElemento(f,c) instanceof Barrera){                       
                       g.setColor(automata.getElemento(f,c).getColor());  
                       
                       g.fillRoundRect(TAMANO*c+3,TAMANO*f+3,16,16,5,5); 
                       g.fillRoundRect(TAMANO*c+3+18,TAMANO*f+3,16,16,5,5); 
                       g.fillRoundRect(TAMANO*c+3,TAMANO*f+3+18,16,16,5,5); 
                       g.fillRoundRect(TAMANO*c+3+18,TAMANO*f+3+18,16,16,5,5); 
                        
                       if (automata.getElemento(f,c).isVivo()){
                           g.fillRoundRect(TAMANO*c+6,TAMANO*f+3,35,35,5,5);
                        }
                    }
                    else if(automata.getElemento(f,c) instanceof Manzana){
                       g.setColor(automata.getElemento(f,c).getColor());  
                       g.setFont(new Font("Baskerville Old Face",Font.PLAIN,40));
                       g.drawString("",TAMANO*c+1,TAMANO*f+30);
                    }
                    
                    else {
                        if (automata.getElemento(f,c).isVivo()){
                            g.setColor(automata.getElemento(f,c).getColor());
                            g.fillOval(TAMANO*c+10,TAMANO*f+10,20,20);
                        } else {
                            g.setColor(automata.getElemento(f,c).getColor());
                            g.drawOval(TAMANO*c+10,TAMANO*f+10,20,20);
                        }
                    }
                }
            }
        }
    }
    
}