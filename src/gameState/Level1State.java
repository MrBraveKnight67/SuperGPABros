package gameState;

import entities.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Level1State extends GameState {

	private BufferedImage bg;
	private Player player;
	private Player player2;
	private HUD hud;
	private HUD hud2;
	private ArrayList<Grade> grades;
	private ArrayList<Explosion> explosions;
	private Door door;
	private int gSpeed = 3;
	private int pSpeed = 2;
	private int pSpeed2 = 3;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/compsciBackground.JPG"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		player = new Player(15, 15, pSpeed, 1);
		player2 = new Player(15, 15, pSpeed2, 2);
		hud = new HUD(player, 15, 15);
		hud2 = new HUD(player2, 15, 213);
		populateGrades();
		explosions = new ArrayList<Explosion>();
	}

	private void populateGrades() {
		grades = new ArrayList<Grade>(7);
		Grade g;
		int[] values = new int[] { 4, 4, 4, 4, 4, 4, 3 };
		Point[] points = new Point[] { new Point(200, 100), new Point(200, 100), new Point(200, 100),
				new Point(200, 100), new Point(200, 100), new Point(200, 100), new Point(200, 100) };
		for (int i = 0; i < points.length; i++) {
			g = new Grade(points[i].x, points[i].y, values[i], gSpeed);
			grades.add(g);
		}

	}

	public void update() {

		// check for door
		if (grades.size() <= 3 && (player.gpa[0] != 0 || player2.gpa[0] != 0)) {
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
		gsm.setState(GameStateManager.DISPLAYSTATE, "/Displays/frenchDisplay.jpg", player, player2,
				GameStateManager.LEVEL2STATE);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setVector("left");
		} else if (k == KeyEvent.VK_RIGHT) {
			player.setVector("right");
		} else if (k == KeyEvent.VK_UP) {
			player.setVector("up");
		} else if (k == KeyEvent.VK_DOWN) {
			player.setVector("down");
		} else if (k == KeyEvent.VK_Q) {
			System.exit(0);
		} else if (k == KeyEvent.VK_A) {
			player2.setVector("left");
		} else if (k == KeyEvent.VK_D) {
			player2.setVector("right");
		} else if (k == KeyEvent.VK_W) {
			player2.setVector("up");
		} else if (k == KeyEvent.VK_S) {
			player2.setVector("down");
		}
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_RIGHT) {
			player.stopVector("hor");
		} else if (k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN) {
			player.stopVector("ver");
		} else if (k == KeyEvent.VK_A || k == KeyEvent.VK_D) {
			player2.stopVector("hor");
		} else if (k == KeyEvent.VK_W || k == KeyEvent.VK_S) {
			player2.stopVector("ver");
		}
	}

}
