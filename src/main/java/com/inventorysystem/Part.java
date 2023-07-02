package com.inventorysystem;
/**
* Supplied class Part.java 
 */

/** This is the Part class that is the parent of InHouse and Outsourced objects.
 *  This class is also abstract.
 * @author Carlos Rodriguez
 */
public abstract class Part {
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
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** This gets the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** This sets the id.
     * @param id Integer value to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** This gets the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** This sets the name.
     * @param name String value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** This gets the price.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /** This sets the price.
     * @param price Double value to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** This gets the stock.
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /** This sets the stock.
     * @param stock Integer value to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This gets the min.
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /** This sets the min.
     * @param min Integer value to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** This gets the max.
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /** This sets the max.
     * @param max Integer value to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
}