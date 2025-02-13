import java.net.URL;
import java.util.Stack;
import java.util.Scanner;
import java.io.IOException;
import java.net.URLConnection;

public class HtmlAnalyzer {
    public static void main(String[] args) {

        if (args.length == 0) {
             System.out.println("No args provided, use java HtmlAnalyzer <url>");
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
            urlConnection = new URL(URL).openConnection();
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
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            if (isOpening(line)) {
                String contentLine = line.substring(1, line.length() - 1).trim();
                String tag = contentLine.isEmpty() || contentLine.isBlank() || contentLine.contains(" ") ? null : contentLine;
                
                if (tag == null) {
                    isMalformed = true;
                    break;
                }
                
                tagStack.push(tag);
            } else if (isClosing(line)) {
                String contentLine = line.substring(2, line.length() - 1).trim();
                String tag = contentLine.isEmpty() || contentLine.isBlank() || contentLine.contains(" ") ? null : contentLine;

                if (tag == null || tagStack.isEmpty() || !tagStack.peek().equals(tag)) {
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

    private static boolean isOpening(String line) {
        return line.startsWith("<") && line.trim().endsWith(">") && !line.startsWith("</");
    }

    private static boolean isClosing(String line) {
        return line.startsWith("</") && line.trim().endsWith(">");
    }
}

// https://www.geeksforgeeks.org/command-line-arguments-in-java/
// new URL deprecated in java 20, the test is in java 17
// https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
// i need a LIFO (Last In First Out) structure to store the tags, so i will use a stack, thanks grokking algorithms
// Error, returned "malformed HTML". Too confusing, i'll refactor
// i forgot to change the substring when i copied it from the opening...
// A space. The problem was a space.
// Else if and else are not correct, i need to check if the tag is malformed
// A trim. The problem was a trim.
