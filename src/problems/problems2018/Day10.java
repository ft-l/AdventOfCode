package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 extends Day {

    public static String TEST_INPUT =   "position=< 9,  1> velocity=< 0,  2>\n" +
                                        "position=< 7,  0> velocity=<-1,  0>\n" +
                                        "position=< 3, -2> velocity=<-1,  1>\n" +
                                        "position=< 6, 10> velocity=<-2, -1>\n" +
                                        "position=< 2, -4> velocity=< 2,  2>\n" +
                                        "position=<-6, 10> velocity=< 2, -2>\n" +
                                        "position=< 1,  8> velocity=< 1, -1>\n" +
                                        "position=< 1,  7> velocity=< 1,  0>\n" +
                                        "position=<-3, 11> velocity=< 1, -2>\n" +
                                        "position=< 7,  6> velocity=<-1, -1>\n" +
                                        "position=<-2,  3> velocity=< 1,  0>\n" +
                                        "position=<-4,  3> velocity=< 2,  0>\n" +
                                        "position=<10, -3> velocity=<-1,  1>\n" +
                                        "position=< 5, 11> velocity=< 1, -2>\n" +
                                        "position=< 4,  7> velocity=< 0, -1>\n" +
                                        "position=< 8, -2> velocity=< 0,  1>\n" +
                                        "position=<15,  0> velocity=<-2,  0>\n" +
                                        "position=< 1,  6> velocity=< 1,  0>\n" +
                                        "position=< 8,  9> velocity=< 0, -1>\n" +
                                        "position=< 3,  3> velocity=<-1,  1>\n" +
                                        "position=< 0,  5> velocity=< 0, -1>\n" +
                                        "position=<-2,  2> velocity=< 2,  0>\n" +
                                        "position=< 5, -2> velocity=< 1,  2>\n" +
                                        "position=< 1,  4> velocity=< 2,  1>\n" +
                                        "position=<-2,  7> velocity=< 2, -2>\n" +
                                        "position=< 3,  6> velocity=<-1, -1>\n" +
                                        "position=< 5,  0> velocity=< 1,  0>\n" +
                                        "position=<-6,  0> velocity=< 2,  0>\n" +
                                        "position=< 5,  9> velocity=< 1, -2>\n" +
                                        "position=<14,  7> velocity=<-2,  0>\n" +
                                        "position=<-3,  6> velocity=< 2, -1>";

    public Day10() {
        super(10, 2018);
    }

    public static void part1(String input) {
        String starPatternString = "position=< ?(-?\\d+),  ?(-?\\d+)> velocity=< ?(-?\\d+),  ?(-?\\d+)>";
        Pattern starProcessingPattern = Pattern.compile(starPatternString);

        ArrayList<Star> stars = new ArrayList<>();

        for (String line: input.split("\n")) {
            Matcher m = starProcessingPattern.matcher(line);
            m.find();
            stars.add(new Star(Integer.parseInt(m.group(1)),
                               Integer.parseInt(m.group(2)),
                               Integer.parseInt(m.group(3)),
                               Integer.parseInt(m.group(4))));
        }

        int approxSecond = 10000;
        int previousSize = 0;
        int size = Integer.MAX_VALUE;

        int minX, maxX, minY, maxY;

        for (int i = 0; i < approxSecond; i++) {
            for (Star star: stars) {
                star.move();
            }
        }

        do {
            previousSize = size;

            minX = Integer.MAX_VALUE;
            maxX = Integer.MIN_VALUE;
            minY = Integer.MAX_VALUE;
            maxY = Integer.MIN_VALUE;

            for (Star star: stars) {
                if (star.positionX < minX) {
                    minX = star.positionX;
                }
                if (star.positionX > maxX) {
                    maxX = star.positionX;
                }
                if (star.positionY < minY) {
                    minY = star.positionY;
                }
                if (star.positionY > maxY) {
                    maxY = star.positionY;
                }
            }

            size = (Math.abs(minY-maxY)+1)*(Math.abs(minX-maxX)+1);

            for (Star star: stars) {
                star.move();
            }
            approxSecond++;
        } while(previousSize > size);

        for (int i = 0; i < 2; i++) {
            approxSecond--;
            for (Star star: stars) {
                star.moveBack();
            }
        }
        printStars(stars);
    }

    public static void part2(String input) {
        String starPatternString = "position=< ?(-?\\d+),  ?(-?\\d+)> velocity=< ?(-?\\d+),  ?(-?\\d+)>";
        Pattern starProcessingPattern = Pattern.compile(starPatternString);

        ArrayList<Star> stars = new ArrayList<>();

        for (String line: input.split("\n")) {
            Matcher m = starProcessingPattern.matcher(line);
            m.find();
            stars.add(new Star(Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)),
                    Integer.parseInt(m.group(4))));
        }

        int approxSecond = 10000;
        int previousSize = 0;
        int size = Integer.MAX_VALUE;

        int minX, maxX, minY, maxY;

        for (int i = 0; i < approxSecond; i++) {
            for (Star star: stars) {
                star.move();
            }
        }

        do {
            previousSize = size;

            minX = Integer.MAX_VALUE;
            maxX = Integer.MIN_VALUE;
            minY = Integer.MAX_VALUE;
            maxY = Integer.MIN_VALUE;

            for (Star star: stars) {
                if (star.positionX < minX) {
                    minX = star.positionX;
                }
                if (star.positionX > maxX) {
                    maxX = star.positionX;
                }
                if (star.positionY < minY) {
                    minY = star.positionY;
                }
                if (star.positionY > maxY) {
                    maxY = star.positionY;
                }
            }

            size = (Math.abs(minY-maxY)+1)*(Math.abs(minX-maxX)+1);

            for (Star star: stars) {
                star.move();
            }
            approxSecond++;
        } while(previousSize > size);

        for (int i = 0; i < 2; i++) {
            approxSecond--;
            for (Star star: stars) {
                star.moveBack();
            }
        }
        System.out.println(approxSecond);
    }

    public static void printStars(ArrayList<Star> stars) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Star star: stars) {
            if (star.positionX < minX) {
                minX = star.positionX;
            }
            if (star.positionX > maxX) {
                maxX = star.positionX;
            }
            if (star.positionY < minY) {
                minY = star.positionY;
            }
            if (star.positionY > maxY) {
                maxY = star.positionY;
            }
        }

        char[][] starMap = new char[Math.abs(minY-maxY)+1][Math.abs(minX-maxX)+1];

        for (int r = 0; r < starMap.length; r++) {
            for (int c = 0; c < starMap[0].length; c++) {
                starMap[r][c] = '.';
            }
        }

        for (Star star: stars) {
            starMap[star.positionY-minY][star.positionX-minX] = '#';
        }

        for (char[] row: starMap) {
            String rowString = "";
            for (char c: row) {
                rowString += c;
            }
            System.out.println(rowString);
        }
    }

    public static class Star {

        int positionX, positionY, velocityX, velocityY;

        public Star(int positionX, int positionY, int velocityX, int velocityY) {
            this.positionX = positionX;
            this.positionY = positionY;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
        }

        public void move() {
            positionX += velocityX;
            positionY += velocityY;
        }

        public void moveBack() {
            positionX -= velocityX;
            positionY -= velocityY;
        }
    }
}
