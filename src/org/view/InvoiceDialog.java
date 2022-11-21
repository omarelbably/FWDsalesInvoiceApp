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
public class InvoiceDialog extends JDialog {
    
    private JTextField custNameField;
    private JTextField invDateField;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton okBtn;
    private JButton cancelBTN;
    
    public InvoiceDialog(SalesInvoiceFrame frame)
    {
        custNameLbl = new JLabel("Customer Name: ");
        custNameField = new JTextField(20);
        
        invDateLbl = new JLabel("Invoice Date: ");
        invDateField = new JTextField(20);
        
        okBtn = new JButton("OK");
        cancelBTN = new JButton("Cancel");
        
        okBtn.setActionCommand("newInvOK");
        cancelBTN.setActionCommand("newInvCancle");
        
        okBtn.addActionListener(frame.getController());
        cancelBTN.addActionListener(frame.getController());
        setLayout(new GridLayout(3 , 2));
        
        add(custNameLbl);
        add(custNameField);
        add(invDateLbl);
        add(invDateField);
        add(okBtn);
        add(cancelBTN); 
        
        pack();
    }

    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getInvDateField() {
        return invDateField;
    }
    
}
