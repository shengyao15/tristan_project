package com.tristan.basic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class JedisDemo {
	
	public static Jedis jedis;
	
	static{
		jedis =  new Jedis("192.168.1.105", 6379);
	}
	public static void main(String[] args) {
		
		prepareData();
		
	}
	



	private static void prepareData() {
		
		
		Random r = new Random();
		String[] countrys = {"USA", "China", "Japan"};
		for(int i =0; i<20; i++){
			Map<String,String> map = new HashMap<String, String>();
			
			Long stduentIndex = jedis.incr("studentIndex");
			String country = countrys[r.nextInt(3)];
			String english = String.valueOf(30+r.nextInt(70));
			
			map.put("name", "redis"+stduentIndex);
			map.put("age", String.valueOf(20+r.nextInt(10)));
			map.put("country", country);
			map.put("english", english);
			jedis.hmset("student:"+stduentIndex+":"+country, map);
			jedis.lpush("link:"+country+":english", english);
			
		}
		
		
		
	}

	private static void testA() {
		jedis.set("xxx", "yyy");
		System.out.println(jedis.get("xxx"));
	}

	public static void testB() {
		/*long status = jedis.sadd("foo", "a");
		System.out.println(status);

		status = jedis.sadd("foo", "a");
		System.out.println(status);*/
		
		jedis.sadd("foo", "a");
		jedis.sadd("foo", "b");
		jedis.sadd("foo", "c");
		jedis.sadd("foo", "b");
		
		Set<String> expected = jedis.smembers("foo");
		for (String s : expected) {
			System.out.println(s);
		}
		
		
	}
}
