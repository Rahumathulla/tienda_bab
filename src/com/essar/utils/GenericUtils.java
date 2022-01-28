/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rahumathulla
 */
public class GenericUtils {
    public static boolean removeRowFromJTable(JTable tblSalesDetails, DefaultTableModel stockModel){
        int nbrrow=tblSalesDetails.getRowCount();
                if(nbrrow>=1){
                        int[] rows = tblSalesDetails.getSelectedRows();
                        if(rows.length>=1){
                            if(tblSalesDetails.getModel().isCellEditable(tblSalesDetails.getSelectedRow(),tblSalesDetails.getSelectedColumn())
                                    &&tblSalesDetails.isEditing()==true){
                         
                                tblSalesDetails.getCellEditor().stopCellEditing();
                                for(int i=0;i<rows.length;i++){ 
                                        stockModel.removeRow(rows[i]-i);
                                    }
                            }else{
                            for(int i=0;i<rows.length;i++){ 
                             stockModel.removeRow(rows[i]-i);
                                }

                            }
                            tblSalesDetails.revalidate();
                            tblSalesDetails.repaint();
                        }
                }
        return true;
    }
    
    public static boolean addRowToJTable(JTable tableName, DefaultTableModel modelName){
        int nbrrow=tableName.getRowCount();
                /*if(nbrrow>=1){
                        int[] rows = tableName.getSelectedRows();
                        if(rows.length>=1){
                            if(tableName.getModel().isCellEditable(tableName.getSelectedRow(),tableName.getSelectedColumn())
                                    &&tableName.isEditing()==true){
                         
                                tableName.getCellEditor().stopCellEditing();
                                for(int i=0;i<rows.length;i++){ 
                                        modelName.removeRow(rows[i]-i);
                                    }
                            }else{
                            for(int i=0;i<rows.length;i++){ 
                             modelName.removeRow(rows[i]-i);
                                }

                            }
                            tableName.revalidate();
                            tableName.repaint();
                        }
                }*/
        return true;
    }
    
    public static double roundDouble(double d, int places) {
	 
	BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
	bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
	return bigDecimal.doubleValue();
    }
    
    public static String numberToWord(int number) {
        // variable to hold string representation of number 
        String words = "";
        String unitsArray[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", 
                      "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                      "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", 
                      "Eighteen", "Nineteen" };
	String tensArray[] = { "Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
                      "Sixty", "Seventy", "Eighty", "Ninety" };
 
	if (number == 0) {
	    return "zero";
	}
	// add minus before conversion if the number is less than 0
	if (number < 0) { // convert the number to a string 
           String numberStr = "" + number; 
           // remove minus before the number 
           numberStr = numberStr.substring(1); 
           // add minus before the number and convert the rest of number 
           return "minus " + numberToWord(Integer.parseInt(numberStr));
        } 
        // check if number is divisible by 1 million
        if ((number / 1000000) > 0) {
	   words += numberToWord(number / 1000000) + " Million ";
	   number %= 1000000;
	}
	// check if number is divisible by 1 thousand
	if ((number / 1000) > 0) {
	    words += numberToWord(number / 1000) + " Thousand ";
	    number %= 1000;
	}
	// check if number is divisible by 1 hundred
	if ((number / 100) > 0) {
	     words += numberToWord(number / 100) + " Hundred ";
	     number %= 100;
	}
 
	if (number > 0) {
	     // check if number is within teens
	     if (number < 20) { 
                // fetch the appropriate value from unit array 
                words += unitsArray[number];
             } else { 
                // fetch the appropriate value from tens array 
                words += tensArray[number / 10]; 
                if ((number % 10) > 0) {
		    words += " " + unitsArray[number % 10];
                }  
	     }
          }
 
	  return words;
   }
}
