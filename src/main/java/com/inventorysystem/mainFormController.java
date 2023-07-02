package com.inventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/** This is the controller for the mainForm page.
 The purpose of this controller is to control the UI for the main page.
 @author Carlos Rodriguez
 */
public class mainFormController implements Initializable{
    /** This is an Array List that holds Parts.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /** This is an Array List that holds Products.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /** This is an Array List that holds InHouse objects.
     */
    public static ObservableList<InHouse> allInHouse = FXCollections.observableArrayList();
    /** This is an Array List that holds Outsourced objects.
     */
    public static ObservableList<Outsourced> allOutsourced = FXCollections.observableArrayList();

    /** This sets the stage.
     */
    private Stage stage;
    /** This sets the scene.
     */
    private Scene scene;
    /** This sets the root.
     */
    private Parent root;


    //Part & Product Table Controls =========================================================================

    /** This is the JavaFX ID for the part table.
     */
    @FXML
    public TableView<Part> partTable;
    /** This is the JavaFX ID for the partId column.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    /** This is the JavaFX ID for the partName column.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;
    /** This is the JavaFX ID for the partInventory column.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;
    /** This is the JavaFX ID for the partPrice column.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    /** This is the JavaFX ID for the product table.
     */
    @FXML
    private TableView<Product> productTable;
    /** This is the JavaFX ID for the productId column.
     */
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    /** This is the JavaFX ID for the productName column.
     */
    @FXML
    private TableColumn<Product, String> productNameColumn;
    /** This is the JavaFX ID for the productInventory column.
     */
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;
    /** This is the JavaFX ID for the productPrice column.
     */
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    /** This keeps track of the id to assign to all parts and objects.
      FUTURE ENHANCEMENT Originally I was going to use a for loop to set the initial starting value, but found that It is optional
     The idCounter should be equal to the number of starting parts and products. We should implement a more diverse way of generating an ID.
     */
    public static int idCounter = 6;
    /** TThis shows an error message when trying to delete products with associated parts
     */
    @FXML
    private Label exceptionLabel;

    /** This method sets up the two tables on the mainForm page.
     On initialization the method will set up the Part table and Product table.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part ,Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        partTable.setItems(Inventory.getAllParts());

        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product ,Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        productTable.setItems(Inventory.getAllProducts());

    }
    /** This method sets up a system to generate unique ID's for all parts and products.
     Everytime a part or product is created, the ID generator will increase by 1 and use this to create a new ID. FUTURE ENHANCEMENT
     We should include a way of making each ID unique and random, not just increasing by 1.
     */
    public static int idGenerator(){
        idCounter += 1;
        return idCounter;
    }


    // Controls the buttons for pane switching =========================================================
    //------------- add/modify buttons
    /** This method takes the user to the addPart page.
     On clicking the button connected to this method, it will direct the user to the proper page.
     */
    public void switchToAddPartForm(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addPartForm.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /** This method takes the user to the modifyPart page.
     On clicking the button connected to this method, it will direct the user to the proper page.
     */
    public void switchToModifyPartForm(ActionEvent e) throws IOException {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        // Checks to see if table item selected
        if (!(selectedPart == null)) {
            modifyPartFormController.idData = selectedPart.getId();
            modifyPartFormController.nameData = selectedPart.getName();
            modifyPartFormController.inventoryData = selectedPart.getStock();
            modifyPartFormController.priceData = selectedPart.getPrice();
            modifyPartFormController.maxData = selectedPart.getMax();
            modifyPartFormController.minData = selectedPart.getMin();

            root = FXMLLoader.load(getClass().getResource("modifyPartForm.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            exceptionLabel.setText("No Part selected to modify");
        }
    }
    /** This method takes the user to the addProduct page.
     On clicking the button connected to this method, it will direct the user to the proper page.
     */
    public void switchToAddProductForm(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addProductForm.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /** This method takes the user to the modifyProduct page.
     On clicking the button connected to this method, it will direct the user to the proper page.
     */
    public void switchToModifyProductForm(ActionEvent e) throws IOException {
        Product selectedPart = productTable.getSelectionModel().getSelectedItem();

        // Checks to see if table item selected
        if (!(selectedPart == null)) {
            modifyProductFormController.selectedProduct = selectedPart;

            modifyProductFormController.idData = selectedPart.getId();
            modifyProductFormController.nameData = selectedPart.getName();
            modifyProductFormController.inventoryData = selectedPart.getStock();
            modifyProductFormController.priceData = selectedPart.getPrice();
            modifyProductFormController.maxData = selectedPart.getMax();
            modifyProductFormController.minData = selectedPart.getMin();

            root = FXMLLoader.load(getClass().getResource("modifyProductForm.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            exceptionLabel.setText("No Product selected to modify");
        }

    }
    //==================Exit Button=================================================
    /** This method exits the program.
     On clicking the button connected to this method, the method will terminate the program.
     */
    public void exitButton() throws IOException {
        System.exit(0);
    }
    //=================Delete Button================================================
    /** This method deletes a part from the part table.
     Will delete selected part but uses a system of checks for error handling.
     RUNTIME ERROR Didn't realize that an error was occuring when pressing delete while selectedPart was null. We fixed this
     by checking first to see if selectedPart is not null and then only continuing with the rest of the code if it has a value.
     */
    public void deleteButtonPart(ActionEvent e) throws IOException {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            exceptionLabel.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Parts");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this part?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        } else {
            exceptionLabel.setText("No Part selected to delete");
        }
    }
    //Checks to see if the product has no associated parts before deleting ====================================
    /** Used to count the number of associated parts belonging to a product.
     */
    int count = 0;
    /** This method deletes a part from the product table.
     Will delete selected product but uses a system of checks for error handling.
     RUNTIME ERROR Didn't realize that an error was occuring when pressing delete while selectedProduct was null. We fixed this
     by checking first to see if selectedProduct is not null and then only continuing with the rest of the code if it has a value.
     */
    public void deleteButtonProduct(ActionEvent e) throws IOException {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        count = 0;
        if (selectedProduct != null) {
            exceptionLabel.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Products");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this product?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                count = countAssociatedParts(selectedProduct);
                if (count == 0) {
                    Inventory.deleteProduct(selectedProduct);
                } else {
                    exceptionLabel.setText("This Product has parts");
                }
            }
        } else {
            exceptionLabel.setText("No Product selected to delete");
        }

    }
    //Counts every part associated with selected product,
    //if it has more than 0 parts it will return a value higher than zero thus,
    //failing the if statement and throwing the error message
    /** This method returns the number of associated parts the product holds.
     This method counts the number of associated parts to the product. Used for checking if deleting the product is allowed.
     @param selectedPart This is the product to be checked.
     @return Returns the number of associated parts in the product.
     */
    public int countAssociatedParts(Product selectedPart){
        int value = 0;
        for(Part x : selectedPart.getAllAssociatedParts()){
            value += 1;
        }
        return value;
    }

    //Search Bar for Part Table ===============================================================
    /** This member holds the input inside the search bar for the Part table.
     */
    @FXML
    private TextField searchPartInput;

    /** This method regulates how the app searches through the part table.
     When text is entered in the search bar, the method will run through checks to filter out the table.
     */
    public void searchPartTable(ActionEvent e){
        exceptionLabel.setText("");
        String q = searchPartInput.getText();
        ObservableList<Part> resultParts =  Inventory.lookupPart(q);
        try {
            if (resultParts.size() == 0) {
                int idLookUp = Integer.parseInt(q);
                Part result = Inventory.lookupPart(idLookUp);
                if (result != null) {
                    resultParts.add(result);
                }
            }
        }
        catch (NumberFormatException er){
            //This is because entering a letter in the search bar causes an error, but we just need to ignore it
        }
        if (resultParts.size() == 0){
            exceptionLabel.setText("No results found");
        }
        partTable.setItems(resultParts);

    }

    //Search Bar for Product Table ===============================================================
    /** This member holds the input inside the search bar for the Product table.
     */
    @FXML
    private TextField searchProductInput;

    /** This method regulates how the app searches through the product table.
     When text is entered in the search bar, the method will run through checks to filter out the table.
     */
    public void searchProductTable(ActionEvent e){
        exceptionLabel.setText("");
        String q = searchProductInput.getText();
        ObservableList<Product> resultProducts =  Inventory.lookupProduct(q);
        try {
            if (resultProducts.size() == 0) {
                int idLookUp = Integer.parseInt(q);
                Product result = Inventory.lookupProduct(idLookUp);
                if (result != null) {
                    resultProducts.add(result);
                }
            }
        }
        catch (NumberFormatException er){
            //This is because entering a letter in the search bar causes an error, but we just need to ignore it
        }
        if (resultProducts.size() == 0){
            exceptionLabel.setText("No results found");
        }
        productTable.setItems(resultProducts);
    }
}