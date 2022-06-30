package org.fraserirvine.vendingmachine.controller;

import org.fraserirvine.vendingmachine.dao.VMAuditFileNotFoundException;
import org.fraserirvine.vendingmachine.dao.VMInsufficientFundsException;
import org.fraserirvine.vendingmachine.dao.VMOutOfStockException;
import org.fraserirvine.vendingmachine.dto.Item;
import org.fraserirvine.vendingmachine.service.VendingMachineServiceLayer;
import org.fraserirvine.vendingmachine.ui.VendingMachineView;

import java.util.List;

public class VendingMachineController {

    private VendingMachineView view;

    private VendingMachineServiceLayer service;


    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean running = true;
        try {
            service.loadItems();
            while (running) {
                List<Item> itemList = service.listAllItems();
                int menuSelection = view.printMenuAndGetSelection(itemList, service.getInserted());
                switch (menuSelection) {
                    case 1:
                        service.insertMoney(view.insertMoney());
                        break;
                    case 2:
                        service.writeItems();
                        running = false;
                        break;
                    default:
                        try {
                            view.displayChange(service.vendItem(itemList.get(menuSelection-3).getItemId()));
                        } catch (VMOutOfStockException | VMInsufficientFundsException e) {
                            view.printError(e);
                        }
                }
            }
        } catch (VMAuditFileNotFoundException e) {
            view.printError(e);
        }
    }
}
