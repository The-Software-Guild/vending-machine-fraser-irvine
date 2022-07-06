package org.fraserirvine.vendingmachine;

import org.fraserirvine.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    // ==========================================================
    // In order to run this program.
    // the environment variable: "VENDING_MACHINE_PATH"
    // needs to be set to the path of the working file
    // ==========================================================

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }

}
