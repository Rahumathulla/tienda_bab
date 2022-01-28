/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.pojos;

import java.util.Date;


/**
 *
 * @author rahumathulla
 */
public class DayBook {
    long dayBookId;
    long incomeExpenseId;
    Date eventDate;
    String particularName;
    String type;    
    double creditAmount;
    double debitAmount;
    String remarks;
    Date createTS;
    Date updateTS;

    public long getDayBookId() {
        return dayBookId;
    }

    public void setDayBookId(long dayBookId) {
        this.dayBookId = dayBookId;
    }

    public long getIncomeExpenseId() {
        return incomeExpenseId;
    }

    public void setIncomeExpenseId(long incomeExpenseId) {
        this.incomeExpenseId = incomeExpenseId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getParticularName() {
        return particularName;
    }

    public void setParticularName(String particularName) {
        this.particularName = particularName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    
}
