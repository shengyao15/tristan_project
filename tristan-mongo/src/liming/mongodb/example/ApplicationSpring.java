package liming.mongodb.example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import liming.mongodb.example.data.impl.UserDaoImpl;
import liming.mongodb.example.data.model.NameEntity;
import liming.mongodb.example.data.model.UserEntity;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationSpring {

	public static void main(String[] args) {

		System.out.println("Bootstrapping HelloMongo");

		ConfigurableApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserDaoImpl userDao = context.getBean(UserDaoImpl.class);
	        userDao._test();
		UserEntity entity1 = new UserEntity();
		entity1.setId("5");
		entity1.setAge(1);
		entity1.setBirth(new Date());
		entity1.setPassword("asdfasdf");
		entity1.setRegionName("beijing");
		entity1.setWorks(1);
		NameEntity name = new NameEntity();
		name.setNickname("tristan");
		name.setUsername("shengyao");
		entity1.setName(name);
		userDao.insert(entity1);
		userDao.update(entity1);
		userDao.createCollection();
	
		List<UserEntity> list = userDao.findList(0, 10);
		for (UserEntity e : list) {
			System.out.println("all - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial())
					+ ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());
		}

		list = userDao.findListByAge(1);
		for (UserEntity e : list) {
			System.out.println("age=1 - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special="
					+ Arrays.toString(e.getSpecial()) + ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());
		}

		UserEntity e = userDao.findOne("5");
		System.out.println("id=1 - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial())
				+ ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());

		e = userDao.findOneByUsername("shengyao");
		System.out.println("username=shengyao - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special="
				+ Arrays.toString(e.getSpecial()) + ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());

		
		System.out.println("DONE!");
	}

}
