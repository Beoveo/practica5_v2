package es.ucm.fdi.tp.pruebas;


import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


public class pruebaMenu implements ItemListener{
	private static JComboBox<String> playerModeCb;
	
	private static JButton botonesMenu(String nombre) {
		JButton button = new JButton();
		ImageIcon imagen = new ImageIcon(nombre);
		Icon icon;
		button.setBounds(10, 10, 50, 50);
		icon = new ImageIcon(imagen.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
		button.setIcon(icon);
		
		return button;
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Panel de control");
		
		frame.setVisible(true);
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		 
		JButton randBtn = botonesMenu("dice.png"); //MOV ALEATORIO
		menuBar.add(randBtn);
		JButton smartBtn = botonesMenu("nerd.png"); //MOV INTELIGENTE
		menuBar.add(smartBtn);
		JButton restartBtn = botonesMenu("restart.png"); //REINICIAR PARTIDA
		menuBar.add(restartBtn);
		JButton exitBtn = botonesMenu("exit.png"); //SALIR
		menuBar.add(exitBtn);
		
		JMenu playerMode = new JMenu("Player Mode: ");
		menuBar.add(playerMode);
		
		menuBar.setLayout(new FlowLayout());
		playerModeCb = new JComboBox<String>();
		
		playerModeCb.setBounds(10, 10, 80, 20);
		menuBar.add(playerModeCb);
		
		playerModeCb.addItem("Manual");
		playerModeCb.addItem("Random");
		playerModeCb.addItem("Smart");
		//playerModeCb.addItemListener();
		
		randBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		smartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		//System.exit(0); //cierra el programa, se refiere a cerrar la ventana o a "finalizar la ejecucion?"
					
				
			
				
		
	}
	
	/**
	 * Metodo que genera una ventana en la que se pregunta al usuario si de verdad quiere salir del juego.
	 * Si selecciona la opcion SI, se cierra el JFrame.
	 * Si selecciona la opcion NO, sigue el juego.
	 */
	private static void quit() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Seguro que quieres parar el juego?", "Salir del juego",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) {
			System.exit(0); //Cierra la ventana.
		}
	}

	/**
	 * Metodo que implementa la clase ItemListener. Compara el playerMode para saber cual es el item seleccionado,
	 * en este caso Smart, Manual o Random. Y el seleccionado lo deja fijo en el ComboBox.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == playerModeCb){
			String seleccionado = (String) playerModeCb.getSelectedItem();
			playerModeCb.setName(seleccionado);
		}
	}	
}
