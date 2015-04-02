package com.tristan.jje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class QueryData {
	public static Jedis jedis;

	static {
		//jedis = new Jedis("172.24.212.47", 6379);
		jedis = new Jedis("192.168.1.101", 6379);
	}

	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream(new File("D:/jinjiang/tristan/account.txt"));
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String temp = "";
		
		Set<String> set = new HashSet<String>();
		Map<String,String> md5EmailMap = new HashMap<String, String>();
		
		while ((temp = br.readLine()) != null) {
			String[] s =temp.split(",");
			if(s.length>=6){
				String md5 = s[6];
				set.add(md5);
				
				String email = s[5];
				if(email.indexOf("@")>0){
					md5EmailMap.put(md5, email);
				}
			}
			
			
			
		}
		
		System.out.println("account size - all " + set.size());
		
		Map<String,String> map = new HashMap<String, String>();
		for (String md5 : set) {
			String password = jedis.hmget("md5", md5).toString();
			if(!"[null]".equals(password)){
				map.put(md5, password);
			}
			
		}
		
		System.out.println("account size - hacker " + map.size());
		
		for (String key : map.keySet()) {
			for (String md5 : md5EmailMap.keySet()) {
				if(key.equals(md5)){
					System.out.println(key + "," + map.get(key) + "," + md5EmailMap.get(md5));
				}
			}
			
			
		}
		
		
	}
}
