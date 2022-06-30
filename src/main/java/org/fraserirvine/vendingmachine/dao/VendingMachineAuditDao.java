package org.fraserirvine.vendingmachine.dao;

public interface VendingMachineAuditDao {

    void writeAuditEntry(String entry) throws VMAuditFileNotFoundException;

}
