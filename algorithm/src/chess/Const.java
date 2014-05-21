package chess;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Const {
	
	public static final String msg1 = "等待其他玩家...";
	public static final String msg2 = "炸弹不能放在第一排";
	public static final String msg3 = "地雷只能放在最后两排";
	public static final String msg4 = "军旗只能放在大本营";
	public static final String msg6 = "布局中";
	public static final String msg7 = "请下棋";
	public static final String msg8 = "该字不能移动！";
	public static final String msg9 = "此路不通";
	//public static final String msg10 = "等待其他玩家下棋...";
	public static final String msg10_1 = "等待玩家【";
	public static final String msg10_2 = "】下棋...";
	public static final String msg11 = "YOU WIN";
	public static final String msg12 = "四国大战";
	public static final String msg13 = "玩家掉线";
	public static final String msg14 = "布局不符合规则";
	public static final String msg15 = "成功加载布局";
	
	public static final String SERVER="S";
	public static final String CLIENT_A="A";
	public static final String CLIENT_B="B";
	public static final String CLIENT_C="C";
	
	
	public static final int SCREEN_WIDTH ;
	public static final int SCREEN_HEIGHT;
	public static final int FRAME_WIDTH ;
	public static final int FRAME_HEIGHT;
	
	static {
		Toolkit tool = Toolkit.getDefaultToolkit() ;
		Dimension screen = tool.getScreenSize() ;
		SCREEN_WIDTH = screen.width;
		SCREEN_HEIGHT = screen.height;
		FRAME_WIDTH = 680;
		FRAME_HEIGHT = 730;
	}
	
	public static final int BORDER_X = 30 ;
	public static final int BORDER_Y = 24 ;
	public static final int ROAD_SIDE_WIDTH = 38;
	public static final int CHESSMAN_SIDE_WIDTH = 33;
	public static final int SP_SIDE_WIDTH = 20;
	
	public static int BUTTON_START_X = 490;
	public static int BUTTON_START_Y = 470;
	public static int BUTTON_GAP_Y = 10;
	public static int BUTTON_WIDTH = 80;
	public static int BUTTON_HEIGHT = 25;
	
	public static final int INIT_TIME_OF_THINKING = 20;

}










