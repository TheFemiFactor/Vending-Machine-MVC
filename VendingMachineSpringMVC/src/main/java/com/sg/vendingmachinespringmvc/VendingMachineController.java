/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import com.sg.vendingmachinespringmvc.dao.ErrorMessage;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sg.vendingmachinespringmvc.servicelayer.VendingMachineWebServiceLayer;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author apprentice
 */
@Controller
public class VendingMachineController {

    VendingMachineWebServiceLayer serviceLayer;

    @Inject
    public VendingMachineController(VendingMachineWebServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        serviceLayer.passThroughDao();
        List<Item> items = serviceLayer.getDaoInv();
        model.addAttribute("items", items);

        BigDecimal amount = serviceLayer.addToChange(BigDecimal.ZERO);
        model.addAttribute("amount", amount);

        int id = serviceLayer.getTheId();
        if (id > 0) {
            model.addAttribute("id", id);
        }

        return "index";
    }

    @RequestMapping(value = "/addDollar", method = RequestMethod.POST)
    public String addDollar(HttpServletRequest request, Model model) {

        String priceInputParam = request.getParameter("submitButton");

        switch (priceInputParam) {
            case "dollar":
                serviceLayer.addToChange(new BigDecimal("1.00"));
                break;
            case "quarter":
                serviceLayer.addToChange(new BigDecimal("0.25"));
                break;
            case "dime":
                serviceLayer.addToChange(new BigDecimal("0.10"));
                break;
            case "nickel":
                serviceLayer.addToChange(new BigDecimal(".05"));
                break;
        }

        return "redirect:index";
    }

    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public String getIdOfInventory(HttpServletRequest request, Model model) {

        String item = request.getParameter("id");
        int idInt = Integer.parseInt(item);
        serviceLayer.getIdNumber(idInt);

        model.addAttribute("item", item);

        return "redirect:index";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase(HttpServletRequest request, Model model) {

        List<Item> items = serviceLayer.getDaoInv();
        model.addAttribute("items", items);

        int id = serviceLayer.getTheId();
       Item item = serviceLayer.getId(id);
        
        if(id<1){
            return "redirect:index";
        } else {
            
        model.addAttribute("id", id);
        
        BigDecimal amount = serviceLayer.addToChange(BigDecimal.ZERO);
        model.addAttribute("amount", amount);
        
        try{
        Change change = serviceLayer.provideChangeForItemChoice(amount, item);
        int quarter = change.getQuartersToUser();
        model.addAttribute("quarter", quarter);

        int dime = change.getDimeToUser();
        model.addAttribute("dime", dime);

        int nickel = change.getNickelToUser();
        model.addAttribute("nickel", nickel);

        int penny = change.getPennyToUser();
        model.addAttribute("penny", penny);
        
        String message = "Thank You!!!";
        model.addAttribute("message", message);
        
        } catch (ErrorMessage e) {
            String message = (e.getMessage());
            model.addAttribute("message", message );
        }

        return "items";
        }
    }
    
    @RequestMapping(value = "/returnChange", method = RequestMethod.POST)
    public String returnChange(HttpServletRequest request, Model model){
        
        List<Item> items = serviceLayer.getDaoInv();
        model.addAttribute("items", items);
        
        BigDecimal amount = serviceLayer.addToChange(BigDecimal.ZERO);
        if(amount.equals(0)){
            return "redirect:index";
        }else{
        
        Change change = serviceLayer.returnChange(amount);

        int quarter = change.getQuartersToUser();
        model.addAttribute("quarter", quarter);

        int dime = change.getDimeToUser();
        model.addAttribute("dime", dime);

        int nickel = change.getNickelToUser();
        model.addAttribute("nickel", nickel);

        int penny = change.getPennyToUser();
        model.addAttribute("penny", penny);
        
        return "changeReturn";
        }
    }
}
