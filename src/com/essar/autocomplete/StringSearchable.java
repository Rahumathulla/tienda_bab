/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author rahumathulla
 */
public class StringSearchable implements Searchable<String, String>{
    	private List<String> values = new ArrayList<String>();
	/**
	 * Constructs a new object based upon the parameter terms. 
	 * @param terms The inventory of terms to search.
	 */
	public StringSearchable(List<String> terms){
		this.values.addAll(terms);
	}

	

	@Override

	public Collection<String> search(String value) {
		List<String> founds = new ArrayList<String>();	

		for ( String s : values ){
			if ( s.indexOf(value) == 0 ){
				founds.add(s);
			}
		}
		return founds;
	}    
}
