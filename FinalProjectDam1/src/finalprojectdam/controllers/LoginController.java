/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam.controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.connection.FindInTables;
import models.connection.VisitControl;
import models.entitys.User;
import models.entitys.encryption.SHAEncryption;
import models.entitys.persistence.SaveUser;


/**
 *
 * @author Albert
 */
public class LoginController extends KeyAdapter implements Initializable{   
    
    @FXML
    private Label lblError;    
    @FXML
    private Button btAccess;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUser;   
    @FXML
    private ImageView vetImg, imgLogin;
    
    private User user;
    
    private WindowControl windowControl;     
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        new VisitControl().deleteVisitPrevious();
        
        txtPassword.setText("1234");
        txtUser.setText("albros");
           
        windowControl = new WindowControl();
        btAccess.setDisable(false);
        Platform.runLater(() -> txtUser.requestFocus());
        Platform.runLater(() -> loadImage());        
        txtUser.setOnKeyReleased(e -> enableButton());
        txtPassword.setOnKeyReleased(e -> enableButton());
        txtUser.setOnKeyPressed(e -> {
            try {
                accesByEnter(e);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        txtPassword.setOnKeyPressed(e -> {
            try {
                accesByEnter(e);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }    

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void access(ActionEvent event) throws IOException {
        try{
            String userName = txtUser.getText().trim();
            String password = new SHAEncryption().Sha256(txtPassword.getText().trim());  
            user = new FindInTables().getUser(userName, password, lblError);
            new SaveUser().saveUser(user);
            if (user!=null){            
                windowControl.openMainWindow();
                windowControl.close(event);       
            }else{            
                lblError.setText("Usuario o contraseña no valido");
                txtUser.requestFocus();
            }
        }catch (IllegalStateException e){
            lblError.setText("A ocurrido un fallo con la conexión");
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
    
    public void accesByEnter(KeyEvent e) throws IOException{
        
        if (e.getCode()== KeyCode.ENTER){
            if (!txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty()){  
                ActionEvent event = new ActionEvent();
                access(event);                
            }
        }       
    }   
    
    public void loadImage(){
        
        Image imgVet = new Image("/images/VetImage.png");
        Image loginImg = new Image("/images/LoginIcon.png");
        vetImg.setImage(imgVet);
        imgLogin.setImage(loginImg);
        
    }
}
