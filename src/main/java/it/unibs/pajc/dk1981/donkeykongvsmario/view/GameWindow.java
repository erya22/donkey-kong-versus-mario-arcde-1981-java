package it.unibs.pajc.dk1981.donkeykongvsmario.view;

import java.awt.Color;

import javax.swing.JFrame;

import it.unibs.pajc.dk1981.donkeykongvsmario.controller.GameInputHandler;

public class GameWindow extends JFrame {
	
	public GameWindow() {
		setTitle("DK VS MARIO - ARCADE 1981");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UniversePanel uniPanel = new UniversePanel();
		add(uniPanel);
		
		addKeyListener(new GameInputHandler());
	}
	
	
}
