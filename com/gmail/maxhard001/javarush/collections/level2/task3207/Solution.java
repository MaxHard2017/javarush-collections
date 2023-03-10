package com.gmail.maxhard001.javarush.collections.level2.task3207;

/* 
 * Реализуй логику метода run в CLIENT_THREAD. В нем будет имитироваться клиентская часть, которая коннектится к серверу.
1) Из registry получи сервис с именем UNIC_BINDING_NAME.
2) Вызови метод у полученного сервиса, передай любой не пустой аргумент.
3) Выведи в консоль результат вызова метода.
4) Обработай специфические исключения.
Метод main не участвует в тестировании.
Требования:
    •    В методе run() необходимо из registry получить сервис с именем UNIC_BINDING_NAME.
    •    В методе run() необходимо вызвать метод doubleString (String) у полученного сервиса.
    •    В методе run() необходимо вывести в консоль результат вызова метода doubleString (String).
    •    В методе run() должен быть перехват исключения RemoteException.
    •    В методе run() должен быть перехват исключения NotBoundException.
 */

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
К серверу по RMI
*/

public class Solution {
    public static final String UNIC_BINDING_NAME = "double.string";
    public static Registry registry;

    // Pretend we're starting an RMI client as the CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            
            try {
                Registry ServerRegestry = LocateRegistry.getRegistry(2099);
                var printTwice = (DoubleString) ServerRegestry.lookup(UNIC_BINDING_NAME);
                System.out.println(printTwice.doubleString("Amigo son of a bitch! "));
                        
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
            
            //напишите тут ваш код
        }
    });

    public static void main(String[] args) {
        // Pretend we're starting an RMI server as the main thread`
        Remote stub = null;
        try {
            registry = LocateRegistry.createRegistry(2099);
            final DoubleStringImpl service = new DoubleStringImpl();

            stub = UnicastRemoteObject.exportObject(service, 0);
            registry.bind(UNIC_BINDING_NAME, stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        // Start the client
        CLIENT_THREAD.start();
    }
}