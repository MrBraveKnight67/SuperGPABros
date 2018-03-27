package entities;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Door extends Entity {

	public Door(int x, int y) {
		posX = x;
		posY = y;
		radius = 10;
		needed = true;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/door1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean updateB(Entity player) {
		if (player.collides(posX, posY, radius)) {
			return true;
		}
		return false;
	}

	public void update(Entity player) {}
}