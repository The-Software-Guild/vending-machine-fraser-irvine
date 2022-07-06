package org.fraserirvine.vendingmachine.dao;

import org.fraserirvine.vendingmachine.dto.Change;
import org.fraserirvine.vendingmachine.dto.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDaoFileImplTest {

    // ==========================================================================
    // In order for these tests to work.
    // the environment variable "VENDING_MACHINE_PATH" needs to be set to the
    // vending machine path file
    // ==========================================================================

    VendingMachineDaoFileImpl testDao;

    @BeforeEach
    public void setUp() {
        testDao = new VendingMachineDaoFileImpl();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void loadItems() {
        testDao.loadItems();

        List<Item> retrievedItems = testDao.listAllItems();

        assertEquals(
                4,
                retrievedItems.size(),
                "the number of items retrieved should be 4"
        );

        boolean allStockIsTen = true;
        for (Item item : retrievedItems) {
            if (item.getStock() != 10) {
                allStockIsTen = false;
                break;
            }
        }

        assertTrue(
                allStockIsTen,
                "The stock of each item should be 10"
        );
    }

    @Test
    void insertMoney() {

        BigDecimal testInsertOne = new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP);

        testDao.insertMoney(testInsertOne);

        BigDecimal retrievedInsert = testDao.getInserted();

        assertEquals(
                new BigDecimal("1.00").setScale(2,RoundingMode.HALF_UP),
                testDao.getInserted(),
                "the inserted money should be 1.00"
        );

        //test increment inserted

        BigDecimal testInsertTwo = new BigDecimal("0.99").setScale(2, RoundingMode.HALF_UP);

        testDao.insertMoney(testInsertTwo);

        assertEquals(
                new BigDecimal("1.99").setScale(2, RoundingMode.HALF_UP),
                testDao.getInserted(),
                "The inserted money should be 1.99"
        );
    }

    @Test
    void vendItem() {
        testDao.loadItems();

        boolean insufficientFundsExceptionThrown = false;

        try {
            testDao.vendItem("1");
        } catch (VMOutOfStockException | VMInsufficientFundsException e) {
            insufficientFundsExceptionThrown = true;
        }

        assertTrue(
                insufficientFundsExceptionThrown,
                "Exception for insufficient funds should've been thrown"
        );

        assertEquals(
                10,
                testDao.listAllItems().get(0).getStock(),
                "Stock of this item shouldn't have changed from 10"
        );

        //insert money and attempt successful vend

        BigDecimal onePound = new BigDecimal("1.00");

        testDao.insertMoney(onePound);

        try {
            testDao.vendItem("1");
        } catch (VMOutOfStockException | VMInsufficientFundsException e) {
            throw new RuntimeException(e);
        }

        assertEquals(
                9,
                testDao.listAllItems().get(0).getStock(),
                "Stock of this item should've gone down by 1"
        );

        //repeatedly vend and test for OutOfStockException

        boolean outOfStockExceptionThrown = false;

        for (int i = 0; i <= 9; i++) {
            testDao.insertMoney(onePound);
            try {
                testDao.vendItem("1");
            } catch (VMOutOfStockException | VMInsufficientFundsException e) {
                outOfStockExceptionThrown = true;
            }
        }

        assertTrue(
                outOfStockExceptionThrown,
                "Out of stock exception should've been thrown"
        );

    }

    @Test
    void changeObject() {
        testDao.loadItems();

        //get change from a vend operation

        testDao.insertMoney(new BigDecimal("1.00"));

        Change testVendOne;

        try {
            testVendOne = testDao.vendItem("1");
        } catch (VMOutOfStockException | VMInsufficientFundsException e) {
            throw new RuntimeException(e);
        }

        //this transaction should return with no change

        assertTrue(
                testVendOne.hasNoChange(),
                "This transaction shouldn't have any change"
        );

        //test change by paying two pounds instead of one

        testDao.insertMoney(new BigDecimal("2.00"));

        Change testVendTwo;
        try {
            testVendTwo = testDao.vendItem("1");
        } catch (VMOutOfStockException | VMInsufficientFundsException e) {
            throw new RuntimeException(e);
        }

        assertEquals(
                1,
                testVendTwo.getOnePound(),
                "There should've been one pound of change"
        );
    }

}