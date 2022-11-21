/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Memo2o1o
 */
public class InvTableModel extends AbstractTableModel {
    
     private ArrayList<InvData> invoices;
     private String[] cols = {"No.", "Date", "Customer", "Total"};

    public InvTableModel(ArrayList<InvData> invoices) {
        this.invoices = invoices;
    }
     
     

    @Override
    public int getRowCount()
    {
        return invoices.size();
    }

    @Override
    public int getColumnCount() 
    {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }
    

    @Override
    public Object getValueAt(int i, int i1) 
    {
        InvData invoice = invoices.get(i);
        
        switch(i1)
        {
            case 0: return invoice.getInvNum();
            case 1: return invoice.getDate();
            case 2: return invoice.getCustomerName();
            case 3: return invoice.getInvTotal();
            default: return "";
        }
    }
     
     
}
