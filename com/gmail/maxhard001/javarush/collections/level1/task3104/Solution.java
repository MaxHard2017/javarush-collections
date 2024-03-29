package com.gmail.maxhard001.javarush.collections.level1.task3104;
/* 
 * В классе Solution переопредели логику двух методов:
- visitFile кроме своей логики должен добавлять в archived все пути к zip и rar файлам
- visitFileFailed должен добавлять в failed все пути к недоступным файлам и возвращать SKIP_SUBTREE
    Пример вывода:
        D:/mydir/BCD.zip
Метод main не участвует в тестировании
Требования:
•	В классе Solution нужно переопределить метод visitFile.
•	Метод visitFile, кроме своей логики, должен добавлять в поле archived все пути к zip и rar файлам.
•	В классе Solution нужно переопределить метод visitFileFailed.
•	Метод visitFileFailed должен добавлять в поле failed все пути к недоступным файлам и возвращать SKIP_SUBTREE.
 */

 
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/* 
Поиск скрытых файлов
*/

public class Solution extends SimpleFileVisitor<Path> {
    public static void main(String[] args) throws IOException {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        final Solution solution = new Solution();
        Files.walkFileTree(Paths.get("D:/"), options, 20, solution);

        List<String> result = solution.getArchived();
        System.out.println("All archived files:");
        for (String path : result) {
            System.out.println("\t" + path);
        }

        List<String> failed = solution.getFailed();
        System.out.println("All failed files:");
        for (String path : failed) {
            System.out.println("\t" + path);
        }

    }

    private List<String> archived = new ArrayList<>();
    private List<String> failed = new ArrayList<>();
    
    public List<String> getArchived() {
        return archived;
    }

    public List<String> getFailed() {
        return failed;
    }

    @Override
    public FileVisitResult visitFile (Path file, BasicFileAttributes attr) {
            if (file.getFileName().toString().matches(".*\\.zip|.*\\.rar")) {
                archived.add(file.toString());
            }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        failed.add(file.toString());
        return FileVisitResult.SKIP_SUBTREE;
    }
}