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

    public static long padding = 0;

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

    @Override
    public void part1() {
        String plantGrowthPatternString = "([.#]+) => ([.#])";
        Pattern plantGrowthPattern = Pattern.compile(plantGrowthPatternString);

        String pots = input.split("\n")[0].substring(15);

        HashMap<Integer, Character> plantGrowthPatterns = new HashMap<>();

        for (int i = 2; i < input.split("\n").length; i++) {
            Matcher m = plantGrowthPattern.matcher(input.split("\n")[i]);
            m.find();
            String pattern = "";
            for (char p: m.group(1).toCharArray()) {
                if (p == '#') {
                    pattern = pattern + "1";
                } else {
                    pattern = pattern + "0";
                }
            }
            plantGrowthPatterns.put(Integer.parseInt(pattern), m.group(2).toCharArray()[0]);
        }


        for (int j = 0; j < 20; j++) {
            pots = removePadding(nextGeneration(pots, plantGrowthPatterns));
        }

        int plantPotNumberSum = 0;
        for (int i = 0; i < pots.length(); i++) {
            if (pots.toCharArray()[i] == '#') {
                plantPotNumberSum += i-padding;
            }
        }
        System.out.println(plantPotNumberSum);
    }

    @Override
    public void part2() {
        String plantGrowthPatternString = "([.#]+) => ([.#])";
        Pattern plantGrowthPattern = Pattern.compile(plantGrowthPatternString);

        String pots = input.split("\n")[0].substring(15);

        HashMap<Integer, Character> plantGrowthPatterns = new HashMap<>();

        for (int i = 2; i < input.split("\n").length; i++) {
            Matcher m = plantGrowthPattern.matcher(input.split("\n")[i]);
            m.find();
            String pattern = "";
            for (char p: m.group(1).toCharArray()) {
                if (p == '#') {
                    pattern = pattern + "1";
                } else {
                    pattern = pattern + "0";
                }
            }
            plantGrowthPatterns.put(Integer.parseInt(pattern), m.group(2).toCharArray()[0]);
        }

        int manualCycles = 200;

        for (int j = 0; j < manualCycles; j++) {
            pots = removePadding(nextGeneration(pots, plantGrowthPatterns));
        }
        padding -= (50000000000L-manualCycles);

        long plantPotNumberSum = 0;
        for (int i = 0; i < pots.length(); i++) {
            if (pots.toCharArray()[i] == '#') {
                plantPotNumberSum += i-padding;
            }
        }
        System.out.println(plantPotNumberSum);
    }

    public static String removePadding(String pots) {
        while(pots.charAt(0) == '.') {
            padding -= 1;
            pots = pots.substring(1);
        }
        return pots;
    }

    public static String nextGeneration(String currentPots, HashMap<Integer, Character> plantGrowthPatterns) {
        String newPots = "";
        int paddingAddition = 0;
        boolean padded = false;
        for (int i = -2; i < currentPots.length()-2; i++) {
            switch (i) {
                case -2:
                    if (plantGrowthPatterns.get(stringPatternToInteger("...."+currentPots.substring(0, 1))) != null && plantGrowthPatterns.get(stringPatternToInteger("...."+currentPots.substring(0, 1))) == '#') {
                        newPots += '#';
                        padded = true;
                        paddingAddition = 2;
                    }
                    break;
                case -1:
                    if (plantGrowthPatterns.get(stringPatternToInteger("..."+currentPots.substring(0, 2))) != null && plantGrowthPatterns.get(stringPatternToInteger("..."+currentPots.substring(0, 2))) == '#') {
                        newPots += '#';
                        if (!padded) {
                            paddingAddition = 1;
                        }
                    } else if (padded) {
                        newPots += '.';
                        paddingAddition = 2;
                    }
                    break;
                case 0:
                    if (plantGrowthPatterns.get(stringPatternToInteger(".."+currentPots.substring(0, 3))) != null && plantGrowthPatterns.get(stringPatternToInteger(".."+currentPots.substring(0, 3))) == '#') {
                        newPots += '#';
                    } else {
                        newPots += '.';
                    }
                    break;
                case 1:
                    if (plantGrowthPatterns.get(stringPatternToInteger("."+currentPots.substring(0, 4))) != null && plantGrowthPatterns.get(stringPatternToInteger("."+currentPots.substring(0, 4))) == '#') {
                        newPots += '#';
                    } else {
                        newPots += '.';
                    }
                    break;
                default:
                    if (plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(i-2, i+3))) != null && plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(i-2, i+3))) == '#') {
                        newPots += '#';
                    } else {
                        newPots += '.';
                    }
                    break;
            }
        }
        padding += paddingAddition;
        if (plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-4)+".")) != null && plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-4)+".")) == '#') {
            newPots += '#';
        } else {
            newPots += '.';
        }
        if (plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-3)+"..")) != null && plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-3)+"..")) == '#') {
            newPots += '#';
        } else {
            newPots += '.';
        }
        boolean firstOut;
        if (plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-2)+"...")) != null && plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-2)+"...")) == '#') {
            newPots += '#';
            firstOut = true;
        } else {
            newPots += '.';
            firstOut = false;
        }
        if (plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-1)+"....")) != null && plantGrowthPatterns.get(stringPatternToInteger(currentPots.substring(currentPots.length()-1)+"....")) == '#') {
            if (!firstOut) {
                newPots += '.';
            }
            newPots += '#';
        } else {
            newPots += '.';
        }
        while (newPots.charAt(newPots.length()-1) == '.') {
            newPots = newPots.substring(0,newPots.length()-1);
        }
        return newPots;
    }

    public static int stringPatternToInteger(String pattern) {
        String integerPattern = "";
        for (char p: pattern.toCharArray()) {
            if (p == '#') {
                integerPattern = integerPattern + "1";
            } else {
                integerPattern = integerPattern + "0";
            }
        }
        return Integer.parseInt(integerPattern);
    }
}
