package donkeykongvsmario.model;

import java.awt.image.BufferedImage;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Entity {
	private static final Logger log = LoggerFactory.getLogger(Entity.class);
	
	private final String name;
	
	private int x, y;
	private int velocityX, velocityY;
	private int height, width;
	
	private Direction direction = Direction.RIGHT;
	private Terrain terrain = Terrain.AIR;
	
	private int frameIndex = 0;
	private int frameCounter = 0;
	
	private int spriteCounter;
	private int spriteNum;
	
	private Universe universe;
	
	private ActionState actionState = ActionState.IDLE;
	private LifeState lifeState = LifeState.ALIVE;
	
	private HashMap<SimpleEntry<ActionState, Direction>, BufferedImage[]> animations = new HashMap<>();
	
	
	public Entity(Universe universe, String name) {
		this.universe = universe;
		this.name = name;
	}
	
	public BufferedImage getCurrentFrame() {
		SimpleEntry<ActionState, Direction> key = new SimpleEntry<>(actionState, direction);
		BufferedImage[] frames = animations.get(key);
		if (frames == null || frames.length == 0) return null;

		frameCounter++;
		if (frameCounter >= 10) { // cambia ogni 10 tick (regolabile)
			frameIndex = (frameIndex + 1) % frames.length;
			frameCounter = 0;
		}
		return frames[frameIndex];
	}
	
	public int getFrameCounter() {
		return frameCounter;
	}

	public void setFrameCounter(int frameCounter) {
		this.frameCounter = frameCounter;
	}

	public HashMap<SimpleEntry<ActionState, Direction>, BufferedImage[]> getAnimations() {
		return animations;
	}

	public void setAnimations(ActionState action, Direction direction, BufferedImage[] frames) {
	    this.animations.put(new SimpleEntry<>(action, direction), frames);
	}

	public BufferedImage[] getAnimations(ActionState action, Direction direction) {
	    return animations.get(new SimpleEntry<>(action, direction));
	}

	public void addAnimation(ActionState action, Direction dir, BufferedImage[] frames) {
		animations.put(new SimpleEntry<>(action, dir), frames);
	}
	
	public ActionState getActionState() {
		return actionState;
	}

	public void setActionState(ActionState actionState) {
		if (this.actionState != actionState) {
			this.actionState = actionState;
			frameIndex = 0;
			frameCounter = 0;
		}
	}

	public LifeState getLifeState() {
		return lifeState;
	}

	public void setLifeState(LifeState lifeState) {
		this.lifeState = lifeState;
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
