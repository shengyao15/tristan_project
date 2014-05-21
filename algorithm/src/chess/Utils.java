package chess;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.Chessman.Soldier;


public class Utils {
	private static MainFrame frame = MainFrame.me;

	public static void writeObject(String side, Object obj) throws IOException{
		if(Const.CLIENT_A.equals(side)){
			if(!MainFrame.lostA){
				MainFrame.oos1.writeObject(obj);
			}
		}else if(Const.CLIENT_B.equals(side)){
			if(!MainFrame.lostB){
				MainFrame.oos2.writeObject(obj);
			}
		}else if(Const.CLIENT_C.equals(side)){
			if(!MainFrame.lostC){
				MainFrame.oos3.writeObject(obj);
			}
		}
	}
	
	public static void showJunQi(ChessmanFigure figure) {
		if (figure.getChessman().getSoldier() == Soldier.SILING) {
			String s = "";
			if (figure.getKey().contains(Const.SERVER)) {
				s = Const.SERVER;
			} else if (figure.getKey().contains(Const.CLIENT_A)) {
				s = Const.CLIENT_A;
			} else if (figure.getKey().contains(Const.CLIENT_B)) {
				s = Const.CLIENT_B;
			} else if (figure.getKey().contains(Const.CLIENT_C)) {
				s = Const.CLIENT_C;
			}

			for (String key : MainPanel.figureFinalMap.keySet()) {
				ChessmanFigure cf = MainPanel.figureFinalMap.get(key);
				if (cf.getChessman().getSoldier() == Soldier.JUNQI && key.contains(s)) {
					cf.setText(cf.getChessman().getCaption());
				}
			}
		}
	}

	synchronized
	public static void removePlayer(ChessmanFigure figure) throws Exception {
		if (figure.getChessman().getSoldier() == Soldier.JUNQI) {
			String s = "";
			if (figure.getKey().contains(Const.SERVER)) {
				s = Const.SERVER;
			} else if (figure.getKey().contains(Const.CLIENT_A)) {
				s = Const.CLIENT_A;
			} else if (figure.getKey().contains(Const.CLIENT_B)) {
				s = Const.CLIENT_B;
			} else if (figure.getKey().contains(Const.CLIENT_C)) {
				s = Const.CLIENT_C;
			}
			
			List<String> tmpList = new ArrayList<String>();
			for (String key : MainPanel.figureFinalMap.keySet()) {
				ChessmanFigure cf = MainPanel.figureFinalMap.get(key);
				if (key.contains(s)) {
					tmpList.add(key);
					MainPanel.mp.remove(cf);
				}
			}
			
			for(String key: tmpList){
				MainPanel.figureFinalMap.remove(key);
			}
		}
	}

	public static Object clone(Object obj) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream outStream = new ObjectOutputStream(byteOut);
		outStream.writeObject(obj);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream inStream = new ObjectInputStream(byteIn);
		return inStream.readObject();
	}

	public static String changeLetter(String s) {
		if (Const.SERVER.equals(s)) {
			if (MainFrame.liveC) {
				return Const.CLIENT_C;
			} else if (MainFrame.liveA) {
				return Const.CLIENT_A;
			} else if (MainFrame.liveB) {
				return Const.CLIENT_B;
			}
		} else if (Const.CLIENT_C.equals(s)) {
			if (MainFrame.liveA) {
				return Const.CLIENT_A;
			} else if (MainFrame.liveB) {
				return Const.CLIENT_B;
			} else if (MainFrame.liveS) {
				return Const.SERVER;
			}
		} else if (Const.CLIENT_A.equals(s)) {
			if (MainFrame.liveB) {
				return Const.CLIENT_B;
			} else if (MainFrame.liveS) {
				return Const.SERVER;
			} else if (MainFrame.liveC) {
				return Const.CLIENT_C;
			}
		} else if (Const.CLIENT_B.equals(s)) {
			if (MainFrame.liveS) {
				return Const.SERVER;
			} else if (MainFrame.liveC) {
				return Const.CLIENT_C;
			} else if (MainFrame.liveA) {
				return Const.CLIENT_A;
			}
		}
		return "";
	}

	public static void sendBeanClientA(TransBeanB bean, boolean finish) {
		try {
			if (finish) {
				bean.setLive(false);
			}
			Utils.writeObject(Const.CLIENT_A, bean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkLive(bean);
		frame.currTurn = Utils.changeLetter(frame.currTurn);
	}

	public static void sendBeanServer(TransBeanB bean1, TransBeanB bean2, TransBeanB bean3) {
		try {
			Utils.writeObject(Const.CLIENT_A, bean1);
			Utils.writeObject(Const.CLIENT_B, bean2);
			Utils.writeObject(Const.CLIENT_C, bean3);

			frame.currTurn = Utils.changeLetter(frame.currTurn);
			
			CurrTurn ct = new CurrTurn(frame.currTurn);
			Utils.writeObject(Const.CLIENT_A, ct);
			Utils.writeObject(Const.CLIENT_B, ct);
			Utils.writeObject(Const.CLIENT_C, ct);

			if(MainFrame.direction.equals(frame.currTurn)){
				MainFrame.me.mp.setMessage(Const.msg7);
			}else{
				MainFrame.me.mp.setMessage(Const.msg10_1 + frame.currTurn +Const.msg10_2);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void checkLive(TransBeanB transBeanB) {
		if (!transBeanB.isLive()) {
			if (transBeanB.getOpponent().equals(Const.CLIENT_A)) {
				MainFrame.liveA = false;
			} else if (transBeanB.getOpponent().equals(Const.CLIENT_B)) {
				MainFrame.liveB = false;
			} else if (transBeanB.getOpponent().equals(Const.CLIENT_C)) {
				MainFrame.liveC = false;
			} else if (transBeanB.getOpponent().equals(Const.SERVER)) {
				MainFrame.liveS = false;
			}
		}
	}

	public static void sendBeanClientB(TransBeanB bean, boolean finish) {
		try {
			if (finish) {
				bean.setLive(false);
			}
			Utils.writeObject(Const.CLIENT_B, bean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.mp.setMessage(Const.msg10_1 + Utils.changeLetter(frame.currTurn) + Const.msg10_2);

	}

	public static void sendBeanClientC(TransBeanB bean, boolean finish) {
		try {
			if (finish) {
				bean.setLive(false);
			}
			Utils.writeObject(Const.CLIENT_C, bean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.mp.setMessage(Const.msg10_1 + Utils.changeLetter(frame.currTurn) + Const.msg10_2);

	}
}
