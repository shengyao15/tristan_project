package com;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class TestC {
	public static void main(String[] args) throws Exception {
		String prefix = "The information";
//		for(String line : Files.readAllLines(Paths.get("COPYRIGHT"),Charset.forName("ISO-8859-1"))){
//			System.out.println(line);
//		}
		
		
		Stream<String> lines = Files.lines(Paths.get("COPYRIGHT"),Charset.forName("ISO-8859-1"));
		Stream<String> lines2 = Files.lines(Paths.get("COPYRIGHT"),Charset.forName("ISO-8859-1"));
		Stream<String> lines3 = Files.lines(Paths.get("COPYRIGHT"),Charset.forName("ISO-8859-1"));
		Optional<String> findFirst = lines.filter(line->line.startsWith(prefix))
		 	 .findFirst();
		System.out.println(findFirst.orElse("not exist"));
		
		
		System.out.println(lines2.count());
	}
}
