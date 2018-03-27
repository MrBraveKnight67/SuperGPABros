package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entities.Player;
import main.Game;

public class ResultState extends GameState {

	private BufferedImage bg;
	private Player player;
	private Player player2;

	private Color titleColor;
	private Font titleFont;
	private Font font;

	public ResultState(GameStateManager gsm, Player player, Player player2) {

		this.gsm = gsm;
		this.player = player;
		this.player2 = player2;

		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/foothillBackground.jpg"));

			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);

			font = new Font("Arial", Font.PLAIN, 12);
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

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Super GPA Bros", 55, 40);
		
		//draw others
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Your GPA's were " + String.format("%.2f", player.gpa[0]) + " and "
				+ String.format("%.2f", player2.gpa[0]), 90, 70);
		g.drawString("Thus, your ranks are...", 110, 90);
		g.setFont(titleFont);
		g.setColor(Color.ORANGE);
		g.drawString(getRank(player.gpa[0]), 30, 115);
		g.drawString(getRank(player2.gpa[0]), 30, 145);
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Press Q to quit.", 120, 160);
		g.drawString("(you could do this at any point in the game lol)", 35, 180);
	}
	
	private String getRank(double gpa) {
		int i = (int) (gpa*3);
		if (i == 12) {
			i--;
		}
		return Game.ranks[i];
	}

	public void goToNext() {
		gsm.setState(GameStateManager.DISPLAYSTATE, "/Displays/controlsDisplay1.5.jpg", player, player2,
				GameStateManager.MENUSTATE);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_Q) {
			System.exit(0);
		} else if (k == KeyEvent.VK_SPACE) {
			goToNext();
		}
	}

	public void keyReleased(int k) {
	}

}
