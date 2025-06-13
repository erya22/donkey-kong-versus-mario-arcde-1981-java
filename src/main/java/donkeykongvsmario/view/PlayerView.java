package donkeykongvsmario.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.model.Animation;
import donkeykongvsmario.model.Direction;
import donkeykongvsmario.model.MovementState;
import donkeykongvsmario.model.Player;
import donkeykongvsmario.model.State;
import donkeykongvsmario.utils.GameConfigurator;

public class PlayerView extends JComponent {
	private static final Logger log = LoggerFactory.getLogger(PlayerView.class);

	private Player player;
	
	public PlayerView(Player player) {
		this.player = player;
	}
	
	 public void draw(Graphics g, int offsetX, int offsetY) {
			Graphics2D g2 = (Graphics2D) g;
			BufferedImage image;

			int drawX = offsetX + player.getX();
		    int drawY = offsetY + player.getY();
			
			//state time è sia per hit che per invincible
	    	if (player.getState() == State.HIT) {
	            int frameIndex = (int) ((System.currentTimeMillis() - player.getStateTime()) / (player.getHIT_DURATION() / 5));
	            frameIndex = Math.min(frameIndex, 4); // evita IndexOutOfBounds
	            BufferedImage[] hitFrames = player.getSpriteMap().get(player.getAnimation());
	            image = hitFrames != null ? hitFrames[frameIndex] : null;
	        } else if (player.getMovement() == MovementState.JUMP) {
	        	Animation animation = (player.getDirection() == Direction.RIGHT) ? Animation.JUMPR : Animation.JUMPL;
	        	BufferedImage[] jumpFrames = player.getSpriteMap().get(animation);
	            image = (jumpFrames != null && jumpFrames.length > 0)
	                    ? jumpFrames[0]
	                    : player.getSpriteMap().get(player.getAnimation())[0];  // backup sicuro
	        } else if (player.getMovement() == MovementState.IDLE && player.getDirection() == Direction.RIGHT) {
	        	BufferedImage[] frames = player.getSpriteMap().get(Animation.IDLER);
	        	if (frames == null || frames.length == 0) {
	        		frames = player.getSpriteMap().get(Animation.IDLER);
	        	}
	        	image = frames[0];
	        } else if (player.getMovement() == MovementState.IDLE && player.getDirection() == Direction.LEFT) {
	        	BufferedImage[] frames = player.getSpriteMap().get(Animation.IDLEL);
	        	if (frames == null || frames.length == 0) {
	        		frames = player.getSpriteMap().get(Animation.IDLEL);
	        	}
	        	image = frames[0];
	        } else {
	            BufferedImage[] frames = player.getSpriteMap().get(player.getAnimation());
	            if (frames == null || frames.length == 0) {
	                frames = player.getSpriteMap().get(player.getAnimation()); // fallback
	            }
	            image = frames[(player.getSpriteNum() - 1) % frames.length];
	        }
	    	
	    	
	    	if (image != null) {
	    		
	    		g2.drawImage(image, drawX, drawY, GameConfigurator.TILE_SIZE , GameConfigurator.TILE_SIZE, null);
	        }
	    	animate();
		}
	 
	 public void update() {
		 long now = System.currentTimeMillis();
		 if (now - player.getLastFrameTime() >= player.getFrameDelay()) {
			 player.setCurrentFrameIndex((player.getCurrentFrameIndex() + 1) % player.getCurrentSpriteMap().length); 
			 player.setLastFrameTime(now);
		 }
	 }
	 
	 public void updateView(Player player) {
		 this.setPlayer(player);
	 }
    
    private void animate() {
		 int spriteCounter = player.getSpriteCounter() + 1;
		 int spriteNum = player.getSpriteNum();

		 player.setSpriteCounter(spriteCounter);

		 if (spriteCounter > 10) {
			 spriteNum++;
			 player.setCurrentSpriteMap(player.getSpriteMap().get(player.getAnimation()));
			 BufferedImage[] frames = player.getCurrentSpriteMap();
			 int maxFrame = frames != null ? frames.length : 1;
			 if (spriteNum > maxFrame) {
				 spriteNum = 1;
			 }
			 player.setSpriteNum(spriteNum); // <- salva il nuovo frame
			 player.setSpriteCounter(0);     // <- resetta il contatore
		 }	    
	 }

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
    
    

	
}
