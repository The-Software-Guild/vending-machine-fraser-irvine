package org.fraserirvine.vendingmachine.dao;

import org.fraserirvine.vendingmachine.dto.Change;
import org.fraserirvine.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineDao {

    List<Item> listAllItems();

    Change vendItem(String itemId) throws VMOutOfStockException, VMInsufficientFundsException;

    BigDecimal getInserted();

    void insertMoney(BigDecimal amount);



}
