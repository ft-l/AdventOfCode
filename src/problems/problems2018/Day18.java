package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.HashSet;

public class Day18 extends Day {

    public static final String TEST_INPUT = ".#.#...|#.\n" +
                                            ".....#|##|\n" +
                                            ".|..|...#.\n" +
                                            "..|#.....#\n" +
                                            "#.#|||#|#|\n" +
                                            "...#.||...\n" +
                                            ".|....|...\n" +
                                            "||...#|.#|\n" +
                                            "|.||||..|.\n" +
                                            "...#.|..|.";

    public static final int REPEATS_AT = 700000;

    public Day18() {
        super(18, 2018);
    }

    @Override
    public void part1() {
        char[][] area = new char[input.split("\n").length][input.split("\n")[0].length()];

        for (int y = 0; y < area[0].length; y++) {
            for (int x = 0; x < area.length; x++) {
                area[x][y] = input.split("\n")[y].charAt(x);
            }
        }
        for (int i = 0; i < 10; i++) {
            area = advanceAreaOneMinute(area);
        }

        System.out.println("Part1 = " + getResourceValue(area));
    }

    @Override
    public void part2() {
        char[][] area = new char[input.split("\n").length][input.split("\n")[0].length()];

        for (int y = 0; y < area[0].length; y++) {
            for (int x = 0; x < area.length; x++) {
                area[x][y] = input.split("\n")[y].charAt(x);
            }
        }
        for (int i = 0; i < 1000000000%REPEATS_AT; i++) {
            area = advanceAreaOneMinute(area);
        }

        System.out.println("Part2 = " + getResourceValue(area));
    }

    public static int getResourceValue(char[][] area) {
        int wood = 0;
        int lumber = 0;
        for (int y = 0; y < area[0].length; y++) {
            for (int x = 0; x < area.length; x++) {
                switch (area[x][y]) {
                    case '|':
                        wood++;
                        break;
                    case '#':
                        lumber++;
                        break;
                }
            }
        }
        return wood*lumber;
    }

    public static char[][] advanceAreaOneMinute(char[][] area) {
        char[][] newArea = new char[area.length][area[0].length];

        for (int y = 0; y < area[0].length; y++) {
            for (int x = 0; x < area.length; x++) {
                int adjacentOpen = 0;
                int adjacentWooded = 0;
                int adjacentLumberyard = 0;
                for (int h = 0; h < 3; h++) {
                    for (int w = 0; w < 3; w++) {
                        if (y-1+h >= 0 && x-1+w >= 0 && y-1+h < area.length && x-1+w < area.length && !(h-1 == 0 && w-1 == 0)) {
                            switch (area[x-1+w][y-1+h]) {
                                case '.':
                                    adjacentOpen++;
                                    break;
                                case '|':
                                    adjacentWooded++;
                                    break;
                                case '#':
                                    adjacentLumberyard++;
                                    break;
                            }
                        }
                    }
                }
                switch (area[x][y]) {
                    case '.':
                        if (adjacentWooded >= 3) {
                            newArea[x][y] = '|';
                        } else {
                            newArea[x][y] = '.';
                        }
                        break;
                    case '|':
                        if (adjacentLumberyard >= 3) {
                            newArea[x][y] = '#';
                        } else {
                            newArea[x][y] = '|';
                        }
                        break;
                    case '#':
                        if (adjacentLumberyard >= 1 && adjacentWooded >= 1) {
                            newArea[x][y] = '#';
                        } else {
                            newArea[x][y] = '.';
                        }
                        break;
                }
            }
        }

        return newArea;
    }
}
