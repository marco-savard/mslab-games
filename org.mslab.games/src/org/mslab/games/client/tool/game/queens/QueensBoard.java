package org.mslab.games.client.tool.game.queens;

import org.mslab.games.client.core.ui.StyleUtil;
import org.mslab.games.client.core.ui.panels.GridPanel;
import org.mslab.games.client.tool.game.peg.PegGameTheme;
import org.mslab.games.shared.text.MessageFormat;
import org.mslab.games.shared.types.Color;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;

public class QueensBoard extends GridPanel {
	private QueensMainPanel _owner;
	private int _size; 
	
	QueensBoard(QueensMainPanel owner) {
		_owner = owner;
		_grid.setCellPadding(0);
		_grid.setCellSpacing(0);
		_grid.setWidth("100%");
		
		getElement().getStyle().setBorderWidth(1, Unit.PX);
		getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		String shadow = MessageFormat.format("10px 10px 5px {0}", PegGameTheme.FG_COLOR.toString());
		StyleUtil.setBoxShadow(this, shadow);
	}
	
	public void setNbQueensTotal(int size) {
		_size = size;
		_grid.clear(true);
		
		for (int row = 0; row<size; row++) {
			for (int col = 0; col<size; col++) {
				QueensCell cell = new QueensCell(this, row, col, size); 
				_grid.setWidget(row, col, cell);
			}
		}
	}

	public void select(QueensCell selectedCell) {
		int queensRemaining = _owner.addQueens();
		int selectedRow = selectedCell.getRow(); 
		int selectedCol = selectedCell.getCol(); 
		
		for (int row = 0; row<_size; row++) {
			for (int col = 0; col<_size; col++) {
				boolean sameRow = (row == selectedRow); 
				boolean sameCol = (col == selectedCol); 
				boolean sameDiagonal = 
					(Math.abs(row-selectedRow) == Math.abs(col-selectedCol));
				
				if (sameRow || sameCol || sameDiagonal) {
					QueensCell cell  = (QueensCell)_grid.getWidget(row, col);
					cell.setEnabled(false); 
				}
			}
		} //end for
		
		boolean atLeastOneEnabled = false;
		for (int row = 0; row<_size; row++) {
			for (int col = 0; col<_size; col++) {
				QueensCell cell  = (QueensCell)_grid.getWidget(row, col);
				if (cell.isEnabled()) {
					atLeastOneEnabled = true;
					break;
				}
			}
		}
		
		if (queensRemaining == 0) {
			QueensContext.getInstance().setHighScore(_size);
			
			//_owner.setSuccessStatus(); 
		} else if (! atLeastOneEnabled) {
			_owner.setFailureStatus(); 
		}
	}

	public void reset() {
		for (int row = 0; row<_size; row++) {
			for (int col = 0; col<_size; col++) {
				QueensCell cell  = (QueensCell)_grid.getWidget(row, col);
				cell.reset();
			}
		}
	}

	
}
