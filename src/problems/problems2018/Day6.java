package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static void part1(String input) {
        char[][] grid = new char[10][10];

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
                int result = point.closestPoint(points);
                grid[c][r] = (char) (result+32);
            }
        }
        for (Point point: points) {
            grid[point.y][point.x] = point.marker;
        }

        for (char[] row: grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void part2(String input) {

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
                    return ((int) '.')-32;
                }
            }
            return (int) points.get(closestPointIndex).marker;
        }
    }
}
