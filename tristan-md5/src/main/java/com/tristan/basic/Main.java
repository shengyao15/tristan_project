package com.tristan.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Main {

	public static Jedis jedis;

	static {
		jedis = new Jedis("192.168.1.105", 6379, 2 * 60 * 1000);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Set<String> s_a = new HashSet<String>();
		s_a.add("a");
		s_a.add("A");
		s_a.add("@");
		Set<String> s_b = new HashSet<String>();
		s_b.add("b");
		s_b.add("B");
		Set<String> s_c = new HashSet<String>();
		s_c.add("c");
		s_c.add("C");
		Set<String> s_d = new HashSet<String>();
		s_d.add("d");
		s_d.add("D");
		Set<String> s_e = new HashSet<String>();
		s_e.add("e");
		s_e.add("E");
		Set<String> s_f = new HashSet<String>();
		s_f.add("f");
		s_f.add("F");
		Set<String> s_g = new HashSet<String>();
		s_g.add("g");
		s_g.add("G");
		Set<String> s_h = new HashSet<String>();
		s_h.add("h");
		s_h.add("H");
		Set<String> s_i = new HashSet<String>();
		s_i.add("i");
		s_i.add("I");
		Set<String> s_j = new HashSet<String>();
		s_j.add("j");
		s_j.add("J");
		Set<String> s_k = new HashSet<String>();
		s_k.add("k");
		s_k.add("K");
		Set<String> s_l = new HashSet<String>();
		s_l.add("l");
		s_l.add("L");
		Set<String> s_m = new HashSet<String>();
		s_m.add("m");
		s_m.add("M");
		Set<String> s_n = new HashSet<String>();
		s_n.add("n");
		s_n.add("N");
		Set<String> s_o = new HashSet<String>();
		s_o.add("o");
		s_o.add("O");
		s_o.add("0");
		Set<String> s_p = new HashSet<String>();
		s_p.add("p");
		s_p.add("P");
		Set<String> s_q = new HashSet<String>();
		s_q.add("q");
		s_q.add("Q");
		Set<String> s_r = new HashSet<String>();
		s_r.add("r");
		s_r.add("R");
		Set<String> s_s = new HashSet<String>();
		s_s.add("s");
		s_s.add("S");
		Set<String> s_t = new HashSet<String>();
		s_t.add("t");
		s_t.add("T");
		Set<String> s_u = new HashSet<String>();
		s_u.add("u");
		s_u.add("U");
		Set<String> s_v = new HashSet<String>();
		s_v.add("v");
		s_v.add("V");
		Set<String> s_w = new HashSet<String>();
		s_w.add("w");
		s_w.add("W");
		Set<String> s_x = new HashSet<String>();
		s_x.add("x");
		s_x.add("X");
		Set<String> s_y = new HashSet<String>();
		s_y.add("y");
		s_y.add("Y");
		Set<String> s_z = new HashSet<String>();
		s_z.add("z");
		s_z.add("Z");

		Set<String> s_0 = new HashSet<String>();
		s_0.add("0");
		s_0.add(")");
		Set<String> s_1 = new HashSet<String>();
		s_1.add("1");
		s_1.add("!");
		Set<String> s_2 = new HashSet<String>();
		s_2.add("2");
		s_2.add("@");
		Set<String> s_3 = new HashSet<String>();
		s_3.add("3");
		s_3.add("#");
		Set<String> s_4 = new HashSet<String>();
		s_4.add("4");
		s_4.add("$");
		Set<String> s_5 = new HashSet<String>();
		s_5.add("5");
		s_5.add("%");
		Set<String> s_6 = new HashSet<String>();
		s_6.add("6");
		s_6.add("^");
		Set<String> s_7 = new HashSet<String>();
		s_7.add("7");
		s_7.add("&");
		Set<String> s_8 = new HashSet<String>();
		s_8.add("8");
		s_8.add("*");
		Set<String> s_9 = new HashSet<String>();
		s_9.add("9");
		s_9.add("(");

		// prepareData("md5-6number",6,false);
		// test("md5-6number","127589");

		// prepareData("md5-10mix", 10, true);
		// test("md5-6number","asdf654321");

		// 2500条数据 = 1MB
		// System.out.println(Math.pow(9, 6)/25000 + "MB"); //21.25764MB
		// System.out.println(Math.pow(9, 7)/(25000*1024) + "GB"); //0.18GB
		// System.out.println(Math.pow(9, 6)/(25000*1024) + "GB"); //1.68GB
		// 19+20
		// System.out.println(Math.pow(36, 10)/(25000*1024) + "GB"); //1.4E8GB
		// 1400W GB

		//jedis.flushAll();
		Set<String> rawData = new HashSet<String>();

		// 6number
		prepareRowData1(rawData, "", 6);
		System.out.println("6number done  " + rawData.size());
		generateMD5(rawData);

		// 1qazxsw2 zaq12wsx
		generateCombination(rawData, s_1, s_q, s_a, s_z, s_x, s_s, s_w, s_2);
		generateCombination(rawData, s_2, s_w, s_s, s_x, s_c, s_d, s_e, s_3);
		generateCombination(rawData, s_3, s_e, s_d, s_c, s_v, s_f, s_r, s_4);
		generateCombination(rawData, s_4, s_r, s_f, s_v, s_b, s_g, s_t, s_5);
		generateCombination(rawData, s_5, s_t, s_g, s_b, s_n, s_h, s_y, s_6);
		generateCombination(rawData, s_6, s_y, s_h, s_n, s_m, s_j, s_u, s_7);
		System.out.println("1qazxsw2 done  " + rawData.size());
		generateMD5(rawData);

		// 90op90op
		generateCombination(rawData, s_9, s_0, s_o, s_p, s_9, s_0, s_o, s_p);
		generateCombination(rawData, s_8, s_9, s_i, s_o, s_8, s_9, s_i, s_o);
		generateCombination(rawData, s_7, s_8, s_u, s_i, s_7, s_8, s_u, s_i);
		generateCombination(rawData, s_6, s_7, s_y, s_u, s_6, s_7, s_y, s_u);
		generateCombination(rawData, s_5, s_6, s_t, s_y, s_5, s_6, s_t, s_y);
		generateCombination(rawData, s_4, s_5, s_r, s_t, s_4, s_5, s_r, s_t);
		generateCombination(rawData, s_3, s_4, s_e, s_r, s_3, s_4, s_e, s_r);
		generateCombination(rawData, s_2, s_3, s_w, s_e, s_2, s_3, s_w, s_e);
		generateCombination(rawData, s_1, s_2, s_q, s_w, s_1, s_2, s_q, s_w);
		System.out.println("90op90op done  " + rawData.size());
		generateMD5(rawData);

		// password
		for (String a : s_a) {
			for (String o : s_o) {
				for (String p : s_p) {
					rawData.add(p + a + "ssw" + o + "rd");

				}
			}
		}
		System.out.println("password done  " + rawData.size());
		generateMD5(rawData);

		// 19xxxxxxxx
		prepareRowData1(rawData, "19", 6);
		System.out.println("19xxxxxxxx done  " + rawData.size());
		generateMD5(rawData);

		// 20xxxxxxxx
		prepareRowData1(rawData, "20", 6);
		System.out.println("20xxxxxxxx done  " + rawData.size());
		generateMD5(rawData);

		// asdf654321
		rawData.add("asdf654321");
		rawData.add("asdf123456");
		prepareRowData1(rawData, "asdf", 6);
		System.out.println("asdf654321 done  " + rawData.size());
		generateMD5(rawData);

		System.out.println("done");

	}

	private static void generateMD5(Set<String> rawData) {

		int i = 0;
		Map<String, String> md5Map = new HashMap<String, String>();
		for (String s : rawData) {
			String k = MD5Utils.generatePassword(s);
			md5Map.put(k, s);
			i++;

			if (i % 10000 == 0) {
				jedis.hmset("md5", md5Map);
				md5Map = new HashMap<String, String>();
			}
		}
		rawData.clear();
		jedis.hmset("md5", md5Map);
		jedis.save();
	}

	private static void generateCombination(Set<String> combination, Set<String> n_1, Set<String> n_2, Set<String> n_3, Set<String> n_4,
			Set<String> n_5, Set<String> n_6, Set<String> n_7, Set<String> n_8) {
		for (String n1 : n_1) {
			for (String n2 : n_2) {
				for (String n3 : n_3) {
					for (String n4 : n_4) {
						for (String n5 : n_5) {
							for (String n6 : n_6) {
								for (String n7 : n_7) {
									for (String n8 : n_8) {
										combination.add(n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8);
										combination.add(n4 + n3 + n2 + n1 + n8 + n7 + n6 + n5);
									}
								}
							}
						}
					}
				}
			}
		}

	}

	private static void prepareRowData1(Set<String> combination, String prefix, int length) {
		long number = (long) Math.pow(10, length) * 5;
		for (long i = 0; i < number; i++) {
			String s = randomString(length);
			combination.add(prefix + s);
		}
	}

	public static String randomString(int length) {
		String str = "0123456789";

		Random random = new Random();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			int num = random.nextInt(str.length());
			buf.append(str.charAt(num));
		}

		return buf.toString();
	}

}
