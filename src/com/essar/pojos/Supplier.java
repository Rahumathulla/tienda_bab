/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.pojos;

/**
 *
 * @author rahumathulla
 */
public class Supplier {
    long supplierId;
    String supplierCode;
    String supplierName;
    String supplierContactNumber;
    String supplierAddress;
    String supplierRating;
    String dateOfFirstDeal;
    String dateOfLastDeal;
    

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierContactNumber() {
        return supplierContactNumber;
    }

    public void setSupplierContactNumber(String supplierContactNumber) {
        this.supplierContactNumber = supplierContactNumber;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierRating() {
        return supplierRating;
    }

    public void setSupplierRating(String supplierRating) {
        this.supplierRating = supplierRating;
    }

    public String getDateOfFirstDeal() {
        return dateOfFirstDeal;
    }

    public void setDateOfFirstDeal(String dateOfFirstDeal) {
        this.dateOfFirstDeal = dateOfFirstDeal;
    }

    public String getDateOfLastDeal() {
        return dateOfLastDeal;
    }

    public void setDateOfLastDeal(String dateOfLastDeal) {
        this.dateOfLastDeal = dateOfLastDeal;
    }

    @Override
    public String toString() { 
        return supplierName; 
    } 
    
}
