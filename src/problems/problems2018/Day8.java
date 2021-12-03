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

    @Override
    public void part1() {
        String[] input2 = input.split(" ");
        int[] license = new int[input2.length];

        for (int i = 0; i < license.length; i++) {
            license[i] = Integer.parseInt(input2[i].replaceAll("\n", ""));
        }
        System.out.println(processNode(license, 0).getPart1Value());
    }

    @Override
    public void part2() {
        String[] input2 = input.split(" ");
        int[] license = new int[input2.length];

        for (int i = 0; i < license.length; i++) {
            license[i] = Integer.parseInt(input2[i].replaceAll("\n", ""));
        }
        System.out.println(processNode(license, 0).getPart2Value());
    }

    public static Node processNode(int[] node, int layer) {
        String checkpoint = "";
        for (int i = 0; i < layer; i++) {
            checkpoint += "  ";
        }
        //System.out.println(checkpoint + "node: " + Arrays.toString(node));
        int childNodeCount = node[0];
        int metaDataCount = node[1];
        int subNodesLength = 0;
        ArrayList<Node> subNodes = new ArrayList<>();
        int[] metaDataEntries = new int[metaDataCount];
        if(childNodeCount > 0) {
            int[] remainingNode = newCutOffArray(node, 2);
            for (int i = 0; i < childNodeCount; i++) {
                subNodes.add(processNode(newCutOffArray(remainingNode, subNodesLength), layer + 1));
                subNodesLength += subNodes.get(i).length;
            }
            for (int i = 2; i < 2+metaDataCount; i++) {
                //System.out.println(checkpoint + "node[i+subNodesLength+2]: " + node[i+subNodesLength+2]);
                METADATA_ENTRY_SUM += node[i+subNodesLength];
                metaDataEntries[i-2] = node[i+subNodesLength];
            }
            return new Node(subNodes, metaDataEntries);
        } else {
            for (int i = 2; i < 2+metaDataCount; i++) {
                //System.out.println(checkpoint + "node[i]: " + node[i]);
                METADATA_ENTRY_SUM += node[i];
                metaDataEntries[i-2] = node[i];
            }
            Node result = new Node(subNodes, metaDataEntries);
            return result;
        }
    }

    public static int[] newCutOffArray(int[] array, int cutoffPoint) {
        int[] result = new int[array.length-cutoffPoint];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[i+cutoffPoint];
        }
        return result;
    }

    public static class Node {

        ArrayList<Node> subNodes;
        int[] metaData;
        int length;

        public Node(ArrayList<Node> subNodes, int[] metaData) {
            this.subNodes = subNodes;
            this.metaData = metaData;
            length = 2+metaData.length;
            for (Node subNode: subNodes) {
                length += subNode.length;
            }
        }

        public int getPart1Value() {
            int value = 0;
            for (int metaDataEntry: metaData) {
                value += metaDataEntry;
            }
            for (Node subNode: subNodes) {
                value += subNode.getPart1Value();
            }
            return value;
        }

        public int getPart2Value() {
            int value = 0;
            if (subNodes.size() > 0) {
                for (int metaDataEntry: metaData) {
                    if(metaDataEntry < subNodes.size()+1) {
                        value += subNodes.get(metaDataEntry-1).getPart2Value();
                    }
                }
            } else {
                for (int metaDataEntry: metaData) {
                    value += metaDataEntry;
                }
            }
            return value;
        }

        @Override
        public String toString() {
            String string = subNodes.toString();
            return string;
        }
    }
}
