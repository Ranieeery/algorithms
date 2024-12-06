package Day_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {
    public static void main(String[] args) {

        ArrayList<Integer> collumn1 = new ArrayList<>();
        ArrayList<Integer> collumn2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Day_01//input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    collumn1.add(Integer.parseInt(parts[0]));
                    collumn2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(collumn1);
        Collections.sort(collumn2);
        
        // Part 1
        int totalPart1 = 0;
        for (int i = 0; i < collumn1.size(); i++) {
            totalPart1 += Math.abs(collumn1.get(i) - collumn2.get(i));
        }

        System.out.println("Total part 1: " + totalPart1);

        // Part 2
        int totalPart2 = 0;
        for (Integer col1 : collumn1) {
            int amount = 0;
            for (Integer col2 : collumn2) {
                if (col1.equals(col2)) {
                    amount++;
                }
            }
            totalPart2 += col1 * amount;
        }

        System.out.println("Total part 2: " + totalPart2);
    }
}