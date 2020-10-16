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
public class Change {

    private int quartersToUser;
    private int dimeToUser;
    private int nickelToUser;
    private int pennyToUser;
  
    
    public Change( int quartersToUser, int dimeToUser, int nickelToUser, int pennyToUser) {
        this.quartersToUser = quartersToUser;
        this.dimeToUser = dimeToUser;
        this.nickelToUser = nickelToUser;
        this.pennyToUser = pennyToUser;
      
    }

    public int getQuartersToUser() {
        return quartersToUser;
    }

    public void setQuartersToUser(int quartersToUser) {
        this.quartersToUser = quartersToUser;
    }

    public int getDimeToUser() {
        return dimeToUser;
    }

    public void setDimeToUser(int dimeToUser) {
        this.dimeToUser = dimeToUser;
    }

    public int getNickelToUser() {
        return nickelToUser;
    }

    public void setNickelToUser(int nickelToUser) {
        this.nickelToUser = nickelToUser;
    }

    public int getPennyToUser() {
        return pennyToUser;
    }

    public void setPennyToUser(int pennyToUser) {
        this.pennyToUser = pennyToUser;
    }

}

