package es.ucm.fdi.tp.pruebas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GamePlayer.PlayerMode;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jboard.JBoard.Shape;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.view.GameController;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class TableroTtt extends JFrame {
	private JBoard boardComp;
	private Integer[][] board;

	private JTextField rows;
	private JTextField cols;
	
	private int numOfRows;
	private int numOfCols;
	
	private Color colorP1;
	private Color colorP2;
	
	GameController<TttState, TttAction> gameCtrl;
	TttState state = new TttState(3);

	public TableroTtt() {
		super("TABLERO ttt");
		this.colorP1 = Color.RED;
		this.colorP2 = Color.BLUE;
		
		initGUI();
		
	}

	private void initGUI() {
		createBoardData(3, 3);

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
						//Crear evento
						//Realizar movimiento
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
				return TableroTtt.this.getPosition(row, col);
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
				return TableroTtt.this.getColor(player);
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
		
		int[][] boardTtt = state.getBoard();
		
		for (int i = 0; i < numOfRows; i++)
			for (int j = 0; j < numOfCols; j++) {
				if(boardTtt[i][j] == -1) board[i][j] = null;
				else board[i][j] = boardTtt[i][j];
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
				new TableroTtt();
			}
		});
	}
}
