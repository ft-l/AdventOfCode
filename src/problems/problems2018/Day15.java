package problems.problems2018;

import problems.Day;

import java.util.*;

public class Day15 extends Day {

    public Day15() {
        super(15, 2018);
    }

    public static Point[][] map;

    public static boolean LOST_ELVES = true;

    public static int ELF_COUNT, GOBLIN_COUNT, FULL_ROUNDS;

    public static final String TEST_INPUT_1 =   "#######\n" +
                                                "#.G...#\n" +
                                                "#...EG#\n" +
                                                "#.#.#G#\n" +
                                                "#..G#E#\n" +
                                                "#.....#\n" +
                                                "#######";

    public static final String TEST_INPUT_2 =   "#######\n" +
                                                "#G..#E#\n" +
                                                "#E#E.E#\n" +
                                                "#G.##.#\n" +
                                                "#...#E#\n" +
                                                "#...E.#\n" +
                                                "#######";

    public static final String TEST_INPUT_3 =   "#######\n" +
                                                "#E..EG#\n" +
                                                "#.#G.E#\n" +
                                                "#E.##E#\n" +
                                                "#G..#.#\n" +
                                                "#..E#.#\n" +
                                                "#######";

    public static void part1(String input) {
        String result;
        processInput(input, 3);
        while (true) {
            result = allTakeTurn(1);
            if (!result.equals("Not done")) {
                break;
            }
        }
    }

    public static void part2(String input) {
        String result = "";
        int attack = 3;
        while (LOST_ELVES) {
            processInput(input, attack);
            while (true) {
                result = allTakeTurn(2);
                if (!result.equals("Not done")) {
                    break;
                }
            }
            attack++;
        }
    }

    public static void processInput(String input, int elfAttack) {
        LOST_ELVES = false;
        ELF_COUNT = 0;
        GOBLIN_COUNT = 0;
        FULL_ROUNDS = 0;

        map = new Point[input.split("\n")[0].length()][input.split("\n").length];

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                switch (input.split("\n")[y].charAt(x)) {
                    case '#':
                        map[x][y] = new Point(x, y, true);
                        break;
                    case '.':
                        map[x][y] = new Point(x, y, false);
                        break;
                    case 'G':
                        map[x][y] = new Point(x, y, new Goblin());
                        GOBLIN_COUNT++;
                        break;
                    case 'E':
                        map[x][y] = new Point(x, y, new Elf(elfAttack));
                        ELF_COUNT++;
                        break;
                }
            }
        }
    }

    public static int getOutcome() {
        int sumOfHitpoints = 0;
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[x][y].combatant != null && map[x][y].combatant.health > 0) {
                    sumOfHitpoints += map[x][y].combatant.health;
                }
            }
        }
        return sumOfHitpoints*FULL_ROUNDS;
    }

    public static String allTakeTurn(int part) {
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[x][y].combatant != null && !map[x][y].combatant.takenTurn) {
                    map[x][y].takeTurn();
                    for (int y2 = 0; y2 < map[0].length; y2++) {
                        for (int x2 = 0; x2 < map.length; x2++) {
                            if (map[x2][y2].combatant != null && map[x2][y2].combatant.health <= 0) {
                                if (map[x2][y2].combatant instanceof Elf) {
                                    ELF_COUNT--;
                                    LOST_ELVES = true;
                                } else {
                                    GOBLIN_COUNT--;
                                }
                                if (ELF_COUNT == 0) {
                                    if (part == 1) {
                                        System.out.println("Part 1 = " + getOutcome());
                                    }
                                    return "Goblin";
                                }
                                if (GOBLIN_COUNT == 0) {
                                    if (!LOST_ELVES) {
                                        System.out.println("Part 2 = " + getOutcome());
                                    }
                                    return "Elf";
                                }
                                map[x2][y2].combatant = null;
                            }
                        }
                    }
                }
            }
        }
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[x][y].combatant != null) {
                    map[x][y].combatant.takenTurn = false;
                }
            }
        }
        FULL_ROUNDS++;
        return "Not done";
    }

    public static void printMap() {
        char[][] mapToPrint = new char[map.length][map[0].length];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (map[x][y].isWall) {
                    mapToPrint[x][y] = '#';
                } else {
                    if (map[x][y].combatant != null) {
                        if (map[x][y].combatant instanceof Goblin) {
                            mapToPrint[x][y] = 'G';
                        } else {
                            mapToPrint[x][y] = 'E';
                        }
                    } else {
                        mapToPrint[x][y] = '.';
                    }
                }
            }
        }

        for (int y = 0; y < mapToPrint[0].length; y++) {
            String combatantReport = "";
            for (int x = 0; x < mapToPrint.length; x++) {
                System.out.print(mapToPrint[x][y]);
                if (map[x][y].combatant != null) {
                    combatantReport += ", "+map[x][y].combatant;
                }
            }
            if (combatantReport.length() > 2) {
                System.out.print("    " + combatantReport.substring(2));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static class Point {

        int x, y;
        boolean isWall;
        Combatant combatant;

        public Point(int x, int y, boolean isWall) {
            this.x = x;
            this.y = y;
            this.isWall = isWall;
            this.combatant = null;
        }

        public Point(int x, int y, Combatant combatant) {
            this.x = x;
            this.y = y;
            this.isWall = false;
            this.combatant = combatant;
        }

        public boolean isTargetAdjacent(Combatant searchingCombatant) {
            for (Point adjacentPoint: getAdjacentPoints()) {
                if (adjacentPoint.combatant != null && searchingCombatant.isEnemy(adjacentPoint.combatant)) {
                    return true;
                }
            }
            return false;
        }

        public HashSet<Point> getAdjacentPoints() {
            HashSet<Point> adjacentPoints = new HashSet<>();
            adjacentPoints.add(map[x][y-1]);
            adjacentPoints.add(map[x-1][y]);
            adjacentPoints.add(map[x+1][y]);
            adjacentPoints.add(map[x][y+1]);
            return adjacentPoints;
        }

        public HashSet<Point> getAvailableAdjacentPoints() {
            HashSet<Point> availableAdjacentPoints = new HashSet<>();
            if (!map[x][y+1].isWall && map[x][y+1].combatant == null) {
                availableAdjacentPoints.add(map[x][y+1]);
            }
            if (!map[x-1][y].isWall && map[x-1][y].combatant == null) {
                availableAdjacentPoints.add(map[x-1][y]);
            }
            if (!map[x+1][y].isWall && map[x+1][y].combatant == null) {
                availableAdjacentPoints.add(map[x+1][y]);
            }
            if (!map[x][y-1].isWall && map[x][y-1].combatant == null) {
                availableAdjacentPoints.add(map[x][y-1]);
            }
            return availableAdjacentPoints;
        }

        public Point findNearestReachableTarget() {
            HashSet<Point> nearestReachableTargetOptions = new HashSet<>();
            HashSet<Point> checkedPoints = new HashSet<>();
            checkedPoints.add(this);
            HashSet<Point> currentLayer  = new HashSet<>(getAvailableAdjacentPoints());
            HashSet<Point> nextLayer     = new HashSet<>();
            while(nearestReachableTargetOptions.size() < 1 && currentLayer.size() > 0) {
                for (Point currentLayerPoint: currentLayer) {
                    if (currentLayerPoint.isTargetAdjacent(this.combatant)) {
                        nearestReachableTargetOptions.add(currentLayerPoint);
                    }
                }
                nextLayer.clear();
                for (Point currentLayerPoint: currentLayer) {
                    for (Point possibleNextLayerPoint: currentLayerPoint.getAvailableAdjacentPoints()) {
                        if (!checkedPoints.contains(possibleNextLayerPoint)) {
                            nextLayer.add(possibleNextLayerPoint);
                        }
                    }
                }
                currentLayer = new HashSet<>(nextLayer);
                checkedPoints.addAll(currentLayer);
            }
            if (nearestReachableTargetOptions.size() == 0) {
                return null;
            } else if (nearestReachableTargetOptions.size() == 1) {
                for (Point p: nearestReachableTargetOptions) {
                    return p;
                }
            } else {
                for (int y = 0; y < map[0].length; y++) {
                    for (int x = 0; x < map.length; x++) {
                        for (Point nearestReachableTargetOption: nearestReachableTargetOptions) {
                            if (nearestReachableTargetOption.equals(map[x][y])) {
                                return nearestReachableTargetOption;
                            }
                        }
                    }
                }
            }
            return null;
        }

        public Point findNextMove() {
            Point target = this.findNearestReachableTarget();

            if (target == null) {
                return null;
            } else if (contains(this.getAdjacentPoints(), target)) {
                return target;
            }

            HashSet<Point> nextStepOptions = new HashSet<>();
            HashSet<Point> checkedPoints = new HashSet<>(target.getAvailableAdjacentPoints());
            checkedPoints.add(target);
            HashSet<Point> currentLayer  = new HashSet<>(target.getAvailableAdjacentPoints());
            HashSet<Point> nextLayer     = new HashSet<>();

            while(nextStepOptions.size() < 1 && currentLayer.size() > 0) {
                for (Point currentLayerPoint: currentLayer) {
                    if (contains(this.getAdjacentPoints(), currentLayerPoint)) {
                        nextStepOptions.add(currentLayerPoint);
                    }
                }
                nextLayer.clear();
                for (Point currentLayerPoint: currentLayer) {
                    for (Point possibleNextLayerPoint: currentLayerPoint.getAvailableAdjacentPoints()) {
                        if (!checkedPoints.contains(possibleNextLayerPoint)) {
                            nextLayer.add(possibleNextLayerPoint);
                        }
                    }
                }
                currentLayer = new HashSet<>(nextLayer);
                checkedPoints.addAll(currentLayer);
            }
            if (nextStepOptions.size() == 0) {
                return null;
            } else if (nextStepOptions.size() == 1) {
                for (Point p: nextStepOptions) {
                    return p;
                }
            } else {
                for (int y = 0; y < map[0].length; y++) {
                    for (int x = 0; x < map.length; x++) {
                        for (Point nearestReachableTargetOption: nextStepOptions) {
                            if (nearestReachableTargetOption.equals(map[x][y])) {
                                return nearestReachableTargetOption;
                            }
                        }
                    }
                }
            }
            return null;
        }

        public Point getAdjacentTarget() {
            int lowestHealth = 200;
            HashSet<Point> adjacentTargets = new HashSet<>();
            for (Point adjacentPoint: this.getAdjacentPoints()) {
                if (adjacentPoint.combatant != null && this.combatant != null && this.combatant.isEnemy(adjacentPoint.combatant)) {
                    adjacentTargets.add(adjacentPoint);
                    if (lowestHealth > adjacentPoint.combatant.health) {
                        lowestHealth = adjacentPoint.combatant.health;
                    }
                }
            }
            for (int y = 0; y < map[0].length; y++) {
                for (int x = 0; x < map.length; x++) {
                    for (Point adjacentTarget: adjacentTargets) {
                        if (adjacentTarget.combatant.health == lowestHealth && adjacentTarget.equals(new Point(x,y,false))) {
                            return adjacentTarget;
                        }
                    }
                }
            }
            return null;
        }

        public static boolean contains(HashSet<Point> container, Point testCase) {
            for (Point p: container) {
                if (p.equals(testCase)) {
                    return true;
                }
            }
            return false;
        }

        public void takeTurn() {
            this.combatant.takenTurn = true;
            if (!attack()) {
                moveAndAttack();
            }
        }

        public boolean attack() {
            if (getAdjacentTarget() != null) {
                getAdjacentTarget().combatant.health -= this.combatant.attack;
                return true;
            } else {
                return false;
            }
        }

        public void moveAndAttack() {
            Point pointToMoveTo = this.findNextMove();
            if (pointToMoveTo != null) {
                pointToMoveTo.combatant = this.combatant;
                pointToMoveTo.attack();
                this.combatant = null;
            }
        }

        public boolean equals(Point p) {
            return p.x == this.x && p.y == this.y;
        }

        @Override
        public String toString() {
            return x+","+y+"  isWall="+isWall+"  combatant="+combatant;
        }
    }

    public static class Combatant {
        int health = 200;
        int attack = 3;
        boolean takenTurn = false;

        public boolean isEnemy(Combatant combatantToTest) {
            return false;
        }
    }

    public static class Elf    extends Combatant {

        public Elf(int attack) {
            this.attack = attack;
        }

        @Override
        public boolean isEnemy(Combatant combatantToTest) {
            return combatantToTest instanceof Goblin;
        }

        @Override
        public String toString() {
            return "E("+health+")";
        }
    }

    public static class Goblin extends Combatant {
        @Override
        public boolean isEnemy(Combatant combatantToTest) {
            return combatantToTest instanceof Elf;
        }
        @Override
        public String toString() {
            return "G("+health+")";
        }
    }
}