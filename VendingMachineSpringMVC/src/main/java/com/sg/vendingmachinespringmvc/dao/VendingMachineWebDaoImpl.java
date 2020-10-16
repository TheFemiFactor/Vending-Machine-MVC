/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class VendingMachineWebDaoImpl implements VendingMachineWebDao {

    private Map<Integer, Item> inventoryItems = new HashMap<>();

    @Override
    public Item addInventory(int idOfInventory, Item item) {
        inventoryItems.put(item.getIdOfInventory(), item);
        return item;

    }

    @Override
    public void setItemsInventory() {
        Item snickers = new Item(1, "Snickers", (new BigDecimal("1.85")), 9);
        inventoryItems.putIfAbsent(1, snickers);

        Item mandms = new Item(2, "M & Ms", (new BigDecimal("1.50")), 2);
        inventoryItems.putIfAbsent(2, mandms);

        Item pringles = new Item(3, "Pringles", (new BigDecimal("2.10")), 5);
        inventoryItems.putIfAbsent(3, pringles);

        Item reese = new Item(4, "Reese's", (new BigDecimal("1.85")), 4);
        inventoryItems.putIfAbsent(4, reese);

        Item pretzels = new Item(5, "Pretzels", (new BigDecimal("1.25")), 9);
        inventoryItems.putIfAbsent(5, pretzels);

        Item twinkies = new Item(6, "Twinkies", (new BigDecimal("1.95")), 3);
        inventoryItems.putIfAbsent(6, twinkies);

        Item doritos = new Item(7, "Doritos", (new BigDecimal("1.75")), 11);
        inventoryItems.putIfAbsent(7, doritos);

        Item almondJoy = new Item(8, "Almond Joy", (new BigDecimal("1.85")), 0);
        inventoryItems.putIfAbsent(8, almondJoy);

        Item trident = new Item(9, "Trident", (new BigDecimal("1.95")), 6);
        inventoryItems.putIfAbsent(9, trident);

    }

    @Override
    public List<Item> getAllInventory() {
        Collection<Item> c = inventoryItems.values();
        return new ArrayList(c);

    }

    @Override
    public Item removeInventory(int idOfInventory, Item item) {
        Item removedItem = inventoryItems.remove(idOfInventory);
        return removedItem;
    }

    @Override
    public Item getItemById(int idOfInventory) {

        return inventoryItems.get(idOfInventory);
    }

}
