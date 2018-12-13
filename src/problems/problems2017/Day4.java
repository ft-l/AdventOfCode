package problems.problems2017;

import problems.Day;

import java.util.Arrays;

public class Day4 extends Day {
	public Day4() {
		super(4, 2017);
	}
	public static void main(String[] args) {
		Day4 day = new Day4();
		day.part2();
	}
	public void part1() {
		int total = 0;
		for(String line : input.split("\n")) {
			boolean isValid = true;
			for(int i = 0; i < line.split(" ").length; i++) {
				for(int c = i+1; c < line.split(" ").length; c++) {
					if(line.split(" ")[i].equals(line.split(" ")[c])) {
						isValid = false;
					}
				}
			}
			if(isValid) {
				total++;
			}
		}
		System.out.println(total);
	}
	public void part2() {
		int total = 0;
		for(String line : input.split("\n")) {
			boolean isValid = true;
			for(int i = 0; i < line.split(" ").length; i++) {
				for(int c = i+1; c < line.split(" ").length; c++) {
					char[] charArray1 = line.split(" ")[i].toCharArray();
					char[] charArray2 = line.split(" ")[c].toCharArray();
					Arrays.sort(charArray1);
					Arrays.sort(charArray2);
					String x = String.valueOf(charArray1);
					String y = String.valueOf(charArray2);
					if(x.equals(y)) {
						isValid = false;
					}
				}
			}
			if(isValid) {
				total++;
			}
		}
		System.out.println(total);
	}
}
