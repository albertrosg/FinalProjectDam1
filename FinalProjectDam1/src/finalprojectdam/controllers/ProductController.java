/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectdam.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.connection.OrderControl;
import models.connection.ProductControl;
import models.entitys.Order;
import models.entitys.Product;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class ProductController implements Initializable {

    @FXML
    private TableView<Product> tvProduct;
    @FXML
    private TableColumn<Product, String> tcProductId, tcProductName;
    @FXML
    private TableColumn<Product, Double> tcProdcutPrice;
    @FXML
    private TableColumn<Product, Integer> tcCriticalStock, tcStock;
    @FXML
    private TableView<Order> tvOrder;
    @FXML
    private TableColumn<Order, String> tcDeliveryNote;
    @FXML
    private TableColumn<Order, Integer> tcOrderNum, tcAmount;
    @FXML
    private TableColumn<Order, LocalDate> tcDate;
    @FXML
    private TextField txtProductId, txtProductName, txtPrice, txtDeliveryNote, txtOrderNum, txtAmount, txtSearchProduct;
    @FXML
    private Button btAddProduct, btRemoveProduct, btAddOrder;
    @FXML
    private Label lblMessageProduct, lblMessageOrder, lblOrder;
    @FXML
    private ListView lvOrder;

    private ProductControl productControl;

    private OrderControl orderControl;

    private Product selectedProduct;

    private ObservableList<Product> items = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        productControl = new ProductControl();

        orderControl = new OrderControl();

        selectedProduct = null;

        tvProduct.setEditable(true);

        tvProduct.setOnMouseReleased(e -> enableButton());

        tvProduct.setOnMouseClicked(e -> fillTVOrder());

        tvProduct.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedProduct = newValue;
                }
        );

        initializeTVProduct();

        initializeTVOrder();

        setItems(productControl.getProduct(), "product");

        btAddProduct.setDisable(true);
        txtProductId.setOnKeyReleased(e -> enableButton());
        txtProductName.setOnKeyReleased(e -> enableButton());
        txtPrice.setOnKeyReleased(e -> enableButton());

        btAddOrder.setDisable(true);
        btAddOrder.setOnMousePressed(e -> enableButton());
        txtDeliveryNote.setOnKeyReleased(e -> enableButton());
        txtOrderNum.setOnKeyReleased(e -> enableButton());
        txtAmount.setOnKeyReleased(e -> enableButton());

        txtSearchProduct.setOnKeyReleased(e -> searchProduct());

    }

    @FXML
    private void addProduct(ActionEvent event) {

        try {

            String id = txtProductId.getText();
            String name = txtProductName.getText();
            double price = Double.parseDouble(txtPrice.getText());

            Product product = new Product(id, name, price, 0, 0);

            if (productControl.createProduct(product)) {
                txtProductId.setText("");
                txtProductName.setText("");
                txtPrice.setText("");
            } else {
                lblMessageProduct.setText("Ha ocurrido un error en la introducción del producto");
            }

        } catch (NumberFormatException e) {
            lblMessageProduct.setText("El formato del precio no es correcto");
        }

        setItems(productControl.getProduct(), "product");

    }

    @FXML
    private void removeProduct(ActionEvent event) {

        if (!productControl.removeProduct(selectedProduct)) {
            lblMessageProduct.setText("Ha ocurrido un error en el borrado");
        } else {
            setItems(productControl.getProduct(), "product");
            tvOrder.setItems(null);
        }

    }

    @FXML
    private void addOrder(ActionEvent event) {

        try {

            Product productSelected = selectedProduct;

            String deliveryNote = txtDeliveryNote.getText();
            int orderNum = Integer.parseInt(txtOrderNum.getText());
            int amount = Integer.parseInt(txtAmount.getText());

            Order order = new Order(amount, deliveryNote, orderNum, selectedProduct);

            if (orderControl.createOrder(order)) {
                txtDeliveryNote.setText("");
                txtOrderNum.setText("");
                txtAmount.setText("");
                setItems(productControl.getProduct(), "product");
                setItems(orderControl.getOrder(productSelected), "order");
            } else {
                lblMessageOrder.setText("Ha ocurrido un error en la introducción");
            }

        } catch (NumberFormatException e) {
            lblMessageOrder.setText("El formato del número de pedido o cantidad no es correcto");
        }

    }

    private void setItems(ObservableList items, String type) {

        if (type.equals("product")) {            
            tvProduct.setItems(items);
        } else if (type.equals("order")) {
            tvOrder.setItems(items);
        }
    }

    private void enableButton() {
        lblMessageProduct.setText("");
        if (!txtProductId.getText().isEmpty()) {
            if (!txtProductName.getText().isEmpty()) {
                if (!txtPrice.getText().isEmpty()) {
                    btAddProduct.setDisable(false);
                } else {
                    btAddProduct.setDisable(true);
                }
            }
        }

        lblMessageOrder.setText("");
        if (!txtDeliveryNote.getText().isEmpty()) {
            if (!txtOrderNum.getText().isEmpty()) {
                if (!txtAmount.getText().isEmpty()) {
                    if (selectedProduct != null) {
                        btAddOrder.setDisable(false);
                    } else {
                        btAddOrder.setDisable(true);
                    }
                }
            }
        }
    }

    private void searchProduct() {

        setItems(productControl.getProduct(txtSearchProduct.getText()), "product");
    }

    private void initializeTVProduct() {
        tcProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));

        tcProductName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tcProdcutPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcProdcutPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tcProdcutPrice.setOnEditCommit(e -> modifyPrice(e));

        tcCriticalStock.setCellValueFactory(new PropertyValueFactory<>("criticalStock"));
        tcCriticalStock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tcCriticalStock.setOnEditCommit(e -> modifyCriticalStock(e));

        tcStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tcStock.setCellFactory(column -> {
            return new TableCell<Product, Integer>() {
                @Override
                protected void updateItem(Integer stock, boolean empty) {
                    super.updateItem(stock, empty);
                    if (empty || stock == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(stock.toString());
                        
                        Product product = (Product) getTableRow().getItem();
                        int criticalStock = product.getCriticalStock();

                        if (stock < criticalStock) {
                            setStyle("-fx-background-color: red;"
                                    + "-fx-text-fill: white;");
                        } else {
                            setStyle("-fx-background-color: green;"
                                    + "-fx-text-fill: white;");
                        }
                    }
                }
            };
        });

    }

    private void initializeTVOrder() {

        tcOrderNum.setCellValueFactory(new PropertyValueFactory<>("orderNum"));
        tcDeliveryNote.setCellValueFactory(new PropertyValueFactory<>("deliveryNote"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    private void fillTVOrder() {

        lblOrder.setText("Pedidos del articulo " + selectedProduct.getName());

        setItems(orderControl.getOrder(selectedProduct), "order");

    }

    private void modifyCriticalStock(TableColumn.CellEditEvent<Product, Integer> e) {
        productControl.modifyCriticalStock(e);
        setItems(productControl.getProduct(), "product");
    }

    private void modifyPrice(TableColumn.CellEditEvent<Product, Double> e) {
        productControl.modifyPrice(e);
        setItems(productControl.getProduct(), "product");
    }

}
