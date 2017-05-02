package es.ucm.fdi.tp.pruebas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PruebaFrame extends JPanel{
	public PruebaFrame(){
		JFrame window = new JFrame(" Mi ventana");
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new JButton("YES"));
		//add(new JButton("YES"), BorderLayout.CENTER); //asi no lo muestra
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new PruebaFrame();
				
			}
		});	
	}
	
}
