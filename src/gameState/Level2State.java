package gameState;

import entities.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Level2State extends GameState {

	private BufferedImage bg;
	private Player player;
	private Player player2;
	private double[] oldGPA;
	private HUD hud;
	private HUD hud2;
	private ArrayList<Grade> grades;
	private ArrayList<Explosion> explosions;
	private Door door;
	private int gSpeed = 4;
	private int pSpeed = 3;
	private int pSpeed2 = 4;

	public Level2State(GameStateManager gsm, Player player, Player player2) {
		this.gsm = gsm;
		this.player = player;
		this.player2 = player2;
		init();
	}

	public void init() {

		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/frenchBackground.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		player.setPosition(0, 160);
		player.speed = pSpeed;
		player2.setPosition(0, 160);
		player2.speed = pSpeed2;
		oldGPA = new double[] {player.gpa[0], player2.gpa[1]};
		hud = new HUD(player, 15, 15);
		hud2 = new HUD(player2, 15, 213);
		populateGrades();
		explosions = new ArrayList<Explosion>();
	}

	private void populateGrades() {
		grades = new ArrayList<Grade>(7);
		Grade g;
		int[] values = new int[] { 4, 4, 4, 3, 3, 3, 3 };
		Point[] points = new Point[] { new Point(200, 100), new Point(200, 100), new Point(200, 100),
				new Point(200, 100), new Point(200, 100), new Point(200, 100), new Point(200, 100) };
		for (int i = 0; i < points.length; i++) {
			g = new Grade(points[i].x, points[i].y, values[i], gSpeed);
			grades.add(g);
		}

	}

	public void update() {

		// check for door
		if (grades.size() <= 3 && (player.gpa[0] != oldGPA[0] || player2.gpa[0] != oldGPA[1])) {
			if (door == null) {
				door = new Door(310, 230);
			} else if (door.updateB(player)) {
				door = null;
				goToNext();
			}
		}

		// update all grades & explosions
		for (int i = 0; i < grades.size(); i++) {
			Grade g = grades.get(i);
			g.update(player);
			g.update(player2);
			if (!g.needed) {
				grades.remove(i);
				i--;
				explosions.add(new Explosion(g.getX(), g.getY()));
			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			Explosion e = explosions.get(i);
			e.update();
			if (!e.needed) {
				explosions.remove(i);
				i--;
			}
		}
		
		player.update();
		player2.update();

	}

	public void draw(Graphics2D g) {

		// draw bg & door
		g.drawImage(bg, 0, 0, null);
		if (door != null) {
			door.draw(g);
		}

		player.draw(g);
		player2.draw(g);
		hud.draw(g);
		hud2.draw(g);

		// draw enemies & explosions
		for (int i = 0; i < grades.size(); i++) {
			grades.get(i).draw(g);
		}
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g);
		}

	}

	public void goToNext() {
		player.stopVector("hor");
		player.stopVector("ver");
		player2.stopVector("hor");
		player2.stopVector("ver");
		gsm.setState(GameStateManager.DISPLAYSTATE, "/Displays/englishDisplay.jpg", player, player2,
				GameStateManager.LEVEL3STATE);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setVector("left");
			player.setVector("up");
		} else if (k == KeyEvent.VK_RIGHT) {
			player.setVector("right");
			player.setVector("down");
		} else if (k == KeyEvent.VK_UP) {
			player.setVector("up");
			player.setVector("right");
		} else if (k == KeyEvent.VK_DOWN) {
			player.setVector("down");
			player.setVector("left");
		} else if (k == KeyEvent.VK_Q) {
			System.exit(0);
		} else if (k == KeyEvent.VK_A) {
			player2.setVector("left");
			player2.setVector("up");
		} else if (k == KeyEvent.VK_D) {
			player2.setVector("right");
			player2.setVector("down");
		} else if (k == KeyEvent.VK_W) {
			player2.setVector("up");
			player2.setVector("right");
		} else if (k == KeyEvent.VK_S) {
			player2.setVector("down");
			player2.setVector("left");
		}
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_RIGHT) {
			player.stopVector("hor");
			player.stopVector("ver");
		} else if (k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN) {
			player.stopVector("hor");
			player.stopVector("ver");
		} else if (k == KeyEvent.VK_A || k == KeyEvent.VK_D) {
			player2.stopVector("hor");
			player2.stopVector("ver");
		} else if (k == KeyEvent.VK_W || k == KeyEvent.VK_S) {
			player2.stopVector("hor");
			player2.stopVector("ver");
		}
	}

}
