package path;


import java.util.ArrayList;

public class Coordinate {
	
	
	public boolean isWater(){
		Coordinate c = new Coordinate(value);
		return AStarSample.water.contains(c);
	}
	
	private int x;
	private int y;
	private int value;
	private Coordinate parent;
	
	public Coordinate(){}
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		this.value = 100 * y + x;
	}
	public Coordinate(int value) {
		this.x = value % 100;
		this.y = value / 100;
		this.value = value;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getValue() {
		return value;
	}
	
	public Coordinate getParent() {
		return parent;
	}
	public void setParent(Coordinate parent) {
		this.parent = parent;
	}
	/**
	 * Whether or not it's border
	 * @return
	 */
	public boolean isBorder(){
		return x == 0 || x == AStarSample.MAP_COLUMN+1 || y ==0 || y == AStarSample.MAP_ROW+1 ;
	}
	
	/**
	 * @param xx
	 * @param yy
	 * @return
	 */
	public boolean valid(int xx, int yy){
		return xx >=1 && xx<=AStarSample.MAP_COLUMN+1 && yy >=1 && yy<=AStarSample.MAP_ROW+1;
	}
	
	/**
	 * Get valid adjacent coordinate list
	 * @return
	 */
	public ArrayList<Coordinate> getValidAdjacent(){
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		for (int t = -1; t<= 1; t ++){
			for (int p = -1; p<= 1; p++){
				if (valid(x + t,y + p)){
					Coordinate c = new Coordinate (x+t, y+p);
					if (!c.isBorder() && !c.isWater()){
						list.add(c);
					}
				}
			}
		}
		return list;
	}
	/**
	 * The G function 
	 * @param c
	 * @return
	 */
	public int getCostG(){
		if (parent != null){
			if (Math.abs(parent.getX () -x)==1 && Math.abs(parent.getY() - y )==1){
				return 14;
			}else {
				return 10;
			}
		}else {
			return 0 ;
		}
	}
	public int getCostG(Coordinate c){
		if (Math.abs(c.getX () -x)==1 && Math.abs(c.getY() - y )==1){
			return 14;
		}else if (c.getX() == x && Math.abs(c.getY()-y) == 1|| c.getY() ==y && Math.abs(c.getX() -x) ==1){
			return 10;
		}else {
			return Integer.MAX_VALUE;
		}
	}
	/**
	 * The H function 
	 * @param c
	 * @return
	 */
	public int getDistanceH(Coordinate c){
		return (Math.abs(c.getX() - x) + Math.abs(c.getY() - y)) * 10;
	}
	/**
	 * The F function
	 * @param c
	 * @return
	 */
	public int getF(Coordinate c){
		return getCostG() + getDistanceH(c);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return String.valueOf(value).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.value == ((Coordinate)obj).value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	
}	
