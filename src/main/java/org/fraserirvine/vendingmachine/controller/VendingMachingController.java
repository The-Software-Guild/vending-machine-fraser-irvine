package org.fraserirvine.vendingmachine.controller;

import org.fraserirvine.vendingmachine.dto.Item;
import org.fraserirvine.vendingmachine.service.VendingMachineServiceLayer;
import org.fraserirvine.vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendingMachingController {

    private VendingMachineView view;

    private VendingMachineServiceLayer service;


    public VendingMachingController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        while (true) {
            List<Item> itemList = service.listAllItems();
            int menuSelection = view.printMenuAndGetSelection(itemList, service.getInserted());

            switch (menuSelection) {
                case 1:
                    service.insertMoney(view.insertMoney());
                case 2:
                    break;
                default:
                    service.vendItem(itemList.get(menuSelection-2).getItemId());
            }

        }

    }

}
