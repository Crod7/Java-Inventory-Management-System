package com.inventorysystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This is the controller for the addPart page.
 This is the method that runs when the addPart page is opened.
 @author Carlos Rodriguez
 */
public class addPartFormController{
    /** This sets the stage.
     */
    private Stage stage;
    /** This sets the scene.
     */
    private Scene scene;
    /** This sets the root.
     */
    private Parent root;

    /** The purpose of selection int is to determine what kind of input to accept when selecting a radio button.
     */
    private int selection = 1;


    // Controls the input for the Text Fields =======================================================

    /** This is the JavaFX ID for the name text field.
     */
    @FXML
    private TextField nameInput;
    /** This is the JavaFX ID for the inventory text field.
     */
    @FXML
    private TextField inventoryInput;
    /** This is the JavaFX ID for the price text field.
     */
    @FXML
    private TextField priceInput;
    /** This is the JavaFX ID for the max text field.
     */
    @FXML
    private TextField maxInput;
    /** This is the JavaFX ID for the min text field.
     */
    @FXML
    private TextField minInput;
    /** This is the JavaFX ID for the radio button text field.
     */
    @FXML
    private TextField radioButtonInput;

    /** This is the JavaFX ID for the InHouse radio button.
     */
    @FXML
    private RadioButton inHouseBtn;
    /** This is the JavaFX ID for the Outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedBtn;
    /** This label changes text depending on which radio button is selected.
     */
    @FXML
    private Label myLabel;
    // The labels below are for exception handling for the text fields =========================
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



    /** Checks to see if text input fails error check, true states the input passes, false states the input failed the error check.
     */
    private boolean errorCheck = true;


    /** This method changes the label for Machine ID to Company Name and vice-versa.
     Depending on which radio button is selected, will determine the text displayed next to the text field.*/
    public void changeText(ActionEvent e){
        if (inHouseBtn.isSelected()){
            myLabel.setText("Machine ID");
            selection = 1;
        }
        else if (outsourcedBtn.isSelected()){
            myLabel.setText("Company Name");
            selection = 2;
        }
    }
    // The Cancel Button -----------------------------------------------------------------------
    /** The cancel button returns you to the main page.
     Once pressed the user will return to the main page and not save any new information.*/
    public void switchToMainForm(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // THE SAVE BUTTON   ========================================================================
    /** ID value that will be generated for this object.
     */
    int id;
    /** Name value that will be set to object.
     */
    String name;
    /** Inventory value that will be set to object.
     */
    int inventory;
    /** Price value that will be set to object.
     */
    double price;
    /** Max value that will be set to object.
     */
    int max;
    /** Min value that will be set to object.
     */
    int min;
    /** Machine ID value that will be set to object.
     */
    int radio1;
    /** Company Name String value that will be set to object.
     */
    String radio2;

    /** The save button returns you to the main page and adds to the Part table.
     Once pressed this method will run multiple checks to verify the input before adding to the Part table and then return the user to the main page.*/

    public void saveBtn(ActionEvent e) throws IOException {
        //Helps to check if the code runs without error,
        //It also resets the debug menu and errorCheck to test a new entry in test fields
        errorCheck = true;
        exceptionLabel.setText("");
        inventoryErrorLabel.setText("");
        priceErrorLabel.setText("");
        maxErrorLabel.setText("");
        minErrorLabel.setText("");
        //selection 1 is when InHouse is selected
        if (selection == 1) {
            id = mainFormController.idGenerator();
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
            try {
                radio1 = Integer.parseInt(radioButtonInput.getText());
            } catch (Exception err){
                exceptionLabel.setText("Machine ID is not an integer");
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


            //PUT METHOD HERE
            if (errorCheck) {
                InHouse object1 = new InHouse(id, name, price, inventory, min, max, radio1);
                Inventory.addPart(object1);
                modifyPartFormController.addInHouse(object1);
                root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else{
                System.out.println("Failed the error check");
            }


        }
        //selection 2 is when OutSourced is selected
        else {
            id = mainFormController.idGenerator();
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
            radio2 = radioButtonInput.getText();
            if (radio2 == ""){
                errorCheck = false;
                exceptionLabel.setText("Exception: No data in Company Name Field");
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
                Outsourced object1 = new Outsourced(id, name, price, inventory, min, max, radio2);
                Inventory.addPart(object1);
                modifyPartFormController.addOutsourced(object1);
                root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Failed the error check");
            }
        }

    }
}