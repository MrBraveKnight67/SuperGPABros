package entities;

import java.awt.*;
import main.Game;

public class HUD {

	private Player player;
	private Font font;
	private int x, y;

	public HUD(Player player, int x, int y) {
		this.player = player;
		this.x = x;
		this.y = y;
		font = new Font("Arial", Font.PLAIN, 14);
	}

	public void draw(Graphics2D g) {
		String rank = getRank(player.gpa[0]);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("GPA: " + String.format("%.2f", player.gpa[0]), x, y);
		g.drawString("RANK: " + rank, x, y + 12);
	}

	public String getRank(double gpa) {
		int i = (int) (gpa * 3);
		if (i == 12) {
			i--;
		}
		return Game.ranks[i];
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
