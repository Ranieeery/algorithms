EASTER_EGG_URLS

# Teste técnico: Software Development Intern

## Configurações do ambiente

- WSL: Ubuntu 24.04.1

- Java: OpenJDK 17.0.14

  ```bash
  raniery@Raniery:~/projects/axur$ java -version
  openjdk version "17.0.14" 2025-01-21 LTS
  OpenJDK Runtime Environment Corretto-17.0.14.7.1 (build 17.0.14+7-LTS)
  OpenJDK 64-Bit Server VM Corretto-17.0.14.7.1 (build 17.0.14+7-LTS, mixed mode, sharing)
  ```

## Conhecimentos adquiridos

- `new URL` está obsoleto a partir do Java 20 

- `Stack` usa generics, então é necessário especificar o tipo de dado que será armazenado

- Consolidação do conhecimento sobre estruturas de dados, como pilhas

- Aprendizado sobre a manipulação de strings em Java

- Alguns comentários feitos durante o desenvolvimento do código (em inglês):

```java
// https://www.geeksforgeeks.org/command-line-arguments-in-java/
// new URL deprecated in java 20, the test is in java 17
// https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
// i need a LIFO (Last In First Out) structure to store the tags, so i will use a stack, thanks grokking algorithms
// Error, returned "malformed HTML". Too confusing, i'll refactor
// i forgot to change the substring when i copied it from the opening...
// A space. The problem was a space.
// Else if and else are not correct, i need to check if the tag is malformed
// A trim. The problem was a trim.
```
