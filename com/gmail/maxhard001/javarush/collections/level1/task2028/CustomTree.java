package com.gmail.maxhard001.javarush.collections.level1.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    int treeSize = 0;

    @Override
    public int size() {
        return this.treeSize;
    }

    // Операции с индексом не поддерживаются в CustomTree
    @Override
    public boolean addAll(int index, Collection<? extends String> c) {

        throw new CustomListIndexException(
                "Operations with index are not supported");
    }

    @Override
    public String get(int index) {
        
        throw new CustomListIndexException(
                "Operations with index are not supported");
    }

    @Override
    public String set(int index, String element) {

        throw new CustomListIndexException(
                "Operations with index are not supported");
    }

    @Override
    public void add(int index, String element) {

        throw new CustomListIndexException(
                "Operations with index are not supported");
    }
    
    @Override
    public String remove(int index) {
        
        throw new CustomListIndexException(
                "Operations with index are not supported");
    }
    
    @Override
    public List<String> subList(int fromIndex, int toIndex) {

        throw new CustomListIndexException(
                "Operations with index are not supported");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {

        throw new CustomListIndexException(
                "Operations with index are not supported");

    }

    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new CustomListIndexException("Can not remove " + o + "Type mismatch.");
        }
        String nodeName = (String) o;
        Entry<String> nodeDelete = getByName(nodeName);
        if (nodeDelete == null || nodeDelete.parent == null) {
            throw new CustomListIndexException("Can not remove " + o);
        }

        treeSize = treeSize - countNodes(nodeDelete);

        if ((nodeDelete.parent.leftChild != null) &&
            nodeDelete.parent.leftChild.equals(nodeDelete)) {
            nodeDelete.parent.leftChild = null;
            nodeDelete.parent = null;
            return true;
            // nodeDelete.parent.availableToAddLeftChildren = true;

        } else if ((nodeDelete.parent.rightChild != null) &&
                    nodeDelete.parent.rightChild.equals(nodeDelete)) {
            nodeDelete.parent.rightChild = null;
            nodeDelete.parent = null;
            return true;
            // nodeDelete.parent.availableToAddRightChildren = true;
        } else {
            return false;
        }
    }
    
    /**считает кол-во нод в поддереве
     * @param startNode 
     * @return
     */
    public int countNodes(Entry<String> startNode) {
        int result = 0;
        Entry<String> current = Objects.requireNonNull(startNode);
        Queue<Entry<String>> fifo = new LinkedList<>();
        int emergencyExit = 10;

        // Кладем все узлы с чайлдами на стек и перебераем их чайлдов
        fifo.add(current);

        while (!fifo.isEmpty() && (emergencyExit < Integer.MAX_VALUE)) {
            
            current = fifo.remove();
            if (current == null) {return -1;}
            result++;

            if (Objects.nonNull(current.rightChild)) {
                fifo.add(current.rightChild);
            }
            if (Objects.nonNull(current.leftChild)) {
                fifo.add(current.leftChild);
            }
        }
        return result;
    }

    public Entry<String> getAvailable() {
        Entry<String> current = this.root;
        Queue<Entry<String>> fifo = new LinkedList<>();
        int emergencyExit = 10;

        // Кладем все узлы с чайлдами на стек и перебераем их чайлдов
        fifo.add(current);

        while (!fifo.isEmpty() && (emergencyExit < Integer.MAX_VALUE)) {
            current = fifo.remove();
            if (current == null) {
                return null;
            }

            if (current.isAvailableToAddChildren()) {
                return current;
            } else {
                if (current.leftChild != null) {
                    fifo.add(current.leftChild);
                }
                if (current.rightChild != null) {
                    fifo.add(current.rightChild);
                }
            }
            if (fifo.isEmpty()) {
                fixlinks();
                fifo.add(this.root);
            }
        }
        return null; // в случае неудачи
    }

    void fixlinks() {
        Entry<String> current = this.root;
        Queue<Entry<String>> fifo = new LinkedList<>();
        int emergencyExit = 10;

        fifo.add(root);
        while (!fifo.isEmpty() && (emergencyExit < Integer.MAX_VALUE)) { // Делаем цикл до Integer.MAX_VALUE - 10 на случай зависания
            current = fifo.remove();
            if (current == null) {
                return;
            }

            if ((current.rightChild == null)
                    && (current.availableToAddRightChildren == false)) {
                current.availableToAddRightChildren = true;
            }
            if ((current.leftChild == null)
                    && (current.availableToAddLeftChildren == false)) {
                current.availableToAddLeftChildren = true;
            }
            if (current.leftChild != null) {
                fifo.add(current.leftChild);
            }
            if (current.rightChild != null) {
                fifo.add(current.rightChild);
            }
        }
    }
    

    Entry<String> getByName(String nodeName) {
        Entry<String> current = this.root;
        Queue<Entry<String>> fifo = new LinkedList<>();
        int emergencyExit = 10;
                
        // Кладем все узлы с чайлдами на стек FIFO и перебераем их чайлдов
        fifo.add(current);

        while (!fifo.isEmpty() && (emergencyExit < Integer.MAX_VALUE)) { // Делаем цикл до Integer.MAX_VALUE - 10 на случай зависания
            current = fifo.remove();

            if (current == null) {return null;}

            if (current.elementName.equals(nodeName)) {
                return current;
            } else {
                if (current.leftChild != null) {
                    fifo.add(current.leftChild);
                }
                if (current.rightChild != null) {
                    fifo.add(current.rightChild);
                }
                emergencyExit++;
            }
        }
        return null; // в случае неудачи
    }

    @Override
    public boolean add(String nodeName) {

        Entry<String> availableNode = getAvailable();
        if (availableNode == null) {
            throw new CustomListIndexException("Can not add element");}
        
        if (availableNode.availableToAddLeftChildren) {
            availableNode.addLeft(nodeName);
            this.treeSize++;
            return true;
        } else if (availableNode.availableToAddRightChildren) {
            availableNode.addRight(nodeName);
            this.treeSize++;
            return true;

        } else
            return false;
    }

    public String getParent(String nodeName) {
        String result = "";
        if (getByName(nodeName) == null) {
            return result = "No such element." + nodeName;
        }
        if (getByName(nodeName).parent == null) {
            return result = nodeName + "Has no parent.";
        }
        result = getByName(nodeName).parent.elementName;
        return result;
    }

    /**
     * Default constructor
     */
    public CustomTree() {
        this.root = new CustomTree.Entry<String>("root");
    }


    
    static class Entry<T > implements Serializable {
        
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;
        
        /**
         * @param elementName
         */
        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            
            return (this.availableToAddLeftChildren || this.availableToAddRightChildren);
        }

        public void addLeft(String nodeName) {
            if (this.availableToAddLeftChildren) {
                this.leftChild = new Entry<>(nodeName);
                this.leftChild.parent = this;  // ставим ссылку в потомке на родителя
                this.availableToAddLeftChildren = false;
            } else
                throw new CustomListIndexException("Can not add left child to " + this.elementName + " left child already exist.");
        }

        public void addRight(String nodeName) {
            if (this.availableToAddRightChildren) {
                this.rightChild = new Entry<>(nodeName);
                this.rightChild.parent = this;  // ставим ссылку в потомке на родителя
                this.availableToAddRightChildren = false;
            } else
                throw new CustomListIndexException("Can not add right child to " + this.elementName + " right child already exist.");
        }
    }
}