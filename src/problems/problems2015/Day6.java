package problems.problems2015;
import problems.Day;

import java.util.ArrayList;

public class Day6 extends Day {
	public Day6() {
		super(6, 2015);
	}
	public static void main(String[] args) {
		Day6 day = new Day6();
		day.part2();
	}
	public void part1() {
		int total = 0;
		ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
		for(int i = 0; i < 1000; i++) {
			ArrayList<Integer> column = new ArrayList<Integer>();
			for(int c = 0; c < 1000; c++) {
				column.add(0);
			}
			rows.add(column);
		}
		for(String line : input.split("\n")) {
			if(line.split(" ")[0].equals("toggle")) {
				rows = changeLights(rows,
									Integer.parseInt(line.split(" ")[1].split(",")[0]),
									Integer.parseInt(line.split(" ")[1].split(",")[1]),
									Integer.parseInt(line.split(" ")[3].split(",")[0]),
									Integer.parseInt(line.split(" ")[3].split(",")[1]),
									line.split(" ")[0]);
			} else {
				rows = changeLights(rows,
									Integer.parseInt(line.split(" ")[2].split(",")[0]),
									Integer.parseInt(line.split(" ")[2].split(",")[1]),
									Integer.parseInt(line.split(" ")[4].split(",")[0]),
									Integer.parseInt(line.split(" ")[4].split(",")[1]),
									line.split(" ")[1]);
			}
		}
		for(ArrayList<Integer> row : rows) {
			for(int light : row) {
				if(light == 1) {
					total++;
				}
			}
			System.out.println(row);
		}
		System.out.println(total);
	}
	
	public void part2() {
		int total = 0;
		ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
		for(int i = 0; i < 1000; i++) {
			ArrayList<Integer> column = new ArrayList<Integer>();
			for(int c = 0; c < 1000; c++) {
				column.add(0);
			}
			rows.add(column);
		}
		for(String line : input.split("\n")) {
			if(line.split(" ")[0].equals("toggle")) {
				rows = changeLightsPart2(rows,
									Integer.parseInt(line.split(" ")[1].split(",")[0]),
									Integer.parseInt(line.split(" ")[1].split(",")[1]),
									Integer.parseInt(line.split(" ")[3].split(",")[0]),
									Integer.parseInt(line.split(" ")[3].split(",")[1]),
									line.split(" ")[0]);
			} else {
				rows = changeLightsPart2(rows,
									Integer.parseInt(line.split(" ")[2].split(",")[0]),
									Integer.parseInt(line.split(" ")[2].split(",")[1]),
									Integer.parseInt(line.split(" ")[4].split(",")[0]),
									Integer.parseInt(line.split(" ")[4].split(",")[1]),
									line.split(" ")[1]);
			}
		}
		for(ArrayList<Integer> row : rows) {
			for(int light : row) {
				total += light;
			}
			System.out.println(row);
		}
		System.out.println(total);
	}
	
	public static ArrayList<ArrayList<Integer>> changeLights(ArrayList<ArrayList<Integer>> rows, int x1, int y1, int x2, int y2, String mode) {
		for(int i = y1; i < y2+1; i++) {
			for(int c = x1; c < x2+1; c++) {
				if(mode.equals("toggle")) {
					rows.get(i).set(c, (rows.get(i).get(c)+1)%2);
				} else if(mode.equals("on")) {
					rows.get(i).set(c, 1);
				} else {
					rows.get(i).set(c, 0);
				}
			}
		}
		return rows;
	}
	
	public static ArrayList<ArrayList<Integer>> changeLightsPart2(ArrayList<ArrayList<Integer>> rows, int x1, int y1, int x2, int y2, String mode) {
		for(int i = y1; i < y2+1; i++) {
			for(int c = x1; c < x2+1; c++) {
				if(mode.equals("toggle")) {
					rows.get(i).set(c, (rows.get(i).get(c)+2));
				} else if(mode.equals("on")) {
					rows.get(i).set(c, (rows.get(i).get(c)+1));
				} else {
					rows.get(i).set(c, (rows.get(i).get(c)-1));
					if(rows.get(i).get(c) < 0) {
						rows.get(i).set(c,0);
					}
				}
			}
		}
		return rows;
	}
}
