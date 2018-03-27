package gameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import entities.Player;

public class DisplayState extends GameState {

	private BufferedImage bg;
	private Player player;
	private Player player2;
	private int nextState;

	public DisplayState(GameStateManager gsm, String display, Player player, Player player2, int nextState) {

		this.gsm = gsm;
		this.player = player;
		this.player2 = player2;
		this.nextState = nextState;

		try {
			bg = ImageIO.read(getClass().getResourceAsStream(display));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
	}

	public void update() {
	}

	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, 0, null);
	}

	public void goToNext() {
		if (nextState == GameStateManager.DISPLAYSTATE) {
			gsm.setState(GameStateManager.DISPLAYSTATE, "/Displays/congratsDisplay.jpg", player, player2,
					GameStateManager.RESULTSTATE);
		} else {
			gsm.setState(nextState, player, player2);
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_Q) {
			System.exit(0);
		}
		if (k == KeyEvent.VK_SPACE) {
			goToNext();
		}
	}

	public void keyReleased(int k) {
	}

}
