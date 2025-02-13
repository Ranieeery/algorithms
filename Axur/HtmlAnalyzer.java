import java.net.URL;
import java.util.Stack;
import java.util.Scanner;
import java.io.IOException;
import java.net.URLConnection;

public class HtmlAnalyzer {
    public static void main(String[] args) {

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
                String tag = getTag(line, 1);

                if (tag == null) {
                    isMalformed = true;
                    break;
                }

                tagStack.push(tag);
            } else if (isClosing(line)) {
                String tag = getTag(line, 2);

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

    private static String getTag(String line, int index) {
        String contentLine = line.substring(index, line.length() - 1).trim();
        return contentLine.isEmpty() || contentLine.isBlank() || contentLine.contains(" ") ? null : contentLine;
    }
}