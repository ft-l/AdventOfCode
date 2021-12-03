package problems.problems2018;

import problems.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 extends Day {

    public static final String TEST_INPUT = "Before: [0, 1, 2, 1]\n" +
                                            "14 1 3 3\n" +
                                            "After:  [0, 1, 2, 1]\n" +
                                            "\n" +
                                            "Before: [3, 2, 2, 3]\n" +
                                            "13 2 1 3\n" +
                                            "After:  [3, 2, 2, 1]\n" +
                                            "\n" +
                                            "Before: [2, 2, 2, 2]\n" +
                                            "13 2 1 0\n" +
                                            "After:  [1, 2, 2, 2]";

    public Day16() {
        super(16, 2018);
    }

    @Override
    public void part1() {
        String samples = input.split("\n\n\n\n")[0];

        String pattern = "Before: +(\\[[\\d, ]+\\])\\n([\\d, ]+)\\nAfter: +(\\[[\\d, ]+\\])";
        Pattern r = Pattern.compile(pattern);

        int samplesWithThreeOrMorePossibleOpcodes = 0;

        for (String sample: samples.split("\n\n")) {
            int possibleOpmodeNum = 0;
            Matcher matcher = r.matcher(sample);
            matcher.find();
            int[] before      = stringToIntArray(matcher.group(1));
            int[] instruction = stringToIntArray(matcher.group(2));
            int[] registers = new int[instruction.length-1];
            for (int i = 0; i < registers.length; i++) {
                registers[i] = instruction[i+1];
            }
            int[] after       = stringToIntArray(matcher.group(3));

            for (int i = 0; i < 16; i++) {
                if (Arrays.equals(after, executeOpcode(i, registers, before))) {
                    possibleOpmodeNum++;
                }
            }
            if (possibleOpmodeNum >= 3) {
                samplesWithThreeOrMorePossibleOpcodes++;
            }
        }
        System.out.println("Part1 = "+samplesWithThreeOrMorePossibleOpcodes);
    }

    @Override
    public void part2() {
        String samples = input.split("\n\n\n\n")[0];

        String pattern = "Before: +(\\[[\\d, ]+\\])\\n([\\d, ]+)\\nAfter: +(\\[[\\d, ]+\\])";
        Pattern r = Pattern.compile(pattern);

        //listed, actual
        HashMap<Integer, Integer> listedToActual = new HashMap<>();
        while (listedToActual.size() < 16) {
            for (String sample : samples.split("\n\n")) {
                Matcher matcher = r.matcher(sample);
                matcher.find();
                int[] before = stringToIntArray(matcher.group(1));
                int[] instruction = stringToIntArray(matcher.group(2));
                int[] registers = new int[instruction.length - 1];
                for (int i = 0; i < registers.length; i++) {
                    registers[i] = instruction[i + 1];
                }
                int[] after = stringToIntArray(matcher.group(3));

                HashSet<Integer> possibleActuals = new HashSet<>();

                for (int i = 0; i < 16; i++) {
                    if (Arrays.equals(after, executeOpcode(i, registers, before))) {
                        possibleActuals.add(i);
                    }
                }
                HashSet<Integer> knownActuals = new HashSet<>();
                for (int key: listedToActual.keySet()) {
                    knownActuals.add(listedToActual.get(key));
                }
                possibleActuals.removeAll(knownActuals);
                if (possibleActuals.size() == 1) {
                    for (int possibleActual : possibleActuals) {
                        listedToActual.put(instruction[0], possibleActual);
                    }
                }
            }
        }
        String testProgramCommands = input.split("\n\n\n\n")[1];

        int[] registers = new int[4];

        for (String command: testProgramCommands.split("\n")) {
            int[] commandArray = stringToIntArray(command);
            int[] inAndOutRegisters = new int[commandArray.length-1];
            for (int i = 0; i < inAndOutRegisters.length; i++) {
                inAndOutRegisters[i] = commandArray[i+1];
            }
            registers = executeOpcode(listedToActual.get(commandArray[0]), inAndOutRegisters, registers);
        }

        System.out.println(registers[0]);
    }

    public static int[] executeOpcode(int opcodeNumber, int[] inAndOutRegisters, int[] before) {
        int[] after = new int[before.length];
        for (int i = 0; i < before.length; i++) {
            after[i] = before[i];
        }

        //A = inAndOutRegisters[0]
        //B = inAndOutRegisters[1]
        //C = inAndOutRegisters[2]

        switch (opcodeNumber) {

            //addr
            case 0:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]+before[inAndOutRegisters[1]];
                break;
            //addi
            case 1:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]+inAndOutRegisters[1];
                break;

            //mulr
            case 2:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]*before[inAndOutRegisters[1]];
                break;
            //muli
            case 3:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]*inAndOutRegisters[1];
                break;

            //banr
            case 4:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]&before[inAndOutRegisters[1]];
                break;
            //bani
            case 5:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]&inAndOutRegisters[1];
                break;

            //borr
            case 6:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]|before[inAndOutRegisters[1]];
                break;
            //bori
            case 7:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]]|inAndOutRegisters[1];
                break;

            //setr
            case 8:
                after[inAndOutRegisters[2]] = before[inAndOutRegisters[0]];
                break;
            //seti
            case 9:
                after[inAndOutRegisters[2]] = inAndOutRegisters[0];
                break;

            //gtir
            case 10:
                if (inAndOutRegisters[0] > before[inAndOutRegisters[1]]) {
                    after[inAndOutRegisters[2]] = 1;
                } else {
                    after[inAndOutRegisters[2]] = 0;
                }
                break;
            //gtri
            case 11:
                if (before[inAndOutRegisters[0]] > inAndOutRegisters[1]) {
                    after[inAndOutRegisters[2]] = 1;
                } else {
                    after[inAndOutRegisters[2]] = 0;
                }
                break;
            //gtrr
            case 12:
                if (before[inAndOutRegisters[0]] > before[inAndOutRegisters[1]]) {
                    after[inAndOutRegisters[2]] = 1;
                } else {
                    after[inAndOutRegisters[2]] = 0;
                }
                break;

            //eqir
            case 13:
                if (inAndOutRegisters[0] == before[inAndOutRegisters[1]]) {
                    after[inAndOutRegisters[2]] = 1;
                } else {
                    after[inAndOutRegisters[2]] = 0;
                }
                break;
            //eqri
            case 14:
                if (before[inAndOutRegisters[0]] == inAndOutRegisters[1]) {
                    after[inAndOutRegisters[2]] = 1;
                } else {
                    after[inAndOutRegisters[2]] = 0;
                }
                break;
            //eqrr
            case 15:
                if (before[inAndOutRegisters[0]] == before[inAndOutRegisters[1]]) {
                    after[inAndOutRegisters[2]] = 1;
                } else {
                    after[inAndOutRegisters[2]] = 0;
                }
                break;
        }
        return after;
    }

    public static int[] stringToIntArray(String toBeArray) {
        if (toBeArray.contains("[")) {
            int[] result = new int[toBeArray.split(",").length];
            result[0] = Integer.parseInt(toBeArray.split(",")[0].substring(1));
            for (int i = 1; i < toBeArray.split(",").length - 1; i++) {
                result[i] = Integer.parseInt(toBeArray.split(",")[i].substring(1));
            }
            result[result.length - 1] = Integer.parseInt(toBeArray.split(",")[result.length - 1].substring(1, toBeArray.split(",")[result.length - 1].length() - 1));
            return result;
        } else {
            int[] result = new int[toBeArray.split(" ").length];
            for (int i = 0; i < toBeArray.split(" ").length; i++) {
                result[i] = Integer.parseInt(toBeArray.split(" ")[i]);
            }
            return result;
        }
    }
}
