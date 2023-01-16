package com.gmail.maxhard001.javarush.collections.level1.task3113;

/* 
Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.

Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией - выведи "[полный путь] - не папка" и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок - [количество папок в директории и поддиректориях]
Всего файлов - [количество файлов в директории и поддиректориях]
Общий размер - [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.

Квадратные скобки [ ] выводить на экран не нужно.

Требования:
•	Метод main должен считывать путь к папке с консоли.
•	Если введенный путь не является директорией - нужно вывести "[полный путь] - не папка" и завершить работу.
•	Используй только классы и методы из пакета java.nio.
•	На консоль должна быть выведена следующая информация: "Всего папок - [количество папок в директории и поддиректориях]".
•	На консоль должна быть выведена следующая информация: "Всего файлов - [количество файлов в директории и поддиректориях]".
•	На консоль должна быть выведена следующая информация: "Общий размер - [общее количество байт, которое хранится в директории и поддиректориях]".

*/

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitOption;
import static java.nio.file.FileVisitOption.*;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.io.BufferedReader;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class Solution {
    private static int dirCount = 0;
    private static int fileCount = 0;
    private static Long sizeBytes = 0L;
    private static DirContentVisiter visiter = new DirContentVisiter();


    static class DirContentVisiter extends SimpleFileVisitor<Path> {

        @Override 
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
            // if (attr.isRegularFile()) {
                System.out.println(file);
                fileCount++;
                sizeBytes = sizeBytes + attr.size();
            // }           
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            dirCount++;
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file,IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }   
    
    public static void main(String[] args) throws IOException {
        String dirName = null;
        try (   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ) {
            dirName = br.readLine();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        Path directory = Paths.get(dirName);
            if (!directory.toFile().isDirectory()) {
                System.out.println(dirName + " - не папка");
            return;
        }
        
        EnumSet<FileVisitOption> options = EnumSet.of(FOLLOW_LINKS);
        Files.walkFileTree(directory, options, Integer.MAX_VALUE, visiter);

        // System.out.println("Параметры папки " + directory.toString() + ":");
        System.out.println("Всего папок - " + dirCount);
        System.out.println("Всего файлов - " + fileCount);
        System.out.println("Общий размер - " + sizeBytes + " байт");
    }
}