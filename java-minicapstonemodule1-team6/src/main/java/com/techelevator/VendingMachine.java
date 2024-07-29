package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class VendingMachine extends Main{
/* initiates map for inventory*/
    private final Map<String, Product> inventory = new HashMap<>();
    private double currentMoney = 0;
// loads inventory from the inventory map
    public VendingMachine(String inventoryFile) {
        loadInventory(inventoryFile);
    }
/*Reader for inventory file */
    private void loadInventory(String inventoryFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inventoryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String slot = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String type = parts[3];
                int quantity = Integer.parseInt(parts[4]);
                inventory.put(slot, new Product(slot, name, price, type, quantity));
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file." );
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
// checks inventory on selection is product inventory is
//  0 then sold out message is the output
    public void displayItems() {
        for (Product product : inventory.values()) {
            String status = product.getQuantity() == 0 ? "SOLD OUT" : String.valueOf(product.getQuantity());
            System.out.printf("%s: %s - $%.2f (%s left)%n", product.getSlot(), product.getName(), product.getPrice(), status);
        }
    }
// feed money into machine
    public void feedMoney(double amount) {
        currentMoney += amount;
        logTransaction("\u001B[421" +
                "FEED MONEY", amount);
    }
// product selection and error exception for invalid product code
//    and in the loop also passes if product is sold out, insufficient funds
    public void selectProduct(String slot) {
        Product product = inventory.get(slot);
        if (product == null) {
            System.out.println("Invalid product code.");
            return;
        }
        if (product.getQuantity() == 0) {
            System.out.println("Product is SOLD OUT.");
            return;
        }
        if (currentMoney < product.getPrice()) {
            System.out.println("Insufficient funds." );
            return;

        }
        currentMoney -= product.getPrice();
        product.setQuantity(product.getQuantity() - 1);
        logTransaction(product.getName(), -product.getPrice());
        dispenseProduct(product);
    }
// completes transaction and iniates give change method
    public void finishTransaction() {
        double change = currentMoney;
        currentMoney = 0;
        logTransaction("GIVE CHANGE", -change);
        giveChange(change);
    }
/*Dispensing method take product input and output correct sound
/* and outputs the sound after purchase has been made using
/*the switch in the method allows for comparison as we are dealing with a
/*single input  in the dispenseProduct method */


    private void dispenseProduct(Product product) {
        String sound;
        switch (product.getType().toLowerCase()) {
            case "duck":
                sound = "Quack, Quack, Splash!";
                break;
            case "penguin":
                sound = "Squawk, Squawk, Whee!";
                break;
            case "cat":
                sound = "Purr, Purr, Meow!";
                break;
            case "pony":
                sound = "Neigh, Neigh, Yay!";
                break;
            default:
                sound = "Dispensed!";
        }
        System.out.printf("Dispensing %s. %s%n", product.getName(), sound);
        System.out.printf("Remaining balance: $%.2f%n", currentMoney);
    }
/*Calculate change method takes input from money fed
/* into the machine and gives change in the form of quarters, dimes, and nickels
 */
    private void giveChange(double change) {
        int quarters = (int) (change / 0.25);
        change %= 0.25;
        int dimes = (int) (change / 0.10);
        change %= 0.10;
        int nickels = (int) (change / 0.05);
        change %= 0.05;
        System.out.printf("Your change is $%.2f in %d quarters, %d dimes, and %d nickels.%n", (quarters * 0.25 + dimes * 0.10 + nickels * 0.05), quarters, dimes, nickels);
    }
/* prints transactions to log.txt file or if it does not exists it
/* creates log.txt file in either case it updates
/*to current transaction history includes error exception for inability to
/* write to file*/
    private void logTransaction(String action, double amount) {
        try (PrintWriter log = new PrintWriter(new FileWriter("Log.txt", true))) {
            String timestamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
            log.printf("%s %s: $%.2f $%.2f%n", timestamp, action, amount, currentMoney);
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Error writing to log file." );
            e.printStackTrace();
        }
    }

    public double getCurrentMoney() {
        return currentMoney;
    }
}
