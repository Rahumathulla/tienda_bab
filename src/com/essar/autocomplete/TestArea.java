/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.autocomplete;

/**
 *
 * @author rahumathulla
 */
public class TestArea {
    public static void main(String[] args) {
        double a = 45.65;
        double b = 12.45;
        double roundOff = (double) Math. round(a * 100) / 100;
        double roundOff1 = (double) Math. round(b * 100) / 100;
        System.out.println("45.65 --"+Math.round(a));
        System.out.println("12.45 --"+Math.round(b));
    }
}
