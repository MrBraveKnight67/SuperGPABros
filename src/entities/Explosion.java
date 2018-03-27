package entities;

import javax.imageio.ImageIO;

public class Explosion extends Entity {

	private int frames;
	private double secs = 0.5;

	public Explosion(double x, double y) {
		posX = x;
		posY = y;
		needed = true;
		frames = -1;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/Grades/explosion.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		frames++;
		if(frames == secs*60) {
			needed = false;
		}
	}

}