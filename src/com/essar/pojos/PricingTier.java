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
public class PricingTier {
    long id;
    String customerTypeName;
    double pricingPercentage;
    String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public double getPricingPercentage() {
        return pricingPercentage;
    }

    public void setPricingPercentage(double pricingPercentage) {
        this.pricingPercentage = pricingPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() { 
        return customerTypeName; 
    } 
    
    
}
