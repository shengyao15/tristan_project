package com;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestD {
	public static void main(String[] args) {
		t1();
		
		t2();		
		
		
	}

	private static void t2() {
		String[] players = {"Rafael Nadal", "Novak Djokovic", 
			    "Stanislas Wawrinka", "David Ferrer",
			    "Roger Federer", "Andy Murray",
			    "Tomas Berdych", "Juan Martin Del Potro",
			    "Richard Gasquet", "John Isner"};
		// 1.2 使用 lambda expression 排序 players
		Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
		Arrays.sort(players, sortByName);

		// 1.3 也可以采用如下形式:
		Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));
	}

	private static void t1() {
		String[] atp = {"Rafael Nadal", "Novak Djokovic",
			       "Stanislas Wawrinka",
			       "David Ferrer","Roger Federer",
			       "Andy Murray","Tomas Berdych",
			       "Juan Martin Del Potro"};
			List<String> players =  Arrays.asList(atp);

			// 以前的循环方式
			for (String player : players) {
			     System.out.print(player + "; ");
			}
			System.out.println();
			
			// 使用 lambda 表达式以及函数操作(functional operation)
			players.forEach((player) -> System.out.print(player + "; "));
			 
			// 在 Java 8 中使用双冒号操作符(double colon operator)
			players.forEach(System.out::println);
	}
}
