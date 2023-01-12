package com.gmail.maxhard001.javarush.collections.level1.task3111;
/* 
 * Давай реализуем настраиваемый поиск файлов в директории.
Просмотри интерфейс java.nio.file.FileVisitor и его базовую реализацию java.nio.file.SimpleFileVisitor.
Во время поиска по дереву файлов с помощью метода Files.walkFileTree(Path start, FileVisitor<? super Path> visitor)
мы используем объект FileVisitor для выполнения необходимых операций над найденными объектами Path.

Наш класс для поиска будет называться SearchFileVisitor и расширять SimpleFileVisitor.

Поиск можно будет выполнять по таким критериям:
- выражение, встречающееся в названии файла (String);
- выражение, встречающееся в содержимом файла (String);
- максимальный и минимальный размер файла (int).
Можно задавать либо один, либо сразу несколько критериев для поиска.

Я в main написал код, который использует готовый SearchFileVisitor для поиска файлов, тебе осталась совсем легкая задача - выполнить его реализацию.
Подсказка 1: методы get... , set... - это геттеры и сеттеры полей. Основная логика класса по поиску выполняется в методе visitFile(Path file, BasicFileAttributes attrs).
Подсказка 2: для работы с файлами используй только классы из пакета java.nio.

Требования:
•	В классе SearchFileVisitor нужно создать поля partOfName, partOfContent, minSize, maxSize и сеттеры для них.
•	В классе SearchFileVisitor нужно создать поле foundFiles и геттер для него. Поле должно быть сразу инициализировано.
•	Если в SearchFileVisitor задан критерий поиска partOfName, метод visitFile должен добавить файл в foundFiles, если в названии содержится строка partOfName.
•	Если в SearchFileVisitor задан критерий поиска partOfContent, метод visitFile должен добавить файл в foundFiles, если в содержимом встречается строка partOfContent.
•	Если в SearchFileVisitor задан критерий поиска maxSize, метод visitFile должен добавить файл в foundFiles, если размер файла меньше maxSize.
•	Если в SearchFileVisitor задан критерий поиска minSize, метод visitFile должен добавить файл в foundFiles, если размер файла больше minSize.
•	Метод visitFile должен быть реализован так, чтобы учитывать сразу несколько критериев поиска.
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Solutoin {
    public static void main(String[] args) throws IOException {
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();

        // Поисковых параметров 4 - задаем сеттерами, но могут быть null (не установлен)
        searchFileVisitor.setPartOfName("amigo");
        searchFileVisitor.setPartOfContent("programmer");
        searchFileVisitor.setMinSize(5);
        searchFileVisitor.setMaxSize(10000);

        // Files.walkFileTree(Paths.get("D:/SecretFolder"), searchFileVisitor);

        Files.walkFileTree(Paths.get("c:\\test\\f1"), searchFileVisitor);

        List<Path> foundFiles = searchFileVisitor.getFoundFiles();
        System.out.println(foundFiles.isEmpty() ? "Nothing was found..." : "Searched files:");
        for (Path file : foundFiles) {
            System.out.println(file);
        }
    }
}