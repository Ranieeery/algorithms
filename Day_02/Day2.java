package Day_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) {

        int safe = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Day_02//input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] numbers = new int[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    numbers[i] = Integer.parseInt(parts[i]);
                }

                if (isSafe(numbers)) safe++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(safe);
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
}