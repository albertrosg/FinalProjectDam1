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
import models.connection.OwnerControl;
import models.entitys.Owner;
import models.entitys.persistence.SaveOwner;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class ConfirmRemoveOwnerController implements Initializable {

    @FXML
    private Label lblRemoveMessage;
    
    private Owner owner;

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
    private void removeOwner(ActionEvent event) {        
        
        new OwnerControl().removeOwner(owner);        
        new WindowControl().close(event);
        
    }

    private void initialize() {
        owner = new SaveOwner().loadOwner();
        lblRemoveMessage.setText("¿Está seguro que desea eliminar al cliente " + owner.getName() + "?");
    }

    
    
}
