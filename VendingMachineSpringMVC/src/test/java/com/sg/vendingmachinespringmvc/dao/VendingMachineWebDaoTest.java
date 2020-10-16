/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class VendingMachineWebDaoTest {

    private VendingMachineWebDao dao = new VendingMachineWebDaoImpl();

    public VendingMachineWebDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Item> inventoryList = dao.getAllInventory();
        for (Item item : inventoryList) {
            dao.removeInventory(item.getIdOfInventory(), item);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addInventory method, of class VendingMachineWebDao.
     */
    @Test
    public void testAddInventory() {

        Item item = new Item(1, "Snickers", (new BigDecimal("4.55")), 10);

        dao.addInventory(item.getIdOfInventory(), item);

        Item fromDAO = dao.getItemById(1);

        assertEquals(item, fromDAO);

    }

    @Test
    public void tesGetInventory() throws Exception {
        Item item = new Item(1, "Snickers", (new BigDecimal("4.55")), 10);
        Assert.assertNotNull(item);
    }

    /**
     * Test of getAllInventory method, of class VendingMachineWebDao.
     */
    @Test
    public void testGetAllInventory() {

        assertEquals(0, dao.getAllInventory().size());

    }

    /**
     * Test of removeInventory method, of class VendingMachineWebDao.
     */
    @Test
    public void testRemoveInventory() {
        Item item = new Item(1, "Snickers", (new BigDecimal("4.55")), 10);

        Item item2 = new Item(2, "Reeses", (new BigDecimal("2.30")), 6);

        dao.addInventory(item.getIdOfInventory(), item);
        dao.addInventory(item2.getIdOfInventory(), item2);

        dao.removeInventory(item.getIdOfInventory(), item);

        assertNull(dao.getItemById(item.getIdOfInventory()));

        dao.removeInventory(item2.getIdOfInventory(), item);

        assertNull(dao.getItemById(item2.getIdOfInventory()));
    }
    


}
