package problems.problems2018;

import problems.Day;

public class Day11 extends Day {

    public Day11() {
        super(11, 2018);
    }

    public static final String TEST_INPUT = "18";

    @Override
    public void part1() {
        int serial = Integer.parseInt(input.substring(0,input.length()-1));
        int[][] grid = new int[300][300];
        for (int r = 0; r < 300; r++) {
            for (int c = 0; c < 300; c++) {
                grid[r][c] = calculatePowerLevel(r,c,serial);
            }
        }
        System.out.println(greatestSquare(grid,3)[0]+","+greatestSquare(grid,3)[1]);
    }

    @Override
    public void part2() {
        int serial = Integer.parseInt(input.substring(0,input.length()-1));
        int[][] grid = new int[300][300];
        for (int r = 0; r < 300; r++) {
            for (int c = 0; c < 300; c++) {
                grid[r][c] = calculatePowerLevel(r,c,serial);
            }
        }
        System.out.println(greatestSquare(grid)[0]+","+greatestSquare(grid)[1]+","+greatestSquare(grid)[2]);
    }

    public static int calculatePowerLevel(int x, int y, int serialNumber) {
        int rackId = x+10;
        int powerLevel = rackId*y;
        powerLevel += serialNumber;
        powerLevel *= rackId;
        if (powerLevel > 99) {
            powerLevel = Integer.parseInt(""+(""+powerLevel).charAt((""+powerLevel).length()-3));
        } else {
            powerLevel = 0;
        }
        return powerLevel-5;
    }

    public static int[] greatestSquare(int[][] grid) {
        int x = 0;
        int y = 0;
        int size = 0;
        int greatestSquareValue = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                for (int s = 1; s < grid.length; s++) {
                    if (squareValue(r, c, s, grid) > greatestSquareValue) {
                        greatestSquareValue = squareValue(r, c, s, grid);
                        x = r;
                        y = c;
                        size = s;
                    }
                }
            }
        }
        int[] result = {x,y,size};
        return result;
    }

    public static int[] greatestSquare(int[][] grid, int size) {
        int x = 0;
        int y = 0;
        int greatestSquareValue = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (squareValue(r, c, size, grid) > greatestSquareValue) {
                    greatestSquareValue = squareValue(r, c, size, grid);
                    x = r;
                    y = c;
                }
            }
        }
        int[] result = {x,y,size};
        return result;
    }

    public static int squareValue(int x, int y, int size, int[][] grid) {
        int value = 0;
        if (x+size >= grid.length || y+size >= grid.length) {
            return -1;
        }
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                value += grid[x+r][y+c];
            }
        }
        return value;
    }
}
