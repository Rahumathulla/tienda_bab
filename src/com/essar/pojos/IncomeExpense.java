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
public class IncomeExpense {
    long incomeExpenseId;
    String incomeExpenseName;
    String type;
    String remarks;

    public long getIncomeExpenseId() {
        return incomeExpenseId;
    }

    public void setIncomeExpenseId(long incomeExpenseId) {
        this.incomeExpenseId = incomeExpenseId;
    }

    public String getIncomeExpenseName() {
        return incomeExpenseName;
    }

    public void setIncomeExpenseName(String incomeExpenseName) {
        this.incomeExpenseName = incomeExpenseName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
   

    @Override
    public String toString() { 
        return incomeExpenseName; 
    } 
    
    
}
