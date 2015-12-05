package org.mslab.games.client.tool.game;

import org.mslab.games.client.tool.game.peg.PegGameShell;
import org.mslab.games.client.tool.game.queens.QueensShell;

import com.google.gwt.user.client.ui.DeckPanel;

public class GameShell extends DeckPanel {
	
	public GameShell() {
		GameHome home = new GameHome(this); 
		add(home);
		
		QueensShell queens = new QueensShell(this); 
		add(queens);
		
		PegGameShell peg = new PegGameShell(this); 
		add(peg);
		
		showWidget(0);
	}

	public void showQueen() {
		showWidget(1);
	}
	
	public void showSolitaire() {
		showWidget(2);
	}

	public void goHome() {
		showWidget(0);
	}



}
