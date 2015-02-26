package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * http://www.oschina.net/code/snippet_580147_39168
 * 
 * @author tristan
 *
 */
public class TestF {
	static int tmp_count = 0;

	public static void main(String[] args) throws IOException {

		// t1();

		t2();
		
		//t3(); 
	}

	private static void t3() throws IOException {
		Map<String, Integer> map = Files.readAllLines(Paths.get("COPYRIGHT"), Charset.forName("ISO-8859-1"))
        .stream()
        .flatMap(x -> Stream.of(x.trim().split(" "))) 
        .collect(Collectors.toMap(w->w, w->1, Integer::sum));
		
		for (String key :map.keySet()) {
			System.out.println(key + " : "+ map.get(key));
		}
	}

	private static void t2() throws IOException {
		System.out.println(Files.readAllLines(Paths.get("COPYRIGHT"), Charset.forName("ISO-8859-1")).stream()
				//.peek(x -> System.out.println(x))
				.flatMap(x -> Stream.of(x.trim().split(" ")))
				//.peek(x -> System.out.println(x))
				.collect(Collectors.toMap(w -> w, w -> 1, Integer::sum)))
				;
	}

	private static void t1() throws IOException {
		Files.readAllLines(Paths.get("COPYRIGHT"), Charset.forName("ISO-8859-1")).stream()
				.flatMap(x -> Stream.of(x.trim().split(" "))).sorted().reduce((x, y) -> {
					if (x.equals(y)) {
						tmp_count += 1;
					} else {
						System.out.println(x + "," + tmp_count);
						tmp_count = 1;
					}
					return y;
				});
	}
}
