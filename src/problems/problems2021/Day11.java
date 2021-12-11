package problems.problems2021;

import problems.Day;

import java.util.ArrayList;

public class Day11 extends Day {

    public Day11() {
        super(11, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        int[][] map = new int[inputLines.length][inputLines[0].length()];
        for (int r = 0; r < inputLines.length; r++) {
            for (int c = 0; c < inputLines[0].length(); c++) {
                map[r][c] = Integer.parseInt(inputLines[r].charAt(c)+"");
            }
        }
        int totalFlashes = 0;
        for (int i = 0; i < 100; i++) {
            totalFlashes += simulateStep(map);
        }
        System.out.println(totalFlashes);
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        int[][] map = new int[inputLines.length][inputLines[0].length()];
        for (int r = 0; r < inputLines.length; r++) {
            for (int c = 0; c < inputLines[0].length(); c++) {
                map[r][c] = Integer.parseInt(inputLines[r].charAt(c)+"");
            }
        }
        int i = 0;
        while (!checkIfAllFlash(map)) {
            simulateStep(map);
            i++;
        }
        System.out.println(i);
    }

    public int simulateStep(int[][] beforeStep) {
        boolean[][] flashed = new boolean[beforeStep.length][beforeStep[0].length];
        int flashCount = 0;
        for (int r = 0; r < beforeStep.length; r++) {
            for (int c = 0; c < beforeStep[0].length; c++) {
                beforeStep[r][c]++;
            }
        }
        for (int r = 0; r < beforeStep.length; r++) {
            for (int c = 0; c < beforeStep[0].length; c++) {
                if (beforeStep[r][c] > 9 && !flashed[r][c]) {
                    flashCount++;
                    flashed[r][c] = true;
                    for (int[] adjacentCoordinate : getAdjacentCoordinates(r, c, beforeStep.length-1, beforeStep[0].length-1)) {
                        beforeStep[adjacentCoordinate[0]][adjacentCoordinate[1]]++;
                    }
                    r = 0;
                    c = -1;
                }
            }
        }
        for (int r = 0; r < beforeStep.length; r++) {
            for (int c = 0; c < beforeStep[0].length; c++) {
                if (flashed[r][c]) {
                    beforeStep[r][c] = 0;
                }
            }
        }
        return flashCount;
    }

    public boolean checkIfAllFlash(int[][] map) {
        for (int[] ints : map) {
            for (int integer : ints) {
                if (integer != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<int[]> getAdjacentCoordinates(int r, int c, int maxR, int maxC) {
        ArrayList<int[]> output = new ArrayList<>();
        if (r == 0) {
            if (c == 0) {
                output.add(new int[]{r+1, c});
                output.add(new int[]{r, c+1});
                output.add(new int[]{r+1, c+1});
                return output;
            } else if (c == maxC) {
                output.add(new int[]{r+1, c});
                output.add(new int[]{r, c-1});
                output.add(new int[]{r+1, c-1});
                return output;
            } else {
                output.add(new int[]{r+1, c});
                output.add(new int[]{r, c+1});
                output.add(new int[]{r+1, c+1});
                output.add(new int[]{r, c-1});
                output.add(new int[]{r+1, c-1});
                return output;
            }
        } else if (r == maxR) {
            if (c == 0) {
                output.add(new int[]{r-1, c});
                output.add(new int[]{r-1, c+1});
                output.add(new int[]{r, c+1});
                return output;
            } else if (c == maxC) {
                output.add(new int[]{r-1, c});
                output.add(new int[]{r, c-1});
                output.add(new int[]{r-1, c-1});
                return output;
            } else {
                output.add(new int[]{r-1, c});
                output.add(new int[]{r, c+1});
                output.add(new int[]{r-1, c+1});
                output.add(new int[]{r, c-1});
                output.add(new int[]{r-1, c-1});
                return output;
            }
        } else {
            if (c == 0) {
                output.add(new int[]{r+1, c});
                output.add(new int[]{r-1, c});
                output.add(new int[]{r+1, c+1});
                output.add(new int[]{r, c+1});
                output.add(new int[]{r-1, c+1});
                return output;
            } else if (c == maxC) {
                output.add(new int[]{r+1, c});
                output.add(new int[]{r-1, c});
                output.add(new int[]{r+1, c-1});
                output.add(new int[]{r, c-1});
                output.add(new int[]{r-1, c-1});
                return output;
            } else {
                output.add(new int[]{r+1, c});
                output.add(new int[]{r-1, c});
                output.add(new int[]{r+1, c+1});
                output.add(new int[]{r, c+1});
                output.add(new int[]{r-1, c+1});
                output.add(new int[]{r+1, c-1});
                output.add(new int[]{r, c-1});
                output.add(new int[]{r-1, c-1});
                return output;
            }
        }
    }
}
