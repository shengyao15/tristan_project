package com.tristan2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * http://blog.csdn.net/renfufei/article/details/24600507
 * @author tristan
 *
 */
public class Person {

	public static void main(String[] args) {
		List<Person> javaProgrammers = new ArrayList<Person>() {
			{
				add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
				add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
				add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
				add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
				add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
				add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
				add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
				add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
				add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
				add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
			}
		};

		List<Person> phpProgrammers = new ArrayList<Person>() {
			{
				add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
				add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
				add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
				add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
				add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
				add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
				add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
				add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
				add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
				add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
			}
		};
		
		//t1(javaProgrammers);
		
		//t2(phpProgrammers);
		
		//t3(javaProgrammers, phpProgrammers);

		//t4(javaProgrammers);
	
		//t5(javaProgrammers);
		
		
		//t6(javaProgrammers);
		
		
		t7(javaProgrammers, phpProgrammers);

		//t8(javaProgrammers);
		
		//t9(); 
		
		
	}

	private static void t9() {
		//计算 count, min, max, sum, and average for numbers
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		IntSummaryStatistics stats = numbers
		          .stream()
		          .mapToInt((x) -> x)
		          .summaryStatistics();

		System.out.println("List中最大的数字 : " + stats.getMax());
		System.out.println("List中最小的数字 : " + stats.getMin());
		System.out.println("所有数字的总和   : " + stats.getSum());
		System.out.println("所有数字的平均值 : " + stats.getAverage());
	}

	private static void t8(List<Person> javaProgrammers) {
		System.out.println("计算付给 Java programmers 的所有money:");
		int totalSalary = javaProgrammers
		          .parallelStream()
		          .mapToInt(p -> p.getSalary())
		          .sum();
		System.out.println(totalSalary);
	}

	private static void t7(List<Person> javaProgrammers, List<Person> phpProgrammers) {
		System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
		String phpDevelopers = phpProgrammers
		          .stream()
		          .map(p->p.getFirstName())
		          //.map(Person::getFirstName)
		          .collect(Collectors.joining(" ; ")); // 在进一步的操作中可以作为标记(token)   
		System.out.println(phpDevelopers);
		
		System.out.println("将 Java programmers 的 first name 存放到 Set:");
		Set<String> javaDevFirstName = javaProgrammers
		          .stream()
		          .map(Person::getFirstName)
		          .collect(Collectors.toSet());
		System.out.println(javaDevFirstName);
		
		System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
		TreeSet<String> javaDevLastName = javaProgrammers
		          .stream()
		          .map(Person::getLastName)
		          .collect(Collectors.toCollection(TreeSet::new));
		System.out.println(javaDevLastName);
	}

	private static void t6(List<Person> javaProgrammers) {
		System.out.println("工资最低的 Java programmer:");
		Person pers = javaProgrammers
		          .stream()
		          .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
		          .get();

		System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());

		System.out.println("工资最高的 Java programmer:");
		Person person = javaProgrammers
		          .stream()
		          .max((p, p2) -> (p.getSalary() - p2.getSalary()))
		          .get();

		System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(), person.getSalary());
	}

	private static void t5(List<Person> javaProgrammers) {
		System.out.println("根据 name 排序,并显示前5个 Java programmers:");
		List<Person> sortedJavaProgrammers = javaProgrammers
		          .stream()
		          .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
		          .limit(5)
		          .collect(Collectors.toList());

		sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));
		 
		System.out.println("根据 salary 排序 Java programmers:");
		sortedJavaProgrammers = javaProgrammers
		          .stream()
		          .sorted( (p, p2) -> (p.getSalary() - p2.getSalary()) )
		          .collect( Collectors.toList() );

		sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));
	}

	private static void t4(List<Person> javaProgrammers) {
		Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));
		
		System.out.println("最前面的3个 Java programmers:");
		javaProgrammers.stream()
		          .limit(3)
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

		System.out.println();
		System.out.println("最前面的3个女性 Java programmers:");
		javaProgrammers.stream()
		          .filter(genderFilter)
		          .limit(3)
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
	}

	private static void t3(List<Person> javaProgrammers, List<Person> phpProgrammers) {
		// 定义 filters
		Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
		Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
		Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

		System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
		phpProgrammers.stream()
		          .filter(ageFilter)
		          .filter(salaryFilter)
		          .filter(genderFilter)
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

		// 重用filters
		System.out.println("年龄大于 24岁的女性 Java programmers:");
		javaProgrammers.stream()
		          .filter(ageFilter)
		          .filter(genderFilter)
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
	}

	private static void t2(List<Person> phpProgrammers) {
		System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
		phpProgrammers.stream()
		          .filter((p) -> (p.getSalary() > 1400))
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
	}

	private static void t1(List<Person> javaProgrammers) {
		System.out.println("所有程序员的姓名:");
		javaProgrammers.forEach((p) -> System.out.printf("%s %s %s; ", p.getFirstName(), p.getLastName(),p.getSalary()));
		System.out.println();
		
		System.out.println("给程序员加薪 5% :");
		
		//传统的内部匿名类
		Consumer<Person> giveRaise2 = new Consumer<Person>() {
			@Override
			public void accept(Person t) {
				t.setSalary(t.getSalary()/100*5 + t.getSalary());
				
			}
		};
		//函数式编程
		Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());

		javaProgrammers.forEach(giveRaise);
		javaProgrammers.forEach(giveRaise2);
		javaProgrammers.forEach((p) -> System.out.printf("%s %s %s; ", p.getFirstName(), p.getLastName(),p.getSalary()));
	}

	private String firstName, lastName, job, gender;
	private int salary, age;

	public Person(String firstName, String lastName, String job, String gender, int age, int salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.job = job;
		this.salary = salary;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}