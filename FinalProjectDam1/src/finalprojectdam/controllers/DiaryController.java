/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import models.connection.VisitControl;
import models.entitys.Owner;
import models.entitys.Pet;
import models.entitys.Visit;
import models.entitys.persistence.SaveUser;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class DiaryController implements Initializable {

    @FXML
    private DatePicker dpDateDiary;
    @FXML
    private TableView<Visit> tvDiary;
    @FXML
    private TableColumn<Visit, Pet> tcPetName;
    @FXML
    private TableColumn<Visit, Owner> tcOwnerName;
    @FXML
    private TableColumn<Visit, String> tcVisitType, tcComment;
    @FXML
    private TableColumn<Visit, LocalDate> tcDate;
    @FXML
    private TableColumn<Visit, LocalTime> tcHour;
    @FXML
    private AnchorPane anchorPane;

    private FlowPane flowPane;

    private ObservableList<Visit> items = FXCollections.observableArrayList();

    private Visit selectedVisit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeTable();

        selectedVisit = null;

    }

    @FXML
    private void addVisit(ActionEvent event) throws IOException {

        flowPane = (FlowPane) anchorPane.getParent();
        anchorPane = FXMLLoader.load(getClass().getResource("/views/manageOwners.fxml"));
        flowPane.getChildren().clear();
        flowPane.getChildren().add(anchorPane);

    }

    private void initializeTable() {

        tvDiary.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, NewValue) -> {
                    selectedVisit = NewValue;
                }
        );
        
        tcPetName.setCellFactory(new Callback<TableColumn<Visit, Pet>, TableCell<Visit, Pet>>() {
            @Override
            public TableCell<Visit, Pet> call(TableColumn<Visit, Pet> column) {
                return new TableCell<Visit, Pet>() {
                    @Override
                    protected void updateItem(Pet pet, boolean empty) {
                        super.updateItem(pet, empty);
                        if (pet == null || empty) {
                            setText(null);
                        } else {
                            setText(pet.getName());
                        }
                    }
                };
            }
        });   
        
        //tcPetName.setCellValueFactory(new PropertyValueFactory<>("pet"));
        
        tcOwnerName.setCellFactory(new Callback<TableColumn<Visit, Owner>, TableCell<Visit, Owner>>() {
            @Override
            public TableCell<Visit, Owner> call(TableColumn<Visit, Owner> column) {
                return new TableCell<Visit, Owner>() {
                    @Override
                    protected void updateItem(Owner owner, boolean empty) {
                        super.updateItem(owner, empty);
                        if (owner == null || empty) {
                            setText(null);
                        } else {
                            setText(owner.getName());
                        }
                    }
                };
            }
        }); 
        
        //tcOwnerName.setCellValueFactory(new PropertyValueFactory<>("owner"));
        tcVisitType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcHour.setCellValueFactory(new PropertyValueFactory<>("hour"));
        setItems(items);

    }

    private void setItems(ObservableList<Visit> items) {
        items = new VisitControl().getVisit(new SaveUser().loadUser().getId());
        tvDiary.setItems(items);
    }

}
