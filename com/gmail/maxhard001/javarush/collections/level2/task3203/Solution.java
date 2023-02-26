package com.gmail.maxhard001.javarush.collections.level2.task3203;


    /* 
     * Реализуй логику метода getStackTrace, который в виде одной строки (одного объекта типа String) должен возвращать весь стек-трейс переданного исключения.
Используй подходящий метод класса Throwable, который поможет записать стек-трейс в StringWriter.
Метод main не участвует в тестировании.
Требования:
    •    Публичный статический метод String getStackTrace (Throwable) должен существовать.
    •    В методе getStackTrace необходимо создать объект типа StringWriter.
    •    В методе getStackTrace (Throwable) необходимо использовать метод класса Throwable, который принимает объект типа PrintWriter.
    •    Метод getStackTrace (Throwable) должен возвращать весь стек-трейс переданного исключения.
     */


     import java.io.PrintWriter;
     import java.io.StringWriter;
     
     /* 
     Пишем стек-трейс
     */
     
public class Solution {
    public static void main(String[] args) {
        String text = getStackTrace(new IndexOutOfBoundsException("fff"));
        System.out.println(text);
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}     