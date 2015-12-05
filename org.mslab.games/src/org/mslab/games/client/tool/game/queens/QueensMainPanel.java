package org.mslab.games.client.tool.game.queens;

import org.mslab.games.client.core.ui.panels.CommonDisclosurePanel;
import org.mslab.games.client.core.ui.panels.GridPanel;
import org.mslab.games.client.tool.game.peg.PegGameCell;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class QueensMainPanel extends GridPanel {
	private QueensPage _owner; 
	private QueensBoard _board;
	private QueensStatus _queensStatus;
	private InstructionHTML _instruction; 
	
	QueensMainPanel(QueensPage owner) {
		_owner = owner;
		int row = 0;
		
		SimplePanel left = new SimplePanel(); 
		_grid.setWidget(row, 0, left);
		_grid.getFlexCellFormatter().setWidth(row, 0, "45%");
		
		_board = new QueensBoard(this); 
		_grid.setWidget(row, 1, _board);
		
		SimplePanel right = new SimplePanel(); 
		_grid.setWidget(row, 2, right);
		_grid.getFlexCellFormatter().setWidth(row, 2, "45%");
		row++; 
		
		_queensStatus = new QueensStatus();
		_queensStatus.getElement().getStyle().setMarginTop(24, Unit.PX);
		_grid.setWidget(row, 0, _queensStatus);
		_grid.getFlexCellFormatter().setColSpan(row, 0, 3);
		row++;
		
		_instruction = new InstructionHTML();
		GameDisclosurePanel instructionPanel = new GameDisclosurePanel("Instruction", _instruction);
		instructionPanel.getElement().getStyle().setMarginTop(24, Unit.PX);
		_grid.setWidget(row, 0, instructionPanel);
		_grid.getFlexCellFormatter().setColSpan(row, 0, 3);
		row++;
	}

	public void setNbQueensTotal(int size) {
		_board.setNbQueensTotal(size);
		_queensStatus.setNbQueens(size);
	}

	public void setNbQueensRemaining(int remaining) {
		_queensStatus.setNbQueensRemaining(remaining);
	}

	public void reset() {
		_board.reset();
	}

	public int addQueens() {
		return _owner.addQueens();
	}

	/*
	public void setSuccessStatus() {
		_queensStatus.setSuccessStatus();
	}*/

	public void setFailureStatus() {
		_queensStatus.setFailureStatus();
	}
	
	//
	// inner classes
	//
	private class GameDisclosurePanel extends CommonDisclosurePanel {
		public GameDisclosurePanel(String name, Widget childWidget) {
			super(name, childWidget);
			setExpanded(true); 
		}
		
		@Override
		public void onOpen(OpenEvent<DisclosurePanel> event) {
			super.onOpen(event);
			HTML hideLbl = (HTML)_showHeader.getWidget(0); 
			hideLbl.setHTML("Cacher les instructions"); 
		}
		
		@Override
		public void onClose(CloseEvent<DisclosurePanel> event) {
			super.onClose(event);
			HTML showLbl = (HTML)_showHeader.getWidget(0); 
			showLbl.setHTML("Afficher les instructions"); 
		}
	}
	
	private class InstructionHTML extends HTML {
		private boolean _selected = false; 
		
		InstructionHTML() {
			_selected = false; 
			refreshHTML(); 
		}

		public void setSelected(PegGameCell cell) {
			_selected = (cell != null);
			refreshHTML(); 
		}

		private void refreshHTML() {
			String html = 
				"Placer toutes les reines sur l'&eacute;chiquier, chaque reine plac&eacutee " +
			    "sur une rang&eacutee, une colonne et une diagonale diff&eacuterente."; 
			setHTML(html);
		}
	}
}
