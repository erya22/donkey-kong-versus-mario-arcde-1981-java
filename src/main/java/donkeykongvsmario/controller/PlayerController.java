package donkeykongvsmario.controller;

import java.awt.event.KeyEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.model.MovementState;
import donkeykongvsmario.model.Player;
import donkeykongvsmario.model.State;
import donkeykongvsmario.obspattern.Observer;
import donkeykongvsmario.view.PlayerView;

public class PlayerController implements Observer {
	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

	private Player player;
	private PlayerView playerView;
	
	public PlayerController(Player player, PlayerView view) {
		this.player = player;
		this.playerView = playerView;
		this.player.addObserver(this);
	}
	
	@Override
	public void update() {
		if (player.getState() == State.DEAD || player.getState() == State.HIT) return;
		
		//TODO: DA AGGIUNGERE SISTEMA CHE GESTISCE LA FISICA
		
		if (player.getState() == State.INVINCIBLE) {
            long elapsed = System.currentTimeMillis() - player.getStateTime();
            if (elapsed >= player.getIMMUNITY()) {
                player.setState(State.ALIVE); 
            }
    	}
	}
	
	// Nel PlayerController
	public void moveRight() {
	    player.setX(player.getX() + player.getVelocityX());
	    notifyView();
	}
	
	public void moveLeft() {
		player.setX(player.getX() - player.getVelocityX());
		notifyView();
	}
	
	public void climbUp() {
		player.setY(player.getY() - player.getVelocityY());
		notifyView();
	}
	
	public void climbDown() {
		player.setY(player.getY() + player.getVelocityY());
		notifyView();
	}
	
	//TODO:
	public void jump() {
	    if (player.getMovement() != MovementState.JUMP) {
	        player.setMovement(MovementState.JUMP);
	        notifyView();
	    }
	}

    public void processInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_UP:
            	climbUp();
            	break;
            case KeyEvent.VK_DOWN:
            	climbDown();
            	break;
            case KeyEvent.VK_SPACE:
                jump();
                break;
        }
    }

	
	public void notifyView() {
        playerView.update(player);  // La View si aggiorna con i dati del Model
    }

	
	
}
