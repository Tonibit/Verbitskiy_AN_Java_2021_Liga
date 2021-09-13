package ru.philosophyit.internship.javabase.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String ...args) throws IOException {
        System.out.println(Files.readString(Path.of("src/main/resources/hello.txt")));

        String path = "src/main/java/ru/philosophyit/internship/javabase";
        displayRecursivelyFileTree(path);

        // Отобразите рекурсивно дерево директорий src/main/java/ru/philosophyit/internship/javabase
        // например 
        // src/main/java/ru/philosophyit/internship/javabase/files/Main.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/DeadLock.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/LiveLock.java
        // src/main/java/ru/philosophyit/internship/javabase/threads/Completable.java
        // и т.д.
        // Более удачные оформления вывода в консоль приветствуются
    }

    private static void displayRecursivelyFileTree(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
    }
}
