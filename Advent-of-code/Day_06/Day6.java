package Day_06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {
    private static final int[][] DIRS = {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
    };

    public static void main(String[] args) throws Exception {
        List<String> input = Files.readAllLines(Path.of("Day_06/input.txt"));

        char[][] map = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            map[i] = input.get(i).toCharArray();
        }

        int rows = map.length;
        int cols = map[0].length;

        int[] guardInfo = findGuardPosDir(map, rows, cols);
        int guardRow = guardInfo[0];
        int guardCol = guardInfo[1];
        int guardDir = guardInfo[2];

        Set<Long> visited = new HashSet<>();
        visited.add(encode(guardRow, guardCol));

        while (true) {
            int fRow = guardRow + DIRS[guardDir][0];
            int fCol = guardCol + DIRS[guardDir][1];

            boolean inside = inBounds(fRow, fCol, rows, cols);

            if (inside && map[fRow][fCol] == '#') {
                guardDir = (guardDir + 1) % 4;
            } else {
                guardRow = fRow;
                guardCol = fCol;

                if (!inBounds(guardRow, guardCol, rows, cols)) {
                    break;
                }

                visited.add(encode(guardRow, guardCol));
            }
        }

        int result2 = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == guardRow && c == guardCol) continue;
                if (map[r][c] == '#') continue;

                char pos = map[r][c];
                map[r][c] = '#';

                if (causeLoop(map, rows, cols)) {
                    result2++;
                }

                map[r][c] = pos;
            }
        }

        int result = visited.size();
        System.out.println(result);
        System.out.println(result2);
    }

    private static int[] findGuardPosDir(char[][] map, int rows, int cols) {
        int guardRow = -1, guardCol = -1, guardDir = -1;
        outer:
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = map[r][c];
                if (ch == '^' || ch == '>' || ch == 'v' || ch == '<') {
                    guardRow = r;
                    guardCol = c;
                    guardDir = direction(ch);
                    break outer;
                }
            }
        }
        return new int[]{guardRow, guardCol, guardDir};
    }

    private static boolean inBounds(int r, int c, int rows, int cols) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private static int direction(char ch) {
        return switch (ch) {
            case '^' -> 0;
            case '>' -> 1;
            case 'v' -> 2;
            case '<' -> 3;
            default -> throw new IllegalArgumentException(ch + " não é válido");
        };
    }

    
    private static boolean causeLoop(char[][] map, int rows, int cols) {
        int[] guardInfo = findGuardPosDir(map, rows, cols);
        int guardRow = guardInfo[0];
        int guardCol = guardInfo[1];
        int guardDir = guardInfo[2];

        if (guardDir == -1) {
            return false;
        }

        Set<Long> visitedStates = new HashSet<>();
        visitedStates.add(encodeState(guardRow, guardCol, guardDir));

        while (true) {
            int fRow = guardRow + DIRS[guardDir][0];
            int fCol = guardCol + DIRS[guardDir][1];

            boolean inside = inBounds(fRow, fCol, rows, cols);
            
            if (inside && map[fRow][fCol] == '#') {
                guardDir = (guardDir + 1) % 4;
            } else {
                guardRow = fRow;
                guardCol = fCol;
                
                if (!inBounds(guardRow, guardCol, rows, cols)) {
                    return false;
                }
                
                long state = encodeState(guardRow, guardCol, guardDir);
                if (!visitedStates.add(state)) {
                    return true;
                }
            }
        }
    }
    
    private static long encodeState(int r, int c, int dir) {
        return (encode(r, c) << 3) | (dir & 0b111);
    }
    
    private static long encode(int r, int c) {
        return ((long) r << 32) | (c & 0xffffffffL);
    }
}
