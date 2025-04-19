import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Stack;

public class HtmlAnalyzer {
    public static void main(String[] args) {

        String URL = args[0];

        String content = connection(URL);
        String result = viewStructure(content);

        if (result != null) {
            System.out.println(result);
        }

    }

    public static String connection(String url) {
        try {
            URLConnection urlConnection = URI.create(url).toURL().openConnection();
            try (Scanner scanner = new Scanner(urlConnection.getInputStream())) {
                scanner.useDelimiter("\\Z");
                return scanner.next();
            }
        } catch (IOException e) {
            System.out.println("URL connection error");
            System.exit(1);
        }
        return null;
    }

    public static String viewStructure(String content) {
        int deepestLevel = 0;
        String text = null;
        boolean isMalformed = false;
        Stack<String> tagStack = new Stack<>();
        String[] htmlLines = content.split("\r?\n");

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
        return contentLine.isBlank() || contentLine.contains(" ") ? null : contentLine;
    }
}