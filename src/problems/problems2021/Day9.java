package problems.problems2021;

import problems.Day;

import java.util.Arrays;

public class Day9 extends Day {

    public Day9() {
        super(9, 2021);
    }

    @Override
    public void part1() {
        String[] inputLines = input.split("\n");
        int[][] map = new int[inputLines.length][inputLines[0].length()];
        for (int r = 0; r < inputLines.length; r++) {
            for (int c = 0; c < inputLines[r].length(); c++) {
                map[r][c] = Integer.parseInt(inputLines[r].charAt(c) + "");
            }
        }
        int totalRiskLevel = 0;
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (isLowPoint(map, r, c)) {
                    totalRiskLevel += 1 + map[r][c];
                }
            }
        }
        System.out.println(totalRiskLevel);
    }

    @Override
    public void part2() {
        String[] inputLines = input.split("\n");
        int[][] map = new int[inputLines.length][inputLines[0].length()];
        for (int r = 0; r < inputLines.length; r++) {
            for (int c = 0; c < inputLines[r].length(); c++) {
                map[r][c] = Integer.parseInt(inputLines[r].charAt(c) + "");
            }
        }
        DisjointSet set = new DisjointSet(map.length * map[0].length);
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] != 9) {
                    if (c != map[r].length-1) {
                        if (map[r][c+1] != 9) {
                            set.union(set.find(c + (r*map[r].length)), set.find((c+1) + (r*map[r].length)));
                        }
                    }
                    if (r != map.length-1) {
                        if (map[r+1][c] != 9) {
                            set.union(set.find(c + (r*map[r].length)), set.find(c + ((r+1)*map[r+1].length)));
                        }
                    }
                }
            }
        }
        int[] finalSizes = set.s;
        Arrays.sort(finalSizes);
        System.out.println(finalSizes[0] * finalSizes[1] * finalSizes[2] * -1);
    }

    public boolean isLowPoint(int[][] map, int r, int c) {
        if (r == 0) {
            if (c == 0) {
                return map[r + 1][c] > map[r][c] && map[r][c + 1] > map[r][c];
            } else if (c == map[r].length-1) {
                return map[r + 1][c] > map[r][c] && map[r][c - 1] > map[r][c];
            } else {
                return map[r + 1][c] > map[r][c] && map[r][c + 1] > map[r][c] && map[r][c - 1] > map[r][c];
            }
        } else if (r == map.length-1) {
            if (c == 0) {
                return map[r - 1][c] > map[r][c] && map[r][c + 1] > map[r][c];
            } else if (c == map[r].length-1) {
                return map[r - 1][c] > map[r][c] && map[r][c - 1] > map[r][c];
            } else {
                return map[r - 1][c] > map[r][c] && map[r][c + 1] > map[r][c] && map[r][c - 1] > map[r][c];
            }
        } else {
            if (c == 0) {
                return map[r + 1][c] > map[r][c] && map[r - 1][c] > map[r][c] && map[r][c + 1] > map[r][c];
            } else if (c == map[r].length-1) {
                return map[r + 1][c] > map[r][c] && map[r - 1][c] > map[r][c] && map[r][c - 1] > map[r][c];
            } else {
                return map[r + 1][c] > map[r][c] && map[r - 1][c] > map[r][c] && map[r][c + 1] > map[r][c] && map[r][c - 1] > map[r][c];
            }
        }
    }

    public static class DisjointSet {

        public DisjointSet( int numElements ) {
            s = new int [ numElements ];
            for (int i = 0; i < numElements; i++) {
                s[i] = -1;
            }
        }

        public void union( int root1, int root2 ) {
            if (root1 == root2) {
                return;
            }
            if (s[root2] < s[root1]) {
                s [root2] = s[root2] + s[root1];
                s [root1] = root2;
            } else {
                s [root1] = s[root1] + s[root2];
                s [root2] = root1;
            }
        }

        public int find( int x ) {
            if( s[ x ] < 0 )
                return x;
            else
                return s[ x ] = find( s[ x ] );
        }

        private final int [ ] s;
    }
}
