package com.gmail.maxhard001.javarush.collections.level1.task3105;
/* 
 * В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри, а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.

Требования:
•	В методе main создай ZipInputStream для архивного файла (второй аргумент main). Нужно вычитать из него все содержимое.
•	В методе main создай ZipOutputStream для архивного файла (второй аргумент main).
•	В ZipOutputStream нужно записать содержимое файла, который приходит первым аргументом в main.
•	В ZipOutputStream нужно записать все остальное содержимое, которое было вычитано из ZipInputStream.
•	Потоки для работы с архивом должны быть закрыты.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path file = Paths.get(args[0]);
        String fileName = file.getFileName().toString();    // резолвим имя файла который будем добавлять
        Path zipFile = Paths.get(args[1]);
        Map<ZipEntry, byte[]> content = new HashMap<>();    // хранение мэпэнтри и его содержимого
        
        System.out.println("Файл " + file.getFileName() + " будет помещен в архив " + zipFile.toString());
        System.out.println("Все файлы с таким же именем, найденные в архиве, будут заменены на новый.\n");

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile.toFile()));
        ){
            boolean copyFound = false;  //  true усли в архиве наайдена копия доабвляемого файла
            ZipEntry inEntry;
            while ( ((inEntry = zipIn.getNextEntry()) != null ))
            //  &&                                        (zipIn.available() > 0)) 
             {
                
                if (inEntry.getName().contains(fileName)) {     //  пробуем найти зипэнтри с таким же именем что и имя добавляемый файл
                    System.out.println("В архиве найден файл :" + inEntry.getName() );
                    System.out.println("файл будет заменен на новый :" + file.toString());
                    inEntry = new ZipEntry(inEntry.getName());
                    content.put(inEntry, Files.readAllBytes(file));  
                    copyFound = true; //  в архиве найдена копия (имя) новго архивируемого файла и она заменена.
                } else {
                    content.put(inEntry, zipIn.readAllBytes());
                }
            }
        if (!copyFound) {       //  если в архиве не найдено такого же файла который мы добавляем
            inEntry = new ZipEntry("new" + File.separator + fileName);
            content.put(inEntry, Files.readAllBytes(file));
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  запись файла в архиив
        try (
                ZipOutputStream zipOut = new ZipOutputStream(
                                         new FileOutputStream(zipFile.toFile(), false));
        ){
            for (Map.Entry<ZipEntry, byte[]> item : content.entrySet()) {   //  item - элемент мапы  связка зипэнтри и содержимого файла
                ZipEntry zipOutEntry = item.getKey();
                zipOut.putNextEntry(zipOutEntry);
                zipOut.write(item.getValue());
                zipOut.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();       
        }
    }
}