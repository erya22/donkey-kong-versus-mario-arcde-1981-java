package donkeykongvsmario.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player extends Entity {
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	
	private MovementState movement;
	private State state;
	
	private int jumpStrenght = 15;
	
	private int vite;
	
	//state time al posto di invincible times
	private long stateTime;
	private final int IMMUNITY = 2000;
	private final long HIT_DURATION = 1500;
	
	private  int highscore;
	
	public Player(Universe universe, String name) {
		super(universe, name);
		getPlayerImage();
		setDefaultValues();
	}
	
	public void setDefaultValues()  {
		
		this.setX(0);
		this.setY(0);
		
		this.setVelocityY(0);
		this.setVelocityX(0);
		
		this.setDirection(Direction.RIGHT);
		this.setTerrain(Terrain.BEAM);
		
		this.setState(State.ALIVE);
	}
	
	public void getPlayerImage() {
		HashMap<String, BufferedImage[]> spriteMap = this.getSpriteMap();
        try {
        	
            // UP/DOWN
            BufferedImage[] up = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/b" + (i+1) + ".png"));
            }
            spriteMap.put("up", up);
            spriteMap.put("down", up);

            // RIGHT
            BufferedImage[] right = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                right[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/a" + (i + 1) + ".png"));
            }
            spriteMap.put("right", right);

            // LEFT
            BufferedImage[] left = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                left[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m" + (i + 1) + ".png"));
            }
            spriteMap.put("left", left);
            
            // JUMP ANIMATION
            BufferedImage[] jumpR = new BufferedImage[1];
            jumpR[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/c3.png"));
            spriteMap.put("jumpR", jumpR);
            BufferedImage[] jumpL = new BufferedImage[1];
            jumpL[0] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/m3.png"));
            spriteMap.put("jumpL", jumpL);

            BufferedImage[] hitFrames = new BufferedImage[5];
            // DEATH ANIMATION
            for (int i = 0; i < 5; i++) {
                hitFrames[i] = ImageIO.read(getClass().getResourceAsStream("/PLAYER/e" + (i + 1) + ".png"));
            }
            
            spriteMap.put("hit", hitFrames);


        } catch (IOException e) {
            e.printStackTrace();
        }
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
	

}
