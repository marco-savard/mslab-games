package org.mslab.games.client.tool.game;

import org.mslab.games.client.core.ui.StyleUtil;
import org.mslab.games.client.core.ui.theme.ThematicButton;
import org.mslab.games.client.tool.game.peg.PegGameTheme;
import org.mslab.games.shared.text.MessageFormat;
import org.mslab.games.shared.types.Color;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;

public class GameButton extends ThematicButton {
	
	public GameButton() {
		Color borderColor = PegGameTheme.FG_COLOR.blendWith(PegGameTheme.BG_COLOR); 
		
		Style style = getElement().getStyle();
		style.setColor(PegGameTheme.FG_COLOR.toString());
		style.setBorderStyle(BorderStyle.SOLID);
		style.setBorderColor(borderColor.toString());
		style.setBackgroundColor(PegGameTheme.BG_COLOR.toString());
		style.setProperty("borderWidth", "2px 2px 2px 2px");
		String shadow = MessageFormat.format("10px 10px 5px {0}", PegGameTheme.FG_COLOR.toString());
		StyleUtil.setBoxShadow(this, shadow);
	}

}
