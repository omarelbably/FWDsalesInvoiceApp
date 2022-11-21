/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Memo2o1o
 */
public class ItemsTableModel extends AbstractTableModel {

        private ArrayList<ItemsData> items;
        private String[] cols = {"No.", "Item Name", "Item Price", "Quantity", "Total"};

    public ItemsTableModel(ArrayList<ItemsData> items) {
        this.items = items;
    }
        
    @Override
    public int getRowCount() 
    {
      return items.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int i) {
        return cols[i];
        
    }
    
    

    @Override
    public Object getValueAt(int i, int i1) 
    {
        ItemsData invoice = items.get(i);
        
        switch(i1)
        {
            case 0: return invoice.getInvoice().getInvNum();
            case 1: return invoice.getItem();
            case 2: return invoice.getPrice();
            case 3: return invoice.getQty();
            case 4: return invoice.getTotalItems();
            default: return "";
    }
        
}

    public ArrayList<ItemsData> getItems() {
        return items;
    }
}
