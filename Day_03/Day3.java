package Day_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("Day_03/input.txt"));

        Matcher matcher = Pattern.compile("(mul\\((\\d{1,3}),(\\d{1,3})\\))|(do\\(\\))|(don't\\(\\))").matcher(input);

        System.out.println(mulFinder(matcher));
        matcher.reset();
        System.out.println(mulEnabler(matcher));
    }

    public static int mulFinder(Matcher matcher) {
        int total = 0;
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                int x = Integer.parseInt(matcher.group(2));
                int y = Integer.parseInt(matcher.group(3));
                total += x * y;
            }
        }
        return total;
    }

    public static int mulEnabler(Matcher matcher) {
        int total = 0;
        boolean control = true;
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                if (control) {
                    int x = Integer.parseInt(matcher.group(2));
                    int y = Integer.parseInt(matcher.group(3));
                    total += x * y;
                }
            } else if (matcher.group(4) != null) {
                control = true;
            } else if (matcher.group(5) != null) {
                control = false;
            }
        }
        return total;
    }
}
