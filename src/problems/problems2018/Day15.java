package problems.problems2018;

import problems.Day;

import java.util.ArrayList;

public class Day15 extends Day {

    public Day15() {
        super(15, 2018);
    }

    public static char[][] map;

    public static final String TEST_INPUT = "";

    public static void part1(String input) {

    }
    public static void part2(String input) {

    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean reachable(Point target) {

        }

        public ArrayList<Point> availableAdjacentSpaces() {
            ArrayList<Point> points = new ArrayList<>();
            if (map[x-1][y] != '#') {
                points.add(new Point(x-1,y));
            }
            if (map[x+1][y] != '#') {
                points.add(new Point(x+1,y));
            }
            if (map[x][y-1] != '#') {
                points.add(new Point(x,y-1));
            }
            if (map[x][y+1] != '#') {
                points.add(new Point(x,y+1));
            }
            return points;
        }

        public ArrayList<Point> availableMinusPreviousAdjacentSpaces() {
            ArrayList<Point> points = new ArrayList<>();
            if (map[x-1][y] != '#') {
                points.add(new Point(x-1,y));
            }
            if (map[x+1][y] != '#') {
                points.add(new Point(x+1,y));
            }
            if (map[x][y-1] != '#') {
                points.add(new Point(x,y-1));
            }
            if (map[x][y+1] != '#') {
                points.add(new Point(x,y+1));
            }
            return points;
        }
    }

    public static class Unit {

        Point position;
        int attackPower, hitPoints;

        public Unit(int x, int y) {
            position = new Point(x,y);
            attackPower = 3;
            hitPoints = 200;
        }

        public void move() {

        }

        public void attack() {

        }

        public ArrayList<Point> inRange(ArrayList<Unit> units) {
            return new ArrayList<>();
        }

        public ArrayList<Point> reachable(ArrayList<Point> pointsInRange) {
            for (Point point: pointsInRange) {

            }
        }
    }

    public static class Goblin extends Unit {

        public Goblin(int x, int y) {
            super(x,y);
        }

        @Override
        public ArrayList<Point> inRange(ArrayList<Unit> units) {
            ArrayList<Point> points = new ArrayList<>();
            for (Unit elf: units) {
                if (elf instanceof Elf) {
                    points.addAll(elf.position.availableAdjacentSpaces());
                }
            }
            return points;
        }
    }

    public static class Elf extends Unit {

        public Elf(int x, int y) {
            super(x,y);
        }

        @Override
        public ArrayList<Point> inRange(ArrayList<Unit> units) {
            ArrayList<Point> points = new ArrayList<>();
            for (Unit goblin: units) {
                if (goblin instanceof Goblin) {
                    if (map[goblin.position.x-1][goblin.position.y] != '#') {
                        points.add(new Point(goblin.position.x-1,goblin.position.y));
                    }
                    if (map[goblin.position.x+1][goblin.position.y] != '#') {
                        points.add(new Point(goblin.position.x+1,goblin.position.y));
                    }
                    if (map[goblin.position.x][goblin.position.y-1] != '#') {
                        points.add(new Point(goblin.position.x,goblin.position.y-1));
                    }
                    if (map[goblin.position.x][goblin.position.y+1] != '#') {
                        points.add(new Point(goblin.position.x,goblin.position.y+1));
                    }
                }
            }
            return points;
        }
    }
}
