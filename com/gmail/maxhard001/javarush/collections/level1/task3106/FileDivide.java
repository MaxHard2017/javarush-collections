package com.gmail.maxhard001.javarush.collections.level1.task3106;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileDivide {
    public static void main(String[] args) {    // args[0] - разбиваемый файл, args[1] - размер разбиения
        int buffSize = Integer.parseInt(args[1]);  // размер в байтах разбивки начального файла
        Path path = Path.of(args[0]);   // пуьт к фалу который разбиваем
        if (!Files.isRegularFile(path)) {
            System.out.println("Wrong filename: " + path.toString());
        }
        String name = path.getFileName().toString();
        
        // подготовка к архивации
        Path zipFilePath = path.resolveSibling(name + ".zip");
        File zipFile = new File(zipFilePath.toString());
        ZipEntry zipInFile = new ZipEntry(name);
        
        try (
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
            FileInputStream fis = new FileInputStream(zipFile);
        ){
            // архивируем файл
            zos.putNextEntry(zipInFile);
            Files.copy(path, zos);
            zos.flush();
            zos.close();

            // разбиваем архив не части
            int fileCount = 0;  // на сколько файлов разобьем начаальный
            int available = 0;
            while ((available = fis.available()) > 0) {
                // имя части файла
                Path filePartName = zipFilePath.resolveSibling(zipFilePath.getFileName().toString() + "_" + fileCount);
                if (available < buffSize) buffSize = available; //  последний файл пишем размером остатка бвйтов
                byte[] buff = new byte[buffSize];
                fis.read(buff);
                Files.write(filePartName, buff);
                fileCount++;
            }
        }catch (IOException exc) {
            exc.printStackTrace();
        }   
    }
}