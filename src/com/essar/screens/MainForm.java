/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.screens;

import com.essar.utils.GlobalVariables;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 *
 * @author rahumathulla
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        this.setTitle("Tienda 2.2");
        this.setExtendedState(6);
        
        //lblPoweredBy.setAlignmentX(20);
        //lblPoweredBy.setAlignmentY(200);
        lblPoweredBy.setBounds(800,800, 300, 100);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tienda.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);         
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        cmdSupplier = new javax.swing.JButton();
        cmdSales = new javax.swing.JButton();
        cmdPurchase = new javax.swing.JButton();
        cmdStock = new javax.swing.JButton();
        cmdGst = new javax.swing.JButton();
        cmdPricingTier = new javax.swing.JButton();
        cmdProductCategory = new javax.swing.JButton();
        lblPoweredBy = new javax.swing.JLabel();
        cmdReports = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        mnuOperations = new javax.swing.JMenu();
        mitSales = new javax.swing.JMenuItem();
        mitPurchase = new javax.swing.JMenuItem();
        mitStockEntry = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mitSalesReturn = new javax.swing.JMenuItem();
        mitPurchaseReturn = new javax.swing.JMenuItem();
        mitExit = new javax.swing.JMenuItem();
        mnuInsights = new javax.swing.JMenu();
        mitActivityLog = new javax.swing.JMenuItem();
        mnuBookKeeping = new javax.swing.JMenu();
        mitDayBook = new javax.swing.JMenuItem();
        mitIncomeStatement = new javax.swing.JMenuItem();
        mnuSettings = new javax.swing.JMenu();
        mitGST = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mitUsers = new javax.swing.JMenuItem();
        mitSuppliers = new javax.swing.JMenuItem();
        mitCustomers = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mitPricingTier = new javax.swing.JMenuItem();
        mitProductCategories = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mitIncomeExpenseLine = new javax.swing.JMenuItem();
        mnuBookKeeping1 = new javax.swing.JMenu();
        mitStockAudit = new javax.swing.JMenuItem();
        mitIncomeStatement1 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        mitBackup = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        desktopPane.setDesktopManager(null);

        cmdSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supplier.png"))); // NOI18N
        cmdSupplier.setMnemonic('u');
        cmdSupplier.setText("Supplier");
        cmdSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSupplierActionPerformed(evt);
            }
        });
        desktopPane.add(cmdSupplier);
        cmdSupplier.setBounds(330, 270, 180, 50);

        cmdSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales.png"))); // NOI18N
        cmdSales.setMnemonic('S');
        cmdSales.setText("Sales");
        cmdSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSalesActionPerformed(evt);
            }
        });
        desktopPane.add(cmdSales);
        cmdSales.setBounds(100, 90, 180, 50);

        cmdPurchase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/purchase.png"))); // NOI18N
        cmdPurchase.setMnemonic('P');
        cmdPurchase.setText("Purchase");
        cmdPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPurchaseActionPerformed(evt);
            }
        });
        desktopPane.add(cmdPurchase);
        cmdPurchase.setBounds(100, 150, 180, 50);

        cmdStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock.png"))); // NOI18N
        cmdStock.setMnemonic('t');
        cmdStock.setText("Menu");
        cmdStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStockActionPerformed(evt);
            }
        });
        desktopPane.add(cmdStock);
        cmdStock.setBounds(100, 210, 180, 50);

        cmdGst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gst.png"))); // NOI18N
        cmdGst.setMnemonic('G');
        cmdGst.setText("GST");
        cmdGst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGstActionPerformed(evt);
            }
        });
        desktopPane.add(cmdGst);
        cmdGst.setBounds(330, 90, 180, 50);

        cmdPricingTier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
        cmdPricingTier.setMnemonic('c');
        cmdPricingTier.setText("Customers");
        cmdPricingTier.setToolTipText("");
        cmdPricingTier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPricingTierActionPerformed(evt);
            }
        });
        desktopPane.add(cmdPricingTier);
        cmdPricingTier.setBounds(330, 150, 180, 50);

        cmdProductCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categories.png"))); // NOI18N
        cmdProductCategory.setMnemonic('d');
        cmdProductCategory.setText("Product Category");
        cmdProductCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProductCategoryActionPerformed(evt);
            }
        });
        desktopPane.add(cmdProductCategory);
        cmdProductCategory.setBounds(330, 210, 180, 50);

        lblPoweredBy.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        lblPoweredBy.setForeground(new java.awt.Color(255, 255, 255));
        lblPoweredBy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPoweredBy.setText("Powered by: essaar software solutions.");
        lblPoweredBy.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        desktopPane.add(lblPoweredBy);
        lblPoweredBy.setBounds(470, 580, 330, 20);

        cmdReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report_home.png"))); // NOI18N
        cmdReports.setMnemonic('R');
        cmdReports.setText("Reports");
        cmdReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdReportsActionPerformed(evt);
            }
        });
        desktopPane.add(cmdReports);
        cmdReports.setBounds(100, 270, 180, 50);

        mnuOperations.setMnemonic('O');
        mnuOperations.setText("Operations");
        mnuOperations.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mitSales.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mitSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales.png"))); // NOI18N
        mitSales.setMnemonic('s');
        mitSales.setText("Sales");
        mitSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitSalesActionPerformed(evt);
            }
        });
        mnuOperations.add(mitSales);

        mitPurchase.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mitPurchase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/purchase.png"))); // NOI18N
        mitPurchase.setMnemonic('P');
        mitPurchase.setText("Purchase");
        mitPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitPurchaseActionPerformed(evt);
            }
        });
        mnuOperations.add(mitPurchase);

        mitStockEntry.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mitStockEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock.png"))); // NOI18N
        mitStockEntry.setMnemonic('E');
        mitStockEntry.setText("Menu Entry");
        mitStockEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitStockEntryActionPerformed(evt);
            }
        });
        mnuOperations.add(mitStockEntry);
        mnuOperations.add(jSeparator1);

        mitSalesReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales_return.png"))); // NOI18N
        mitSalesReturn.setMnemonic('R');
        mitSalesReturn.setText("Sales Return");
        mitSalesReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitSalesReturnActionPerformed(evt);
            }
        });
        mnuOperations.add(mitSalesReturn);

        mitPurchaseReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/purchase_return.png"))); // NOI18N
        mitPurchaseReturn.setMnemonic('u');
        mitPurchaseReturn.setText("Purchase Return");
        mitPurchaseReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitPurchaseReturnActionPerformed(evt);
            }
        });
        mnuOperations.add(mitPurchaseReturn);

        mitExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mitExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        mitExit.setMnemonic('x');
        mitExit.setText("Exit");
        mitExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitExitActionPerformed(evt);
            }
        });
        mnuOperations.add(mitExit);

        menuBar.add(mnuOperations);

        mnuInsights.setMnemonic('I');
        mnuInsights.setText(" Insights");
        mnuInsights.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mnuInsights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInsightsActionPerformed(evt);
            }
        });

        mitActivityLog.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mitActivityLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/log.png"))); // NOI18N
        mitActivityLog.setText("Activity Log");
        mitActivityLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitActivityLogActionPerformed(evt);
            }
        });
        mnuInsights.add(mitActivityLog);

        menuBar.add(mnuInsights);

        mnuBookKeeping.setMnemonic('B');
        mnuBookKeeping.setText(" Book Keeping");
        mnuBookKeeping.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mitDayBook.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mitDayBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book_keeping.png"))); // NOI18N
        mitDayBook.setText("Day Book");
        mitDayBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitDayBookActionPerformed(evt);
            }
        });
        mnuBookKeeping.add(mitDayBook);

        mitIncomeStatement.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        mitIncomeStatement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/income_statement.png"))); // NOI18N
        mitIncomeStatement.setText("Income Statement");
        mitIncomeStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitIncomeStatementActionPerformed(evt);
            }
        });
        mnuBookKeeping.add(mitIncomeStatement);

        menuBar.add(mnuBookKeeping);

        mnuSettings.setMnemonic('e');
        mnuSettings.setText(" Settings");
        mnuSettings.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mitGST.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        mitGST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gst.png"))); // NOI18N
        mitGST.setMnemonic('t');
        mitGST.setText("GST");
        mitGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitGSTActionPerformed(evt);
            }
        });
        mnuSettings.add(mitGST);
        mnuSettings.add(jSeparator3);

        mitUsers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mitUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/users.png"))); // NOI18N
        mitUsers.setMnemonic('d');
        mitUsers.setText("Users");
        mitUsers.setEnabled(false);
        mnuSettings.add(mitUsers);

        mitSuppliers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        mitSuppliers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supplier.png"))); // NOI18N
        mitSuppliers.setMnemonic('y');
        mitSuppliers.setText("Suppliers");
        mitSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitSuppliersActionPerformed(evt);
            }
        });
        mnuSettings.add(mitSuppliers);

        mitCustomers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        mitCustomers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
        mitCustomers.setMnemonic('p');
        mitCustomers.setText("Customers");
        mitCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitCustomersActionPerformed(evt);
            }
        });
        mnuSettings.add(mitCustomers);
        mnuSettings.add(jSeparator2);

        mitPricingTier.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        mitPricingTier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pricing.png"))); // NOI18N
        mitPricingTier.setText("Pricing Tier");
        mitPricingTier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitPricingTierActionPerformed(evt);
            }
        });
        mnuSettings.add(mitPricingTier);

        mitProductCategories.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        mitProductCategories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categories.png"))); // NOI18N
        mitProductCategories.setText("Product Categories");
        mitProductCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitProductCategoriesActionPerformed(evt);
            }
        });
        mnuSettings.add(mitProductCategories);
        mnuSettings.add(jSeparator4);

        mitIncomeExpenseLine.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mitIncomeExpenseLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/income_expense_line.png"))); // NOI18N
        mitIncomeExpenseLine.setText("Income/Expense Line");
        mitIncomeExpenseLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitIncomeExpenseLineActionPerformed(evt);
            }
        });
        mnuSettings.add(mitIncomeExpenseLine);

        menuBar.add(mnuSettings);

        mnuBookKeeping1.setMnemonic('A');
        mnuBookKeeping1.setText(" Audit");
        mnuBookKeeping1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mitStockAudit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mitStockAudit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/audit.png"))); // NOI18N
        mitStockAudit.setText("Stock Audit");
        mitStockAudit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitStockAuditActionPerformed(evt);
            }
        });
        mnuBookKeeping1.add(mitStockAudit);

        mitIncomeStatement1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        mitIncomeStatement1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/income_statement.png"))); // NOI18N
        mitIncomeStatement1.setText("Income Statement");
        mitIncomeStatement1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitIncomeStatement1ActionPerformed(evt);
            }
        });
        mnuBookKeeping1.add(mitIncomeStatement1);

        menuBar.add(mnuBookKeeping1);

        helpMenu.setMnemonic('h');
        helpMenu.setText(" Help");

        contentMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/contents.png"))); // NOI18N
        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        contentMenuItem.setEnabled(false);
        helpMenu.add(contentMenuItem);

        mitBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backup.png"))); // NOI18N
        mitBackup.setText("Backup & Restore");
        mitBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitBackupActionPerformed(evt);
            }
        });
        helpMenu.add(mitBackup);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about.png"))); // NOI18N
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mitExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mitExitActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        About about = new About();
        about.setBounds(100, 100, 468, 320);
        about.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void mitProductCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitProductCategoriesActionPerformed
        // TODO add your handling code here:
        ProductCategoryForm productCategory = new ProductCategoryForm();
        productCategory.setBounds(cmdGst.getX(), cmdGst.getY(), 520, 360);
        productCategory.setVisible(true);
    }//GEN-LAST:event_mitProductCategoriesActionPerformed

    private void mitSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitSuppliersActionPerformed
        // TODO add your handling code here:
        SupplierForm supplierForm = new SupplierForm();
        //supplierForm.setBounds(cmdGst.getX(), cmdGst.getY(), 820, 540);
        supplierForm.setVisible(true);
    }//GEN-LAST:event_mitSuppliersActionPerformed

    private void mitStockEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitStockEntryActionPerformed
        // TODO add your handling code here:
        StockEntry stockEntry = new StockEntry();
        stockEntry.setBounds(100, 100, 820, 540);
        stockEntry.setVisible(true);
    }//GEN-LAST:event_mitStockEntryActionPerformed

    private void mitSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitSalesActionPerformed
        // TODO add your handling code here:
        /*SalesForm salesForm = new SalesForm();
        salesForm.setBounds(100, 100, 800, 500);
        salesForm.setVisible(true);*/
        
        SalesWindow salesWindow = new SalesWindow();
        salesWindow.setBounds(40, 40, 980, 680);
        salesWindow.setVisible(true);
    }//GEN-LAST:event_mitSalesActionPerformed

    private void mitPricingTierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitPricingTierActionPerformed
        // TODO add your handling code here:
        PricingTierForm pricingTierForm =  new PricingTierForm();
        pricingTierForm.setBounds(cmdGst.getX(), cmdGst.getY(), 820, 540);
        pricingTierForm.setVisible(true);
    }//GEN-LAST:event_mitPricingTierActionPerformed

    private void mitCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitCustomersActionPerformed
        // TODO add your handling code here:
        CustomerForm customerForm = new CustomerForm();
        //pricingTierForm.setBounds(100, 100, 560, 495);
        customerForm.setVisible(true);
    }//GEN-LAST:event_mitCustomersActionPerformed

    private void mitActivityLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitActivityLogActionPerformed
        // TODO add your handling code here:
        ActivityLog activityLog =  new ActivityLog();
        activityLog.setVisible(true);
    }//GEN-LAST:event_mitActivityLogActionPerformed

    private void mnuInsightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInsightsActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_mnuInsightsActionPerformed

    private void cmdReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdReportsActionPerformed
        // TODO add your handling code here:
        ViewReports viewReports =  new ViewReports();
        viewReports.setBounds(60, 60, 580, 320);
        viewReports.setVisible(true);
    }//GEN-LAST:event_cmdReportsActionPerformed

    private void cmdProductCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProductCategoryActionPerformed
        // TODO add your handling code here:
        ProductCategoryForm productCategoryForm = new ProductCategoryForm();
        productCategoryForm.setBounds(cmdGst.getX(), cmdGst.getY(), 520, 360);
        productCategoryForm.setVisible(true);

    }//GEN-LAST:event_cmdProductCategoryActionPerformed

    private void cmdPricingTierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPricingTierActionPerformed
        // TODO add your handling code here:
        CustomerForm customerForm = new CustomerForm();
        customerForm.setVisible(true);
    }//GEN-LAST:event_cmdPricingTierActionPerformed

    private void cmdGstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGstActionPerformed
        // TODO add your handling code here:
        
        GSTForm gSTForm = new GSTForm();
        gSTForm.setBounds(cmdGst.getX(), cmdGst.getY(), 460, 440);
        gSTForm.setVisible(true);
    }//GEN-LAST:event_cmdGstActionPerformed

    private void cmdStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStockActionPerformed
        // TODO add your handling code here:
        StockEntry stockEntry = new StockEntry();
        stockEntry.setBounds(40, 40, 640, 540);
        stockEntry.setVisible(true);
    }//GEN-LAST:event_cmdStockActionPerformed

    private void cmdPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPurchaseActionPerformed
        // TODO add your handling code here:
        PurchaseWindow purchaseWindow = new PurchaseWindow();
        purchaseWindow.setBounds(40, 40, 820, 600);
        purchaseWindow.setVisible(true);
    }//GEN-LAST:event_cmdPurchaseActionPerformed

    private void cmdSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSalesActionPerformed
        // TODO add your handling code here:
        SalesWindow salesWindow = new SalesWindow();
        //salesWindow.setBounds(40, 40, 980, 680);
        salesWindow.setExtendedState(6);
        salesWindow.setVisible(true);
        
        // java - get screen size using the Toolkit class
        /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SalesWindow salesWindow = new SalesWindow();
        salesWindow.setBounds(0, 0, screenSize.width, screenSize.height);
        salesWindow.setVisible(true);*/
    }//GEN-LAST:event_cmdSalesActionPerformed

    private void cmdSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSupplierActionPerformed
        // TODO add your handling code here:
        SupplierForm supplierForm = new SupplierForm();
        //supplierForm.setBounds(100, 100, 800, 500);
        supplierForm.setVisible(true);

    }//GEN-LAST:event_cmdSupplierActionPerformed

    private void mitIncomeExpenseLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitIncomeExpenseLineActionPerformed
        // TODO add your handling code here:
        IncomeExpenseLine incomeExpenseLine = new IncomeExpenseLine();
        incomeExpenseLine.setBounds(cmdGst.getX(), cmdGst.getY(), 860, 540);
        incomeExpenseLine.setVisible(true);
    }//GEN-LAST:event_mitIncomeExpenseLineActionPerformed

    private void mitPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitPurchaseActionPerformed
        // TODO add your handling code here:
        PurchaseWindow purchaseWindow = new PurchaseWindow();
        purchaseWindow.setBounds(40, 40, 960, 680);
        purchaseWindow.setVisible(true);
    }//GEN-LAST:event_mitPurchaseActionPerformed

    private void mitPurchaseReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitPurchaseReturnActionPerformed
        // TODO add your handling code here:
        PurchaseWindow purchaseWindow = new PurchaseWindow();
        purchaseWindow.setBounds(40, 40, 960, 680);
        purchaseWindow.setVisible(true);
    }//GEN-LAST:event_mitPurchaseReturnActionPerformed

    private void mitSalesReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitSalesReturnActionPerformed
        // TODO add your handling code here:
        SalesWindow salesWindow = new SalesWindow();
        salesWindow.setBounds(40, 40, 980, 680);
        salesWindow.setVisible(true);
    }//GEN-LAST:event_mitSalesReturnActionPerformed

    private void mitGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitGSTActionPerformed
        // TODO add your handling code here:
        GSTForm gSTForm = new GSTForm();
        gSTForm.setBounds(cmdGst.getX(), cmdGst.getY(), 460, 440);
        gSTForm.setVisible(true);
    }//GEN-LAST:event_mitGSTActionPerformed

    private void mitIncomeStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitIncomeStatementActionPerformed
        // TODO add your handling code here:
        IncomeStatement incomeStatement = new IncomeStatement();
        incomeStatement.setBounds(100, 50, 960, 660);
        incomeStatement.setVisible(true);
    }//GEN-LAST:event_mitIncomeStatementActionPerformed

    private void mitDayBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitDayBookActionPerformed
        // TODO add your handling code here:
        DayBookForm dayBookForm = new DayBookForm();
        dayBookForm.setBounds(100, 50, 960, 660);
        dayBookForm.setVisible(true);
    }//GEN-LAST:event_mitDayBookActionPerformed

    private void mitStockAuditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitStockAuditActionPerformed
        // TODO add your handling code here:
        StockAudit stockAudit =  new StockAudit();
        stockAudit.setBounds(60, 60, 960,660);
        stockAudit.setVisible(true);
    }//GEN-LAST:event_mitStockAuditActionPerformed

    private void mitIncomeStatement1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitIncomeStatement1ActionPerformed
        // TODO add your handling code here:
        IncomeStatement incomeStatement = new IncomeStatement();
        incomeStatement.setBounds(100, 50, 960, 660);
        incomeStatement.setVisible(true);
    }//GEN-LAST:event_mitIncomeStatement1ActionPerformed

    private void mitBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitBackupActionPerformed
        // TODO add your handling code here:
        Backup backup =  new Backup();
        backup.setBounds(200,160,580,500);
        backup.setVisible(true);

    }//GEN-LAST:event_mitBackupActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new MainForm().setVisible(true);
            }
        });
    }
          private void handleClosing() {
        if (hasUnsaveData()) {
            int answer = showWarningMessage();
             
            switch (answer) {
                case JOptionPane.YES_OPTION:
                    System.out.println("Quit");
                    dispose();
                    break;
                     
                case JOptionPane.NO_OPTION:
                    System.out.println("Don't Quit");
                    //dispose();
                    break;
                     
                case JOptionPane.CANCEL_OPTION:
                    System.out.println("Don't Quit");
                    break;
            }
        } else {
            dispose();
        }      
    }
    
       private int showWarningMessage() {
        String[] buttonLabels = new String[] {"Yes", "No"};
        String defaultOption = buttonLabels[1];
        Icon icon = null;
        //new javax.swing.ImageIcon(getClass().getResource("/images/save.png"));
         
        return JOptionPane.showOptionDialog(this,
                "Do you really want to exit the application?\n",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);    
    }
 
    private boolean hasUnsaveData() {
        // checks if there's still something unsaved
        // this method always return true for demo purpose
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton cmdGst;
    private javax.swing.JButton cmdPricingTier;
    private javax.swing.JButton cmdProductCategory;
    private javax.swing.JButton cmdPurchase;
    private javax.swing.JButton cmdReports;
    private javax.swing.JButton cmdSales;
    private javax.swing.JButton cmdStock;
    private javax.swing.JButton cmdSupplier;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel lblPoweredBy;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mitActivityLog;
    private javax.swing.JMenuItem mitBackup;
    private javax.swing.JMenuItem mitCustomers;
    private javax.swing.JMenuItem mitDayBook;
    private javax.swing.JMenuItem mitExit;
    private javax.swing.JMenuItem mitGST;
    private javax.swing.JMenuItem mitIncomeExpenseLine;
    private javax.swing.JMenuItem mitIncomeStatement;
    private javax.swing.JMenuItem mitIncomeStatement1;
    private javax.swing.JMenuItem mitPricingTier;
    private javax.swing.JMenuItem mitProductCategories;
    private javax.swing.JMenuItem mitPurchase;
    private javax.swing.JMenuItem mitPurchaseReturn;
    private javax.swing.JMenuItem mitSales;
    private javax.swing.JMenuItem mitSalesReturn;
    private javax.swing.JMenuItem mitStockAudit;
    private javax.swing.JMenuItem mitStockEntry;
    private javax.swing.JMenuItem mitSuppliers;
    private javax.swing.JMenuItem mitUsers;
    private javax.swing.JMenu mnuBookKeeping;
    private javax.swing.JMenu mnuBookKeeping1;
    private javax.swing.JMenu mnuInsights;
    private javax.swing.JMenu mnuOperations;
    private javax.swing.JMenu mnuSettings;
    // End of variables declaration//GEN-END:variables

}
