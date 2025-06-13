package donkeykongvsmario.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.model.ActionState;
import donkeykongvsmario.model.AnimationType;
import donkeykongvsmario.model.Direction;
import donkeykongvsmario.model.LifeState;
import donkeykongvsmario.model.Player;
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
		if (player.getLifeState() == LifeState.DEAD || player.getActionState() == ActionState.HIT) return;
		
		//TODO: DA AGGIUNGERE SISTEMA CHE GESTISCE LA FISICA
		
		if (player.getLifeState() == LifeState.INVINCIBLE) {
            long elapsed = System.currentTimeMillis() - player.getStateTime();
            if (elapsed >= player.getIMMUNITY()) {
                player.setLifeState(LifeState.ALIVE); 
            }
    	}
	}

	
	//TODO:
	public void jump() {
//	    if (player.getMovement() != MovementState.JUMP) {
//	        player.setMovement(MovementState.JUMP);
//	        notifyView();
//	    }
	}

	public void notifyView() {
        playerView.updateView(player);  // La View si aggiorna con i dati del Model
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private Set<Integer> keysPressed = new HashSet<>();

	@Override
	public void keyPressed(KeyEvent e) {
		keysPressed.add(e.getKeyCode());
		switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            player.setDirection(Direction.LEFT);
            player.setAnimationType(AnimationType.WALK_LEFT);
            player.walk(player.getDirection());
            break;
        case KeyEvent.VK_RIGHT:
        	player.setDirection(Direction.RIGHT);
            player.setAnimationType(AnimationType.WALK_RIGHT);
            player.walk(player.getDirection());
            break;
        case KeyEvent.VK_UP:
//            player.climb(MovementState.UPCLIMB);
            break;
        case KeyEvent.VK_DOWN:
//            player.climb(MovementState.DOWNCLIMB);
            break;
        case KeyEvent.VK_SPACE:
        	//TODO
            break;
		}
		
		notifyView();
		
	}

	
	//AGGIUNGERE IDLEL
	@Override
	public void keyReleased(KeyEvent e) {
	    keysPressed.remove(e.getKeyCode());

	    // Imposta IDLE solo se nessun altro tasto di movimento è premuto
	    if (!keysPressed.contains(KeyEvent.VK_LEFT) && !keysPressed.contains(KeyEvent.VK_RIGHT)) {
	        player.setActionState(ActionState.IDLE);
	        player.setAnimationType(AnimationType.IDLE_RIGHT); 
	        player.setCurrentFrameIndex(0);  // Forza il primo frame idle
	        log.debug("Tasto rilasciato: Mario ora è IDLE");

	    }

	    notifyView();
	}
	
	
	public void update(float deltaTime) {
		if (player.getLifeState() == LifeState.DEAD || player.getActionState() == ActionState.HIT) return;
    	
//    	player.updatePhysics();
    	
    	if (player.getLifeState() == LifeState.INVINCIBLE) {
            long elapsed = System.currentTimeMillis() - player.getStateTime();
            if (elapsed >= player.getIMMUNITY()) {
                player.setLifeState(LifeState.ALIVE); 
            }
    	}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerView getPlayerView() {
		return playerView;
	}

	public void setPlayerView(PlayerView playerView) {
		this.playerView = playerView;
	}
	
	

	
	
}
