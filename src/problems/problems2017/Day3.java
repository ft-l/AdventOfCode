package problems.problems2017;

import problems.Day;

import java.util.ArrayList;

public class Day3 extends Day {
	public Day3() {
		super(3, 2017);
	}
	public static void main(String[] args) {
		Day3 day = new Day3();
		day.part1();
		day.part2();
	}
	public void part1() {
		int code = 289326;
		int counter = 1;
		int rowWidth = 1;
		boolean direction = true;
		int x = 0;
		int y = 0;
		while(counter + rowWidth*2 < code) {
			if(direction) {
				x += rowWidth;
				y += rowWidth;
			} else {
				x -= rowWidth;
				y -= rowWidth;
			}
			direction = !direction;
			counter += rowWidth*2;
			rowWidth++;
			System.out.println(counter + " " + rowWidth);
			System.out.println(x + " " + y);
		}
		if(x > 0) {
			x = x - (code - counter);
		} else {
			x = x + (code - counter);
		}
		System.out.println(Math.abs(x) + Math.abs(y));
		//System.out.println(code - counter);
		//System.out.println(rowWidth);
	}
	public void part2() {
		int code = 289326;
		int recent = 0;
		int rowWidth = 1;
		int x = 26;
		int y = 26;
		boolean direction = false;
		ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 51; i++) {
			grid.add(new ArrayList<Integer>());
			for(int c = 0; c < 51; c++) {
				grid.get(i).add(0);
			}
		}
		grid.get(x).set(y, 1);
		while(recent < code) {
			for(int i = 0; i < rowWidth; i++) {
				if(direction) {
					y++;
				} else {
					y--;
				}
				grid.get(x).set(y,
								grid.get(x).get(y+1)
								+grid.get(x).get(y-1)
								+grid.get(x-1).get(y)
								+grid.get(x-1).get(y+1)
								+grid.get(x-1).get(y-1)
								+grid.get(x+1).get(y)
								+grid.get(x+1).get(y+1)
								+grid.get(x+1).get(y-1));
				recent = grid.get(x).get(y);
				System.out.println(recent);
				if(recent > code) {
					break;
				}
			}
			for(int i = 0; i < rowWidth; i++) {
				if(direction) {
					x++;
				} else {
					x--;
				}
				grid.get(x).set(y,
						grid.get(x).get(y+1)
						+grid.get(x).get(y-1)
						+grid.get(x-1).get(y)
						+grid.get(x-1).get(y+1)
						+grid.get(x-1).get(y-1)
						+grid.get(x+1).get(y)
						+grid.get(x+1).get(y+1)
						+grid.get(x+1).get(y-1));
				recent = grid.get(x).get(y);
				System.out.println(recent);
				if(recent > code) {
					break;
				}
			}
			direction = !direction;
			rowWidth++;
		}
		System.out.println(recent + " " + x + " " + y);
		for(ArrayList<Integer> line : grid) {
			for(int i : line) {
				System.out.printf("%9d",i);
			}
			System.out.println();
		}
	}
}
