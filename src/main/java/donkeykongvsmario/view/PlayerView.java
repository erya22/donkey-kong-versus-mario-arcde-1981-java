package donkeykongvsmario.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.model.ActionState;
import donkeykongvsmario.model.AnimationType;
import donkeykongvsmario.model.Direction;
import donkeykongvsmario.model.Player;
import donkeykongvsmario.utils.GameConfigurator;

public class PlayerView extends JComponent {
	private static final Logger log = LoggerFactory.getLogger(PlayerView.class);

	private Player player;
	
	public PlayerView(Player player) {
		this.player = player;
	}
	
	 public void draw(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			BufferedImage image = null;

			int drawX = player.getX();
		    int drawY = player.getY();
		    
		    ActionState action = player.getActionState();
		    Direction direction = player.getDirection();
		    SimpleEntry<ActionState, Direction> key = new SimpleEntry<>(action, direction);
		    
		    BufferedImage[] spriteMap = player.getCurrentSpriteMap();
		    int frameIndex = player.getCurrentFrameIndex();

		    if (spriteMap != null && frameIndex >= 0 && frameIndex < spriteMap.length) {
		        image = spriteMap[frameIndex];
		    }
		    
		    if (image != null) {
		        g2.drawImage(image, drawX, drawY, GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE, null);
		    }
		    
		    animate();


		    
//		    BufferedImage[] frames = player.getAnimations().get(key);
//			
//			//state time è sia per hit che per invincible
//	    	if (player.getActionState() == ActionState.HIT && frames != null) {
//	            int frameCount = frames.length;
//	    		int frameIndex = (int) ((System.currentTimeMillis() - player.getStateTime()) / (player.getHIT_DURATION() / 5));
//	            frameIndex = Math.min(frameIndex, frameCount - 1); // evita IndexOutOfBounds
//	            image = frames[frameIndex];
//	        } else if (player.getActionState() == ActionState.JUMPING) {
//	        	SimpleEntry<ActionState, Direction> jumpKey = new SimpleEntry<>(ActionState.JUMPING, direction);
//	            BufferedImage[] jumpFrames = player.getAnimations().get(jumpKey);
//	            image = (jumpFrames != null && jumpFrames.length > 0) ? jumpFrames[0] : (frames != null ? frames[0] : null);
//	        } else if (player.getActionState() == ActionState.IDLE) {
//	        	SimpleEntry<ActionState, Direction> idleKey = new SimpleEntry<>(ActionState.IDLE, direction);
//	            BufferedImage[] idleFrames = player.getAnimations().get(idleKey);
//	            image = (idleFrames != null && idleFrames.length > 0) ? idleFrames[0] : (frames != null ? frames[0] : null);
//	        } else {
//	        	 // Other animations - cycle through frames based on spriteNum
//	            if (frames != null && frames.length > 0) {
//	                image = frames[(player.getSpriteNum() - 1) % frames.length];
//	            }
//	        }
//	    	
//	    	if (image != null) {
//	    		g2.drawImage(image, drawX, drawY, GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE, null);
//	    	}
//	    	animate();
	    		
	    		
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
		 repaint();
	 }
	 
	 private void animate() {
		 log.debug("Stato attuale: " + player.getActionState());
		 log.debug("Frame attuale: " + player.getCurrentFrameIndex());
		 log.debug("Sprite counter: " + player.getSpriteCounter());
		 if (player.getActionState() == ActionState.IDLE) {
			 player.setSpriteNum(1); // Fermo, resetta al primo frame idle
			 player.setCurrentFrameIndex(0); // Mostra il primo frame della sequenza idle
			 return; // Esci senza aggiornare altri frame
		 }
	        
		 int spriteCounter = player.getSpriteCounter() + 1;
	        
		 int spriteNum = player.getSpriteNum();

		 player.setSpriteCounter(spriteCounter);
	        
		 if (spriteCounter > 10) {
			 spriteNum++;
			 BufferedImage[] frames = player.getCurrentSpriteMap();
			 int maxFrame = (frames != null) ? frames.length : 1;
			 if (spriteNum > maxFrame) {
				 spriteNum = 1;
			 }
			 player.setSpriteNum(spriteNum);
			 player.setCurrentFrameIndex(spriteNum - 1); // Assicura il cambio di frame
			 player.setSpriteCounter(0);
		 }

	 }


    
//	 private void animate() {
//		    int spriteCounter = player.getSpriteCounter() + 1;
//		    int spriteNum = player.getSpriteNum();
//
//		    player.setSpriteCounter(spriteCounter);
//
//		    if (spriteCounter > 10) {
//		        spriteNum++;
//		        
//		        // Calcola la key corretta in base allo stato e direzione
//		        AnimationType animKey = getAnimationKeyForPlayer(player);
//
//		        BufferedImage[] frames = player.getAnimations().get(animKey);
//		        int maxFrame = (frames != null) ? frames.length : 1;
//
//		        if (spriteNum > maxFrame) {
//		            spriteNum = 1;
//		        }
//
//		        player.setSpriteNum(spriteNum);
//		        player.setSpriteCounter(0);
//		    }
//		}

		// Metodo di utilità per determinare l'animazione corrente in base a stato e direzione
		private AnimationType getAnimationKeyForPlayer(Player player) {
		    ActionState state = player.getActionState();
		    Direction dir = player.getDirection();

		    switch (state) {
		        case IDLE:
		            return dir == Direction.LEFT ? AnimationType.IDLE_LEFT : AnimationType.IDLE_RIGHT;
		        case WALKING:
		            return dir == Direction.LEFT ? AnimationType.WALK_LEFT : AnimationType.WALK_RIGHT;
		        case JUMPING:
		            return dir == Direction.LEFT ? AnimationType.JUMP_LEFT : AnimationType.JUMP_RIGHT;
		        case HIT:
		            return dir == Direction.LEFT ? AnimationType.HIT_LEFT : AnimationType.HIT_RIGHT;
		        case CLIMBING_UP:
		            return AnimationType.CLIMB_UP;
		        case CLIMBING_DOWN:
		            return AnimationType.CLIMB_DOWN;
		        default:
		            return dir == Direction.LEFT ? AnimationType.IDLE_LEFT : AnimationType.IDLE_RIGHT;
		    }
		}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	 @Override
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    draw(g);  
		}
    
    

	
}
