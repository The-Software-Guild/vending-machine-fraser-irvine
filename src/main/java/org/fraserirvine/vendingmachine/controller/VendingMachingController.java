package org.fraserirvine.vendingmachine.controller;

import org.fraserirvine.vendingmachine.service.VendingMachineServiceLayer;
import org.fraserirvine.vendingmachine.ui.VendingMachineView;

public class VendingMachingController {

    private VendingMachineView view;

    private VendingMachineServiceLayer service;


    public VendingMachingController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

    }

}
