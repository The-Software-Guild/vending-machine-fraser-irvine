package org.fraserirvine.vendingmachine.dao;

public class VMInsufficientFundsException extends Exception {

    public VMInsufficientFundsException(String message) {
        super(message);
    }

    public VMInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

}
