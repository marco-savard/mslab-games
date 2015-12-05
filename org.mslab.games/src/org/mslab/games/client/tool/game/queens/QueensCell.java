package org.mslab.games.client.tool.game.queens;

import org.mslab.games.client.core.ui.StyleUtil;
import org.mslab.games.client.core.ui.panels.GridPanel;
import org.mslab.games.shared.types.Color;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class QueensCell extends FocusPanel implements MouseOverHandler, MouseOutHandler, ClickHandler {
	enum ContentState {EMPTY, HOVER, SELECTED, DISABLED};
	private ContentState _contentState = ContentState.EMPTY; 
	private boolean _enabled = true;
	private QueensBoard _owner;
	private HTML _piece; 
	
	QueensCell(QueensBoard owner, int row, int col, int nbQueens) {
		_owner = owner;
		_row = row;
		_col = col;
		
		int size = computeSizeFromNbQueens(nbQueens); 
		setSize(size + "px", size + "px");
		
		
		//boolean black = ((_row + _col) % 2) == 0; 
		//Color bgColor = black ? Color.BLACK : Color.WHITE; 
		//Color fgColor = black ? Color.WHITE : Color.BLACK; 
		//getElement().getStyle().setBackgroundColor(bgColor.toString());
		//getElement().getStyle().setColor(fgColor.toString());
		
		_piece = new HTML("&#9813;"); 
		setWidget(_piece);
		
		//center horizontally
		_piece.getElement().getStyle().setTextAlign(TextAlign.CENTER);
		
		//center vertically
		_piece.getElement().getStyle().setPosition(Position.RELATIVE);
		_piece.getElement().getStyle().setTop(50, Unit.PCT);
		StyleUtil.transform(_piece, "translateY(-50%)");
		
		addMouseOverHandler(this);
		addMouseOutHandler(this); 
		addClickHandler(this);
		refresh(); 
		
		//_grid.setWidget(0, 0, piece);
		//_grid.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	private int computeSizeFromNbQueens(int nbQueens) {
		int size = 80; 
		
		if (nbQueens == 4) {
			size = 100;
		} else if (nbQueens > 8) {
			size = 640 / nbQueens;
		}
		
		return size;
	}

	public int getRow() {
		return _row;
	}

	public int getCol() {
		return _col;
	}
	
	private int _row, _col;

	@Override
	public void onMouseOver(MouseOverEvent event) {
		if (_enabled && (_contentState == ContentState.EMPTY)) {
			_contentState = ContentState.HOVER; 
			getElement().getStyle().setCursor(Cursor.POINTER);
		}
		refresh();
	}
	
	@Override
	public void onMouseOut(MouseOutEvent event) {
		if (_contentState == ContentState.HOVER) {
			_contentState = ContentState.EMPTY; 
		}
		getElement().getStyle().setCursor(Cursor.DEFAULT);
		
		refresh(); 
	}

	@Override
	public void onClick(ClickEvent event) {
		if (_enabled) {
			_contentState = ContentState.SELECTED;
			_owner.select(this);
			refresh();
		}
	}
	
	public void setEnabled(boolean enabled) {
		_enabled = enabled;
		refresh();
	}

	private void refresh() {
		boolean visible = (_contentState != ContentState.EMPTY);
		int fontSize = (_contentState == ContentState.SELECTED) ? 400 : 250;
		_piece.setVisible(visible);
		_piece.getElement().getStyle().setFontSize(fontSize, Unit.PCT);
		
		Color darkColor = _enabled ? Color.BLACK : Color.GREY; 
		Color lightColor = _enabled ? Color.WHITE : Color.GREY_LIGHT; 
		boolean black = ((_row + _col) % 2) == 0;  
		Color bgColor = black ? darkColor : lightColor; 
		Color fgColor = black ? lightColor : darkColor; 
		getElement().getStyle().setBackgroundColor(bgColor.toString());
		getElement().getStyle().setColor(fgColor.toString());
	}

	public void reset() {
		_contentState =  ContentState.EMPTY;
		_enabled = true;
		refresh();
	}

	public boolean isEnabled() {
		return _enabled;
	}
}
