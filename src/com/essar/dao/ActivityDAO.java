/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import com.essar.pojos.Activity;
import com.essar.pojos.GST;
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
public class ActivityDAO {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    ResultSet rs = null;
    String sql = null;
    //List<ProductCategory>  productCategoryList= null;
        
    public List<Activity> retrieveAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Activity>  activityList= null;
        try {
            sql = "SELECT * from activity_log order by create_ts DESC limit 500";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            Activity activity = null;
            activityList = new ArrayList<Activity>();
            while(rs.next()){
                activity = new Activity();
                activity.setActivityId(rs.getLong(1));
                activity.setDescription(rs.getString(2));
                activity.setCreatedBy(rs.getString(3));
                activity.setCreateTS(rs.getDate(4));
                
                //Adding to List
                activityList.add(activity);                        
                
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return activityList;
    }

    public List<Object> retrieveByQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public GST retrieveById(long id) {
        GST  gst= null;
        try {
            sql = "SELECT * from gst where gst_id = " + id;
            Statement st = con.createStatement();            
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, id);
            rs = st.executeQuery(sql);
            while(rs.next()){
                gst = new GST();
                gst.setGstId(rs.getLong(1));
                gst.setGstValue(rs.getDouble(2));
                gst.setSgst(rs.getDouble(3));
                gst.setCgst(rs.getDouble(4));
                gst.setDescription(rs.getString(5));                                                    
            }
            con.close();
            //System.out.println("</TABLE></BODY></HTML>"+productCategoryList.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gst;
    }

    public void insertIntoDB(GST gst) {
        try {            
            String sql = "INSERT INTO gst (gst_value, sgst, cgst, description)  values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, gst.getGstValue());
            ps.setDouble(2, gst.getSgst());
            ps.setDouble(3, gst.getCgst());
            ps.setString(4, gst.getDescription());
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
            String sql = "DELETE FROM gst WHERE gst_id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            System.out.println("--"+ps.toString());
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void updateRecord(GST gst) {
        try {            
            String sql = "UPDATE gst set gst_value = ? "
                    + ", sgst=? , cgst=?, description = ?   WHERE gst_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            

            ps.setDouble(1, gst.getGstValue());
            ps.setDouble(2, gst.getSgst());
            ps.setDouble(3, gst.getCgst());
            ps.setString(4, gst.getDescription());
            ps.setLong(5, gst.getGstId());
            ps.executeUpdate();
            System.out.println("--::"+ps.toString());
            con.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
