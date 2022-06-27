package org.fraserirvine.vendingmachine.dto;

import java.math.BigDecimal;

public class Item {

    private String itemId;
    private String itemName;
    private BigDecimal itemCost;
    private int quantity;

    public Item(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public int getQuantity() {
        return quantity;
    }
}
