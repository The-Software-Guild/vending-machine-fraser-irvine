package org.fraserirvine.vendingmachine.dao;

public class VMAuditFileNotFoundException extends Exception {

    public VMAuditFileNotFoundException(String message) {
        super(message);
    }

    public VMAuditFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
