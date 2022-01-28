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
public class GST {
    long gstId;
    double gstValue;
    double sgst;
    double cgst;
    String description;

    public long getGstId() {
        return gstId;
    }

    public void setGstId(long gstId) {
        this.gstId = gstId;
    }

    public double getGstValue() {
        return gstValue;
    }

    public void setGstValue(double gstValue) {
        this.gstValue = gstValue;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() { 
        return gstValue+""; 
    }   
    
}
