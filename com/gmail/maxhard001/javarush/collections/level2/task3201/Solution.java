package com.gmail.maxhard001.javarush.collections.level2.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
 * В метод main приходят три параметра:
1) fileName - путь к файлу;
2) number - число, позиция в файле;
3) text - текст.

Считать текст с файла начиная с позиции number, длиной такой же как и длина переданного текста в третьем параметре.
Если считанный текст такой же как и text, то записать в конец файла строку 'true', иначе записать 'false'.
Используй RandomAccessFile и его методы seek(long pos), read(byte[] b, int off, int len), write(byte[] b).
Используй один из конструкторов класса String для конвертации считанной строчки в текст.
Требования:
    •    В методе main класса Solution необходимо использовать RandomAccessFile, который должен использовать файл, который приходит первым параметром.
    •    В методе main класса Solution программа должна устанавливать позицию в файле, которая передана во втором параметре.
    •    В методе main класса Solution программа должна считывать данные с файла при помощи метода read(byte[] b, int off, int len).
    •    Запись должна происходить в конец файла.
    •    Если считанный текст такой же как и text, то программа должна записать в конец переданного файла строку 'true'.
    •    Если считанный текст НЕ такой же как и text, то программа должна записать в конец переданного файла строку 'false'.
 */

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        
        String fileName = args[0];
        String number = args[1];
        int position = Integer.parseInt(number);
        String text = args[2];
        
        try (
              RandomAccessFile raf = new RandomAccessFile(fileName, "w");
        ) {
            if ((raf.length() - position) < text.length()) {
                raf.seek(raf.length());
                raf.write(text.getBytes()); 
            } else {
                raf.seek(position);
                raf.write(text.getBytes()); 
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}