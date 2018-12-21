package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day15 extends Day {

    public Day15() {
        super(15, 2018);
    }

    public static Point[][] map;

    public static ArrayList<Unit> combatants = new ArrayList<>();

    public static final String TEST_INPUT = "#######\n" +
                                            "#E..G.#\n" +
                                            "#...#.#\n" +
                                            "#.G.#G#\n" +
                                            "#######";

    public static void part1(String input) {
        map = new Point[input.split("\n")[0].length()][input.split("\n").length];

        System.out.println(map.length);
        System.out.println(map[0].length);

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                switch (input.split("\n")[y].charAt(x)) {
                    case '#':
                        map[x][y] = new Point(x,y,true);
                        break;
                    case '.':
                        map[x][y] = new Point(x,y,false);
                        break;
                    case 'G':
                        map[x][y] = new Point(x,y,false);
                        combatants.add(new Goblin(x,y));
                        break;
                    case 'E':
                        map[x][y] = new Point(x,y,false);
                        combatants.add(new Elf(x,y));
                        break;
                }
            }
        }

        printMap();
    }
    public static void part2(String input) {

    }

    public static void printMap() {
        char[][] mapToPrint = new char[map.length][map[0].length];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (map[x][y].isWall) {
                    mapToPrint[x][y] = '#';
                } else {
                    mapToPrint[x][y] = '.';
                }
            }
        }
        for (Unit combatant: combatants) {
            if (combatant instanceof Goblin) {
                mapToPrint[combatant.position.x][combatant.position.y] = 'G';
            } else {
                mapToPrint[combatant.position.x][combatant.position.y] = 'E';
                for (Point point: combatant.position.getReachablePoints()) {
                    mapToPrint[point.x][point.y] = '?';
                }
            }
        }

        for (int y = 0; y < mapToPrint[0].length; y++) {
            for (int x = 0; x < mapToPrint.length; x++) {
                System.out.print(mapToPrint[x][y]);
            }
            System.out.println();
        }
    }

    public static class Point {

        int x, y;
        boolean isWall;

        public Point(int x, int y, boolean isWall) {
            this.x = x;
            this.y = y;
            this.isWall = isWall;
        }

        public boolean reachable(Point target) {
            return getReachablePoints().contains(target);
        }

        public HashSet<Point> getReachablePoints() {
            HashSet<Point> reachablePoints = new HashSet<>();
            reachablePoints.addAll(this.availableAdjacentSpaces());
            reachablePoints.addAll(getReachablePoints(reachablePoints));
            return reachablePoints;
        }

        public HashSet<Point> getReachablePoints(HashSet<Point> knownReachablePoints) {
            System.out.println(knownReachablePoints);
            for (Point point: knownReachablePoints) {
                if (!containsAllPoints(knownReachablePoints, point.availableAdjacentSpaces())) {
                    knownReachablePoints.addAll(point.getReachablePoints(knownReachablePoints));
                }
            }
            return knownReachablePoints;
        }

        public ArrayList<Point> availableAdjacentSpaces() {
            ArrayList<Point> points = new ArrayList<>();
            if (!map[x-1][y].isWall) {
                points.add(new Point(x-1,y, false));
            }
            if (!map[x+1][y].isWall) {
                points.add(new Point(x+1,y, false));
            }
            if (!map[x][y-1].isWall) {
                points.add(new Point(x,y-1, false));
            }
            if (!map[x][y+1].isWall) {
                points.add(new Point(x,y+1, false));
            }

            for (int i = 0; i < points.size(); i++) {
                for (Unit combatant: combatants) {
                    if (points.get(i).x == combatant.position.x && points.get(i).y == combatant.position.y) {
                        points.remove(i);
                        i--;
                        break;
                    }
                }
            }

            return points;
        }

        public boolean containsPoint(Set<Point> points, Point target) {
            for (Point point: points) {
                if (point.x == target.x && point.y == target.y) {
                    return true;
                }
            }
            return false;
        }

        public boolean containsAllPoints(Set<Point> points, ArrayList<Point> targetPoints) {
            for (Point targetPoint: points) {
                if (!containsPoint(points, targetPoint)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class Unit {

        Point position;
        int attackPower, hitPoints;

        public Unit(int x, int y) {
            position = new Point(x,y, false);
            attackPower = 3;
            hitPoints = 200;
        }

        public void move() {

        }

        public void attack() {

        }

        public ArrayList<Point> reachable(ArrayList<Point> pointsInRange) {
            for (Point point: pointsInRange) {
                if (!point.reachable(this.position)) {
                    pointsInRange.remove(point);
                }
            }
            return pointsInRange;
        }

        public ArrayList<Point> inRange(ArrayList<Unit> units) {
            ArrayList<Point> points = new ArrayList<>();
            for (Unit enemy: this.getEnemies(units)) {
                points.addAll(enemy.position.availableAdjacentSpaces());
            }
            return points;
        }

        public ArrayList<Unit> getEnemies(ArrayList<Unit> units) {
            return new ArrayList<>();
        }
    }

    public static class Goblin extends Unit {

        public Goblin(int x, int y) {
            super(x,y);
        }


        @Override
        public ArrayList<Unit> getEnemies(ArrayList<Unit> units) {
            ArrayList<Unit> enemies = new ArrayList<>();
            for (Unit elf : units) {
                if (elf instanceof Elf) {
                    enemies.add(elf);
                }
            }
            return enemies;
        }
    }

    public static class Elf extends Unit {

        public Elf(int x, int y) {
            super(x,y);
        }

        @Override
        public ArrayList<Unit> getEnemies(ArrayList<Unit> units) {
            ArrayList<Unit> enemies = new ArrayList<>();
            for (Unit goblin : units) {
                if (goblin instanceof Goblin) {
                    enemies.add(goblin);
                }
            }
            return enemies;
        }
    }
}
