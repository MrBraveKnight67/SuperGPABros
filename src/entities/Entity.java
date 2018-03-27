package entities;

import java.awt.image.BufferedImage;

public abstract class Entity {
	public double posX;
	public double posY;
	public double dy;
	public double dx;
	public int speed;
	public int radius;
	protected BufferedImage img;
	public boolean needed;

	public boolean collides(double tX, double tY, int tRadius) {
		double diffX = Math.abs(tX - posX);
		double diffY = Math.abs(tY - posY);
		if (Math.sqrt((diffX * diffX + diffY * diffY)) <= radius + tRadius) {
			return true;
		}
		return false;
	}

	public void setPosition(int x, int y) {
		move((int) (-posX + x), (int) (-posY + y));
	}

	public double getX() {
		return posX;
	}

	public double getY() {
		return posY;
	}

	public void move(double dx, double dy) {
		if (posX + dx > 320 - radius) {
			posX = 320 - radius;
		} else if (posX + dx < radius) {
			posX = radius;
		} else {
			posX += dx;
		}

		if (posY + dy > 240 - radius) {
			posY = 240 - radius;
		} else if (posY + dy < radius) {
			posY = radius;
		} else {
			posY += dy;
		}
	}

	public void update() {
		this.move(dx, dy);
	}

	public void setVector(String direction) {
		if (direction == "left") {
			dx = -speed;
		} else if (direction == "right") {
			dx = speed;
		} else if (direction == "up") {
			dy = -speed;
		} else if (direction == "down") {
			dy = speed;
		}
	}
	
	public void stopVector(String axis) {
		if(axis == "hor") {
			dx = 0;
		}else if(axis == "ver") {
			dy = 0;
		}
	}

	public void draw(java.awt.Graphics2D g) {
		g.drawImage(img, (int) (posX - radius), (int) (posY - radius), null);
	}
	
	public boolean needed() {
		return needed;
	}

}
