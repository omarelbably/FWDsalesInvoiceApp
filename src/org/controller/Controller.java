/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.model.InvData;
import org.model.InvTableModel;
import org.model.ItemsData;
import org.model.ItemsTableModel;
import org.view.InvoiceDialog;
import org.view.ItemDialog;
import org.view.SalesInvoiceFrame;


/**
 *
 * @author Memo2o1o
 */
public class Controller implements ActionListener, ListSelectionListener{

        private SalesInvoiceFrame frame;
        private InvoiceDialog invDialog;
        private ItemDialog itemDialog;
        
        public Controller(SalesInvoiceFrame frame)
        {
            this.frame = frame;
        }
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String actionCommand = ae.getActionCommand();
        
        System.out.println("Action " + actionCommand);
        
        switch (actionCommand)
        {
            case "Load":
                loadFile();
                break;
            case "Save":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInv();
                break;
            case "Delete Invoice":
                deleteInv();
                break;
            case "Create New Item":
                createNewIems();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "newInvOK":
                newInvOK();
                break;
            case "newInvCancle":
                newInvCancel();
                break;
                
            case "newItemOK":
                newItemOK();
                break;
            case "newItemCancel":
                newItemCancel();
                break;
        }
       
    }
    
     @Override
    public void valueChanged(ListSelectionEvent lse) {
        int selectedIndex = frame.getInvTable().getSelectedRow();
        if(selectedIndex != -1)
        {
        InvData currentInvoice = frame.getInvoices().get(selectedIndex);
        frame.getInvNum().setText(""+currentInvoice.getInvNum());
        frame.getInvDate().setText(currentInvoice.getDate());
        frame.getCustomerName().setText(currentInvoice.getCustomerName());
        frame.getTotalPrice().setText(""+currentInvoice.getInvTotal());
        
         ItemsTableModel itemsTableModel = new ItemsTableModel(currentInvoice.getItems());
         frame.getItemsTable().setModel(itemsTableModel);
         itemsTableModel.fireTableDataChanged();
         System.out.println("u have selected invoice");  
           }
    }

    private void loadFile() 
    {
        JFileChooser fc = new JFileChooser();
        try{
       int result = fc.showOpenDialog(frame);
       if(result == JFileChooser.APPROVE_OPTION)
       {
           File invFile =  fc.getSelectedFile(); 
           Path invPath = Paths.get(invFile.getAbsolutePath());
           List<String> headerLines = Files.readAllLines(invPath);
           ArrayList<InvData> invoicesArray = new ArrayList<InvData>();
           
           for (String headerline : headerLines)
           {
               String[] headerComponents = headerline.split(",");
               int invoiceNum = Integer.parseInt(headerComponents[0]);
               String invoiceDate = headerComponents[1];
               String customerName = headerComponents[2];
               
               InvData invoice = new InvData(invoiceNum,invoiceDate, customerName);
               invoicesArray.add(invoice);
           }
           result = fc.showOpenDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File itemsFile =  fc.getSelectedFile(); 
            Path itemsPath = Paths.get(itemsFile.getAbsolutePath());
            List<String> itemLines = Files.readAllLines(itemsPath);
            
            for(String itemline : itemLines)
            {
                String[] itemsComponents = itemline.split(",");
                int invoiceNum = Integer.parseInt(itemsComponents[0]);
                String itemName = itemsComponents[1];
                double itemCost = Double.parseDouble(itemsComponents[2]);
                int qty = Integer.parseInt(itemsComponents[3]);
                InvData inv = null;
                for(InvData invoice : invoicesArray)
                {
                   if(invoice.getInvNum() == invoiceNum)
                   {
                     inv = invoice;
                     break;
                   }
                }
                
                
                ItemsData item = new ItemsData(itemName, itemCost , qty ,  inv);
                inv.getItems().add(item);
                
            }
            System.out.println("Check");
            
        
         }
             frame.setInvoices(invoicesArray);
             InvTableModel invTableModel = new InvTableModel(invoicesArray);
             frame.setInvTableModel(invTableModel);
             frame.getInvTable().setModel(invTableModel);
             frame.getInvTableModel().fireTableDataChanged();
                   System.out.println("Check");
       }    
        }catch(IOException ex)
        {
        ex.printStackTrace();
        }
    }

    private void saveFile() 
    {
        ArrayList<InvData> invoices = frame.getInvoices();
        String headers = "";
        String items = "";
        for (InvData invoice : invoices)
        {
            String invCSV = invoice.getAsCommaSeparatedVal();
            headers += invCSV;
            headers += "\n";
            
            for (ItemsData item : invoice.getItems())
            {
                String itemsCSV = item.getAsCommaSeparatedVal();
                items += itemsCSV;
                items += "\n";
                          
            }
        }
        
        
        try{
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File headerFile = fc.getSelectedFile();
            FileWriter hfw = new FileWriter(headerFile);
            hfw.write(headers);
            hfw.flush();
            hfw.close();
            
            result = fc.showSaveDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                   File itemsFile =  fc.getSelectedFile();
                   FileWriter lfw = new FileWriter(itemsFile);
                   lfw.write(items);
                   lfw.flush();
                   lfw.close();
            }
        }
        } catch(Exception e )
    {
                
    }
}

    private void createNewInv() 
    {
           invDialog = new InvoiceDialog(frame);
           invDialog.setVisible(true);
          
    }

    private void deleteInv() 
    {
      int selectedRow =  frame.getInvTable().getSelectedRow();
      if(selectedRow != -1)
      {
          frame.getInvoices().remove(selectedRow);
          frame.getInvTableModel().fireTableDataChanged();
      }
    }

    private void createNewIems() 
    {
        itemDialog = new ItemDialog(frame);
        itemDialog.setVisible(true);
    }

    private void deleteItem() 
    {
        int selectedInv = frame.getInvTable().getSelectedRow();
       int selectedRow =  frame.getItemsTable().getSelectedRow();
      if(selectedInv != -1 &&  selectedRow != -1)
      {
          InvData invoice =  frame.getInvoices().get(selectedInv);
          invoice.getItems().remove(selectedRow);
          ItemsTableModel itemsTableModel = new ItemsTableModel(invoice.getItems());
          frame.getItemsTable().setModel(itemsTableModel);
          itemsTableModel.fireTableDataChanged();
          frame.getInvTableModel().fireTableDataChanged();
      }
    }

    private void newInvOK() 
    {
        String date = invDialog.getInvDateField().getText();
        String customer = invDialog.getCustNameField().getText();
        int num = frame.getNxtInvNom();
        
        InvData invoice = new InvData(num, date , customer);
        frame.getInvoices().add(invoice);
        frame.getInvTableModel().fireTableDataChanged();
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog = null;   
    }

    private void newInvCancel() 
    {
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog = null;
         }

    private void newItemOK() 
    {
        String itemN = itemDialog.getItemNameField().getText();
        String qtyStr = itemDialog.getItemQtyField().getText();
        String priceStr = itemDialog.getItemPriceField().getText();
        int qty = Integer.parseInt(qtyStr);
        double price = Integer.parseInt(priceStr);
        int selectedInv = frame.getInvTable().getSelectedRow();
        if(selectedInv != -1){
        InvData invoice = frame.getInvoices().get(selectedInv);
        ItemsData item = new ItemsData(itemN, price, qty, invoice);
        invoice.getItems().add(item);
        ItemsTableModel itemsTableModel =  (ItemsTableModel) frame.getItemsTable().getModel();
        //itemsTableModel.getItems().add(item);
        itemsTableModel.fireTableDataChanged();
        frame.getInvTableModel().fireTableDataChanged();
                
        }
        
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog = null;
    }

    private void newItemCancel() 
    {
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog = null;
    }
        
   
    
    
}
