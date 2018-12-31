package problems.problems2018;

import problems.Day;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17 extends Day {

    public static int MIN_X = Integer.MAX_VALUE;
    public static int MIN_Y = Integer.MAX_VALUE;
    public static int MAX_X = 0;
    public static int MAX_Y = 0;

    public static boolean NO_PATH_LEFT = false;

    public static GroundSpot[][] GROUND;

    public static final String TEST_INPUT = "x=495, y=2..7\n" +
                                            "y=7, x=495..501\n" +
                                            "x=501, y=3..7\n" +
                                            "x=498, y=2..4\n" +
                                            "x=506, y=1..2\n" +
                                            "x=498, y=10..13\n" +
                                            "x=504, y=10..13\n" +
                                            "y=13, x=498..504";

    public Day17() {
        super(17, 2018);
    }

    public static void part1(String input) {
        String verticalPatternString = "x=(\\d+), y=(\\d+)\\.\\.(\\d+)";
        String horizontalPatternString = "y=(\\d+), x=(\\d+)\\.\\.(\\d+)";

        Pattern verticalPattern = Pattern.compile(verticalPatternString);
        Pattern horizontalPattern = Pattern.compile(horizontalPatternString);

        for (String clayLine: input.split("\n")) {
            Matcher vMatcher = verticalPattern.matcher(clayLine);
            Matcher hMatcher = horizontalPattern.matcher(clayLine);
            if (vMatcher.find()) {
                if (Integer.parseInt(vMatcher.group(1)) < MIN_X) {
                    MIN_X = Integer.parseInt(vMatcher.group(1));
                } else if (Integer.parseInt(vMatcher.group(1)) > MAX_X) {
                    MAX_X = Integer.parseInt(vMatcher.group(1));
                }
                if (Integer.parseInt(vMatcher.group(2)) < MIN_Y) {
                    MIN_Y = Integer.parseInt(vMatcher.group(2));
                }
                if (Integer.parseInt(vMatcher.group(3)) > MAX_Y) {
                    MAX_Y = Integer.parseInt(vMatcher.group(3));
                }
            } else if (hMatcher.find()) {
                if (Integer.parseInt(hMatcher.group(1)) < MIN_Y) {
                    MIN_Y = Integer.parseInt(hMatcher.group(1));
                } else if (Integer.parseInt(hMatcher.group(1)) > MAX_Y) {
                    MAX_Y = Integer.parseInt(hMatcher.group(1));
                }
                if (Integer.parseInt(hMatcher.group(2)) < MIN_X) {
                    MIN_X = Integer.parseInt(hMatcher.group(2));
                }
                if (Integer.parseInt(hMatcher.group(3)) > MAX_X) {
                    MAX_X = Integer.parseInt(hMatcher.group(3));
                }
            }
        }

        GROUND = new GroundSpot[MAX_X-MIN_X+3][MAX_Y];

        for (int y = 0; y < GROUND[0].length; y++) {
            for (int x = 0; x < GROUND.length; x++) {
                GROUND[x][y] = new GroundSpot(false);
            }
        }

        for (String clayLine: input.split("\n")) {
            Matcher vMatcher = verticalPattern.matcher(clayLine);
            Matcher hMatcher = horizontalPattern.matcher(clayLine);
            if (vMatcher.find()) {
                for (int y = Integer.parseInt(vMatcher.group(2))-MIN_Y; y < Integer.parseInt(vMatcher.group(3))+1-MIN_Y; y++) {
                    GROUND[Integer.parseInt(vMatcher.group(1))-MIN_X+1][y].hasClay = true;
                }
            } else if (hMatcher.find()) {
                for (int x = Integer.parseInt(hMatcher.group(2))-MIN_X+1; x < Integer.parseInt(hMatcher.group(3))+2-MIN_X; x++) {
                    GROUND[x][Integer.parseInt(hMatcher.group(1))-MIN_Y].hasClay = true;
                }
            }
        }

        GROUND[500-MIN_X][0].waterInSpot = new Water();

        int tick = 0;
        while (true) {
            tick++;
            /*for (int y = 0; y < 20; y++) {
                for (int x = 494; x < 508; x++) {
                    System.out.print(GROUND[x][y]);
                }
                System.out.println();
            }
            System.out.println();*/

            GroundSpot[][] previousGround = new GroundSpot[GROUND.length][GROUND[0].length];
            for (int y = 0; y < GROUND[0].length; y++) {
                for (int x = 0; x < GROUND.length; x++) {
                    previousGround[x][y] = new GroundSpot(GROUND[x][y]);
                }
            }
            progressWater();
            if (twoDArrayEquals(previousGround, GROUND)) {
                /*for (int y = 0; y < GROUND[0].length; y++) {
                    for (int x = 0; x < GROUND.length; x++) {
                        System.out.print(GROUND[x][y]);
                    }
                    System.out.println();
                }*/
                break;
            }
        }
        int spotsWaterReaches = 0;
        for (int y = 0; y < GROUND[0].length; y++) {
            for (int x = 0; x < GROUND.length; x++) {
                if (GROUND[x][y].waterInSpot != null && !GROUND[x][y].hasClay) {
                    spotsWaterReaches++;
                }
            }
        }
        System.out.println("Part1 = "+spotsWaterReaches);
    }

    public static void part2(String input) {
        String verticalPatternString = "x=(\\d+), y=(\\d+)\\.\\.(\\d+)";
        String horizontalPatternString = "y=(\\d+), x=(\\d+)\\.\\.(\\d+)";

        Pattern verticalPattern = Pattern.compile(verticalPatternString);
        Pattern horizontalPattern = Pattern.compile(horizontalPatternString);

        for (String clayLine: input.split("\n")) {
            Matcher vMatcher = verticalPattern.matcher(clayLine);
            Matcher hMatcher = horizontalPattern.matcher(clayLine);
            if (vMatcher.find()) {
                if (Integer.parseInt(vMatcher.group(1)) < MIN_X) {
                    MIN_X = Integer.parseInt(vMatcher.group(1));
                } else if (Integer.parseInt(vMatcher.group(1)) > MAX_X) {
                    MAX_X = Integer.parseInt(vMatcher.group(1));
                }
                if (Integer.parseInt(vMatcher.group(2)) < MIN_Y) {
                    MIN_Y = Integer.parseInt(vMatcher.group(2));
                }
                if (Integer.parseInt(vMatcher.group(3)) > MAX_Y) {
                    MAX_Y = Integer.parseInt(vMatcher.group(3));
                }
            } else if (hMatcher.find()) {
                if (Integer.parseInt(hMatcher.group(1)) < MIN_Y) {
                    MIN_Y = Integer.parseInt(hMatcher.group(1));
                } else if (Integer.parseInt(hMatcher.group(1)) > MAX_Y) {
                    MAX_Y = Integer.parseInt(hMatcher.group(1));
                }
                if (Integer.parseInt(hMatcher.group(2)) < MIN_X) {
                    MIN_X = Integer.parseInt(hMatcher.group(2));
                }
                if (Integer.parseInt(hMatcher.group(3)) > MAX_X) {
                    MAX_X = Integer.parseInt(hMatcher.group(3));
                }
            }
        }

        GROUND = new GroundSpot[MAX_X-MIN_X+3][MAX_Y];

        for (int y = 0; y < GROUND[0].length; y++) {
            for (int x = 0; x < GROUND.length; x++) {
                GROUND[x][y] = new GroundSpot(false);
            }
        }

        for (String clayLine: input.split("\n")) {
            Matcher vMatcher = verticalPattern.matcher(clayLine);
            Matcher hMatcher = horizontalPattern.matcher(clayLine);
            if (vMatcher.find()) {
                for (int y = Integer.parseInt(vMatcher.group(2))-MIN_Y; y < Integer.parseInt(vMatcher.group(3))+1-MIN_Y; y++) {
                    GROUND[Integer.parseInt(vMatcher.group(1))-MIN_X+1][y].hasClay = true;
                }
            } else if (hMatcher.find()) {
                for (int x = Integer.parseInt(hMatcher.group(2))-MIN_X+1; x < Integer.parseInt(hMatcher.group(3))+2-MIN_X; x++) {
                    GROUND[x][Integer.parseInt(hMatcher.group(1))-MIN_Y].hasClay = true;
                }
            }
        }

        GROUND[500-MIN_X][0].waterInSpot = new Water();

        int tick = 0;
        while (true) {
            tick++;
            /*for (int y = 0; y < 20; y++) {
                for (int x = 494; x < 508; x++) {
                    System.out.print(GROUND[x][y]);
                }
                System.out.println();
            }
            System.out.println();*/

            GroundSpot[][] previousGround = new GroundSpot[GROUND.length][GROUND[0].length];
            for (int y = 0; y < GROUND[0].length; y++) {
                for (int x = 0; x < GROUND.length; x++) {
                    previousGround[x][y] = new GroundSpot(GROUND[x][y]);
                }
            }
            progressWater();
            if (twoDArrayEquals(previousGround, GROUND)) {
                /*for (int y = 0; y < GROUND[0].length; y++) {
                    for (int x = 0; x < GROUND.length; x++) {
                        System.out.print(GROUND[x][y]);
                    }
                    System.out.println();
                }*/
                break;
            }
        }
        int spotsWaterReaches = 0;
        for (int y = 0; y < GROUND[0].length; y++) {
            for (int x = 0; x < GROUND.length; x++) {
                if (GROUND[x][y].waterInSpot != null && !GROUND[x][y].hasClay && GROUND[x][y].waterInSpot.resting) {
                    spotsWaterReaches++;
                }
            }
        }
        System.out.println("Part2 = "+spotsWaterReaches);
    }

    public static boolean twoDArrayEquals(GroundSpot[][] array1, GroundSpot[][] array2) {
        for (int y = 0; y < array1[0].length; y++) {
            for (int x = 0; x < array1.length; x++) {
                if (!array1[x][y].equals(array2[x][y])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void progressWater() {
        for (int y = MAX_Y-MIN_Y-1; y >= 0; y--) {
            for (int x = 0; x < GROUND.length; x++) {
                if (GROUND[x][y].waterInSpot != null && !GROUND[x][y].waterInSpot.resting) {
                    if (!GROUND[x][y+1].hasClay && (GROUND[x][y+1].waterInSpot == null || !GROUND[x][y+1].waterInSpot.resting)) {
                        GROUND[x][y+1].waterInSpot = new Water();
                    } else {
                        boolean spillingOffSide = false;
                        int leftMove = 0;
                        while (!GROUND[x-leftMove][y].hasClay) {
                            leftMove++;
                            if (x-leftMove < 0 || !(GROUND[x-leftMove][y+1].hasClay || (GROUND[x-leftMove][y+1].waterInSpot != null && GROUND[x-leftMove][y+1].waterInSpot.resting && !GROUND[x-leftMove][y+1].waterInSpot.spillingOffSides))) {
                                spillingOffSide = true;
                                leftMove++;
                                break;
                            }
                        }
                        leftMove--;
                        leftMove *= -1;
                        int rightMove = 0;
                        while (!GROUND[x+rightMove][y].hasClay) {
                            rightMove++;
                            if (x+rightMove >= GROUND.length || !(GROUND[x+rightMove][y+1].hasClay || (GROUND[x+rightMove][y+1].waterInSpot != null && GROUND[x+rightMove][y+1].waterInSpot.resting && !GROUND[x+rightMove][y+1].waterInSpot.spillingOffSides))) {
                                spillingOffSide = true;
                                rightMove++;
                                break;
                            }
                        }
                        for (int i = leftMove; i < rightMove; i++) {
                            GROUND[x+i][y].waterInSpot = new Water();
                            if (!spillingOffSide) {
                                GROUND[x + i][y].waterInSpot.resting = true;
                            } else {
                                GROUND[x + i][y].waterInSpot.spillingOffSides = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public static class GroundSpot {

        boolean hasClay;
        Water waterInSpot;

        public GroundSpot(boolean hasClay) {
            this.hasClay = hasClay;
            waterInSpot = null;
        }

        public GroundSpot(GroundSpot previousGroundSpot) {
            this.hasClay = previousGroundSpot.hasClay;
            if (previousGroundSpot.waterInSpot != null) {
                this.waterInSpot = new Water(previousGroundSpot.waterInSpot);
            } else {
                this.waterInSpot = null;
            }
        }

        public boolean equals(GroundSpot obj) {
            if (this.waterInSpot != null) {
                return this.hasClay == obj.hasClay && this.waterInSpot.equals(obj.waterInSpot);
            } else {
                return this.hasClay == obj.hasClay && obj.waterInSpot == null;
            }
        }

        @Override
        public String toString() {
            if (hasClay) {
                return "#";
            } else {
                if (waterInSpot != null) {
                    if (waterInSpot.resting) {
                        return "~";
                    } else {
                        return "|";
                    }
                } else {
                    return ".";
                }
            }
        }
    }

    public static class Water {

        boolean resting, spillingOffSides;

        public Water() {
            resting = false;
        }

        public Water(Water previousWater) {
            this.resting = previousWater.resting;
            this.spillingOffSides = previousWater.spillingOffSides;
        }

        public boolean equals(Water obj) {
            return obj != null && this.resting == obj.resting && this.spillingOffSides == obj.spillingOffSides;
        }
    }
}
