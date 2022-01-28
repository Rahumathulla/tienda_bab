/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

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
public class PricingTierDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<PricingTier> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<PricingTier>  pricingTierList= null;
        try {
            sql = "SELECT * from pricing_tier";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            PricingTier pricingTier = null;
            pricingTierList = new ArrayList<PricingTier>();
            while(rs.next()){
                pricingTier = new PricingTier();
                pricingTier.setId(rs.getLong(1));
                pricingTier.setCustomerTypeName(rs.getString(2));
                pricingTier.setPricingPercentage(rs.getDouble(3));
                pricingTier.setDescription(rs.getString(4));  
                
                //Adding to List
                pricingTierList.add(pricingTier);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pricingTierList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PricingTier retrieveById(long id) {
        PricingTier  pricingTier= null;
        try {
            sql = "SELECT * from pricing_tier where id = " + id;
            Statement st = con.createStatement();            
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, id);
            rs = st.executeQuery(sql);
            while(rs.next()){
                pricingTier = new PricingTier();
                pricingTier.setId(rs.getLong(1));
                pricingTier.setCustomerTypeName(rs.getString(2));
                pricingTier.setPricingPercentage(rs.getDouble(3));
                pricingTier.setDescription(rs.getString(4));                                                      
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pricingTier;
    }

    public void insertIntoDB(PricingTier pricingTier) {
        try {            
            String sql = "INSERT INTO pricing_tier (customer_type_name,pricing_percentage,description)  values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pricingTier.getCustomerTypeName());
            ps.setDouble(2, pricingTier.getPricingPercentage());
            ps.setString(3, pricingTier.getDescription());
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
            String sql = "DELETE FROM pricing_tier WHERE id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void updateRecord(PricingTier pricingTier) {
        try {            
            String sql = "UPDATE pricing_tier set customer_type_name = ? "
                    + ", pricing_percentage=? ,description=?  WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, pricingTier.getCustomerTypeName());
            ps.setDouble(2, pricingTier.getPricingPercentage());
            ps.setString(3, pricingTier.getDescription());
            ps.setLong(4, pricingTier.getId());
            ps.executeUpdate();
            System.out.println("--::"+ps.toString());
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
