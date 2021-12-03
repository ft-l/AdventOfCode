package problems.problems2021;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day3 extends Day {

    public Day3() {
        super(3, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        int length = inputLines[0].length();
        int[] sumArray = new int[length];
        long currentLine;
        for (int i = 0; i < inputLines.length; i++) {
            currentLine = Long.parseLong(inputLines[i]);
            for (int j = 1; j < length + 1; j++) {
                sumArray[length - j] += ((long) (currentLine % (Math.pow(10, j)))) / ((long) Math.pow(10, j - 1));
            }
        }
        int[] gammaArray = new int[length];
        int[] epsilonArray = new int[length];
        for (int i = 0; i < length; i++) {
            if (sumArray[i] > inputLines.length/2) {
                gammaArray[i]++;
            } else {
                epsilonArray[i]++;
            }
        }
        long gamma = 0;
        long epsilon = 0;
        for (int i = 0; i < length; i++) {
            gamma += Math.pow(2, i) * gammaArray[length-i-1];
            epsilon += Math.pow(2, i) * epsilonArray[length-i-1];
        }
        System.out.println(gamma*epsilon);
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        int length = inputLines[0].length();
        ArrayList<Long> inputLinesBinary = new ArrayList<>();
        for (String inputLine : inputLines) {
            inputLinesBinary.add(Long.parseLong(inputLine));
        }
        ArrayList<Long> oxygenGeneratorRatings = new ArrayList<>(inputLinesBinary);
        ArrayList<Long>[] dividedOxygen;
        ArrayList<Long> co2ScrubberRatings = new ArrayList<>(inputLinesBinary);
        ArrayList<Long>[] dividedCO2;
        for (int i = 0; i < length; i++) {
            if (oxygenGeneratorRatings.size() > 1) {
                dividedOxygen = dividedList(oxygenGeneratorRatings, i, length);
                if (dividedOxygen[0].size() >= dividedOxygen[1].size()) {
                    oxygenGeneratorRatings = dividedOxygen[0];
                } else {
                    oxygenGeneratorRatings = dividedOxygen[1];
                }
            }
            if (co2ScrubberRatings.size() > 1) {
                dividedCO2 = dividedList(co2ScrubberRatings, i, length);
                if (dividedCO2[0].size() < dividedCO2[1].size()) {
                    co2ScrubberRatings = dividedCO2[0];
                } else {
                    co2ScrubberRatings = dividedCO2[1];
                }
            }
        }
        System.out.println(binaryLongToDecimalInt(oxygenGeneratorRatings.get(0), length) * binaryLongToDecimalInt(co2ScrubberRatings.get(0), length));
    }

    private ArrayList<Long>[] dividedList(ArrayList<Long> allCodes, int index, int length) {
        ArrayList<Long> zeroesIndex = new ArrayList<>();
        for (int i = 0; i < allCodes.size(); i++) {
            if ((allCodes.get(i) / ((long) Math.pow(10, length-1-index)) % 10) == 0) {
                zeroesIndex.add(allCodes.remove(i));
                i--;
            }
        }
        return new ArrayList[]{allCodes, zeroesIndex};
    }

    private int binaryLongToDecimalInt(long binary, int length) {
        int result = 0;
        for (int i = 0; i < length; i++) {
            result += Math.pow(2, i) * (binary / ((long) Math.pow(10, i)) % 10);
        }
        return result;
    }
}
