/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

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
public class SupplierDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<Supplier> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Supplier>  supplierList= null;
        try {
            sql = "SELECT * from supplier";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            Supplier supplier = null;
            supplierList = new ArrayList<Supplier>();
            while(rs.next()){
                supplier = new Supplier();
                supplier.setSupplierId(rs.getLong(1));
                supplier.setSupplierCode(rs.getString(2));
                supplier.setSupplierName(rs.getString(3));
                supplier.setSupplierContactNumber(rs.getString(4));                 
                supplier.setSupplierAddress(rs.getString(5));
                supplier.setSupplierRating(rs.getString(6)); 
                //Adding to List
                supplierList.add(supplier);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return supplierList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Supplier retrieveById(long id) {
        Supplier  supplier= null;
        try {
            sql = "SELECT * from supplier where supplier_id = " + id;
            Statement st = con.createStatement();            
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, id);
            rs = st.executeQuery(sql);
            while(rs.next()){
                supplier = new Supplier();
                supplier.setSupplierId(rs.getLong(1));
                supplier.setSupplierCode(rs.getString(2));
                supplier.setSupplierName(rs.getString(3));
                supplier.setSupplierContactNumber(rs.getString(4));                 
                supplier.setSupplierAddress(rs.getString(5));
                supplier.setSupplierRating(rs.getString(6));                                       
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return supplier;
    }

    public void insertIntoDB(Supplier supplier) {
        try {            
            String sql = "INSERT INTO supplier (supplier_code,supplier_name, supplier_contact_number"
                    + ", supplier_rating, supplier_address)  values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, supplier.getSupplierCode());
            ps.setString(2, supplier.getSupplierName());
            ps.setString(3, supplier.getSupplierContactNumber());
            ps.setString(4, supplier.getSupplierRating());
            ps.setString(5, supplier.getSupplierAddress());
            ps.executeUpdate();
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void deleteRecordById(long id) {
        try {            
            String sql = "DELETE FROM supplier WHERE supplier_id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRecord(Supplier supplier) {
        try {            
            String sql = "UPDATE supplier set supplier_code=?, supplier_name=?"
                    + ", supplier_contact_number=?"
                    + ", supplier_rating=?, supplier_address=?  WHERE supplier_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("--::"+sql);
            ps.setString(1, supplier.getSupplierCode());
            ps.setString(2, supplier.getSupplierName());
            ps.setString(3, supplier.getSupplierContactNumber());
            ps.setString(4, supplier.getSupplierRating());
            ps.setString(5, supplier.getSupplierAddress());
            ps.setLong(6, supplier.getSupplierId());
            ps.executeUpdate();
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
