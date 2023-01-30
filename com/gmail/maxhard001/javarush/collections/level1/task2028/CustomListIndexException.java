package com.gmail.maxhard001.javarush.collections.level1.task2028;

class CustomListIndexException extends UnsupportedOperationException{

    /**
     * 
     */
    public CustomListIndexException() {
    }

    /**
     * @param message
     */
    public CustomListIndexException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CustomListIndexException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public CustomListIndexException(String message, Throwable cause) {
        super(message, cause);
    }

}