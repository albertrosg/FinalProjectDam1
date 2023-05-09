/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Albert
 */
public class Hours {
    
    private ObservableList<LocalTime> hours = FXCollections.observableArrayList();
    
    public Hours(){
        
        LocalTime start = LocalTime.of(8, 0);
        LocalTime finish = LocalTime.of(19, 30);
        
        for (LocalTime hour = start; hour.isBefore(finish); hour = hour.plusMinutes(30)){
            hours.add(hour);
        }
        
    }

    public ObservableList<LocalTime> getHours() {
        return hours;
    }

    public void setHours(ObservableList<LocalTime> hours) {
        this.hours = hours;
    }
    
    
    
}
