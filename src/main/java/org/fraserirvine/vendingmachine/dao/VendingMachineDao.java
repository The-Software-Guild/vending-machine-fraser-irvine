package org.fraserirvine.vendingmachine.dao;

import org.fraserirvine.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineDao {

    List<Item> listAllItems();

    void vendItem(String itemId);

    BigDecimal getInserted();

    void insertMoney(BigDecimal amount);



}
