package donkeykongvsmario.controller;

import java.awt.event.KeyEvent;

import donkeykongvsmario.model.Player;
import donkeykongvsmario.view.PlayerView;

public class GameEngine {

	private PlayerController playerController;
	
	public GameEngine(Player player, PlayerView playerView) {
		this.playerController = new PlayerController(player, playerView);
	}
	
	public void handleInput(KeyEvent e) {
		playerController.keyPressed(e);
	}
	
	public void updateGame() {
		playerController.update();
	}
	
	public void updateAnimation(long currentTimeMillis) {
	    if (playerController.getPlayer().getCurrentSpriteMap() == null || playerController.getPlayer().getCurrentSpriteMap().length == 0) return;

	    if (currentTimeMillis - playerController.getPlayer().getLastFrameTime() >= playerController.getPlayer().getFrameDelay()) {
	    	playerController.getPlayer().setCurrentFrameIndex((playerController.getPlayer().getCurrentFrameIndex() + 1) % playerController.getPlayer().getCurrentSpriteMap().length); 
	    	playerController.getPlayer().setLastFrameTime(currentTimeMillis); 
	    }
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
	
	
	

	
}
