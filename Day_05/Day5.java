package Day_05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day_05/input.txt"));

        List<String> orderingRules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();
        boolean isUpdateSection = false;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) {
                isUpdateSection = true;
                continue;
            }
            if (isUpdateSection) {
                String[] parts = line.split(",");
                List<Integer> update = new ArrayList<>();
                for (String part : parts) {
                    update.add(Integer.parseInt(part.trim()));
                }
                updates.add(update);
            } else {
                orderingRules.add(line);
            }
        }

        Map<Integer, Set<Integer>> orderMap = new HashMap<>();
        for (String rule : orderingRules) {
            String[] parts = rule.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);
            orderMap.computeIfAbsent(before, _ -> new HashSet<>()).add(after);
        }

        int totalMiddlePages = 0;
        for (List<Integer> update : updates) {
            if (isValidUpdate(update, orderMap)) {
                int middlePage = update.get(update.size() / 2);
                totalMiddlePages += middlePage;
            }
        }

        System.out.println(totalMiddlePages);
    }

    private static boolean isValidUpdate(List<Integer> update, Map<Integer, Set<Integer>> orderMap) {
        Map<Integer, Integer> positions = new HashMap<>();
        for (int i = 0; i < update.size(); i++) {
            positions.put(update.get(i), i);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : orderMap.entrySet()) {
            int before = entry.getKey();
            if (!positions.containsKey(before)) continue;
            int beforePos = positions.get(before);

            for (int after : entry.getValue()) {
                if (!positions.containsKey(after)) continue;
                int afterPos = positions.get(after);
                if (beforePos >= afterPos) return false;
            }
        }
        return true;
    }
}
