package ga;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * 现有数据的最短距离 1926
 * sample = 100;
 * generation = 1000;
 * mutationRate = 0.05
 * 
 * 
 */
public class CityDistance extends JComponent {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CityDistance.class);

	int num = 0;
	String str = "城市路线图";
	static List<City> cityList = new ArrayList<City>();

	static Boolean b3Flag = false;
	static Boolean b5Flag = false;

	static double minDistance = Double.MAX_VALUE;
	static String minRoute = "";

	static Set<String> allArrange = new HashSet<String>();

	// 准备数据 (城市列表， 距离矩阵)
	// 准备数据1：城市列表
	static String[] cityArray = null;
	// 准备数据2：距离矩阵
	static double[][] distanceArray = null;
	// 准备数据3：群种, 遗传次数
	static int sample = 100;
	static int generation = 1000;
	static double mutationRate = 0.05;
	
	static List<Route> routeList = new ArrayList<Route>();
	// 种群的排列
	static List<String> speciesList = new ArrayList<String>();

	static double defaultMaxDistance = Double.MIN_VALUE;
	static double defaultMinDistance = Double.MAX_VALUE;

	static List<Route> selectedRoute = new ArrayList<Route>();
	static List<Route> childrenRoute = new ArrayList<Route>();
	// 保存每一代 最短的距离
	static List<Route> eliteRoute = new ArrayList<Route>();

	static boolean breakout = true;

	static Random random = new Random();

	public static class COBean {
		int count;
		int index;

		public COBean(int count, int index) {
			this.count = count;
			this.index = index;
		}
	}

	
	public static class Route {
		String rang;
		double distance;

		public Route() {
		}

		public Route(String rang, double distance) {
			this.rang = rang;
			this.distance = distance;
		}

	}
	
	static class City {
		String seq;
		Point point;

		public City(String seq, Point point) {
			this.seq = seq;
			this.point = point;
		}
	}
	
	public static void init(){
		
	}
	
	public CityDistance() {
		City city = new City(String.valueOf(num++), new Point(295, 77));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(202, 208));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(217, 326));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(290, 432));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(423, 440));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(445, 345));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(360, 349));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(138, 401));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(99, 301));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(328, 300));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(234, 397));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(304, 202));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(154, 148));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(99, 212));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(152, 276));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(404, 262));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(420, 164));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(503, 209));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(510, 306));
		cityList.add(city);
		city = new City(String.valueOf(num++), new Point(480, 101));
		cityList.add(city);
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				City city = new City(String.valueOf(num++), new Point(e.getX(),
						e.getY()));
				cityList.add(city);
				b3Flag = false;
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		g.setColor(Color.blue);
		for (City c : cityList) {
			int x = (int) (c.point.getX()) - 5;
			int y = (int) (c.point.getY()) - 5;
			g.fillOval(x, y, 10, 10);
			g.drawString(String.valueOf(c.seq), x, y);
		}

		City[] cityArray = new City[cityList.size()];
		cityList.toArray(cityArray);

		// 连线
		if (b3Flag) {

			for (int i = 0; i < cityArray.length - 1; i++) {
				int j = i + 1;
				g.drawLine((int) cityArray[i].point.getX(),
						(int) cityArray[i].point.getY(),
						(int) cityArray[j].point.getX(),
						(int) cityArray[j].point.getY());
			}
			g.drawLine((int) cityArray[cityArray.length - 1].point.getX(),
					(int) cityArray[cityArray.length - 1].point.getY(),
					(int) cityArray[0].point.getX(),
					(int) cityArray[0].point.getY());
		}


		if (b5Flag) {
			b5Flag = false;
			initSepcies();
			calEveryCityDistance();

			for (int i = 1; i < generation+1; i++) {
				log.info("===========第【" + (i) + "】代=============");
				selectedRoute = new ArrayList<Route>();
				
				calFitness(); // 将 routeList 中的值按照一定概率 放到 selectedRoute
				Route route  = calEliteRoute(selectedRoute);  // 取出 selectedRoute 中最短的距离放到 eliteRoute 中
				calCrossOver(route);  // 根据 selectedRoute 生成  childrenRoute, 并保持每代的最优解都会遗传到下一代
				
				// 保证eliteRoute 中的最优解会传到下一代中 
				double minDistance = Double.MAX_VALUE;
				for (Route r : eliteRoute) {
					// 计算最短距离
					if (minDistance > r.distance) {
						minDistance = r.distance;
					}
				}

				for (Route r : eliteRoute) {
					if (r.distance == minDistance) {
						childrenRoute.add(r);
						break;
					}
				}
				
				//变异
				calMutation();
				
				routeList = childrenRoute;   // 将 routeList 设置成 childrenRoute 以便下次轮询
				childrenRoute = new ArrayList<Route>();
				breakout = true;
				
				double sum = 0;
				for(Route v : selectedRoute){
					sum += v.distance;
				}
				
				double tmp = Double.MAX_VALUE;
				for(Route r : routeList){
					if(tmp> r.distance){
						tmp=r.distance;
					}
				}
				log.info(tmp);
				
			}
			
			double minDistance = Double.MAX_VALUE;
			for (Route route : eliteRoute) {
				// 计算最短距离
				if (minDistance > route.distance) {
					minDistance = route.distance;
				}
			}

			for (Route route : eliteRoute) {
				if (route.distance == minDistance) {
					System.out.println("-----最佳路线-----" + route.rang + " : "
							+ route.distance);
					jLabel2.setText(String.valueOf(route.distance));
					g.setColor(Color.GREEN);
					String[] c = route.rang.split("\\|");
					for (int i = 0; i < c.length - 1; i++) {
						g.drawLine((int) cityArray[Integer.valueOf(String
								.valueOf(c[i]))].point.getX(),
								(int) cityArray[Integer.valueOf(String
										.valueOf(c[i]))].point.getY(),
								(int) cityArray[Integer.valueOf(String
										.valueOf(c[i + 1]))].point.getX(),
								(int) cityArray[Integer.valueOf(String
										.valueOf(c[i + 1]))].point.getY());
					}
					g.drawLine((int) cityArray[Integer.valueOf(String
							.valueOf(c[c.length - 1]))].point.getX(),
							(int) cityArray[Integer.valueOf(String
									.valueOf(c[c.length - 1]))].point.getY(),
							(int) cityArray[Integer.valueOf(String
									.valueOf(c[0]))].point.getX(),
							(int) cityArray[Integer.valueOf(String
									.valueOf(c[0]))].point.getY());

					break;
				}
			}

		}
	}

	

	public void clear() {
		clearb6();
		cityList = new ArrayList<City>();
		num = 0;
		b3Flag = false;
		b5Flag = false;
		minDistance = Double.MAX_VALUE;
		minRoute = "";
		allArrange = new HashSet<String>();
		speciesList = new ArrayList<String>();
		
		repaint();
	}

	public void clearb6() {
		b3Flag = false;
		b5Flag = false;
		minDistance = Double.MAX_VALUE;
		minRoute = "";

		childrenRoute = new ArrayList<Route>();
		breakout = true;
		selectedRoute = new ArrayList<Route>();
		routeList = new ArrayList<Route>();
		eliteRoute = new ArrayList<Route>();
		speciesList = new ArrayList<String>();
		
		jLabel2.setText("");
		
		repaint();
	}

	public static JFrame f = new JFrame("计算城市距离");
	public static JButton b2;
	public static JButton b5;
	public static JButton b6;
	public static CityDistance jPanel1;
	public static JPanel jPanel2;
	public static JLabel jLabel1;
	public static JLabel jLabel2;
	public static JPanel jPanel3;
	public static JPanel jPanel4;
	
    private static javax.swing.JLabel jLabel3;
    private static javax.swing.JLabel jLabel4;
    private static javax.swing.JLabel jLabel5;
    private static javax.swing.JLabel jLabel6;
    private static javax.swing.JLabel jLabel7;
    private static javax.swing.JLabel jLabel8;
    private static javax.swing.JTextField jTextField1;
    private static javax.swing.JTextField jTextField2;
    private static javax.swing.JTextField jTextField3;
    
    public static JButton jButton6;
    public static JButton jButton7;
    
    private static void  jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        jLabel6.setText("100");
        jLabel7.setText("1000");
        jLabel8.setText("0.05");
        
    	 sample = 100;
    	 generation = 1000;
    	 mutationRate = 0.05;
        
     }


     private static void  jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        String v1 = jTextField1.getText().trim();
        String v2 = jTextField2.getText().trim();
        String v3 = jTextField3.getText().trim();
        
        System.out.print("["+v1+"]");
        System.out.print("["+v2+"]");
        System.out.print("["+v3+"]");
        
        if(v1!=null && !"".equals(v1)){
        	sample = Integer.valueOf(v1);
        }
        if(v2!=null && !"".equals(v2)){
        	generation = Integer.valueOf(v2);
        }
        if(v3!=null && !"".equals(v3)){
        	mutationRate = Integer.valueOf(v3);
        }
   	 
        jLabel6.setText(String.valueOf(sample));
        jLabel7.setText(String.valueOf(generation));
        jLabel8.setText(String.valueOf(mutationRate));
     }
    
    public static void start(){
    	jPanel1 = new CityDistance();
        jPanel2 = new javax.swing.JPanel();
        b2 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("地图区域"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("控制区域"));

        b2.setText("清空城市");
        b5.setText("【GA】");
        b6.setText("清空连线");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jPanel1.clear();
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b5)
                    .addComponent(b2)
                    .addComponent(b6))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(b2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b6)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("参数控制"));

        jLabel3.setText("种群：");

        jTextField1.setText("              ");

        jLabel4.setText("遗传次数：");

        jLabel5.setText("变异概率：");

        jButton6.setText("确定");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("重置");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setText("100");

        jLabel7.setText("1000");

        jLabel8.setText("0.05");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7)
                    .addComponent(jButton6))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("显示区"));

        jLabel1.setText("最短距离");

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        
        b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPanel1.clear();
			}
		});


		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				distanceArray = new double[cityList.size()][cityList.size()];
				City[] cities = new City[cityList.size()];
				cityList.toArray(cities);

				for (int i = 0; i < cities.length; i++) {
					for (int j = 0; j < cities.length; j++) {
						log.info("城市"
								+ cities[i].seq
								+ " 到 城市"
								+ cities[j].seq
								+ " 的距离为 ： "
								+ cities[i].point
										.distance(cities[j].point));
						distanceArray[i][j] =  cities[i].point
								.distance(cities[j].point);
					}
				}

				cityArray = new String[cities.length];
				for (int i = 0; i < cities.length; i++) {
					cityArray[i] = String.valueOf(cities[i].seq);
				}

				b5Flag = true;
				jPanel1.repaint();
			}
		});

		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPanel1.clearb6();
			}
		});
		
		
        f.pack();
        
		f.setVisible(true);
		f.setLocationRelativeTo(null);

	
    }
    
	public static void main(String[] args) {
		CityDistance.start();
	}

	/**
	 * 根据种群数，初始化整个种群 。
	 * 随机排列， 且不相同
	 * 生成 speciesSet
	 */
	public static void initSepcies() {

		List<String> list = new ArrayList<String>();
		list = Arrays.asList(cityArray);
		for (int i = 0; i < sample; i++) {
			Collections.shuffle(list); // 混乱的意思
			StringBuilder tmp = new StringBuilder();
			for (String v : list) {
				tmp.append(v + "|");
			}
			speciesList.add(tmp.toString());
		}

		/*
		 * for (String v : speciesSet) { System.out.println(v); }
		 */
	}

	public static double calSumByRang(String v) {
		// 根据一个rang来计算一个distance
		String[] c = v.split("\\|");
		double sum = 0;
		for (int i = 0; i < c.length - 1; i++) {
			double tmp = distanceArray[Integer.valueOf(String.valueOf(c[i]))][Integer.valueOf(String.valueOf(c[i + 1]))];
			sum += tmp;
		}
		sum += distanceArray[Integer.valueOf(String.valueOf(c[c.length - 1]))][Integer
				.valueOf(String.valueOf(c[0]))];
		return sum;
	}

	/**
	 * 根据speciesSet distanceArray 来生成每个个体的距离长度
	 */
	public static void calEveryCityDistance() {
		log.debug("---默认种群---");
		for (String v : speciesList) {
			Route route = new Route();
			route.rang = v;

			double sum = calSumByRang(v);

			route.distance = sum;
			routeList.add(route);

			// 计算最长距离，以确定概率
			if (defaultMaxDistance < sum) {
				defaultMaxDistance = sum;
			}

			// 计算最短距离
			if (defaultMinDistance > sum) {
				defaultMinDistance = sum;
			}
			log.debug(route.rang + " : " + route.distance);
		}

		for (Route r : routeList) {
			if (r.distance == defaultMinDistance) {
				eliteRoute.add(r);
			}
		}
	}

	/**
	 * 根据每个个体的距离长度来生成fitness  用最长长度减去最短长度
	 */

	/**
	 * 根据个体距离长度形成概率，准备优秀的父母
	 */
	public static void calFitness() {

		log.debug("-----------calFitness--------------");
		
		// 保证eliteRoute 中的最优解会传到下一代中 
		double minDistance2 = Double.MAX_VALUE;
		double maxDistance2 = Double.MIN_VALUE;
		
		for (Route r : routeList) {
			if (maxDistance2 < r.distance) {
				maxDistance2 = r.distance;
			}
			
			if (minDistance2 > r.distance) {
				minDistance2 = r.distance;
			}
		}
		
		log.debug("aaa: " + maxDistance2 + " : " + minDistance2);
		
		while (breakout) {
			// System.out.println("调用循环第 【" +(++i)+ "】次");
			calRate(maxDistance2, minDistance2);
		}

		/*for (Route r : selectedRoute) {
			System.out.println(r.rang + " : " + r.distance);
		}*/

	}

	public static void calRate(double maxDistance, double minDistance) {
		for (Route r : routeList) {
			double diff = maxDistance - minDistance;
			if((int)diff == 0){
				breakout = false;
				return;
			}
			double value = random.nextInt((int)(diff))
					+ minDistance;

			if (r.distance < value) {
				selectedRoute.add(r);
			}
			if (selectedRoute.size() >= sample) {
				breakout = false;
				return;
			}
		}

	}

	/**
	 * 根据selectedRoute  并调用交叉函数, 先不考虑交叉概率
	 */
	public static void calCrossOver(Route route) {

		// 随机交配
		for (int i = 0; i < selectedRoute.size() / 2; i++) {
			int father = random.nextInt(selectedRoute.size());
			int mother = random.nextInt(selectedRoute.size());
			if (father != mother) {
				crossOver(selectedRoute.get(father).rang,
						selectedRoute.get(mother).rang);
			} else if (father == mother && mother + 1 < selectedRoute.size()) {
				crossOver(selectedRoute.get(father).rang,
						selectedRoute.get(mother + 1).rang);
			} else {
				crossOver(selectedRoute.get(father).rang,
						selectedRoute.get(mother - 1).rang);
			}
		}

		if(route != null){
			childrenRoute.add(route);
		}
		/*System.out.println("-----------calCrossOver------------");
		for (Route r : childrenRoute) {
			System.out.println(r.rang + " : " + r.distance);
		}*/

	}

	public static Route calEliteRoute(List<Route> list) {
		double minDistance = Double.MAX_VALUE;
		for (Route route : list) {

			// 计算最短距离
			if (minDistance > route.distance) {
				minDistance = route.distance;
			}
		}

		for (Route route : list) {
			if (route.distance == minDistance) {
				eliteRoute.add(route);
				return route;
			}
		}
		return null;
	}

	/**
	 * 交叉函数
	 */
	public static void crossOver(String father, String mother) {

		String[] cFather = father.split("\\|");
		String[] cMother = mother.split("\\|");

		// 按照插入顺序排列
		List<COBean> coSet = new ArrayList<COBean>();

		for (int i = 0; i < cFather.length; i++) {
			COBean bean = new COBean(0, 0);
			Set<String> sFather = new HashSet<String>();
			Set<String> sMother = new HashSet<String>();
			if (cFather[i] != cMother[i]) {
				sFather.add(String.valueOf(cFather[i]));
				sMother.add(String.valueOf(cMother[i]));
				bean.count++;
				for (int j = i + 1; j < cFather.length; j++) {
					if (cFather[j] != cMother[j]
							&& !sMother.contains(String.valueOf(cFather[j]))
							&& !sFather.contains(String.valueOf(cMother[j]))) {
						sFather.add(String.valueOf(cFather[j]));
						sMother.add(String.valueOf(cMother[j]));
						bean.count++;
						bean.index = i;
					} else {
						break;
					}
				}
			}
			coSet.add(bean);
		}

		int[] region = new int[] { 0, 0 };
		for (COBean bean : coSet) {
			if (bean.count > region[1]) {
				region[1] = bean.count;
				region[0] = bean.index;
			}
		}

		log.debug("区域交叉的起始点为 ： " + region[0] + " 距离为： " + region[1]);

		// 将对于映射放入Map中
		Map<String, String> fatherMap = new HashMap<String, String>();
		Map<String, String> motherMap = new HashMap<String, String>();
		int k = region[0];
		for (int j = 0; j < region[1]; j++) {
			fatherMap.put(cFather[k], cMother[k]);
			k++;
		}
		k = region[0];
		for (int j = 0; j < region[1]; j++) {
			motherMap.put(cMother[k], cFather[k]);
			k++;
		}

		// 打印【map1】 应该为 8-1，4-7， 3-9 【map2】 应该为 1-8, 7-4, 9-3
		/*
		 * System.out.println("---map1---"); for(String key :
		 * fatherMap.keySet()){ System.out.println(key + "-" +
		 * fatherMap.get(key)); } System.out.println("---map2---"); for(String
		 * key : motherMap.keySet()){ System.out.println(key + "-" +
		 * motherMap.get(key)); }
		 */

		String boy = calChildren(father, mother, region, fatherMap);
		String girl = calChildren(mother, father, region, motherMap);

		//System.out.print(boy + "   ");
		log.debug(girl);

		Route r1 = new Route(boy, calSumByRang(boy));
		Route r2 = new Route(girl, calSumByRang(girl));

		childrenRoute.add(r1);
		childrenRoute.add(r2);

	}

	public static String calChildren(String father, String mother,
			int[] region, Map<String, String> map) {

		String[] tF = father.split("\\|");
		String[] tM = mother.split("\\|");

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb1Pre = new StringBuilder();
		StringBuilder sb1Post = new StringBuilder();

		for (int i = region[0]; i < region[0] + region[1]; i++) {
			sb1.append(tF[i]);
			sb1.append("|");
		}

		for (int i = 0; i < region[0]; i++) {
			sb1Pre.append(tM[i]);
			sb1Pre.append("|");
		}

		for (int i = region[0] + region[1]; i < tF.length; i++) {
			sb1Post.append(tM[i]);
			sb1Post.append("|");
		}

		String t1 = sb1.toString();
		String t1Pre = sb1Pre.toString();
		String t1Post = sb1Post.toString();

		log.debug(t1Pre+t1+t1Post);

		String[] c1Pre = t1Pre.split("\\|");
		for (int i = 0; i < c1Pre.length; i++) {
			if (map.containsKey(String.valueOf(c1Pre[i]))) {
				c1Pre[i] = map.get(String.valueOf(c1Pre[i]));
			}

		}
		t1Pre = "";
		if (!"".equals(c1Pre[0])) {
			for (String v : c1Pre) {
				t1Pre += v;
				t1Pre += "|";
			}
		}

		String[] c1Post = t1Post.split("\\|");
		for (int i = 0; i < c1Post.length; i++) {
			if (map.containsKey(String.valueOf(c1Post[i]))) {
				c1Post[i] = map.get(String.valueOf(c1Post[i]));
			}
		}

		t1Post = "";
		if (!"".equals(c1Post[0])) {
			for (String v : c1Post) {
				t1Post += v;
				t1Post += "|";
			}
		}

		String b1 = t1Pre + t1 + t1Post;

		log.debug(b1);

		return b1;
	}

	/**
	 * 调用变异函数，完善下一代
	 */
	public static void calMutation() {
		double num = mutationRate * sample;
		log.debug((int) num);

		for (int i = 0; i < num; i++) {
			int index = random.nextInt(childrenRoute.size());
			Route route = childrenRoute.remove(index);
			String[] rang1 = route.rang.split("\\|");
			
			int rangIndex1 = random.nextInt(rang1.length);
			int rangIndex2 = random.nextInt(rang1.length);
			
			if(rangIndex1 != rangIndex2){
				String tmp = rang1[rangIndex1];
				rang1[rangIndex1] = rang1[rangIndex2];
				rang1[rangIndex2] = tmp;
			}
			
			String rang2 = "";
			if (!"".equals(rang1[0])) {
				for (String v : rang1) {
					rang2 += v;
					rang2 += "|";
				}
			}
			
			childrenRoute.add(new Route(rang2,calSumByRang(rang2)));
			
		}

	}


}
