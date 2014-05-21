/**
 * Author by metaphy
 * Date Feb 26, 2008
 * All Rights Reserved.
 */
package chess;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class OperationButton extends JButton {
	private static final long serialVersionUID = 1L;

	public enum TheOperation {
		COMPLETE_LINEUP, CALLIN_LINEUP, SAVE_LINEUP, CALLIN_REPEAT
	}

	private TheOperation operation;

	public OperationButton(TheOperation operation) {
		this.operation = operation;
		setOpaque(true);
		setAutoscrolls(false);
		setText(getCaption());

		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.white, Color.white, Color.gray, Color.gray);
		border = BorderFactory.createLineBorder(Color.gray);
		setBorder(border);
		setBounds(getBound());
		setFocusable(false);

		switch (operation) {
			case COMPLETE_LINEUP:
				addActionListener(new OperationCompleteLineup());
				break;
			case CALLIN_LINEUP:
				addActionListener(new OperationCallinLineup());
				break;
			case SAVE_LINEUP:
				addActionListener(new OperationSaveLineup());
				break;
		}
	}

	private String getCaption() {
		String caption = "";
		switch (operation) {
		case COMPLETE_LINEUP:
			caption = "完成调度";
			break;
		case CALLIN_LINEUP:
			caption = "调入布局";
			break;
		case SAVE_LINEUP:
			caption = "保存布局";
			break;
		case CALLIN_REPEAT:
			caption = "调入复盘";
			break;
		}
		return caption;
	}

	private Rectangle getBound() {
		Rectangle rec = null;
		switch (operation) {
		case COMPLETE_LINEUP:
			rec = new Rectangle(Const.BUTTON_START_X, Const.BUTTON_START_Y,
					Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
			break;
		case CALLIN_LINEUP:
			rec = new Rectangle(Const.BUTTON_START_X, Const.BUTTON_START_Y
					+ Const.BUTTON_HEIGHT + Const.BUTTON_GAP_Y,
					Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
			break;
		case SAVE_LINEUP:
			rec = new Rectangle(Const.BUTTON_START_X, Const.BUTTON_START_Y
					+ (Const.BUTTON_HEIGHT + Const.BUTTON_GAP_Y) * 2,
					Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
			break;
		case CALLIN_REPEAT:
			rec = new Rectangle(Const.BUTTON_START_X, Const.BUTTON_START_Y
					+ (Const.BUTTON_HEIGHT + Const.BUTTON_GAP_Y) * 3,
					Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
			break;

		}
		return rec;
	}

}
