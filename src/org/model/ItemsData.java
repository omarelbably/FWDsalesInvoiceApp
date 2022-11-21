/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

/**
 *
 * @author Memo2o1o
 */
public class ItemsData {

    private String item;
    private double price;
    private int qty;
   
    InvData invoice;

    public ItemsData(String item, double price, int qty, InvData invoice) {
      
        this.item = item;
        this.price = price;
        this.qty = qty;
        this.invoice = invoice;
    }

 
    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

   

    public ItemsData(int invNum, String item, double price, int sum) {
        this.item = item;
        this.price = price;
        this.qty = sum;
    }
    
    public ItemsData() {
    }
    
    public double getTotalItems()
    {
    
        return price * qty;
    
    }
     @Override
    public String toString() {
        return "ItemsData{" + "invNum=" + invoice.getInvNum() + ", item=" + item + ", price=" + price + ", qty=" + qty + '}';
    }

    public InvData getInvoice() {
        return invoice;
    }
    
    public String getAsCommaSeparatedVal()
    {
        return invoice.getInvNum() + "," + item + "," + price + "," + qty;
    }
    
    
}
