package problems.problems2017;

import problems.Day;

import java.util.ArrayList;
import java.util.Collections;

public class Day2 extends Day {
	public Day2() {
		super(2, 2017);
	}
	public static void main(String[] args) {
		Day2 day = new Day2();
		day.part1();
		day.part2();
	}
	public void part1() {
		int checksum = 0;
		for(String line:input.split("\n")) {
			ArrayList<Integer> values = new ArrayList<>();
			for(String x : line.split("\t")) {
				values.add(Integer.parseInt(x));
			}
			checksum += Collections.max(values) - Collections.min(values);
		}
		System.out.println(checksum);
	}
	public void part2() {
		int checksum = 0;
		for(String line: input.split("\n")) {
			ArrayList<Integer> values = new ArrayList<>();
			for(String x : line.split("\t")) {
				values.add(Integer.parseInt(x));
			}
			for(int i = 0; i < values.size(); i++) {
				for(int c = 0; c < values.size(); c++) {
					if(values.get(i) < values.get(c)) {
						if((values.get(c) % values.get(i)) == 0)  {
							checksum += values.get(c)/values.get(i);
						}
					}
				}
			}
		}
		System.out.println(checksum);
	}
}
