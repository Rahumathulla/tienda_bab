/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.screens;

import com.essar.dao.CustomerDAO;
import com.essar.dao.SalesDAO;
import com.essar.dao.StockDAO;
import com.essar.dao.SupplierDAO;
import com.essar.pojos.Customer;
import com.essar.pojos.SalesView;
import com.essar.pojos.Stock;
import com.essar.pojos.Supplier;
import com.essar.suggestion.SuggestionUtility;
import com.essar.utils.QueryStrings;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author rahumathulla
 */
public class ItemWiseSales extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Stock
     */
    long stockId=0;
    DecimalFormat df = new DecimalFormat("####.##");
    Stock stock = null;
    double gSalesPercentage = 0;
    double gStockAvailable = 0;
    Customer customer =null;
    DefaultTableModel salesModel = null;
    boolean customerSelected = false;
    public ItemWiseSales() {
        initComponents();
        initializeForm();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tienda.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mitDelete = new javax.swing.JMenuItem();
        mitView = new javax.swing.JMenuItem();
        mnuTable = new javax.swing.JPopupMenu();
        pnlStockEntry = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        lblCustomerName = new javax.swing.JLabel();
        cmbCustomerName = new javax.swing.JComboBox();
        panSalesView = new javax.swing.JScrollPane();
        tblSalesView = new javax.swing.JTable();
        btnCancel = new javax.swing.JButton();
        lblTotalQty = new javax.swing.JLabel();
        lblTotalSalesValue = new javax.swing.JLabel();

        mitDelete.setText("jMenuItem1");

        mitView.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Item-wise Report");
        setExtendedState(6);

        pnlStockEntry.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Details"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Commodity/Item Name");

        txtItemName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtItemNameFocusLost(evt);
            }
        });
        txtItemName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtItemNameMouseClicked(evt);
            }
        });
        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });
        txtItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtItemNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtItemNameKeyTyped(evt);
            }
        });

        cmdSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        cmdSearch.setMnemonic('S');
        cmdSearch.setText("Search");
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        lblCustomerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCustomerName.setText("Customer Name");

        cmbCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCustomerNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStockEntryLayout = new javax.swing.GroupLayout(pnlStockEntry);
        pnlStockEntry.setLayout(pnlStockEntryLayout);
        pnlStockEntryLayout.setHorizontalGroup(
            pnlStockEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStockEntryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStockEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlStockEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cmdSearch)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        pnlStockEntryLayout.setVerticalGroup(
            pnlStockEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStockEntryLayout.createSequentialGroup()
                .addGroup(pnlStockEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStockEntryLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(pnlStockEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlStockEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlStockEntryLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(cmdSearch)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panSalesView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panSalesViewMouseClicked(evt);
            }
        });
        panSalesView.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panSalesViewKeyPressed(evt);
            }
        });

        tblSalesView.setForeground(new java.awt.Color(0, 153, 153));
        tblSalesView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Bill Number", "Item Name", "Qty", "Item Price", "Discount", "Net Amount", "Sales Date", "Sales Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSalesView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalesViewMouseClicked(evt);
            }
        });
        tblSalesView.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSalesViewKeyPressed(evt);
            }
        });
        panSalesView.setViewportView(tblSalesView);

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnCancel.setMnemonic('C');
        btnCancel.setText("Close");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblTotalQty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalQty.setForeground(new java.awt.Color(255, 51, 102));

        lblTotalSalesValue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotalSalesValue.setForeground(new java.awt.Color(0, 102, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(lblTotalSalesValue, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlStockEntry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panSalesView))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStockEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panSalesView, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalSalesValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameFocusLost

    private void txtItemNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtItemNameMouseClicked

    }//GEN-LAST:event_txtItemNameMouseClicked

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
        System.out.println("---inside Action Peformed");
        performItemFieldAction();
        populateItemTransaction(stockId);
        
    }//GEN-LAST:event_txtItemNameActionPerformed

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameKeyPressed

    private void txtItemNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            System.out.println("---inside item name enter");
            try{
                performItemFieldAction();
                populateItemTransaction(stockId);
            }
            catch(Exception e){
                System.out.println("Error Retrieving the item -"+e.getMessage());
            }
        }
    }//GEN-LAST:event_txtItemNameKeyReleased

    private void txtItemNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameKeyTyped

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        // TODO add your handling code here:
        try{
                performItemFieldAction();
                populateItemTransaction(stockId);
            }
            catch(Exception e){
                System.out.println("Error Retrieving the item -"+e.getMessage());
            }
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void tblSalesViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalesViewMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2 && tblSalesView.getSelectedRow() != -1){

        }
    }//GEN-LAST:event_tblSalesViewMouseClicked

    private void tblSalesViewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSalesViewKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_HOME && tblSalesView.getRowCount()>0){
            mnuTable.removeAll();
            //Add Existing
            mitView = new JMenuItem("View");
            mitView.addActionListener(this);
            mnuTable.add(mitView);
            //Add New
            mitDelete = new JMenuItem("Delete");
            mitDelete.addActionListener(this);
            mnuTable.add(mitDelete);

        }
        if(evt.getKeyCode()==KeyEvent.VK_END){
            //JOptionPane.showMessageDialog(this, "Hi");
            tblSalesView.changeSelection(salesModel.getRowCount()-1,1,false, false);
        }
    }//GEN-LAST:event_tblSalesViewKeyPressed

    private void panSalesViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panSalesViewMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            //syncDataFromViewToTable();

        }
    }//GEN-LAST:event_panSalesViewMouseClicked

    private void panSalesViewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panSalesViewKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //syncDataFromViewToTable();
        }
    }//GEN-LAST:event_panSalesViewKeyPressed

    private void cmbCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCustomerNameActionPerformed
        // TODO add your handling code here:

        customer = (Customer) cmbCustomerName.getSelectedItem();
        if(customer.getCustomerName().equalsIgnoreCase("customer") || customer.getCustomerName().equalsIgnoreCase("new")
            || customer.getCustomerName().equalsIgnoreCase("name")|| customer.getCustomerName().equalsIgnoreCase("default")){
            customerSelected = false;
        }else
        customerSelected = true;
    }//GEN-LAST:event_cmbCustomerNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ItemWiseSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemWiseSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemWiseSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemWiseSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemWiseSales().setVisible(true);
            }
        });
    }
    
    public void initializeForm(){
        stockId =0;
        //txtHSNCode.requestFocus();

        SupplierDAO supplierDAO = new SupplierDAO();
        renderDataIntoTable(supplierDAO.retrieveAll());
        SuggestionUtility.populateItemNames(this, txtItemName);
        
        CustomerDAO customerDAO = new CustomerDAO();
        renderCustomerDataIntoDropDown(customerDAO.retrieveAll());
            //txtItemName.requestFocus();
        txtItemName.requestFocus();
        clearItemFields();

    }
    
    public void clearItemFields(){
        txtItemName.setText("");
        //txtHSNCode.setText("");
    }
    
    public void enableFormElements(boolean flag){
        //txtHSNCode.setEnabled(flag);
       
    }
    public boolean validateForm(){
        //btnSave.setText("Add");
        //btnSave.requestFocus();

        if(null == txtItemName.getText() || txtItemName.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Item name "+txtItemName.getText() +" is not valid.");
            txtItemName.requestFocus();
            return false;
        }
        
        return true;
    }
    
    public void renderCustomerDataIntoDropDown(List<Customer> customerList){
        Customer customer = null;
        if(customerList!= null){
            //cmbCustomerName.addItem("Select");
            for(int i=0; i<customerList.size();i++){
                cmbCustomerName.addItem(customerList.get(i));
            }
            cmbCustomerName.setSelectedIndex(0);
            customer = (Customer)cmbCustomerName.getSelectedItem();            
        }
    }
    
    public void populateItemTransaction(long itemId){
        System.out.println("Inside populateItemTransaction-------");
        String selectClause = "SELECT s.sales_id"
            + ", s.customer_name"
            + ", s.customer_name"
            + ", s.bill_number"
            + ", d.quantity"
            + ", d.unit_price"
            + ", d.net_amount"
            + ", d.discount"
            + ", d.gross_amount"
            + ", s.sales_date"
            + ", d.item_name  "
            + " from sales s "
            + " JOIN customer cust ON s.customer_id = cust.customer_id"
            + " JOIN sales_details d ON s.sales_id = d.sales_id WHERE 1=1 ";
        if(itemId>0)
                selectClause = selectClause + " AND d.item_id="+itemId;
        if(customerSelected)
            selectClause = selectClause + " AND s.customer_id="+customer.getCustomerId();
        System.out.println("Query---"+selectClause);
        SalesDAO salesDAO = new SalesDAO();
        renderItemTransactionDataIntoTable(salesDAO.retrieveSalesByQuery(selectClause));
        
    }
    
    public void performItemFieldAction(){
        try{
            StockDAO stockDAO = null;
            //Stock
            stock = null;
            double dUnitGst=0.0;
            double dUnitSellingPrice=0.0;
            double dUnitDiscount=0.0;
            double dUnitPostGstPrice=0.0;

            if(!txtItemName.getText().equalsIgnoreCase(""))
            stockDAO = new StockDAO();
            stock = stockDAO.retrieveByName(txtItemName.getText().trim());
            stockId = stock.getItemId();
            //txtHSNCode.setText(stock.getHsnCode());

            //GST Amount Calculation
            dUnitGst = stock.getGstPercentage()*stock.getPurchasePrice()/100;
            //txt.setText(df.format(dUnitGst)+"");

            //Price after Tax calculation
            dUnitPostGstPrice = dUnitGst +stock.getPurchasePrice();

            //Discount calculation
            if(stock.getMrp()>0){

            }
            //dUnitDiscount = Double.parseDouble(stock.getDiscount())*Double.parseDouble(stock.getPurchasePrice())/100;

            //Selling Price calculation
            dUnitSellingPrice = dUnitPostGstPrice + dUnitPostGstPrice*stock.getMargin()/100;
            double tempPrice = dUnitSellingPrice;
            dUnitSellingPrice = dUnitSellingPrice * gSalesPercentage *0.01;// *.01 is equivalent to division by 100

            System.out.println("outside cust type loop");


            //txtSellingPrice.setText(df.format(dUnitSellingPrice)+"");
            //Stock Availability Check
            gStockAvailable = stock.getQuantity();
            //txtHSNCode.setEnabled(false);
            
            List<Double> summaryList = null;
            stockDAO = new StockDAO();
            System.out.println("ITEM ID :" +stock.getItemId());
            summaryList = stockDAO.retrieveAuditSummary(stock.getItemId(), stock.getItemName());
            System.out.println("Summary Size :"+summaryList.size());
                       
            
            cmdSearch.requestFocus();
            
        }
        catch(Exception e){
            System.out.println("Error Retrieving the item -"+e.getMessage());
        }
    }
    
    public void renderDataIntoTable(List<Supplier> supplierList){

        
    }
    
    public void renderItemTransactionDataIntoTable(List<SalesView> salesViewList){

        salesModel = (DefaultTableModel) tblSalesView.getModel();
        salesModel.setRowCount(0);
        TableColumn column = tblSalesView.getColumnModel().getColumn(tblSalesView.getColumnCount()-1);
        column.setPreferredWidth(0);
        column.setMaxWidth(0);
        //column.setWidth(0);
        
        double totalQty=0;
        double totalValue=0;
        for(int i=0;i<salesViewList.size();i++){
            //salesModel.addRow(new Object[]{salesViewList.get(i).getCustomerName(), salesViewList.get(i).getBillNumber(),salesViewList.get(i).getBillAmount(),salesViewList.get(i).getCess(),salesViewList.get(i).getDiscount(), salesViewList.get(i).getPayableAmount(), salesViewList.get(i).getSalesDate(), salesViewList.get(i).getStatus()});
            salesModel.addRow(new Object[]{salesViewList.get(i).getCustomerName()
                    , salesViewList.get(i).getBillNumber()
                    , salesViewList.get(i).getStatus()
                    , salesViewList.get(i).getQuantity()
                    , salesViewList.get(i).getBillAmount()
                    , salesViewList.get(i).getDiscount()
                    , salesViewList.get(i).getPayableAmount()
                    , salesViewList.get(i).getSalesDate()                    
                    , salesViewList.get(i).getSalesId()});
            totalQty+=salesViewList.get(i).getQuantity();
            totalValue+=salesViewList.get(i).getPayableAmount();
        }
        //if(totalQty>0){
            lblTotalQty.setText("Total Qty : "+totalQty);
            lblTotalSalesValue.setText("Total Sales Value : "+totalValue);
        //}

    }   


    @Override
    public void actionPerformed(ActionEvent event) {
        try{
            JMenuItem menu = (JMenuItem) event.getSource();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox cmbCustomerName;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCustomerName;
    private javax.swing.JLabel lblTotalQty;
    private javax.swing.JLabel lblTotalSalesValue;
    private javax.swing.JMenuItem mitDelete;
    private javax.swing.JMenuItem mitView;
    private javax.swing.JPopupMenu mnuTable;
    private javax.swing.JScrollPane panSalesView;
    private javax.swing.JPanel pnlStockEntry;
    private javax.swing.JTable tblSalesView;
    private javax.swing.JTextField txtItemName;
    // End of variables declaration//GEN-END:variables
}
