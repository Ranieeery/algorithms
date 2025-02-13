import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HtmlAnalyzer {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments provided, use java HtmlAnalyzer <url>");
            return;
        }

        String URL = args[0];

        try {
            String content = connection(URL);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public static String connection(String URL) {
        String content = null;
        URLConnection urlConnection = null;

        try {
            urlConnection = new URL(URL).openConnection(); // deprecated i java 20
            Scanner scanner = new Scanner(urlConnection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();

            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }
}

// https://www.geeksforgeeks.org/command-line-arguments-in-java/
// new URL deprecated in java 20, the test is in java 17
// https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java