package donkeykongvsmario.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import donkeykongvsmario.controller.GameInputHandler;
import donkeykongvsmario.model.Universe;

public class GameWindow extends JFrame {
	
	public GameWindow() {
		setTitle("DK VS MARIO - ARCADE 1981");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		Universe universe = new Universe();
		UniversePanel uniPanel = new UniversePanel(universe);
		add(uniPanel, BorderLayout.CENTER);
		
//		addKeyListener(new GameInputHandler());
		
		setVisible(true);
	}
	
	
}
