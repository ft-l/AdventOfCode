package problems.problems2021;

import problems.Day;

public class Day2 extends Day {

    public Day2() {
        super(2, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        int depth = 0;
        int horizontalPosition = 0;
        int distance;
        for (String inputLine :
                inputLines) {
            distance = Integer.parseInt(inputLine.substring(inputLine.indexOf(" ")+1));
            switch (inputLine.substring(0, inputLine.indexOf(" "))) {
                case "forward":
                    horizontalPosition += distance;
                    break;
                case "down":
                    depth += distance;
                    break;
                case "up":
                    depth -= distance;
                    break;
                default:
                    break;
            }
        }
        System.out.println(depth*horizontalPosition);
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        int depth = 0;
        int horizontalPosition = 0;
        int distance;
        int aim = 0;
        for (String inputLine :
                inputLines) {
            distance = Integer.parseInt(inputLine.substring(inputLine.indexOf(" ")+1));
            switch (inputLine.substring(0, inputLine.indexOf(" "))) {
                case "forward":
                    horizontalPosition += distance;
                    depth += (aim*distance);
                    break;
                case "down":
                    aim += distance;
                    break;
                case "up":
                    aim -= distance;
                    break;
                default:
                    break;
            }
        }
        System.out.println(depth*horizontalPosition);
    }
}
