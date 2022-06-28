package org.fraserirvine.vendingmachine.service;

import org.fraserirvine.vendingmachine.dao.VMInsufficientFundsException;
import org.fraserirvine.vendingmachine.dao.VMOutOfStockException;
import org.fraserirvine.vendingmachine.dto.Change;
import org.fraserirvine.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    void loadItems();

    List<Item> listAllItems();

    Change vendItem(String itemId) throws VMOutOfStockException, VMInsufficientFundsException;

    BigDecimal getInserted();

    void insertMoney(BigDecimal amount);

}
