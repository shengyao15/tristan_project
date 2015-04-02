package com.tristan.jje;

import redis.clients.jedis.Jedis;

import com.tristan.basic.MD5Utils;

public class TestMD5 {
	public static Jedis jedis;

	static {
		jedis = new Jedis("192.168.1.101", 6379);
	}

	public static void main(String[] args) {
		System.out.println(jedis.hmget("md5", MD5Utils.generatePassword("90op()OP")));
		System.out.println(jedis.hmget("md5", MD5Utils.generatePassword("1qazXSW@")));
		System.out.println(jedis.hmget("md5", MD5Utils.generatePassword("asdf641231")));
		System.out.println(jedis.hmget("md5", MD5Utils.generatePassword("P@ssw0rd")));

		System.out.println(jedis.hmget("md5", "6485600689F28B9D062486BB48E77494"));
		System.out.println(jedis.hmget("md5", "E10ADC3949BA59ABBE56E057F20F883E"));
		System.out.println(jedis.hmget("md5", "0FA0A7CDAFF5C077221DCBAEB145FC38"));
		System.out.println(jedis.hmget("md5", "91C236A945365190DF095EF51F8DEB47"));
		System.out.println(jedis.hmget("md5", "78019107BBC41E58F83262A231EBBBA7"));
		System.out.println(jedis.hmget("md5", "F15E046FF6638FFB75618F1F69ADFBF4"));
		System.out.println(jedis.hmget("md5", "8643E50A3A6858FADDF6172141711D83"));
		System.out.println(jedis.hmget("md5", "F63F4FBC9F8C85D409F2F59F2B9E12D5"));
		System.out.println(jedis.hmget("md5", "910E848E38C05CA41E8D3CEDED2BEED8"));
		System.out.println(jedis.hmget("md5", "E10ADC3949BA59ABBE56E057F20F883E"));
		System.out.println(jedis.hmget("md5", "A10964B1FA5A2F7CD73FFB3911C3791A"));
		System.out.println(jedis.hmget("md5", "00EDC11D6F718E88F8319F1912462EFE"));
		System.out.println(jedis.hmget("md5", "9D3D671266CAA802D5A9C8801D42D464"));
	}
}
