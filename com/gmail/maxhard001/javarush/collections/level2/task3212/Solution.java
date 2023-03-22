package com.gmail.maxhard001.javarush.collections.level2.task3212;
import com.gmail.maxhard001.javarush.collections.level2.task3212.service.Service;

/* 
 * Прочитать о паттерне Service locator.
Реализуй логику метода getService(String jndiName) в ServiceLocator.
В нем будет реализована работа с контекстом и кэшем.
    1) Верни из кэша нужный сервис.
    2) Если в кэше нет нужного сервиса то:
    2.1) Создай контекст.
    2.2) Возьми у контекста нужный сервис.
    2.3) Добавь сервис в кеш и верни его.
Требования:
    •    Класс ServiceLocator должен содержать метод Service getService(String jndiName).
    •    Если нужный сервис находится в кэше, метод getService(String jndiName) должен возвращать сервис из кэша.
    •    Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен создавать контекст.
    •    Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен искать нужный сервис в контексте.
    •    Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен добавлять в кэш сервис, найденный в контексте и возвращать его.
 */


/* 
Service Locator

*/

public class Solution {
    public static void main(String[] args) {
        Service service = ServiceLocator.getService("EJBService");
        service.execute();
        System.out.println();
        service = ServiceLocator.getService("JMSService");
        service.execute();
        System.out.println();
        service = ServiceLocator.getService("EJBService");
        service.execute();
        System.out.println();
        service = ServiceLocator.getService("JMSService");
        service.execute();

    }

}
