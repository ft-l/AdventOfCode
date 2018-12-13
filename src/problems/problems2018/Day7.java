package problems.problems2018;

import problems.Day;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends Day {

    public Day7() {
        super(7, 2018);
    }

    public static final String TEST_INPUT = "Step C must be finished before step A can begin.\n" +
                                            "Step C must be finished before step F can begin.\n" +
                                            "Step A must be finished before step B can begin.\n" +
                                            "Step A must be finished before step D can begin.\n" +
                                            "Step B must be finished before step E can begin.\n" +
                                            "Step D must be finished before step E can begin.\n" +
                                            "Step F must be finished before step E can begin.";

    public static void part1(String input) {
        boolean stepsAvailable = true;

        String steps = "";

        while(stepsAvailable) {
            if(!availableSteps(steps,input).isEmpty()) {
                steps += availableSteps(steps,input).charAt(0);
            } else {
                stepsAvailable = false;
            }
        }

        System.out.println(steps);
    }

    public static void part2(String input) {
        boolean stepsAvailable = true;

        String steps = "";

        int[] workers = new int[5];
        char[] workerJobs = {'/', '/', '/', '/', '/'};

        int time = 0;

        while(stepsAvailable) {
            if(!availableSteps(steps,input).isEmpty()) {
                String stepstoAssign = availableSteps(steps, input);
                boolean reload = false;
                for(char stepToAssign : stepstoAssign.toCharArray()) {
                    boolean stepBeingWorkedOn = steps.contains(""+stepToAssign);
                    for (int i = 0; i < workerJobs.length; i++) {
                        if(stepToAssign == workerJobs[i]) {
                            stepBeingWorkedOn = true;
                        }
                    }
                    for (int i = 0; i < workers.length; i++) {
                        if (workers[i] == 0 && workerJobs[i] != '/') {
                            steps += workerJobs[i];
                            workerJobs[i] = '/';
                            reload = true;
                        }
                    }
                    if(!stepBeingWorkedOn) {
                        for (int i = 0; i < workers.length; i++) {
                            if (workers[i] == 0) {
                                workerJobs[i] = stepToAssign;
                                workers[i] = ((int) stepToAssign) - 4;
                                break;
                            }
                        }
                    }
                }
                if(!reload) {
                    int nextWorkerIndex = closestToDoneIndex(workers);
                    int workTillWorkerAvailable = workers[nextWorkerIndex];
                    for (int i = 0; i < workers.length; i++) {
                        if (workers[i] > 0) {
                            workers[i] -= workTillWorkerAvailable;
                        }
                    }
                    time += workTillWorkerAvailable;
                }
            } else {
                stepsAvailable = false;
            }
        }
        System.out.println(time);
    }

    public static int closestToDoneIndex(int[] workers) {
        int index = 0;
        for (int i = 0; i < workers.length; i++) {
            if(workers[i] > workers[index]) {
                index = i;
            }
        }
        for (int i = 0; i < workers.length; i++) {
            if(0 < workers[i] && workers[i] < workers[index]) {
                index = i;
            }
        }
        return index;
    }

    public static String availableSteps(String completedSteps, String stepRequirements) {
        HashSet<Character> availableSteps = new HashSet<>();

        if(completedSteps.isEmpty()) {
            HashSet<Character> firstSteps = new HashSet<>();
            for (String line: stepRequirements.split("\n")) {
                String linePatternString = "Step ([A-Z]) must be finished before step ([A-Z]) can begin";
                Pattern linePattern = Pattern.compile(linePatternString);
                Matcher m = linePattern.matcher(line);
                m.find();
                firstSteps.add(m.group(1).charAt(0));
            }
            for (String line: stepRequirements.split("\n")) {
                String linePatternString = "Step ([A-Z]) must be finished before step ([A-Z]) can begin";
                Pattern linePattern = Pattern.compile(linePatternString);
                Matcher m = linePattern.matcher(line);
                m.find();
                firstSteps.remove(m.group(2).charAt(0));
            }
            String firstStepsString = "";
            for (char step: firstSteps) {
                firstStepsString += step;
            }
            return firstStepsString;
        }

        for (String line: stepRequirements.split("\n")) {
            String linePatternString = "Step ([A-Z]) must be finished before step ([A-Z]) can begin";
            Pattern linePattern = Pattern.compile(linePatternString);
            Matcher m = linePattern.matcher(line);
            m.find();
            availableSteps.add(m.group(1).charAt(0));
            availableSteps.add(m.group(2).charAt(0));
        }

        for (String line: stepRequirements.split("\n")) {
            String linePatternString = "Step ([A-Z]) must be finished before step ([A-Z]) can begin";
            Pattern linePattern = Pattern.compile(linePatternString);
            Matcher m = linePattern.matcher(line);
            m.find();
            if(!completedSteps.contains(m.group(1))) {
                availableSteps.remove(m.group(2).charAt(0));
            }

        }
        for (char step: completedSteps.toCharArray()) {
            availableSteps.remove(step);
        }
        String availableStepsString = "";
        for (char step: availableSteps) {
            availableStepsString += step;
        }
        return availableStepsString;
    }
}
