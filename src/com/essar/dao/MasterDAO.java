/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.dao;

import java.util.List;



/**
 *
 * @author rahumathulla
 */
public interface MasterDAO {
    public List<Object> retrieveAll();
    public List<Object> retrievebyQuery(String query);
    public Object retrieveById(long id);
    public void insertIntoDB(List<Object> objects);
    public void deleteRecord(long id);
    public void updateRecord(long id);
        
}
