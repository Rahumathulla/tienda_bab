/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essar.suggestion;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;
import java.util.function.Function;
/**
 *
 * @author rahumathulla
 */
public class TextComponentSuggestionClient implements SuggestionClient<JTextComponent> {

    private Function<String, List<String>> suggestionProvider;

    public TextComponentSuggestionClient(Function<String, List<String>> suggestionProvider) {
        this.suggestionProvider = suggestionProvider;
    }

    @Override
    public Point getPopupLocation(JTextComponent invoker) {
        invoker.setFont(null);
        return new Point(0, invoker.getPreferredSize().height);
    }

    @Override
    public void setSelectedText(JTextComponent invoker, String selectedValue) {
        invoker.setText(selectedValue);
    }

    @Override
    public List<String> getSuggestions(JTextComponent invoker) {
        return suggestionProvider.apply(invoker.getText().trim());
    }
}