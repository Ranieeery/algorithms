package Day_01;
    
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("Day_01//input.txt"));
        ArrayList<Integer> collumn1 = new ArrayList<>(), collumn2 = new ArrayList<>();

        input.forEach(line -> {
            String[] parts = line.trim().split("\\s+");
            collumn1.add(Integer.parseInt(parts[0]));
            collumn2.add(Integer.parseInt(parts[1]));
        });

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