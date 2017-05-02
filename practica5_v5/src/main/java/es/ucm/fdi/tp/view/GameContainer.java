package es.ucm.fdi.tp.view;


import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;

public class GameContainer<S extends GameState<S,A>, A extends GameAction<S,A>> extends GUIView<S,A> implements GameObserver<S,A> {
	private GUIView<S, A> gameView;
	private MessageViewer<S, A> messageViewer;
	private PlayersInfoViewer<S, A> playersInfoViewer;
	private ControlPanel<S, A> controlPanel;
	private GameController<S, A> gameCtrl;
	//private WolfAndSheepView wasView;
	//private TicTacToeView tttView;
	
	
	public GameContainer(GUIView<S, A> gameView, GameController<S, A> gameCtrl,
			 GameObservable<S, A> game){
		this.gameView = gameView;
		this.gameCtrl = gameCtrl;
		initGUI(this.gameView);
		game.addObserver(this);
	}
	
	private void initGUI(GUIView<S, A> gameView) {
		playersInfoViewer = new PlayersInfoComp<S, A>(gameView);
		messageViewer = new MessageViewerComp<S, A>(gameView); 
		controlPanel = new ControlPanel<S, A>(gameView);
	}
	
	@Override
	public void notifyEvent(GameEvent e) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { handleEvent(e); }
		});
	}

	private void handleEvent(GameEvent<S,A> e) {		//Menejar Eventos
		switch ( e.getType() ) {
		case Change:
				update(e.getState()); 
				messageViewer.addContent("El estado del juego ha cambiado!" + System.getProperty("line.separator") + 
					"Es el turno del jugador " + e.getAction().getPlayerNumber() + " ." + System.getProperty("line.separator") +
						"Jugador "+ e.getAction().getPlayerNumber() + " : " + e.getAction().toString());
			break;
		case Error:
			messageViewer.addContent("Se ha producido un error durante el juego!");
			messageViewer.addContent(e.getError().getMessage());
				disable();
			break;
		case Info:
			//infoJugadores??
				update(e.getState());
			break;
		case Start:
				messageViewer.addContent("Comienza el juego !");
				initGUI(this.gameView);
			break;
		case Stop:
			boolean partidaAcabada = e.getState().isFinished();
			if(partidaAcabada) notifyWinner(e.getState().getWinner());
			messageViewer.addContent("Fin del juego !");
			System.out.println("Fin del juego !");
				disable();
			break;
		}
		SwingUtilities.invokeLater(new Runnable() { 
			 public void run() { gameCtrl.handleEvent(e); } 
		});
	} 
	
	private void notifyWinner(int winner) {
		String endText = " ";
		
		if (winner == -1) 
			endText += "Empate!";
	    else 
			endText += "Jugador " + winner + " :" + " ha ganado la partida!";
		messageViewer.addContent(endText);
		System.out.println(endText);		
	}

	@Override
	public void enable() {
		messageViewer.enable();
		playersInfoViewer.enable();
		controlPanel.enable();
		gameView.disable();
	}
	@Override
	public void disable() {
		messageViewer.disable();
		playersInfoViewer.disable();
		controlPanel.disable();
		gameView.disable();
	}
	@Override
	public void update(S state) {
		messageViewer.update(state);
		playersInfoViewer.update(state);
		gameView.update(state);
	}
	@Override
	public void setMessageViewer(MessageViewer<S, A> infoViewer) {
		this.messageViewer = infoViewer;
	}
	
	@Override
	public void setPlayersInfoViewer(PlayersInfoViewer<S, A> playersInfoViewer) {
		this.playersInfoViewer = playersInfoViewer;
	}
	@Override
	public void setGameController(GameController<S, A> gameCntrl) {
		gameView.setGameController(gameCntrl);
		messageViewer.setGameController(gameCntrl);
	}

}
