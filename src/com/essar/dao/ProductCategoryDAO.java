/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import com.essar.utils.ConnectionManager;
import com.essar.pojos.ProductCategory;
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
public class ProductCategoryDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<ProductCategory> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<ProductCategory>  productCategoryList= null;
        try {
            sql = "SELECT * from product_category";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            ProductCategory productCategory = null;
            productCategoryList = new ArrayList<ProductCategory>();
            while(rs.next()){
                productCategory = new ProductCategory();
                productCategory.setProductCategoryId(rs.getLong(1));
                productCategory.setProductCategoryCode(rs.getString(2));
                productCategory.setProductCategoryName(rs.getString(3));              
                
                //Adding to List
                productCategoryList.add(productCategory);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productCategoryList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ProductCategory retrieveById(long id) {
        ProductCategory  productCategory= null;
        try {
            sql = "SELECT * from product_category where product_category_id = " + id;
            Statement st = con.createStatement();            
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, id);
            rs = st.executeQuery(sql);
            while(rs.next()){
                productCategory = new ProductCategory();
                productCategory.setProductCategoryId(rs.getLong(1));
                productCategory.setProductCategoryCode(rs.getString(2));
                productCategory.setProductCategoryName(rs.getString(3));                                    
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productCategory;
    }


    public void insertIntoDB(ProductCategory productCategory) {
        try {            
            String sql = "INSERT INTO product_category (product_category_code,product_category_name)  values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productCategory.getProductCategoryCode());
            ps.setString(2, productCategory.getProductCategoryName());
            ps.executeUpdate();
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateProductCategory(ProductCategory productCategory) {
        try {            
            String sql = "UPDATE product_category SET product_category_code= ?, product_category_name = ?"
                    + " WHERE product_category_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productCategory.getProductCategoryCode());
            ps.setString(2, productCategory.getProductCategoryName());
            ps.setLong(3, productCategory.getProductCategoryId());
            ps.executeUpdate();
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void deleteRecordByCode(String code) {
        try {            
            String sql = "DELETE FROM product_category WHERE product_category_code = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRecord(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
