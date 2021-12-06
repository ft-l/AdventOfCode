package problems.problems2021;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day6 extends Day {

    public Day6() {
        super(6, 2021);
    }

    @Override
    public void part1() {
        String[] agesStrings = input.split(",");
        ArrayList<Integer> ages = new ArrayList<>();
        for (String ageString :
                agesStrings) {
            ages.add(Integer.parseInt(ageString.trim()));
        }
        long[] numberOfEachAge = new long[9];
        for (Integer age :
                ages) {
            numberOfEachAge[age]++;
        }
        for (int i = 0; i < 80; i++) {
            numberOfEachAge = getNextAgeBucketArray(numberOfEachAge);
        }
        long sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += numberOfEachAge[i];
        }
        System.out.println(sum);
    }

    @Override
    public void part2() {
        String[] agesStrings = input.split(",");
        ArrayList<Integer> ages = new ArrayList<>();
        for (String ageString :
                agesStrings) {
            ages.add(Integer.parseInt(ageString.trim()));
        }
        long[] numberOfEachAge = new long[9];
        for (Integer age :
                ages) {
            numberOfEachAge[age]++;
        }
        for (int i = 0; i < 256; i++) {
            numberOfEachAge = getNextAgeBucketArray(numberOfEachAge);
        }
        long sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += numberOfEachAge[i];
        }
        System.out.println(sum);
    }

    public long[] getNextAgeBucketArray(long[] currentAgeBucket) {
        long[] newBucket = new long[9];
        System.arraycopy(currentAgeBucket, 1, newBucket, 0, 8);
        newBucket[8] = currentAgeBucket[0];
        newBucket[6] = newBucket[6] + currentAgeBucket[0];
        return newBucket;
    }
}
