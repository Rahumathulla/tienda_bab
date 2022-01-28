/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.suggestion;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author rahumathulla
 */
public interface SuggestionClient<C extends JComponent> {

    Point getPopupLocation(C invoker);

    void setSelectedText(C invoker, String selectedValue);

    java.util.List<String> getSuggestions(C invoker);

}
