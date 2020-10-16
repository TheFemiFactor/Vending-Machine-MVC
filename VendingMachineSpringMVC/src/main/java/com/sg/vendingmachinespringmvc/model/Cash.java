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
public class Cash {
    
    BigDecimal dollar;
    BigDecimal quarter;
    BigDecimal dime;
    BigDecimal penny;
    BigDecimal totalCash;

    public BigDecimal getDollar() {
        return dollar;
    }

    public void setDollar(BigDecimal dollar) {
        this.dollar = dollar;
    }

    public BigDecimal getQuarter() {
        return quarter;
    }

    public void setQuarter(BigDecimal quarter) {
        this.quarter = quarter;
    }

    public BigDecimal getDime() {
        return dime;
    }

    public void setDime(BigDecimal dime) {
        this.dime = dime;
    }

    public BigDecimal getPenny() {
        return penny;
    }

    public void setPenny(BigDecimal penny) {
        this.penny = penny;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }
    
}
