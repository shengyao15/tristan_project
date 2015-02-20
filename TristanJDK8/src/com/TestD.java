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
		// 1.2 ʹ�� lambda expression ���� players
		Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
		Arrays.sort(players, sortByName);

		// 1.3 Ҳ���Բ���������ʽ:
		Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));
	}

	private static void t1() {
		String[] atp = {"Rafael Nadal", "Novak Djokovic",
			       "Stanislas Wawrinka",
			       "David Ferrer","Roger Federer",
			       "Andy Murray","Tomas Berdych",
			       "Juan Martin Del Potro"};
			List<String> players =  Arrays.asList(atp);

			// ��ǰ��ѭ����ʽ
			for (String player : players) {
			     System.out.print(player + "; ");
			}
			System.out.println();
			
			// ʹ�� lambda ����ʽ�Լ���������(functional operation)
			players.forEach((player) -> System.out.print(player + "; "));
			 
			// �� Java 8 ��ʹ��˫ð�Ų�����(double colon operator)
			players.forEach(System.out::println);
	}
}