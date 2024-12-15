package Day_04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day_04/input_test2.txt"));
        char[][] grid = new char[lines.size()][lines.getFirst().length()]; // remember me css grid

        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        System.out.println(countXMAS(grid));
        System.out.println(countXMAS2(grid));
    }

    private static int countXMAS(char[][] grid) {
        int count = 0;
        int line = grid.length;
        int cols = grid[0].length;

        int[] dline = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dcol = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < cols; j++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (checkXMAS(grid, i, j, dline[dir], dcol[dir])) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkXMAS(char[][] grid, int line, int col, int dline, int dcol) {
        String target = "XMAS";
        int lines = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < 4; i++) {
            int newLine = line + i * dline;
            int newCol = col + i * dcol;

            if (newLine < 0 || newLine >= lines || newCol < 0 || newCol >= cols) {
                return false;
            }

            if (grid[newLine][newCol] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static int countXMAS2(char[][] grid) {
        int count = 0;
        int lines = grid.length;
        int cols = grid[0].length;

        for (int i = 1; i < lines - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 'A' && isValidX(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValidX(char[][] grid, int line, int col) {
        return (checkMAS(grid, line, col, -1, -1) && checkMAS(grid, line, col, 1, 1)) ||
                (checkMAS(grid, line, col, -1, 1) && checkMAS(grid, line, col, 1, -1));
    }

    private static boolean checkMAS(char[][] grid, int line, int col, int dline, int dcol) {
        String[] patterns = { "MAS", "SAM" };
        int lines = grid.length;
        int cols = grid[0].length;

        for (String pattern : patterns) {
            int line1 = line + dline * -1;
            int col1 = col + dcol * -1;
            int line2 = line + dline;
            int col2 = col + dcol;

            if (line1 < 0 || line1 >= lines || col1 < 0 || col1 >= cols ||
                    line2 < 0 || line2 >= lines || col2 < 0 || col2 >= cols) {
                continue;
            }

            if (grid[line1][col1] == pattern.charAt(0) &&
                    grid[line][col] == pattern.charAt(1) &&
                    grid[line2][col2] == pattern.charAt(2)) {
                return true;
            }
        }
        return false;
    }
}
