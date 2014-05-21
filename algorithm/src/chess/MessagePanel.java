package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
* 消息面板***********************
* 位于主窗体顶部,专门用于显示各种系统消息
*/
public class MessagePanel extends JPanel {
	private JLabel message = new JLabel(); //显示消息的标签

	public MessagePanel() {
		setBackground(Color.BLACK);
		message.setFont(new Font("宋体", Font.BOLD, 15));
		message.setForeground(Color.BLACK); //初始化时设置消息标签的颜色为黑色(这样就看不见了)
		add(message);
		setMessage(Const.msg12);
	}


	/********************************************
	函数功能:改变消息标签message的内容并进行淡出效果显示
	输入参数:消息标签的新内容
	输出参数:无
	*******************************************/
	public void setMessage(String newMessage) {
		message.setForeground(Color.YELLOW);
		message.setText(newMessage);
	}
}



