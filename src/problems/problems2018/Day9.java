package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 extends Day {

    public static final String TEST_INPUT = "17 players; last marble is worth 1104 points";

    public Day9() {
        super(9, 2018);
    }

    @Override
    public void part1() {
        String inputPatternString = "(\\d+) players; last marble is worth (\\d+) points";
        Pattern inputPattern = Pattern.compile(inputPatternString);
        Matcher m = inputPattern.matcher(input);
        m.find();

        int players = Integer.parseInt(m.group(1));
        int lastMarbleValue = Integer.parseInt(m.group(2));

        int[] playerScores = new int[players];

        Marble currentMarble = new Marble(0);
        currentMarble.leftMarble = currentMarble;
        currentMarble.rightMarble = currentMarble;

        currentMarble.insert(1);
        currentMarble.insert(2);

        currentMarble = currentMarble.rightMarble;

        int currentMarbleValue = 2;
        int currentPlayer = 3;

        while (currentMarbleValue < lastMarbleValue) {
            currentPlayer = (currentPlayer+1)%players;
            currentMarbleValue++;
            if (currentMarbleValue % 23 == 0) {
                currentMarble = currentMarble.leftMarble.leftMarble.leftMarble.leftMarble.leftMarble.leftMarble;
                playerScores[currentPlayer] += currentMarbleValue+currentMarble.leftMarble.remove().value;
            } else {
                currentMarble = currentMarble.rightMarble.insert(currentMarbleValue);
            }
        }

        int greatestScore = 0;

        for (int score: playerScores) {
            if (score > greatestScore) {
                greatestScore = score;
            }
        }
        System.out.println(greatestScore);
    }

    @Override
    public void part2() {
        String inputPatternString = "(\\d+) players; last marble is worth (\\d+) points";
        Pattern inputPattern = Pattern.compile(inputPatternString);
        Matcher m = inputPattern.matcher(input);
        m.find();

        int players = Integer.parseInt(m.group(1));
        int lastMarbleValue = Integer.parseInt(m.group(2))*100;

        long[] playerScores = new long[players];

        Marble currentMarble = new Marble(0);
        currentMarble.leftMarble = currentMarble;
        currentMarble.rightMarble = currentMarble;

        currentMarble.insert(1);
        currentMarble.insert(2);

        currentMarble = currentMarble.rightMarble;

        int currentMarbleValue = 2;
        int currentPlayer = 3;

        while (currentMarbleValue < lastMarbleValue) {
            currentPlayer = (currentPlayer+1)%players;
            currentMarbleValue++;
            if (currentMarbleValue % 23 == 0) {
                currentMarble = currentMarble.leftMarble.leftMarble.leftMarble.leftMarble.leftMarble.leftMarble;
                playerScores[currentPlayer] += currentMarbleValue+currentMarble.leftMarble.remove().value;
            } else {
                currentMarble = currentMarble.rightMarble.insert(currentMarbleValue);
            }
        }

        long greatestScore = 0;

        for (long score: playerScores) {
            if (score > greatestScore) {
                greatestScore = score;
            }
        }
        System.out.println(greatestScore);
    }

    public static class Marble {

        int value;
        Marble leftMarble, rightMarble;

        public Marble(int value) {
            this.value = value;
        }

        public Marble(int value, int leftMarbleValue, int rightMarbleValue) {
            this.value = value;
            leftMarble = new Marble(leftMarbleValue);
            rightMarble = new Marble(rightMarbleValue);
        }

        public Marble(int value, Marble leftMarble, Marble rightMarble) {
            this.value = value;
            this.leftMarble = leftMarble;
            this.rightMarble = rightMarble;
        }

        public Marble insert(int newMarbleValue) {
            Marble newMarble = new Marble(newMarbleValue, this, this.rightMarble);
            this.rightMarble.leftMarble = newMarble;
            this.rightMarble = newMarble;
            return newMarble;
        }

        public Marble remove() {
            this.rightMarble.leftMarble = this.leftMarble;
            this.leftMarble.rightMarble = this.rightMarble;
            return this;
        }

        @Override
        public String toString() {
            String result = "value = "+value;
            result += "\nleftMarble = "+leftMarble.value;
            result += "\nrightMarble = "+rightMarble.value;
            return result;
        }

        public String chain() {
            String result = "";
            Marble currentMarble = this;
            while (currentMarble.rightMarble.value != this.value) {
                result += " " + currentMarble.value;
                currentMarble = currentMarble.rightMarble;
            }
            result += " " + currentMarble.value;
            return result;
        }
    }
}
