/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.screens;

import com.essar.dao.CustomerDAO;
import com.essar.dao.PricingTierDAO;
import com.essar.dao.EstimateDAO;
import com.essar.dao.SalesDAO;
import com.essar.dao.StockDAO;
import com.essar.pojos.Customer;
import com.essar.pojos.PricingTier;
import com.essar.suggestion.SuggestionUtility;
import com.essar.utils.TableMouseListener;
import com.essar.pojos.Sales;
import com.essar.pojos.SalesDetails;
import com.essar.pojos.SalesReturn;
import com.essar.pojos.Stock;
import com.essar.utils.ConnectionManager;
import com.essar.utils.GenericUtils;
import com.essar.utils.PropertyUtil;
import com.essar.utils.QueryStrings;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rahumathulla
 */
public class DummySales extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form ProductCategory
     */
    String billerName="";
    String billType = "";
    long billSeqNumber=0;
    double gSalesPercentage = 0;
    Stock stock = null;
    double gBillAmount = 0;
    double gDiscount = 0;
    double gPyableAmount = 0;
    double gBalanceAmount = 0;
    double gTotalGst = 0;
    double gSGst = 0;
    double gCGst = 0;
    double gQuantity = 0;
    double gGst12 = 0;
    double gGst18 = 0;
    double gGst5 = 0;
    double gGst28 = 0;
    double gCess = 0;
    double gStockAvailable = 0;
    long saleIdEdit=0;
    HashMap moreMap = new HashMap();
    HashMap returnMap = new HashMap();
    List<SalesReturn> returnList = null;
    DecimalFormat df = new DecimalFormat("####.##");
    DefaultTableModel salesDetailsModel = null;
    
    //!-- 03/15/2020 Added for calculating taxable value
    double gTaxableAt12 = 0;
    double gTaxableAt18 = 0;
    double gTaxableAt5 = 0;
    double gTaxableAt28 = 0;
    double gTaxableAt0 = 0;
    
    //--Calculation for cess;
    double gCessGross = 0;
    
    //--Calculation for Mrp total used for deriving Discount
    double gTotalMrp = 0;
    //!-- 06/20/2020 Added for navigation
    long minId = 0;
    long maxId = 0;
    long currentId=0;
    
    boolean trackActivity =true;
    
    //!-- 01/12/2020 Printer Configuration for direct printing
    PropertyUtil propertyUtil = new PropertyUtil();
    Properties props = propertyUtil.readProperties();
    String b2c_printer = props.getProperty("b2c_printer");
    String b2b_printer = props.getProperty("b2b_printer");
    
    public DummySales() {
        initComponents();
        initializeForm();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tienda.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });
        txtBillNumber.setText("######");
    }
    
    public DummySales(Sales sales) {
        initComponents();
        //initializeForm();
        //fetchSalesDataIntoTable(sales);

    }
    
    private void printReportToPrinter(JasperPrint jp, String printer) {
        try {
            // TODO Auto-generated method stub
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
            printRequestAttributeSet.add(new Copies(1));

            PrinterName printerName = new PrinterName(printer, null); //gets printer

            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
            printServiceAttributeSet.add(printerName);

            JRPrintServiceExporter exporter = new JRPrintServiceExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
        } catch (JRException ex) {
             JOptionPane.showMessageDialog(null,"Printer not found..!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        mnuSalesDetails = new javax.swing.JPopupMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        mitDelete = new javax.swing.JMenuItem();
        stockDAO1 = new com.essar.dao.StockDAO();
        grpSalesType = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        mitReturn = new javax.swing.JMenuItem();
        mitEdit = new javax.swing.JMenuItem();
        pnlSupplier = new javax.swing.JPanel();
        lblItemName = new javax.swing.JLabel();
        lblHSNCode = new javax.swing.JLabel();
        txtHSNCode = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        spnSalesItems = new javax.swing.JScrollPane();
        tblSalesDetails = new javax.swing.JTable();
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        txtUnitPrice = new javax.swing.JTextField();
        lblUnitPrice = new javax.swing.JLabel();
        lblCustomerType = new javax.swing.JLabel();
        cmbCustomerType = new javax.swing.JComboBox();
        cmdReset = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblUnitPrice1 = new javax.swing.JLabel();
        txtSellingPrice = new javax.swing.JTextField();
        lblUnitPrice2 = new javax.swing.JLabel();
        txtGstPercentage = new javax.swing.JTextField();
        lblUnitPrice3 = new javax.swing.JLabel();
        txtGstAmount = new javax.swing.JTextField();
        lblCustomerName = new javax.swing.JLabel();
        cmbCustomerName = new javax.swing.JComboBox();
        dtpSalesDate = new com.toedter.calendar.JDateChooser();
        lblSalesDate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        radCash = new javax.swing.JRadioButton();
        radCredit = new javax.swing.JRadioButton();
        lblAvailableQty = new javax.swing.JLabel();
        lblDisplayName = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDisplayName = new javax.swing.JTextArea();
        lblHSNCode1 = new javax.swing.JLabel();
        txtVehicleDetails = new javax.swing.JTextField();
        lblDisplayName1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtShippingAddress = new javax.swing.JTextArea();
        chkSameAsAbove = new javax.swing.JCheckBox();
        lblUnitPrice4 = new javax.swing.JLabel();
        txtMrp = new javax.swing.JTextField();
        lblWPIndicator = new javax.swing.JLabel();
        txtBillNumber = new javax.swing.JTextField();
        chkWholesale = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        lblGst14 = new javax.swing.JLabel();
        lblGst12 = new javax.swing.JLabel();
        txtGst5 = new javax.swing.JTextField();
        txtGst12 = new javax.swing.JTextField();
        lblGst13 = new javax.swing.JLabel();
        txtGst18 = new javax.swing.JTextField();
        lblGst28 = new javax.swing.JLabel();
        txtGst28 = new javax.swing.JTextField();
        lblSGst = new javax.swing.JLabel();
        lblCGst = new javax.swing.JLabel();
        lblTotalGst = new javax.swing.JLabel();
        txtTotalGst = new javax.swing.JTextField();
        txtCGst = new javax.swing.JTextField();
        txtSGst = new javax.swing.JTextField();
        lblCess = new javax.swing.JLabel();
        txtCess = new javax.swing.JTextField();
        lblItemCount = new javax.swing.JLabel();
        lblGst17 = new javax.swing.JLabel();
        lblCustomerPrice = new javax.swing.JLabel();
        lblGst16 = new javax.swing.JLabel();
        lblLandingPrice = new javax.swing.JLabel();
        lblCPrice = new javax.swing.JLabel();
        lblContractorPrice = new javax.swing.JLabel();
        lblSGst4 = new javax.swing.JLabel();
        lblSGst2 = new javax.swing.JLabel();
        lblSGst1 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        txtDiscountAmount = new javax.swing.JTextField();
        txtTotalMrp = new javax.swing.JTextField();
        lblRoundOff = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnAddMore = new javax.swing.JButton();
        btnPrintPreview = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnViewAll = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        chkPayBalance = new javax.swing.JCheckBox();
        lblSGst7 = new javax.swing.JLabel();
        lblSGst5 = new javax.swing.JLabel();
        lblSGst6 = new javax.swing.JLabel();
        lblSGst3 = new javax.swing.JLabel();
        txtAmountPayable = new javax.swing.JTextField();
        txtPreviousBalance = new javax.swing.JTextField();
        txtPayingAmount = new javax.swing.JTextField();
        txtBalanceAmount = new javax.swing.JTextField();
        lblPPIndicator = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        mitDelete.setText("jMenuItem1");

        mitReturn.setText("jMenuItem1");

        mitEdit.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estimate");
        setExtendedState(6);
        setName("frmProductCategory"); // NOI18N

        lblItemName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblItemName.setText("Item Name");

        lblHSNCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblHSNCode.setText("HSN Code");

        txtHSNCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtHSNCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHSNCodeActionPerformed(evt);
            }
        });

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

        spnSalesItems.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spnSalesItemsKeyPressed(evt);
            }
        });

        tblSalesDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sl.No:", "HSN Code", "Item Name", "MRP", "Rate", "GST%", "GST Amt", "Qty", "Net Amount", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSalesDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSalesDetailsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSalesDetailsKeyReleased(evt);
            }
        });
        spnSalesItems.setViewportView(tblSalesDetails);

        lblQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblQuantity.setText("Quantity");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        txtUnitPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUnitPrice.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtUnitPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitPriceActionPerformed(evt);
            }
        });
        txtUnitPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnitPriceKeyReleased(evt);
            }
        });

        lblUnitPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUnitPrice.setText("Unit Price");

        lblCustomerType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCustomerType.setText("Customer Type");
        lblCustomerType.setMaximumSize(new java.awt.Dimension(84, 21));
        lblCustomerType.setMinimumSize(new java.awt.Dimension(84, 21));
        lblCustomerType.setPreferredSize(new java.awt.Dimension(84, 21));
        lblCustomerType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCustomerTypeMouseClicked(evt);
            }
        });

        cmbCustomerType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCustomerTypeActionPerformed(evt);
            }
        });
        cmbCustomerType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCustomerTypeKeyPressed(evt);
            }
        });

        cmdReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_small.png"))); // NOI18N
        cmdReset.setMnemonic('t');
        cmdReset.setText("Reset");
        cmdReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdResetActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Bill Number:");

        lblUnitPrice1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUnitPrice1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUnitPrice1.setText("Selling Price");

        txtSellingPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSellingPrice.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSellingPriceActionPerformed(evt);
            }
        });
        txtSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSellingPriceKeyReleased(evt);
            }
        });

        lblUnitPrice2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUnitPrice2.setText("GST%");

        txtGstPercentage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGstPercentage.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtGstPercentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGstPercentageActionPerformed(evt);
            }
        });

        lblUnitPrice3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUnitPrice3.setText("GST Amt");

        txtGstAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtGstAmount.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtGstAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGstAmountActionPerformed(evt);
            }
        });

        lblCustomerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCustomerName.setText("Customer Name");
        lblCustomerName.setMaximumSize(new java.awt.Dimension(87, 21));
        lblCustomerName.setMinimumSize(new java.awt.Dimension(87, 21));
        lblCustomerName.setPreferredSize(new java.awt.Dimension(87, 21));

        cmbCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCustomerNameActionPerformed(evt);
            }
        });
        cmbCustomerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCustomerNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCustomerNameKeyReleased(evt);
            }
        });

        dtpSalesDate.setDateFormatString("dd/MM/yyyy");
        dtpSalesDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtpSalesDateKeyPressed(evt);
            }
        });

        lblSalesDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSalesDate.setForeground(new java.awt.Color(204, 0, 0));
        lblSalesDate.setText("Sales Date");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Payment Type"));

        grpSalesType.add(radCash);
        radCash.setText("Cash");
        radCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCashActionPerformed(evt);
            }
        });

        grpSalesType.add(radCredit);
        radCredit.setText("Credit");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radCash)
                .addGap(30, 30, 30)
                .addComponent(radCredit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radCash)
                    .addComponent(radCredit))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lblAvailableQty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAvailableQty.setForeground(new java.awt.Color(0, 102, 0));
        lblAvailableQty.setText("00");

        lblDisplayName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDisplayName.setText("Customer Address/GSTIN");
        lblDisplayName.setMaximumSize(new java.awt.Dimension(139, 21));
        lblDisplayName.setMinimumSize(new java.awt.Dimension(139, 21));
        lblDisplayName.setPreferredSize(new java.awt.Dimension(139, 21));

        txtDisplayName.setColumns(20);
        txtDisplayName.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        txtDisplayName.setRows(2);
        txtDisplayName.setTabSize(2);
        txtDisplayName.setMaximumSize(new java.awt.Dimension(144, 32));
        txtDisplayName.setMinimumSize(new java.awt.Dimension(144, 32));
        txtDisplayName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDisplayNameKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(txtDisplayName);

        lblHSNCode1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblHSNCode1.setText("Vehicle Details:");

        txtVehicleDetails.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtVehicleDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVehicleDetailsKeyReleased(evt);
            }
        });

        lblDisplayName1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDisplayName1.setText("Delivery Address");
        lblDisplayName1.setMaximumSize(new java.awt.Dimension(140, 21));
        lblDisplayName1.setMinimumSize(new java.awt.Dimension(140, 21));
        lblDisplayName1.setPreferredSize(new java.awt.Dimension(140, 21));

        txtShippingAddress.setColumns(20);
        txtShippingAddress.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        txtShippingAddress.setRows(2);
        txtShippingAddress.setTabSize(2);
        txtShippingAddress.setMaximumSize(new java.awt.Dimension(144, 32));
        txtShippingAddress.setMinimumSize(new java.awt.Dimension(144, 32));
        txtShippingAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtShippingAddressKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtShippingAddressKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(txtShippingAddress);

        chkSameAsAbove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSameAsAboveActionPerformed(evt);
            }
        });
        chkSameAsAbove.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkSameAsAboveKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chkSameAsAboveKeyReleased(evt);
            }
        });

        lblUnitPrice4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUnitPrice4.setText("MRP");

        txtMrp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMrp.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtMrp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMrpActionPerformed(evt);
            }
        });

        lblWPIndicator.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblWPIndicator.setForeground(new java.awt.Color(0, 153, 153));
        lblWPIndicator.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtBillNumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtBillNumber.setForeground(new java.awt.Color(51, 153, 255));
        txtBillNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBillNumberKeyReleased(evt);
            }
        });

        chkWholesale.setText("Whole Sale ?");

        lblGst14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGst14.setText("GST @ 5.0%");

        lblGst12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGst12.setText("GST @ 12.0%");

        txtGst5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGst5.setForeground(new java.awt.Color(0, 153, 153));
        txtGst5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGst5.setText("$$");
        txtGst5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtGst5.setRequestFocusEnabled(false);

        txtGst12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGst12.setForeground(new java.awt.Color(0, 153, 153));
        txtGst12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGst12.setText("$$");
        txtGst12.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtGst12.setRequestFocusEnabled(false);

        lblGst13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGst13.setText("GST @ 18.0%");

        txtGst18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGst18.setForeground(new java.awt.Color(0, 153, 153));
        txtGst18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGst18.setText("$$");
        txtGst18.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtGst18.setRequestFocusEnabled(false);

        lblGst28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGst28.setText("GST @ 28.0%");

        txtGst28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGst28.setForeground(new java.awt.Color(0, 153, 153));
        txtGst28.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGst28.setText("$$");
        txtGst28.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtGst28.setRequestFocusEnabled(false);

        lblSGst.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst.setText("SGST");

        lblCGst.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCGst.setText("CGST");

        lblTotalGst.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTotalGst.setText("Total GST");

        txtTotalGst.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalGst.setForeground(new java.awt.Color(0, 153, 153));
        txtTotalGst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalGst.setText("$$");
        txtTotalGst.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTotalGst.setRequestFocusEnabled(false);

        txtCGst.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCGst.setForeground(new java.awt.Color(0, 153, 153));
        txtCGst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCGst.setText("$$");
        txtCGst.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCGst.setRequestFocusEnabled(false);

        txtSGst.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSGst.setForeground(new java.awt.Color(0, 153, 153));
        txtSGst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSGst.setText("$$");
        txtSGst.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtSGst.setRequestFocusEnabled(false);

        lblCess.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCess.setText("Cess Amt");

        txtCess.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCess.setForeground(new java.awt.Color(0, 153, 153));
        txtCess.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCess.setText("$$");
        txtCess.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCess.setRequestFocusEnabled(false);

        lblItemCount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblItemCount.setForeground(new java.awt.Color(51, 0, 255));
        lblItemCount.setText("#Count :");

        lblGst17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGst17.setForeground(new java.awt.Color(0, 153, 51));
        lblGst17.setText("Price-");

        lblCustomerPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCustomerPrice.setForeground(new java.awt.Color(0, 153, 51));
        lblCustomerPrice.setText("Price");

        lblGst16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGst16.setForeground(new java.awt.Color(204, 102, 0));
        lblGst16.setText(",L Price-");

        lblLandingPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLandingPrice.setForeground(new java.awt.Color(204, 102, 0));
        lblLandingPrice.setText("L Price");

        lblCPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCPrice.setForeground(new java.awt.Color(0, 0, 255));
        lblCPrice.setText(",C Price-");

        lblContractorPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblContractorPrice.setForeground(new java.awt.Color(0, 51, 204));
        lblContractorPrice.setText("C Price");

        lblSGst4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSGst4.setText("Total MRP");

        lblSGst2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSGst2.setText("Discount Amount");

        lblSGst1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSGst1.setText("Total Bill Amount");

        txtTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalAmount.setForeground(new java.awt.Color(0, 153, 51));
        txtTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalAmount.setText("$$");
        txtTotalAmount.setAutoscrolls(false);
        txtTotalAmount.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTotalAmount.setMaximumSize(new java.awt.Dimension(21, 6));
        txtTotalAmount.setRequestFocusEnabled(false);

        txtDiscountAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiscountAmount.setForeground(new java.awt.Color(153, 153, 0));
        txtDiscountAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscountAmount.setText("$$");
        txtDiscountAmount.setAutoscrolls(false);
        txtDiscountAmount.setMaximumSize(new java.awt.Dimension(6, 21));
        txtDiscountAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountAmountActionPerformed(evt);
            }
        });
        txtDiscountAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscountAmountKeyReleased(evt);
            }
        });

        txtTotalMrp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalMrp.setForeground(new java.awt.Color(255, 102, 102));
        txtTotalMrp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalMrp.setText("$$");
        txtTotalMrp.setAutoscrolls(false);
        txtTotalMrp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTotalMrp.setMaximumSize(new java.awt.Dimension(21, 6));
        txtTotalMrp.setRequestFocusEnabled(false);

        lblRoundOff.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRoundOff.setForeground(new java.awt.Color(0, 102, 102));
        lblRoundOff.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRoundOff.setText("Round Off:");

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus.png"))); // NOI18N
        btnSave.setMnemonic('S');
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAddMore.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAddMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus.png"))); // NOI18N
        btnAddMore.setMnemonic('S');
        btnAddMore.setText("More");
        btnAddMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMoreActionPerformed(evt);
            }
        });

        btnPrintPreview.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrintPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print-preview.png"))); // NOI18N
        btnPrintPreview.setMnemonic('w');
        btnPrintPreview.setText("Preview");
        btnPrintPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintPreviewActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        btnPrint.setMnemonic('P');
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btnSearch.setMnemonic('e');
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnPrevious.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/previous.png"))); // NOI18N
        btnPrevious.setMnemonic('o');
        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        btnNext.setMnemonic('x');
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnViewAll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnViewAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/viewall.png"))); // NOI18N
        btnViewAll.setMnemonic('V');
        btnViewAll.setText("View All");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        btnReset.setMnemonic('R');
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnCancel.setMnemonic('C');
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        chkPayBalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkPayBalance.setForeground(new java.awt.Color(0, 51, 102));
        chkPayBalance.setText("Pay");
        chkPayBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chkPayBalanceMouseReleased(evt);
            }
        });
        chkPayBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chkPayBalanceKeyReleased(evt);
            }
        });

        lblSGst7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst7.setForeground(new java.awt.Color(0, 51, 102));
        lblSGst7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSGst7.setText("Delivery Charge");

        lblSGst5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst5.setForeground(new java.awt.Color(153, 153, 153));
        lblSGst5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSGst5.setText("Paying Amount");

        lblSGst6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst6.setForeground(new java.awt.Color(204, 0, 51));
        lblSGst6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSGst6.setText("Balance Amount");

        lblSGst3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSGst3.setForeground(new java.awt.Color(153, 153, 153));
        lblSGst3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSGst3.setText("Bill Total");

        txtAmountPayable.setEditable(false);
        txtAmountPayable.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        txtAmountPayable.setForeground(new java.awt.Color(102, 102, 255));
        txtAmountPayable.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAmountPayable.setText("$$");
        txtAmountPayable.setOpaque(false);
        txtAmountPayable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtAmountPayablePropertyChange(evt);
            }
        });
        txtAmountPayable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAmountPayableKeyReleased(evt);
            }
        });

        txtPreviousBalance.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPreviousBalance.setForeground(new java.awt.Color(102, 102, 102));
        txtPreviousBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPreviousBalance.setText("$$");
        txtPreviousBalance.setAutoscrolls(false);
        txtPreviousBalance.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPreviousBalance.setMaximumSize(new java.awt.Dimension(21, 6));

        txtPayingAmount.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPayingAmount.setForeground(new java.awt.Color(51, 153, 255));
        txtPayingAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPayingAmount.setText("$$");
        txtPayingAmount.setAutoscrolls(false);
        txtPayingAmount.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPayingAmount.setMaximumSize(new java.awt.Dimension(21, 6));
        txtPayingAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayingAmountKeyReleased(evt);
            }
        });

        txtBalanceAmount.setEditable(false);
        txtBalanceAmount.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtBalanceAmount.setForeground(new java.awt.Color(102, 102, 102));
        txtBalanceAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBalanceAmount.setText("$$");
        txtBalanceAmount.setAutoscrolls(false);
        txtBalanceAmount.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtBalanceAmount.setMaximumSize(new java.awt.Dimension(21, 6));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkPayBalance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRoundOff, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblGst17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lblCustomerPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblGst16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblLandingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblCPrice)
                        .addGap(118, 118, 118)
                        .addComponent(lblContractorPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddMore, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrintPreview)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblGst14, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtGst5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(lblSGst, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtSGst, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137)
                                .addComponent(lblCess, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtCess, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblItemCount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblGst12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtGst12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(lblCGst, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtCGst, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblGst13, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtGst18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(lblTotalGst, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtTotalGst, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblGst28, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtGst28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(224, 224, 224)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSGst4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSGst2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSGst1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalMrp, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblSGst5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSGst6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPayingAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBalanceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSGst7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPreviousBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(lblSGst3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtAmountPayable, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblSGst4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(lblSGst2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(lblSGst1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtTotalMrp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(chkPayBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblRoundOff, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAddMore, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnPrintPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSGst3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAmountPayable, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPreviousBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSGst7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblSGst5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(lblSGst6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtPayingAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBalanceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGst14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGst5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSGst, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSGst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCess, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtCess, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblItemCount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGst12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGst12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCGst, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCGst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGst13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGst18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalGst, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalGst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGst28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGst28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGst17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCustomerPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGst16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLandingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblContractorPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPPIndicator.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPPIndicator.setForeground(new java.awt.Color(0, 51, 255));
        lblPPIndicator.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pnlSupplierLayout = new javax.swing.GroupLayout(pnlSupplier);
        pnlSupplier.setLayout(pnlSupplierLayout);
        pnlSupplierLayout.setHorizontalGroup(
            pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierLayout.createSequentialGroup()
                .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtBillNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSalesDate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSupplierLayout.createSequentialGroup()
                                .addComponent(dtpSalesDate, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(chkWholesale, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDisplayName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSupplierLayout.createSequentialGroup()
                                .addComponent(chkSameAsAbove, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lblDisplayName1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHSNCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVehicleDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlSupplierLayout.createSequentialGroup()
                                .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                                        .addComponent(lblItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(355, 355, 355)
                                        .addComponent(lblHSNCode, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                                        .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(138, 138, 138)
                                        .addComponent(txtHSNCode, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(603, 603, 603)
                                .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                                        .addComponent(txtMrp, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtGstPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtGstAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(cmdReset))
                                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                                        .addComponent(lblUnitPrice4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblUnitPrice2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addComponent(lblUnitPrice3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUnitPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblWPIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlSupplierLayout.createSequentialGroup()
                                                .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(lblAvailableQty, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblPPIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(spnSalesItems, javax.swing.GroupLayout.PREFERRED_SIZE, 1860, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pnlSupplierLayout.setVerticalGroup(
            pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addComponent(lblCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmbCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(txtBillNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSalesDate)
                        .addGap(4, 4, 4)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtpSalesDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkWholesale)))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblDisplayName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkSameAsAbove)
                            .addComponent(lblDisplayName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblHSNCode1)
                        .addGap(6, 6, 6)
                        .addComponent(txtVehicleDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblItemName)
                            .addComponent(lblHSNCode))
                        .addGap(6, 6, 6)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHSNCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlSupplierLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblWPIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPPIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUnitPrice4)
                            .addComponent(lblUnitPrice)
                            .addComponent(lblUnitPrice2)
                            .addComponent(lblUnitPrice3)
                            .addComponent(lblUnitPrice1)
                            .addComponent(lblQuantity)
                            .addComponent(lblAvailableQty))
                        .addGap(5, 5, 5)
                        .addGroup(pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGstPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGstAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdReset))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnSalesItems, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pnlSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBillNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBillNumberKeyReleased
        // TODO add your handling code here:
        String numberPart = "";
        if(txtBillNumber.getText().contains("D-")){
            numberPart = txtBillNumber.getText().substring(txtBillNumber.getText().indexOf("D-")+2);
            System.out.println("Bill Number Part"+numberPart);
            billSeqNumber = Long.parseLong(numberPart);
        }else if(txtBillNumber.getText().contains("E-")){
            numberPart = txtBillNumber.getText().substring(txtBillNumber.getText().indexOf("E-")+2);
            System.out.println("Bill Number Part"+numberPart);
            billSeqNumber = Long.parseLong(numberPart);
        }else{
            billSeqNumber = Long.parseLong(txtBillNumber.getText());
        }
    }//GEN-LAST:event_txtBillNumberKeyReleased

    private void txtMrpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMrpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMrpActionPerformed

    private void chkSameAsAboveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkSameAsAboveKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSameAsAboveKeyReleased

    private void chkSameAsAboveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkSameAsAboveKeyPressed
        // TODO add your handling code here:
        if((evt.getKeyCode()== KeyEvent.VK_ENTER || evt.getKeyCode()== KeyEvent.VK_TAB)){
            txtShippingAddress.requestFocus();
        }
    }//GEN-LAST:event_chkSameAsAboveKeyPressed

    private void chkSameAsAboveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSameAsAboveActionPerformed
        // TODO add your handling code here:
        if(chkSameAsAbove.isSelected()){
            txtShippingAddress.setText(txtDisplayName.getText());
            txtVehicleDetails.requestFocus();
        }else{
            txtShippingAddress.setText("");
        }
    }//GEN-LAST:event_chkSameAsAboveActionPerformed

    private void txtShippingAddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShippingAddressKeyReleased
        // TODO add your handling code here:
        matchShippingAndBillingAddresses();
    }//GEN-LAST:event_txtShippingAddressKeyReleased

    private void txtShippingAddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShippingAddressKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_TAB){
            txtVehicleDetails.requestFocus();
        }
    }//GEN-LAST:event_txtShippingAddressKeyPressed

    private void txtVehicleDetailsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehicleDetailsKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            txtItemName.requestFocus();
        }
    }//GEN-LAST:event_txtVehicleDetailsKeyReleased

    private void txtDisplayNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisplayNameKeyReleased
        // TODO add your handling code here:
        matchShippingAndBillingAddresses();
        if(evt.getKeyCode()== KeyEvent.VK_TAB){
            chkSameAsAbove.requestFocus();
        }
    }//GEN-LAST:event_txtDisplayNameKeyReleased

    private void radCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radCashActionPerformed

    private void dtpSalesDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtpSalesDateKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_TAB ){
            txtDisplayName.requestFocus();
        }/*else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtItemName.requestFocus();
        }*/
    }//GEN-LAST:event_dtpSalesDateKeyPressed

    private void cmbCustomerNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCustomerNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCustomerNameKeyReleased

    private void cmbCustomerNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCustomerNameKeyPressed
        // TODO add your handling code here:
        if((evt.getKeyCode()== KeyEvent.VK_ENTER || evt.getKeyCode()== KeyEvent.VK_TAB) && null!=txtItemName.getText()){
            String selectedCustomer = ((Customer) cmbCustomerName.getSelectedItem()).getCustomerName();
            if(selectedCustomer.equalsIgnoreCase("new") || selectedCustomer.equalsIgnoreCase("default")){
                txtDisplayName.requestFocus();
            }else {
                chkSameAsAbove.requestFocus();
            }
        }
    }//GEN-LAST:event_cmbCustomerNameKeyPressed

    private void cmbCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCustomerNameActionPerformed
        // TODO add your handling code here:
        try{
            customerSelectedAction();
            chkSameAsAbove.setSelected(false);
            txtShippingAddress.setText("");
        }catch(Exception e){
            System.out.println("--"+e.getMessage());
        }
    }//GEN-LAST:event_cmbCustomerNameActionPerformed

    private void txtGstAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGstAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGstAmountActionPerformed

    private void txtGstPercentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGstPercentageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGstPercentageActionPerformed

    private void txtSellingPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSellingPriceKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtQuantity.requestFocus();
        }if(evt.getKeyCode() == KeyEvent.VK_F1 && !("".equals(lblWPIndicator.getText())) && null!=lblWPIndicator.getText()){
            txtSellingPrice.setText(lblWPIndicator.getText());
            txtQuantity.requestFocus();
        }
    }//GEN-LAST:event_txtSellingPriceKeyReleased

    private void txtSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSellingPriceActionPerformed
        // TODO add your handling code here:
        txtQuantity.requestFocus();
    }//GEN-LAST:event_txtSellingPriceActionPerformed

    private void cmdResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdResetActionPerformed
        // TODO add your handling code here:
        clearItemFields();
    }//GEN-LAST:event_cmdResetActionPerformed

    private void cmbCustomerTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCustomerTypeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER){
            cmbCustomerName.requestFocus();
        }/*else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtItemName.requestFocus();
        }*/
    }//GEN-LAST:event_cmbCustomerTypeKeyPressed

    private void cmbCustomerTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCustomerTypeActionPerformed
        // TODO add your handling code here:
        clearItemFields();
        try{
            gSalesPercentage = ((PricingTier)(cmbCustomerType.getSelectedItem())).getPricingPercentage();
            System.out.println("-----"+gSalesPercentage + " for "+ ((PricingTier)(cmbCustomerType.getSelectedItem())).getCustomerTypeName());
            //====06/18 : Brought here from New button action
            EstimateDAO billGenDAO =  new EstimateDAO();
            //long openBills = 0;
            billType = ((PricingTier)(cmbCustomerType.getSelectedItem())).getCustomerTypeName();
            /*if(billGenDAO.doesOpenBillExists(txtBillNumber.getText())){
                billGenDAO.deleteFromOpenBillTable(txtBillNumber.getText());
            }*/
            if(!(btnSave.getText().equalsIgnoreCase("update"))){
                billSeqNumber = billGenDAO.generateBillNumber(billType);
                //openBills = billGenDAO.getOpenBillCount(billType);
                if(billType.equalsIgnoreCase("B2B")){
                    //long b2bBillNumber = billSeqNumber + openBills;
                    txtBillNumber.setText("E-"+billSeqNumber);
                    chkWholesale.setVisible(false);

                    //txtDisplayName.requestFocus();
                }else{
                    txtBillNumber.setText("D-"+billSeqNumber);
                    chkWholesale.setVisible(true);
                }
                if(billType.equalsIgnoreCase("contractor")){
                    //radCredit.setSelected(true);
                    //txtDisplayName.requestFocus();
                }
                /*if(!billGenDAO.doesOpenBillExists(txtBillNumber.getText())){
                    billGenDAO.insertIntoOpenBillTable(txtBillNumber.getText(), billSeqNumber, billType);
                }*/
            }

            //#######
        }catch(Exception e){
            System.out.println("-- "+e.getMessage());
        }
    }//GEN-LAST:event_cmbCustomerTypeActionPerformed

    private void lblCustomerTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCustomerTypeMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() > 1){
            this.dispose();
            SalesWindow salesWindow = new SalesWindow();
            salesWindow.setBounds(40, 40, 980, 680);
            salesWindow.setVisible(true);
        }
    }//GEN-LAST:event_lblCustomerTypeMouseClicked

    private void txtUnitPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnitPriceKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== evt.VK_ENTER || evt.getKeyCode()== evt.VK_TAB){
            txtSellingPrice.requestFocus();
        } else{
            calculatePricingLogic();
        }
    }//GEN-LAST:event_txtUnitPriceKeyReleased

    private void txtUnitPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitPriceActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
        if(null == txtQuantity.getText()|| "".equalsIgnoreCase(txtQuantity.getText().trim())){
            JOptionPane.showMessageDialog(null, "Please enter the quantity");
            txtQuantity.requestFocus();

        }
        else if(validateForm()){
            txtQuantity.requestFocus();
            renderSalesDataIntoTable(stock);
            clearItemFields();
            txtItemName.requestFocus();
            System.out.println("-- Line Item # : "+tblSalesDetails.getRowCount());
        }
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void spnSalesItemsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnSalesItemsKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_END){
            //tblSalesDetails.getModel().getRowCount()
            tblSalesDetails.getModel().getValueAt(100, 0).notify();
        }
    }//GEN-LAST:event_spnSalesItemsKeyPressed

    private void tblSalesDetailsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSalesDetailsKeyReleased
        // TODO add your handling code here:
        System.out.println("---Price");
        //if(tblSalesDetails.getEditingColumn()==3){
            System.out.println("---Editing Price");
            updatePricesWithinTable();
    }//GEN-LAST:event_tblSalesDetailsKeyReleased

    private void tblSalesDetailsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSalesDetailsKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_HOME && tblSalesDetails.getRowCount()>0){
            mnuSalesDetails.removeAll();
            mitEdit = new JMenuItem("Edit");
            mitEdit.addActionListener(this);
            mnuSalesDetails.add(mitEdit);

        }
    }//GEN-LAST:event_tblSalesDetailsKeyPressed

    private void txtItemNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameKeyTyped

    private void txtItemNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER && null!=txtItemName.getText() && !("".equalsIgnoreCase(txtItemName.getText()))){
            populateItemDetails();
        }
    }//GEN-LAST:event_txtItemNameKeyReleased

    private void txtItemNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameKeyPressed

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
        if(null!=txtItemName.getText() && !("".equalsIgnoreCase(txtItemName.getText())))
        populateItemDetails();
    }//GEN-LAST:event_txtItemNameActionPerformed

    private void txtItemNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtItemNameMouseClicked
        /* if(evt.getClickCount()==2){
            populateItemDetails();
        }*/
    }//GEN-LAST:event_txtItemNameMouseClicked

    private void txtItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameFocusLost

    private void txtHSNCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHSNCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHSNCodeActionPerformed

    private void txtDiscountAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscountAmountActionPerformed

    private void txtDiscountAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountAmountKeyReleased
        // TODO add your handling code here:
        try {
            txtAmountPayable.setText(GenericUtils.roundDouble(Double.parseDouble(txtMrp.getText()) - Double.parseDouble(txtDiscountAmount.getText()), 2)+"");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDiscountAmountKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(btnSave.getText().equalsIgnoreCase("new")){
            saleIdEdit = 0;
            PricingTierDAO pricingTierDAO = new PricingTierDAO();
            renderDataIntoDropDown(pricingTierDAO.retrieveAll());

            CustomerDAO customerDAO = new CustomerDAO();
            renderCustomerDataIntoDropDown(customerDAO.retrieveAll());

            //Moved to Bill type Drop Down for different series of billing
            /*SalesDAO billGenDAO =  new SalesDAO();
            txtBillNumber.setText(billGenDAO.generateBillNumber()+"");*/
            //((DefaultTableModel)tblSalesDetails.getModel()).setNumRows(0);
            btnSave.setText("Save");
            btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png")));
            enableFormElements(true);
            //if(null==dtpSalesDate.getDate()){
                dtpSalesDate.setDate(new Date());
            //}
            SuggestionUtility.populateItemNames(this, txtItemName);
            //txtItemName.requestFocus();
            //txtDisplayName.requestFocus();
            //cmbCustomerName.requestFocus();
            cmbCustomerType.requestFocus();
            txtShippingAddress.setVisible(true);
            txtVehicleDetails.setVisible(true);
            chkSameAsAbove.setEnabled(true);

        }else if(btnSave.getText().equalsIgnoreCase("save")){
            //Implement Save Logic
            EstimateDAO estimateDAO = new EstimateDAO();
            if(validateSalesDetails()){
                if(estimateDAO.doesSalesBillExist(txtBillNumber.getText())){
                    JOptionPane.showMessageDialog(this, "The bill number already exists. Please try saving with next bill number.");
                    txtBillNumber.requestFocus();
                }else{
                    saveSalesForm();
                    initializeForm();
                }
            }
            /*this.dispose();
            SalesWindow salesWindow = new SalesWindow();
            salesWindow.setBounds(100, 60, 960, 590);
            salesWindow.setVisible(true);*/

        }else if(btnSave.getText().equalsIgnoreCase("update")){

            updateSalesAfterReturn(returnList);
            initializeForm();
            /*this.dispose();
            SalesWindow salesWindow = new SalesWindow();
            salesWindow.setBounds(100, 60, 960, 590);
            salesWindow.setVisible(true);*/

        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMoreActionPerformed
        // TODO add your handling code here:
        enableFormElements(true);
        SuggestionUtility.populateItemNames(this, txtItemName);
        PricingTierDAO pricingTierDAO = new PricingTierDAO();
        renderDataIntoDropDown(pricingTierDAO.retrieveAll());
        txtItemName.requestFocus();
    }//GEN-LAST:event_btnAddMoreActionPerformed

    private void btnPrintPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintPreviewActionPerformed
        // TODO add your handling code here:
        ConnectionManager cm = new ConnectionManager();
        Connection con = cm.getConnection();

        String reportString="";
        if(txtBillNumber.getText().contains("E-") || chkWholesale.isSelected()){
            reportString="src\\com\\essar\\reports\\b2e.jrxml";
        }else{
            reportString="src\\com\\essar\\reports\\b2d.jrxml";
        }
        JasperReport jr = null;
        HashMap map = new HashMap();
        map.put("bill_no",txtBillNumber.getText());
        try {
            jr = JasperCompileManager.compileReport(reportString);
        } catch (JRException ex) {
            Logger.getLogger(SalesWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jp = null;
        try {
            jp = JasperFillManager.fillReport(jr, map,con);

            con.close();
        } catch (JRException ex) {
            Logger.getLogger(StockEntry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SalesWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JasperViewer.viewReport(jp);
        JasperViewer jv = new JasperViewer(jp, false);
        jv.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt){
                //JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Closed","Why?", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Jasper Window has been closed");
            }
        });
        jv.setVisible(true);
    }//GEN-LAST:event_btnPrintPreviewActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        ConnectionManager cm = new ConnectionManager();
        Connection con = cm.getConnection();
        //String reportString="C:\\Users\\rahumathulla\\Documents\\NetBeansProjects\\shopaid\\src\\com\\essar\\reports\\sales_print_1.jrxml";
        //String reportString="C:\\Users\\rahumathulla\\AppData\\Local\\shopaid\\src\\com\\essar\\reports\\sales_print.jrxml";
        //String reportString="src\\com\\essar\\reports\\sales_print.jrxml";
        //String reportString="src\\com\\essar\\reports\\first_choice.jrxml";
        //String reportString="src\\com\\essar\\reports\\test.jrxml";
        String reportString="";
        String desiredPrinter = "";
        if(txtBillNumber.getText().contains("E-") || chkWholesale.isSelected()){
            reportString="src\\com\\essar\\reports\\b2e.jrxml";
            desiredPrinter = b2b_printer;
        }else{
            reportString="src\\com\\essar\\reports\\b2d.jrxml";
            desiredPrinter = b2c_printer;
        }
        JasperReport jr = null;
        HashMap map = new HashMap();
        map.put("bill_no",txtBillNumber.getText());
        try {
            jr = JasperCompileManager.compileReport(reportString);
        } catch (JRException ex) {
            Logger.getLogger(SalesWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jp = null;
        try {
            jp = JasperFillManager.fillReport(jr, map,con);
            //JasperViewer jv = new JasperViewer( jp, false );
            //jv.viewReport( jp, false );
            //JasperViewer.viewReport(jp, false);
            //jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            //jv.setTitle("Sales Bill");
            con.close();
        } catch (JRException ex) {
            Logger.getLogger(StockEntry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SalesWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JasperViewer.viewReport(jp);
        /*JasperViewer jv = new JasperViewer(jp, false);
        jv.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt){
                //JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Closed","Why?", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Jasper Window has been closed");
            }s
        });
        jv.setVisible(true);*/
        printReportToPrinter(jp,desiredPrinter);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        /*try{
            String billNumber = JOptionPane.showInputDialog(this, " Please enter the bill number.");
            System.out.println("Bill Number = "+billNumber);
            //if(billNumber.length() < 5 || null == billNumber || !(billNumber.matches("\\d+"))){
                if(null == billNumber || "".equalsIgnoreCase(billNumber.trim())){
                    JOptionPane.showMessageDialog(this, "No Data found!");
                }else{
                    SalesDAO salesDAO = new SalesDAO();
                    fetchSalesDataIntoTable(salesDAO.retrieveByBillNumber(billNumber));
                }
            }catch(Exception e){
                System.out.println("Invalid Bill Number");
            }*/

            DummySalesFind dummySalesFind = new DummySalesFind();
            dummySalesFind.setBounds(60, 60, 630, 360);
            dummySalesFind.setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        try{

            if(currentId-1 >= minId){
                currentId = currentId -1;
                EstimateDAO estimateDAO = new EstimateDAO();
                fetchSalesDataIntoTable(estimateDAO.retrieveBySalesId(currentId));
            }else{
                JOptionPane.showMessageDialog(null, "No more record before.");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Reached at the beginning.");
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        try{
            if(currentId+1 <= maxId){
                currentId = currentId +1;
                EstimateDAO estimateDAO = new EstimateDAO();
                fetchSalesDataIntoTable(estimateDAO.retrieveBySalesId(currentId));
            }else{
                JOptionPane.showMessageDialog(null, "No more record after.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Reached at the end.");
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        // TODO add your handling code here:
        this.dispose();
        QueryStrings.viewSalesSql =  "SELECT s.sales_id"
        + ", cust.customer_name"
        + ", s.customer_name"
        + ", s.bill_number"
        + ", SUM(d.quantity)"
        + ", s.bill_amount"
        + ", s.cess"
        + ", s.discount"
        + ", s.payable_amount"
        + ", s.sales_date"
        + ", s.status  "
        + " from dsales s "
        + " JOIN customer cust ON s.customer_id = cust.customer_id"
        + " JOIN dsales_details d ON s.sales_id = d.sales_id "
        + " group by sales_id order by sales_id DESC";
        ViewDummySales salesView =  new ViewDummySales();
        //salesView.setSize(800, 520);
        salesView.setVisible(true);
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        initializeForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        handleClosing();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void chkPayBalanceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkPayBalanceMouseReleased
        // TODO add your handling code here:
        try {
            if(chkPayBalance.isSelected()){
                txtPayingAmount.setText(Double.parseDouble(txtAmountPayable.getText())+Double.parseDouble(txtPreviousBalance.getText())+"");
                txtBalanceAmount.setText(Double.parseDouble(txtAmountPayable.getText())-Double.parseDouble(txtPayingAmount.getText())+"");
            }else{
                txtPayingAmount.setText(Double.parseDouble(txtAmountPayable.getText())+"");
                txtBalanceAmount.setText(Double.parseDouble(txtAmountPayable.getText())-Double.parseDouble(txtPayingAmount.getText())+"");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_chkPayBalanceMouseReleased

    private void chkPayBalanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkPayBalanceKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPayBalanceKeyReleased

    private void txtAmountPayablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtAmountPayablePropertyChange
        // TODO add your handling code here:
        try {
            txtPayingAmount.setText(GenericUtils.roundDouble(Double.parseDouble(txtAmountPayable.getText()), 2)+"");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtAmountPayablePropertyChange

    private void txtAmountPayableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountPayableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountPayableKeyReleased

    private void txtPayingAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayingAmountKeyReleased
        // TODO add your handling code here:
        try {
            txtBalanceAmount.setText(GenericUtils.roundDouble(Double.parseDouble(txtAmountPayable.getText()) - Double.parseDouble(txtPayingAmount.getText()), 2)+"");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPayingAmountKeyReleased

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
            java.util.logging.Logger.getLogger(DummySales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DummySales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DummySales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DummySales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new DummySales().setVisible(true);
            }
        });
    }
    
    public void calculatePricingLogic(){
        try{
            
        
            if(txtGstPercentage.getText()!=null && !("".equalsIgnoreCase(txtGstPercentage.getText()))){
                double sellingPrice =0;
                if(Double.parseDouble(txtGstPercentage.getText()) > 5){
                    sellingPrice = Double.parseDouble(txtUnitPrice.getText()) + 
                            (Double.parseDouble(txtGstPercentage.getText())+1)*0.01 //+1 is for cess addition
                            *Double.parseDouble(txtUnitPrice.getText());
                }else if(Double.parseDouble(txtGstPercentage.getText()) <= 5){
                    sellingPrice = Double.parseDouble(txtUnitPrice.getText()) + 
                            (Double.parseDouble(txtGstPercentage.getText()))*0.01 //Non Cess items <=5% Tax
                            *Double.parseDouble(txtUnitPrice.getText());
                }
                txtSellingPrice.setText(GenericUtils.roundDouble(sellingPrice, 2)+"");
            }
        }catch(Exception e){
            System.out.println("---"+e.getMessage());
        }
    }
    
    public void initializeForm(){
        trackActivity = true;
        lblContractorPrice.setVisible(false);
        lblCPrice.setVisible(false);
        btnAddMore.setVisible(false);
        btnSave.requestFocus();
        txtHSNCode.setText("");
        txtItemName.setText("");
        txtDisplayName.setVisible(false);
        txtShippingAddress.setVisible(false);
        txtVehicleDetails.setVisible(false);
        chkSameAsAbove.setSelected(false);
        chkSameAsAbove.setEnabled(false);
        txtVehicleDetails.setText("");
        lblItemCount.setText("");
        txtTotalMrp.setText("0.0");
        //txtBillNumber.setText("######");
        //txtSupplierRating.setText("");
        txtQuantity.setText("");
        //txtSupplierAddress.setText("");
        txtGst12.setText("0.0");
        txtGst18.setText("0.0");
        txtGst28.setText("0.0");
        txtGst5.setText("0.0");
        txtSGst.setText("0.0");
        txtCGst.setText("0.0");
        txtTotalGst.setText("0.0");
        txtTotalAmount.setText("0.0");
        txtDiscountAmount.setText("0.0");
        txtAmountPayable.setText("0.0");
        txtPayingAmount.setText("0.0");
        txtBalanceAmount.setText("0.0");
        txtPreviousBalance.setText("0.0");
        txtCess.setText("0.0");
        radCash.setSelected(true);
        chkPayBalance.setVisible(false);
        //txtBillNumber.setText("10021");
        
        
        enableFormElements(false);
        
        mnuSalesDetails = new JPopupMenu();
        mitDelete = new JMenuItem("Remove");
        mitDelete.addActionListener(this);        
        mnuSalesDetails.add(mitDelete);
                   
        
        
         // sets the popup menu for the table
        tblSalesDetails.setComponentPopupMenu(mnuSalesDetails);      
        tblSalesDetails.addMouseListener(new TableMouseListener(tblSalesDetails));
        
        //!@--09/03/2020 Setting id column width to 0;
        TableColumn idColumn1 = tblSalesDetails.getColumnModel().getColumn(tblSalesDetails.getColumnCount()-1);
        idColumn1.setPreferredWidth(0);
        idColumn1.setMinWidth(0);
        idColumn1.setMaxWidth(0);
        
        TableColumn idColumn2 = tblSalesDetails.getColumnModel().getColumn(tblSalesDetails.getColumnCount()-2);
        idColumn2.setPreferredWidth(0);
        idColumn2.setMinWidth(0);
        idColumn2.setMaxWidth(0);
        
        TableColumn itemNameColumn = tblSalesDetails.getColumnModel().getColumn(2);
        itemNameColumn.setPreferredWidth(400);
        itemNameColumn.setMinWidth(400);
        //itemNameColumn.setMaxWidth(400);
        
        TableColumn serialNumberColumn = tblSalesDetails.getColumnModel().getColumn(0);
        serialNumberColumn.setPreferredWidth(50);
        serialNumberColumn.setMaxWidth(50);
        
        TableColumn hsnColumn = tblSalesDetails.getColumnModel().getColumn(1);
        hsnColumn.setPreferredWidth(100);
        hsnColumn.setMaxWidth(100);
        
        EstimateDAO navigationDAO =  new EstimateDAO();
        List<Long> idMaxMin = navigationDAO.getMaxAndMinIds();
        minId = idMaxMin.get(0);
        maxId = idMaxMin.get(1);
        //currentId = maxId;
        /*navigationDAO =  new EstimateDAO();
        if(maxId >0){
            fetchSalesDataIntoTable(navigationDAO.retrieveBySalesId(maxId));
            currentId = maxId;
        }*/
        
        
        btnSave.setText("New");
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus.png")));
        
        tblSalesDetails.removeAll();
        tblSalesDetails.repaint();
        DefaultTableModel model = (DefaultTableModel) tblSalesDetails.getModel();
        model.setRowCount(0);
        
        //Techpoint
        //simulateNewButtonClick();
   
    }
    
    public void enableFormElements(boolean flag){
        txtHSNCode.setEnabled(flag);
        txtItemName.setEnabled(flag);
        txtMrp.setEnabled(flag);
        txtQuantity.setEnabled(flag);
        txtUnitPrice.setEnabled(flag);
        txtSellingPrice.setEnabled(flag);
        txtGstPercentage.setEnabled(flag);
        txtGstAmount.setEnabled(flag);
        dtpSalesDate.setEnabled(flag);
        //txtVehicleDetails.setEnabled(flag);
        //txtShippingAddress.setEnabled(flag);
    }
    public boolean validateForm(){
        //btnSave.setText("Add");
        //btnSave.requestFocus();
        if(gStockAvailable < Double.parseDouble(txtQuantity.getText())){
            int choice = JOptionPane.showConfirmDialog(null, "This item is out of stock, are you sure to continue with Billing?");
            System.out.println("choice--"+choice);
            if(choice==0)
                return true;
            else 
                return false;
        }
        if(cmbCustomerType.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Please choose a customer type");
            cmbCustomerType.requestFocus();
            return false;
        }
        
        if(null == txtItemName.getText()){
            JOptionPane.showMessageDialog(null, "Please give the item name.");
            txtItemName.requestFocus();
            return false;
        }
        if(null == txtHSNCode.getText() || !(txtHSNCode.getText().matches("\\w+"))){
            JOptionPane.showMessageDialog(null, "HSN Code  "+txtHSNCode.getText() +" is not valid.");
            txtHSNCode.requestFocus();
            return false;
        }
        if(null == txtQuantity.getText() || !(txtQuantity.getText().matches("^\\d*\\.\\d+|\\d+|\\d+\\.\\d*$"))){
            JOptionPane.showMessageDialog(null, "Quantity "+txtQuantity.getText() +" is not valid.");
            txtQuantity.requestFocus();
            return false;
        }      
     
        //txtProductCategoryName.setText("");
        return true;
    }
    
    public boolean validateSalesDetails(){
        //btnSave.setText("Add");
        //btnSave.requestFocus();
        
        if(cmbCustomerType.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Please choose a customer type");
            return false;
        }
        /*if(null == txtHSNCode.getText() || !(txtHSNCode.getText().matches("\\w+"))){
            JOptionPane.showMessageDialog(null, "HSN Code  "+txtHSNCode.getText() +" is not valid.");
            return false;
        }*/
        /*if(null == txtQuantity.getText() || !(txtQuantity.getText().matches("\\d+"))){
            JOptionPane.showMessageDialog(null, "Quantity "+txtQuantity.getText() +" is not valid.");
            return false;
        }*/
        if(tblSalesDetails.getRowCount()<1){
            JOptionPane.showMessageDialog(null, "Please enter the item details.");
            txtItemName.requestFocus();
            return false;
        }
     
        //txtProductCategoryName.setText("");
        return true;
    }
    
        
    public void saveSalesForm(){

        SalesDAO billGenDAO =  new SalesDAO();
        Sales sales = new Sales();
        sales.setBillNumber(txtBillNumber.getText());
        sales.setSalesDate(dtpSalesDate.getDate());
        Customer customer = (Customer)cmbCustomerName.getSelectedItem();
        sales.setCustomerId(customer.getCustomerId());
        System.out.println("----"+sales.getBillNumber());
        sales.setBillAmount(Double.parseDouble(txtTotalAmount.getText()));
        sales.setDiscount(Double.parseDouble(txtDiscountAmount.getText()));
        sales.setPayableAmount(Double.parseDouble(txtAmountPayable.getText()));
        sales.setTotalGst(Double.parseDouble(txtTotalGst.getText()));
        sales.setSgst(Double.parseDouble(txtSGst.getText()));
        sales.setCgst(Double.parseDouble(txtCGst.getText()));
        sales.setGstAt12(Double.parseDouble(txtGst12.getText()));
        sales.setGstAt18(Double.parseDouble(txtGst18.getText()));
        sales.setGstAt28(Double.parseDouble(txtGst28.getText()));
        sales.setGstAt30(Double.parseDouble(txtGst5.getText()));
        sales.setCess(Double.parseDouble(txtCess.getText()));
        sales.setCustomerName(txtDisplayName.getText());
        System.out.println("Cess---"+sales.getCess());
        
        //!@-- Amount in Words change 13/03/2020
        //String sPayableAmount = sales.getPayableAmount()+".";
        String sPayableAmount = Math.round(sales.getBillAmount())+"";
        //String intPart = sPayableAmount.substring(0, sPayableAmount.indexOf("."));
        //System.out.println("--Int Part :: "+intPart);
        sales.setAmountInWords("Rupees "+GenericUtils.numberToWord(Integer.parseInt(sPayableAmount))+" Only");
        sales.setRoundOff(Math.round(sales.getBillAmount())-sales.getBillAmount());
        sales.setTaxableAt5(gTaxableAt5);
        sales.setTaxableAt12(gTaxableAt12);
        sales.setTaxableAt18(gTaxableAt18);
        sales.setTaxableAt0(gTaxableAt0);
        sales.setTaxableAt28(gTaxableAt28);
        sales.setBillType(billType);
        sales.setBillSeqNumber(billSeqNumber);
        sales.setVehicleDetails(txtVehicleDetails.getText());
        sales.setShippingAddress(txtShippingAddress.getText());
        sales.setPreviousBalance(Double.parseDouble(txtPreviousBalance.getText()));
        System.out.println("--Amount in Words :: "+sales.getAmountInWords());
        if(radCash.isSelected())
            sales.setStatus("Paid");
        else
            sales.setStatus("Credit");
        sales.setCreateTS(Calendar.getInstance().getTime());
        sales.setUpdateTS(Calendar.getInstance().getTime());
        
        SalesDetails salesDetails = null;
        List<SalesDetails> salesDetailsList =  new ArrayList<>();
        for(int i=0;i<salesDetailsModel.getRowCount();i++){
            salesDetails = new SalesDetails();
            salesDetails.setHsnCode(salesDetailsModel.getValueAt(i, 1).toString());
            salesDetails.setItemName(salesDetailsModel.getValueAt(i, 2).toString());
            salesDetails.setMrp(Double.parseDouble(salesDetailsModel.getValueAt(i, 3).toString()));
            salesDetails.setUnitPrice(Double.parseDouble(salesDetailsModel.getValueAt(i, 4).toString()));
            salesDetails.setGstPercentage(Double.parseDouble(salesDetailsModel.getValueAt(i, 5).toString()));
            salesDetails.setGstAmount(Double.parseDouble(salesDetailsModel.getValueAt(i, 6).toString()));
            salesDetails.setQuantity(Double.parseDouble(salesDetailsModel.getValueAt(i, 7).toString()));
            salesDetails.setNetAmount(Double.parseDouble(salesDetailsModel.getValueAt(i, 8).toString()));
            salesDetails.setDiscount(0.0);
            salesDetails.setGrossAmount(Double.parseDouble(salesDetailsModel.getValueAt(i, 8).toString()));
            salesDetails.setItemId(Long.parseLong(salesDetailsModel.getValueAt(i, 9).toString()));
            salesDetailsList.add(salesDetails);
        }         
        sales.setSalesDetails(salesDetailsList);
        EstimateDAO estimateDAO =  new EstimateDAO();
        for(int i=0; i<moreMap.size();i++){
            System.out.println("--key :: "+moreMap.keySet());
            System.out.println("--value :: "+moreMap.values());
        }
        estimateDAO.insertIntoDB(sales, trackActivity);
        
    }
    
    public void updateSalesAfterReturn(List<SalesReturn> returnList){

        //EstimateDAO billGenDAO =  new EstimateDAO();
        //!@-- 03/15/2020
        deriveValuesFromSalesInput();
        
        Sales sales = new Sales();
        sales.setBillNumber(txtBillNumber.getText());
        //System.out.println("!@#-----------"+sales.getBillNumber());
        sales.setSalesDate(dtpSalesDate.getDate());
        /*Customer customer = (Customer)cmbCustomerName.getSelectedItem();
        sales.setCustomerId(customer.getCustomerId());*/
        //System.out.println("----"+sales.getBillNumber());
        sales.setBillAmount(Double.parseDouble(txtTotalAmount.getText()));
        sales.setDiscount(Double.parseDouble(txtDiscountAmount.getText()));
        sales.setPayableAmount(Double.parseDouble(txtAmountPayable.getText()));
        sales.setTotalGst(Double.parseDouble(txtTotalGst.getText()));
        sales.setSgst(Double.parseDouble(txtSGst.getText()));
        sales.setCgst(Double.parseDouble(txtCGst.getText()));
        sales.setGstAt12(Double.parseDouble(txtGst12.getText()));
        sales.setGstAt18(Double.parseDouble(txtGst18.getText()));
        sales.setGstAt28(Double.parseDouble(txtGst28.getText()));
        sales.setGstAt30(Double.parseDouble(txtGst5.getText()));
        sales.setCess(Double.parseDouble(txtCess.getText()));
        sales.setCustomerName(txtDisplayName.getText());
        sales.setVehicleDetails(txtVehicleDetails.getText());
        sales.setShippingAddress(txtShippingAddress.getText());
        sales.setPreviousBalance(Double.parseDouble(txtPreviousBalance.getText()));
        sales.setSalesDate(dtpSalesDate.getDate());
        
        //!@-- Amount in Words change 13/03/2020
        String sPayableAmount = Math.round(sales.getPayableAmount())+"";
        //String intPart = sPayableAmount.substring(0, sPayableAmount.indexOf("."));
        //System.out.println("--Int Part :: "+intPart);
        //sales.setAmountInWords("Rupees "+GenericUtils.numberToWord(Integer.parseInt(sPayableAmount))+" Only");
        //sales.setPayableAmount(Math.round(sales.getBillAmount()));
        sales.setRoundOff(Math.round(sales.getBillAmount())-sales.getBillAmount());
        //System.out.println("--Amount in Words :: "+sales.getAmountInWords());
        sales.setTaxableAt5(gTaxableAt5);
        sales.setTaxableAt12(gTaxableAt12);
        sales.setTaxableAt18(gTaxableAt18);
        sales.setTaxableAt0(gTaxableAt0);
        sales.setTaxableAt28(gTaxableAt28);
        //System.out.println("Cess---"+sales.getCess());
        
        
        if(radCash.isSelected())
            sales.setStatus("Paid");
        else
            sales.setStatus("Credit");
        
        //For handling more line items after on top of existing Bill
        SalesDetails salesDetails = null;
        List<SalesDetails> moreItemsList =  new ArrayList<SalesDetails>();
        for(int i=0;i<salesDetailsModel.getRowCount();i++){ //Newly added items logic
            if(salesDetailsModel.getValueAt(i, 10)==null){
                //System.out.println("--!@#"+salesDetailsModel.getValueAt(i, 1).toString());
                salesDetails = new SalesDetails();
                salesDetails.setHsnCode(salesDetailsModel.getValueAt(i, 1).toString());
                salesDetails.setItemName(salesDetailsModel.getValueAt(i, 2).toString());
                salesDetails.setMrp(Double.parseDouble(salesDetailsModel.getValueAt(i, 3).toString()));
                salesDetails.setUnitPrice(Double.parseDouble(salesDetailsModel.getValueAt(i, 4).toString()));
                salesDetails.setGstPercentage(Double.parseDouble(salesDetailsModel.getValueAt(i, 5).toString()));
                salesDetails.setGstAmount(Double.parseDouble(salesDetailsModel.getValueAt(i, 6).toString()));
                salesDetails.setQuantity(Double.parseDouble(salesDetailsModel.getValueAt(i, 7).toString()));
                salesDetails.setNetAmount(Double.parseDouble(salesDetailsModel.getValueAt(i, 8).toString()));
                salesDetails.setDiscount(0.0);
                salesDetails.setGrossAmount(Double.parseDouble(salesDetailsModel.getValueAt(i, 8).toString()));
                salesDetails.setItemId(Long.parseLong(salesDetailsModel.getValueAt(i, 9).toString()));
                moreItemsList.add(salesDetails);
            }
        }       
        sales.setSalesDetails(moreItemsList); 
        EstimateDAO estimateDAO =  new EstimateDAO();
        /*for(int i=0; i<moreMap.size();i++){
            System.out.println("--key :: "+moreMap.keySet());
            System.out.println("--value :: "+moreMap.values());
        }*/
        System.out.println(" moreItemsList :"+moreItemsList.size());
        estimateDAO.updateSalesAfterReturn(sales,returnList, trackActivity);
        
    }
    
       
    public void matchShippingAndBillingAddresses(){
        if(txtShippingAddress.getText().equalsIgnoreCase(txtDisplayName.getText()))
            chkSameAsAbove.setSelected(true);
        else
            chkSameAsAbove.setSelected(false);
    }
    
    public void renderDataIntoDropDown(List<PricingTier> pricingTierList){
        PricingTier pricingTier = null;
        cmbCustomerType.removeAllItems();
        if(pricingTierList!= null){
            for(int i=0; i<pricingTierList.size();i++){
                cmbCustomerType.addItem(pricingTierList.get(i));
            }
            cmbCustomerType.setSelectedIndex(0);
            pricingTier = (PricingTier)cmbCustomerType.getSelectedItem();
            //cmbCustomerType.gets
            //System.out.println("----------"+cmbCustomerType.getSelectedItem()+"::"+pricingTier.getPricingPercentage());
        }
    }
    
    public void renderCustomerDataIntoDropDown(List<Customer> customerList){
        Customer customer = null;
        //cmbCustomerName.removeAllItems();
        if(customerList!= null){
            cmbCustomerName.removeAllItems();
            for(int i=0; i<customerList.size();i++){
                cmbCustomerName.addItem(customerList.get(i));
            }
            cmbCustomerName.setSelectedIndex(0);
            customer = (Customer)cmbCustomerName.getSelectedItem();
            //cmbCustomerType.gets
            System.out.println("----------"+cmbCustomerName.getSelectedItem()+"::"+customer.getCustomerName());
        }
    }
    
    public void updatePricesWithinTable(){
        
        try{
            Double unitPrice = Double.parseDouble(tblSalesDetails.getValueAt(tblSalesDetails.getSelectedRow(), 4).toString());
            Double gstPercentage = Double.parseDouble(tblSalesDetails.getValueAt(tblSalesDetails.getSelectedRow(), 5).toString());
            Double gstAmount = gstPercentage * 0.01 * unitPrice;
            Double quantity = Double.parseDouble(tblSalesDetails.getValueAt(tblSalesDetails.getSelectedRow(), 7).toString());
            Double netAmount = (unitPrice + gstAmount) * quantity;
            
            tblSalesDetails.setValueAt(GenericUtils.roundDouble(gstAmount, 2), tblSalesDetails.getSelectedRow(), 6);
            tblSalesDetails.setValueAt(GenericUtils.roundDouble(netAmount, 2), tblSalesDetails.getSelectedRow(), 8);
            TableColumn nameColumn = tblSalesDetails.getColumnModel().getColumn(2);
            nameColumn.setPreferredWidth(240);

            //double itemAmount = (Double.parseDouble(stock.getSellingPrice())*gSalesPercentage)/100;
            //double itemAmount = (stock.getSellingPrice()*gSalesPercentage)/100;
            //double dBasingPercentage = (1+stock.getGstPercentage()/100);
            //==========Change for Cess on total amount for items more than 5%  GST

            double dBasingPercentage = 0.0;
            if(gstPercentage<=5 || txtBillNumber.getText().contains("E-")){
                dBasingPercentage=(1+gstPercentage/100);
            }else{
                dBasingPercentage=(1.01+gstPercentage/100);
            }
            //############################
            System.out.println("Pricing Percent --"+dBasingPercentage);

        }catch(Exception e){
            e.printStackTrace();
        }
        deriveValuesFromSalesInput();
        lblItemCount.setText("#Count : "+salesDetailsModel.getRowCount());
    }
    
    public void renderSalesDataIntoTable(Stock stock){      
        
        salesDetailsModel = (DefaultTableModel) tblSalesDetails.getModel();      
        
        TableColumn nameColumn = tblSalesDetails.getColumnModel().getColumn(1);
        nameColumn.setPreferredWidth(240);
        
        //double itemAmount = (Double.parseDouble(stock.getSellingPrice())*gSalesPercentage)/100;
        double itemAmount = (stock.getSellingPrice()*gSalesPercentage)/100;
        //double dBasingPercentage = (1+stock.getGstPercentage()/100);
        //==========Change for Cess on total amount for items more than 5%  GST
        
        double dBasingPercentage = 0.0;
        if(stock.getGstPercentage()<=5 || txtBillNumber.getText().contains("E-")){
            dBasingPercentage=(1+stock.getGstPercentage()/100);
        }else{
            dBasingPercentage=(1.01+stock.getGstPercentage()/100);
        }
        //############################
        //System.out.println("Pricing Percent --"+dBasingPercentage);
        double dBasePrice = Double.parseDouble(txtSellingPrice.getText())/dBasingPercentage;
        double dDerivedGst = dBasePrice * stock.getGstPercentage()/100;
        moreMap.put(stock.getItemId(), Double.parseDouble(txtQuantity.getText()));
        System.out.println("!@#-----------ID | "+stock.getItemId());
        gStockAvailable = 0;
        if(salesDetailsModel != null){
        salesDetailsModel.addRow(new Object[]{salesDetailsModel.getRowCount()+1
                , stock.getHsnCode()
                , stock.getItemName()
                , Double.parseDouble(txtMrp.getText())
                , df.format(dBasePrice)
                , stock.getGstPercentage()
                , df.format(dDerivedGst * Double.parseDouble(txtQuantity.getText()))
                , Double.parseDouble(txtQuantity.getText())
                , df.format((dBasePrice+dDerivedGst) * Double.parseDouble(txtQuantity.getText()))
                , stock.getItemId()});
        //model.
                
        } 
        deriveValuesFromSalesInput();
        lblItemCount.setText("#Count : "+salesDetailsModel.getRowCount());
    }
    
    public final void fetchSalesDataIntoTable(Sales sales){
        System.out.println("--Called fetchSalesDataIntoTable"+sales.getSalesDetails().size());
        tblSalesDetails.removeAll();
        tblSalesDetails.repaint();
        DefaultTableModel model = (DefaultTableModel) tblSalesDetails.getModel();
        model.setRowCount(0);
        //initializeForm();
        //enableFormElements(true);
        //09/03/2020
        saleIdEdit = sales.getSalesId();
        currentId = sales.getSalesId();
        btnSave.setText("Update");
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png")));
        btnAddMore.setVisible(true);
        dtpSalesDate.setEnabled(true);
        txtVehicleDetails.setEnabled(true);
        mnuSalesDetails.removeAll();
        mitReturn = new JMenuItem("Return");
        mitReturn.addActionListener(this); 
        mnuSalesDetails.add(mitReturn); 

        returnList =new ArrayList<SalesReturn>();
        
     
        mnuSalesDetails.remove(mitDelete);
        
        System.out.println("---- :: "+ saleIdEdit);
        dtpSalesDate.setDate(sales.getSalesDate());
        //!@--11/03/2020 To Display the customer name
        txtDisplayName.setVisible(true);
        txtShippingAddress.setVisible(true);
        txtVehicleDetails.setVisible(true);
        chkSameAsAbove.setEnabled(true);
        txtDisplayName.setText(sales.getCustomerName());
        txtBillNumber.setText(sales.getBillNumber()+"");
        txtVehicleDetails.setText(sales.getVehicleDetails());
        txtShippingAddress.setText(sales.getShippingAddress());
        txtPreviousBalance.setText(sales.getPreviousBalance()+"");
        matchShippingAndBillingAddresses();
        txtTotalAmount.setText(sales.getBillAmount()+"");
        txtDiscountAmount.setText(sales.getDiscount()+"");
        txtAmountPayable.setText(sales.getPayableAmount()+"");
        txtTotalGst.setText(sales.getTotalGst()+"");
        txtSGst.setText(sales.getSgst()+"");
        txtCGst.setText(sales.getCgst()+"");
        txtGst12.setText(sales.getGstAt12()+"");
        txtGst18.setText(sales.getGstAt18()+"");
        txtGst28.setText(sales.getGstAt28()+"");
        txtGst5.setText(sales.getGstAt30()+"");
        txtCess.setText(sales.getCess()+"");
        if(sales.getStatus().equals("Paid"))
            radCash.setSelected(true);
        else
            radCredit.setSelected(true);
        
        sales.setSgst(Double.parseDouble(txtSGst.getText()));
        sales.setCgst(Double.parseDouble(txtCGst.getText()));
        sales.setGstAt12(Double.parseDouble(txtGst12.getText()));
        sales.setGstAt18(Double.parseDouble(txtGst18.getText()));
        sales.setGstAt28(Double.parseDouble(txtGst28.getText()));
        sales.setGstAt30(Double.parseDouble(txtGst5.getText()));
        sales.setCess(Double.parseDouble(txtCess.getText()));
        List<SalesDetails> salesDetailsList = sales.getSalesDetails();
        salesDetailsModel = (DefaultTableModel) tblSalesDetails.getModel();
        for(int i=0;i<salesDetailsList.size();i++){
            salesDetailsModel.addRow(new Object[]{i+1
                    , salesDetailsList.get(i).getHsnCode()
                    , salesDetailsList.get(i).getItemName()
                    , salesDetailsList.get(i).getMrp()
                    , salesDetailsList.get(i).getUnitPrice()
                    , salesDetailsList.get(i).getGstPercentage()
                    , salesDetailsList.get(i).getGstAmount()                    
                    , salesDetailsList.get(i).getQuantity()
                    , salesDetailsList.get(i).getNetAmount()
                    , salesDetailsList.get(i).getItemId()
                    , salesDetailsList.get(i).getSalesDetailsId()});
        }
        lblItemCount.setText("#Count : "+salesDetailsModel.getRowCount());
        /*if(salesDetailsModel != null){
        salesDetailsModel.addRow(new Object[]{stock.getHsnCode(),stock.getItemName(),stock.getGstPercentage(),df.format(Double.parseDouble(stock.getGstAmount()) * Double.parseDouble(txtQuantity.getText())), itemAmount, Double.parseDouble(txtQuantity.getText()), df.format(itemAmount * Double.parseDouble(txtQuantity.getText())), stock.getItemId()});
        //model.
                
        } */
        //deriveValuesFromSalesInput();
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
                "Are you sure to quit the window ?\n",
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

    
    public void deriveValuesFromSalesInput(){
        lblItemCount.setText("#Count : "+salesDetailsModel.getRowCount());
        gBillAmount = 0;
         gDiscount = 0;
         gPyableAmount = 0;
         gBalanceAmount = 0;
         gTotalGst = 0;
         gSGst = 0;
         gCGst = 0;
         gQuantity = 0;
         gGst12 = 0;
         gGst18 = 0;
         gGst28 = 0;
         gGst5 = 0;
         gCess = 0;

         gTaxableAt5=0;
         gTaxableAt12=0;
         gTaxableAt18=0;
         gTaxableAt0=0;
         gTaxableAt28=0;
         gCessGross = 0;
         
         gTotalMrp = 0;
         
            for(int i=0; i<tblSalesDetails.getRowCount();i++){
                gQuantity += Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                gTotalMrp += Double.parseDouble(tblSalesDetails.getValueAt(i,3).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                gBillAmount += Double.parseDouble(tblSalesDetails.getValueAt(i,8).toString());
                if(tblSalesDetails.getValueAt(i,5).toString().equals("12.0") || tblSalesDetails.getValueAt(i,5).toString().equals("12")){
                    gGst12 += Double.parseDouble(tblSalesDetails.getValueAt(i,6).toString());
                    gTaxableAt12 += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                    gCessGross += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                }else if(tblSalesDetails.getValueAt(i,5).toString().equals("18.0") || tblSalesDetails.getValueAt(i,5).toString().equals("18")){
                    gGst18 += Double.parseDouble(tblSalesDetails.getValueAt(i,6).toString());
                    gTaxableAt18 += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                    gCessGross += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                }else if(tblSalesDetails.getValueAt(i,5).toString().equals("5.0")|| tblSalesDetails.getValueAt(i,5).toString().equals("5")){
                    gGst5 += Double.parseDouble(tblSalesDetails.getValueAt(i,6).toString());
                    gTaxableAt5 += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                }else if(tblSalesDetails.getValueAt(i,5).toString().equals("28.0")|| tblSalesDetails.getValueAt(i,5).toString().equals("28")){
                    gGst28 += Double.parseDouble(tblSalesDetails.getValueAt(i,6).toString());
                    gTaxableAt28 += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                    gCessGross += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                }else if(tblSalesDetails.getValueAt(i,5).toString().equals("0.0")|| tblSalesDetails.getValueAt(i,5).toString().equals("0")){
                    gTaxableAt0 += Double.parseDouble(tblSalesDetails.getValueAt(i,4).toString())*Double.parseDouble(tblSalesDetails.getValueAt(i,7).toString());
                }
            
            gTotalGst += Double.parseDouble(tblSalesDetails.getValueAt(i,6).toString());
            
        }
        
        txtGst12.setText(df.format(gGst12));
        txtGst18.setText(df.format(gGst18));
        txtGst28.setText(df.format(gGst28));
        txtGst5.setText(df.format(gGst5));
        gSGst = gTotalGst*0.5;
        gCGst = gTotalGst*0.5;
        txtSGst.setText(df.format(gSGst));
        txtCGst.setText(df.format(gCGst));
        txtTotalGst.setText(df.format(gTotalGst));
        //Change for B2B Cess=0
        if(!(btnSave.getText().equalsIgnoreCase("update"))){
            if(cmbCustomerType.getSelectedItem().toString().equalsIgnoreCase("b2b"))
                gCess = 0;
            else
                gCess = gCessGross*0.01; 
                //gCess = gTotalGst*0.01;       
        }else if(!(txtBillNumber.getText().contains("E-"))){//Added 6/24 for Cess calculation logic in Sales Return
            gCess = gCessGross*0.01;
        }
        System.out.println("--Cess  GROSS "+gCessGross);
        
        txtCess.setText(df.format(gCess));
        txtTotalAmount.setText(df.format(GenericUtils.roundDouble((gBillAmount+gCess),2)));
        
        /*if(((PricingTier)cmbCustomerType.getSelectedItem()).getCustomerTypeName().equals("Contractor")){
            gDiscount = gBillAmount*0.03;
        }else{
            gDiscount = 0.0;
        }*/
        //gDiscount = gBillAmount*0.05;
        gPyableAmount = Math.round(gBillAmount + gCess - gDiscount);
        txtTotalMrp.setText(GenericUtils.roundDouble(gTotalMrp, 2)+"");
        //gPyableAmount = gBillAmount - gDiscount;
        txtDiscountAmount.setText(df.format(gTotalMrp - gBillAmount - gCess));
        txtAmountPayable.setText(df.format(gPyableAmount));
        txtPayingAmount.setText(df.format(gPyableAmount));
        double dRound = gPyableAmount - Double.parseDouble(txtTotalAmount.getText());
        lblRoundOff.setText("Round Off: "+GenericUtils.roundDouble(dRound,2));
    }
    
    public void clearItemFields(){
        txtItemName.setText(null);
        txtHSNCode.setText(null);
        txtUnitPrice.setText(null);
        txtQuantity.setText(null);
        txtSellingPrice.setText(null);
        txtGstAmount.setText(null);
        txtGstPercentage.setText(null);
        txtMrp.setText(null);
        lblAvailableQty.setText(null);
        lblWPIndicator.setText(null);
        
    }
    public void simulateNewButtonClick(){
            saleIdEdit = 0;
            PricingTierDAO pricingTierDAO = new PricingTierDAO();
            renderDataIntoDropDown(pricingTierDAO.retrieveAll());

            CustomerDAO customerDAO = new CustomerDAO();
            renderCustomerDataIntoDropDown(customerDAO.retrieveAll());

            //Moved to Bill type Drop Down for different series of billing
            /*EstimateDAO billGenDAO =  new EstimateDAO();
            txtBillNumber.setText(billGenDAO.generateBillNumber()+"");*/
            ((DefaultTableModel)tblSalesDetails.getModel()).setNumRows(0);
            btnSave.setText("Save");
            btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png")));
            enableFormElements(true);
            //if(null==dtpSalesDate.getDate()){
                dtpSalesDate.setDate(new Date());
            //}
            SuggestionUtility.populateItemNames(this, txtItemName);
            //txtItemName.requestFocus();
            txtDisplayName.requestFocus();
    }
    public void populateItemDetails(){
            System.out.println("---inside item name change");
            try{
                StockDAO stockDAO = null;
                //Stock
                stock = null;
                double dUnitGst=0.0;
                double dUnitSellingPrice=0.0;
                double dUnitDiscount=0.0;
                double dUnitPostGstPrice=0.0;

                if(!txtItemName.getText().equalsIgnoreCase("")){
                    stockDAO = new StockDAO();
                    stock = stockDAO.retrieveByName(txtItemName.getText().trim());
                }
                txtHSNCode.setText(stock.getHsnCode());
                txtUnitPrice.setText(stock.getPurchasePrice()+"");
                if(stock.getMrp()>0){
                    txtMrp.setText(stock.getMrp()+"");
                    txtSellingPrice.requestFocus();
                }else{
                    txtMrp.setText("0");
                    txtMrp.requestFocus();
                }
                
                //GST Amount Calculation
                txtGstPercentage.setText(stock.getGstPercentage()+"");
                dUnitGst = stock.getGstPercentage()*stock.getPurchasePrice()/100;
                txtGstAmount.setText(df.format(dUnitGst)+"");
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
                dUnitSellingPrice = dUnitSellingPrice * gSalesPercentage *0.01;
                txtSellingPrice.setText(df.format(dUnitSellingPrice)+"");
                //For Wholesale price logic
                if((txtBillNumber.getText().contains("E-") || chkWholesale.isSelected()) && stock.getWholesalePrice()>0){
                    txtSellingPrice.setText(df.format(stock.getWholesalePrice()));
                }else if (stock.getSellingPrice()>=0){
                    txtSellingPrice.setText(df.format(stock.getSellingPrice()));
                    lblWPIndicator.setText(df.format(stock.getWholesalePrice()));
                }else{
                    txtSellingPrice.setText(df.format(dUnitSellingPrice)+"");
                }
                //Stock Availability Check
                gStockAvailable = stock.getQuantity();
                lblAvailableQty.setText(gStockAvailable+"");
                //txtMrp.setEnabled(false);
                txtHSNCode.setEnabled(false);
                txtGstPercentage.setEnabled(false);
                txtGstAmount.setEnabled(false);
                //txtUnitPrice.setEnabled(false);
                //txtSellingPrice.requestFocus();
                //txtQuantity.setText("1");
                //txtQuantity.requestFocus();

                //System.out.println("outside cust type loop");
                for(int i=0;i<cmbCustomerType.getItemCount();i++){
                    System.out.println("inside cust--> type loop"+tempPrice);
                    PricingTier pricingTier = (PricingTier) cmbCustomerType.getItemAt(i);
                    if(pricingTier.getCustomerTypeName().equalsIgnoreCase("plumber")){
                        //lblLandingPrice.setText(df.format(tempPrice*pricingTier.getPricingPercentage()*0.01));
                    }else if(pricingTier.getCustomerTypeName().equalsIgnoreCase("contractor")){
                        lblContractorPrice.setText(df.format(tempPrice*pricingTier.getPricingPercentage()*0.01));
                    }else{
                        lblCustomerPrice.setText(df.format(tempPrice*pricingTier.getPricingPercentage()*0.01));
                    }
                }
                System.out.println("--------"+dUnitPostGstPrice);
                System.out.println("Landing Price--");
                if(stock.getLandingPrice()>0)
                    lblPPIndicator.setText(stock.getLandingPrice()+"");
                   else
                lblPPIndicator.setText(df.format(dUnitPostGstPrice));
            }
            
            catch(Exception e){
                System.out.println("Error Retrieving the item -"+e.getMessage());
            }          
            
    }
    public void customerSelectedAction(){
            String selectedCustomer = ((Customer) cmbCustomerName.getSelectedItem()).getCustomerName();
            if(selectedCustomer.equalsIgnoreCase("new") || selectedCustomer.equalsIgnoreCase("default")){
            txtDisplayName.setText("");
            txtShippingAddress.setText("");
            txtDisplayName.setVisible(true);
            lblDisplayName.setVisible(true);
            //txtDisplayName.requestFocus();
        }else{
            txtDisplayName.setText(((Customer) cmbCustomerName.getSelectedItem()).getCustomerName()+", \n"+((Customer) cmbCustomerName.getSelectedItem()).getAddress());
            //txtItemName.requestFocus();
            txtDisplayName.setVisible(true);
            lblDisplayName.setVisible(true);
            //txtVehicleDetails.requestFocus();
        }
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMore;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrintPreview;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewAll;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox chkPayBalance;
    private javax.swing.JCheckBox chkSameAsAbove;
    private javax.swing.JCheckBox chkWholesale;
    private javax.swing.JComboBox cmbCustomerName;
    private javax.swing.JComboBox cmbCustomerType;
    private javax.swing.JButton cmdReset;
    private com.toedter.calendar.JDateChooser dtpSalesDate;
    private javax.swing.ButtonGroup grpSalesType;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblAvailableQty;
    private javax.swing.JLabel lblCGst;
    private javax.swing.JLabel lblCPrice;
    private javax.swing.JLabel lblCess;
    private javax.swing.JLabel lblContractorPrice;
    private javax.swing.JLabel lblCustomerName;
    private javax.swing.JLabel lblCustomerPrice;
    private javax.swing.JLabel lblCustomerType;
    private javax.swing.JLabel lblDisplayName;
    private javax.swing.JLabel lblDisplayName1;
    private javax.swing.JLabel lblGst12;
    private javax.swing.JLabel lblGst13;
    private javax.swing.JLabel lblGst14;
    private javax.swing.JLabel lblGst16;
    private javax.swing.JLabel lblGst17;
    private javax.swing.JLabel lblGst28;
    private javax.swing.JLabel lblHSNCode;
    private javax.swing.JLabel lblHSNCode1;
    private javax.swing.JLabel lblItemCount;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblLandingPrice;
    private javax.swing.JLabel lblPPIndicator;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblRoundOff;
    private javax.swing.JLabel lblSGst;
    private javax.swing.JLabel lblSGst1;
    private javax.swing.JLabel lblSGst2;
    private javax.swing.JLabel lblSGst3;
    private javax.swing.JLabel lblSGst4;
    private javax.swing.JLabel lblSGst5;
    private javax.swing.JLabel lblSGst6;
    private javax.swing.JLabel lblSGst7;
    private javax.swing.JLabel lblSalesDate;
    private javax.swing.JLabel lblTotalGst;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JLabel lblUnitPrice1;
    private javax.swing.JLabel lblUnitPrice2;
    private javax.swing.JLabel lblUnitPrice3;
    private javax.swing.JLabel lblUnitPrice4;
    private javax.swing.JLabel lblWPIndicator;
    private javax.swing.JMenuItem mitDelete;
    private javax.swing.JMenuItem mitEdit;
    private javax.swing.JMenuItem mitReturn;
    private javax.swing.JPopupMenu mnuSalesDetails;
    private javax.swing.JPanel pnlSupplier;
    private javax.swing.JRadioButton radCash;
    private javax.swing.JRadioButton radCredit;
    private javax.swing.JScrollPane spnSalesItems;
    private com.essar.dao.StockDAO stockDAO1;
    private javax.swing.JTable tblSalesDetails;
    private javax.swing.JTextField txtAmountPayable;
    private javax.swing.JTextField txtBalanceAmount;
    private javax.swing.JTextField txtBillNumber;
    private javax.swing.JTextField txtCGst;
    private javax.swing.JTextField txtCess;
    private javax.swing.JTextField txtDiscountAmount;
    private javax.swing.JTextArea txtDisplayName;
    private javax.swing.JTextField txtGst12;
    private javax.swing.JTextField txtGst18;
    private javax.swing.JTextField txtGst28;
    private javax.swing.JTextField txtGst5;
    private javax.swing.JTextField txtGstAmount;
    private javax.swing.JTextField txtGstPercentage;
    private javax.swing.JTextField txtHSNCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtMrp;
    private javax.swing.JTextField txtPayingAmount;
    private javax.swing.JTextField txtPreviousBalance;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSGst;
    private javax.swing.JTextField txtSellingPrice;
    private javax.swing.JTextArea txtShippingAddress;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtTotalGst;
    private javax.swing.JTextField txtTotalMrp;
    private javax.swing.JTextField txtUnitPrice;
    private javax.swing.JTextField txtVehicleDetails;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent event) {
        try{
            JMenuItem menu = (JMenuItem) event.getSource();
            if (menu == mitDelete) {
                //int rowIndex = tblSupplier.getSelectedRow();
                System.out.println("--------INSIDE DELETE-"+tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),1));
                //Removes just the selected row from UI,  NOT from DB
                System.out.println("Map Size ::" + moreMap.size());
                if(moreMap.size()>0){
                    moreMap.remove(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),9));
                }
                System.out.println("map keys : "+moreMap.keySet());
                GenericUtils.removeRowFromJTable(tblSalesDetails, salesDetailsModel);
                
                deriveValuesFromSalesInput();
                
            } else if (menu == mitReturn) {
                SalesReturn salesReturn = new SalesReturn();
                //int rowIndex = tblSupplier.getSelectedRow();
                System.out.println("--------INSIDE RETURN "+txtBillNumber.getText());
                //Removes just the selected row from UI,  NOT from DB
                //System.out.println("Map Size ::" + map.size());
                salesReturn.setBillNumber(txtBillNumber.getText().trim());
                salesReturn.setItemName(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),2).toString());
                salesReturn.setCreateTS(Calendar.getInstance().getTime());                
                salesReturn.setNetAmount(Double.parseDouble(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),8).toString()));
                salesReturn.setQuantity(Double.parseDouble(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),7).toString()));
                salesReturn.setUpdateTS(Calendar.getInstance().getTime());
                salesReturn.setSalesId(saleIdEdit);
                if(null!=tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),10)){
                    salesReturn.setItemId(Long.parseLong(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),9).toString()));
                    salesReturn.setSalesDetailsId(Long.parseLong(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),10).toString()));
                }
                
                returnList.add(salesReturn);
                  
                /*if(map.size()>0){
                    map.remove(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),7));
                }
                System.out.println("map keys : "+map.keySet());*/
                GenericUtils.removeRowFromJTable(tblSalesDetails, salesDetailsModel);
                deriveValuesFromSalesInput();
                
            } 
            
            else if (menu == mitEdit) {
                SalesReturn salesReturn = new SalesReturn();
                //int rowIndex = tblSupplier.getSelectedRow();
                System.out.println("--------INSIDE EDIT "+txtBillNumber.getText());
                //Removes just the selected row from UI,  NOT from DB
                //System.out.println("Map Size ::" + map.size());
                enableFormElements(true);
                //======Populating item details for editing
                //txtHSNCode.setText(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),0).toString());
                txtItemName.setText(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),2).toString());
                //txtUnitPrice.setText(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),2).toString());
                //txtGstPercentage.setText(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),3).toString());
                //txtGstAmount.setText(df.format(Double.parseDouble(txtUnitPrice.getText())
                //        *Double.parseDouble(txtGstPercentage.getText())*0.01));
                //txtQuantity.setText(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),5).toString());
                //txtSellingPrice.setText(df.format(Double.parseDouble(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),6).toString())/Double.parseDouble(txtQuantity.getText())));
                
                
                //txtQuantity.requestFocus();
                enableFormElements(true);
                SuggestionUtility.populateItemNames(this, txtItemName);
                PricingTierDAO pricingTierDAO = new PricingTierDAO();
                renderDataIntoDropDown(pricingTierDAO.retrieveAll());
                txtItemName.requestFocus();
                //########0
                salesReturn.setBillNumber(txtBillNumber.getText().trim());
                salesReturn.setItemName(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),2).toString());
                salesReturn.setCreateTS(Calendar.getInstance().getTime());
                salesReturn.setItemId(Long.parseLong(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),9).toString()));
                salesReturn.setNetAmount(Double.parseDouble(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),8).toString()));
                salesReturn.setQuantity(Double.parseDouble(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),7).toString()));
                salesReturn.setUpdateTS(Calendar.getInstance().getTime());
                salesReturn.setSalesId(saleIdEdit);
                salesReturn.setSalesDetailsId(Long.parseLong(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),10).toString()));
                returnList.add(salesReturn);
                  
                /*if(map.size()>0){
                    map.remove(tblSalesDetails.getModel().getValueAt(tblSalesDetails.getSelectedRow(),7));
                }
                System.out.println("map keys : "+map.keySet());*/
                GenericUtils.removeRowFromJTable(tblSalesDetails, salesDetailsModel);
                deriveValuesFromSalesInput();
                trackActivity = false;
                
            } 
        }catch(Exception e){
            System.out.println("---"+e.getMessage());
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
}
