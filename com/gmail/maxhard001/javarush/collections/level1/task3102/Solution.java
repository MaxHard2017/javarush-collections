package com.gmail.maxhard001.javarush.collections.level1.task3102;
/* 
 * Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используй очередь, рекурсию не используй.
Верни список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.

Требования:
•	Метод getFileTree должен принимать аргументом String root, по которому нужно найти все вложенные файлы.
•	Метод getFileTree должен возвращать список строк.
•	Нужно реализовать метод getFileTree: найти все файлы по указанному пути и добавить их в список.
•	Метод getFileTree должен быть вызван только 1 раз (рекурсию не использовать).
 */
import static java.nio.file.FileVisitResult.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {

    static class FileListVizit extends SimpleFileVisitor<Path> {
        private List<String> fileList;
        
        public FileListVizit(List<String> fileList) {
            this.fileList = Objects.requireNonNull(fileList);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            if (attr.isSymbolicLink()) {
                fileList.add(file.toString());
                // System.out.format("Symbolic link: %s ", file);
            } else if (attr.isRegularFile()) {
                fileList.add(file.toString());
                // System.out.format("Regular file: %s ", file);
            } else {
                // System.out.format("Other: %s ", file);
            }
            // System.out.println("(" + attr.size() + "bytes)");
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed( Path path, IOException exc) {
            System.out.println(exc.getMessage());
            return CONTINUE;
        }   
  
    }

    public static List<String> getFileTree(String root) throws IOException {
        Path path = Paths.get(root);
        List<String> resultList = new ArrayList<>();
        FileListVizit fv = new FileListVizit(resultList);
        Files.walkFileTree(path, fv);
        return resultList;
    }
    public static void main(String[] args) {
        try {
            List<String> fileList = getFileTree("c:\\test");
            for (String string : fileList) {
                System.out.println(string);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}