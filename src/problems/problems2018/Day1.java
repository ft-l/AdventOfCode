package problems.problems2018;

import problems.Day;

import java.util.*;

public class Day1 extends Day {

    public Day1() {
        super(1, 2018);
    }

    private static String TEST_INPUT =  "+3\n" +
                                        "+3\n" +
                                        "+4\n" +
                                        "-2\n" +
                                        "-4\n";

    private void part1(String input) {
        int total = 0;
        for(String line : input.split("\n")) {
            if(line.substring(0,1).equals("+")) {
                total += Integer.parseInt(line.substring(1));
            } else {
                total -= Integer.parseInt(line.substring(1));
            }
        }
        System.out.println(total);
    }

    private void part2(String input) {
        int frequency = 0;
        ArrayList<Integer> frequencies = new ArrayList<>();
        boolean repeat = false;
        while(!repeat) {
            for (String line : input.split("\n")) {
                if (!frequencies.contains(frequency)) {
                    frequencies.add(frequency);
                } else {
                    System.out.println(frequency);
                    repeat = true;
                    break;
                }
                if (line.substring(0, 1).equals("+")) {
                    frequency += Integer.parseInt(line.substring(1));
                } else {
                    frequency -= Integer.parseInt(line.substring(1));
                }
            }
        }
    }
}
