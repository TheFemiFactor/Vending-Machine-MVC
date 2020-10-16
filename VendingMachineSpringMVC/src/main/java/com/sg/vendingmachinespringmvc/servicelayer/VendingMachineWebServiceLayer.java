/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.servicelayer;

import com.sg.vendingmachinespringmvc.dao.ErrorMessage;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineWebServiceLayer {

    public List passThroughDao();

    public List passChange();

    public List<Item> getDaoInv();

    public BigDecimal addToChange(BigDecimal addMoney);

    public BigDecimal addToChange();

    public int getIdNumber(int idOfInventory);

    public Item getId(int idOfInventory);

    public int getTheId();

    public Change provideChangeForItemChoice(BigDecimal userCash, Item item) throws ErrorMessage;

    public Change returnChange(BigDecimal userCash);

    public List<Change> getAllChange();

}
