/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import com.essar.utils.ConnectionManager;
import com.essar.pojos.ProductCategory;
import com.essar.pojos.Sales;
import com.essar.pojos.SalesDetails;
import com.essar.pojos.SalesReturn;
import com.essar.pojos.SalesView;
import com.essar.pojos.Supplier;
import com.essar.utils.GenericUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahumathulla
 */
public class SalesDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    
    
    public List<SalesView> retrieveSalesList() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<SalesView>  salesViewList= null;
        try {
            sql = "SELECT s.sales_id"
                    + ", cust.customer_name"
                    + ", s.bill_number"
                    + ", SUM(d.quantity)"
                    + ", s.bill_amount"
                    + ", s.cess"
                    + ", s.discount"
                    + ", s.payable_amount"
                    + ", s.sales_date"
                    + ", s.status  "
                    + " from sales s "
                    + " JOIN customer cust ON s.customer_id = cust.customer_id"
                    + " JOIN sales_details d ON s.sales_id = d.sales_id "
                    + " group by sales_id order by sales_id";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            SalesView salesView = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            salesViewList = new ArrayList<SalesView>();            
            while(rs.next()){
                salesView = new SalesView();
                salesView.setSalesId(rs.getLong(1));
                salesView.setCustomerName(rs.getString(2));
                salesView.setBillNumber(rs.getString(3));
                salesView.setQuantity(rs.getDouble(4));
                salesView.setBillAmount(rs.getDouble(5));
                salesView.setCess(rs.getDouble(6));
                salesView.setDiscount(rs.getDouble(7)); 
                salesView.setPayableAmount(rs.getDouble(8));
                salesView.setSalesDate(sdf.format(rs.getDate(9)));
                salesView.setStatus(rs.getString(10));
                //Adding to List
                salesViewList.add(salesView);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salesViewList;
    }
    
    public long generateBillNumber(String billType) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        long billNumber = 10001;
        try {
            if(billType.equalsIgnoreCase("B2B")){
                sql = "SELECT CASE WHEN bill_seq_number IS NULL THEN 1 ELSE MAX(bill_seq_number)+1 END FROM sales  " +
                    " WHERE UPPER(bill_type) = 'B2B' ";
            }else{
                sql = "SELECT CASE WHEN bill_seq_number IS NULL THEN 1 ELSE MAX(bill_seq_number)+1 END FROM sales  " +
                        " WHERE UPPER(bill_type) IS NULL OR UPPER(bill_type) <> 'B2B'  ";
            }
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               billNumber = rs.getLong(1);                                  
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return billNumber;
    }
    
    public double getBalanceAmount(long customerId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double balanceAmount = 0;
        try {

            sql = "SELECT SUM(balance_amount) FROM sales " +
                " WHERE customer_id ="+customerId;            
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               balanceAmount = rs.getDouble(1);                                  
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return balanceAmount;
    }
    
    public boolean doesSalesBillExist(String billNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean existCheck = false;
        try {
            sql = "SELECT count(*) from sales WHERE bill_number = '"+billNumber+"'";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               if (rs.getLong(1) > 0)
                   existCheck = true;
            }                 
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return existCheck;
    }
    
    public long getLastBillNumber() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        long billNumber = 10001;
        try {
            sql = "SELECT max(sales_id) from sales";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               billNumber = rs.getLong(1);                                  
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return billNumber;
    }
    
    public List<Long> getMaxAndMinIds() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Long> borderIds = new ArrayList<>();
        try {
            sql = "SELECT min(sales_id),max(sales_id) from sales";
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
    
    public long getOngoingSalesId(String billNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        long salesId=0;
        try {
            sql = "SELECT sales_id from sales where bill_number ='"+billNumber+"'";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               salesId = rs.getLong(1);                                  
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return salesId;
    }
    
    public List<SalesView> retrieveSalesByQuery(String query) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<SalesView>  salesViewList= null;
        try {
            sql = query;
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            SalesView salesView = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            salesViewList = new ArrayList<SalesView>();            
            while(rs.next()){
                salesView = new SalesView();
                salesView.setSalesId(rs.getLong(1));
                salesView.setCustomerName(rs.getString(3));
                salesView.setBillNumber(rs.getString(4));
                salesView.setQuantity(rs.getDouble(5));
                salesView.setBillAmount(rs.getDouble(6));
                salesView.setCess(rs.getDouble(7));
                salesView.setDiscount(rs.getDouble(8)); 
                salesView.setPayableAmount(rs.getDouble(9));
                salesView.setSalesDate(sdf.format(rs.getDate(10)));
                salesView.setStatus(rs.getString(11));

                //Adding to List
                salesViewList.add(salesView);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salesViewList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
     
     public Sales retrieveByBillNumber(String billNumber) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Sales sales= null;
        SalesDetails salesDetails = null;
        List<SalesDetails> salesDetailsList = new ArrayList<>();
        try {
            sql = "SELECT * from sales where bill_number='" + billNumber +"'";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                sales = new Sales();
                sales.setSalesId(rs.getLong(1));
                sales.setCustomerId(rs.getLong(2));
                sales.setBillNumber(rs.getString(3));
                sales.setBillAmount(rs.getDouble(4));
                sales.setDiscount(rs.getDouble(5));                 
                sales.setPayableAmount(rs.getLong(6));
                sales.setStatus(rs.getString(7)); 
                sales.setBalanceAmount(rs.getDouble(8)); 
               
                sales.setSalesDate(rs.getDate(9));
                sales.setTotalGst(rs.getDouble(10));
                sales.setSgst(rs.getDouble(11));
                sales.setCgst(rs.getDouble(12));
                sales.setGstAt12(rs.getDouble(13));
                sales.setGstAt18(rs.getDouble(14));
                sales.setGstAt30(rs.getDouble(15));
                sales.setCess(rs.getDouble(16));
                sales.setCustomerName(rs.getString(19));
                sales.setGstAt28(rs.getDouble(27));
                sales.setVehicleDetails(rs.getString(30));
                sales.setShippingAddress(rs.getString(31));
                sales.setPreviousBalance(rs.getDouble(32));
                //sales.setCreateTS(rs.getDate(13)); 
                //sales.setUpdateTS(rs.getDate(14));
             String detailSql = "SELECT * from sales_details where sales_id ="+sales.getSalesId();  
             Statement detailStatement = con.createStatement();
             ResultSet rsDetails = detailStatement.executeQuery(detailSql);
             while(rsDetails.next()){
                 salesDetails = new SalesDetails();
                 salesDetails.setSalesDetailsId(rsDetails.getLong(1));
                 salesDetails.setSalesId(rsDetails.getLong(2));
                 salesDetails.setHsnCode(rsDetails.getString(3));
                 salesDetails.setItemName(rsDetails.getString(4));
                 salesDetails.setUnitPrice(rsDetails.getDouble(5));
                 salesDetails.setGstPercentage(rsDetails.getDouble(6));
                 salesDetails.setGstAmount(rsDetails.getDouble(7));
                 salesDetails.setQuantity(rsDetails.getDouble(8));
                 salesDetails.setGrossAmount(rsDetails.getDouble(9));
                 salesDetails.setDiscount(rsDetails.getDouble(10));
                 salesDetails.setNetAmount(rsDetails.getDouble(11));
                 salesDetails.setItemId(rsDetails.getLong(12));
                 salesDetails.setMrp(rsDetails.getDouble(13));
                 salesDetailsList.add(salesDetails);
             }
             sales.setSalesDetails(salesDetailsList);
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sales;
    }
     
    public Sales retrieveBySalesId(long salesId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Sales sales= null;
        SalesDetails salesDetails = null;
        List<SalesDetails> salesDetailsList = new ArrayList<>();
        try {
            sql = "SELECT * from sales where sales_id=" + salesId;
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                sales = new Sales();
                sales.setSalesId(rs.getLong(1));
                sales.setCustomerId(rs.getLong(2));
                sales.setBillNumber(rs.getString(3));
                sales.setBillAmount(rs.getDouble(4));
                sales.setDiscount(rs.getDouble(5));                 
                sales.setPayableAmount(rs.getLong(6));
                sales.setStatus(rs.getString(7)); 
                sales.setBalanceAmount(rs.getDouble(8)); 
               
                sales.setSalesDate(rs.getDate(9));
                sales.setTotalGst(rs.getDouble(10));
                sales.setSgst(rs.getDouble(11));
                sales.setCgst(rs.getDouble(12));
                sales.setGstAt12(rs.getDouble(13));
                sales.setGstAt18(rs.getDouble(14));
                sales.setGstAt30(rs.getDouble(15));
                sales.setCess(rs.getDouble(16));
                sales.setCustomerName(rs.getString(19));
                sales.setGstAt28(rs.getDouble(27));
                sales.setVehicleDetails(rs.getString(30));
                sales.setShippingAddress(rs.getString(31));
                sales.setPreviousBalance(rs.getDouble(32));
                //sales.setCreateTS(rs.getDate(13)); 
                //sales.setUpdateTS(rs.getDate(14));
             String detailSql = "SELECT * from sales_details where sales_id ="+sales.getSalesId();  
             Statement detailStatement = con.createStatement();
             ResultSet rsDetails = detailStatement.executeQuery(detailSql);
             while(rsDetails.next()){
                 salesDetails = new SalesDetails();
                 salesDetails.setSalesDetailsId(rsDetails.getLong(1));
                 salesDetails.setSalesId(rsDetails.getLong(2));
                 salesDetails.setHsnCode(rsDetails.getString(3));
                 salesDetails.setItemName(rsDetails.getString(4));
                 salesDetails.setUnitPrice(rsDetails.getDouble(5));
                 salesDetails.setGstPercentage(rsDetails.getDouble(6));
                 salesDetails.setGstAmount(rsDetails.getDouble(7));
                 salesDetails.setQuantity(rsDetails.getDouble(8));
                 salesDetails.setGrossAmount(rsDetails.getDouble(9));
                 salesDetails.setDiscount(rsDetails.getDouble(10));
                 salesDetails.setNetAmount(rsDetails.getDouble(11));
                 salesDetails.setItemId(rsDetails.getLong(12));
                 salesDetails.setMrp(rsDetails.getDouble(13));
                 salesDetailsList.add(salesDetails);
             }
             sales.setSalesDetails(salesDetailsList);
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sales;
    }
    
    public void insertIntoDB(Sales sales, boolean trackActivity) {
        try {            
            String sql = "INSERT INTO sales (customer_id,bill_number, bill_amount"
                    + ", discount, payable_amount, status, balance_amount, sales_date"
                    + ", total_gst, sgst, cgst, gst_12, gst_18, gst_30,cess, create_ts, update_ts"
                    + ", customer_name, amount_in_words, round_off, taxable_at_5, taxable_at_12"
                    + ", taxable_at_18, taxable_at_0, taxable_at_28, gst_28, bill_type, bill_seq_number, vehicle_details, shipping_address, previous_balance)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("---"+sales.getSalesDate().getTime());
            java.sql.Date salesDate = new java.sql.Date( sales.getSalesDate().getTime() );
            java.sql.Date createTS = new java.sql.Date( sales.getCreateTS().getTime() );
            java.sql.Date updateTS = new java.sql.Date( sales.getUpdateTS().getTime() );
            //java.sql.Date updateTS = new java.sql.Date( sales.getSalesDate().getTime() );
            ps.setLong(1, sales.getCustomerId());
            ps.setString(2, sales.getBillNumber());
            ps.setDouble(3, sales.getBillAmount());
            ps.setDouble(4, sales.getDiscount());
            ps.setDouble(5, sales.getPayableAmount());
            ps.setString(6, sales.getStatus());
            ps.setDouble(7, sales.getBalanceAmount());
            ps.setDate(8, salesDate);
            ps.setDouble(9, sales.getTotalGst());            
            ps.setDouble(10, sales.getSgst());
            ps.setDouble(11, sales.getCgst());
            ps.setDouble(12, sales.getGstAt12());            
            ps.setDouble(13, sales.getGstAt18());
            ps.setDouble(14, sales.getGstAt30());
            System.out.println("----Saving Cess"+sales.getCess());
            ps.setDouble(15, sales.getCess());
            ps.setDate(16, createTS);
            ps.setString(17, sales.getCustomerName());
            ps.setString(18, sales.getAmountInWords());
            ps.setDouble(19, sales.getRoundOff());
            ps.setDouble(20, sales.getTaxableAt5());
            ps.setDouble(21, sales.getTaxableAt12());
            ps.setDouble(22, sales.getTaxableAt18());
            ps.setDouble(23, sales.getTaxableAt0());
            ps.setDouble(24, sales.getTaxableAt28());
            ps.setDouble(25, sales.getGstAt28());
            ps.setString(26, sales.getBillType());
            ps.setLong(27, sales.getBillSeqNumber());
            ps.setString(28, sales.getVehicleDetails());
            ps.setString(29, sales.getShippingAddress());
            ps.setDouble(30, sales.getPreviousBalance());
            //ps.setDate(17, updateTS);
            ps.executeUpdate();
                        
            //SalesDetails salesDetails = new SalesDetails();
            List<SalesDetails> salesDetailsList = sales.getSalesDetails();
            PreparedStatement detailStatement = null;
            String detailSql = "INSERT INTO sales_details (sales_id,hsn_code, item_name"
                    + ", unit_price, gst_percentage, gst_amount, quantity, gross_amount"
                    + ", discount, net_amount, item_id, mrp)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
            detailStatement = con.prepareStatement(detailSql);
            SalesDAO salesDAO =  new SalesDAO();
            long ongoingSalesId = salesDAO.getOngoingSalesId(sales.getBillNumber());            
            for (SalesDetails salesDetail : salesDetailsList) {
                
                detailStatement.setLong(1, ongoingSalesId);
                detailStatement.setString(2, salesDetail.getHsnCode());
                detailStatement.setString(3, salesDetail.getItemName());
                detailStatement.setDouble(4, salesDetail.getUnitPrice());
                detailStatement.setDouble(5, salesDetail.getGstPercentage());
                detailStatement.setDouble(6, salesDetail.getGstAmount());
                detailStatement.setDouble(7, salesDetail.getQuantity());
                detailStatement.setDouble(8, salesDetail.getGrossAmount());
                detailStatement.setDouble(9, salesDetail.getDiscount());
                detailStatement.setDouble(10, salesDetail.getNetAmount());
                detailStatement.setLong(11, salesDetail.getItemId()); 
                detailStatement.setDouble(12, salesDetail.getMrp());
                detailStatement.addBatch();
            }
          
            int[] results = detailStatement.executeBatch();
            System.out.println("Size : "+salesDetailsList.size()+"| Final Details Statement :"+detailStatement.toString());
            updateStockAfterSales(sales,salesDetailsList, trackActivity);
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertMoreLineItemsToDB(Sales sales, boolean trackActivity) {
        try {            

            //SalesDetails salesDetails = new SalesDetails();
            List<SalesDetails> salesDetailsList = sales.getSalesDetails();
            PreparedStatement detailStatement = null;
            String detailSql = "INSERT INTO sales_details (sales_id,hsn_code, item_name"
                    + ", unit_price, gst_percentage, gst_amount, quantity, gross_amount"
                    + ", discount, net_amount, item_id, mrp)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
            SalesDAO salesDAO =  new SalesDAO();
            long ongoingSalesId = salesDAO.getOngoingSalesId(sales.getBillNumber());
            detailStatement = con.prepareStatement(detailSql);
            for (SalesDetails salesDetails:salesDetailsList){
                //detailStatement = con.prepareStatement(detailSql);
                //salesDetails = salesDetailsList.get(i);
                
                //Getting the sales id with Bill Number
                
                detailStatement.setLong(1, ongoingSalesId);
                detailStatement.setString(2, salesDetails.getHsnCode());
                detailStatement.setString(3, salesDetails.getItemName());
                detailStatement.setDouble(4, salesDetails.getUnitPrice());
                detailStatement.setDouble(5, salesDetails.getGstPercentage());
                detailStatement.setDouble(6, salesDetails.getGstAmount());
                detailStatement.setDouble(7, salesDetails.getQuantity());
                detailStatement.setDouble(8, salesDetails.getGrossAmount());
                detailStatement.setDouble(9, salesDetails.getDiscount());
                detailStatement.setDouble(10, salesDetails.getNetAmount());
                detailStatement.setLong(11, salesDetails.getItemId()); 
                detailStatement.setDouble(12, salesDetails.getMrp());
                detailStatement.addBatch();
                //detailStatement.clearParameters();
                //detailStatement.executeUpdate();                
                
            }
            int[] results = detailStatement.executeBatch();
            System.out.println("More Size : "+salesDetailsList.size()+"| Final Details Statement :"+detailStatement.toString());
            updateStockAfterSales(sales,salesDetailsList,trackActivity);
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateSalesAfterReturn(Sales sales, List<SalesReturn> returnList, boolean trackActivity) {
        try {
            //ConnectionManager localCM = new ConnectionManager();
            //Connection localCon = cm.getConnection();
            String sql = "UPDATE sales SET bill_amount =?"
                    + ", discount =?, payable_amount=?, status =?, balance_amount =?"
                    + ", total_gst =?, sgst =?, cgst=?, gst_12=?, gst_18=?, gst_30=?"
                    + ", cess =?, sales_date=? , amount_in_words=?, round_off =?, taxable_at_5 =?"
                    + ", taxable_at_12 =?, taxable_at_18 =?, taxable_at_0 =?, taxable_at_28 =?"
                    + ", gst_28 =?, customer_name = ?, vehicle_details = ?, shipping_address = ?, update_ts=NOW() "
                    + " WHERE bill_number = ?"
                    ;
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("---"+sales.getSalesDate().getTime());
            //java.sql.Date salesDate = new java.sql.Date( sales.getSalesDate().getTime() );
            //java.sql.Date createTS = new java.sql.Date( sales.getCreateTS().getTime() );
            //java.sql.Date updateTS = new java.sql.Date( sales.getUpdateTS().getTime() );
            //java.sql.Date updateTS = new java.sql.Date( sales.getSalesDate().getTime() );
            java.sql.Date salesDate = new java.sql.Date( sales.getSalesDate().getTime() );
            String sPayableAmount = Math.round(sales.getPayableAmount())+"";
            ps.setDouble(1, sales.getBillAmount());
            ps.setDouble(2, sales.getDiscount());
            ps.setDouble(3, sales.getPayableAmount());
            ps.setString(4, sales.getStatus());
            ps.setDouble(5, sales.getBalanceAmount());
            //ps.setDate(6, salesDate);
            ps.setDouble(6, sales.getTotalGst());            
            ps.setDouble(7, sales.getSgst());
            ps.setDouble(8, sales.getCgst());
            ps.setDouble(9, sales.getGstAt12());            
            ps.setDouble(10, sales.getGstAt18());
            ps.setDouble(11, sales.getGstAt30());
            ps.setDouble(12, sales.getCess());
            ps.setDate(13, salesDate);
            ps.setString(14, "Rupees "+GenericUtils.numberToWord(Integer.parseInt(sPayableAmount))+" Only");
            ps.setDouble(15, sales.getRoundOff());
            ps.setDouble(16, sales.getTaxableAt5());
            ps.setDouble(17, sales.getTaxableAt12());
            ps.setDouble(18, sales.getTaxableAt18());
            ps.setDouble(19, sales.getTaxableAt0());
            ps.setDouble(20, sales.getTaxableAt28());
            ps.setDouble(21, sales.getGstAt28());
            ps.setString(22, sales.getCustomerName());
            ps.setString(23, sales.getVehicleDetails());
            ps.setString(24, sales.getShippingAddress());
            ps.setString(25, sales.getBillNumber());

            System.out.println("----Updating Sales = "+sales.getBillNumber());
            System.out.println("----Updating Query = "+ps.toString());
            System.out.println("----Sales Details Size  = "+sales.getSalesDetails().size());
            //ps.setDouble(15, sales.getCess());
            //ps.setDate(16, createTS);
            //ps.setString(17, sales.getCustomerName());
            //ps.setDate(17, updateTS);
            ps.executeUpdate();
            
            //More Line Item on top of existing Bill Logic
            PreparedStatement detailStatement = null;
            String detailSql = "INSERT INTO sales_details (sales_id,hsn_code, item_name"
                    + ", unit_price, gst_percentage, gst_amount, quantity, gross_amount"
                    + ", discount, net_amount, item_id, mrp)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
            List<SalesDetails> salesDetailsList = sales.getSalesDetails();
            //SalesDetails salesDetails = null;
            SalesDAO salesDAO =  new SalesDAO();
            long ongoingSalesId = salesDAO.getOngoingSalesId(sales.getBillNumber());
            System.out.println("Bill Number >> "+sales.getBillNumber());
            //System.out.println("Saled Id >> "+salesDAO.getOngoingSalesId(sales.getBillNumber()));
            detailStatement = con.prepareStatement(detailSql);
            for (SalesDetails salesDetails : salesDetailsList){
                
                //Getting the sales id with Bill Number
                detailStatement.setLong(1, ongoingSalesId);
                detailStatement.setString(2, salesDetails.getHsnCode());
                detailStatement.setString(3, salesDetails.getItemName());
                detailStatement.setDouble(4, salesDetails.getUnitPrice());
                detailStatement.setDouble(5, salesDetails.getGstPercentage());
                detailStatement.setDouble(6, salesDetails.getGstAmount());
                detailStatement.setDouble(7, salesDetails.getQuantity());
                detailStatement.setDouble(8, salesDetails.getGrossAmount());
                detailStatement.setDouble(9, salesDetails.getDiscount());
                detailStatement.setDouble(10, salesDetails.getNetAmount());
                detailStatement.setLong(11, salesDetails.getItemId());
                detailStatement.setDouble(12, salesDetails.getMrp());
                System.out.println("!@#==========="+detailStatement.toString());
                detailStatement.addBatch();
                //detailStatement.clearParameters();
                //detailStatement.executeUpdate();               
                
            }
            int[] results = detailStatement.executeBatch();
            updateStockAfterSales(sales,salesDetailsList, trackActivity);
                        
            SalesReturn salesReturn = new SalesReturn();
            //List<SalesDetails> salesDetailsList = sales.getSalesDetails();
            PreparedStatement deleteStatement = null;
            String deleteSql = "DELETE FROM sales_details where sales_details_id =?  ";  
            deleteStatement = con.prepareStatement(deleteSql);
            for (int i=0; i<returnList.size(); i++){                
                salesReturn = returnList.get(i);
                deleteStatement.setLong(1, salesReturn.getSalesDetailsId());                
                deleteStatement.executeUpdate();                
                
            }
            updateStockAfterSalesReturn(sales, returnList, trackActivity);
            //con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStockAfterSalesReturn(Sales sales,List<SalesReturn> returnList, boolean trackActivity) {
        try {
            //ConnectionManager cm = new ConnectionManager();
            Connection co = cm.getConnection();
            String stSql = "UPDATE stock SET quantity = quantity+?, update_ts =NOW() WHERE item_id = ? ";
            PreparedStatement ps = co.prepareStatement(stSql);        
            for(int i=0;i<returnList.size();i++){                
                ps.setDouble(1, returnList.get(i).getQuantity());
                ps.setLong(2, returnList.get(i).getItemId());
                ps.addBatch();
                //ps.executeUpdate();
            }
            ps.executeBatch();
            /*if(trackActivity){
                String auditSql = "INSERT INTO activity_log (description, created_by) values (?,?)";
                PreparedStatement psAudit = co.prepareStatement(auditSql);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for(int i=0;i<returnList.size();i++){                
                    psAudit.setString(1, "Sales Returned "+returnList.get(i).getQuantity()+" unit of " + returnList.get(i).getItemName()+" with Bill Number-"+sales.getBillNumber()+" and Sales Date-"+sdf.format(sales.getSalesDate()));
                    psAudit.setString(2, "Sales Return");
                    psAudit.executeUpdate();
                }
            }*/
            
            co.close();
            //con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStockAfterBillDeletion(Sales sales,List<SalesDetails> deletedList, boolean trackActivity) {
        try {
            //ConnectionManager cm = new ConnectionManager();
            //Connection co = cm.getConnection();
            String stSql = "UPDATE stock SET quantity = quantity+?, update_ts =NOW() WHERE item_id = ? ";
            PreparedStatement ps = con.prepareStatement(stSql);        
            for(int i=0;i<deletedList.size();i++){                
                ps.setDouble(1, deletedList.get(i).getQuantity());
                ps.setLong(2, deletedList.get(i).getItemId());
                System.out.println("stock update :"+ ps.toString());
                ps.executeUpdate();
            }
            /*if(trackActivity){
                String auditSql = "INSERT INTO activity_log (description, created_by) values (?,?)";
                PreparedStatement psAudit = co.prepareStatement(auditSql);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for(int i=0;i<deletedList.size();i++){                
                    psAudit.setString(1, "Returned "+deletedList.get(i).getQuantity()+" unit of " + deletedList.get(i).getItemName()+" with Bill Number-"+sales.getBillNumber()+" and Sales Date-"+sdf.format(sales.getSalesDate()));
                    psAudit.setString(2, "Bill Delete");
                    psAudit.executeUpdate();
                }
            }*/
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateStockAfterSales(Sales sales,List<SalesDetails> salesDetailsList, boolean trackActivity) {
        try {
            //ConnectionManager cm = new ConnectionManager();
            //Connection co = cm.getConnection();
            String stSql = "UPDATE stock SET quantity = quantity-? WHERE item_id = ? ";
            PreparedStatement ps = con.prepareStatement(stSql);        
            for(SalesDetails salesDetails : salesDetailsList){                
                ps.setDouble(1, salesDetails.getQuantity());
                ps.setLong(2, salesDetails.getItemId());
                ps.addBatch();
                //ps.clearParameters();
                //ps.executeUpdate();
            }
            int[] results = ps.executeBatch();
            /*if(trackActivity){
                String auditSql = "INSERT INTO activity_log (description, created_by) values (?,?)";
                PreparedStatement psAudit = co.prepareStatement(auditSql);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for(int i=0;i<salesDetails.size();i++){                
                    psAudit.setString(1, "Sold "+salesDetails.get(i).getQuantity()+" unit of " + salesDetails.get(i).getItemName() +" on " +sdf.format(sales.getSalesDate()) +" Bill Number-"+sales.getBillNumber());
                    psAudit.setString(2, "Sales");
                    psAudit.executeUpdate();
                    psAudit.addBatch();
                    psAudit.clearParameters();
                }
                int[] auditCount = psAudit.executeBatch();
            }*/
            //con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSalesByBillNumber(String billNum) {
        try {            
            String sql = "DELETE FROM sales WHERE bill_number = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, billNum);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteSalesBySalesId(long salesId) {
        try {            
            String sql = "DELETE FROM sales WHERE sales_id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, salesId);
            ps.executeUpdate();
            System.out.println("SALES DELETE "+ps.toString());
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
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
