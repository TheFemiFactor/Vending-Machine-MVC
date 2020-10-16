/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Item {

    private int idOfInventory;
    private String itemName;
    private BigDecimal price;
    private int invAmount;

    public Item(int idOfInventory, String itemName, BigDecimal price, int invAmount) {
        this.idOfInventory = idOfInventory;
        this.itemName = itemName;
        this.price = price;
        this.invAmount = invAmount;
    }
    
    

    public int getIdOfInventory() {
        return idOfInventory;
    }

    public void setIdOfInventory(int idOfInventory) {
        this.idOfInventory = idOfInventory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(int invAmount) {
        this.invAmount = invAmount;
    }
    
}