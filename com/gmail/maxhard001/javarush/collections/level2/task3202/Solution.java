package com.gmail.maxhard001.javarush.collections.level2.task3202;
/* 
 * Реализуй логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
Требования:
    •    Публичный статический метод getAllDataFromInputStream (InputStream) должен существовать.
    •    Метод getAllDataFromInputStream (InputStream) должен возвращать StringWriter.
    •    Метод getAllDataFromInputStream (InputStream) должен вернуть StringWriter, который содержит все данные из переданного потока.
    •    Возвращаемый объект ни при каких условиях не должен быть null.
 */
import java.io.*;
import java.util.Objects;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        
        StringWriter result = new StringWriter();
        StringBuilder sb = new StringBuilder();
        
        if (Objects.nonNull(is)) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is));)
            {
                while(br.ready()) {
                    sb.append(br.readLine());
                }
            }
        }
        result.write(sb.toString());
        return  result;
    }
}