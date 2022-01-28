/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import com.essar.utils.ConnectionManager;
import com.essar.pojos.ProductCategory;
import com.essar.pojos.Purchase;
import com.essar.pojos.PurchaseDetails;
import com.essar.pojos.PurchaseReturn;
import com.essar.pojos.PurchaseView;
import com.essar.pojos.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahumathulla
 */
public class PurchaseDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<Purchase> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Purchase>  purchaseList= null;
        try {
            sql = "SELECT * from purchase order by purchase_id ";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            Purchase purchase = null;
            purchaseList = new ArrayList<Purchase>();            
            while(rs.next()){
                purchase = new Purchase();
                purchase.setPurchaseId(rs.getLong(1));
                purchase.setBillNumber(rs.getString(2));
                purchase.setBillAmount(rs.getDouble(3));
                purchase.setDiscount(rs.getDouble(4));                 
                purchase.setPayableAmount(rs.getLong(5));
                purchase.setStatus(rs.getString(6));
                purchase.setBalanceAmount(rs.getDouble(7)); 
                purchase.setPurchaseDate(rs.getDate(8));
                purchase.setTotalGst(rs.getDouble(9));
                purchase.setSgst(rs.getDouble(10));
                purchase.setCgst(rs.getDouble(11)); 
                purchase.setCreateTS(rs.getDate(12)); 
                purchase.setUpdateTS(rs.getDate(13)); 
                
                //Adding to List
                purchaseList.add(purchase);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchaseList;
    }
    
    public List<PurchaseView> retrievePurchaseList() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<PurchaseView>  purchaseViewList= null;
        try {
            sql = "SELECT p.purchase_id"
                    + ", sup.supplier_name"
                    + ", p.bill_number"
                    + ", SUM(d.quantity)"
                    + ", p.bill_amount"
                    + ", p.discount"
                    + ", p.payable_amount"
                    + ", p.purchase_date"
                    + ", p.status  "
                    + " from purchase p JOIN supplier sup ON p.supplier_id = sup.supplier_id"
                    + " JOIN purchase_details d ON p.purchase_id = d.purchase_id "
                    + " group by purchase_id order by purchase_id";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            PurchaseView purchaseView = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            purchaseViewList = new ArrayList<PurchaseView>();            
            while(rs.next()){
                purchaseView = new PurchaseView();
                purchaseView.setPurchaseId(rs.getLong(1));
                purchaseView.setSupplierName(rs.getString(2));
                purchaseView.setBillNumber(rs.getString(3));
                purchaseView.setQuantity(rs.getDouble(4));
                purchaseView.setBillAmount(rs.getDouble(5));                
                purchaseView.setDiscount(rs.getDouble(6)); 
                purchaseView.setPayableAmount(rs.getDouble(7));
                purchaseView.setPurchaseDate(sdf.format(rs.getDate(8)));
                purchaseView.setStatus(rs.getString(9));
                //Adding to List
                purchaseViewList.add(purchaseView);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchaseViewList;
    }
    
    public List<PurchaseView> retrievePurchaseByQuery(String sql) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<PurchaseView>  purchaseViewList= null;
        try {
            

            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            PurchaseView purchaseView = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            purchaseViewList = new ArrayList<PurchaseView>();            
            while(rs.next()){
                purchaseView = new PurchaseView();
                purchaseView.setPurchaseId(rs.getLong(1));
                purchaseView.setSupplierName(rs.getString(2));
                purchaseView.setBillNumber(rs.getString(3));
                purchaseView.setQuantity(rs.getDouble(4));
                purchaseView.setBillAmount(rs.getDouble(5));                
                purchaseView.setDiscount(rs.getDouble(6)); 
                purchaseView.setPayableAmount(rs.getDouble(7));
                purchaseView.setPurchaseDate(sdf.format(rs.getDate(8)));
                purchaseView.setStatus(rs.getString(9));
                //Adding to List
                purchaseViewList.add(purchaseView);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchaseViewList;
    }
    
    public long generateBillNumber() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        long billNumber = 10001;
        try {
            sql = "SELECT max(bill_number) from purchase order by create_ts";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               billNumber = rs.getLong(1) + 1;                                  
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return billNumber;
    }
    
    public long getOngoingPurchaseId(String billNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        long purchaseId=0;
        try {
            sql = "SELECT purchase_id from purchase where bill_number ='"+billNumber+"'";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               purchaseId = rs.getLong(1);
               System.out.println("-----purchaseId  = "+purchaseId);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return purchaseId;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public Purchase retrieveById(long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Purchase purchase= null;
        try {
            sql = "SELECT * from purchase where purchase_id=" + id;
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                purchase = new Purchase();
                purchase.setPurchaseId(rs.getLong(1));
                purchase.setBillNumber(rs.getString(2));
                purchase.setBillAmount(rs.getDouble(3));
                purchase.setDiscount(rs.getDouble(4));                 
                purchase.setPayableAmount(rs.getLong(5));
                purchase.setStatus(rs.getString(6));
                purchase.setBalanceAmount(rs.getDouble(7)); 
                purchase.setPurchaseDate(rs.getDate(8));
                purchase.setTotalGst(rs.getDouble(9));
                purchase.setSgst(rs.getDouble(10));
                purchase.setCgst(rs.getDouble(11)); 
                purchase.setCreateTS(rs.getDate(12)); 
                purchase.setUpdateTS(rs.getDate(13));                      
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchase;
    }
     
     public Purchase retrieveByBillNumber(String billNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Purchase purchase= null;
        PurchaseDetails purchaseDetails = null;
        List<PurchaseDetails> purchaseDetailsList = new ArrayList<>();
        try {
            sql = "SELECT * from purchase where bill_number='" + billNumber+"'";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                purchase = new Purchase();
                purchase.setPurchaseId(rs.getLong(1));
                purchase.setSupplierId(rs.getLong(2));
                purchase.setBillNumber(rs.getString(3));
                purchase.setBillAmount(rs.getDouble(4));
                purchase.setDiscount(rs.getDouble(5));                 
                purchase.setPayableAmount(rs.getLong(6));
                purchase.setStatus(rs.getString(7)); 
                purchase.setBalanceAmount(rs.getDouble(8)); 
               
                purchase.setPurchaseDate(rs.getDate(9));
                purchase.setTotalGst(rs.getDouble(10));
                purchase.setSgst(rs.getDouble(11));
                purchase.setCgst(rs.getDouble(12));
                purchase.setGstAt12(rs.getDouble(13));
                purchase.setGstAt18(rs.getDouble(14));
                purchase.setGstAt30(rs.getDouble(15));
                purchase.setCess(rs.getDouble(16));
                //sales.setCreateTS(rs.getDate(13)); 
                //sales.setUpdateTS(rs.getDate(14));
             String detailSql = "SELECT * from purchase_details where purchase_id ="+purchase.getPurchaseId();  
             Statement detailStatement = con.createStatement();
             ResultSet rsDetails = detailStatement.executeQuery(detailSql);
             while(rsDetails.next()){
                 purchaseDetails = new PurchaseDetails();
                 purchaseDetails.setPurchaseDetailsId(rsDetails.getLong(1));
                 purchaseDetails.setPurchaseId(rsDetails.getLong(2));
                 purchaseDetails.setHsnCode(rsDetails.getString(3));
                 purchaseDetails.setItemName(rsDetails.getString(4));
                 purchaseDetails.setUnitPrice(rsDetails.getDouble(5));
                 purchaseDetails.setGstPercentage(rsDetails.getDouble(6));
                 purchaseDetails.setGstAmount(rsDetails.getDouble(7));
                 purchaseDetails.setQuantity(rsDetails.getDouble(8));
                 purchaseDetails.setGrossAmount(rsDetails.getDouble(9));
                 purchaseDetails.setDiscount(rsDetails.getDouble(10));
                 purchaseDetails.setNetAmount(rsDetails.getDouble(11));
                 purchaseDetails.setItemId(rsDetails.getLong(12));
                 purchaseDetailsList.add(purchaseDetails);
             }
             purchase.setPurchaseDetails(purchaseDetailsList);
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchase;
    }
     
     public Purchase retrieveByPurchasId(Long purchaseId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Purchase purchase= null;
        PurchaseDetails purchaseDetails = null;
        List<PurchaseDetails> purchaseDetailsList = new ArrayList<>();
        try {
            sql = "SELECT * from purchase where purchase_id=" + purchaseId;
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                purchase = new Purchase();
                purchase.setPurchaseId(rs.getLong(1));
                purchase.setSupplierId(rs.getLong(2));
                purchase.setBillNumber(rs.getString(3));
                purchase.setBillAmount(rs.getDouble(4));
                purchase.setDiscount(rs.getDouble(5));                 
                purchase.setPayableAmount(rs.getLong(6));
                purchase.setStatus(rs.getString(7)); 
                purchase.setBalanceAmount(rs.getDouble(8)); 
               
                purchase.setPurchaseDate(rs.getDate(9));
                purchase.setTotalGst(rs.getDouble(10));
                purchase.setSgst(rs.getDouble(11));
                purchase.setCgst(rs.getDouble(12));
                purchase.setGstAt12(rs.getDouble(13));
                purchase.setGstAt18(rs.getDouble(14));
                purchase.setGstAt30(rs.getDouble(15));
                purchase.setCess(rs.getDouble(16));
                //sales.setCreateTS(rs.getDate(13)); 
                //sales.setUpdateTS(rs.getDate(14));
             String detailSql = "SELECT * from purchase_details where purchase_id ="+purchase.getPurchaseId();  
             Statement detailStatement = con.createStatement();
             ResultSet rsDetails = detailStatement.executeQuery(detailSql);
             while(rsDetails.next()){
                 purchaseDetails = new PurchaseDetails();
                 purchaseDetails.setPurchaseDetailsId(rsDetails.getLong(1));
                 purchaseDetails.setPurchaseId(rsDetails.getLong(2));
                 purchaseDetails.setHsnCode(rsDetails.getString(3));
                 purchaseDetails.setItemName(rsDetails.getString(4));
                 purchaseDetails.setUnitPrice(rsDetails.getDouble(5));
                 purchaseDetails.setGstPercentage(rsDetails.getDouble(6));
                 purchaseDetails.setGstAmount(rsDetails.getDouble(7));
                 purchaseDetails.setQuantity(rsDetails.getDouble(8));
                 purchaseDetails.setGrossAmount(rsDetails.getDouble(9));
                 purchaseDetails.setDiscount(rsDetails.getDouble(10));
                 purchaseDetails.setNetAmount(rsDetails.getDouble(11));
                 purchaseDetails.setItemId(rsDetails.getLong(12));
                 purchaseDetailsList.add(purchaseDetails);
             }
             purchase.setPurchaseDetails(purchaseDetailsList);
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return purchase;
    }
     
     public List<Long> getMaxAndMinIds() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Long> borderIds = new ArrayList<>();
        try {
            sql = "SELECT min(purchase_id),max(purchase_id) from purchase";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               borderIds.add(rs.getLong(1));
               borderIds.add(rs.getLong(2));    
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return borderIds;
    }
    
    public void insertIntoDB(Purchase purchase) {
        try {            
            String sql = "INSERT INTO purchase (supplier_id,bill_number, bill_amount"
                    + ", discount, payable_amount, status, balance_amount, purchase_date"
                    + ", total_gst, sgst, cgst, gst_12, gst_18, gst_30,cess, create_ts, update_ts)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("---"+purchase.getPurchaseDate().getTime());
            java.sql.Date purchaseDate = new java.sql.Date( purchase.getPurchaseDate().getTime() );
            java.sql.Date createTS = new java.sql.Date( purchase.getCreateTS().getTime() );
            java.sql.Date updateTS = new java.sql.Date( purchase.getUpdateTS().getTime() );
            //java.sql.Date updateTS = new java.sql.Date( sales.getSalesDate().getTime() );
            ps.setLong(1, purchase.getSupplierId());
            ps.setString(2, purchase.getBillNumber());
            ps.setDouble(3, purchase.getBillAmount());
            ps.setDouble(4, purchase.getDiscount());
            ps.setDouble(5, purchase.getPayableAmount());
            ps.setString(6, purchase.getStatus());
            ps.setDouble(7, purchase.getBalanceAmount());
            ps.setDate(8, purchaseDate);
            ps.setDouble(9, purchase.getTotalGst());            
            ps.setDouble(10, purchase.getSgst());
            ps.setDouble(11, purchase.getCgst());
            ps.setDouble(12, purchase.getGstAt12());            
            ps.setDouble(13, purchase.getGstAt18());
            ps.setDouble(14, purchase.getGstAt30());
            System.out.println("----Saving CEss"+purchase.getCess());
            ps.setDouble(15, purchase.getCess());
            ps.setDate(16, createTS);
            //ps.setDate(17, updateTS);
            ps.executeUpdate();
                        
            //PurchaseDetails purchaseDetails = new PurchaseDetails();
            List<PurchaseDetails> purchaseDetailsList = purchase.getPurchaseDetails();
            PreparedStatement detailStatement = null;
            String detailSql = "INSERT INTO purchase_details (purchase_id,hsn_code, item_name"
                    + ", unit_price, gst_percentage, gst_amount, quantity, gross_amount"
                    + ", discount, net_amount, item_id)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";
            detailStatement = con.prepareStatement(detailSql);
            PurchaseDAO purchaseDAO =  new PurchaseDAO();
            long ongoingPurchaseId = purchaseDAO.getOngoingPurchaseId(purchase.getBillNumber());
            System.out.println("P Details Size =="+purchaseDetailsList.size());
            for (PurchaseDetails purchaseDetails: purchaseDetailsList){
                
                //Getting the sales id with Bill Number

                System.out.println("------Bill No ::"+purchase.getBillNumber());
                detailStatement.setLong(1, ongoingPurchaseId);
                detailStatement.setString(2, purchaseDetails.getHsnCode());
                detailStatement.setString(3, purchaseDetails.getItemName());
                detailStatement.setDouble(4, purchaseDetails.getUnitPrice());
                detailStatement.setDouble(5, purchaseDetails.getGstPercentage());
                detailStatement.setDouble(6, purchaseDetails.getGstAmount());
                detailStatement.setDouble(7, purchaseDetails.getQuantity());
                detailStatement.setDouble(8, purchaseDetails.getGrossAmount());
                detailStatement.setDouble(9, purchaseDetails.getDiscount());
                detailStatement.setDouble(10, purchaseDetails.getNetAmount());
                detailStatement.setDouble(11, purchaseDetails.getItemId());
                //System.out.println(" Query :: "+detailStatement);
                detailStatement.addBatch();
                //detailStatement.clearParameters();
                //detailStatement.executeUpdate();
                
            }
            int[] results = detailStatement.executeBatch();
            updateStockAfterPurchase(purchase,purchaseDetailsList);
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePurchaseAfterReturn(Purchase purchase, List<PurchaseReturn> returnList) {
        try {
            
            String sql = "UPDATE purchase SET bill_amount =?"
                    + ", discount =?, payable_amount=?, status =?, balance_amount =?"
                    + ", total_gst =?, sgst =?, cgst=?, gst_12=?, gst_18=?, gst_30=?"
                    + ", cess =?, purchase_date=? "             
                    + ", update_ts=NOW() "
                    + " WHERE bill_number = ?"
                    ;
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("---"+purchase.getPurchaseDate().getTime());
            //java.sql.Date salesDate = new java.sql.Date( sales.getSalesDate().getTime() );
            //java.sql.Date createTS = new java.sql.Date( sales.getCreateTS().getTime() );
            //java.sql.Date updateTS = new java.sql.Date( sales.getUpdateTS().getTime() );
            //java.sql.Date updateTS = new java.sql.Date( sales.getSalesDate().getTime() );
            java.sql.Date purchaseDate = new java.sql.Date( purchase.getPurchaseDate().getTime() );
            ps.setDouble(1, purchase.getBillAmount());
            ps.setDouble(2, purchase.getDiscount());
            ps.setDouble(3, purchase.getPayableAmount());
            ps.setString(4, purchase.getStatus());
            ps.setDouble(5, purchase.getBalanceAmount());
            //ps.setDate(6, salesDate);
            ps.setDouble(6, purchase.getTotalGst());            
            ps.setDouble(7, purchase.getSgst());
            ps.setDouble(8, purchase.getCgst());
            ps.setDouble(9, purchase.getGstAt12());            
            ps.setDouble(10, purchase.getGstAt18());
            ps.setDouble(11, purchase.getGstAt30());
            ps.setDouble(12, purchase.getCess());
            ps.setDate(13, purchaseDate);
            ps.setString(14, purchase.getBillNumber());
            System.out.println("----Updating Purchase = "+purchase.getBillNumber());
            System.out.println("----Updating Query = "+ps.toString());
            System.out.println("----Purchase Details Size  = "+purchase.getPurchaseDetails().size());
            //ps.setDouble(15, sales.getCess());
            //ps.setDate(16, createTS);
            //ps.setString(17, sales.getCustomerName());
            //ps.setDate(17, updateTS);
            ps.executeUpdate();
            
            //More Line Item on top of existing Bill Logic
            PreparedStatement detailStatement = null;
            String detailSql = "INSERT INTO purchase_details (purchase_id,hsn_code, item_name"
                    + ", unit_price, gst_percentage, gst_amount, quantity, gross_amount"
                    + ", discount, net_amount, item_id)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";
            PurchaseDAO purchaseDAO =  new PurchaseDAO();
            long ongoingPurchaseId = purchaseDAO.getOngoingPurchaseId(purchase.getBillNumber());
            List<PurchaseDetails> purchaseDetailsList = purchase.getPurchaseDetails();
            detailStatement = con.prepareStatement(detailSql);
            //PurchaseDetails purchaseDetails = null;
            for (PurchaseDetails purchaseDetails: purchaseDetailsList){                
                //Getting the sales id with Bill Number
                detailStatement.setLong(1,ongoingPurchaseId);
                detailStatement.setString(2, purchaseDetails.getHsnCode());
                detailStatement.setString(3, purchaseDetails.getItemName());
                detailStatement.setDouble(4, purchaseDetails.getUnitPrice());
                detailStatement.setDouble(5, purchaseDetails.getGstPercentage());
                detailStatement.setDouble(6, purchaseDetails.getGstAmount());
                detailStatement.setDouble(7, purchaseDetails.getQuantity());
                detailStatement.setDouble(8, purchaseDetails.getGrossAmount());
                detailStatement.setDouble(9, purchaseDetails.getDiscount());
                detailStatement.setDouble(10, purchaseDetails.getNetAmount());
                detailStatement.setLong(11, purchaseDetails.getItemId()); 
                //detailStatement.executeUpdate(); 
                detailStatement.addBatch();
                //detailStatement.clearParameters();
                
            }
            int[] results = detailStatement.executeBatch();
            updateStockAfterPurchase(purchase,purchaseDetailsList);
                        
            PurchaseReturn purchaseReturn = new PurchaseReturn();
            //List<SalesDetails> salesDetailsList = sales.getSalesDetails();
            PreparedStatement deleteStatement = null;
            String deleteSql = "DELETE FROM purchase_details where purchase_details_id =?  ";                 
            for (int i=0; i<returnList.size(); i++){
                deleteStatement = con.prepareStatement(deleteSql);
                purchaseReturn = returnList.get(i);
                deleteStatement = con.prepareStatement(deleteSql);
                deleteStatement.setLong(1, purchaseReturn.getPurchaseDetailsId());
                
                deleteStatement.executeUpdate();                
                
            }
            System.out.println("Above Stock Update after PReturn");
            updateStockAfterPurchaseReturn(purchase, returnList);
            
            if(purchase.getBillAmount()<=0){
                PreparedStatement stDelete = null;               
                String deleteBill = "DELETE FROM purchase where bill_number =?  ";
                stDelete = con.prepareStatement(deleteBill);
                stDelete.setString(1, purchase.getBillNumber());
                stDelete.executeUpdate();
                System.out.println("####"+stDelete.toString());
            }
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStockAfterPurchase(Purchase purchase,List<PurchaseDetails> purchaseDetailsList) {
        try {
            //ConnectionManager cm = new ConnectionManager();
            //Connection co = cm.getConnection();
            //String stSql = "UPDATE stock SET quantity = quantity+?, update_ts = NOW() WHERE item_id = ? ";
            /*String stSql = "UPDATE stock SET quantity = CASE "
			+" WHEN (quantity <0 OR quantity IS NULL) THEN ?"
			+" ELSE quantity+?"
			+" END "
                        +" , selling_price = CASE WHEN (selling_price IS NULL OR selling_price <> ?) AND ?>0 THEN ? END"
                        +" , wholesale_price = CASE WHEN (wholesale_price IS NULL OR wholesale_price <> ?) AND ?>0 THEN ? END"
                        +" WHERE item_id = ? ";*/
            String stSql = "UPDATE stock SET quantity = CASE "
			+" WHEN (quantity <0 OR quantity IS NULL) THEN ?"
			+" ELSE quantity+?"
			+" END "
                        +" , selling_price = CASE WHEN ?>0 THEN ? END"
                        +" , wholesale_price = CASE WHEN ?>0 THEN ? END"
                        +" WHERE item_id = ? ";
            PreparedStatement ps = con.prepareStatement(stSql);        
            for(int i=0;i<purchaseDetailsList.size();i++){                
                ps.setDouble(1, purchaseDetailsList.get(i).getQuantity());
                ps.setDouble(2, purchaseDetailsList.get(i).getQuantity());
                ps.setDouble(3, purchaseDetailsList.get(i).getRetailPrice());
                ps.setDouble(4, purchaseDetailsList.get(i).getRetailPrice());
                //ps.setDouble(5, purchaseDetailsList.get(i).getRetailPrice());
                ps.setDouble(5, purchaseDetailsList.get(i).getWholesalePrice());
                ps.setDouble(6, purchaseDetailsList.get(i).getWholesalePrice());
                //ps.setDouble(8, purchaseDetailsList.get(i).getWholesalePrice());
                ps.setLong(7, purchaseDetailsList.get(i).getItemId());
                //ps.executeUpdate();
                System.out.println("STOCK UPDATE QUERY || " + ps.toString());
                ps.addBatch();
                //ps.clearParameters();
            }
            int[] results = ps.executeBatch();
            /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String auditSql = "INSERT INTO activity_log (description,created_by) values (?,?)";
            PreparedStatement psAudit = con.prepareStatement(auditSql);
            for(int i=0;i<purchaseDetailsList.size();i++){                
                psAudit.setString(1, "Purchased "+purchaseDetailsList.get(i).getQuantity()+" unit of " + purchaseDetailsList.get(i).getItemName()+" on " +sdf.format(purchase.getPurchaseDate()) +" Invoice Number-"+purchase.getBillNumber());
                psAudit.setString(2, "Purchase");
                psAudit.executeUpdate();
            }*/
            //co.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStockAfterPurchaseReturn(Purchase purchase,List<PurchaseReturn> returnList) {
        try {
            ConnectionManager cm = new ConnectionManager();
            Connection co = cm.getConnection();
            String stSql = "UPDATE stock SET quantity = quantity-?, update_ts =NOW() WHERE item_id = ? ";
            PreparedStatement psUpdate = co.prepareStatement(stSql); 
            
            for(int i=0;i<returnList.size();i++){                
                psUpdate.setDouble(1, returnList.get(i).getQuantity());
                psUpdate.setLong(2, returnList.get(i).getItemId());
                //psUpdate.executeUpdate();
                //System.out.println("SQL || "+psUpdate.toString());
                psUpdate.addBatch();
                //psUpdate.clearParameters();
            }
            int[] results = psUpdate.executeBatch();
            /*String auditSql = "INSERT INTO activity_log (description, created_by) values (?,?)";
            PreparedStatement psAudit = co.prepareStatement(auditSql);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for(int i=0;i<returnList.size();i++){                
                psAudit.setString(1, "Purchase Returned "+returnList.get(i).getQuantity()+" unit of " + returnList.get(i).getItemName()+" with Bill Number-"+purchase.getBillNumber()+" and Purchase Date-"+sdf.format(purchase.getPurchaseDate()));
                psAudit.setString(2, "Purchase Return");
                psAudit.executeUpdate();
            }*/
            
            co.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void deleteRecordByCode(String code) {
        try {            
            String sql = "DELETE FROM supplier WHERE supplier_code = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
