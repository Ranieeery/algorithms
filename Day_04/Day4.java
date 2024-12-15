package Day_04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day_04/input.txt"));
        char[][] grid = new char[lines.size()][lines.getFirst().length()]; // remember me css grid

        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        int count = countXMAS(grid);
        System.out.println("XMAS appears " + count + " times");
    }

    private static int countXMAS(char[][] grid) {
        int count = 0;
        int line = grid.length;
        int cols = grid[0].length;

        int[] dline = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dcollumn = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < cols; j++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (checkXMAS(grid, i, j, dline[dir], dcollumn[dir])) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkXMAS(char[][] grid, int row, int col, int dr, int dc) {
        String target = "XMAS";
        int line = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < 4; i++) {
            int newLine = row + i * dr;
            int newCol = col + i * dc;

            if (newLine < 0 || newLine >= line || newCol < 0 || newCol >= cols) {
                return false;
            }

            if (grid[newLine][newCol] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
