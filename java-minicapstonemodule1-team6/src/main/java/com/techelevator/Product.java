package com.techelevator;
/* Initiates Strings and int to be used for products*/
public class Product {
    private String slot;
    private String name;
    private double price;
    private String type;
    private int quantity;
    /*Constuctor of the product class
    @param slot assigns a slot the product will be placed(e.g A1,B1...D4)
    @param name assigns a name to the product
    @param price assigns a double value(price) to the products
    @param type assigns a type classification for each product(e.g. cat, duck, penquin, or horse/pony
    @param quantity is  a value assigned to the representing the stock amount on the products.
*/
    //Establishes string to match the vendingmachine format
    public Product(String slot, String name, double price, String type, int quantity) {
        //assigns slot, name, price, type, and quantity identifiers to the products instanced variables
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }
//getters
    public String getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
//setter
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
