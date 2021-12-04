package problems.problems2021;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Day4 extends Day {

    public Day4() {
        super(4, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        String[] numbersDrawn = inputLines[0].split(",");
        ArrayList<Grid> grids = new ArrayList<>();
        for (int i = 2; i < inputLines.length; i += 6) {
            grids.add(new Grid(Arrays.copyOfRange(inputLines, i, i + 5)));
        }
        int numberIndex = 0;
        boolean winnerFound = false;
        Grid winningGrid = null;
        int winningNumber = -1;
        while (!winnerFound) {
            for (Grid grid : grids) {
                if (grid.setCheckedAndCheckIfWon(Integer.parseInt(numbersDrawn[numberIndex]))) {
                    winningGrid = grid;
                    winnerFound = true;
                    winningNumber = Integer.parseInt(numbersDrawn[numberIndex]);
                    break;
                }
            }
            numberIndex++;
        }
        System.out.println(winningGrid.getScore(winningNumber));
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        String[] numbersDrawn = inputLines[0].split(",");
        ArrayList<Grid> grids = new ArrayList<>();
        for (int i = 2; i < inputLines.length; i += 6) {
            grids.add(new Grid(Arrays.copyOfRange(inputLines, i, i + 5)));
        }
        int numberIndex = 0;
        while (grids.size() > 1) {
            for (int i = 0; i < grids.size(); i++) {
                if (grids.get(i).setCheckedAndCheckIfWon(Integer.parseInt(numbersDrawn[numberIndex]))) {
                    grids.remove(i);
                    i--;
                }
            }
            numberIndex++;
        }
        while (!grids.get(0).setCheckedAndCheckIfWon(Integer.parseInt(numbersDrawn[numberIndex]))) {
            numberIndex++;
        }
        System.out.println(grids.get(0).getScore(Integer.parseInt(numbersDrawn[numberIndex])));
    }

    public static class Grid {

        int[][] numbers = new int[5][5];
        boolean[][] checked = new boolean[5][5];

        public Grid(String[] lines) {
            for (int r = 0; r < 5; r++) {
                String[] lineValues = lines[r].split(" ");
                int c = 0;
                for (String value : lineValues) {
                    if (!Objects.equals(value, "")) {
                        numbers[r][c] = Integer.parseInt(value);
                        checked[r][c] = false;
                        c++;
                    }
                }
            }
        }

        public boolean setCheckedAndCheckIfWon(int value) {
            setChecked(value);
            for (int r = 0; r < 5; r++) {
                boolean won = true;
                for (int c = 0; c < 5; c++) {
                    if (!checked[r][c]) {
                        won = false;
                        break;
                    }
                }
                if (won) {
                    return true;
                }
            }
            for (int c = 0; c < 5; c++) {
                boolean won = true;
                for (int r = 0; r < 5; r++) {
                    if (!checked[r][c]) {
                        won = false;
                        break;
                    }
                }
                if (won) {
                    return true;
                }
            }
            return false;
        }

        public void setChecked(int value) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (numbers[r][c] == value) {
                        checked[r][c] = true;
                        return;
                    }
                }
            }
        }

        public int getScore(int winningNumber) {
            int sum = 0;
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (!checked[r][c]) {
                        sum += numbers[r][c];
                    }
                }
            }
            return sum*winningNumber;
        }

        public void printGrid() {
            for (int r = 0; r < 5; r++) {
                System.out.println(Arrays.toString(numbers[r]));
            }
        }
    }
}
