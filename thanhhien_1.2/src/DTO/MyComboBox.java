package DTO;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author bumte
 */
public class MyComboBox {
    Object values;
    Object text;

    public MyComboBox(Object values, Object text) {
        this.values = values;
        this.text = text;
    }
    public String toString(){
        return text.toString();
    }
    public int toInt(){
        return Integer.parseInt(values.toString());
    }
    public double toDouble(){
        return Double.parseDouble(values.toString());
    }
}
