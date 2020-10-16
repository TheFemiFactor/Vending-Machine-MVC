/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.servicelayer;

import com.sg.vendingmachinespringmvc.dao.ErrorMessage;
import com.sg.vendingmachinespringmvc.dao.VendingMachineWebDao;
import com.sg.vendingmachinespringmvc.model.Cash;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.InventoryIdToSave;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class VendingMachineWebServiceLayerImpl implements VendingMachineWebServiceLayer {

    VendingMachineWebDao dao;

    private Map<String, Change> change = new HashMap<>();

    Cash cash = new Cash();

    InventoryIdToSave theIdNumber = new InventoryIdToSave();

    public VendingMachineWebServiceLayerImpl(VendingMachineWebDao dao) {
        this.dao = dao;
        dao.setItemsInventory();
    }

    @Override
    public List passThroughDao() {
        return dao.getAllInventory();

    }

    @Override
    public BigDecimal addToChange(BigDecimal addMoney) {

        if (cash.getTotalCash() == null) {
            cash.setTotalCash(new BigDecimal("0.00"));
            return cash.getTotalCash();
        } else {
            BigDecimal currentAmount = cash.getTotalCash();
            BigDecimal addMoreToCash = currentAmount.add(addMoney);
            cash.setTotalCash(addMoreToCash);
            return cash.getTotalCash();
        }
    }

    @Override
    public BigDecimal addToChange() {
        return cash.getTotalCash();
    }

    @Override
    public Item getId(int idOfInventory) {
        return dao.getItemById(idOfInventory);
    }

    @Override
    public int getIdNumber(int id) {
        theIdNumber.setInventoryToSave(id);
        return theIdNumber.getInventoryToSave();
    }

    @Override
    public int getTheId() {
        return theIdNumber.getInventoryToSave();
    }

    @Override
    public Change provideChangeForItemChoice(BigDecimal userCash, Item item) throws ErrorMessage {

        BigDecimal priceOfItem = item.getPrice();
        BigDecimal changeToUser;
        BigDecimal originalChange;

        int quartersToUser;
        int dimesToUser;
        int nickelsToUser;
        int pennyToUser;

        if (userCash.compareTo(priceOfItem) == -1) {
            BigDecimal amountNeeded = priceOfItem.subtract(userCash);
            throw new ErrorMessage("Please deposit: $" + amountNeeded);
        } else if (item.getInvAmount() <= 0) {

            throw new ErrorMessage("SOLD OUT!!!");
        } else {

            originalChange = userCash.subtract(priceOfItem);

            changeToUser = originalChange;

            BigDecimal quarter = new BigDecimal(".25");
            BigDecimal dime = new BigDecimal(".10");
            BigDecimal nickel = new BigDecimal(".05");
            BigDecimal penny = new BigDecimal(".01");

            quartersToUser = changeToUser.divide(quarter, 0, RoundingMode.FLOOR).intValue();
            changeToUser = changeToUser.remainder(quarter);

            dimesToUser = changeToUser.divide(dime, 0, RoundingMode.FLOOR).intValue();
            changeToUser = changeToUser.remainder(dime);

            nickelsToUser = changeToUser.divide(nickel, 0, RoundingMode.FLOOR).intValue();
            changeToUser = changeToUser.remainder(nickel);

            pennyToUser = changeToUser.divide(penny, 0, RoundingMode.FLOOR).intValue();

            Change change = new Change(quartersToUser, dimesToUser, nickelsToUser, pennyToUser);

            if (item.getInvAmount() > 0) {
                int b = 1;
                item.setInvAmount(item.getInvAmount() - b);
            }

            cash.setTotalCash(new BigDecimal("0.00"));

            return change;
        }
    }

    @Override
    public List passChange() {
        List<Change> changeForUser = getAllChange();
        return changeForUser;
    }

    @Override
    public List<Item> getDaoInv() {
        return dao.getAllInventory();
    }

    @Override
    public Change returnChange(BigDecimal userCash) {

        BigDecimal changeToUser;

        int quartersToUser;
        int dimesToUser;
        int nickelsToUser;
        int pennyToUser;

        changeToUser = userCash;

        BigDecimal quarter = new BigDecimal(".25");
        BigDecimal dime = new BigDecimal(".10");
        BigDecimal nickel = new BigDecimal(".05");
        BigDecimal penny = new BigDecimal(".01");

        quartersToUser = changeToUser.divide(quarter, 0, RoundingMode.FLOOR).intValue();
        changeToUser = changeToUser.remainder(quarter);

        dimesToUser = changeToUser.divide(dime, 0, RoundingMode.FLOOR).intValue();
        changeToUser = changeToUser.remainder(dime);

        nickelsToUser = changeToUser.divide(nickel, 0, RoundingMode.FLOOR).intValue();
        changeToUser = changeToUser.remainder(nickel);
        pennyToUser = changeToUser.divide(penny, 0, RoundingMode.FLOOR).intValue();

        Change change = new Change(quartersToUser, dimesToUser, nickelsToUser, pennyToUser);

        cash.setTotalCash(new BigDecimal("0.00"));

        return change;
    }

    @Override
    public List<Change> getAllChange() {
        Collection<Change> cashBack = change.values();
        return new ArrayList(cashBack);

    }

}
