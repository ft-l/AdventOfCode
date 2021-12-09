package problems.problems2021;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day8 extends Day {

    public Day8() {
        super(8, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        String outputValueString;
        int count = 0;
        for (String line : inputLines) {
            outputValueString = line.substring(line.indexOf("|")+2).trim();
            for (String outputValue :
                    outputValueString.split(" ")) {
                if (outputValue.trim().length() == 2 || outputValue.trim().length() == 3 || outputValue.trim().length() == 4 || outputValue.trim().length() == 7) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        String signalPatternString;
        String outputValueString;
        int total = 0;
        for (String line : inputLines) {
            // The possible mappings from [0] to [1] where [0] is the value being mapped to the proper location [1]
            HashMap<Character, Character> map = new HashMap<>();
            ArrayList<Character> unknownCharacters = new ArrayList<>();
            for (int i = 97; i < 104; i++) {
                unknownCharacters.add((char)i);
            }
            signalPatternString = line.substring(0, line.indexOf("|")).trim();
            outputValueString = line.substring(line.indexOf("|")+2).trim();
            String twoPattern = "";
            String threePattern = "";
            ArrayList<String> sixPatterns = new ArrayList<>();
            for (String signalPattern : signalPatternString.split(" ")) {
                if (signalPattern.length() == 2) {
                    twoPattern = signalPattern;
                } else if (signalPattern.length() == 3) {
                    threePattern = signalPattern;
                } else if (signalPattern.length() == 6) {
                    sixPatterns.add(signalPattern);
                }
            }

            // Add all together and compare occurrences of each element
            // Will tell us which correspond to b, e, and f
            for (int i = 97; i < 104; i++) {
                if (occurrences(signalPatternString, (char) i) == 4) {
                    map.put((char)i, 'e');
                    Character c = (char)i;
                    unknownCharacters.remove(c);
                }
                if (occurrences(signalPatternString, (char) i) == 6) {
                    map.put((char)i, 'b');
                    Character c = (char)i;
                    unknownCharacters.remove(c);
                }
                if (occurrences(signalPatternString, (char) i) == 9) {
                    map.put((char)i, 'f');
                    Character c = (char)i;
                    unknownCharacters.remove(c);
                }
            }

            // Since we know f, we can determine c from the pattern of length two, which represents 1
            if (map.containsKey(twoPattern.charAt(0))) {
                map.put(twoPattern.charAt(1), 'c');
                Character c = twoPattern.charAt(1);
                unknownCharacters.remove(c);
            } else {
                map.put(twoPattern.charAt(0), 'c');
                Character c = twoPattern.charAt(0);
                unknownCharacters.remove(c);
            }

            // The only difference between the twoPattern (1) and the threePattern (7) is a, so removing two's
            // characters will tell us which is a
            for (char character : twoPattern.toCharArray()) {
                threePattern = threePattern.replace(character + "", "");
            }

            map.put(threePattern.charAt(0), 'a');
            Character c = threePattern.charAt(0);
            unknownCharacters.remove(c);

            // By adding all the patterns of length six together, the characters that occur twice are c, d, and e, and
            // since we know c and e, we can determine which is d
            StringBuilder allLengthSixPatterns = new StringBuilder();

            for (String patternSix :
                    sixPatterns) {
                allLengthSixPatterns.append(patternSix);
            }

            ArrayList<Character> charactersOccurringTwice = new ArrayList<>();
            for (int i = 97; i < 104; i++) {
                if (occurrences(allLengthSixPatterns.toString(), (char) i) == 2) {
                    charactersOccurringTwice.add((char) i);
                }
            }

            for (int i = 0; i < charactersOccurringTwice.size(); i++) {
                if (map.containsKey(charactersOccurringTwice.get(i))) {
                    charactersOccurringTwice.remove(i);
                    i--;
                }
            }

            map.put(charactersOccurringTwice.get(0), 'd');
            c = charactersOccurringTwice.get(0);
            unknownCharacters.remove(c);

            // The last remaining unaccounted for character must be g since all the rest have been discovered
            map.put(unknownCharacters.get(0), 'g');

            String[] outputValues = outputValueString.split(" ");
            int numDisplayed = 0;
            for (int i = 0; i < 4; i++) {
                if (outputValues[i].length() == 2) {
                    numDisplayed += Math.pow(10, 3-i);
                }
                if (outputValues[i].length() == 3) {
                    numDisplayed += (7 * Math.pow(10, 3-i));
                }
                if (outputValues[i].length() == 4) {
                    numDisplayed += (4 * Math.pow(10, 3-i));
                }
                if (outputValues[i].length() == 7) {
                    numDisplayed += (8 * Math.pow(10, 3-i));
                }
                if (outputValues[i].length() == 5) {
                    StringBuilder temp = new StringBuilder();
                    for (int j = 0; j < 5; j++) {
                        temp.append(map.get(outputValues[i].charAt(j)));
                    }

                    char[] x = temp.toString().toCharArray();
                    Arrays.sort(x);
                    String sorted = new String(x);
                    switch (sorted) {
                        case "acdeg" -> numDisplayed += (2 * Math.pow(10, 3 - i));
                        case "acdfg" -> numDisplayed += (3 * Math.pow(10, 3 - i));
                        case "abdfg" -> numDisplayed += (5 * Math.pow(10, 3 - i));
                    }
                }
                if (outputValues[i].length() == 6) {
                    StringBuilder temp = new StringBuilder();
                    for (int j = 0; j < 6; j++) {
                        temp.append(map.get(outputValues[i].charAt(j)));
                    }

                    char[] x = temp.toString().toCharArray();
                    Arrays.sort(x);
                    String sorted = new String(x);

                    switch (sorted) {
                        case "abdefg" -> numDisplayed += (6 * Math.pow(10, 3 - i));
                        case "abcdfg" -> numDisplayed += (9 * Math.pow(10, 3 - i));
                    }
                }
            }
            total += numDisplayed;
        }
        System.out.println(total);
    }

    public int occurrences(String string, char character) {
        int count = 0;
        for (char x : string.toCharArray()) {
            if (x == character) {
                count++;
            }
        }
        return count;
    }
}
