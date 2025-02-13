import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Stack;

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
            if (result != null) {
                System.out.println(result);
            }
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
        int deepestLevel = 0;
        String text = null;
        boolean isMalformed = false;
        Stack<String> tagStack = new Stack<>();
        String[] htmlLines = content.split("\n");

        for (String line : htmlLines) {

            if (line.trim().isEmpty()) {
                continue;
            }

            if (isOpeningTag(line)) {
                String contentLine = line.substring(1, line.length() - 1).trim();
                String tag = contentLine.isEmpty() || contentLine.isBlank() || contentLine.contains("")? null : content;

                if (tag == null) {
                    isMalformed = true;
                    break;
                }

                tagStack.push(tag);
            } else if (isClosingTag(line)) {
                String contentLine = line.substring(2, line.length() - 1).trim();
                
                if (contentLine == null || tagStack.isEmpty()) {
                    isMalformed = true;
                    break;
                }
                tagStack.pop();
            } else {
                int depth = tagStack.size();

                if (depth > deepestLevel) {
                    deepestLevel = depth;
                    text = line.trim();
                }
            }
        }

        if (!tagStack.isEmpty()) {
            isMalformed = true;
        }

        if (isMalformed) {
            return "malformed HTML";
        } else {
            return text;
        }
    }

    private static boolean isOpeningTag(String line) {
        return line.trim().startsWith("<") && line.trim().endsWith(">") && !line.startsWith("</");
    }

    private static boolean isClosingTag(String line) {
        return line.trim().startsWith("</") && line.trim().endsWith(">");
    }
}


// https://www.geeksforgeeks.org/command-line-arguments-in-java/
// new URL deprecated in java 20, the test is in java 17
// https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
// i need a LIFO (Last In First Out) structure to store the tags, so i will use a stack, thanks grokking algorithms
// Error, returned "malformed HTML". Too confusing, i'll refactor
// i forgot to change the substring when i copied it from the opening...
