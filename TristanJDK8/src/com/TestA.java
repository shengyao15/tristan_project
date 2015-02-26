package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestA {

	public static void main(String[] args) {

		// Runnable
		//t1();

		// sort
		//t2();

		// Converter
		//t3();

		// Stream
		//t4();
		
		// Parallise
		//t5();
		
		// Object  
		//t6();
		
		
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");
		List<String> filteredNames = names.stream().filter(e -> e.length() >= 4).collect(Collectors.toList());
		for (String name : filteredNames) {
		    System.out.println(name);
		}
	}

	private static void t6() {
		//一个很有意思的事情： 之前我们说Object是一切类的父类，然而在加入了lambda以后，这种大一统的局面将不复存在：
		//很显然，编译器会检查变量的引用类型里面是否真的是一个函数式接口。那么如何让这段代码通过编译呢？
		// Runnable加上了注释 @FunctionalInterface
		Object r = (Runnable)()->System.out.println("hello,lambda");
	}

	private static void t5() {
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
		    UUID uuid = UUID.randomUUID();
		    values.add(uuid.toString());
		}
		
		long begin = System.currentTimeMillis();
		//long count = values.stream().sorted().count();  //2209
		long count = values.parallelStream().sorted().count();  //1377
		System.out.println(count);
		System.out.println(System.currentTimeMillis() - begin);
	}

	private static void t4() {
		List<String> list = new ArrayList<>();
		list.add("ddd2");
		list.add("aaa2");
		list.add("bbb1");
		list.add("aaa1");
		list.add("bbb3");
		list.add("aaa3");
		list.add("ccc");
		list.add("bbb2");
		list.add("ddd1");

		list.stream().sorted((a, b) -> b.compareTo(a)).filter((s) -> s.startsWith("a"))
				.forEach(System.out::println);

		list.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);

		long startsWithB = list.stream().filter((s) -> s.startsWith("b")).count();
		System.out.println(startsWithB);

		Optional<String> reduced = list.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);
		
		Stream<String> filter = list.stream().sorted((a, b) -> b.compareTo(a)).filter((s) -> s.startsWith("a"));
		filter.forEach(System.out::println);
		Optional<String> findAny = filter.findAny();
		
		
		
	}

	
	private static void t3() {
		Converter<String, Integer> converter = Integer::valueOf;
		Integer i = converter.convert("123");
		System.out.println(i); // 123

	}

	private static void t2() {
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		Collections.sort(names);

		Collections.sort(names, (a, b) -> b.compareTo(a));

		names.forEach(System.out::println);
	}

	private static void t1() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello,lambda!");
			}
		};
		r.run();

		Runnable r2 = () -> System.out.println("hello,lambda");
		r2.run();
	}

}


@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
}