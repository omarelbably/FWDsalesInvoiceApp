/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Memo2o1o
 */
public class ItemDialog extends JDialog {
    
    private JTextField itemNameField;
    private JTextField itemQtyField;
    private JTextField  itemPriceField;
    private JLabel itemNameLbl;
    private JLabel itemQtyLbl;
    private JLabel itemPriceLbl;
    private JButton okBtn;
    private JButton cancelBTN;
    
    public ItemDialog(SalesInvoiceFrame frame)
    {
        itemNameLbl = new JLabel("Item Name");   
        itemNameField = new JTextField(20);
     
        itemQtyLbl = new JLabel("Item Quantiy");
        itemQtyField = new JTextField(20);
       
        itemPriceLbl = new JLabel("Item Price");
        itemPriceField = new JTextField(20);
        
        okBtn = new JButton("OK");
        cancelBTN = new JButton("Cancel");
        
        okBtn.setActionCommand("newItemOK");
        cancelBTN.setActionCommand("newItemCancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBTN.addActionListener(frame.getController());
        setLayout(new GridLayout(5, 2));
        
        add(itemNameLbl);
        add(itemNameField);
        add(itemQtyLbl);
        add(itemQtyField);
        add(itemPriceLbl);
        add(itemPriceField);
        add(okBtn);
        add(cancelBTN);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemQtyField() {
        return itemQtyField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }




}