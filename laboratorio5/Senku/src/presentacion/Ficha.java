package presentacion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

class Ficha extends JPanel {
	private Color color;
	private boolean visible;
	private int value,x,y;
	private SenkuGUI gui;
	public Ficha(Color color,boolean visible,int value,int x, int y, SenkuGUI interfaz) {
		super();
		this.visible=visible;
		this.color=color;
		this.value=value;
		this.x = x;
		this.y = y;
		this.gui = interfaz;	
	prepareAcciones();
	} 
	public void prepareAcciones(){
		this.addMouseListener(new MouseAdapter(){
			
			public  void mouseClicked(MouseEvent e){
				oprimir();
			}
			/*
			public void mousePressed(MouseEvent e){
				preSel();
			}
			public void mouseReleased(MouseEvent e){
				endSel();
			}
			*/
		});
	}
	
	
	private void oprimir(){
		if(value==2 || value==1){
			gui.mover(x,y);	
			this.color = Color.WHITE;
		}
		
	} 
	private void preSel(){
		this.color = Color.WHITE;
		gui.preMove(x,y);
	}

	private void endSel(){
		gui.toMove(x,y);
	}
 	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int h=this.getHeight();
		g.setColor(color);
		if(visible && (value==2)) {
			g.fillOval(0, 0, h, h);
			this.setBackground(Color.DARK_GRAY);
		}
		else if (value==1){
			this.setBackground(Color.GRAY);
		}
		else {
			this.setBackground(Color.WHITE);
		}
	}

}
