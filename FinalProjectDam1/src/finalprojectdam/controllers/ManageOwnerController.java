/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam.controllers;

import exceptions.DniException;
import exceptions.EmailException;
import exceptions.PhoneNumberException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import models.Hours;
import models.TypeAppoinment;
import models.connection.FindInTables;
import models.connection.OwnerControl;
import models.connection.PetControl;
import models.connection.UserControl;
import models.connection.VisitControl;
import models.entitys.Owner;
import models.entitys.Pet;
import models.entitys.User;
import models.entitys.Visit;
import models.entitys.persistence.SaveOwner;
import models.entitys.persistence.SavePet;
import models.entitys.persistence.SaveUser;
import validations.Validation;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class ManageOwnerController implements Initializable {

    @FXML
    private TextField txtNameOwner, txtSurnameOwner, txtDniOwner, txtEmailOwner, txtPhoneNumberOwner, txtAddressOwner, txtSearchOwner;
    @FXML
    private TableView<Pet> tvPet;
    @FXML
    private TableColumn<Pet, String> tcPetName, tcType, tcChipNumber;
    @FXML
    private TableView<Owner> tvOwner;
    @FXML
    private TableColumn<Owner, String> tcNameOwner, tcSurnameOwner, tcDNIOwner;
    @FXML
    private TextField txtNamePet, txtChipNumber, txtWeightPet;
    @FXML
    private Button btModifyOwner, btAddPet, btAddOwner, btModifyPet, btRemovePet, btRemoveOwner;
    @FXML
    private ComboBox<String> cbTypePet, cbRacePet, cbAppoinmentType;
    @FXML
    private ComboBox<LocalTime> cbAppoinmentHour;
    @FXML
    private DatePicker dpBirthDatePet, appoinmentDate;
    @FXML
    private TextArea txtComment;
    @FXML
    private RadioButton rbFemale, rbMale;
    @FXML
    private TextArea txtOwnerDetails;
    @FXML
    private Label lblErrorOwner, lblErrorPet, lblMessageVisit;

    private Owner owner, selectedOwner;

    private Pet pet, selectedPet;

    private ObservableList<String> itemsCbTypePet = FXCollections.observableArrayList();

    private ObservableList<String> itemsCbRacePet = FXCollections.observableArrayList();

    private final ObservableList<Owner> itemsOwner = FXCollections.observableArrayList();

    private final ObservableList<Pet> itemsPet = FXCollections.observableArrayList();

    private OwnerControl ownerControl;

    private PetControl petControl;

    private int diaryId;

    private User user;
    
    private final ObservableList<String> typeAppoinment = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ownerControl = new OwnerControl();

        petControl = new PetControl();

        selectedOwner = null;

        selectedPet = null;

        user = new SaveUser().loadUser();

        initializeTables();

        itemsCbTypePet = new FindInTables().getType();
        cbTypePet.setItems(itemsCbTypePet);
        cbTypePet.setValue(itemsCbTypePet.get(0));
        fillRace();
        cbTypePet.setOnAction(e -> fillRace());

        tvOwner.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedOwner = newValue;
                }
        );

        tvPet.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedPet = newValue;
                });

        tvOwner.setOnMouseClicked(e -> actionOwner());
        tvPet.setOnMouseClicked(e -> actionPet());

        btModifyOwner.setDisable(true);
        btAddOwner.setDisable(true);
        btRemoveOwner.setDisable(true);
        btAddPet.setDisable(true);
        btModifyPet.setDisable(true);
        btRemovePet.setDisable(true);

        txtSearchOwner.setOnKeyReleased(e -> searchOwner());
        allOwner();

        addListenerTextFields();
        
        
        
        cbAppoinmentHour.setDisable(true);
        cbAppoinmentHour.setItems(new Hours().getHours()); 
        cbAppoinmentHour.setCellFactory(new Callback<ListView<LocalTime>, ListCell<LocalTime>>() {
            @Override
            public ListCell<LocalTime> call(ListView<LocalTime> param) {
                return new ListCell<LocalTime>(){
                    @Override
                    protected void updateItem(LocalTime item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setDisable(false);
                        } else {
                            setText(item.toString());
                            if (new VisitControl().getHours(appoinmentDate.getValue()).contains(item)){                                
                                setDisable(true);
                                setStyle("-fx-opacity: 0.3");
                            }else{
                                setDisable(false);
                            }
                        }
                    }
                };
            }
        });
        
        cbAppoinmentType.setDisable(true);
        cbAppoinmentType.setItems(new TypeAppoinment().getTypes());

        appoinmentDate.setDisable(true);
        appoinmentDate.setOnAction(e -> cbAppoinmentHour.setDisable(false));
        appoinmentDate.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                        }
                    }
                };
            }
        });

        dpBirthDatePet.setValue(LocalDate.now());

    }

    @FXML
    private void addOwner(ActionEvent event) {
        
        try{

        String name = txtNameOwner.getText();
        String surname = txtSurnameOwner.getText();
        String dni = null;
        if(new Validation().dniValidate(txtDniOwner.getText())){
            dni = txtDniOwner.getText();
        }else{
            throw new DniException("DNI's format isn't correct");
        }
        String email = null;
        if(new Validation().emailValidate(txtEmailOwner.getText())){
            email = txtEmailOwner.getText();
        }else {
            throw new EmailException("Email's format isn't correct");
        }
        String phoneNumber = null;
        if(new Validation().phoneNumberValidate(txtPhoneNumberOwner.getText())){
            phoneNumber = txtPhoneNumberOwner.getText();
        }else{
            throw new PhoneNumberException("Phone number's format isn't correct");
        }
        String address = txtAddressOwner.getText();

        if (ownerControl.createOwner(new Owner(dni, name, surname, phoneNumber, email, address), lblErrorOwner, txtOwnerDetails)) {
            allOwner();
            cleanTextOwner();
        }
        }catch(DniException e){
            lblErrorOwner.setText("EL DNI no tiene el formato correcto");
        } catch (EmailException ex) {
            lblErrorOwner.setText("El email no tiene el formato correcto");
        } catch (PhoneNumberException ex) {
            lblErrorOwner.setText("El teléfono no tiene un formato correcto");
        }

    }

    @FXML
    private void modifyOwner(ActionEvent event) throws IOException {

        new WindowControl().openOtherWindow("modifyOwner", "Modificar cliente");
        allOwner();
        actionOwner();

    }

    @FXML
    private void removeOwner(ActionEvent event) throws IOException {

        new WindowControl().openOtherWindow("confirmRemoveOwner", "Eliminar cliente");
        txtOwnerDetails.setText("");
        selectedOwner = null;
        new SaveOwner().removeOwner();
        allOwner();
        allPet();
    }

    @FXML
    private void addPet(ActionEvent event) {

        try {

            owner = ownerControl.getOwnerTable(selectedOwner.getDni());

            String sex = "";

            String name = txtNamePet.getText();
            String type = cbTypePet.getValue();
            String race = cbRacePet.getValue();
            if (rbFemale.isSelected()) {
                sex = "Hembra";
            } else if (rbMale.isSelected()) {
                sex = "Macho";
            }
            String chipNumber = txtChipNumber.getText();
            Double weigth = Double.parseDouble(txtWeightPet.getText());
            LocalDate birthDate = dpBirthDatePet.getValue();

            Pet pet = new Pet(chipNumber, name, type, race, sex, weigth, birthDate, owner);

            if (petControl.addPet(pet, lblErrorPet)) {
                txtNamePet.setText("");
                cbRacePet.setValue(itemsCbRacePet.get(0));
                cbTypePet.setValue(itemsCbTypePet.get(0));
                rbFemale.setSelected(false);
                rbMale.setSelected(false);
                txtChipNumber.setText("");
                txtWeightPet.setText("");
                dpBirthDatePet.setValue(null);
                btAddPet.setDisable(true);
            }

        } catch (NumberFormatException e) {
            lblErrorPet.setText("El peso debe ser un número.");
        }

        allPet();
    }

    @FXML
    private void modifyPet(ActionEvent event) {

    }

    @FXML
    private void removePet(ActionEvent event) throws IOException {

        new WindowControl().openOtherWindow("confirmRemovePet", "Eliminar mascota");
        selectedPet = null;
        new SavePet().removePet();
        allPet();

    }

    @FXML
    private void makeAppoinment(ActionEvent event) {
        
        String type = cbAppoinmentType.getValue();
        LocalTime hour = cbAppoinmentHour.getValue();
        LocalDate date = appoinmentDate.getValue();
        String comment = txtComment.getText();
        User user = new SaveUser().loadUser();
        Pet pet = new SavePet().loadPet();
        Owner owner = new SaveOwner().loadOwner();
        
        Visit visit = new Visit(type, hour, date, comment, user, pet, owner);
        
        if (new VisitControl().createVisit(visit)){
            lblMessageVisit.setText("Se ha introducido correctamente");
            appoinmentDate.setValue(null);
            cbAppoinmentHour.setValue(null);
            cbAppoinmentHour.setDisable(true);
            cbAppoinmentType.setValue(null);
            cbAppoinmentType.setDisable(true);
            txtComment.setText("");
        }else{
            lblMessageVisit.setText("Ha ocurrido un error");
            appoinmentDate.setValue(null);
            cbAppoinmentHour.setValue(null);
            cbAppoinmentHour.setDisable(true);
            cbAppoinmentType.setValue(null);
            cbAppoinmentType.setDisable(true);
            txtComment.setText("");
        }      

    }

    /**
     * This method initializes the owners TextField
     */
    private void addListenerTextFields() {

        txtNameOwner.setOnKeyReleased(e -> enableButtonAddOwner());
        txtSurnameOwner.setOnKeyReleased(e -> enableButtonAddOwner());
        txtDniOwner.setOnKeyReleased(e -> enableButtonAddOwner());
        txtEmailOwner.setOnKeyReleased(e -> enableButtonAddOwner());
        txtPhoneNumberOwner.setOnKeyReleased(e -> enableButtonAddOwner());
        txtAddressOwner.setOnKeyReleased(e -> enableButtonAddOwner());

        txtNamePet.setOnKeyReleased(e -> enableButtonAddPet());
        txtNamePet.setOnKeyReleased(e -> enableButtonAddPet());
        txtChipNumber.setOnKeyReleased(e -> enableButtonAddPet());
        txtWeightPet.setOnKeyReleased(e -> enableButtonAddPet());
        rbFemale.setOnMouseClicked(e -> enableButtonAddPet());
        rbMale.setOnMouseClicked(e -> enableButtonAddPet());
    }

    /**
     * This method enables the modify button if any owner Texfield changes
     */
    private void enableButtonAddOwner() {
        if (!txtNameOwner.getText().isEmpty()) {
            if (!txtSurnameOwner.getText().isEmpty()) {
                if (!txtDniOwner.getText().isEmpty()) {
                    if (!txtEmailOwner.getText().isEmpty()) {
                        if (!txtPhoneNumberOwner.getText().isEmpty()) {
                            if (!txtAddressOwner.getText().isEmpty()) {
                                btAddOwner.setDisable(false);
                            } else {
                                btAddOwner.setDisable(true);
                            }
                        } else {
                            btAddOwner.setDisable(true);
                        }
                    } else {
                        btAddOwner.setDisable(true);
                    }
                } else {
                    btAddOwner.setDisable(true);
                }
            } else {
                btAddOwner.setDisable(true);
            }
        } else {
            btAddOwner.setDisable(true);
        }
        lblErrorOwner.setText("");
    }

    private void enableButtonAddPet() {
        if (!txtNamePet.getText().isEmpty()) {
            if (!txtChipNumber.getText().isEmpty()) {
                if (!txtWeightPet.getText().isEmpty()) {
                    if (rbMale.isSelected() || rbFemale.isSelected()) {
                        if (selectedOwner != null) {
                            btAddPet.setDisable(false);
                        } else {
                            btAddPet.setDisable(true);
                        }
                    } else {
                        btAddPet.setDisable(true);
                    }
                } else {
                    btAddPet.setDisable(true);
                }
            } else {
                btAddPet.setDisable(true);
            }
        } else {
            btAddPet.setDisable(true);
        }
        lblErrorPet.setText("");
    }

    private void fillRace() {

        cbRacePet.getItems().clear();
        itemsCbRacePet = new FindInTables().getRace(cbTypePet.getValue());
        cbRacePet.setItems(itemsCbRacePet);
        cbRacePet.setValue(itemsCbRacePet.get(0));

    }

    private void initializeTables() {

        tcNameOwner.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcSurnameOwner.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tcDNIOwner.setCellValueFactory(new PropertyValueFactory<>("dni"));
        setItems(itemsOwner, "owner");

        tcPetName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcChipNumber.setCellValueFactory(new PropertyValueFactory<>("chipNumber"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    private void setItems(ObservableList items, String type) {
        if (type.equals("owner")) {
            tvOwner.setItems(items);
        } else if (type.equals("pet")) {
            tvPet.setItems(items);
        }

    }

    private void actionOwner() {
        owner = ownerControl.getOwnerTable(selectedOwner.getDni());
        new SaveOwner().removeOwner();
        new SaveOwner().saveOwner(owner);
        txtOwnerDetails.setText("ID: \t\t\t" + owner.getOwnerId()
                + "\n\nNombre: \t\t\t" + owner.getName()
                + "\n\nApellidos: \t\t" + owner.getSurname()
                + "\n\nDNI: \t\t\t" + owner.getDni()
                + "\n\nCorreo electrónico: \t" + owner.getEmail()
                + "\n\nTeléfono: \t\t\t" + owner.getPhoneNumber()
                + "\n\nDirección: \t\t" + owner.getAddress());
        btModifyOwner.setDisable(false);
        btRemoveOwner.setDisable(false);
        allPet();
    }

    private void searchOwner() {
        itemsOwner.clear();
        setItems(ownerControl.getOwner(txtSearchOwner.getText()), "owner");
    }

    private void allOwner() {
        setItems(ownerControl.getOwner(), "owner");
    }

    private void allPet() {
        tvPet.getItems().clear();
        if (selectedOwner != null) {
            Owner newOwner = ownerControl.getOwnerTable(selectedOwner.getDni());
            setItems(petControl.getPet(newOwner.getOwnerId()), "pet");
        }
    }

    private void actionPet() {

        appoinmentDate.setDisable(false);
        btModifyOwner.setDisable(false);
        btRemovePet.setDisable(false);
        cbAppoinmentType.setDisable(false);

        pet = petControl.getPet(selectedPet.getChipNumber());
        new SavePet().savePet(pet);

    }

    private void cleanTextOwner() {
        txtNameOwner.setText("");
        txtSurnameOwner.setText("");
        txtDniOwner.setText("");
        txtEmailOwner.setText("");
        txtPhoneNumberOwner.setText("");
        txtAddressOwner.setText("");
    }

    
    
}
