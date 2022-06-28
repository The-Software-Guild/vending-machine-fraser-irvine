package org.fraserirvine.vendingmachine.service;

import org.fraserirvine.vendingmachine.dao.VendingMachineDao;
import org.fraserirvine.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }


    @Override
    public List<Item> listAllItems() {
        return dao.listAllItems();
    }

    @Override
    public void vendItem(String itemId) {
        dao.vendItem(itemId);
    }

    @Override
    public BigDecimal getInserted() {
        return null;
    }

    @Override
    public void insertMoney(BigDecimal amount) {

    }
}
