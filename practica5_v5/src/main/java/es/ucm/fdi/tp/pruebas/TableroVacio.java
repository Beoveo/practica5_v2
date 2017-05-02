package es.ucm.fdi.tp.pruebas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GamePlayer.PlayerMode;
import es.ucm.fdi.tp.extra.jboard.BoardExample;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jboard.JBoard.Shape;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.view.GameController;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class TableroVacio extends JFrame {

	private JBoard boardComp;
	private Integer[][] board;

	private JTextField rows;
	private JTextField cols;
	
	private int numOfRows;
	private int numOfCols;
	
	private Color colorP1;
	private Color colorP2;
	
	GameController<WolfAndSheepState, WolfAndSheepAction> gameCtrl;
	WolfAndSheepState state = new WolfAndSheepState(8);

	public TableroVacio() {
		super("TABLERO WAAAAAAAS");
		this.colorP1 = Color.RED;
		this.colorP2 = Color.BLUE;
		
		initGUI();
		
	}

	private void initGUI() {
		createBoardData(8, 8);

		JPanel mainPanel = new JPanel(new BorderLayout());

		boardComp = new JBoard() {


			@Override
			protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
				if((gameCtrl.getPlayerMode().equals(PlayerMode.Manual)) && this.isEnabled()){
					if(clickCount == 1){
						System.out.println("Mouse: " + clickCount + "clicks at position (" + row + "," + col + ") with Button "
						+ mouseButton + "where do you want to go?");
				}
					if(clickCount == 2){
						if(gameCtrl.getPlayerId() == state.getTurn()){
							WolfAndSheepAction action = new WolfAndSheepAction(state.getTurn(), row, col, state.lastRow(), state.lastCol());
						if(state.isValid(action))
							gameCtrl.makeManualMove(action); //Realizar movimiento
						}
					}
				}
			}

			@Override
			protected void keyTyped(int keyCode) {
				System.out.println("Key " + keyCode + " pressed ..");
			}

			@Override
			protected Shape getShape(int player) {
				return Shape.CIRCLE;
			}

			@Override
			protected Integer getPosition(int row, int col) {
				return TableroVacio.this.getPosition(row, col);
			}

			@Override
			protected int getNumRows() {
				return numOfRows;
			}

			@Override
			protected int getNumCols() {
				return numOfCols;
			}

			@Override
			protected Color getColor(int player) {
				return TableroVacio.this.getColor(player);
			}

			@Override
			protected Color getBackground(int row, int col) {
				//return Color.LIGHT_GRAY;

				// use this for 2 chess like board
				 return (row+col) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK;
			}

			@Override
			protected int getSepPixels() {
				return 1; // put to 0 if you don't want a separator between
							// cells
			}
		};

		mainPanel.add(boardComp, BorderLayout.CENTER);
		JPanel sizePabel = new JPanel();
		mainPanel.add(sizePabel, BorderLayout.PAGE_START);

		
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}


	private void createBoardData(int numOfRows, int numOfCols) {
		this.numOfRows = numOfRows;
		this.numOfCols = numOfCols;
		board = new Integer[numOfRows][numOfCols];
		
		WolfAndSheepState state = new WolfAndSheepState(8);
		int[][] boardWas = state.getBoard();
		
		
	
		for (int i = 0; i < numOfRows; i++)
			for (int j = 0; j < numOfCols; j++) {
				if(boardWas[i][j] == -1) board[i][j] = null;
				else board[i][j] = boardWas[i][j];
			}
			
			
	}

	protected Integer getPosition(int row, int col) {
		return board[row][col];
	}

	protected Color getColor(int player) {
		return player == 0 ?  colorP1 : colorP2;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TableroVacio();
			}
		});
	}
}
