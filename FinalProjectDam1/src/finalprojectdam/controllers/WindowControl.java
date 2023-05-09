/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Albert
 */
public class WindowControl {   
    
    public void openMainWindow() throws IOException{
        
        Stage stage = new Stage();    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mainWindow.fxml"));
        Parent root = (Parent) loader.load();        
        Scene scene = new Scene(root); 
        stage.setScene(scene);        
        stage.setResizable(false);
        stage.show();
        
    }
    
    public void openOtherWindow(String view, String title) throws IOException{
        
        Stage stage = new Stage();        
        Parent root = FXMLLoader.load(getClass().getResource("/views/" + view + ".fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);  
        stage.setTitle(title);
        stage.showAndWait();
        
    }
    
    
    
    public void openLogin(){
        
        try {
            Stage stage = new Stage();
            
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WindowControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
