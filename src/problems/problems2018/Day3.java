package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day {

    public Day3() {
        super(3, 2018);
    }

    public static final String TEST_INPUT = "#1 @ 1,3: 4x4\n" +
                                            "#2 @ 3,1: 4x4\n" +
                                            "#3 @ 5,5: 2x2";

    @Override
    public void part1() {
        int result = 0;

        String pattern = "#\\d{1,4} @ (\\d{1,3}),(\\d{1,3}): (\\d{1,3})x(\\d{1,3})";
        Pattern r = Pattern.compile(pattern);
        int[][] cloth = new int[1000][1000];
        for (String line: input.split("\n")) {
            Matcher matcher = r.matcher(line);
            matcher.find();
            int leftMargin = Integer.parseInt(matcher.group(1));
            int topMargin = Integer.parseInt(matcher.group(2));
            int width = Integer.parseInt(matcher.group(3));
            int height = Integer.parseInt(matcher.group(4));
            for (int w = leftMargin; w < leftMargin+width; w++) {
                for (int h = topMargin; h < topMargin+height; h++) {
                    cloth[w][h]++;
                }
            }
        }
        for (int w = 0; w < 1000; w++) {
            for (int h = 0; h < 1000; h++) {
                if (cloth[w][h] > 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    @Override
    public void part2() {
        String pattern = "#\\d{1,4} @ (\\d{1,3}),(\\d{1,3}): (\\d{1,3})x(\\d{1,3})";
        Pattern r = Pattern.compile(pattern);
        int[][] cloth = new int[1000][1000];
        ArrayList<int[]> claims = new ArrayList<>();
        for (String line: input.split("\n")) {
            Matcher matcher = r.matcher(line);
            matcher.find();
            int leftMargin = Integer.parseInt(matcher.group(1));
            int topMargin = Integer.parseInt(matcher.group(2));
            int width = Integer.parseInt(matcher.group(3));
            int height = Integer.parseInt(matcher.group(4));
            int[] claim = {leftMargin,topMargin,width,height};
            claims.add(claim);
            for (int w = leftMargin; w < leftMargin+width; w++) {
                for (int h = topMargin; h < topMargin+height; h++) {
                    cloth[w][h]++;
                }
            }
        }
        for (int i = 0; i < claims.size(); i++) {
            boolean claimValid = true;
            for (int w = claims.get(i)[0]; w < claims.get(i)[0]+claims.get(i)[2]; w++) {
                for (int h = claims.get(i)[1]; h < claims.get(i)[1]+claims.get(i)[3]; h++) {
                    if(cloth[w][h] > 1) {
                        claimValid = false;
                        break;
                    }
                }
                if(!claimValid) {
                    break;
                }
            }
            if(claimValid) {
                System.out.println(i+1);
                break;
            }
        }
    }
}
