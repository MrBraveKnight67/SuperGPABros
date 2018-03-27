package gameState;

import entities.Player;

public class GameStateManager {
	
	private GameState[] gameStates;
	public int currentState;
	public int nextState;
	public String display;
	
	private Player player;
	private Player player2;
	
	public static final int NUMGAMESTATES = 6;
	public static final int DISPLAYSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int LEVEL1STATE = 2;
	public static final int LEVEL2STATE = 3;
	public static final int LEVEL3STATE = 4;
	public static final int RESULTSTATE = 5;
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = DISPLAYSTATE;
		nextState = MENUSTATE;
		display = "/Displays/controlsDisplay1.5.jpg";
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
		if(state == DISPLAYSTATE)
			gameStates[state] = new DisplayState(this, display, player, player2, nextState);
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
		if(state == LEVEL2STATE)
			gameStates[state] = new Level2State(this, player, player2);
		if(state == LEVEL3STATE)
			gameStates[state] = new Level3State(this, player, player2);
		if(state == RESULTSTATE)
			gameStates[state] = new ResultState(this, player, player2);
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state, Player player, Player player2) {
		unloadState(currentState);
		currentState = state;
		this.player = player;
		this.player2 = player2;
		loadState(currentState);
		gameStates[currentState].init();
	}

	public void setState(int state, String display, Player player, Player player2, int nextState) {
		unloadState(currentState);
		currentState = state;
		this.player = player;
		this.player2 = player2;
		this.nextState = nextState;
		this.display = display;
		loadState(currentState);
		gameStates[currentState].init();
	}

	public void update() {
		try {
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		try {
			gameStates[currentState].keyPressed(k);
		} catch(Exception e) {}
	}
	
	public void keyReleased(int k) {
		try {
			gameStates[currentState].keyReleased(k);
		} catch(Exception e) {}
	}
	
}









