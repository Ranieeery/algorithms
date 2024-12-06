package Day_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day2 {
    public static void main(String[] args) {
        ArrayList<String> collumn = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Day_02//input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Collections.addAll(collumn, line.split(","));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int control = 0;
        for (String s : collumn) {
            String[] parts = s.split("\\s+");
            int test = 0;
            for (String part : parts) {
                if (test == 0){
                    test = Integer.parseInt(part);
                    continue;
                }
                if (Integer.parseInt(part) >= test){
                    break;
                } else {
                    test = Integer.parseInt(part);
                    if (parts.length - 1 == Arrays.asList(parts).indexOf(part)){
                        control++;
                    }
                }

            }
        }

        System.out.println(control);

    }
}
