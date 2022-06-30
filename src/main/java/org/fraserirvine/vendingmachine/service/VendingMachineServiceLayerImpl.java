package org.fraserirvine.vendingmachine.service;

import org.fraserirvine.vendingmachine.dao.VMInsufficientFundsException;
import org.fraserirvine.vendingmachine.dao.VMOutOfStockException;
import org.fraserirvine.vendingmachine.dao.VendingMachineAuditDao;
import org.fraserirvine.vendingmachine.dao.VendingMachineDao;
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
    public void loadItems() {
        dao.loadItems();
    }

    @Override
    public void writeItems() {
        dao.writeItems();
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
    public void insertMoney(BigDecimal amount) {
        dao.insertMoney(amount);
    }
}
