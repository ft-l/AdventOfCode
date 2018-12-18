package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 extends Day {

    public Day12() {
        super(12, 2018);
    }

    public static int padding = 0;

    public static final String TEST_INPUT = "initial state: #..#.#..##......###...###\n" +
                                            "\n" +
                                            "...## => #\n" +
                                            "..#.. => #\n" +
                                            ".#... => #\n" +
                                            ".#.#. => #\n" +
                                            ".#.## => #\n" +
                                            ".##.. => #\n" +
                                            ".#### => #\n" +
                                            "#.#.# => #\n" +
                                            "#.### => #\n" +
                                            "##.#. => #\n" +
                                            "##.## => #\n" +
                                            "###.. => #\n" +
                                            "###.# => #\n" +
                                            "####. => #";

    public static void part1(String input) {
        String plantGrowthPatternString = "([.#]+) => ([.#])";
        Pattern plantGrowthPattern = Pattern.compile(plantGrowthPatternString);

        String pots = input.split("\n")[0].substring(15);

        HashMap<String, Character> plantGrowthPatterns = new HashMap<>();

        for (int i = 2; i < input.split("\n").length; i++) {
            Matcher m = plantGrowthPattern.matcher(input.split("\n")[i]);
            m.find();
            plantGrowthPatterns.put(m.group(1), m.group(2).toCharArray()[0]);
        }

        for (int i = 0; i < 20; i++) {
            pots = nextGeneration(pots, plantGrowthPatterns);
        }

        int plantPotNumberSum = 0;
        for (int i = 0; i < pots.length(); i++) {
            if (pots.toCharArray()[i] == '#') {
                plantPotNumberSum += i-padding;
            }
        }
        System.out.println(plantPotNumberSum);
    }

    public static void part2(String input) {

    }

    public static String nextGeneration(String currentPots, HashMap<String, Character> plantGrowthPatterns) {
        String newPots = "..";
        for (int i = 2; i < currentPots.length()-2; i++) {
            boolean foundPattern = false;
            for (String key: plantGrowthPatterns.keySet()) {
                if (currentPots.substring(i-2, i+3).equals(key)) {
                    newPots += plantGrowthPatterns.get(key);
                    foundPattern = true;
                    break;
                }
            }
            if (!foundPattern) {
                newPots += ".";
            }
        }
        newPots += "..";
        return newPots;
    }
}
