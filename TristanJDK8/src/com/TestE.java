package com;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestE {
	public static void main(String[] args) {
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		final List<Integer> doubleNumbers = numbers.stream().map(number -> number * 2)
				.collect(Collectors.toList());
		System.out.println(doubleNumbers);

		final Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
		System.out.println(sum.orElseGet(() -> 0));

		final Integer sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println(sum2);
	}
}

