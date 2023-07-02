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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the controller for the modifyPart page.
 This is the method that runs when the modifyPart page is opened.
 @author Carlos Rodriguez
 */
public class modifyPartFormController implements Initializable{
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
    /** This is the JavaFX ID for the radio button text field.
     */
    @FXML
    private TextField radioButtonInput;

    //Controls the radio buttons and the label to correspond to radio button selected---------
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
    //The labels below are for exception handling for the text fields =========================
    /** This label displays an error message when the add button fails to pass all checks.
     */
    @FXML
    private Label exceptionLabel;
    /** This label displays an error message when an Integer isn't used for its text field.
     */
    @FXML
    private Label inventoryErrorLabel;
    /** This label displays an error message when an Double isn't used for its text field.
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
    /** An array list that holds temporary objects to be used for removing from the target array list.
     * RUNTIME ERROR This was the work-around used to solve the Null Pointer Exception.
     */
    public static ObservableList<InHouse> allInHouseRemove = FXCollections.observableArrayList();
    /** An array list that holds temporary objects to be used for removing from the target array list.
     * RUNTIME ERROR This was the work-around used to solve the Null Pointer Exception.
     */
    public static ObservableList<Outsourced> allOutsourcedRemove = FXCollections.observableArrayList();

    /** This method changes the label for Machine ID to Company Name and vice-versa.
     Depending on which radio button is selected, will determine the text displayed next to the text field.*/
    public void changeText(ActionEvent e) {
        if (inHouseBtn.isSelected()) {
            myLabel.setText("Machine ID");
            selection = 1;
        } else if (outsourcedBtn.isSelected()) {
            myLabel.setText("Company Name");
            selection = 2;
        }
    }


    // The Cancel Button -----------------------------------------------------------------------
    /** The cancel button returns you to the main page.
     Once pressed the user will return to the main page and not save any new information.*/
    public void switchToMainForm(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // This adds to the global list so that the entire program can call the radio data into it's modify pane textField=================
    /** This method adds a Part to the InHouse list.
     The purpose of this method is to change a part from Outsourced to InHouse.
     @param newInHouse This is the Part to accept the new state.
     */
    public static void addInHouse(InHouse newInHouse){
        mainFormController.allInHouse.add(newInHouse);
    }
    /** This method adds a Part to the Outsourced list.
     The purpose of this method is to change a part from InHouse to Outsourced
     @param newOutsourced This is the Part to accept the new state.
     */
    public static void addOutsourced(Outsourced newOutsourced){
        mainFormController.allOutsourced.add(newOutsourced);
    }
    // This gathers the rest of the textField data, no globals needed============================================
    /** This method fills out all the text fields on the modifyPart page.
     On initialization the method will grab data from the selected Part from the main page and fill out all the text fields.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idInput.setText(String.valueOf(idData));
        nameInput.setText(nameData);
        inventoryInput.setText(String.valueOf(inventoryData));
        priceInput.setText(String.valueOf(priceData));
        maxInput.setText(String.valueOf(maxData));
        minInput.setText(String.valueOf(minData));
        for(InHouse x: mainFormController.allInHouse) {
            if (x.getId() == idData){
                selection = 1;
                inHouseBtn.setSelected(true);
                myLabel.setText("Machine ID");
                radioButtonInput.setText(String.valueOf(loadRadioInfoInHouse(idData)));
            }
        }
        for(Outsourced x : mainFormController.allOutsourced){
            if (x.getId() == idData){
                selection = 2;
                outsourcedBtn.setSelected(true);
                myLabel.setText("Company Name");
                radioButtonInput.setText(String.valueOf(loadRadioInfoOutsourced(idData)));
            }
        }
    }
    // Loading the textFields on start of modify window========================================
    /** The value used to hold the Machine ID and be returned using loadRadioInfoInHouse() to fill out the text field at the start of the modifyPart page.
     */
    int y = 0;
    /** This method gets the machine ID that belongs to a part with the matching id.
     The InHouse list is iterated through to find the matching Part to retrieve its machine ID data.
     @param i the id from the Part entered that will be searched for.
     @return Returns the machine ID.
     */
    public int loadRadioInfoInHouse(int i) {

        for (InHouse x : mainFormController.allInHouse) {
            if (i == x.getId()) {
                y = x.getMachineId();
            }
        }
        return y;
    }
    /** The value used to hold the company name and be returned using loadRadioInfoOutsourced() to fill out the text field at the start of the modifyPart page.
     */
    String z = "";
    /** This method gets the company name that belongs to a part with the matching id.
     The Outsourced list is iterated through to find the matching Part to retrieve its company name data.
     @param i the id from the Part entered that will be searched for.
     @return Returns the company name.
     */
    public String loadRadioInfoOutsourced(int i) {
        for (Outsourced x : mainFormController.allOutsourced) {
            if (i == x.getId()) {
                z = x.getCompanyName();
            }
        }
        return z;
    }

    /** This method removes the Part from the InHouse list.
     The selected part is added to a temporary list that is later used to remove from the original list.
     RUNTIME ERROR an error that occured was the NullPointerException. This was resolved by creating a temporary array list, rather than altering the target array list while iterating through it.
     @param i the id from the Part entered that will be removed.
     */
    public void deleteInHouse(int i){
        for (InHouse x : mainFormController.allInHouse) {
            if (i == x.getId()) {
                allInHouseRemove.add(x);
            }
            else if(i != x.getId()){
            }
        }
        System.out.println("Done Deleteing From InHouse List");
    }
    /** This method removes the Part from the Outsourced list.
     The selected part is added to a temporary list that is later used to remove from the original list.
     RUNTIME ERROR an error that occured was the NullPointerException. This was resolved by creating a temporary array list, rather than altering the target array list while iterating through it.
     @param i the id from the Part entered that will be removed.
     */
    public void deleteOutsourced(int i){
        for (Outsourced x : mainFormController.allOutsourced) {
            if (i == x.getId()) {
                allOutsourcedRemove.add(x);
            }
            else if(i != x.getId()){
            }
        }
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
            int id = idData;
            // if modified object is switched from outsourced to InHouse,
            // the object will add it to the InHouse list but remove it from the Outsourced list
            for (Outsourced obj : mainFormController.allOutsourced){
                if(idData == obj.getId()){
                    deleteOutsourced(idData);
                    deleteInHouse(idData);
                }
                else if(!(idData == obj.getId())){
                }
            }
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

            if (errorCheck) {
                mainFormController.allInHouse.removeAll(allInHouseRemove);
                allInHouseRemove.clear();
                mainFormController.allOutsourced.removeAll(allOutsourcedRemove);
                allOutsourcedRemove.clear();
                InHouse object1 = new InHouse(id, name, price, inventory, min, max, radio1);
                mainFormController.allInHouse.add(object1);
                Inventory.updatePart(object1, idData);

                root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        }
        //selection 2 is when OutSourced is selected
        else {
            int id = idData;
            // if modified object is switched from InHouse to Outsourced,
            // the object will add it to the OutSourced list but remove it from the InHouse list
            for (InHouse obj : mainFormController.allInHouse){
                if(idData == obj.getId()){
                    deleteOutsourced(idData);
                    deleteInHouse(idData);
                }
                else if(!(idData == obj.getId())){
                }
            }
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
                mainFormController.allInHouse.removeAll(allInHouseRemove);
                allInHouseRemove.clear();
                mainFormController.allOutsourced.removeAll(allOutsourcedRemove);
                allOutsourcedRemove.clear();
                Outsourced object1 = new Outsourced(id, name, price, inventory, min, max, radio2);
                mainFormController.allOutsourced.add(object1);
                Inventory.updatePart(object1, idData);

                root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

    }
}