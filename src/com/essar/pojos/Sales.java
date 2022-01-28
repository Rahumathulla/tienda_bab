/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.pojos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author rahumathulla
 */
public class Sales {
    long salesId;
    long customerId;
    String billNumber;
    double billAmount;
    double discount;
    double payableAmount;
    String status;
    double balanceAmount;
    double totalGst;
    double sgst;
    double cgst;
    double gstAt12;
    double gstAt18;
    double gstAt30;
    double gstAt28;
    double cess;
    Date salesDate;
    Date createTS;
    Date updateTS;
    String customerName;
    String amountInWords;
    double roundOff;
    double taxableAt5;
    double taxableAt12;
    double taxableAt18;
    double taxableAt0;
    double taxableAt28;
    String billType;
    long billSeqNumber;
    String vehicleDetails;
    String shippingAddress;
    double previousBalance;
    
    List<SalesDetails> salesDetails;

    public long getSalesId() {
        return salesId;
    }

    public void setSalesId(long salesId) {
        this.salesId = salesId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getTotalGst() {
        return totalGst;
    }

    public void setTotalGst(double totalGst) {
        this.totalGst = totalGst;
    }

    public double getSgst() {
        return sgst;
    }

    public void setSgst(double sgst) {
        this.sgst = sgst;
    }

    public double getCgst() {
        return cgst;
    }

    public void setCgst(double cgst) {
        this.cgst = cgst;
    }

    public double getGstAt12() {
        return gstAt12;
    }

    public void setGstAt12(double gstAt12) {
        this.gstAt12 = gstAt12;
    }

    public double getGstAt18() {
        return gstAt18;
    }

    public void setGstAt18(double gstAt18) {
        this.gstAt18 = gstAt18;
    }

    public double getGstAt30() {
        return gstAt30;
    }

    public void setGstAt30(double gstAt30) {
        this.gstAt30 = gstAt30;
    }

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }
        
    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public Date getCreateTS() {
        return createTS;
    }

    public void setCreateTS(Date createTS) {
        this.createTS = createTS;
    }

    public Date getUpdateTS() {
        return updateTS;
    }

    public void setUpdateTS(Date updateTS) {
        this.updateTS = updateTS;
    }

    public List<SalesDetails> getSalesDetails() {
        return salesDetails;
    }

    public void setSalesDetails(List<SalesDetails> salesDetails) {
        this.salesDetails = salesDetails;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public double getRoundOff() {
        return roundOff;
    }

    public void setRoundOff(double roundOff) {
        this.roundOff = roundOff;
    }    

    public double getTaxableAt5() {
        return taxableAt5;
    }

    public void setTaxableAt5(double taxableAt5) {
        this.taxableAt5 = taxableAt5;
    }

    public double getTaxableAt12() {
        return taxableAt12;
    }

    public void setTaxableAt12(double taxableAt12) {
        this.taxableAt12 = taxableAt12;
    }

    public double getTaxableAt18() {
        return taxableAt18;
    }

    public void setTaxableAt18(double taxableAt18) {
        this.taxableAt18 = taxableAt18;
    }

    public double getTaxableAt0() {
        return taxableAt0;
    }

    public void setTaxableAt0(double taxableAt0) {
        this.taxableAt0 = taxableAt0;
    }

    public double getTaxableAt28() {
        return taxableAt28;
    }

    public void setTaxableAt28(double taxableAt28) {
        this.taxableAt28 = taxableAt28;
    } 

    public double getGstAt28() {
        return gstAt28;
    }

    public void setGstAt28(double gstAt28) {
        this.gstAt28 = gstAt28;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public long getBillSeqNumber() {
        return billSeqNumber;
    }

    public void setBillSeqNumber(long billSeqNumber) {
        this.billSeqNumber = billSeqNumber;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(double previousBalance) {
        this.previousBalance = previousBalance;
    }
    
}
