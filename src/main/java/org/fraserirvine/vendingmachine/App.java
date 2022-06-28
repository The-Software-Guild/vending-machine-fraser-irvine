package org.fraserirvine.vendingmachine;

import org.fraserirvine.vendingmachine.controller.VendingMachingController;
import org.fraserirvine.vendingmachine.dao.VendingMachineDao;
import org.fraserirvine.vendingmachine.dao.VendingMachineDaoFileImpl;
import org.fraserirvine.vendingmachine.service.VendingMachineServiceLayer;
import org.fraserirvine.vendingmachine.service.VendingMachineServiceLayerImpl;
import org.fraserirvine.vendingmachine.ui.UserIO;
import org.fraserirvine.vendingmachine.ui.UserIOConsoleImpl;
import org.fraserirvine.vendingmachine.ui.VendingMachineView;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);
        VendingMachingController controller = new VendingMachingController(myService,myView);
        controller.run();
    }

}
