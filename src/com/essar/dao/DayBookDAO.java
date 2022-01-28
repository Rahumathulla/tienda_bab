/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import com.essar.pojos.Customer;
import com.essar.pojos.DayBook;
import com.essar.pojos.IncomeExpense;
import com.essar.pojos.PricingTier;
import com.essar.utils.ConnectionManager;
import com.essar.pojos.ProductCategory;
import com.essar.pojos.Supplier;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahumathulla
 */
public class DayBookDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<DayBook> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<DayBook>  dayBookList= null;
        try {
            sql = "SELECT * from day_book order by event_date";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            DayBook dayBook = null;
            dayBookList = new ArrayList<DayBook>();
            while(rs.next()){
                dayBook = new DayBook();
                dayBook.setDayBookId(rs.getLong(1));
                dayBook.setIncomeExpenseId(rs.getLong(2));
                dayBook.setEventDate(rs.getDate(3));
                dayBook.setParticularName(rs.getString(4));  
                dayBook.setType(rs.getString(5));
                dayBook.setCreditAmount(rs.getDouble(6));
                dayBook.setDebitAmount(rs.getDouble(7));
                dayBook.setRemarks(rs.getString(8));
                dayBook.setCreateTS(rs.getTimestamp(9));
                dayBook.setUpdateTS(rs.getTimestamp(10));
                //Adding to List
                dayBookList.add(dayBook);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dayBookList;
    }
    
    public List<DayBook> retrieveDayBookEntriesInDateRange(java.util.Date stDate, java.util.Date endDate) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<DayBook>  dayBookList= null;
        String sqlPattern = "yyyy-MM-dd";
        SimpleDateFormat sqlDateFormat = new SimpleDateFormat(sqlPattern);
        String fromDate = sqlDateFormat.format(stDate);
        String toDate = sqlDateFormat.format(endDate);
        try {
            sql = "SELECT day_book_id, income_expense_id, event_date, particular_name"
                    + " ,type, credit_amount, debit_amount, remarks, create_ts, update_ts from day_book WHERE event_date BETWEEN "
                    + "'"+fromDate +" 00:00:00' AND '"+toDate+ " 23:59:59'" + "  #order by event_date \n"
                    + " UNION SELECT 11111, 22222,'"+toDate+"', 'Sales in the Period','Credit' "
                        + ", ROUND(SUM(payable_amount),2), 0, '', '2020-01-01 00:00:00', '2020-01-01 00:00:00' "
                        + " FROM sales WHERE sales_date BETWEEN"
                        + " '"+fromDate +" 00:00:00' AND '"+toDate+ " 23:59:59'"
                    + " UNION SELECT 333333, 555555,'"+toDate+"', 'Purchase in the Period','Debit' "
                        + ", 0, ROUND(SUM(payable_amount),2), '', '2020-01-01 00:00:00', '2020-01-01 00:00:00' "
                        + " FROM purchase WHERE purchase_date BETWEEN"
                        + " '"+fromDate +" 00:00:00' AND '"+toDate+ " 23:59:59'"
            
                    + " UNION SELECT 666666, 77777,'"+toDate+"', 'Closing Stock','Credit' "
                        + ", ROUND(SUM((purchase_price+(purchase_price*gst_percentage*0.01))*quantity),2), 0, '', '2020-01-01 00:00:00', '2020-01-01 00:00:00' "
                        + " FROM stock WHERE quantity >= 0";
            System.out.println("SQL :: "+sql);
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            DayBook dayBook = null;
            dayBookList = new ArrayList<DayBook>();
            while(rs.next()){
                dayBook = new DayBook();
                dayBook.setDayBookId(rs.getLong(1));
                dayBook.setIncomeExpenseId(rs.getLong(2));
                dayBook.setEventDate(rs.getDate(3));
                dayBook.setParticularName(rs.getString(4));  
                dayBook.setType(rs.getString(5));
                dayBook.setCreditAmount(rs.getDouble(6));
                dayBook.setDebitAmount(rs.getDouble(7));
                dayBook.setRemarks(rs.getString(8));
                dayBook.setCreateTS(rs.getTimestamp(9));
                dayBook.setUpdateTS(rs.getTimestamp(10));
                //Adding to List
                dayBookList.add(dayBook);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dayBookList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DayBook retrieveById(long id) {
        DayBook  dayBook= null;
        try {
            sql = "SELECT * from day_book where day_book_id = " + id;
            Statement st = con.createStatement();            
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, id);
            rs = st.executeQuery(sql);
            while(rs.next()){
                dayBook = new DayBook();
                dayBook.setDayBookId(rs.getLong(1));
                dayBook.setIncomeExpenseId(rs.getLong(2));
                dayBook.setEventDate(rs.getDate(3));
                dayBook.setParticularName(rs.getString(4));  
                dayBook.setType(rs.getString(5));
                dayBook.setCreditAmount(rs.getDouble(6));
                dayBook.setDebitAmount(rs.getDouble(7));
                dayBook.setRemarks(rs.getString(8));
                dayBook.setCreateTS(rs.getTimestamp(9));
                dayBook.setUpdateTS(rs.getTimestamp(10));                                                      
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dayBook;
    }

    public void insertIntoDB(DayBook dayBook) {
        try {            
            String sql = "INSERT INTO day_book (income_expense_id, event_date, particular_name "
                    + ", type, credit_amount, debit_amount, remarks, create_ts)"
                    + "  values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.Date eventDate = new java.sql.Date( dayBook.getEventDate().getTime());
            java.sql.Date createTS = new java.sql.Date( Calendar.getInstance().getTime().getTime() );
            //java.sql.Date updateTS = new java.sql.Date( Calendar.getInstance().getTime().getTime() );
            ps.setLong(1, dayBook.getIncomeExpenseId());
            ps.setDate(2, eventDate);
            ps.setString(3, dayBook.getParticularName());
            ps.setString(4, dayBook.getType());
            ps.setDouble(5, dayBook.getCreditAmount());
            ps.setDouble(6, dayBook.getDebitAmount());
            ps.setString(7, dayBook.getRemarks());
            ps.setDate(8, createTS);
            //ps.setTimestamp(9, dayBook.getCreateTS().getTime());
            ps.executeUpdate();
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void deleteRecordById(Long id) {
        try {            
            String sql = "DELETE FROM day_book WHERE day_book_id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void updateRecord(DayBook dayBook) {
        try {            
             String sql = "UPDATE day_book SET income_expense_id = ?, event_date = ?, particular_name =?"
                    + ", type=?, credit_amount=?, debit_amount=?, remarks=?, update_ts=?"
                    + "  WHERE day_book_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.Date eventDate = new java.sql.Date( dayBook.getEventDate().getTime());
            //java.sql.Date createTS = new java.sql.Date( Calendar.getInstance().getTime().getTime() );
            java.sql.Date updateTS = new java.sql.Date( Calendar.getInstance().getTime().getTime() );
            ps.setLong(1, dayBook.getIncomeExpenseId());
            ps.setDate(2, eventDate);
            ps.setString(3, dayBook.getParticularName());
            ps.setString(4, dayBook.getType());
            ps.setDouble(5, dayBook.getCreditAmount());
            ps.setDouble(6, dayBook.getDebitAmount());
            ps.setString(7, dayBook.getRemarks());
            ps.setDate(8, updateTS);
            ps.setLong(9, dayBook.getDayBookId());
            //ps.setTimestamp(9, dayBook.getCreateTS().getTime());
            ps.executeUpdate();
            con.close();                      
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
