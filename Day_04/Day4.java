package Day_04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day_04/input.txt"));
        char[][] grid = new char[lines.size()][lines.getFirst().length()]; 

        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        System.out.println(countXMAS(grid));
        System.out.println(countXMAS2(grid));
    }

    private static int countXMAS(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        int[] drow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dcol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (checkXMAS(grid, r, c, drow[dir], dcol[dir])) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkXMAS(char[][] grid, int row, int col, int drow, int dcol) {
        String target = "XMAS";
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < 4; i++) {
            int newRow = row + i * drow;
            int newCol = col + i * dcol;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            if (grid[newRow][newCol] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static int countXMAS2(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 'A' && isValidX(grid, r, c)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValidX(char[][] grid, int row, int col) {
        boolean diag1 = checkPattern(grid, row - 1, col - 1, row, col, row + 1, col + 1);
        boolean diag2 = checkPattern(grid, row - 1, col + 1, row, col, row + 1, col - 1);
        return diag1 && diag2;
    }

    private static boolean checkPattern(char[][] grid, int row1, int col1, int row, int col, int row2, int col2) {
        String[] patterns = {"MAS", "SAM"};
        int lines = grid.length;
        int cols = grid[0].length;

        if (row1 < 0 || row1 >= lines || col1 < 0 || col1 >= cols ||
            row2 < 0 || row2 >= lines || col2 < 0 || col2 >= cols) {
            return false;
        }

        for (String pattern : patterns) {
            if (grid[row1][col1] == pattern.charAt(0) &&
                grid[row][col] == pattern.charAt(1) &&
                grid[row2][col2] == pattern.charAt(2)) {
                return true;
            }
        }
        return false;
    }
}
