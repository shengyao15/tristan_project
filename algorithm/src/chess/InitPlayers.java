/**
 * Author by metaphy
 * Date Dec 24, 2007
 * All Rights Reserved.
 */
package chess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import chess.Chessman;
import chess.Chessman.Soldier;
import chess.Player.Located;


public class InitPlayers {

	private static int id = 0;

	/**
	 * Init a player
	 * @throws Exception 
	 */
	public static Player initOnePlayer(Located loc, File file) throws Exception {
		Player player = new Player();
		player.setLocated(loc);

		BufferedReader br = null;
		if (file == null) {
			br = new BufferedReader(new InputStreamReader(GameResources.getSampleLineup().openStream()));
		} else {
			br = new BufferedReader(new FileReader(file));
		}

		String line = null;
		int lineNum = 0;
		int coor = 0;
		while ((line = br.readLine()) != null) {
			lineNum++;
			String[] arr = line.split(",");
			for (int i = 0; i < arr.length; i++) {
				String title = new String(arr[i].getBytes(), "UTF-8");
				Chessman chm = new Chessman();
				Soldier soldier = Chessman.getSoldier(title);
				chm.setId(id++);
				chm.setSoldier(soldier);
				chm.setVisible(true);
				chm.setDisplay(true);

				if (loc == Located.SOUTH) {
					chm.setDisplay(true);
					switch (lineNum) {
					case 1:
						coor = 1207 + i;
						break;
					case 2:
						coor = 1307 + i * 2;
						break;
					case 3:
						coor = 1407 + i;
						if (i >= 2)
							coor++;
						break;
					case 4:
						coor = 1507 + i * 2;
						break;
					case 5:
						coor = 1607 + i;
						break;
					case 6:
						coor = 1707 + i;
						break;
					}
				}

				chm.setCoordinate(new Coordinate(coor));
				player.addChessman(chm);
			}

		}

		//验证布局是否合法  大小25, 各个子的数量， 位置
		List<Chessman> clist = player.getChessmans();
		if(clist.size() != 25){
			throw new LineupException();
		}
		
		int i40 = 0;
		int i39 = 0;
		int i38 = 0;  //师长
		int i37 = 0;  //旅
		int i36 = 0;  //团
		int i35 = 0;  //营
		int i34 = 0;  //连
		int i33 = 0;  //排
		int i30 = 0;  // 工兵
		int i20 = 0;  // 地雷
		int i10 = 0;  // 炸弹 
		int i00 = 0;  // 军旗
		
		for(Chessman c : clist){
			if(c.getSoldier() == Soldier.SILING){
				i40++;
			}else if(c.getSoldier() == Soldier.JUNZHANG){
				i39++;
			}else if(c.getSoldier() == Soldier.SHIZHANG){
				i38++;
			}else if(c.getSoldier() == Soldier.LVZHANG){
				i37++;
			}else if(c.getSoldier() == Soldier.TUANZHANG){
				i36++;
			}else if(c.getSoldier() == Soldier.YINGZHANG){
				i35++;
			}else if(c.getSoldier() == Soldier.LIANZHANG){
				i34++;
			}else if(c.getSoldier() == Soldier.PAIZHANG){
				i33++;
			}else if(c.getSoldier() == Soldier.GONGBING){
				i30++;
			}else if(c.getSoldier() == Soldier.DILEI){
				i20++;
			}else if(c.getSoldier() == Soldier.ZHADAN){
				i10++;
			}else if(c.getSoldier() == Soldier.JUNQI){
				i00++;
			}
		}
		if(i40 != 1 && i39 != 1 && i38 != 2 && i37 != 2 && i36 != 2 && i36 != 2 && i35 != 2 
				&& i34 != 3 && i33 != 3 && i30 != 3 && i20 != 3 && i10 != 2 && i00 != 1){
			throw new LineupException();
		}
		
		for(Chessman c : clist){
			if(c.getSoldier() == Soldier.ZHADAN){
				if(c.getCoordinate().getY() == 12 ){
					throw new LineupException();
				}
			}else if(c.getSoldier() == Soldier.DILEI){
				if(c.getCoordinate().getY() != 16 &&  c.getCoordinate().getY() != 17){
					throw new LineupException();
				}
			}else if(c.getSoldier() == Soldier.JUNQI){
				if(c.getCoordinate().getCoordinateValue() != 1708 && c.getCoordinate().getCoordinateValue() != 1710){
					throw new LineupException();
				}
			}
		}
		
		return player;
	}

	public static ArrayList<Player> init(int count) throws Exception {
		if (count != 2 && count != 4)
			return null;

		ArrayList<Player> players = new ArrayList<Player>();
		Player player = initOnePlayer(Located.SOUTH, null);
		players.add(player);
		player = initOnePlayer(Located.NORTH, null);
		players.add(player);

		if (count == 4) {
			player = initOnePlayer(Located.EAST, null);
			players.add(player);
			player = initOnePlayer(Located.WEST, null);
			players.add(player);
		}
		return players;
	}
}
