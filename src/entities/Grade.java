package entities;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Grade extends Entity {
	int val;
	Random rand;

	public Grade(int x, int y, int val, int speed) {
		posX = x;
		posY = y;
		radius = 10;
		this.val = val;
		this.speed = speed;
		
		needed = true;
		try {
			if (val == 4) {
				img = ImageIO.read(getClass().getResourceAsStream("/Grades/gradeA.png"));
			} else if (val == 3) {
				img = ImageIO.read(getClass().getResourceAsStream("/Grades/gradeB.jpg"));
			} else if (val == 2) {
				img = ImageIO.read(getClass().getResourceAsStream("/Grades/gradeC.png"));
			} else if (val == 1) {
				img = ImageIO.read(getClass().getResourceAsStream("/Grades/gradeD.png"));
			} else if (val == 0) {
				img = ImageIO.read(getClass().getResourceAsStream("/Grades/gradeF.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		rand = new Random();
	}

	public void update(Player player) {
		if (player.collides(posX, posY, radius)) {
			player.gpa[1] += val;
			player.gpa[2]++;
			player.gpa[0] = player.gpa[1] / player.gpa[2];
			needed = false;
		}
		int xDir = rand.nextInt(10);
		int yDir = rand.nextInt(10);
		if (xDir > 4) {
			this.setVector("right");
		} else {
			this.setVector("left");
		}
		if (yDir > 4) {
			this.setVector("up");
		} else {
			this.setVector("down");
		}
			this.move(dx, dy);
		this.stopVector("hor");
		this.stopVector("ver");
	}
}