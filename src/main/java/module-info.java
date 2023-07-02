module com.inventorysystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.inventorysystem to javafx.fxml;
    exports com.inventorysystem;
}