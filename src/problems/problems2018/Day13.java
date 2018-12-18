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

    public static void part1(String input) {

        char[][] map = new char[input.split("\n")[0].length()][input.split("\n").length];

        ArrayList<Cart> carts = new ArrayList<>();

        for (int x = 0; x < input.split("\n")[0].length(); x++) {
            for (int y = 0; y < input.split("\n").length; y++) {
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
        boolean collision = false;
        printMapWithCarts(map, carts);
        /*while(!collision) {
            for (Cart cart: carts) {
                cart.move(map);
            }
            printMapWithCarts(map, carts);
            for (int i = 0; i < carts.size(); i++) {
                for (int j = i + 1; j < carts.size(); j++) {
                    if (carts.get(i).x == carts.get(j).x && carts.get(i).y == carts.get(j).y) {
                        System.out.println(carts.get(i).y + "," + carts.get(i).x);
                        collision = true;
                    }
                }
            }
        }*/
    }

    public static void part2(String input) {
        char[][] test = new char[5][10];
        System.out.println(test.length);
        System.out.println(test[0].length);
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
            System.out.println(Arrays.toString(line));
        }
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

        public void turn() {

        }

        public void move(char[][] map) {

        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, STRAIGHT
    }
}
