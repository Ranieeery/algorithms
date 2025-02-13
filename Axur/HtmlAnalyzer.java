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
            String result = viewStructure(content);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public static String connection(String URL) {
        String content = null;
        URLConnection urlConnection = null;

        try {
            urlConnection = new URL(URL).openConnection(); // deprecated in java 20
            Scanner scanner = new Scanner(urlConnection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }

    public static String viewStructure(String content) {
        String deepestLevel = null;
        boolean isMalformed = false;
        String[] htmlLines = content.split("\n");

        for (String line : htmlLines) {
            System.out.println(line);
            System.out.println("---");

            
        }


        return "";
    }
}


// https://www.geeksforgeeks.org/command-line-arguments-in-java/
// new URL deprecated in java 20, the test is in java 17
// https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java