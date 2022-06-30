package org.fraserirvine.vendingmachine;

import org.fraserirvine.vendingmachine.controller.VendingMachineController;
import org.fraserirvine.vendingmachine.dao.VendingMachineAuditDao;
import org.fraserirvine.vendingmachine.dao.VendingMachineAuditDaoImpl;
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
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        //the path to the vending machine file is defined as a CLI argument.
        VendingMachineDao myDao = new VendingMachineDaoFileImpl(args[0]);
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService,myView);
        controller.run();
    }

}
