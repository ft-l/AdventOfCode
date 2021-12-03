package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 extends Day {

    public Day6() {
        super(6, 2018);
    }

    public static final String TEST_INPUT = "1, 1\n" +
                                            "1, 6\n" +
                                            "8, 3\n" +
                                            "3, 4\n" +
                                            "5, 5\n" +
                                            "8, 9";

    @Override
    public void part1() {
        int[][] intGrid = new int[400][400];

        ArrayList<Point> points = new ArrayList<>();

        String pointPatternString = "(\\d+), (\\d+)";

        Pattern pointPattern = Pattern.compile(pointPatternString);

        for (int i = 0; i < input.split("\n").length; i++) {
            Matcher m = pointPattern.matcher(input.split("\n")[i]);
            m.find();
            points.add(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), (char) (i+65)));
        }

        for (int r = 0; r < intGrid.length; r++) {
            for (int c = 0; c < intGrid[0].length; c++) {
                Point point = new Point(r,c);
                intGrid[c][r] = point.closestPoint(points);
            }
        }

        int[][] grid;
        grid = eliminateInfinite(getInfiniteList(points, intGrid), intGrid);
        for (Point point: getInfiniteList(points, grid)) {
            points.remove(point);
        }

        System.out.println(getLargestArea(points, grid));
    }

    @Override
    public void part2() {
        boolean[][] grid = new boolean[400][400];

        ArrayList<Point> points = new ArrayList<>();

        String pointPatternString = "(\\d+), (\\d+)";

        Pattern pointPattern = Pattern.compile(pointPatternString);

        for (int i = 0; i < input.split("\n").length; i++) {
            Matcher m = pointPattern.matcher(input.split("\n")[i]);
            m.find();
            points.add(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), (char) (i+65)));
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                Point point = new Point(r,c);
                if(point.distanceFromAll(points) < 10000) {
                    grid[r][c] = true;
                } else {
                    grid[r][c] = false;
                }
            }
        }

        int totalArea = 0;

        for (boolean[] row: grid) {
            for (boolean point: row) {
                if(point) {
                    totalArea++;
                }
            }
        }

        System.out.println(totalArea);
    }

    public static class Point {

        int x, y;
        char marker;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, char marker) {
            this.x = x;
            this.y = y;
            this.marker = marker;
        }

        public int distanceFromPoint(Point target) {
            return Math.abs(target.x - x) + Math.abs(target.y - y);
        }

        public int closestPoint(ArrayList<Point> points) {
            int closestPointIndex = 0;
            for (int i = 0; i < points.size(); i++) {
                if(distanceFromPoint(points.get(i)) < distanceFromPoint(points.get(closestPointIndex))) {
                    closestPointIndex = i;
                }
            }
            for (int i = 0; i < points.size(); i++) {
                if(closestPointIndex != i && distanceFromPoint(points.get(i)) == distanceFromPoint(points.get(closestPointIndex))) {
                    return ((int) '.');
                }
            }
            return (int) points.get(closestPointIndex).marker;
        }

        public int distanceFromAll(ArrayList<Point> points) {
            int distance = 0;
            for (Point point: points) {
                distance += distanceFromPoint(point);
            }
            return distance;
        }
    }

    public static int[][] eliminateInfinite(ArrayList<Point> points, int[][] grid) {
        HashSet<Integer> infiniteMarkers = new HashSet<>();
        infiniteMarkers.add((int) '.');
        for (Point point: points) {
            infiniteMarkers.add((int) point.marker);
        }
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if(infiniteMarkers.contains(grid[r][c])) {
                    grid[r][c] = (int) '.';
                }
            }
        }
        return grid;
    }

    public static ArrayList<Point> getInfiniteList(ArrayList<Point> points, int[][] grid) {
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x < minX) {
                minX = points.get(i).x;
            }
            if (points.get(i).x > maxX) {
                maxX = points.get(i).x;
            }
            if (points.get(i).y < minY) {
                minY = points.get(i).y;
            }
            if (points.get(i).y > maxY) {
                maxY = points.get(i).y;
            }
        }
        HashSet<Integer> infinitePointMarkers = new HashSet<>();
        for(int c = minX; c < maxX; c++) {
            infinitePointMarkers.add(grid[minY][c]);
            infinitePointMarkers.add(grid[maxY][c]);
        }
        for (int r = minY; r < maxY; r++) {
            infinitePointMarkers.add(grid[r][minX]);
            infinitePointMarkers.add(grid[r][maxX]);
        }

        ArrayList<Point> infinitePoints = new ArrayList<>();

        for (Point point: points) {
            if(infinitePointMarkers.contains((int) point.marker)) {
                infinitePoints.add(point);
            }
        }
        return infinitePoints;
    }

    public static char[][] intGridToCharGrid(int[][] grid) {
        char[][] result = new char[grid.length][grid[0].length];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                result[r][c] = (char) grid[r][c];
            }
        }

        return result;
    }

    public static int getLargestArea(ArrayList<Point> points, int[][] grid) {
        int[] pointSizes = new int[points.size()];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                for (int i = 0; i < points.size(); i++) {
                    if(grid[r][c] == (int) points.get(i).marker) {
                        pointSizes[i]++;
                    }
                }
            }
        }
        int greatestPointArea = 0;
        for (int i = 0; i < pointSizes.length; i++) {
            if (pointSizes[i] > greatestPointArea) {
                greatestPointArea = pointSizes[i];
            }
        }
        return greatestPointArea;
    }
}
