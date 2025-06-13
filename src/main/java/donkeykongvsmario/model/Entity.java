package donkeykongvsmario.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Entity {
	private static final Logger log = LoggerFactory.getLogger(Entity.class);
	
	private final String name;
	
	private int x, y;
	private int velocityX, velocityY;
	
	private Direction direction;
	private Terrain terrain;
	
	private int spriteCounter;
	private int spriteNum;
	
	private Universe universe;
	
	private int frameIndex;
	
	private int height, width;
	
	public Entity(Universe universe, String name) {
		this.universe = universe;
		this.name = name;
	}
	
	

	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		log.info("set x({})", x);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		log.info("set x({})", x);
		this.y = y;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getSpriteCounter() {
		return spriteCounter;
	}

	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}

	public int getSpriteNum() {
		return spriteNum;
	}

	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}

	public Universe getUniverse() {
		return universe;
	}

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	public String getName() {
		return name;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public int getFrameIndex() {
		return frameIndex;
	}

	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}
	
	
	
	
	
}
