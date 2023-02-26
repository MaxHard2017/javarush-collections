package com.gmail.maxhard001.javarush.collections.level2.task3204;

import java.io.ByteArrayOutputStream;

/* 
 * Реализуй логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов.
2) только цифры и латинские буквы разного регистра.
3) обязательно должны присутствовать цифры, и буквы разного регистра.
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7smNu
Требования:
    •    Класс Solution должен содержать метод getPassword().
    •    Длина пароля должна составлять 8 символов.
    •    Пароь должен содержать хотя бы одну цифру.
    •    Пароль должен содержать хотя бы одну латинскую букву нижнего регистра.
    •    Пароль должен содержать хотя бы одну латинскую букву верхнего регистра.
    •    Пароль не должен содержать других символов, кроме цифр и латинских букв разного регистра.
    •    Сгенерированные пароли должны быть уникальными.
 */
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        boolean bigLetter = false;
        boolean smallLetter = false;
        boolean number = false;
        boolean eightNumbers = false;
        int i;
        int countChars = 0;
        
        while (!bigLetter || !number || !smallLetter || !eightNumbers ) {
            
            i = ((int) (Math.random() * 74) + 48) ;
            System.out.println(i);
            
            if ( i > 47 && i < 58) {
                number = true;
                bos.write(i);
                countChars++;
            } else if (( i > 64 ) && (i < 91)) {
                bigLetter = true;
                bos.write(i);
                countChars++;
            } else if (( i > 96 ) && (i < 123)) {
                smallLetter = true;
                bos.write(i);
                countChars++;
            }
            if (countChars > 7) {
                eightNumbers = true;
            }

            if  (eightNumbers && (!bigLetter || !smallLetter || !number)) {
                bos.reset();
                countChars = 0;
                eightNumbers = false;
                bigLetter = false;
                smallLetter = false;
                number = false;
            }
        }

        System.out.println("c - " + countChars);
        return bos;
    }
}