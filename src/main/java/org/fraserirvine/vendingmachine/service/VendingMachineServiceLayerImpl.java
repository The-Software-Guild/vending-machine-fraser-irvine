package org.fraserirvine.vendingmachine.service;

import org.fraserirvine.vendingmachine.dao.*;
import org.fraserirvine.vendingmachine.dto.Change;
import org.fraserirvine.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;

    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void loadItems() throws VMAuditFileNotFoundException {
        dao.loadItems();
        auditDao.writeAuditEntry("Vending machine data successfully loaded");
    }

    @Override
    public void writeItems() throws VMAuditFileNotFoundException {
        dao.writeItems();
        auditDao.writeAuditEntry("Vending machine data successfully saved");
    }


    @Override
    public List<Item> listAllItems() {
        return dao.listAllItems();
    }

    @Override
    public Change vendItem(String itemId) throws VMOutOfStockException, VMInsufficientFundsException {

        return dao.vendItem(itemId);
    }

    @Override
    public BigDecimal getInserted() {
        return dao.getInserted();
    }

    @Override
    public void insertMoney(BigDecimal amount) throws VMAuditFileNotFoundException {
        dao.insertMoney(amount);
        auditDao.writeAuditEntry("User inserted " + amount.toString() + " into vending machine");
    }
}
