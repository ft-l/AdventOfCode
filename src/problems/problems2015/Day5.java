package problems.problems2015;
import problems.Day;

import java.util.ArrayList;

public class Day5 extends Day {
	public Day5() {
		super(5, 2015);
	}
	public static void main(String[] args) {
		Day5 day = new Day5();
		day.part1();
		day.part2();
	}
	public void part1() {
		int total = 0;
		for(String line : input.split("\n")) {
			if(isNice(line)) {
				total++;
			}
		}
		System.out.println(total);
	}
	public void part2() {
		int total = 0;
		for(String line : input.split("\n")) {
			if(isNicePart2(line)) {
				total++;
			}
		}
		System.out.println(total);
	}
	public static boolean hasThreeVowels(String line) {
		int vowels = 0;
		for(char character : line.toCharArray()) {
			if(character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u') {
				vowels++;
			}
			if(vowels > 2) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean hasPair(String line) {
		for(int i = 0; i < line.length()-1; i++) {
			if(line.charAt(i) == line.charAt(i+1)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean containsBadPair(String line) {
		return line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy");
	}
	
	public static boolean hasNotOverlappingPair(String line) {
		ArrayList<String> pairs = new ArrayList<>();
		for(int i = 0; i < line.length()-1; i++) {
			pairs.add(line.substring(i, i+2));
		}
		for(int i = 0; i < pairs.size()-2; i++) {
			for(int c = i+2; c < pairs.size(); c++) {
				if(pairs.get(i).equals(pairs.get(c))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean hasABA(String line) {
		for(int i = 0; i < line.length()-2; i++) {
			if(line.charAt(i) == line.charAt(i+2)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNice(String line) {
		return hasPair(line) && !containsBadPair(line) && hasThreeVowels(line);
	}
	
	public static boolean isNicePart2(String line) {
		 return hasNotOverlappingPair(line) && hasABA(line);
	}
}
