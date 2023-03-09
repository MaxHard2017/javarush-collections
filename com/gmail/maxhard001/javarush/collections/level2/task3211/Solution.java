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
/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true
    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] inBytes = byteArrayOutputStream.toByteArray();
        byte[] inBytesHash = md.digest(inBytes);

        return md5.equalsIgnoreCase(byteToHex2(inBytesHash));
    }

    public static String byteToHex(byte... inBytes) {

        StringBuilder sbHash = new StringBuilder(2 * inBytes.length); // 1 byte reprezents as 2 chars in HEX
        for (byte b : inBytes) {

            sbHash.append("0123456789abcdef".charAt((b & 0xF0) >> 4)); // первые 4 бита берем и сдвигаем чтобы получить инт значение первого октета
            sbHash.append("0123456789abcdef".charAt((b & 0x0F)));
        }

        return sbHash.toString();
    }
    
    public static String byteToHex2(byte... inBytes) {

        StringBuilder sbHash = new StringBuilder(inBytes.length);
        int intValue;
        for (byte b : inBytes) {
            intValue = Byte.toUnsignedInt(b); //  для того чтобы приувести byte к беззнековому
            //  ииначе 'f' будет распространятся влево как -1 при перекасте из byte в int
            sbHash.append(Integer.toHexString(intValue)); // представлен как беззнаковое целое число - переводим в текстовое представление в шеснадцатиричном виде
        }
        return sbHash.toString();
    }
}