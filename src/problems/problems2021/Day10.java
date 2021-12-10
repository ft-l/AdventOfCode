package problems.problems2021;

import problems.Day;

import java.util.ArrayList;
import java.util.Comparator;

public class Day10 extends Day {

    public Day10() {
        super(10, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        ArrayList<Character> characterStack = new ArrayList<>();
        int totalSyntaxErrorScore = 0;
        for (String line :
                inputLines) {
            for (Character c :
                    line.trim().toCharArray()) {
                boolean done = false;
                switch (c) {
                    case '[' -> characterStack.add('[');
                    case '(' -> characterStack.add('(');
                    case '{' -> characterStack.add('{');
                    case '<' -> characterStack.add('<');
                    case ']' -> {
                        if (characterStack.remove(characterStack.size()-1) != '[') {
                            done = true;
                            totalSyntaxErrorScore += 57;
                        }
                    }
                    case ')' -> {
                        if (characterStack.remove(characterStack.size()-1) != '(') {
                            done = true;
                            totalSyntaxErrorScore += 3;
                        }
                    }
                    case '}' -> {
                        if (characterStack.remove(characterStack.size()-1) != '{') {
                            done = true;
                            totalSyntaxErrorScore += 1197;
                        }
                    }
                    case '>' -> {
                        if (characterStack.remove(characterStack.size()-1) != '<') {
                            done = true;
                            totalSyntaxErrorScore += 25137;
                        }
                    }
                }
                if (done) {
                    break;
                }
            }
        }
        System.out.println(totalSyntaxErrorScore);
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        ArrayList<Character> characterStack;
        ArrayList<ArrayList<Character>> incompleteLines = new ArrayList<>();
        for (String line : inputLines) {
            boolean corrupted = false;
            characterStack = new ArrayList<>();
            for (Character c : line.trim().toCharArray()) {
                switch (c) {
                    case '[' -> characterStack.add('[');
                    case '(' -> characterStack.add('(');
                    case '{' -> characterStack.add('{');
                    case '<' -> characterStack.add('<');
                    case ']' -> {
                        if (characterStack.remove(characterStack.size()-1) != '[') {
                            corrupted = true;
                        }
                    }
                    case ')' -> {
                        if (characterStack.remove(characterStack.size()-1) != '(') {
                            corrupted = true;
                        }
                    }
                    case '}' -> {
                        if (characterStack.remove(characterStack.size()-1) != '{') {
                            corrupted = true;
                        }
                    }
                    case '>' -> {
                        if (characterStack.remove(characterStack.size()-1) != '<') {
                            corrupted = true;
                        }
                    }
                }
                if (corrupted) {
                    break;
                }
            }
            if (!corrupted) {
                incompleteLines.add(characterStack);
            }
        }
        incompleteLines.sort(Comparator.comparingLong(this::completionScore));
        System.out.println(completionScore(incompleteLines.get(incompleteLines.size()/2)));
    }

    public long completionScore(ArrayList<Character> stringToComplete) {
        long score = 0;
        for (int i = stringToComplete.size()-1; i >= 0; i--) {
            score = 5*score;
            switch (stringToComplete.get(i)) {
                case '(' -> score += 1;
                case '[' -> score += 2;
                case '{' -> score += 3;
                case '<' -> score += 4;
            }
        }
        return score;
    }
}
