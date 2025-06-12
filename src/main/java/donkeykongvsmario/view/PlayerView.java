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
	
	public void update(Player player) {
		
		//Serve per settare le sprite
		if (player.getState() == State.HIT) {
			
		} else if (player.getMovement() == MovementState.JUMP) {
			
		} else if (player.getMovement() == MovementState.DOWNCLIMB) {
			
		} else if (player.getMovement() == MovementState.UPCLIMB) {
			
		} else if (player.getMovement() == MovementState.WALK) {
			
			if (player.getDirection() == Direction.RIGHT) {
				
			} else {
				
			}
		} 
		repaint();
	}
    
    public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		BufferedImage image;

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
        } else if (player.getMovement() == MovementState.IDLE) {
        	BufferedImage[] frames = player.getSpriteMap().get(player.getAnimation());
        	if (frames == null || frames.length == 0) {
        		frames = player.getSpriteMap().get(player.getAnimation());
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
    		
    		g2.drawImage(image, player.getX(), player.getY() , GameConfigurator.TILE_SIZE , GameConfigurator.TILE_SIZE, null);
        }
    	animate();
	}
    
    private void animate() {
		 int spriteCounter = player.getSpriteCounter() + 1;
		 int spriteNum = player.getSpriteNum();

		 player.setSpriteCounter(spriteCounter);

		 if (spriteCounter > 10) {
			 spriteNum++;
			 BufferedImage[] frames = player.getSpriteMap().get(player.getDirection());
			 int maxFrame = frames != null ? frames.length : 1;
			 if (spriteNum > maxFrame) {
				 spriteNum = 1;
			 }
			 player.setSpriteNum(spriteNum); // <- salva il nuovo frame
			 player.setSpriteCounter(0);     // <- resetta il contatore
		 }	    
	 }
    
    @Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    draw(g);  
	}

	
}
