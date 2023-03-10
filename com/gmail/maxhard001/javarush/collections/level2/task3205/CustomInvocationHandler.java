package com.gmail.maxhard001.javarush.collections.level2.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    
    SomeInterfaceWithMethods someInterface;
    public CustomInvocationHandler(SomeInterfaceWithMethods o) {
        this.someInterface = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName() + " in");

        Object methodReturn = method.invoke(someInterface, args);

        System.out.println(method.getName() + " out");
        //throw new UnsupportedOperationException("Unimplemented method 'invoke'");
        return methodReturn;
    }
}
