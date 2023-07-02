package com.inventorysystem;
/** This class is a child of the Part class. This class comes with an extra attribute machineID which is an integer.
 * @author Carlos Rodriguez
 */
public class InHouse extends Part {
    /** This holds an integer to be added to only the InHouse object and is used as a unique identifier for this object.
     */
    private int machineId;
    /** This constructor requires an id, name, price, stock, min, max and machineId values.
     @param id This is automatically generated unique ID.
     @param name This is a string value that holds the objects name.
     @param price This is a double value that holds the objects price.
     @param stock This is an integer value that displays stock in inventory.
     @param min This is an integer that represents the minimal amount that can be purchased at one time.
     @param max This is an integer that represents the maximum amount that can be purchased at one time.
     @param machineId Unique to InHouse objects, is a user entered identifying number.
     */
    InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super (id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    /** Sets the machine ID value.
     @param setId This is the argument used to set the machine ID.
     */
    public void setMachineId(int setId){
        machineId = setId;
    }
    /** Gets the machineId value.
     @return Returns the machineId value of this object.
     */
    public int getMachineId(){
        return machineId;
    }
}
