package problems.problems2018;

import problems.Day;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 extends Day {

    public static final String TEST_INPUT = "#ip 0\n" +
                                            "seti 5 0 1\n" +
                                            "seti 6 0 2\n" +
                                            "addi 0 1 0\n" +
                                            "addr 1 2 3\n" +
                                            "setr 1 0 0\n" +
                                            "seti 8 0 4\n" +
                                            "seti 9 0 5";

    public Day19() {
        super(19, 2018);
    }

    @Override
    public void part1() {
        String numPatternString = "(\\d+) (\\d+) (\\d+)";
        Pattern numPattern = Pattern.compile(numPatternString);

        int boundRegister = Integer.parseInt(input.split("\n")[0].substring(4));
        int[] registers = new int[6];

        Command[] commands = new Command[input.split("\n").length-1];
        for (int i = 1; i < input.split("\n").length; i++) {
            String line = input.split("\n")[i];
            Matcher m = numPattern.matcher(line.substring(5));
            m.find();
            int commandNum = 0;
            switch (line.substring(0,4)) {
                case "addr":
                    commandNum = 0;
                    break;
                case "addi":
                    commandNum = 1;
                    break;
                case "mulr":
                    commandNum = 2;
                    break;
                case "muli":
                    commandNum = 3;
                    break;
                case "banr":
                    commandNum = 4;
                    break;
                case "bani":
                    commandNum = 5;
                    break;
                case "borr":
                    commandNum = 6;
                    break;
                case "bori":
                    commandNum = 7;
                    break;
                case "setr":
                    commandNum = 8;
                    break;
                case "seti":
                    commandNum = 9;
                    break;
                case "gtir":
                    commandNum = 10;
                    break;
                case "gtri":
                    commandNum = 11;
                    break;
                case "gtrr":
                    commandNum = 12;
                    break;
                case "eqir":
                    commandNum = 13;
                    break;
                case "eqri":
                    commandNum = 14;
                    break;
                case "eqrr":
                    commandNum = 15;
                    break;
            }
            commands[i-1] = new Command(commandNum, Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
        }

        int instructionPointer = 0;

        while (instructionPointer >= 0 && instructionPointer < commands.length) {
            registers[boundRegister] = instructionPointer;
            int[] inAndOut = {commands[instructionPointer].a, commands[instructionPointer].b, commands[instructionPointer].targetRegister};
            registers = Day16.executeOpcode(commands[instructionPointer].command, inAndOut, registers);
            instructionPointer = registers[boundRegister];
            instructionPointer++;
        }

        System.out.println("Part1 = " + registers[0]);
    }

    @Override
    public void part2() {
        String numPatternString = "(\\d+) (\\d+) (\\d+)";
        Pattern numPattern = Pattern.compile(numPatternString);

        int boundRegister = Integer.parseInt(input.split("\n")[0].substring(4));
        int[] registers = new int[6];

        registers[0] = 1;

        Command[] commands = new Command[input.split("\n").length-1];
        for (int i = 1; i < input.split("\n").length; i++) {
            String line = input.split("\n")[i];
            Matcher m = numPattern.matcher(line.substring(5));
            m.find();
            int commandNum = 0;
            switch (line.substring(0,4)) {
                case "addr":
                    commandNum = 0;
                    break;
                case "addi":
                    commandNum = 1;
                    break;
                case "mulr":
                    commandNum = 2;
                    break;
                case "muli":
                    commandNum = 3;
                    break;
                case "banr":
                    commandNum = 4;
                    break;
                case "bani":
                    commandNum = 5;
                    break;
                case "borr":
                    commandNum = 6;
                    break;
                case "bori":
                    commandNum = 7;
                    break;
                case "setr":
                    commandNum = 8;
                    break;
                case "seti":
                    commandNum = 9;
                    break;
                case "gtir":
                    commandNum = 10;
                    break;
                case "gtri":
                    commandNum = 11;
                    break;
                case "gtrr":
                    commandNum = 12;
                    break;
                case "eqir":
                    commandNum = 13;
                    break;
                case "eqri":
                    commandNum = 14;
                    break;
                case "eqrr":
                    commandNum = 15;
                    break;
            }
            commands[i-1] = new Command(commandNum, Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
        }

        int instructionPointer = 0;

        //System.out.println(Arrays.toString(registers));

        int test = 0;
        int test2 = 0;

        boolean testsOn = true;
        boolean test2On = false;

        while (instructionPointer >= 0 && instructionPointer < commands.length) {
            registers[boundRegister] = instructionPointer;
            int[] inAndOut = {commands[instructionPointer].a, commands[instructionPointer].b, commands[instructionPointer].targetRegister};
            registers = Day16.executeOpcode(commands[instructionPointer].command, inAndOut, registers);
            instructionPointer = registers[boundRegister];
            instructionPointer++;
        }

        System.out.println(Arrays.toString(registers));
        System.out.println(registers[0]);

        /*System.out.println(boundRegister);
        for (Command command: commands) {
            System.out.println(command);
        }*/
    }

    public static class Command {

        int command, a, b, targetRegister;

        public Command(int command, int a, int b, int targetRegister) {
            this.command = command;
            this.a = a;
            this.b = b;
            this.targetRegister = targetRegister;
        }

        @Override
        public String toString() {
            return command + " " + a + " " + b + " " + targetRegister;
        }
    }
}
