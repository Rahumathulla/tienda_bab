/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import com.essar.pojos.Customer;
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
public class CustomerDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<Customer> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Customer>  customerList= null;
        try {
            sql = "SELECT * from customer";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            Customer customer = null;
            customerList = new ArrayList<Customer>();
            while(rs.next()){
                customer = new Customer();
                customer.setCustomerId(rs.getLong(1));
                customer.setCustomerName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setAddress(rs.getString(4));  
                
                //Adding to List
                customerList.add(customer);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customerList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Customer retrieveById(long id) {
        Customer  customer= null;
        try {
            sql = "SELECT * from customer where customer_id = " + id;
            Statement st = con.createStatement();            
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, id);
            rs = st.executeQuery(sql);
            while(rs.next()){
                customer = new Customer();
                customer.setCustomerId(rs.getLong(1));
                customer.setCustomerName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setAddress(rs.getString(4));                                                       
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customer;
    }

    public void insertIntoDB(Customer customer) {
        try {            
            String sql = "INSERT INTO customer (customer_name,customer_phone,address)  values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getAddress());
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
            String sql = "DELETE FROM customer WHERE customer_id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void updateRecord(Customer customer) {
        try {            
            String sql = "UPDATE customer set customer_name = ? "
                    + ", customer_phone=? ,address=?  WHERE customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getAddress());
            ps.setLong(4, customer.getCustomerId());
            ps.executeUpdate();
            System.out.println("--::"+ps.toString());
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
