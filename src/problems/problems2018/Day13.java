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
    public static final String TEST_INPUT_2 = "/---\\  \n" +
                                              "^   ^  \n" +
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

        ArrayList<Cart> carts = new ArrayList<>();

        for (int y = 0; y < input.split("\n").length; y++) {
            for (int x = 0; x < input.split("\n")[y].length(); x++) {
                if ("v><^".contains(""+input.split("\n")[y].charAt(x))) {
                    switch (input.split("\n")[y].charAt(x)) {
                        case 'v':
                            carts.add(new Cart(Direction.DOWN, x, y));
                            break;
                        case '^':
                            carts.add(new Cart(Direction.UP, x, y));
                            break;
                        case '<':
                            carts.add(new Cart(Direction.LEFT, x, y));
                            break;
                        case '>':
                            carts.add(new Cart(Direction.RIGHT, x, y));
                            break;
                    }
                    map[x][y] = '.';
                } else {
                    map[x][y] = input.split("\n")[y].charAt(x);
                }
            }
        }
        //printMapWithCarts(map, carts);
        boolean collision = false;
        while(!collision) {
            for (Cart cart: carts) {
                cart.move(map);
            }
            //printMapWithCarts(map, carts);
            for (int i = 0; i < carts.size(); i++) {
                for (int j = i + 1; j < carts.size(); j++) {
                    if (carts.get(i).x == carts.get(j).x && carts.get(i).y == carts.get(j).y) {
                        System.out.println(carts.get(i).position());
                        collision = true;
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

        ArrayList<Cart> carts = new ArrayList<>();

        for (int y = 0; y < input.split("\n").length; y++) {
            for (int x = 0; x < input.split("\n")[y].length(); x++) {
                if ("v><^".contains(""+input.split("\n")[y].charAt(x))) {
                    switch (input.split("\n")[y].charAt(x)) {
                        case 'v':
                            carts.add(new Cart(Direction.DOWN, x, y));
                            break;
                        case '^':
                            carts.add(new Cart(Direction.UP, x, y));
                            break;
                        case '<':
                            carts.add(new Cart(Direction.LEFT, x, y));
                            break;
                        case '>':
                            carts.add(new Cart(Direction.RIGHT, x, y));
                            break;
                    }
                    map[x][y] = '.';
                } else {
                    map[x][y] = input.split("\n")[y].charAt(x);
                }
            }
        }

        int step = 0;
        while(carts.size() > 1) {
            step++;
            for (Cart cart: carts) {
                cart.move(map);
            }
            boolean collision = true;
            while (collision) {
                collision = false;
                for (int i = 0; i < carts.size(); i++) {
                    for (int j = i + 1; j < carts.size(); j++) {
                        if (carts.get(i).x == carts.get(j).x && carts.get(i).y == carts.get(j).y) {
                            printMapWithCarts(map, carts);
                            System.out.println("Collision at step "+step+" at "+carts.get(i).position());
                            carts.remove(i);
                            carts.remove(j - 1);
                            collision = true;
                        }
                    }
                }
            }
        }
        System.out.println(carts.get(0).position());
    }

    public static void printMapWithCarts(char[][] map, ArrayList<Cart> carts) {
        char[][] map1 = new char[map[0].length][map.length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                map1[c][r] = map[r][c];
            }
        }
        for (Cart cart: carts) {
            switch (cart.movementDirection) {
                case UP:
                    map1[cart.y][cart.x] = '^';
                    break;
                case DOWN:
                    map1[cart.y][cart.x] = 'v';
                    break;
                case RIGHT:
                    map1[cart.y][cart.x] = '>';
                    break;
                case LEFT:
                    map1[cart.y][cart.x] = '<';
            }
        }
        for (char[] line: map1) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static class Cart {

        Direction nextTurn, movementDirection;
        int x, y;

        public Cart(Direction initialDirection, int x, int y) {
            movementDirection = initialDirection;
            nextTurn = Direction.LEFT;
            this.x = x;
            this.y = y;
        }

        public String position() {
            return x+","+y;
        }

        public void turn() {
            switch (nextTurn) {
                case LEFT:
                    switch (movementDirection) {
                        case LEFT:
                            movementDirection = Direction.DOWN;
                            break;
                        case RIGHT:
                            movementDirection = Direction.UP;
                            break;
                        case UP:
                            movementDirection = Direction.LEFT;
                            break;
                        case DOWN:
                            movementDirection = Direction.RIGHT;
                            break;
                    }
                    nextTurn = Direction.STRAIGHT;
                    break;
                case STRAIGHT:
                    nextTurn = Direction.RIGHT;
                    break;
                case RIGHT:
                    switch (movementDirection) {
                        case LEFT:
                            movementDirection = Direction.UP;
                            break;
                        case RIGHT:
                            movementDirection = Direction.DOWN;
                            break;
                        case UP:
                            movementDirection = Direction.RIGHT;
                            break;
                        case DOWN:
                            movementDirection = Direction.LEFT;
                            break;
                    }
                    nextTurn = Direction.LEFT;
                    break;
            }
        }

        public void move(char[][] map) {
            switch (movementDirection) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
            checkForDirectionChange(map);
        }

        public void checkForDirectionChange(char[][] map) {
            if (map[x][y] == '+') {
                turn();
            } else {
                switch (movementDirection) {
                    case UP:
                        if (map[x][y] == '/') {
                            movementDirection = Direction.RIGHT;
                        } else if (map[x][y] == '\\') {
                            movementDirection = Direction.LEFT;
                        }
                        break;
                    case DOWN:
                        if (map[x][y] == '/') {
                            movementDirection = Direction.LEFT;
                        } else if (map[x][y] == '\\') {
                            movementDirection = Direction.RIGHT;
                        }
                        break;
                    case LEFT:
                        if (map[x][y] == '/') {
                            movementDirection = Direction.DOWN;
                        } else if (map[x][y] == '\\') {
                            movementDirection = Direction.UP;
                        }
                        break;
                    case RIGHT:
                        if (map[x][y] == '/') {
                            movementDirection = Direction.UP;
                        } else if (map[x][y] == '\\') {
                            movementDirection = Direction.DOWN;
                        }
                        break;
                }
            }
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, STRAIGHT
    }
}
