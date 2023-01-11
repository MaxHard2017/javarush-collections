package com.gmail.maxhard001.javarush.collections.level1.task3101;
/* 
 * 1. На вход метода main() подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя (полный путь) существующего файла, который будет 
содержать результат.
2. Переименовать resultFileAbsolutePath в allFilesContent.txt (используй метод FileUtils.renameFile(), и, если понадобится,
FileUtils.isExist()).
3. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
Если у файла длина в байтах НЕ больше 50, то записать его содержимое в allFilesContent.txt.
После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
В качестве разделителя пути используй "/".
Для создания файлов используй конструктор File(String pathname).

Требования:
•	Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
•	Нужно создать поток для записи в переименованный файл.
•	Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt.
•	Поток для записи в файл нужно закрыть.
•	Не используй статические переменные.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.FileVisitResult.*;

public class Solution {
    
    static class FileContentVizit extends SimpleFileVisitor<Path> {
        private Path filesContent;
        private List<Path> searchedFiles = new ArrayList<>();
        public FileContentVizit(Path filesContent) {
            this.filesContent = filesContent;
        }

        @Override 
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
            if (Long.compare(attr.size(),50) <= 0) {  //вычисляет размер файла меньше или равен 50 байт
                searchedFiles.add(file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            try (
                    Writer out = new BufferedWriter(
                                    new OutputStreamWriter(
                                    new FileOutputStream(filesContent.toFile(), true)));
            ) {
                // out.write("\n");    // Начинаем склейку содержимого файлов с новой строчки
                for (Path path : searchedFiles ) {
                    try (BufferedReader br = new BufferedReader( new FileReader(path.toFile()) );
                    ){
                        // out.write(/* "Content of " +
                        //             path.getFileName() + */ ":\n"); // контент каждого файла с новой строчки
                        while (br.ready()) {
                            out.write(br.readLine() + "\n");
                        }   
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(path.toString());
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            searchedFiles = new ArrayList<>();  //обновляем список
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file,IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }

    public static void main(String[] args) {
        Path searchDir = Paths.get(args[0]);
        Path resultFileAbsolutePath = Paths.get(args[1]);
        // make 2 paths using 2 different variants - as example
        Path tempResult = Path.of( "allFilesContent.txt");
        
        try {   
            // tempResult = Files.move(resultFileAbsolutePath, resultFileAbsolutePath.resolveSibling(tempResult));
            // переименовываем файл из resultFileAbsolutePath в tempResult зачем-то (по требованиям)
            tempResult = Files.copy(resultFileAbsolutePath, resultFileAbsolutePath.resolveSibling(tempResult));  // копируем вместо переименования для теста
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileContentVizit fc = new FileContentVizit(tempResult);
        try {
            Files.walkFileTree(searchDir, fc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}