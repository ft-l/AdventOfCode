package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day8 extends Day {

    public static final String TEST_INPUT = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

    public Day8() {
        super(8, 2018);
    }

    public static void part1(String input) {
        int[] license = new int[input.split(" ").length];

        for (int i = 0; i < license.length; i++) {
            license[i] = Integer.parseInt(input.split(" ")[i]);
        }

        System.out.println(Arrays.toString(processNode(license)[0]));
        System.out.println(Arrays.toString(processNode(license)[1]));
    }

    public static void part2(String input) {

    }

    public static int[][] processNode(int[] node) {
        int childNodeNum = node[0];
        int metaDataCount = node[1];
        int[] metadataEntries = new int[metaDataCount];
        for (int i = 0; i < metaDataCount; i++) {
            metadataEntries[i] = node[node.length-metaDataCount+i];
        }
        int[] header = {childNodeNum, metaDataCount};
        int[][] result = {header, metadataEntries};
        return result;
    }
}
