/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.entitys.User;
import models.entitys.persistence.SaveUser;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class MainWindowController implements Initializable {

    @FXML
    private FlowPane flowPane;
    @FXML
    private Label lblWelcome;
    @FXML
    private ImageView imgIcon;
    @FXML
    private VBox vbMainLeft;
    @FXML
    private Button btLogout, btExit;

    private AnchorPane manageUsers;

    private WindowControl windowControl;

    private User user;

    private LoginController loginController;

    private Button btOwnerPets = new Button("Clientes");
    private Button btUser = new Button("Gestión usuarios");
    private Button btProduct = new Button("Gestión productos");
    private Button btDiary = new Button("Agenda");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        windowControl = new WindowControl();
        Platform.runLater(() -> {
            try {
                initialize();
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        user = new SaveUser().loadUser();
        
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {

        new SaveUser().removeUser();
        System.exit(0);

    }

    @FXML
    private void logout(ActionEvent event) {

        user = null;
        new SaveUser().removeUser();
        windowControl.openLogin();
        windowControl.close(event);

    }


    private void getNode(String view, String title) {

        try {
            Node source = FXMLLoader.load(getClass().getResource("/views/" + view + ".fxml"));

        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*public void setUser(LoginController loginController, User user) {
        this.user = user;
        this.loginController = loginController;
    }*/

    private void initialize() throws IOException {
        lblWelcome.setText("Bienvenido " + user.getName() + " " + user.getSurname());

        btLogout.setMinWidth(vbMainLeft.getWidth());
        btExit.setMinWidth(vbMainLeft.getWidth());

        if (user.isAdmin()) {
            manageUsers = FXMLLoader.load(getClass().getResource("/views/manageUsers.fxml"));
            flowPane.getChildren().add(manageUsers);
            btUser.setMinWidth(vbMainLeft.getWidth());
            btUser.setOnMouseClicked(event -> panelControl(event));
            btProduct.setMinWidth(vbMainLeft.getWidth());
            btProduct.setOnMouseClicked(event -> panelControl(event));
            vbMainLeft.setSpacing(10);
            vbMainLeft.setPadding(new Insets(20));
            vbMainLeft.getChildren().addAll(btUser, btProduct);
        }

        btOwnerPets.setMinWidth(vbMainLeft.getWidth());
        btOwnerPets.setOnMouseClicked(event -> panelControl(event));
        vbMainLeft.getChildren().add(btOwnerPets);
        
        btDiary.setMinWidth(vbMainLeft.getWidth());
        btDiary.setOnMouseClicked(event -> panelControl(event));
        vbMainLeft.getChildren().add(btDiary);

    }

    private void panelControl(MouseEvent event) {
        try {
            if (event.getSource().equals(btOwnerPets)) {                             
                manageUsers = FXMLLoader.load(getClass().getResource("/views/manageOwners.fxml"));
            } else if (event.getSource().equals(btUser)) {
                manageUsers = FXMLLoader.load(getClass().getResource("/views/manageUsers.fxml"));
            }else if (event.getSource().equals(btProduct)) {
                manageUsers = FXMLLoader.load(getClass().getResource("/views/product.fxml"));
            }else if (event.getSource().equals(btDiary)){
                manageUsers = FXMLLoader.load(getClass().getResource("/views/diary.fxml"));
            }
            flowPane.getChildren().clear();
            flowPane.getChildren().add(manageUsers);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    
    
}


