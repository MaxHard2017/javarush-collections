package com.gmail.maxhard001.javarush.collections.level2.task3208;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Animal extends Remote {
    
    void speak() throws RemoteException;
    
    void printName() throws RemoteException;
}

