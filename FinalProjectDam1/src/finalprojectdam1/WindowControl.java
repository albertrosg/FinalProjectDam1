/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam1;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Albert
 */
public class WindowControl {
    
    public void openMainWindow() throws IOException{
        
        Stage stage = new Stage();        
        Parent root = FXMLLoader.load(getClass().getResource("/views/mainWindow.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        
    }
    
    public void close(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
