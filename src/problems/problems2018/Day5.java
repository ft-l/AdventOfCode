package problems.problems2018;

import problems.Day;

public class Day5 extends Day {

    public Day5() {
        super(5, 2018);
    }

    public static final String TEST_INPUT = "dabAcCaCBAcCcaDA";

    @Override
    public void part1() {
        input = fullReact(input);
        System.out.println(input.length());
    }

    @Override
    public void part2() {

        int[] polymers = new int[26];

        for (int i = 0; i < 26; i++) {
            polymers[i] = fullReact(
                    input.replace(""+((char) (i+97))   ,"")
                         .replace(""+((char) (i+97-32)),"")
            ).length();
        }

        int shortestPolymer = 0;

        for (int i = 0; i < polymers.length; i++) {
            if(polymers[i] < polymers[shortestPolymer]) {
                shortestPolymer = i;
            }
        }
        System.out.println(polymers[shortestPolymer]);
    }

    public static String react(String polymer) {
        for (int i = 0; i < polymer.length() - 1; i++) {
            if (Math.abs(polymer.charAt(i) - polymer.charAt(i + 1)) == 32) {
                polymer = polymer.substring(0, i) + polymer.substring(i + 2);
            }
        }
        return polymer;
    }

    public static String fullReact(String polymer) {
        boolean foundReaction = true;
        while(foundReaction) {
            if(polymer.equals(react(polymer))) {
                foundReaction = false;
            } else {
                polymer = react(polymer);
            }
        }
        return polymer;
    }
}
