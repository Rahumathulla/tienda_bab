/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.utils;

/**
 *
 * @author rahumathulla
 */
public class QueryStrings {
    public static String stockQuery = "SELECT * FROM stock";
    public static String salesQuery = "SELECT * FROM sales order by sales_id";
    public static String purchaseQuery = "SELECT * FROM purchase";
    public static String customerQuery = "SELECT * FROM customer";
    public static String supplierQuery = "SELECT * FROM supplier";
    public static String viewSalesSql =  "SELECT s.sales_id"
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
                    + " from sales s "
                    + " JOIN customer cust ON s.customer_id = cust.customer_id"
                    + " JOIN sales_details d ON s.sales_id = d.sales_id "
                    + " group by sales_id order by sales_id DESC";
    
    
    public static String viewPurchaseSql =  "SELECT p.purchase_id"
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
                    + " group by purchase_id order by purchase_date DESC"; 
}
