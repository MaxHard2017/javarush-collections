package com.gmail.maxhard001.javarush.collections.level2.task3205;

/* 
 * 1) В отдельном файле создай публичный класс CustomInvocationHandler, который будет хэндлером при создании прокси-объекта.
2) CustomInvocationHandler должен поддерживать интерфейс InvocationHandler.
3) CustomInvocationHandler должен иметь один публичный конструктор с одним аргументом типа SomeInterfaceWithMethods.
4) Перед вызовом любого метода у оригинального объекта должна выводиться фраза [methodName in].
5) После вызова любого метода у оригинального объекта должна выводиться фраза [methodName out].
6) Реализуй логику метода getProxy, который должен создавать прокси (Proxy.newProxyInstance(...)).
См. пример вывода в методе main.
Метод main не участвует в тестировании.
Требования:
    •    Класс CustomInvocationHandler должен существовать.
    •    Класс CustomInvocationHandler должен поддерживать интерфейс InvocationHandler.
    •    Класс CustomInvocationHandler должен иметь один публичный конструктор с одним аргументом типа SomeInterfaceWithMethods.
    •    Перед вызовом любого метода у оригинального объекта должна выводиться фраза [methodName in].
    •    После вызова любого метода у оригинального объекта должна выводиться фраза [methodName out].
    •    Метод getProxy должен создавать прокси для интерфейса SomeInterfaceWithMethods.
 */

import java.lang.reflect.Proxy;

public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside 
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {

        SomeInterfaceWithMethodsImpl s = new SomeInterfaceWithMethodsImpl();
        ClassLoader classLoader = s.getClass().getClassLoader();
        Class<?>[] interfaces = s.getClass().getInterfaces();

        SomeInterfaceWithMethods proxyObj;
        proxyObj = (SomeInterfaceWithMethods) Proxy.newProxyInstance(
                                                classLoader, interfaces, new CustomInvocationHandler(s));
        
        return proxyObj;
    }
}
