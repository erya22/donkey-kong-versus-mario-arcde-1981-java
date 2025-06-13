package donkeykongvsmario.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.model.Direction;
import donkeykongvsmario.model.MovementState;
import donkeykongvsmario.model.Player;
import donkeykongvsmario.model.State;
import donkeykongvsmario.obspattern.Observer;
import donkeykongvsmario.view.PlayerView;

public class PlayerController implements Observer, KeyListener {
	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

	private Player player;
	private PlayerView playerView;
	
	public PlayerController(Player player, PlayerView playerView) {
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
//	public void moveRight() {
//	    player.setX(player.getX() + player.getVelocityX());
//	    notifyView();
//	}
//	
//	public void moveLeft() {
//		player.setX(player.getX() - player.getVelocityX());
//		notifyView();
//	}
//	
//	public void climbUp() {
//		player.setY(player.getY() - player.getVelocityY());
//		notifyView();
//	}
//	
//	public void climbDown() {
//		player.setY(player.getY() + player.getVelocityY());
//		notifyView();
//	}
	
	public void moveRight() {
	    player.move(player.getVelocityX(), 0);
	    notifyView();
	}

	public void moveLeft() {
	    player.move(-player.getVelocityX(), 0);
	    notifyView();
	}

	public void climbUp() {
	    player.move(0, -player.getVelocityY());
	    notifyView();
	}

	public void climbDown() {
	    player.move(0, player.getVelocityY());
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
	    int keyPressed = e.getKeyCode();
	    if (keyPressed == KeyEvent.VK_RIGHT) {
	        player.move(5, 0);  // prova a muovere Mario a destra di 5 pixel
	    } else if (keyPressed == KeyEvent.VK_LEFT) {
	        player.move(-5, 0); // muovi a sinistra
	    } else if (keyPressed == KeyEvent.VK_UP) {
	        player.move(0, -5); // sali
	    } else if (keyPressed == KeyEvent.VK_DOWN) {
	        player.move(0, 5);  // scendi
	    }
	    notifyView();
	}


	
	public void notifyView() {
        playerView.update(player);  // La View si aggiorna con i dati del Model
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            player.setDirection(Direction.LEFT);
            player.walk(player.getDirection());
            break;
        case KeyEvent.VK_RIGHT:
            player.setDirection(Direction.RIGHT);
            player.walk(player.getDirection());
            break;
        case KeyEvent.VK_UP:
            player.climb(MovementState.UPCLIMB);
            break;
        case KeyEvent.VK_DOWN:
            player.climb(MovementState.DOWNCLIMB);
            break;
        case KeyEvent.VK_SPACE:
        	//TODO
            break;
		}
		
	}

	
	
	@Override
	public void keyReleased(KeyEvent e) {
		player.setMovement(MovementState.IDLE);
	}
	
	public void update(float deltaTime) {
if (player.getState() == State.DEAD || player.getState() == State.HIT) return;
    	
//    	player.updatePhysics();
    	
    	if (player.getState() == State.INVINCIBLE) {
            long elapsed = System.currentTimeMillis() - player.getStateTime();
            if (elapsed >= player.getIMMUNITY()) {
                player.setState(State.ALIVE); 
            }
    	}
	}

	
	
}
