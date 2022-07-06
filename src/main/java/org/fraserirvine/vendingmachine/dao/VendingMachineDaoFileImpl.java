package org.fraserirvine.vendingmachine.dao;

import org.fraserirvine.vendingmachine.dto.Change;
import org.fraserirvine.vendingmachine.dto.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private final String PATH;

    private static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    private BigDecimal currentInserted;

    public VendingMachineDaoFileImpl() {
        this.PATH = System.getenv("VENDING_MACHINE_PATH");
        resetInserted();
    }

    private void resetInserted() {
        this.currentInserted = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public List<Item> listAllItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Change vendItem(String itemId) throws VMOutOfStockException, VMInsufficientFundsException {
        Item currentItem = items.get(itemId);

        if (currentItem.getStock() == 0) {
            throw new VMOutOfStockException("Item out of stock");
        }

        if (currentInserted.compareTo(currentItem.getItemCost()) >= 0) {
            currentInserted = currentInserted.subtract(currentItem.getItemCost());
            currentItem.removeSingleStock();
            items.put(currentItem.getItemId(), currentItem);
            BigDecimal changeAmount = currentInserted;
            resetInserted();
            return new Change(changeAmount);
        } else {
            throw new VMInsufficientFundsException("Insufficient Funds");
        }
    }

    @Override
    public BigDecimal getInserted() {
        return currentInserted;
    }

    @Override
    public void insertMoney(BigDecimal amount) {
        currentInserted = currentInserted.add(amount);
    }


    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);

        Item itemFromFile = new Item(itemTokens[0]);
        itemFromFile.setItemName(itemTokens[1]);
        itemFromFile.setItemCost(new BigDecimal(itemTokens[2]));
        itemFromFile.setStock(Integer.parseInt(itemTokens[3]));

        return itemFromFile;
    }

    private String marshallItem(Item item) {
        return item.getItemId() +
                DELIMITER + item.getItemName() +
                DELIMITER + item.getItemCost() +
                DELIMITER + item.getStock();
    }

    @Override
    public void loadItems() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        lines.forEach((line) -> {
            Item currentItem = unmarshallItem(line);
            items.put(currentItem.getItemId(), currentItem);
        });

    }

    @Override
    public void writeItems() {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        items.values().forEach((v) -> {
            out.println(marshallItem(v));
        });
        out.close();
    }

}
