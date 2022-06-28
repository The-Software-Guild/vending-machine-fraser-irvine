package org.fraserirvine.vendingmachine.ui;

import org.fraserirvine.vendingmachine.dto.Change;
import org.fraserirvine.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(List<Item> items, BigDecimal inserted) {
        io.print("=== Main Menu===");
        io.print("Currently inserted: " + inserted.toString());
        io.print("(1) Insert Money");
        io.print("(2) Exit");
        io.print("=== Available Items ===");
        //loop items list and print to user
        for (int i = 0; i < items.size(); i++) {
            io.print("(" + (i+3) + ") " + items.get(i).toString());
        }
        return io.readInt("Please select from the above choices", 1, items.size() + 2);
    }

    public BigDecimal insertMoney() {
        return io.readBigDecimal("Enter the amount you would like to insert").setScale(2, RoundingMode.HALF_UP);
    }

    public void displayChange(Change change) {
        io.print(change.toString());
    }

    public void printError(Exception e) {
        io.print("Error: " + e.getMessage());
    }

}
