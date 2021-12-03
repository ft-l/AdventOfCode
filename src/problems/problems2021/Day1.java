package problems.problems2021;

import problems.Day;

public class Day1 extends Day {

    public Day1() {
        super(1, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        int[] levels = new int[inputLines.length];
        for (int i = 0; i < inputLines.length; i++) {
            levels[i] = Integer.parseInt(inputLines[i]);
        }
        int count = 0;
        for (int i = 0; i < levels.length-1; i++) {
            if (levels[i] < levels[i+1]) {
                count++;
            }
        }
        System.out.println(count);
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        int[] levels = new int[inputLines.length];
        for (int i = 0; i < inputLines.length; i++) {
            levels[i] = Integer.parseInt(inputLines[i]);
        }
        int count = 0;
        int currentSum = 0;
        int nextSum = levels[0] + levels[1] + levels[2];
        for (int i = 1; i < levels.length-2; i++) {
            currentSum = nextSum;
            nextSum = levels[i] + levels[i+1] + levels[i+2];
            if (currentSum < nextSum) {
                count++;
            }
        }
        System.out.println(count);
    }
}
