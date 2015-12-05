package org.mslab.games.client.tool.game.queens;

import org.mslab.games.client.tool.game.GameShell;

import com.google.gwt.user.client.ui.ScrollPanel;

public class QueensShell extends ScrollPanel {
	
	public QueensShell(GameShell owner) {
		QueensPage page = new QueensPage(owner);
		setWidget(page);
	}

}
