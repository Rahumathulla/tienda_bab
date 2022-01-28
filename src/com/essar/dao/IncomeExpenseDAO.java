/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import com.essar.pojos.Customer;
import com.essar.pojos.IncomeExpense;
import com.essar.pojos.PricingTier;
import com.essar.utils.ConnectionManager;
import com.essar.pojos.ProductCategory;
import com.essar.pojos.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahumathulla
 */
public class IncomeExpenseDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<IncomeExpense> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<IncomeExpense>  incomeExpenseList= null;
        try {
            sql = "SELECT * from income_expense_line";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            IncomeExpense incomeExpense = null;
            incomeExpenseList = new ArrayList<IncomeExpense>();
            while(rs.next()){
                incomeExpense = new IncomeExpense();
                incomeExpense.setIncomeExpenseId(rs.getLong(1));
                incomeExpense.setIncomeExpenseName(rs.getString(2));
                incomeExpense.setType(rs.getString(3));
                incomeExpense.setRemarks(rs.getString(4));  
                
                //Adding to List
                incomeExpenseList.add(incomeExpense);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return incomeExpenseList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public IncomeExpense retrieveById(long id) {
        IncomeExpense  incomeExpense= null;
        try {
            sql = "SELECT * from income_expense_line where income_expense_id = " + id;
            Statement st = con.createStatement();            
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, id);
            rs = st.executeQuery(sql);
            while(rs.next()){
                incomeExpense = new IncomeExpense();
                incomeExpense.setIncomeExpenseId(rs.getLong(1));
                incomeExpense.setIncomeExpenseName(rs.getString(2));
                incomeExpense.setType(rs.getString(3));
                incomeExpense.setRemarks(rs.getString(4));                                                      
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return incomeExpense;
    }

    public void insertIntoDB(IncomeExpense incomeExpense) {
        try {            
            String sql = "INSERT INTO income_expense_line (income_expense_name,type,remarks)  values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, incomeExpense.getIncomeExpenseName());
            ps.setString(2, incomeExpense.getType());
            ps.setString(3, incomeExpense.getRemarks());
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
            String sql = "DELETE FROM income_expense_line WHERE income_expense_id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void updateRecord(IncomeExpense incomeExpense) {
        try {            
            String sql = "UPDATE income_expense_line set income_expense_name = ? "
                    + ", type=? ,remarks=?  WHERE income_expense_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, incomeExpense.getIncomeExpenseName());
            ps.setString(2, incomeExpense.getType());
            ps.setString(3, incomeExpense.getRemarks());
            ps.setLong(4, incomeExpense.getIncomeExpenseId());
            ps.executeUpdate();
            System.out.println("--::"+ps.toString());
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
