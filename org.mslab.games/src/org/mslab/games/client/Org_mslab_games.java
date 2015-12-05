package org.mslab.games.client;

import org.mslab.games.client.tool.ApplicationContext;
import org.mslab.games.client.tool.game.GameHome;
import org.mslab.games.client.tool.game.GameShell;
import org.mslab.games.client.tool.game.queens.QueensPage;
import org.mslab.games.client.tool.game.queens.QueensShell;
import org.mslab.games.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This is the entry point method.
 * 
 * How to create a splash screen while GWT loads
 *   http://turbomanage.wordpress.com/2009/10/13/how-to-create-a-splash-screen-while-gwt-loads/
 *   
 * How to create a splash (animated) gif
 *   http://www.ajaxload.info/
 */
public class Org_mslab_games implements EntryPoint {
	private RootLayoutPanel _root; 
	
	public void onModuleLoad() {
		// Set GWT container invisible
		_root = RootLayoutPanel.get();
		_root.setVisible(false);
		
		//create the context
		ApplicationContext ctx = ApplicationContext.getInstance();
		init(ctx);		
	}
	
	private void init(ApplicationContext ctx) {
		//Load the application (can be long)
		//ApplicationShell shell = new ApplicationShell(); 
		//EducShell shell = new EducShell();
		//BabyShell shell = new BabyShell();
		//GameShell shell = new GameShell();
		//QueensShell shell = new QueensShell(); 
		GameShell home = new GameShell();
		_root.add(home);
		
		// Hide the splash & set GWT container visible
		DOM.removeChild(RootPanel.getBodyElement(), DOM.getElementById("splashScreen"));
		_root.setVisible(true);
	}
}
