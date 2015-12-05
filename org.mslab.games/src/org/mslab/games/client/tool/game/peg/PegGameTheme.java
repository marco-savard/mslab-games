package org.mslab.games.client.tool.game.peg;

import org.mslab.games.client.core.ui.theme.AbstractTheme;
import org.mslab.games.shared.types.Color;

public class PegGameTheme extends AbstractTheme {
	public static final Color FG_COLOR = new Color("493D26");
	public static final Color BG_COLOR = new Color("F3E5AB");
	public static final Color CHECK_COLOR = Color.GREEN_DARK; 
	public static final Color DELETE_COLOR = Color.RED_FIRE_BRICK;
	
	public PegGameTheme() {
		_primaryColor = FG_COLOR; 
	}
	
}
