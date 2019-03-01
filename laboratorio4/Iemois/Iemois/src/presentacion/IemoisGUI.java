package presentacion; 
  
import registro.*;
import aplicacion.*;
import excepcion.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
            
/**
* @version ECI 2016
*/
public class IemoisGUI extends JFrame{
    
    private static final int ANCHO_PREFERIDO = 450;
    private static final int ALTO_PREFERIDO= 450;
    private static final Dimension DIMENSION_PREFERIDA =
                         new Dimension(ANCHO_PREFERIDO,ALTO_PREFERIDO);
    
    private Iemois coleccion;
    
    /*Panel botonListar*/
    private JButton botonListar;
    private JButton botonReiniciarListar;
    private JTextArea textoInformacion;
    
    /*Panel botonAdicionar*/
    private JTextField textoNombre;   
    private JTextField textoArea;
    private JTextField textoDistribuidor;
    private JTextField textoSemanas;
    private JTextArea  textoObjetivo;
    
    private JButton botonAdicionar;
    private JButton botonReiniciarAdicionar;
    
    /*Panel buscar*/
    private JTextField busquedaTexto;
    private JTextArea resultadosTexto;
    
    
    
    
    private IemoisGUI(){
        coleccion=new Iemois();
        coleccion.adicione();
        prepareElementos();
        prepareAcciones();
    }
    
    
    private void prepareElementos(){
        setTitle("Sinap. Areas.");
        textoNombre = new JTextField(50);
        textoArea = new JTextField(50);
        textoDistribuidor = new JTextField(50);
        textoSemanas = new JTextField(4);
        textoObjetivo = new JTextArea(10, 50);
        textoObjetivo.setLineWrap(true);
        textoObjetivo.setWrapStyleWord(true);
        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar",   prepareListar());
        etiquetas.add("Adicionar",  prepareAdicionar());
        etiquetas.add("Buscar", prepareBuscar());
        getContentPane().add(etiquetas);
        setSize(DIMENSION_PREFERIDA);
        
    }
    
    
    /**
     * Prepara el panel para listar
     * @return
     */
    private JPanel prepareListar(){
    
        textoInformacion = new JTextArea(10, 50);
        textoInformacion.setEditable(false);
        textoInformacion.setLineWrap(true);
        textoInformacion.setWrapStyleWord(true);
        JScrollPane scrollArea =
                new JScrollPane(textoInformacion,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                
        JPanel  botones = new JPanel();
        botonListar = new JButton("Listar");
        botonReiniciarListar = new JButton("Limpiar");
        botones.add(botonListar);
        botones.add(botonReiniciarListar);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
    
        return panel;
     }
     
    /**
     * Prepara el area de adición
     * @return El area de adición
     */
    private JPanel prepareAdicionar(){
        
        Box textoNombreArea = Box.createHorizontalBox();
        textoNombreArea.add(new JLabel("Nombre", JLabel.LEFT));
        textoNombreArea.add(Box.createGlue());
        Box nombreArea = Box.createVerticalBox();
        nombreArea.add(textoNombreArea);
        nombreArea.add(textoNombre);
    
        Box textoAreaArea = Box.createHorizontalBox();
        textoAreaArea.add(new JLabel("Area", JLabel.LEFT));
        textoAreaArea.add(Box.createGlue());
        Box AreaArea = Box.createVerticalBox();
        AreaArea.add(textoAreaArea);
        AreaArea.add(textoArea);
        
        Box textoDistribuidorArea = Box.createHorizontalBox();
        textoDistribuidorArea.add(new JLabel("Distribuidor", JLabel.LEFT));
        textoDistribuidorArea.add(Box.createGlue());
        Box DistribuidorArea = Box.createVerticalBox();
        DistribuidorArea.add(textoDistribuidorArea);
        DistribuidorArea.add(textoDistribuidor);
    
        Box textoSemanasArea = Box.createHorizontalBox();
        textoSemanasArea.add(new JLabel("Semanas", JLabel.LEFT));
        textoSemanasArea.add(Box.createGlue());
        Box SemanasArea = Box.createVerticalBox();
        SemanasArea.add(textoSemanasArea);
        SemanasArea.add(textoSemanas);
        
        Box textoObjetivoArea = Box.createHorizontalBox();
        textoObjetivoArea.add(new JLabel("Objetivo", JLabel.LEFT));
        textoObjetivoArea.add(Box.createGlue());
        Box ObjetivoArea = Box.createVerticalBox();
        ObjetivoArea.add(textoObjetivoArea);
        ObjetivoArea.add(textoObjetivo);
    
        Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(nombreArea);
        singleLineFields.add(AreaArea);
        singleLineFields.add(DistribuidorArea);
        singleLineFields.add(SemanasArea);        
    
        JPanel textoInformacionPanel = new JPanel();
        textoInformacionPanel.setLayout(new BorderLayout());
        textoInformacionPanel.add(singleLineFields, BorderLayout.NORTH);
        textoInformacionPanel.add(ObjetivoArea, BorderLayout.CENTER);
    
        JPanel botones = new JPanel();
        botonAdicionar = new JButton("Adicionar");
        botonReiniciarAdicionar = new JButton("Limpiar");
    
        botones.add(botonAdicionar);
        botones.add(botonReiniciarAdicionar);
    
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textoInformacionPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }
    
    
    
    
    
    /**
     * Prepara el area de liatar
     * @return El panel para buscar coleccions
     */
    private JPanel prepareBuscar(){
    
        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        busquedaTexto = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(busquedaTexto);
        
        resultadosTexto = new JTextArea(10,50);
        resultadosTexto.setEditable(false);
        resultadosTexto.setLineWrap(true);
        resultadosTexto.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(resultadosTexto,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
        JPanel botonListarArea = new JPanel();
        botonListarArea.setLayout(new BorderLayout());
        botonListarArea.add(busquedaArea, BorderLayout.NORTH);
        botonListarArea.add(scrollArea, BorderLayout.CENTER);
    
        return botonListarArea;
    }
    
    
    public void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                setVisible(false);
                System.exit(0);
            }
        });
        
        /*Listar*/
        botonListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionListar();
            }
        });
    
        botonReiniciarListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                textoInformacion.setText("");
            }
        });
        
        /*Adicionar*/
        botonAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                accionAdicionar();                    
            }
        });
        
        botonReiniciarAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                textoNombre.setText("");
                textoArea.setText("");
                textoDistribuidor.setText("");
                textoSemanas.setText("");
                textoObjetivo.setText("");
            }
        });
        
        /*Buscarr*/
        busquedaTexto.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){
                accionBuscar();
    
            }
           
            public void insertUpdate(DocumentEvent ev){
                accionBuscar();
            }
            
            public void removeUpdate(DocumentEvent ev){
                accionBuscar();
            }
            
    
        });
    }    
    
    
    private void accionListar(){
        textoInformacion.setText(coleccion.toString());
    }
    
    
    private void  accionAdicionar(){
        try{
            coleccion.adicione(textoNombre.getText(),textoArea.getText(),textoObjetivo.getText(), textoDistribuidor.getText(), 
            textoSemanas.getText());
        }
        catch(IemoisExcepcion e){
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
            Registro.registre(e);
        }
    }

    private void accionBuscar(){
        String patronBusqueda=busquedaTexto.getText();
        StringBuffer buffer = new StringBuffer();
        if(patronBusqueda.length() > 0) {
            try{
            ArrayList <Mooc> results = coleccion.busque(patronBusqueda);
            for(int i = 0; i < results.size(); i++) {
                buffer.append(results.get(i).toString());
                buffer.append('\n');
                buffer.append('\n');
             }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"????","Error",JOptionPane.ERROR_MESSAGE);
                Registro.registre(e);
            }
        }
        resultadosTexto.setText(buffer.toString());
    } 
    
   public static void main(String args[]){
       IemoisGUI gui=new IemoisGUI();
       gui.setVisible(true);
   }    
}
