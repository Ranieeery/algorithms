package Day_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2 {
    public static void main(String[] args) throws IOException {

        int safe = 0, safe2 = 0;
        List<String> input = Files.readAllLines(Path.of("Day_02//input.txt"));
        for (String line : input) {
            String[] parts = line.trim().split("\\s+");
            int[] numbers = new int[parts.length];

            for (int i = 0; i < parts.length; i++) numbers[i] = Integer.parseInt(parts[i]);

            if (isSafe(numbers)) safe++;

            if (isSafeRemovingLevel(numbers)) safe2++;
        }

        System.out.println(safe);
        System.out.println(safe2);
    }

    public static boolean isSafe(int[] numbers) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < numbers.length; i++) {
            int diff = numbers[i] - numbers[i - 1];

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) return false;

            if (diff > 0) decreasing = false;

            if (diff < 0) increasing = false;

            if (!increasing && !decreasing) return false;
        }
        return true;
    }

    public static boolean isSafeRemovingLevel(int[] numbers) {
        if (isSafe(numbers)) return true;

        for (int i = 0; i < numbers.length; i++) {
            int index = 0;
            int[] temp = new int[numbers.length - 1];

            for (int j = 0; j < numbers.length; j++) {
                if (j != i) temp[index++] = numbers[j];
            }

            if (isSafe(temp)) return true;
        }
        return false;
    }
}