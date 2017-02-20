/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dfcastellanosc
 */
@Named(value = "counterView")
@ViewScoped
public class CounterView {

    private int number = 0;
    
    public int getNumber() {
        return number;
    }
 
    public void increment() {
        number++;
    }
    
}
