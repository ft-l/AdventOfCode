package problems.problems2015;
import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day14 extends Day {
	ArrayList<Reindeer> reindeers = new ArrayList<>();
	public Day14() {
		super(14, 2015);
		for(int i = 0; i < input.split("\n").length; i++) {
			String[] x = input.split("\n")[i].split(" ");
			reindeers.add(new Reindeer(x[0], Integer.parseInt(x[3]),Integer.parseInt(x[6]),Integer.parseInt(x[13])));
		}
	}
	public Day14(String test) {
		super(14, 2015);
		reindeers.add(new Reindeer("Comet", 14, 10, 127));
		reindeers.add(new Reindeer("Dancer", 16, 11, 162));
	}
	public static void main(String[] args) {
		Day14 day = new Day14();
		//day.part1();
		day.part2();
	}
	public void part1() {
		int highest = 0;
		for(Reindeer reindeer : reindeers) {
			if(reindeer.distanceTravelled(2503)> highest) {
				highest = reindeer.distanceTravelled(2503);
			}
		}
		System.out.println(highest);
	}
	public void part2() {
		int[] points = new int[reindeers.size()];
		for(int i = 1; i <= 2503; i++) {
			Integer[] second = {reindeers.get(0).distanceTravelled(i),reindeers.get(1).distanceTravelled(i),reindeers.get(2).distanceTravelled(i),reindeers.get(3).distanceTravelled(i),reindeers.get(4).distanceTravelled(i),reindeers.get(5).distanceTravelled(i),reindeers.get(6).distanceTravelled(i),reindeers.get(7).distanceTravelled(i),reindeers.get(8).distanceTravelled(i)};
			ArrayList<Integer> secondArray = new ArrayList<Integer>(Arrays.asList(second));
			for(int c = 0; c < reindeers.size(); c++) {
				if(second[c].equals(Collections.max(secondArray))) {
					points[c]++;
				}
			}
		}
		for(int point : points) {
			System.out.println(point);
		}
	}
	public static boolean onlyMax(ArrayList<Integer> x) {
		for(Integer y : x) {
			if(!(y == x.get(0) || y == 0)) {
				return false;
			}
		}
		return true;
	}
}