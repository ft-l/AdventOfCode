package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day13 extends Day {

    public Day13() {
        super(13, 2018);
    }

    public static final String TEST_INPUT = "/->-\\        \n" +
                                            "|   |  /----\\\n" +
                                            "| /-+--+-\\  |\n" +
                                            "| | |  | v  |\n" +
                                            "\\-+-/  \\-+--/\n" +
                                            "  \\------/   ";

    public static final String TEST_INPUT_2 = "/>-<\\  \n" +
                                              "|   |  \n" +
                                              "| /<+-\\\n" +
                                              "| | | v\n" +
                                              "\\>+</ |\n" +
                                              "  |   ^\n" +
                                              "  \\<->/";

    public static void part1(String input) {
        int widestPoint = 0;
        for (String line: input.split("\n")) {
            if (line.length() > widestPoint) {
                widestPoint = line.length();
            }
        }

        char[][] map = new char[widestPoint][input.split("\n").length];
        //                      map.length   map[0].input
        //                          r              c

        ArrayList<Cart> carts = new ArrayList<>();

        for (int c = 0; c < map[0].length; c++) {
            for (int r = 0; r < input.split("\n")[c].length(); r++) {
                map[r][c] = input.split("\n")[c].charAt(r);
                switch (map[r][c]) {
                    case '^':
                        carts.add(new Cart(Direction.UP, r, c));
                        map[r][c] = '.';
                        break;
                    case 'v':
                        carts.add(new Cart(Direction.DOWN, r, c));
                        map[r][c] = '.';
                        break;
                    case '<':
                        carts.add(new Cart(Direction.LEFT, r, c));
                        map[r][c] = '.';
                        break;
                    case '>':
                        carts.add(new Cart(Direction.RIGHT, r, c));
                        map[r][c] = '.';
                        break;
                }
            }
        }

        boolean collisionOccured = false;
        while (!collisionOccured) {

            for (Cart cart: carts) {
                cart.move(map);
            }

            for (int c1 = 0; c1 < carts.size(); c1++) {
                for (int c2 = c1+1; c2 < carts.size(); c2++) {
                    if (carts.get(c1).r == carts.get(c2).r && carts.get(c1).c == carts.get(c2).c) {
                        System.out.println(carts.get(c1).r + "," + carts.get(c1).c);
                        collisionOccured = true;
                    }
                }
            }
        }
    }

    public static void part2(String input) {
        int widestPoint = 0;
        for (String line: input.split("\n")) {
            if (line.length() > widestPoint) {
                widestPoint = line.length();
            }
        }

        char[][] map = new char[widestPoint][input.split("\n").length];
        //                      map.length   map[0].input
        //                          r              c

        ArrayList<Cart> carts = new ArrayList<>();

        for (int c = 0; c < map[0].length; c++) {
            for (int r = 0; r < input.split("\n")[c].length(); r++) {
                map[r][c] = input.split("\n")[c].charAt(r);
                switch (map[r][c]) {
                    case '^':
                        carts.add(new Cart(Direction.UP, r, c));
                        map[r][c] = '.';
                        break;
                    case 'v':
                        carts.add(new Cart(Direction.DOWN, r, c));
                        map[r][c] = '.';
                        break;
                    case '<':
                        carts.add(new Cart(Direction.LEFT, r, c));
                        map[r][c] = '.';
                        break;
                    case '>':
                        carts.add(new Cart(Direction.RIGHT, r, c));
                        map[r][c] = '.';
                        break;
                }
            }
        }

        while (carts.size() > 1) {

            for (int c = 0; c < carts.size(); c++) {
                carts.get(c).move(map);
                boolean foundCollision = true;
                while(foundCollision) {
                    foundCollision = false;
                    for (int c1 = 0; c1 < carts.size(); c1++) {
                        for (int c2 = c1 + 1; c2 < carts.size(); c2++) {
                            if (carts.get(c1).r == carts.get(c2).r && carts.get(c1).c == carts.get(c2).c) {
                                carts.remove(c1);
                                carts.remove(c2 - 1);
                                c--;
                                foundCollision = true;
                            }
                        }
                    }
                }
            }
            printMapWithCarts(map, carts);
        }

        System.out.println(carts.get(0).r + "," + carts.get(0).c);
    }

    public static void printMapWithCarts(char[][] map, ArrayList<Cart> carts) {

        char[][] mapToPrint = new char[map.length][map[0].length];

        for (int c = 0; c < map[0].length; c++) {
            for (int r = 0; r < map.length; r++) {
                mapToPrint[r][c] = map[r][c];
            }
        }

        for (Cart cart: carts) {
            switch (cart.movementDirection) {
                case UP:
                    mapToPrint[cart.r][cart.c] = '^';
                    break;
                case DOWN:
                    mapToPrint[cart.r][cart.c] = 'v';
                    break;
                case LEFT:
                    mapToPrint[cart.r][cart.c] = '<';
                    break;
                case RIGHT:
                    mapToPrint[cart.r][cart.c] = '>';
                    break;
            }
        }

        for (int c = 0; c < map[0].length; c++) {
            for (int r = 0; r < map.length; r++) {
                System.out.print(mapToPrint[r][c]);
            }
            System.out.println();
        }
    }

    public static class Cart {

        Direction movementDirection, turnDirection;
        int r,c;

        public Cart(Direction movementDirection, int r, int c) {
            this.movementDirection = movementDirection;
            this.r = r;
            this.c = c;
            turnDirection = Direction.LEFT;
        }

        public void move(char[][] map) {
            switch (movementDirection) {
                case DOWN:
                    c++;
                    break;
                case UP:
                    c--;
                    break;
                case LEFT:
                    r--;
                    break;
                case RIGHT:
                    r++;
                    break;
            }
            checkForCurve(map);
            checkForCrossRoads(map);
        }

        public void moveBack(char[][] map) {
            switch (movementDirection) {
                case DOWN:
                    c--;
                    break;
                case UP:
                    c++;
                    break;
                case LEFT:
                    r++;
                    break;
                case RIGHT:
                    r--;
                    break;
            }
        }

        public void checkForCurve(char[][] map) {
            switch (map[r][c]) {
                case '\\':
                    switch (movementDirection) {
                        case RIGHT:
                            movementDirection = Direction.DOWN;
                            break;
                        case LEFT:
                            movementDirection = Direction.UP;
                            break;
                        case UP:
                            movementDirection = Direction.LEFT;
                            break;
                        case DOWN:
                            movementDirection = Direction.RIGHT;
                            break;
                    }
                    break;
                case '/':
                    switch (movementDirection) {
                        case RIGHT:
                            movementDirection = Direction.UP;
                            break;
                        case LEFT:
                            movementDirection = Direction.DOWN;
                            break;
                        case UP:
                            movementDirection = Direction.RIGHT;
                            break;
                        case DOWN:
                            movementDirection = Direction.LEFT;
                            break;
                    }
                    break;
            }
        }

        public void checkForCrossRoads(char[][] map) {
            if (map[r][c] == '+') {
                switch (turnDirection) {
                    case LEFT:
                        switch (movementDirection) {
                            case RIGHT:
                                movementDirection = Direction.UP;
                                break;
                            case LEFT:
                                movementDirection = Direction.DOWN;
                                break;
                            case UP:
                                movementDirection = Direction.LEFT;
                                break;
                            case DOWN:
                                movementDirection = Direction.RIGHT;
                                break;
                        }
                        turnDirection = Direction.STRAIGHT;
                        break;
                    case STRAIGHT:
                        turnDirection = Direction.RIGHT;
                        break;
                    case RIGHT:
                        switch (movementDirection) {
                            case RIGHT:
                                movementDirection = Direction.DOWN;
                                break;
                            case LEFT:
                                movementDirection = Direction.UP;
                                break;
                            case UP:
                                movementDirection = Direction.RIGHT;
                                break;
                            case DOWN:
                                movementDirection = Direction.LEFT;
                                break;
                        }
                        turnDirection = Direction.LEFT;
                        break;
                }
            }
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, STRAIGHT
    }
}
