package problems.problems2021;

import problems.Day;

import java.util.Arrays;

public class Day7 extends Day {

    public Day7() {
        super(7, 2021);
    }

    @Override
    public void part1() {
        String[] inputLine = input.split(",");
        int[] positions = new int[inputLine.length];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = Integer.parseInt(inputLine[i].trim());
        }
        Arrays.sort(positions);
        int leftSum;
        int rightSum;
        int totalCost;
        int split;
        int leastFuelCost = Integer.MAX_VALUE;
        for (int i = 0; i < positions[positions.length-1]; i++) {
            split = findFirstIndexOfEqualToOrGreater(positions, i);
            leftSum = 0;
            rightSum = 0;
            for (int j = 0; j < split; j++) {
                leftSum += positions[j];
            }
            for (int j = split; j < positions.length; j++) {
                rightSum += positions[j];
            }
            totalCost = Math.abs((i*split) - leftSum) + Math.abs(rightSum - (i*(positions.length-split)));
            if (totalCost < leastFuelCost) {
                leastFuelCost = totalCost;
            }
        }
        System.out.println(leastFuelCost);
    }

    @Override
    public void part2() {
        String[] inputLine = input.split(",");
        int[] positions = new int[inputLine.length];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = Integer.parseInt(inputLine[i].trim());
        }
        Arrays.sort(positions);
        int leftCost;
        int rightCost;
        int split;
        int leastFuelCost = Integer.MAX_VALUE;
        for (int i = 0; i < positions[positions.length-1]; i++) {
            split = findFirstIndexOfEqualToOrGreater(positions, i);
            leftCost = 0;
            rightCost = 0;
            for (int j = 0; j < split; j++) {
                leftCost += getSumOfNumbers(Math.abs(positions[j]-i));
            }
            for (int j = split; j < positions.length; j++) {
                rightCost += getSumOfNumbers(Math.abs(positions[j]-i));
            }
            if (leftCost+rightCost < leastFuelCost) {
                leastFuelCost = leftCost+rightCost;
            }
        }
        System.out.println(leastFuelCost);
    }

    public int findFirstIndexOfEqualToOrGreater(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    public int getSumOfNumbers(int end) {
        return (end * (end+1))/2;
    }
}
