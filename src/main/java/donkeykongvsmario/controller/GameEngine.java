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
		playerController.processInput(e);
	}
	
	public void updateGame() {
		playerController.update();
	}
	
}
