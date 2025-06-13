package donkeykongvsmario.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.obspattern.Observer;

public class Player extends Entity {
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	
	private List<Observer> observers = new ArrayList<>();

	private MovementState movement;
	private State state;
	private Animation animation;
	
	private int jumpStrenght = 15;
	
	private int vite;
	
	//state time al posto di invincible times
	private long stateTime;
	private final int IMMUNITY = 2000;
	private final long HIT_DURATION = 1500;
	
	private  int highscore;
	
	private HashMap<Animation, BufferedImage[]> spriteMap;
	
	private BufferedImage currentSprite;
	
	public Player(Universe universe, String name) {
		super(universe, name);

		this.spriteMap = new HashMap<Animation, BufferedImage[]>();
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
		this.setY(0);
		
		this.setVelocityY(0);
		this.setVelocityX(0);
		
		this.setDirection(Direction.RIGHT);
		this.setTerrain(Terrain.BEAM);
		this.setState(State.ALIVE);
		this.setAnimation(Animation.IDLE);
		this.setMovement(MovementState.IDLE);
		
	}
	
	public HashMap<Animation, BufferedImage[]> getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(HashMap<Animation, BufferedImage[]> spriteMap) {
		this.spriteMap = spriteMap;
	}

	public void getPlayerImage() {
        try {
            // UP/DOWN
            BufferedImage[] up = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/b" + (i+1) + ".png"));
            }
            spriteMap.put(Animation.CLIMBU, up);
            spriteMap.put(Animation.CLIMBD, up);

            // RIGHT
            BufferedImage[] right = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                right[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/a" + (i + 1) + ".png"));
            }
            spriteMap.put(Animation.WALKR, right);
            spriteMap.put(Animation.IDLE, right);
            // LEFT
            BufferedImage[] left = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                left[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m" + (i + 1) + ".png"));
            }
            spriteMap.put(Animation.WALKL, left);
            
            // JUMP ANIMATION
            BufferedImage[] jumpR = new BufferedImage[1];
            jumpR[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/c3.png"));
            spriteMap.put(Animation.JUMPR, jumpR);
            BufferedImage[] jumpL = new BufferedImage[1];
            jumpL[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m3.png"));
            spriteMap.put(Animation.JUMPL, jumpL);

            BufferedImage[] hitFramesR = new BufferedImage[5];
            // DEATH ANIMATION
            for (int i = 0; i < 5; i++) {
                hitFramesR[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/e" + (i + 1) + ".png"));
            }
            
            spriteMap.put(Animation.HITR, hitFramesR);
            
            BufferedImage[] hitFramesL = new BufferedImage[6];
            for (int i = 5; i > 0; i--) {
            	hitFramesL[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/e" + i + ".png"));	
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public MovementState getMovement() {
		return movement;
	}

	public void setMovement(MovementState movement) {
		this.movement = movement;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
	
	public void climb(MovementState movement) {
		
	}
	
	public void walk(Direction direction) {
		
	}
	
	public BufferedImage getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(BufferedImage currentSprite) {
		this.currentSprite = currentSprite;
	}

	public void move(int dx, int dy) {
	    int newX = this.getX() + dx;
	    int newY = this.getY() + dy;

	    // usa l'universo per verificare la collisione
	    if (!getUniverse().checkCollision(newX, newY, getWidth(), getHeight())) {
	        // nessuna collisione, aggiorna posizione
	        this.setX(newX);
	        this.setY(newY);
	    } else {
	        // collisione, blocca movimento
	        System.out.println("Collisione rilevata, movimento bloccato");
	    }
	}

	public void idle() {
		if (getTerrain() != Terrain.AIR) {
			return;
		}
		switch(getMovement()) {
			case JUMP: 
				this.setMovement(MovementState.FALL);
				this.setVelocityY(0);
				break;
			
		}
		
	}

}
