package chess;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	public static String ip ;
	public static String ipPopup;
	
	public static boolean lostA;
	public static boolean lostB;
	public static boolean lostC;

	public static boolean liveS;
	public static boolean liveA;
	public static boolean liveB;
	public static boolean liveC;

	public static LineupBean lineupBean = null;

	public static ArrayBlockingQueue serverQueue1 = new ArrayBlockingQueue(100);
	public static ArrayBlockingQueue serverQueue2 = new ArrayBlockingQueue(100);

	public static int count;
	public static String direction;
	public static String currTurn;
	public static boolean finishBujuSelf;

	//网络对战的socket对象***************************************************
	public static ServerSocket ss1;
	public static ServerSocket ss2;
	public static ServerSocket ss3;

	public static Socket client1;
	public static Socket client2;
	public static Socket client3;

	public static BufferedReader in;
	public static PrintWriter out;
	public static ObjectOutputStream oos1;
	public static ObjectInputStream ois1;

	public static ObjectOutputStream oos2;
	public static ObjectInputStream ois2;

	public static ObjectOutputStream oos3;
	public static ObjectInputStream ois3;

	//线程对象***************************************************************
	private ClientThread1 ct1;
	private ClientThread2 ct2;
	private ClientThread3 ct3;

	MessagePanel mp = new MessagePanel(); //系统消息面板
	//菜单对象**************************************************************
	private JMenuBar mb = new JMenuBar();
	private JMenu game = new JMenu("游戏");
	private JMenuItem serverPart = new JMenuItem("【橙色】主机S");
	private JMenuItem clientPart1 = new JMenuItem("【绿色】客户端A...");
	private JMenuItem clientPart2 = new JMenuItem("【蓝色】客户端B...");
	private JMenuItem clientPart3 = new JMenuItem("【浅蓝】客户端C...");

	public static MainFrame me;
	public static MainPanel panel;
	//1 布局中    2 对战中
	public static int gameState = 1;

	static {
		lostA = false;
		lostB = false;
		lostC = false;
		liveS = true;
		liveA = true;
		liveB = true;
		liveC = true;
		oos1 = null;
		ois1 = null;
		oos2 = null;
		ois2 = null;
		oos3 = null;
		ois3 = null;
		panel = new MainPanel();

		count = 0;
		direction = "";
		currTurn = "";
		finishBujuSelf = false;
		
		
	}

	public MainFrame() {

		initComponent();
		
		Properties defaultSet = new Properties();
		InputStream in = getClass().getResourceAsStream("/resources/military.properties");
		try {
			defaultSet.load(in);
		} catch (IOException e) {
			return;
		}
		ipPopup = defaultSet.getProperty("ipPopup");
		ip = defaultSet.getProperty("ip");
	}

	public void initComponent() {
		menuEvent();
		menuInit();

		me = this;

		setTitle("Military war game chess");
		setLocation((Const.SCREEN_WIDTH - Const.FRAME_WIDTH) / 2, (Const.SCREEN_HEIGHT - Const.FRAME_HEIGHT) / 2);
		setSize(Const.FRAME_WIDTH, Const.FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);

		//		timer1 = new TimerThread();
		//		timer1.setVisible(true);
		//		panel.add(timer1);
		//		Thread t = new Thread(timer1);
		//		t.start();

		Container conPane = this.getContentPane();
		conPane.setLayout(new BorderLayout());
		conPane.add(mp, BorderLayout.NORTH);
		conPane.add(panel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void setButtonDisabled(){
		serverPart.setEnabled(false);
		clientPart1.setEnabled(false);
		clientPart2.setEnabled(false);
		clientPart3.setEnabled(false);
	}
	
	private void menuEvent() {
		game.addSeparator();
		game.add(serverPart);
		game.add(clientPart3);
		game.add(clientPart1);
		game.add(clientPart2);

		mb.add(game);
		this.setJMenuBar(mb);

		//"主机"菜单
		serverPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direction = Const.SERVER;
				currTurn = direction;
				ServerThread1 st = new ServerThread1();
				st.start();

				ServerThread2 st2 = new ServerThread2();
				st2.start();

				ServerThread3 st3 = new ServerThread3();
				st3.start();

				mp.setMessage(Const.msg1);

				ServerForwardThread sft = new ServerForwardThread();
				sft.start();

				getPanel().initFlag = true;
				getPanel().repaint();

				setButtonDisabled();
			}
		});

		//"客户端"菜单
		clientPart1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direction = Const.CLIENT_A;
				ct1 = new ClientThread1();
				ct1.start();

				getPanel().initFlag = true;
				getPanel().repaint();

				setButtonDisabled();
			}
		});

		//"客户端"菜单
		clientPart2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direction = Const.CLIENT_B;
				ct2 = new ClientThread2();
				ct2.start();

				getPanel().initFlag = true;
				getPanel().repaint();
				
				setButtonDisabled();

			}
		});

		//"客户端"菜单
		clientPart3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direction = Const.CLIENT_C;
				ct3 = new ClientThread3();
				ct3.start();

				getPanel().initFlag = true;
				getPanel().repaint();

				setButtonDisabled();
			}
		});

	}

	//等待客户端连接的线程
	class ServerThread1 extends Thread {
		public void run() {
			try {
				ss1 = new ServerSocket(10001);
				client1 = ss1.accept();

				serverQueue1.put(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//等待客户端连接的线程
	class ServerThread2 extends Thread {
		public void run() {
			try {
				ss2 = new ServerSocket(10002);
				client2 = ss2.accept();

				serverQueue1.put(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//等待客户端连接的线程
	class ServerThread3 extends Thread {
		public void run() {
			try {
				ss3 = new ServerSocket(10003);
				client3 = ss3.accept();

				serverQueue1.put(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class ServerForwardThread extends Thread {

		public void run() {

			try {
				serverQueue1.take();
				serverQueue1.take();
				serverQueue1.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			mp.setMessage(Const.msg6);

			ServerForwardThread1 t1 = new ServerForwardThread1();
			t1.start();

			ServerForwardThread2 t2 = new ServerForwardThread2();
			t2.start();

			ServerForwardThread3 t3 = new ServerForwardThread3();
			t3.start();

			try {
				serverQueue2.take();
				serverQueue2.take();
				serverQueue2.take();
				serverQueue2.take();

				mp.setMessage(Const.msg7);
				Utils.writeObject(Const.CLIENT_A, Const.msg10_1 + Const.SERVER + Const.msg10_2);
				Utils.writeObject(Const.CLIENT_B, Const.msg10_1 + Const.SERVER + Const.msg10_2);
				Utils.writeObject(Const.CLIENT_C, Const.msg10_1 + Const.SERVER + Const.msg10_2);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class ServerForwardThread1 extends Thread {

		public void run() {
			try {
				getPanel().getButtonCompleteLineup().setVisible(true);
				oos1 = new ObjectOutputStream(client1.getOutputStream());
				ois1 = new ObjectInputStream(client1.getInputStream());

				Utils.writeObject(Const.CLIENT_A, Const.msg6);

				while (true) {
					readServer1();
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 玩家掉线   1，去掉该玩家   2， 发消息给其他两个玩家  3， currTurn跳过该玩家  4，给其他人提示信息
				LostPlayer p = new LostPlayer();
				p.setSide(Const.CLIENT_A);
				panel.lostPlayer = p;
				panel.five = true;
				panel.repaint();

				lostA = true;
				liveA = false;
				currTurn = lostPlayerCurrTurn(Const.CLIENT_A);
				String msg = Const.msg13 + Const.CLIENT_A + "|" + " 轮到玩家  " + currTurn;
				p.setMsg(msg);
				p.setCurrTurn(currTurn);
				mp.setMessage(msg);

				try {
					Utils.writeObject(Const.CLIENT_B, p);
					Utils.writeObject(Const.CLIENT_C, p);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

	class ServerForwardThread2 extends Thread {

		public void run() {
			try {
				getPanel().getButtonCompleteLineup().setVisible(true);
				oos2 = new ObjectOutputStream(client2.getOutputStream());
				ois2 = new ObjectInputStream(client2.getInputStream());

				Utils.writeObject(Const.CLIENT_B, Const.msg6);

				while (true) {
					readServer2();
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 玩家掉线   1，去掉该玩家   2， 发消息给其他两个玩家  3， currTurn跳过该玩家  4，给其他人提示信息
				LostPlayer p = new LostPlayer();
				p.setSide(Const.CLIENT_B);
				panel.lostPlayer = p;
				panel.five = true;
				panel.repaint();

				lostB = true;
				liveB = false;
				currTurn = lostPlayerCurrTurn(Const.CLIENT_B);
				String msg = Const.msg13 + Const.CLIENT_B + "|" + "轮到玩家 " + currTurn;
				p.setMsg(msg);
				p.setCurrTurn(currTurn);
				mp.setMessage(msg);

				try {
					Utils.writeObject(Const.CLIENT_A, p);
					Utils.writeObject(Const.CLIENT_C, p);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	}

	class ServerForwardThread3 extends Thread {

		public void run() {
			try {
				getPanel().getButtonCompleteLineup().setVisible(true);
				oos3 = new ObjectOutputStream(client3.getOutputStream());
				ois3 = new ObjectInputStream(client3.getInputStream());

				Utils.writeObject(Const.CLIENT_C, Const.msg6);

				while (true) {
					readServer3();
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 玩家掉线   1，去掉该玩家   2， 发消息给其他两个玩家  3， currTurn跳过该玩家  4，给其他人提示信息
				LostPlayer p = new LostPlayer();
				p.setSide(Const.CLIENT_C);
				panel.lostPlayer = p;
				panel.five = true;
				panel.repaint();

				lostC = true;
				liveC = false;
				currTurn = lostPlayerCurrTurn(Const.CLIENT_C);
				String msg = Const.msg13 + Const.CLIENT_C + "|" + "轮到玩家 " + currTurn;
				p.setMsg(msg);
				p.setCurrTurn(currTurn);
				mp.setMessage(msg);

				try {
					Utils.writeObject(Const.CLIENT_A, p);
					Utils.writeObject(Const.CLIENT_B, p);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}

	private String lostPlayerCurrTurn(String lostSide) {
		if (currTurn.equals(lostSide)) {
			return Utils.changeLetter(currTurn);
		} else {
			return currTurn;
		}
	}

	private void readServer1() throws Exception {
		Object obj = ois1.readObject();
		if (obj instanceof LineupBean) {
			lineupBean = (LineupBean) obj;

			// 想 CLIENT_B 发送位置
			LineupBean sendBean = (LineupBean) Utils.clone(lineupBean);
			Map<String, ChessmanFigure> mapDeep = sendBean.getMap();
			for (String key : mapDeep.keySet()) {
				mapDeep.get(key).getChessman()
						.setCoordinate(Coordinate.steer90(mapDeep.get(key).getChessman().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_B, sendBean);

			// 想 CLIENT_C 发送位置
			LineupBean sendBean2 = (LineupBean) Utils.clone(lineupBean);
			Map<String, ChessmanFigure> mapDeep2 = sendBean2.getMap();
			for (String key : mapDeep2.keySet()) {
				mapDeep2.get(key).getChessman()
						.setCoordinate(Coordinate.steerMinus90(mapDeep2.get(key).getChessman().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_C, sendBean2);

			MainPanel.opponentFlag = true;
			panel.repaint();
			serverQueue2.put(1);

		} else if (obj instanceof TransBeanB) {
			panel.transBeanB = (TransBeanB) obj;

			// 想 CLIENT_B 发送位置
			TransBeanB sendBean = (TransBeanB) Utils.clone(panel.transBeanB);
			sendBean.getFirst().getChessman()
					.setCoordinate(Coordinate.steer90(sendBean.getFirst().getChessman().getCoordinate()));
			if (sendBean.getSecond() instanceof ChessmanFigure) {
				ChessmanFigure cf = (ChessmanFigure) sendBean.getSecond();
				cf.getChessman().setCoordinate(Coordinate.steer90(cf.getChessman().getCoordinate()));
			} else if (sendBean.getSecond() instanceof StationFigure) {
				StationFigure sf = (StationFigure) sendBean.getSecond();
				sf.getSp().setCoordinate(Coordinate.steer90(sf.getSp().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_B, sendBean);

			// 想 CLIENT_C 发送位置
			TransBeanB sendBean2 = (TransBeanB) Utils.clone(panel.transBeanB);
			sendBean2.getFirst().getChessman()
					.setCoordinate(Coordinate.steerMinus90(sendBean2.getFirst().getChessman().getCoordinate()));
			if (sendBean2.getSecond() instanceof ChessmanFigure) {
				ChessmanFigure cf = (ChessmanFigure) sendBean2.getSecond();
				cf.getChessman().setCoordinate(Coordinate.steerMinus90(cf.getChessman().getCoordinate()));
			} else if (sendBean2.getSecond() instanceof StationFigure) {
				StationFigure sf = (StationFigure) sendBean2.getSecond();
				sf.getSp().setCoordinate(Coordinate.steerMinus90(sf.getSp().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_C, sendBean2);

			panel.four = true;
			panel.repaint();

			sendCurrTurn();
		}

	}

	private void sendCurrTurn() throws IOException {
		checkLive();
		currTurn = Utils.changeLetter(panel.transBeanB.getSide());

		CurrTurn ct = new CurrTurn(currTurn);

		Utils.writeObject(Const.CLIENT_A, ct);
		Utils.writeObject(Const.CLIENT_B, ct);
		Utils.writeObject(Const.CLIENT_C, ct);

		if (direction.equals(currTurn)) {
			mp.setMessage(Const.msg7);
		} else {
			mp.setMessage(Const.msg10_1 + currTurn + Const.msg10_2);
		}

	}

	private void readServer2() throws Exception {
		Object obj = ois2.readObject();
		if (obj instanceof LineupBean) {
			lineupBean = (LineupBean) obj;

			// 想 CLIENT_A 发送位置
			LineupBean sendBean = (LineupBean) Utils.clone(lineupBean);
			Map<String, ChessmanFigure> mapDeep = sendBean.getMap();
			for (String key : mapDeep.keySet()) {
				mapDeep.get(key).getChessman()
						.setCoordinate(Coordinate.symmetry(mapDeep.get(key).getChessman().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_A, sendBean);

			// 想 CLIENT_C 发送位置
			LineupBean sendBean2 = (LineupBean) Utils.clone(lineupBean);
			Map<String, ChessmanFigure> mapDeep2 = sendBean2.getMap();
			for (String key : mapDeep2.keySet()) {
				mapDeep2.get(key).getChessman()
						.setCoordinate(Coordinate.steerMinus90(mapDeep2.get(key).getChessman().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_C, sendBean2);

			MainPanel.opponentFlag = true;
			panel.repaint();
			serverQueue2.put(1);
		} else if (obj instanceof TransBeanB) {
			panel.transBeanB = (TransBeanB) obj;
			checkLive();

			// 想 CLIENT_A 发送位置
			TransBeanB sendBean = (TransBeanB) Utils.clone(panel.transBeanB);
			sendBean.getFirst().getChessman()
					.setCoordinate(Coordinate.symmetry(sendBean.getFirst().getChessman().getCoordinate()));
			if (sendBean.getSecond() instanceof ChessmanFigure) {
				ChessmanFigure cf = (ChessmanFigure) sendBean.getSecond();
				cf.getChessman().setCoordinate(Coordinate.symmetry(cf.getChessman().getCoordinate()));
			} else if (sendBean.getSecond() instanceof StationFigure) {
				StationFigure sf = (StationFigure) sendBean.getSecond();
				sf.getSp().setCoordinate(Coordinate.symmetry(sf.getSp().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_A, sendBean);

			// 想 CLIENT_C 发送位置
			TransBeanB sendBean2 = (TransBeanB) Utils.clone(panel.transBeanB);
			sendBean2.getFirst().getChessman()
					.setCoordinate(Coordinate.steerMinus90(sendBean2.getFirst().getChessman().getCoordinate()));
			if (sendBean2.getSecond() instanceof ChessmanFigure) {
				ChessmanFigure cf = (ChessmanFigure) sendBean2.getSecond();
				cf.getChessman().setCoordinate(Coordinate.steerMinus90(cf.getChessman().getCoordinate()));
			} else if (sendBean2.getSecond() instanceof StationFigure) {
				StationFigure sf = (StationFigure) sendBean2.getSecond();
				sf.getSp().setCoordinate(Coordinate.steerMinus90(sf.getSp().getCoordinate()));
			}

			Utils.writeObject(Const.CLIENT_C, sendBean2);

			panel.four = true;
			panel.repaint();

			sendCurrTurn();

		}

	}

	private void readServer3() throws Exception {
		Object obj = ois3.readObject();
		if (obj instanceof LineupBean) {
			lineupBean = (LineupBean) obj;

			// 想 CLIENT_A 发送位置
			LineupBean sendBean = (LineupBean) Utils.clone(lineupBean);
			Map<String, ChessmanFigure> mapDeep = sendBean.getMap();
			for (String key : mapDeep.keySet()) {
				mapDeep.get(key).getChessman()
						.setCoordinate(Coordinate.symmetry(mapDeep.get(key).getChessman().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_A, sendBean);

			// 想 CLIENT_B 发送位置
			LineupBean sendBean2 = (LineupBean) Utils.clone(lineupBean);
			Map<String, ChessmanFigure> mapDeep2 = sendBean2.getMap();
			for (String key : mapDeep2.keySet()) {
				mapDeep2.get(key).getChessman()
						.setCoordinate(Coordinate.steer90(mapDeep2.get(key).getChessman().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_B, sendBean2);

			MainPanel.opponentFlag = true;
			panel.repaint();
			serverQueue2.put(1);
		} else if (obj instanceof TransBeanB) {
			panel.transBeanB = (TransBeanB) obj;
			checkLive();

			// 想 CLIENT_A 发送位置
			TransBeanB sendBean = (TransBeanB) Utils.clone(panel.transBeanB);
			sendBean.getFirst().getChessman()
					.setCoordinate(Coordinate.symmetry(sendBean.getFirst().getChessman().getCoordinate()));
			if (sendBean.getSecond() instanceof ChessmanFigure) {
				ChessmanFigure cf = (ChessmanFigure) sendBean.getSecond();
				cf.getChessman().setCoordinate(Coordinate.symmetry(cf.getChessman().getCoordinate()));
			} else if (sendBean.getSecond() instanceof StationFigure) {
				StationFigure sf = (StationFigure) sendBean.getSecond();
				sf.getSp().setCoordinate(Coordinate.symmetry(sf.getSp().getCoordinate()));
			}
			Utils.writeObject(Const.CLIENT_A, sendBean);

			// 想 CLIENT_B 发送位置
			TransBeanB sendBean2 = (TransBeanB) Utils.clone(panel.transBeanB);
			sendBean2.getFirst().getChessman()
					.setCoordinate(Coordinate.steer90(sendBean2.getFirst().getChessman().getCoordinate()));
			if (sendBean2.getSecond() instanceof ChessmanFigure) {
				ChessmanFigure cf = (ChessmanFigure) sendBean2.getSecond();
				cf.getChessman().setCoordinate(Coordinate.steer90(cf.getChessman().getCoordinate()));
			} else if (sendBean2.getSecond() instanceof StationFigure) {
				StationFigure sf = (StationFigure) sendBean2.getSecond();
				sf.getSp().setCoordinate(Coordinate.steer90(sf.getSp().getCoordinate()));
			}

			Utils.writeObject(Const.CLIENT_B, sendBean2);

			panel.four = true;
			panel.repaint();

			sendCurrTurn();
		}
	}

	//等待客户端连接的线程
	class ClientThread1 extends Thread {
		public void run() {
			try {
				
				if("true".equals(ipPopup)){
					 ip = JOptionPane.showInputDialog(MainFrame.this,"请输入主机IP地址或主机名称:", ip);
				}
				
				Socket server = new Socket(ip, 10001);

				mp.setMessage(Const.msg1);

				oos1 = new ObjectOutputStream(server.getOutputStream());
				ois1 = new ObjectInputStream(server.getInputStream());

				while (true) {
					readInfo(ois1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//等待客户端连接的线程
	class ClientThread2 extends Thread {
		public void run() {
			try {
				if("true".equals(ipPopup)){
					 ip = JOptionPane.showInputDialog(MainFrame.this,"请输入主机IP地址或主机名称:", ip);
				}
				
				Socket server = new Socket(ip, 10002);
				mp.setMessage(Const.msg1);

				oos2 = new ObjectOutputStream(server.getOutputStream());
				ois2 = new ObjectInputStream(server.getInputStream());

				while (true) {
					readInfo(ois2);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class ClientThread3 extends Thread {
		public void run() {
			try {
				if("true".equals(ipPopup)){
					 ip = JOptionPane.showInputDialog(MainFrame.this,"请输入主机IP地址或主机名称:", ip);
				}
				
				Socket server = new Socket(ip, 10003);
				mp.setMessage(Const.msg1);

				oos3 = new ObjectOutputStream(server.getOutputStream());
				ois3 = new ObjectInputStream(server.getInputStream());

				while (true) {
					readInfo(ois3);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void readInfo(ObjectInputStream ois) throws Exception {
		Object obj = ois.readObject();
		if (obj instanceof LineupBean) {
			lineupBean = (LineupBean) obj;
			MainPanel.opponentFlag = true;
			panel.repaint();
		} else if (obj instanceof TransBeanB) {
			panel.transBeanB = (TransBeanB) obj;
			panel.four = true;
			panel.repaint();
		} else if (obj instanceof CurrTurn) {
			currTurn = ((CurrTurn) obj).getCurrTurn();
			if (direction.equals(currTurn)) {
				mp.setMessage(Const.msg7);
			} else {
				mp.setMessage(Const.msg10_1 + currTurn + Const.msg10_2);
			}
		} else if (obj instanceof String) {
			if (((String) obj).equals(Const.msg6)) {
				getPanel().getButtonCompleteLineup().setVisible(true);
			}
			mp.setMessage((String) obj);
		} else if (obj instanceof LostPlayer) {
			panel.lostPlayer = (LostPlayer) obj;
			currTurn = panel.lostPlayer.getCurrTurn();
			mp.setMessage(panel.lostPlayer.getMsg());

			panel.five = true;
			panel.repaint();
		}

	}

	public void checkLive() {
		if (!panel.transBeanB.isLive()) {
			if (panel.transBeanB.getOpponent().equals(Const.CLIENT_A)) {
				liveA = false;
			} else if (panel.transBeanB.getOpponent().equals(Const.CLIENT_B)) {
				liveB = false;
			} else if (panel.transBeanB.getOpponent().equals(Const.CLIENT_C)) {
				liveC = false;
			} else if (panel.transBeanB.getOpponent().equals(Const.SERVER)) {
				liveS = false;
			}
		}
	}

	private void menuInit() {
		serverPart.setEnabled(true);
		clientPart1.setEnabled(true);
		clientPart2.setEnabled(true);
		clientPart3.setEnabled(true);
	}

	public MainPanel getPanel() {
		return panel;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
}
