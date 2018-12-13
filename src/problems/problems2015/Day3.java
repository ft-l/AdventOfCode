package problems.problems2015;
import problems.Day;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Day3 extends Day {
	public Day3() {
		super(3, 2015);
		//input = new ArrayList<String>();
		//input.add("^v");
	}
	public static void main(String[] args) {
		Day3 day = new Day3();
		day.part2();
	}
	public void part1() {
		int x = 0;
		int y = 0;
		ArrayList<String> houses = new ArrayList<String>();
		houses.add("0,0");
		for(char move : input.toCharArray()) {
			switch(move) {
				case '^':
					y++;
					break;
				case 'v':
					y--;
					break;
				case '<':
					x--;
					break;
				case '>':
					x++;
					break;
			}
			houses.add(x+","+y);
		}
		houses = new ArrayList<String>(new LinkedHashSet<String>(houses));
		System.out.println(houses.size());
	}
	public void part2() {
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		boolean realSanta = true;
		ArrayList<String> houses = new ArrayList<String>();
		houses.add("0,0");
		for(char move : input.toCharArray()) {
			if(realSanta) {
				switch(move) {
					case '^':
						y1++;
						break;
					case 'v':
						y1--;
						break;
					case '<':
						x1--;
						break;
					case '>':
						x1++;
						break;
				}
			} else {
				switch(move) {
					case '^':
						y2++;
						break;
					case 'v':
						y2--;
						break;
					case '<':
						x2--;
						break;
					case '>':
						x2++;
						break;
				}
			}
			houses.add(x1+","+y1);
			houses.add(x2+","+y2);
			realSanta = !realSanta;
		}
		houses = new ArrayList<String>(new LinkedHashSet<String>(houses));
		System.out.println(houses.size());
	}
}
