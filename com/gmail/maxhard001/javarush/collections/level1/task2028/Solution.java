package com.gmail.maxhard001.javarush.collections.level1.task2028;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.gmail.maxhard001.javarush.collections.level1.task2028.CustomTree.Entry;

public class Solution {
    public static void main(String[] args) {

        List<String> list = new CustomTree();

        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        printList(list);


        System.out.println("The list size is " + list.size());
        System.out.println("The expected parent is 3. The actual parent is " + ((CustomTree) list).getParent("8"));
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("20"));

        list.remove("3");
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("8"));
        printList(list);
        list.add("16");
        System.out.println("The expected parent is 9. The actual parent is " + ((CustomTree) list).getParent("16"));


        printList(list);


       
        list.remove("4");
        list.remove("5");
        list.remove("6");
        printList(list);

        System.out.println("Expected: true. Actual: " + list.add("20"));
        System.out.println("The expected parent is 1. The actual parent is " + ((CustomTree) list).getParent("20"));
    

    }

    private static void printList(List<String> list) {
        CustomTree.Entry<String> current = ((CustomTree) list).root;
        Queue<Entry<String>> fifo = new LinkedList<>();

        // Кладем все узлы с чайлдами на стек и перебераем их чайлдов
        fifo.add(current);

        while (!fifo.isEmpty()) {
            current = fifo.remove();
            if (current == null) {return;}
            
            System.out.println(current.elementName);

            if (current.leftChild != null) {
                System.out.println("L ch - " + current.leftChild.elementName);
            }
            if (current.rightChild != null) {
                System.out.println("R ch -" + current.rightChild.elementName);
            }
            if (current.parent != null) {
                System.out.println("P: " + current.parent.elementName);
            }

            if (current.leftChild != null) {
                fifo.add(current.leftChild);
            }
            if (current.rightChild != null) {
                fifo.add(current.rightChild);
            }

        }
        
        System.out.println("Size: " + list.size());
    }
}