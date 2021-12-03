package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 extends Day {

    public Day4() {
        super(4, 2018);
    }

    public static final String TEST_INPUT = "[1518-11-01 00:00] Guard #10 begins shift\n" +
            "[1518-11-01 00:05] falls asleep\n" +
            "[1518-11-01 00:25] wakes up\n" +
            "[1518-11-01 00:30] falls asleep\n" +
            "[1518-11-01 00:55] wakes up\n" +
            "[1518-11-01 23:58] Guard #99 begins shift\n" +
            "[1518-11-02 00:40] falls asleep\n" +
            "[1518-11-02 00:50] wakes up\n" +
            "[1518-11-03 00:05] Guard #10 begins shift\n" +
            "[1518-11-03 00:24] falls asleep\n" +
            "[1518-11-03 00:29] wakes up\n" +
            "[1518-11-04 00:02] Guard #99 begins shift\n" +
            "[1518-11-04 00:36] falls asleep\n" +
            "[1518-11-04 00:46] wakes up\n" +
            "[1518-11-05 00:03] Guard #99 begins shift\n" +
            "[1518-11-05 00:45] falls asleep\n" +
            "[1518-11-05 00:55] wakes up";

    @Override
    public void part1() {
        String guardPatternString = "\\[1518-\\d{2}-\\d{2} \\d{2}:\\d{2}] Guard #(\\d{1,4}) begins shift";
        String wakePatternString = "\\[1518-\\d{2}-\\d{2} \\d{2}:(\\d{2})\\] wakes up";
        String asleepPatternString = "\\[1518-\\d{2}-\\d{2} \\d{2}:(\\d{2})\\] falls asleep";

        Pattern guardPattern = Pattern.compile(guardPatternString);
        Pattern wakePattern = Pattern.compile(wakePatternString);
        Pattern asleepPattern = Pattern.compile(asleepPatternString);

        HashMap<Integer,Integer> guardSleepTime = new HashMap<>();

        int currentGuard = 0;

        ArrayList<String> sortedInput = sort(input.split("\n"));

        for (int i = 0; i < sortedInput.size(); i++) {
            Matcher guardMatcher = guardPattern.matcher(sortedInput.get(i));
            Matcher wakeMatcher = wakePattern.matcher(sortedInput.get(i));
            if(guardMatcher.find()) {
                currentGuard = Integer.parseInt(guardMatcher.group(1));
                if(!guardSleepTime.keySet().contains(currentGuard)) {
                    guardSleepTime.put(currentGuard,0);
                }
            } else if(wakeMatcher.find()) {
                guardSleepTime.put(currentGuard,
                        guardSleepTime.get(currentGuard) + timeDifference(sortedInput.get(i-1), sortedInput.get(i))
                );
            }
        }

        int sleepiestGuardId = 0;
        int sleepiestGuardIdSleepTime = 0;

        for (int key: guardSleepTime.keySet()) {
            if(guardSleepTime.get(key) > sleepiestGuardIdSleepTime) {
                sleepiestGuardId = key;
                sleepiestGuardIdSleepTime = guardSleepTime.get(key);
            }
        }

        boolean onGuardDay = false;
        int[] sleepCount = new int[60];

        for (int i = 0; i < sortedInput.size(); i++) {
            Matcher guardMatcher = guardPattern.matcher(sortedInput.get(i));
            Matcher wakeMatcher = wakePattern.matcher(sortedInput.get(i));
            if(guardMatcher.find()) {
                if(sleepiestGuardId == Integer.parseInt(guardMatcher.group(1))) {
                    onGuardDay = true;
                } else {
                    onGuardDay = false;
                }
            } else if(wakeMatcher.find() && onGuardDay) {
                Matcher asleepMatcher = asleepPattern.matcher(sortedInput.get(i-1));
                asleepMatcher.find();
                for (int j = Integer.parseInt(asleepMatcher.group(1)); j < Integer.parseInt(wakeMatcher.group(1)); j++) {
                    sleepCount[j]++;
                }
            }
        }

        int mostSleptDay = 0;
        int mostSlept = 0;

        for (int i = 0; i < sleepCount.length; i++) {
            if(sleepCount[i] > mostSlept) {
                mostSleptDay = i;
                mostSlept = sleepCount[i];
            }
        }
        System.out.println(sleepiestGuardId*mostSleptDay);
    }

    @Override
    public void part2() {
        String guardPatternString = "\\[1518-\\d{2}-\\d{2} \\d{2}:\\d{2}] Guard #(\\d{1,4}) begins shift";
        String wakePatternString = "\\[1518-\\d{2}-\\d{2} \\d{2}:(\\d{2})\\] wakes up";
        String asleepPatternString = "\\[1518-\\d{2}-\\d{2} \\d{2}:(\\d{2})\\] falls asleep";

        Pattern guardPattern = Pattern.compile(guardPatternString);
        Pattern wakePattern = Pattern.compile(wakePatternString);
        Pattern asleepPattern = Pattern.compile(asleepPatternString);

        HashSet<Integer> guardIDs = new HashSet<>();

        ArrayList<String> sortedInput = sort(input.split("\n"));

        for (String line: sortedInput) {
            Matcher guardMatch = guardPattern.matcher(line);
            if(guardMatch.find()) {
                guardIDs.add(Integer.parseInt(guardMatch.group(1)));
            }
        }

        HashMap<Integer, int[]> guardSleepSchedules = new HashMap<>();

        for (int id : guardIDs) {
            boolean onGuardDay = false;
            int[] sleepCount = new int[60];

            for (int i = 0; i < sortedInput.size(); i++) {
                Matcher guardMatcher = guardPattern.matcher(sortedInput.get(i));
                Matcher wakeMatcher = wakePattern.matcher(sortedInput.get(i));
                if(guardMatcher.find()) {
                    if(id == Integer.parseInt(guardMatcher.group(1))) {
                        onGuardDay = true;
                    } else {
                        onGuardDay = false;
                    }
                } else if(wakeMatcher.find() && onGuardDay) {
                    Matcher asleepMatcher = asleepPattern.matcher(sortedInput.get(i-1));
                    asleepMatcher.find();
                    for (int j = Integer.parseInt(asleepMatcher.group(1)); j < Integer.parseInt(wakeMatcher.group(1)); j++) {
                        sleepCount[j]++;
                    }
                }
            }
            guardSleepSchedules.put(id, sleepCount);
        }

        int mostSleptOnDay = 0;
        int mostSleptOnDayGuardID = 0;
        int mostSleptOnDaySleepCount = 0;

        for (int id: guardIDs) {
            for (int i = 0; i < 60; i++) {
                if(guardSleepSchedules.get(id)[i] > mostSleptOnDaySleepCount) {
                    mostSleptOnDay = i;
                    mostSleptOnDayGuardID = id;
                    mostSleptOnDaySleepCount = guardSleepSchedules.get(id)[i];
                }
            }
        }

        System.out.println(mostSleptOnDay*mostSleptOnDayGuardID);

        //System.out.println(sleepiestGuardId*mostSleptDay);
    }

    public static ArrayList<String> sort(String[] input) {
        ArrayList<String> sortedList = new ArrayList<>();
        sortedList.add(input[0]);
        for (int i = 1; i < input.length; i++) {
            boolean stringSorted = false;
            for(int x = 0; x < sortedList.size(); x++) {
                switch (compare(input[i],sortedList.get(x))) {
                    case A_AFTER_B:
                        if(x == sortedList.size()-1) {
                            sortedList.add(x+1,input[i]);
                            stringSorted = true;
                        }
                        break;
                    case A_BEFORE_B:
                        stringSorted = true;
                        sortedList.add(x,input[i]);
                        break;
                    case A_OR_B:
                        System.out.println("a or b");
                        break;
                }
                if(stringSorted) {
                    break;
                }
            }
        }
        return sortedList;
    }

    private static final int A_BEFORE_B = -1;
    private static final int A_OR_B = 0;
    private static final int A_AFTER_B = 1;

    public static int compare(String a, String b) {
        if(timeDifference(a,b) > 0) {
            return A_BEFORE_B;
        } else {
            return A_AFTER_B;
        }
    }

    public static int timeDifference(String a, String b) {
        String timePattern = "\\[1518-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})]";
        Pattern p = Pattern.compile(timePattern);
        Matcher aMatcher = p.matcher(a);
        Matcher bMatcher = p.matcher(b);
        aMatcher.find();
        bMatcher.find();
        int monthDif = Integer.parseInt(aMatcher.group(1)) - Integer.parseInt(bMatcher.group(1));
        int dayDif   = Integer.parseInt(aMatcher.group(2)) - Integer.parseInt(bMatcher.group(2));
        int hourDif  = Integer.parseInt(aMatcher.group(3)) - Integer.parseInt(bMatcher.group(3));
        int minDif   = Integer.parseInt(aMatcher.group(4)) - Integer.parseInt(bMatcher.group(4));

        int dif = minDif+(hourDif*60)+(dayDif*24*60)+(monthDif*31*24*60);

        return -dif;
    }
}
