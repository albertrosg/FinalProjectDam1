/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam1.controllers;

import finalprojectdam1.WindowControl;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.connection.CreateUser;
import models.connection.FindUser;
import models.entitys.encryption.SHAEncryption;


/**
 *
 * @author Albert
 */
public class LoginController extends KeyAdapter implements Initializable{
    
    private WindowControl windowControl;    
    
    @FXML
    private Label lblError;
    @FXML
    private Button btExit;
    @FXML
    private Button btAccess;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUser;   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
                
        btAccess.setDisable(true);       
        Platform.runLater(() -> txtUser.requestFocus());
        txtUser.setOnKeyReleased(e -> enableButton());
        txtPassword.setOnKeyReleased(e -> enableButton());
        txtUser.setOnKeyPressed(e -> accesByEnter(e));
        txtPassword.setOnKeyPressed(e -> accesByEnter(e));              
    }    

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void access(ActionEvent event) throws IOException {
        String user = txtUser.getText();
        String password = new SHAEncryption().Sha256(txtPassword.getText().trim());
        int prueba = new FindUser().getUser(user, password);
        if (new FindUser().getUser(user, password)!=0){
            
            /*windowControl.openMainWindow();
            windowControl.close(event);*/
            
            Stage stage = new Stage();        
        Parent root = FXMLLoader.load(getClass().getResource("/views/mainWindow.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        }else{            
            lblError.setText("Usuario o contraseña no valido");
        }
    }     
   
    public void enableButton(){
        
        lblError.setText("");
        if (!txtUser.getText().isEmpty()){
            if (!txtPassword.getText().isEmpty()){
                btAccess.setDisable(false);
            } else btAccess.setDisable(true);
        }
    }
    
    public void accesByEnter(KeyEvent e){
        
        if (e.getCode()== KeyCode.ENTER){
            if (!txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty()){  
                try {
                    windowControl.openMainWindow();
                    windowControl.close((ActionEvent) e.getSource());
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
    }
    
}
