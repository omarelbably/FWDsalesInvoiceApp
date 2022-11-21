/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.util.ArrayList;

/**
 *
 * @author Memo2o1o
 */
public class InvData {
    private int invNum;
    private String date;
    private String customerName;
    private double invTotal;
     private ArrayList<ItemsData> items ;
    
     public InvData() {
    }

    public InvData(int invNum, String date, String customerName) {
        this.invNum = invNum;
        this.date = date;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

    public ArrayList<ItemsData> getItems() {
        if(items == null)
        {
            items = new ArrayList<>();
        }
        return items;
    }
    
   public double getInvTotal()
   {
       double total = 0.0;
       for(ItemsData item : getItems())
       {
           total += item.getTotalItems();
       }
       return total;
   }

    @Override
    public String toString() {
        return "InvData{" + "invNum=" + invNum + ", date=" + date + ", customerName=" + customerName + '}';
    }
    
    public String getAsCommaSeparatedVal()
    {
        return invNum + "," + date + "," + customerName;
    }
    
    
    
    
}
