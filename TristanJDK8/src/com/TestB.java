package com;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 对象集合排序
 * 
 * @author tristan
 *
 */
public class TestB {
	public static void main(String args[]) {
		Person people[] = new Person[] { new Person("Ted", "Neward", 41),
				new Person("Charlotte", "Neward", 41), new Person("Michael", "Neward", 19),
				new Person("Matthew", "Neward", 13) };
		// method1
		Arrays.sort(people, (a, b) -> a.firstName.compareTo(b.firstName));
		for (Person p : people) {
			System.out.println(p);
		}

		// method2
		Arrays.sort(people, Person.compareAge);// 这里直接引用lambda
		for (Person p : people) {
			System.out.println(p);
		}

		// method3
		Arrays.sort(people, Person::compareFirstName);// 这里直接引用lambda
		for (Person p : people) {
			System.out.println(p);
		}

		// method4
		Arrays.sort(people,  Comparator.comparing(Person::getFirstName));// 这里直接引用lambda
		for (Person p : people) {
			System.out.println(p);
		}

	}
}

class Person {
	public String firstName;
	public String lastName;
	public int age;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String toString() {
		return firstName + "," + lastName + "," + age;
	}

	public static int compareFirstName(Person lhs, Person rhs) {
		return lhs.firstName.compareTo(rhs.firstName);
	}

	public final static Comparator<Person> compareFirstName = (a, b) -> a.firstName.compareTo(b.firstName);

	public final static Comparator<Person> compareLastName = (a, b) -> a.lastName.compareTo(b.lastName);

	public final static Comparator<Person> compareAge = (a, b) -> a.age - b.age;
}
