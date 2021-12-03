package problems.problems2018;

import problems.Day;

public class Day2 extends Day {

    public Day2() {
        super(2, 2018);
    }

    @Override
    public void part1() {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int appearsTwice = 0;
        int appearsThrice = 0;
        for(String line : input.split("\n")) {
            boolean counts2 = false;
            boolean counts3 = false;
            for(char letter : alphabet) {
                int appearances = 0;
                for(char character : line.toCharArray()) {
                    if(character == letter) {
                        appearances++;
                    }
                }
                if(appearances == 2 && !counts2) {
                    appearsTwice++;
                    counts2 = true;
                } else if(appearances == 3 && !counts3) {
                    appearsThrice++;
                    counts3 = true;
                }
                if(counts2 && counts3) {
                    break;
                }
            }
        }
        System.out.println(appearsTwice*appearsThrice);
    }

    @Override
    public void part2() {
        boolean done = false;
        for(int i = 0; i < input.split("\n").length-1; i++) {
            for(int r = i+1; r < input.split("\n").length; r++) {
                boolean strick1 = false;
                boolean passes = true;
                for(int c = 0; c < 26; c++) {
                    if(input.split("\n")[i].charAt(c) != input.split("\n")[r].charAt(c) && !strick1) {
                        strick1 = true;
                    } else if(input.split("\n")[i].charAt(c) != input.split("\n")[r].charAt(c) && strick1) {
                        passes = false;
                        break;
                    }
                }
                if(passes) {
                    System.out.println(input.split("\n")[i] + "   " + input.split("\n")[r]);
                    done = true;
                    break;
                }
            }
            if(done) {
                break;
            }
        }
    }
}
