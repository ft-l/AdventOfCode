package problems.problems2018;

import problems.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day8 extends Day {

    public static final String TEST_INPUT = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

    public static int METADATA_ENTRY_SUM = 0;

    public Day8() {
        super(8, 2018);
    }

    public static void part1(String input) {
        String[] input2 = input.split(" ");
        int[] license = new int[input2.length];

        for (int i = 0; i < license.length; i++) {
            license[i] = Integer.parseInt(input2[i].replaceAll("\n", ""));
        }
        processNode(license, 0);
        System.out.println(METADATA_ENTRY_SUM);
    }

    public static void part2(String input) {

    }

    public static int processNode(int[] node, int layer) {
        String checkpoint = "";
        for (int i = 0; i < layer; i++) {
            checkpoint += "  ";
        }
        //System.out.println(checkpoint + "node: " + Arrays.toString(node));
        int childNodeCount = node[0];
        int metaDataCount = node[1];
        int subNodesLength = 0;
        if(childNodeCount > 0) {
            int[] remainingNode = newCutOffArray(node, 2);
            for (int i = 0; i < childNodeCount; i++) {
                subNodesLength += processNode(newCutOffArray(remainingNode, subNodesLength), layer + 1);
                //System.out.println(checkpoint + "subNodeLength " + subNodesLength);
            }
            for (int i = 0; i < metaDataCount; i++) {
                //System.out.println(checkpoint + "node[i+subNodesLength+2]: " + node[i+subNodesLength+2]);
                METADATA_ENTRY_SUM += node[i+subNodesLength+2];
            }
            return 2+subNodesLength+metaDataCount;
        } else {
            for (int i = 2; i < 2+metaDataCount; i++) {
                //System.out.println(checkpoint + "node[i]: " + node[i]);
                METADATA_ENTRY_SUM += node[i];
            }
            return 2+metaDataCount;
        }
    }

    public static int[] newCutOffArray(int[] array, int cutoffPoint) {
        int[] result = new int[array.length-cutoffPoint];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[i+cutoffPoint];
        }
        return result;
    }
}
