package com.gmail.maxhard001.javarush.collections.level2.task3211;

/* 
 * Реализуй логику метода compareMD5, который должен получать MD5 хеш из переданного ByteArrayOutputStream и сравнивать с эталонным MD5 переданным вторым параметром.
Метод main не участвует в тестировании.
Требования:
•	Класс Solution должен содержать метод compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5).
•	Метод compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) должен использовать MessageDigest.
•	Метод compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) должен возвращать результат сравнения вычисленного MD5 хеша для byteArrayOutputStream с переданным параметром md5.
•	Класс Solution должен содержать метод main.
 */


import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import jakarta.xml.bind.DatatypeConverter;
import javax.xml.bind.DatatypeConverter;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("te-st string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true
    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        System.out.println(byteArrayOutputStream.toString());
        MessageDigest md =  MessageDigest.getInstance("MD5");
        byte[] inBytes = byteArrayOutputStream.toByteArray();
        System.out.println("1" + inBytes.toString());
        
        md.update(inBytes);
        byte[] targetMD = md.digest();
        String myHash = DatatypeConverter.printHexBinary(targetMD).toUpperCase();
       System.out.println(myHash);

        return MessageDigest.isEqual(md5.getBytes(), md.digest(byteArrayOutputStream.toByteArray()));
    }
}
