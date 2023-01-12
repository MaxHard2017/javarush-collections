package com.gmail.maxhard001.javarush.collections.level1.task3111;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName = null;
    private String partOfContent = null;
    private Integer minSize = null;
    private Integer maxSize = null;


    private List<Path> foundFiles = new ArrayList<>();
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file);
        
        // представление текущего файлв в виде строки для поиска по содержимому
        String contentString = new String(content, StandardCharsets.UTF_8);
        
        boolean nameFound = false; // true если задан параметр и совпадает часть имени
        boolean contentFound = false;
        boolean sizeMinTrue = false;
        boolean sizeMaxTrue = false;
        
        /* 
         * Если прараметр null то он не влияет на поиск т.е. поисковое выражение
         * по этому параметру - true. Но если все поисковые параметры не заданы то 
         * считаем, что поиск ничего не выдает (чтбы не выводить все :)
         */
        // Если все параметры не заданы то прекращаем поиск
        if ( Objects.isNull(partOfName) && Objects.isNull(partOfContent) &&
                Objects.isNull(minSize) && Objects.isNull(maxSize) ) {
            
            return FileVisitResult.TERMINATE;
        }

        // Если параметр не задан или задан и совпадает то true
        if ( Objects.isNull(partOfName) ||
             file.getFileName().toString().contains(partOfName) ) {
            nameFound = true;
        }
        
        if ( (Objects.isNull(partOfContent)) ||
             (contentString.contains(partOfContent)) ) {
            contentFound = true;
        }

        if ( (Objects.isNull(minSize)) ||
             (minSize.compareTo((int) attrs.size()) <= 0) ) { // size болше или равен minSize
            sizeMinTrue = true;
        }
        if ((Objects.isNull(maxSize)) ||
             (maxSize.compareTo((int) attrs.size()) >= 0) ) {   // size меньше или равен maxSize
            sizeMaxTrue = true;
        }

        // Считаем, что поисковое значение в пересечении множеств т.е. удовлетворяет всем
        // заданным параметрам
        if (nameFound && contentFound && sizeMinTrue && sizeMaxTrue) {
            foundFiles.add(file);
        }

        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }
    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }
    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}