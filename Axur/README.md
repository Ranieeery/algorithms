EASTER_EGG_URLS

# Technical Test: Software Development Intern

## Environment Setup

- WSL: Ubuntu 24.04.1

- Java: OpenJDK 17.0.14

  ```bash
  raniery@Raniery:~/projects/axur$ java -version
  openjdk version "17.0.14" 2025-01-21 LTS
  OpenJDK Runtime Environment Corretto-17.0.14.7.1 (build 17.0.14+7-LTS)
  OpenJDK 64-Bit Server VM Corretto-17.0.14.7.1 (build 17.0.14+7-LTS, mixed mode, sharing)
  ```

## Getting Started

1. Compile the code

  ```bash
  javac HtmlAnalyzer.java
  ```

2. Run the code

  ```bash
  java HtmlAnalyzer http://hiring.axreng.com/internship/example1.html
  ```

## Knowledge acquired

- `new URL` is deprecated in Java 20, URI was used to access the content of the page

- `Stack` uses Generics, so it's necessary to specify the type of data that will be stored

- Consolidation of knowledge about data structures, such as stacks

- What  `String[] args` really means in Java

Some notes made while developing the code:

```java
// https://www.geeksforgeeks.org/command-line-arguments-in-java/
// new URL deprecated in java 20, the test is in java 17
// https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
// i need a LIFO (Last In First Out) structure to store the tags, so i will use a stack, thanks grokking algorithms
// Error, returned "malformed HTML". Too confusing, i'll refactor
// i forgot to change the substring when i copied it from the isOpening...
// A space. The problem was a space.
// Else if and else are not correct, i need to check if the tag is malformed
// A trim. The problem was a trim.
```

## Considerations

Although it's a simple test, it was interesting to develop the code since using Java for something like this was new to me, where normally I'd use Python with Beautiful Soup or Jsoup in Java itself, which I got to see while researching how to solve the test.

Even though I hadn't scraped the content of a web page with Java before, once I managed to save it in a String I found it very similar to the challenges at [Advent of Code](https://adventofcode.com/) that involve manipulating strings and data structures, which I participated in last year.

My first solution took into account the URL as I found on Stack Overflow, but after researching more on the subject, I discovered that `new URL` is deprecated as of Java 20 so I used URI to access the content of the page but it may be possible with HttpClient as well. The final solution was quite simple, but I believe it covered what was requested, I also managed to find the easter egg in `example6.html` and complete what was marked as optional.

I have also added an optional parameter to the regex in case it is CRLF instead of LF. There's also a private repository on GitHub with my commits and progress, which I can share if necessary.
