package org.fraserirvine.vendingmachine.dao;

public class VMOutOfStockException extends Exception {

    public VMOutOfStockException(String message) {
        super(message);
    }

    public VMOutOfStockException(String message, Throwable cause) {
        super(message,cause);
    }

}
