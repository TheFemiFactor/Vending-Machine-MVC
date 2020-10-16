/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineWebDao {

    Item addInventory(int idOfInventory, Item item);
    
    public void setItemsInventory();

    List<Item> getAllInventory();

    Item removeInventory(int idOfInventory, Item item);

    public Item getItemById(int idOfInventory);

}
