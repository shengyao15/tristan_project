package tristan;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import Jama.Matrix;

public class CoMatrix {
	
	private static Logger logger = Logger.getLogger(CoMatrix.class);
	
	public static void main(String[] args) throws Exception {
		String userID = "3";
		String recommandItem = calculate(userID);
		logger.info("为用户 【"+userID + "】   推荐的物品是  【"+recommandItem+"】");
	}
	
	
	public static String  calculate(String userID) throws Exception{
		String[][] userMatrix = parseFile();
		
		//用户 物品的种类
		Set<String> userSet = new TreeSet<String>();
		Set<String> itemSet = new TreeSet<String>();
		
		for (int j = 0; j < userMatrix.length; j++) {
			userSet.add(userMatrix[j][0]);
			itemSet.add(userMatrix[j][1]);
		}
		
		//下标和物品名对应
		Map<String, Integer> mapping = new HashMap<String, Integer>();
		int i2 = 0;
		for (String s : itemSet) {
			mapping.put(s, i2);
			i2++;
		}
		
		//构建共现矩阵
		int itemMatrixSize = itemSet.size();
		double[][] itemMatrix = new double[itemMatrixSize][itemMatrixSize];
		
		//填充共现矩阵
		for (String user: userSet) {
			fillMatrix(user, userMatrix, mapping, itemMatrix);
		}
		
		//将三角矩阵变成全矩阵
		for (int j = 0; j < itemMatrix.length; j++) {
			for (int j2 = 0; j2 < itemMatrix.length; j2++) {
				if(itemMatrix[j][j2] == 0){
					itemMatrix[j][j2] = itemMatrix[j2][j];
				}
			}
		}
		
		//生成用户向量
		double[][] userVector = new double[itemMatrixSize][1];
		double[] userVector2 = new double[itemMatrixSize];
		for (int j = 0; j < userMatrix.length; j++) {
			if(userMatrix[j][0].equals(userID)){
				System.out.println(userMatrix[j][1] + " " + userMatrix[j][2]);
				int index = mapping.get(userMatrix[j][1]);
				userVector[index][0]=Double.valueOf(userMatrix[j][2]);
				userVector2[index] = Double.valueOf(userMatrix[j][2]);
			}
		}
		
		
		//共现矩阵 乘以 用户向量 jama
		Matrix a = new Matrix(itemMatrix);
		Matrix b = new Matrix(userVector);
		Matrix result = a.times(b);
		
		System.out.println();
		double[][] r = result.getArray();
		double[] r2 = new double[r.length];
		for (int j = 0; j < r.length; j++) {
			System.out.println(r[j][0]);
			r2[j] = r[j][0];
		}
		
		
		//减掉已买的
		for (int i = 0; i < userVector2.length; i++) {
			if(userVector2[i]!=0){
				r2[i]=0;
			}
		}
		
		
		//根据R的结果来排序 
		double t[] = new double[r2.length];
		System.arraycopy(r2, 0, t, 0, r2.length);
		Arrays.sort(r2);
		
		double index[] = new double[r2.length];
		int indexInt = 0;
		
		System.out.println(Arrays.toString(r2));
		for (int i = r2.length-1; i >= 0; i--) {
			for (int j = 0; j < t.length; j++) {
				if (r2[i] == t[j] && r2[i]!=0) {
					System.out.println(r2[i] + "的原下标为:" + j);
					index[indexInt] = j;
					indexInt++;
				}
			}
		}
		
		//将下标转换成物品
		String recommendItem = "";
		for (String key : mapping.keySet()) {
			if(mapping.get(key)==index[0]){
				recommendItem=key;
				break;
			}
		}

		
		
		System.out.println("end "+ recommendItem);
		
		return recommendItem;
	}


	private static String[][] parseFile() throws UnsupportedEncodingException,
			FileNotFoundException, IOException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream("intro.csv"), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		int row=0;
		int column=0;
		while ((temp = br.readLine()) != null) {
			if(!temp.equals("")){
				String[] columns = temp.split(",");
				column = columns.length;
				row++;
			}
		}
		
		
		//---
		
		String[][] userMatrix = new String[row][column];
		InputStreamReader isr2 = new InputStreamReader(new FileInputStream("intro.csv"), "UTF-8");
		BufferedReader br2 = new BufferedReader(isr2);
		
		int i9=0;
		while ((temp = br2.readLine()) != null) {
			if(!temp.equals("")){
				
				String[] values = temp.split(",");
				for (int j = 0; j < values.length; j++) {
					userMatrix[i9][j] = values[j];
				}
				
				i9++;
			}
			
		}
		
		//print
		for (int j = 0; j < userMatrix.length; j++) {
			for (int j2 = 0; j2 < userMatrix[j].length; j2++) {
				System.out.print(userMatrix[j][j2] + "  ");
			}
			System.out.println();
		}
		return userMatrix;
	}


	private static void fillMatrix(String userID, String[][] userMatrix,
			Map<String, Integer> mapping, double[][] itemMatrix) {
		List<String> list = new ArrayList<String>();
		for (int j = 0; j < userMatrix.length; j++) {
			if(userMatrix[j][0].equals(userID)){
				list.add(userMatrix[j][1]);
			}
		}
		
		String[] array = list.toArray(new String[0]);
		for (int j = 0; j < array.length; j++) {
			for (int j2 = j; j2 < array.length; j2++) {
				int rowkey = mapping.get(array[j]);
				int columnkey = mapping.get(array[j2]);
				itemMatrix[rowkey][columnkey]++;
			}
		}
	}
}
