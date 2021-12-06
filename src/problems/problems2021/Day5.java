package problems.problems2021;

import problems.Day;

import java.util.Arrays;

public class Day5 extends Day {

    public Day5() {
        super(5, 2021);
    }

    @Override
    public void part1() {
        final int size = 1000;
        String[] inputLines = input.split("\n");
        int[][] vents = new int[size][size];
        for (String line :
                inputLines) {
            String[] startAndEnd = line.split(" -> ");
            String start = startAndEnd[0];
            String end = startAndEnd[1];
            int startX = Integer.parseInt(start.split(",")[0]);
            int endX = Integer.parseInt(end.split(",")[0]);
            int startY = Integer.parseInt(start.split(",")[1]);
            int endY = Integer.parseInt(end.split(",")[1]);

            if (startX == endX) {
                if (startY > endY) {
                    int y = endY;
                    endY = startY;
                    startY = y;
                }
                for (int j = startY; j < endY+1; j++) {
                    vents[j][startX]++;
                }
            } else if (startY == endY) {
                if (startX > endX) {
                    int x = endX;
                    endX = startX;
                    startX = x;
                }
                for (int i = startX; i < endX+1; i++) {
                    vents[startY][i]++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (vents[i][j] >= 2) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void part2() {
        final int size = 1000;
        String[] inputLines = input.split("\n");
        int[][] vents = new int[size][size];
        for (String line :
                inputLines) {
            String[] startAndEnd = line.split(" -> ");
            String start = startAndEnd[0];
            String end = startAndEnd[1];
            int startX = Integer.parseInt(start.split(",")[0]);
            int endX = Integer.parseInt(end.split(",")[0]);
            int startY = Integer.parseInt(start.split(",")[1]);
            int endY = Integer.parseInt(end.split(",")[1]);

            if (startX == endX) {
                if (startY > endY) {
                    int y = endY;
                    endY = startY;
                    startY = y;
                }
                for (int j = startY; j < endY+1; j++) {
                    vents[j][startX]++;
                }
            } else if (startY == endY) {
                if (startX > endX) {
                    int x = endX;
                    endX = startX;
                    startX = x;
                }
                for (int i = startX; i < endX+1; i++) {
                    vents[startY][i]++;
                }
            } else {
                int xOffset = (startX - endX < 0) ? 1 : -1;
                int yOffset = (startY - endY < 0) ? 1 : -1;
                while (startX != endX) {
                    vents[startY][startX]++;
                    startX += xOffset;
                    startY += yOffset;
                }
                vents[endY][endX]++;
            }
        }
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (vents[i][j] >= 2) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
