package donkeykongvsmario.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.obspattern.Observer;
import donkeykongvsmario.utils.GameConfigurator;

public class Player extends Entity {
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	
	private List<Observer> observers = new ArrayList<>();
	
	private int jumpStrenght = 15;
	
	private int vite = 3;
	
	//state time al posto di invincible times
	private long stateTime;
	private final int IMMUNITY = 2000;
	private final long HIT_DURATION = 1500;
	
	private final long frameDelay = 100;
	private long lastFrameTime;
	private int currentFrameIndex;
	
	private  int highscore;
	
	private BufferedImage[] currentSpriteMap;
	
	private HashMap<SimpleEntry<ActionState, Direction>, BufferedImage[]> animations;
	private AnimationType animationType;
	
	public Player(Universe universe, String name) {
		super(universe, name);

		getPlayerImage();
		setDefaultValues();
	}
	
	private void notifyObservers() {
        for (Observer observer : observers) {
//            observer.update(this);  // Supponendo che Observer abbia un metodo update(Player player)
        }
    }

	
	public void addObserver(Observer observer) {
	    observers.add(observer);
	}
	
	public void setDefaultValues()  {
		
		this.setX(0);
		this.setY(30* GameConfigurator.TILE_SIZE);
		
		this.setVelocityY(4);
		this.setVelocityX(4);
		
		this.setDirection(Direction.RIGHT);
		this.setTerrain(Terrain.BEAM);
		this.setAnimationType(AnimationType.IDLE_RIGHT);
		
		
		this.setLastFrameTime(0);
		this.setCurrentFrameIndex(0);
		
		this.setFrameIndex((int) ((System.currentTimeMillis() - this.getHIT_DURATION()) / (this.getHIT_DURATION() / 5)));
		
		
	}
	
	
	
	public int getCurrentFrameIndex() {
		return currentFrameIndex;
	}

	public void setCurrentFrameIndex(int currentFrameIndex) {
		this.currentFrameIndex = currentFrameIndex;
	}

	public void getPlayerImage() {
		 this.animations = new HashMap<>();

		
        try {
            // UP/DOWN
            BufferedImage[] up = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/b" + (i+1) + ".png"));
            }
            BufferedImage[] down = new BufferedImage[7];
            for (int i = 6; i >= 0; i--) {
                down[6 - i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/b" + (i + 1) + ".png"));
            }
            animations.put(new SimpleEntry<>(ActionState.CLIMBING_UP, Direction.NONE), up);
            animations.put(new SimpleEntry<>(ActionState.CLIMBING_DOWN, Direction.NONE), down);
            
            // RIGHT
            BufferedImage[] right = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                right[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/a" + (i + 1) + ".png"));
            }
            animations.put(new SimpleEntry<>(ActionState.WALKING, Direction.RIGHT), right);
 
            BufferedImage[] idleR = new BufferedImage[1];
            idleR[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/a" + 1 + ".png"));
            animations.put(new SimpleEntry<>(ActionState.IDLE, Direction.RIGHT), idleR);

            // LEFT
            BufferedImage[] left = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                left[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m" + (i + 1) + ".png"));
            }
            animations.put(new SimpleEntry<>(ActionState.WALKING, Direction.LEFT), left);

            BufferedImage[] idleL = new BufferedImage[1];
            idleL[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m" + 1 + ".png"));
            animations.put(new SimpleEntry<>(ActionState.IDLE, Direction.LEFT), idleL);

            // JUMP ANIMATION
            BufferedImage[] jumpR = new BufferedImage[1];
            jumpR[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/c3.png"));
            animations.put(new SimpleEntry<>(ActionState.JUMPING, Direction.RIGHT), jumpR);

            BufferedImage[] jumpL = new BufferedImage[1];
            jumpL[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m3.png"));
            animations.put(new SimpleEntry<>(ActionState.JUMPING, Direction.LEFT), jumpL);

            BufferedImage[] hitFramesR = new BufferedImage[5];
            // DEATH ANIMATION
            for (int i = 0; i < 5; i++) {
                hitFramesR[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/e" + (i + 1) + ".png"));
            }            
            animations.put(new SimpleEntry<>(ActionState.HIT, Direction.RIGHT), hitFramesR);

            BufferedImage[] hitFramesL = new BufferedImage[5];
            for (int i = 0; i < 5; i++) {
                hitFramesL[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/e" + (5 - i) + ".png"));
            }
            animations.put(new SimpleEntry<>(ActionState.HIT, Direction.LEFT), hitFramesL);


            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	

	public HashMap<SimpleEntry<ActionState, Direction>, BufferedImage[]> getAnimations() {
		return animations;
	}

	public void setAnimations(HashMap<SimpleEntry<ActionState, Direction>, BufferedImage[]> animations) {
		this.animations = animations;
	}

	public long getLastFrameTime() {
		return lastFrameTime;
	}

	public void setLastFrameTime(long lastFrameTime) {
		this.lastFrameTime = lastFrameTime;
	}

	public long getFrameDelay() {
		return frameDelay;
	}

	public int getJumpStrenght() {
		return jumpStrenght;
	}

	public void setJumpStrenght(int jumpStrenght) {
		this.jumpStrenght = jumpStrenght;
	}

	public int getVite() {
		return vite;
	}

	public void setVite(int vite) {
		this.vite = vite;
	}

	public long getStateTime() {
		return stateTime;
	}

	public void setStateTime(long stateTime) {
		this.stateTime = stateTime;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public int getIMMUNITY() {
		return IMMUNITY;
	}

	public long getHIT_DURATION() {
		return HIT_DURATION;
	}
	
	public void walk(Direction direction) {
	    log.info("Mario si muove verso {} ", direction);
	    setActionState(ActionState.WALKING);

	    switch (direction) {
	        case RIGHT:
	            setDirection(Direction.RIGHT);
	            BufferedImage[] rightAnim = getAnimations(ActionState.WALKING, Direction.RIGHT);
	            if (rightAnim != null) {
	                setCurrentSpriteMap(rightAnim);
	                this.setX(getX() + this.getVelocityX());
	            } else {
	                log.error("Right walking animation is null!");
	            }
	            break;
	        case LEFT:
	            setDirection(Direction.LEFT);
	            BufferedImage[] leftAnim = getAnimations(ActionState.WALKING, Direction.LEFT);
	            if (leftAnim != null) {
	                setCurrentSpriteMap(leftAnim);
	                this.setX(getX() - this.getVelocityX());
	            } else {
	                log.error("Left walking animation is null!");
	            }
	            break;
	        default:
	            log.warn("Direzione non gestita: {}", direction);
	            break;
	    }
	}

	
	public BufferedImage[] getAnimations(ActionState action, Direction dir) {
	    return animations.get(new SimpleEntry<>(action, dir));
	}

	
	public BufferedImage[] getCurrentSpriteMap() {
		return currentSpriteMap;
	}

	public void setCurrentSpriteMap(BufferedImage[] currentSpriteMap) {
		this.currentSpriteMap = currentSpriteMap;
	}

	public AnimationType getAnimationType() {
		return animationType;
	}

	public void setAnimationType(AnimationType animationType) {
		this.animationType = animationType;
	}
	
//	public void move(int dx, int dy) {
//	    int newX = this.getX() + dx;
//	    int newY = this.getY() + dy;
//
//	    // usa l'universo per verificare la collisione
//	    if (!getUniverse().checkCollision(newX, newY, getWidth(), getHeight())) {
//	        // nessuna collisione, aggiorna posizione
//	        this.setX(newX);
//	        this.setY(newY);
//	    } else {
//	        // collisione, blocca movimento
//	        System.out.println("Collisione rilevata, movimento bloccato");
//	    }
//	}

//	public void idle() {
//		if (getTerrain() != Terrain.AIR) {
//			return;
//		}
//		switch(getMovement()) {
//			case JUMP: 
//				this.setMovement(MovementState.FALL);
//				this.setVelocityY(0);
//				break;
//			
//		}
//		
//	}
	
	

}
