package org.fraserirvine.vendingmachine.dao;

import org.fraserirvine.vendingmachine.dto.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private static final String PATH = "";

    private static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    BigDecimal currentInserted = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);

    @Override
    public List<Item> listAllItems() {
        return (List<Item>) items.values();
    }

    @Override
    public void vendItem(String itemId) {

    }

    @Override
    public BigDecimal getInserted() {
        return currentInserted;
    }

    @Override
    public void insertMoney(BigDecimal amount) {
        currentInserted.add(amount);
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


    private void loadItems() {
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

    private void writeItems() {
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
