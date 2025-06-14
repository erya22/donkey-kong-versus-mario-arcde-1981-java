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
	
	 public void draw(Graphics g, int offsetX, int offsetY) {
			Graphics2D g2 = (Graphics2D) g;
			BufferedImage image = null;

			int drawX = player.getX() + offsetX;
		    int drawY = player.getY() + offsetY;
		    
		    
		    
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


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	 @Override
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    draw(g, GameConfigurator.MAP_OFFSET_X, GameConfigurator.MAP_OFFSET_Y);  
		}
    
    

	
}
