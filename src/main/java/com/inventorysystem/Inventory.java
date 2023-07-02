package com.inventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is the Inventory controller.
 @author Carlos Rodriguez
 */
public class Inventory {
    /** This is an array list for all Part objects.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /** This is an array list for all Product objects.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /** This method adds a new part to the table.
     Creates a new part.
     @param newPart Part to be added.
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    /** This method adds a new product to the table.
     Creates a new product.
     @param newProduct Part to be added.
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }


    /** This method looks up the part with it's corresponding ID.
    Entering an ID in the search box will result in finding any matching Parts. RUNTIME ERROR I had trouble
     figuring out how to return the part. Issue was resolved by creating an observable list inside the method.
    @param partId A part's identification number.
    @return returns the part with matching id.
    */
    public static Part lookupPart(int partId){
        ObservableList<Part> allParts = getAllParts();
        for (int i = 0; i < allParts.size(); i++){
            Part x = allParts.get(i);
            if (x.getId() == partId){
                return x;
            }
        }

        return null;
    }

    /** This method looks up the product with it's corresponding ID.
     Entering an ID in the search box will result in finding any matching Products. RUNTIME ERROR I had trouble
     figuring out how to return the part. Issue was resolved by creating an observable list inside the method.
     @param productId A product's identification number.
     @return returns the product with matching id.
     */
    public static Product lookupProduct(int productId){
        ObservableList<Product> allProducts = getAllProducts();
        for (int i = 0; i < allProducts.size(); i++){
            Product x = allProducts.get(i);
            if (x.getId() == productId){
                return x;
            }
        }

        return null;
    }

    /** This method looks up the part if a string matches the name of the part.
     Entering a String in the search box will result in filtering any matching Parts. RUNTIME ERROR I had trouble
     figuring out how to return the part. Issue was resolved by creating an observable list inside the method.
     @param partName A part's name.
     @return returns the part with letters in name.
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();
        for (Part x: allParts){
            if (x.getName().contains(partName)){
                namedParts.add(x);
            }
        }
        return namedParts;
    }

    /** This method looks up the product if a string matches the name of the product.
     Entering a String in the search box will result in filtering any matching Products. RUNTIME ERROR I had trouble
     figuring out how to return the product. Issue was resolved by creating an observable list inside the method.
     @param productName A part's name.
     @return returns the product with letters in name.
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = getAllProducts();
        for (Product x: allProducts){
            if (x.getName().contains(productName)){
                namedProducts.add(x);
            }
        }
        return namedProducts;
    }
    /** This method updates a part's values.
     * @param selectedPart This is the part to be modified.
     * @param index This is the part's id.
     */
    public static void updatePart(Part selectedPart, int index){
        for (Part x : allParts) {

            if (x.getId() == selectedPart.getId()) {
                x.setName(selectedPart.getName());
                x.setStock(selectedPart.getStock());
                x.setPrice(selectedPart.getPrice());
                x.setMax(selectedPart.getMax());
                x.setMin(selectedPart.getMin());
            }
            else if(!(x.getId() == selectedPart.getId())){
            }
        }
    }
    /** This method updates a product's values.
     * @param selectedProduct This is the product to be modified.
     * @param index This is the product's id.
     */
    public static void updateProduct(Product selectedProduct, int index){
        for (Product x : allProducts) {

            if (x.getId() == selectedProduct.getId()) {
                x.setName(selectedProduct.getName());
                x.setStock(selectedProduct.getStock());
                x.setPrice(selectedProduct.getPrice());
                x.setMax(selectedProduct.getMax());
                x.setMin(selectedProduct.getMin());
            }
            else if(!(x.getId() == selectedProduct.getId())){
            }
        }
    }
    /** This method deletes a part.
     * @param selectedPart This is the part to be deleted.
     * @return Returns true if object found and then removes it.
     */
    public static boolean deletePart(Part selectedPart){

        if (selectedPart == null) {
            return false;
        }
        allParts.remove(selectedPart);
        return true;
    }

    /** This method deletes a product.
     * @param selectedProduct This is the product to be deleted.
     * @return Returns true if object found and then removes it.
     */
    public static boolean deleteProduct(Product selectedProduct){
        if (selectedProduct == null) {
            return false;
        }
        allProducts.remove(selectedProduct);
        return true;
    }

    /** This method returns the array list allParts.
     * @return Returns allParts array list.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    /** This method returns the array list allProducts.
     * @return Returns allProducts array list.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }


}
