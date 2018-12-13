package problems.problems2015;
import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day2 extends Day {
	public Day2() {
		super(2, 2015);
	}
	public static void main(String[] args) {
		Day2 day = new Day2();
		day.part2();
	}
	public void part1() {
		int total = 0;
		for(String line : input.split("\n")) {
			String[] lineParts = line.split("x");
			int l = Integer.parseInt(lineParts[0]);
			int w = Integer.parseInt(lineParts[1]);
			int h = Integer.parseInt(lineParts[2]);
			int surfArea = (2*l*w) + (2*w*h) + (2*l*h);
			Integer[] sides = {l,w,h};
			for(int i = 0; i < 3; i ++) {
				if(sides[i] == Collections.max(new ArrayList<Integer>(Arrays.asList(sides)))) {
					sides[i] = 1;
					break;
				}
			}
			surfArea += arrayProduct(sides);
			System.out.println(surfArea);
			total += surfArea;
		}
		System.out.println(total);
	}
	public void part2() {
		int total = 0;
		for(String line : input.split("\n")) {
			String[] lineParts = line.split("x");
			int l = Integer.parseInt(lineParts[0]);
			int w = Integer.parseInt(lineParts[1]);
			int h = Integer.parseInt(lineParts[2]);
			Integer[] sides = {l,w,h};
			int ribbon = arrayProduct(sides);
			for(int i = 0; i < 3; i ++) {
				if(sides[i] == Collections.max(new ArrayList<Integer>(Arrays.asList(sides)))) {
					sides[i] = 0;
					break;
				}
			}
			ribbon += (sides[0]*2) + (sides[1]*2) + (sides[2]*2);
			System.out.println(ribbon);
			total += ribbon;
		}
		System.out.println(total);
	}
	public static int arrayProduct(Integer[] array) {
		int total = 1;
		for(int x : array) {
			total = total*x;
		}
		return total;
	}
}
