package problems.problems2017;

import problems.Day;

public class Day5 extends Day {
	public Day5() {
		super(5, 2017);
	}
	public static void main(String[] args) {
		Day5 day = new Day5();
		day.part1();
		day = new Day5();
		day.part2();
	}
	public void part1() {
		int jumps = 0;
		int position = 0;
		String[] input2 = input.split("\n");
		while(position <= input2.length-1) {
			input2[position] = ""+(Integer.parseInt(input2[position])+1);
			position += Integer.parseInt(input2[position])-1;
			jumps++;
		}
		System.out.println(position + " " + jumps);
	}
	public void part2() {
		int jumps = 0;
		int position = 0;
		String[] input2 = input.split("\n");
		while(position <= input2.length-1) {
			if(Integer.parseInt(input2[position]) >= 3) {
				input2[position] = ""+(Integer.parseInt(input2[position])-1);
				position += Integer.parseInt(input2[position])+1;
			} else {
				input2[position] = ""+(Integer.parseInt(input2[position])+1);
				position += Integer.parseInt(input2[position])-1;
			}
			jumps++;
		}
		System.out.println(position + " " + jumps);
	}
}
