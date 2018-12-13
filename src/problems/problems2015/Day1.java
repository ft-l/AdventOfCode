package problems.problems2015;

import problems.Day;

public class Day1 extends Day {

	public Day1() {
		super(1, 2015);
	}

	public static void main(String[] args) {
		Day1 day = new Day1();
		day.part2();
	}
	public void part1() {
		int floor = 0;
		for(char move : input.toCharArray()) {
			if(move == '(') {
				floor++;
			} else {
				floor--;
			}
		}
		System.out.println(floor);
	}
	public void part2() {
		int floor = 0;
		for(int i = 0; i < input.toCharArray().length; i++) {
			if(input.toCharArray()[i] == '(') {
				floor++;
			} else {
				floor--;
			}
			if(floor == -1) {
				System.out.println(i+1);
				break;
			}
		}
	}
}
