package Day_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("Day_03/input.txt"));

        Matcher matcher = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)").matcher(input);

        System.out.println(mulFinder(matcher));
    }

    public static int mulFinder(Matcher matcher) {
        int total = 0;
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            total += x * y;
        }
        return total;
    }
}
