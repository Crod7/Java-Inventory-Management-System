package com.inventorysystem;
/** This class is a child of the Part class. This class comes with an extra attribute companyName which is a String.
 * @author Carlos Rodriguez
 */
public class Outsourced extends Part {
    /** This holds a String to be added to only the Outsourced object and is used to show which company supplied the object.
     */
    private String companyName;
    /** This constructor requires an id, name, price, stock, min, max and machineId values.
     @param id This is automatically generated unique ID.
     @param name This is a string value that holds the objects name.
     @param price This is a double value that holds the objects price.
     @param stock This is an integer value that displays stock in inventory.
     @param min This is an integer that represents the minimal amount that can be purchased at one time.
     @param max This is an integer that represents the maximum amount that can be purchased at one time.
     @param companyName Unique to Outsourced objects, is a user entered String to show company supplying this object.
     */
    Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super (id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /** Sets the companyName value.
     @param name This is the argument used to set the name.
     */
    public void setCompanyName(String name){
        companyName = name;
    }
    /** Gets the companyName value.
     @return Returns the companyName value of this object.
     */
    public String getCompanyName(){
        return companyName;
    }
}
