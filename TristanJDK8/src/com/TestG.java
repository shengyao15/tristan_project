package com;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 * @author tristan
 *
 */
public class TestG {
	public static void main(String[] args) {
	     Stream.of("one", "two", "three", "four")
         .filter(e -> e.length() > 3)
         .peek(e -> System.out.println("Filtered value: " + e))
         .map(String::toUpperCase)
         .peek(e -> System.out.println("Mapped value: " + e))
         .collect(Collectors.toList());
	}
}
