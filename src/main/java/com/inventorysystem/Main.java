//The JavaDoc folder is located at
//    C:\Users\karNmar\Desktop\InventorySystem\javaDoc


/** The JavaDoc folder is located at C:\Users\karNmar\Desktop\InventorySystem\javaDoc\index.html .
 */
package com.inventorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** The JavaDoc folder is located at InventorySystem\javaDoc\index.html , the JavaDoc folder has also been submitted separately named JavaDoc.rar.
 * This class creates an app that manages the inventory by controlling two tables.
 * RUNTIME EXCEPTION an error that occurred was the NullPointerException. This was resolved by creating a temporary array list, rather than altering the target array list while iterating through it. The temporary array list was used to remove from the original list using a method created for deleting objects in an array list by comparing two array lists.
 * FUTURE ENHANCEMENT A future enhancement that could improve the functionality of the program is to improve the ID generation method of Parts and Products. Instead of just incrementing the ID's by 1 for each new creation, a method should be created to randomize unique IDs and assign them and form a system so that these unique IDs don't repeat for multiple objects.
 * @author Carlos Rodriguez
 * */
public class Main extends Application {

    /** This is the main method, The JavaDoc folder is located at InventorySystem\javaDoc\index.html , the JavaDoc folder has also been submitted separately named JavaDoc.rar.
     This is the method that runs at the start of the program. */
    public static void main(String[] args) {
        InHouse obj1 = new InHouse(1, "Brakes", 39.99, 5, 1, 30, 1111);
        InHouse obj2 = new InHouse(2, "Wheels", 49.99, 10, 1, 25, 2222);
        Outsourced obj3 = new Outsourced(3, "Seat", 89.99, 8, 1, 20, "Carlos' Chairs");
        Product obj4 = new Product(4, "Bike", 199.99, 12, 1, 20);
        Outsourced obj5 = new Outsourced(5, "Mirror", 19.99, 20, 1, 35, "Glass Co.");
        Product obj6 = new Product(6, "Car", 19999.99, 3, 1, 5);

        modifyPartFormController.addInHouse(obj1);
        modifyPartFormController.addInHouse(obj2);
        modifyPartFormController.addOutsourced(obj3);
        modifyPartFormController.addOutsourced(obj5);
        Inventory.addPart(obj1);
        Inventory.addPart(obj2);
        Inventory.addPart(obj3);
        Inventory.addProduct(obj4);
        Inventory.addPart(obj5);
        Inventory.addProduct(obj6);
        launch();
    }
    /** This is the start method.
     This is the method that opens the initial UI page which is the mainForm page. */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

    }
}