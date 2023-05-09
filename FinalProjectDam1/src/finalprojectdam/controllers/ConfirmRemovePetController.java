/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.connection.PetControl;
import models.entitys.Pet;
import models.entitys.persistence.SavePet;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class ConfirmRemovePetController implements Initializable {

    @FXML
    private Label lblRemoveMessage;
    
    private Pet pet;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        Platform.runLater(() -> initialize());
        
        
    }    

    @FXML
    private void cancel(ActionEvent event) {
        
        new WindowControl().close(event);
        
    }

    @FXML
    private void removePet(ActionEvent event) {        
        
        new PetControl().removePet(pet);        
        new WindowControl().close(event);
        
    }

    private void initialize() {
        pet = new SavePet().loadPet();
        lblRemoveMessage.setText("¿Está seguro que desea eliminar ala mascota " + pet.getName() + "?");
    }

    
    
}
