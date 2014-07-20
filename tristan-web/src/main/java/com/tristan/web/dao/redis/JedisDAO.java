package com.tristan.web.dao.redis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.tristan.web.po.redis.StudentJedis;

import redis.clients.jedis.Jedis;

public class JedisDAO {
	
	public static Jedis jedis;
	
	static{
		jedis =  new Jedis("192.168.1.102", 6379);
	}
	public static void main(String[] args) {
		
		//prepareData();
		//listAll();
		//searchByCountry("Japan");
		avgByCountry();
		
	}
	
	public static List<StudentJedis> avgByCountry() {
		
		String[] countrys = {"USA","China","Japan"};
		List<StudentJedis> list = new ArrayList<StudentJedis>();
		
		for (int i = 0; i < countrys.length; i++) {
			StudentJedis student = new StudentJedis();
				List<String> l1 = jedis.lrange("link:"+countrys[i]+":english", 0, -1);
				int sum = 0;
				for (String s : l1) {
					sum += Integer.valueOf(s);
				}
				student.setCountry(countrys[i]);
				student.setEnglish(sum/l1.size());
				list.add(student);
		}
		return list;
		
	}

	public static List<StudentJedis> searchByCountry(String country) {
		Set<String> keys = jedis.keys("student:*:"+country);
		List<StudentJedis> list = new ArrayList<StudentJedis>();
		
		for (String key : keys) {
			Map<String,String> map = jedis.hgetAll(key);
			StudentJedis s = new StudentJedis();
			s.setKey(key);
			s.setName(map.get("name"));
			s.setAge(Integer.valueOf(map.get("age")));
			s.setCountry(map.get("country"));
			s.setEnglish(Integer.valueOf(map.get("english")));
			list.add(s);
		}
		
		for (StudentJedis student : list) {
			System.out.println(student.getKey() + "  " + student.getName() + "  " + student.getAge() + "   " + student.getCountry() + "  "+ student.getEnglish());
		}
		
		return list;
	}
	
	public static List<StudentJedis> listAll() {
		Set<String> keys = jedis.keys("student:*");
		List<StudentJedis> list = new ArrayList<StudentJedis>();
		
		for (String key : keys) {
			Map<String,String> map = jedis.hgetAll(key);
			StudentJedis s = new StudentJedis();
			s.setName(map.get("name"));
			s.setAge(Integer.valueOf(map.get("age")));
			s.setCountry(map.get("country"));
			s.setEnglish(Integer.valueOf(map.get("english")));
			list.add(s);
		}
		
		for (StudentJedis student : list) {
			System.out.println(student.getName() + "  " + student.getAge() + "   " + student.getCountry() + "  "+ student.getEnglish());
		}
		
		return list;
	}



	public static void prepareData() {
		
		
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

	public static void testA() {
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
