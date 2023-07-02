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

/** This is the controller for the modifyProduct page.
 The purpose of this controller is to modify existing products and to add associated parts to the products.
 @author Carlos Rodriguez
 */
public class modifyProductFormController implements Initializable {
    /** When starting the modifyProduct page, this variable holds the product selected to modify from the main page Product table.
     */
    public static Product selectedProduct;
    /** This sets the stage.
     */
    private Stage stage;
    /** This sets the scene.
     */
    private Scene scene;
    /** This sets the root.
     */
    private Parent root;
    // Controls the input for the Text Fields-------------------------------------------------
    /** This is the JavaFX ID for the id text field.
     */
    @FXML
    private TextField idInput;
    /** This is set to the selected object's id value and is then passed to idInput.
     */

    public static int idData;
    /** This is the JavaFX ID for the name text field.
     */
    @FXML
    private TextField nameInput;
    /** This is set to the selected object's name value and is then passed to nameInput.
     */

    public static String nameData;
    /** This is the JavaFX ID for the inventory text field.
     */
    @FXML
    private TextField inventoryInput;
    /** This is set to the selected object's inventory value and is then passed to inventoryInput.
     */

    public static int inventoryData;
    /** This is the JavaFX ID for the price text field.
     */
    @FXML
    private TextField priceInput;
    /** This is set to the selected object's price value and is then passed to priceInput.
     */

    public static double priceData;
    /** This is the JavaFX ID for the max text field.
     */
    @FXML
    private TextField maxInput;
    /** This is set to the selected object's max value and is then passed to maxInput.
     */

    public static int maxData;
    /** This is the JavaFX ID for the min text field.
     */
    @FXML
    private TextField minInput;
    /** This is set to the selected object's min value and is then passed to minInput.
     */

    public static int minData;

    //The labels below are for exception handling for the text fields =========================
    /** This label displays an error message when the add button fails to pass all checks.
     */
    @FXML
    private Label exceptionLabel;
    /** This label displays an error message when an Integer isn't used for its text field.
     */
    @FXML
    private Label inventoryErrorLabel;
    /** This label displays an error message when a Double isn't used for its text field.
     */
    @FXML
    private Label priceErrorLabel;
    /** This label displays an error message when an Integer isn't used for its text field.
     */
    @FXML
    private Label maxErrorLabel;
    /** This label displays an error message when an Integer isn't used for its text field.
     */
    @FXML
    private Label minErrorLabel;

    // THE PART TABLE VARIABLES ==============================================================
    /** This is the JavaFX ID for the part table.
     */
    @FXML
    private TableView<Part> partTable;
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

    // THE Associated Parts TABLE VARIABLES ==============================================================
    /** This is the JavaFX ID for the associatedParts table.
     */
    @FXML
    private TableView<Part> associatedPartTable;
    /** This is the JavaFX ID for the associatedParts partId column.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;
    /** This is the JavaFX ID for the associatedParts partName column.
     */
    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;
    /** This is the JavaFX ID for the associatedParts partInventory column.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryColumn;
    /** This is the JavaFX ID for the associatedParts partPrice column.
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    /** Checks to see if text input fails error check, true states the input passes, false states the input failed the error check.
     */
    private boolean errorCheck = true;
    /** This method adds an associated part to the product.
     Each product has its own associated parts, this adds it to that specific product and displays it to the table.
     RUNTIME ERROR had trouble adding parts to a product without it applying to all tables, this was fixed when we gave each product its own private array list.
     */
    public void addAssociatedPartBtn(){
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            selectedProduct.addAssociatedPart(selectedPart);
        } else {
            exceptionLabel.setText("No Part selected, unable to add");
        }
    }
    /** This method deletes an associated part from the product.
     Each product has its own associated parts, this deletes it from that specific product and displays the new information on the table.
     */
    public void removeAssociatedPartBtn(){
        Part tableItem = associatedPartTable.getSelectionModel().getSelectedItem();
        if (tableItem != null) {
            exceptionLabel.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Associated Parts");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this part?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                selectedProduct.deleteAssociatedPart(tableItem);
            }
        } else {
            exceptionLabel.setText("No Part was selected, unable to remove");
        }
    }

    // This gathers the rest of the textField data, no globals needed============================================
    /** This method sets up the two tables on the modifyProduct page.
     On initialization the method will set up the Part table and Product table.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // CREATES THE PART TABLE
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part ,Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        partTable.setItems(Inventory.getAllParts());
        // CREATES THE Associated Parts TABLE
        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<Part ,Integer>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        associatedPartTable.setItems(selectedProduct.getAllAssociatedParts());




        // ADDS THE INPUT TO THE TEXT FIELDS
        idInput.setText(String.valueOf(idData));
        nameInput.setText(nameData);
        inventoryInput.setText(String.valueOf(inventoryData));
        priceInput.setText(String.valueOf(priceData));
        maxInput.setText(String.valueOf(maxData));
        minInput.setText(String.valueOf(minData));
    }
    // THE SAVE BUTTON   ========================================================================
    /** ID value that will be generated for this object.
     */
    int id;
    /** Name value that will be generated for this object.
     */
    String name;
    /** Inventory value that will be generated for this object.
     */
    int inventory;
    /** Price value that will be generated for this object.
     */
    double price;
    /** Max value that will be generated for this object.
     */
    int max;
    /** Min value that will be generated for this object.
     */
    int min;
    /** The save button returns you to the main page and adds to the Product table.
     Once pressed this method will run multiple checks to verify the input before adding to the Product table and then return the user to the main page.*/
    public void saveBtn(ActionEvent e) throws IOException {
        //Helps to check if the code runs without error,
        //It also resets the debug menu and errorCheck to test a new entry in test fields
        errorCheck = true;
        exceptionLabel.setText("");
        inventoryErrorLabel.setText("");
        priceErrorLabel.setText("");
        maxErrorLabel.setText("");
        minErrorLabel.setText("");
        int id = idData;
        //========================================================================
        name = nameInput.getText();
        if (name == ""){
            errorCheck = false;
            exceptionLabel.setText("Exception: No data in name field");
        }
        //========================================================================
        try {
            inventory = Integer.parseInt(inventoryInput.getText());
        } catch (Exception err){
            inventoryErrorLabel.setText("Inventory is not an integer");
            errorCheck = false;
        }
        //========================================================================
        try {
            price = Double.parseDouble(priceInput.getText());
        } catch (Exception err){
            priceErrorLabel.setText("Price is not a double");
            errorCheck = false;
        }
        //========================================================================
        try {
            max = Integer.parseInt(maxInput.getText());
        } catch (Exception err){
            maxErrorLabel.setText("Max is not an integer");
            errorCheck = false;
        }
        //========================================================================
        try {
            min = Integer.parseInt(minInput.getText());
        } catch (Exception err){
            minErrorLabel.setText("Min is not an integer");
            errorCheck = false;
        }
        //========================================================================
        //This code checks to see that INV is between MAX and MIN and that MIN is less than Max
        if (!(inventory <= max && inventory >= min)){
            exceptionLabel.setText("Exception: INV must be between MIN and MAX");
            errorCheck = false;
        }
        if (min > max){
            exceptionLabel.setText("Exception: Min must be less than Max");
            errorCheck = false;
        }
        //========================================================================

        if (errorCheck) {
            Product object1 = new Product(id, name, price, inventory, min, max);
            Inventory.updateProduct(object1, id);
            root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    //  THE CANCEL BUTTON =====================================================================
    /** The cancel button returns you to the main page.
     Once pressed the user will return to the main page and not save any new information.*/
    public void switchToMainForm(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Search Bar for Part Table ===============================================================
    /** This member holds the input inside the search bar for the Part table.
     */
    @FXML
    private TextField searchPartInput;

    /** This method regulates how the app searches through the part table.
     When text is entered in the search bar, the method will run through checks to filter out the table.*/
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
}