package com.inventorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This is the Product class that is used as a template for all Product objects.
 * @author Carlos Rodriguez
 */
public class Product {
    /** This is an Observable List of Part objects. This is used so that each individual product can carry it's own array list of associated parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /** ID value that will be set to object.
     */
    private int id;
    /** Name value that will be set to object.
     */
    private String name;
    /** Price value that will be set to object.
     */
    private double price;
    /** Stock value that will be set to object.
     */
    private int stock;
    /** Min value that will be set to object.
     */
    private int min;
    /** Max value that will be set to object.
     */
    private int max;

    /** This constructor requires an id, name, price, stock, min and max.
     @param id This is automatically generated unique ID.
     @param name This is a string value that holds the objects name.
     @param price This is a double value that holds the objects price.
     @param stock This is an integer value that displays stock in inventory.
     @param min This is an integer that represents the minimal amount that can be purchased at one time.
     @param max This is an integer that represents the maximum amount that can be purchased at one time.
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /** This sets the id.
     * @param setId Integer value to set
     */
    public void setId(int setId){
        id = setId;
    }
    /** This sets the name.
     * @param setName String value to set
     */
    public void setName(String setName){
        name = setName;
    }
    /** This sets the price.
     * @param setPrice Double value to set
     */
    public void setPrice(double setPrice){
        price = setPrice;
    }
    /** This sets the stock.
     * @param setStock Integer value to set
     */
    public void setStock(int setStock){
        stock = setStock;
    }
    /** This sets the min.
     * @param setMin Integer value to set
     */
    public void setMin(int setMin){
        min = setMin;
    }
    /** This sets the max.
     * @param setMax Integer value to set
     */
    public void setMax(int setMax){
        max = setMax;
    }
    /** This gets the id.
     * @return the id
     */
    public int getId(){
        return id;
    }
    /** This gets the name.
     * @return the name
     */
    public String getName(){
        return name;
    }
    /** This gets the price.
     * @return the price
     */
    public double getPrice(){
        return price;
    }
    /** This gets the stock.
     * @return the stock
     */
    public int getStock(){
        return  stock;
    }
    /** This gets the min.
     * @return the min
     */
    public int getMin(){
        return min;
    }
    /** This gets the max.
     * @return the max
     */
    public int getMax(){
        return max;
    }


    /** This method takes a Part object and adds it to an individual Product object.
     * @param part Adds part object to the associatedParts list
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
        System.out.println(part.getName()+" was added");
    }
    /** This method takes a Part object and returns a boolean confirming it's removal.
     * @param selectedAssociatedPart Part to be checked to see if it exists inside the Product's associatedPart list so it can be removed.
     * @return returns a boolean confirming if the item exists inside the Product's associatedPart list.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        for (Part x : associatedParts){
            if (selectedAssociatedPart.getId() == x.getId()){
                associatedParts.remove(selectedAssociatedPart);

                return true;
            }
        }
        return false;
    }

    /** This method returns an array list unique to each product.
     * @return Returns the associatedParts array list
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}
