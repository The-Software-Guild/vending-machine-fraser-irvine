package org.fraserirvine.vendingmachine.dto;

import java.math.BigDecimal;

public class Item {

    private String itemId;
    private String itemName;
    private BigDecimal itemCost;
    private int stock;

    public Item(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "[" + itemName + " | " + itemCost + " ]";
    }

}
