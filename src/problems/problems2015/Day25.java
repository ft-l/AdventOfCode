package problems.problems2015;

public class Day25 {
	public Day25() {
		
	}
	public static void main(String[] args) {
		Day25 day = new Day25();
		day.part1();
	}
	public void part1() {
		System.out.println(getValueAt(getPositionCode(2978,3083)));
	}
	public void part2() {
		
	}
	public static long getNextValue(long previous) {
		return (previous*252533)%33554393;
	}
	public static long getPositionCode(int row, int column) {
		int code = 1;
		int currentRow = 1;
		int currentColumn = 1;
		int mostRecentRow = 1;
		while(currentRow != row || currentColumn != column) {
			if(currentRow == 1) {
				mostRecentRow++;
				currentRow = mostRecentRow;
				currentColumn = 1;
				code++;
			} else {
				currentColumn++;
				currentRow--;
				code++;
			}
		}
		return code;
	}
	public static long getValueAt(long code) {
		long value = 20151125;
		for(int i = 1; i < code; i++) {
			value = getNextValue(value);
		}
		return value;
	}
}
