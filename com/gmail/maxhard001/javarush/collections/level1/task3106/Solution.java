package com.gmail.maxhard001.javarush.collections.level1.task3106;

/* 
 * В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002

Требования:
•	В методе main нужно создать ZipInputStream для архива, собранного из кусочков файлов. Файлы приходят аргументами в main, начиная со второго.
•	Создай поток для записи в файл, который приходит первым аргументом в main. Запиши туда содержимое файла из архива.
•	Поток для чтения из архива должен быть закрыт.
•	Поток для записи в файл должен быть закрыт.
Добавление файла в архив
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipInputStream;

public class Solution {
    public static void main(String[] args) {    // args[0] - имя файла для сборки args[1..n] - файлы кусков архивов
        Path resultFileName = Paths.get(args[0]);

        // Path[] fileNameParts = new Path[args.length - 1];
        Set<Path> fileNameParts = new TreeSet<>();

        for (int i = 0; i < args.length - 1; i++) {     //  в массив от 0 берем все аргументы начмная с первого (0+1)
            // fileNameParts[i] = Paths.get(args[i+1]);
            fileNameParts.add(Paths.get(args[i+1]));
        }

        Path tempZipFile = null;
        try {
        tempZipFile = Files.createTempFile(null, null);
        } catch (IOException exc) {
            System.out.println("Can not create temp zip file for merging zip peaces.");
            exc.printStackTrace(); 
        }

        try ( 
                ZipInputStream zipStream = new ZipInputStream(new FileInputStream(tempZipFile.toFile()));

        ){              
            for (Path partOfZip : fileNameParts) {
                Files.write(tempZipFile, Files.readAllBytes(partOfZip), StandardOpenOption.APPEND);
            }
             while (true) {
                if (zipStream.getNextEntry() == null) break;
                Files.write(resultFileName, zipStream.readAllBytes());
            }

        } catch (IOException e) {
            System.out.println("Can not merge result zip file");
             e.printStackTrace();
        }
        
    }
}