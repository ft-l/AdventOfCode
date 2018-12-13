package problems.problems2017;

import problems.Day;

public class Day1 extends Day {
	public Day1() {
		super(1, 2017);
	}
	public static void main(String[] args) {
		Day1 day = new Day1();
		day.part1();
		day.part2();
	}
	public void part1() {
		int total = 0;
		for(int i = 0; i < input.length()-1; i++) {
			if(input.charAt(i) == input.charAt(i+1)) {
				total += Integer.parseInt(input.charAt(i) + "");
			}
		}
		if(input.charAt(input.length()-1) == input.charAt(0)) {
			total += Integer.parseInt(input.charAt(0) + "");
		}
		System.out.println(total);
	}
	public void part2() {
		int total = 0;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == input.charAt((i+input.length()/2)%input.length())) {
				total += Integer.parseInt(input.charAt(i) + "");
			}
		}
		System.out.println(total);
	}
}
