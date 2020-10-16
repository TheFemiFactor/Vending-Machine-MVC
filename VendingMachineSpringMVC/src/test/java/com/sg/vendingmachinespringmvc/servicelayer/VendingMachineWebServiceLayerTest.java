/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.servicelayer;

import com.sg.vendingmachinespringmvc.dao.ErrorMessage;
import com.sg.vendingmachinespringmvc.dao.VendingMachineWebDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineWebDaoImpl;
import com.sg.vendingmachinespringmvc.model.Cash;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.InventoryIdToSave;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import static java.util.Collections.list;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class VendingMachineWebServiceLayerTest {

    VendingMachineWebServiceLayer service;
    private VendingMachineWebDao dao = new VendingMachineWebDaoImpl();

    public VendingMachineWebServiceLayerTest() {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        service = ctx.getBean("vendingMachineServiceLayer", VendingMachineWebServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of passThroughDao method, of class VendingMachineWebServiceLayer.
     */
    @Test
    public void testPassThroughDao() {
        
        List<Item> items =service.passThroughDao();
        
        Assert.assertFalse(items.isEmpty());
         
        Assert.assertTrue(items.size()==9);
       
    }

    /**
     * Test of provideChangeForItemChoice method, of class
     * VendingMachineWebServiceLayer.
     */
    @Test
    public void testExcpetionNeedMoreMoney() throws Exception {
        Item item = new Item(1, "Snicker", (new BigDecimal("4.30")), 5);

        BigDecimal userCashEntered = new BigDecimal("1.00");

        boolean correctExceptionThrown = false;
        try {
            service.provideChangeForItemChoice(userCashEntered, item);
        } catch (ErrorMessage e) {
            correctExceptionThrown = true;
        }

        Assert.assertTrue(correctExceptionThrown);
    }

    @Test
    public void testExceptionSoldOut() throws Exception {
        Item item = new Item(1, "Snicker", (new BigDecimal("4.30")), 0);

        BigDecimal userCashEntered = new BigDecimal("1.00");

        boolean correctExceptionThrown = false;
        try {
            service.provideChangeForItemChoice(userCashEntered, item);
        } catch (ErrorMessage e) {
            correctExceptionThrown = true;
        }

        Assert.assertTrue(correctExceptionThrown);
    }

    @Test
    public void testExceptionNotThrown() throws Exception {
        Item item = new Item(1, "Snicker", (new BigDecimal("1.30")), 3);

        BigDecimal userCashEntered = new BigDecimal("1.50");

        boolean exceptionThrown = true;
        try {
            service.provideChangeForItemChoice(userCashEntered, item);
        } catch (ErrorMessage e) {
            exceptionThrown = false;
        }
        Assert.assertTrue("Method ran with no exception", exceptionThrown);
    }

    @Test
    public void testDimeChangeAmount() throws Exception {
        Item item = new Item(1, "Snicker", (new BigDecimal("1.30")), 3);

        BigDecimal userCashEntered = new BigDecimal("1.50");

        Change change = service.provideChangeForItemChoice(userCashEntered, item);

        Assert.assertEquals(change.getQuartersToUser(), 0);
        Assert.assertEquals(change.getDimeToUser(), 2);
        Assert.assertEquals(change.getNickelToUser(), 0);
    }

    @Test
    public void testQuarterChangeAmount() throws Exception {
        Item item = new Item(1, "Snicker", (new BigDecimal("1.00")), 3);

        BigDecimal userCashEntered = new BigDecimal("3.50");

        Change change = service.provideChangeForItemChoice(userCashEntered, item);

        Assert.assertEquals(change.getQuartersToUser(), 10);
        Assert.assertEquals(change.getDimeToUser(), 0);
        Assert.assertEquals(change.getNickelToUser(), 0);
    }

    @Test
    public void testNickelAndDimeChangeAmount() throws Exception {
        Item item = new Item(1, "Snicker", (new BigDecimal("1.00")), 3);

        BigDecimal userCashEntered = new BigDecimal("3.65");

        Change change = service.provideChangeForItemChoice(userCashEntered, item);

        Assert.assertEquals(change.getQuartersToUser(), 10);
        Assert.assertEquals(change.getDimeToUser(), 1);
        Assert.assertEquals(change.getNickelToUser(), 1);
    }

    @Test
    public void testRemoveInvAmount() throws Exception {
        Item item = new Item(1, "Snicker", (new BigDecimal("1.00")), 3);

        BigDecimal userCashEntered = new BigDecimal("3.65");

        service.provideChangeForItemChoice(userCashEntered, item);

        Assert.assertEquals(item.getInvAmount(), 2);

    }

    @Test
    public void testReturnChange() throws Exception {
        BigDecimal userCashEntered = new BigDecimal("1.65");

        Change change = service.returnChange(userCashEntered);

        Assert.assertEquals(change.getQuartersToUser(), 6);
        Assert.assertEquals(change.getDimeToUser(), 1);
        Assert.assertEquals(change.getNickelToUser(), 1);

    }
    
         /**
     * Test of getAllChange method, of class VendingMachineWebDao.
     */
   @Test
    public void testGetAllChange() {
    Change change = new Change(3, 5, 0, 6);      
      Assert.assertNotNull(change);
        
    }

    
    
}
