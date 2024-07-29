package com.techelevator;

import java.util.Scanner;
//Display Class is the engine for the vending machines functions
public class Display {
    // Instaniates new vendingMachine and scanner
    protected VendingMachine vendingMachine;
    private Scanner scanner;
//Constructor for initializing vendingMachine and scanner
    public Display(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.scanner = new Scanner(System.in);
    }
//Main method for displaying options on vending machine and scanner
    public void mainMenu() {
        while (true) {
            System.out.println("\u001B[3m(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            //prompt for user's input/choice

            String choice = getInput("Select an option: ");
            switch (choice) {
                case "1":
                    vendingMachine.displayItems();
                    break;
                case "2":
                    purchaseMenu();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
// Purchsae menu code block
    private void purchaseMenu() {
        while (true) {
            System.out.printf("Current Money Provided: $%.2f%n", vendingMachine.getCurrentMoney());
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            String choice = getInput("Select an option: ");
            switch (choice) {
                case "1":
                    double amount = Double.parseDouble(getInput("Enter amount to feed: "));
                    vendingMachine.feedMoney(amount);
                    break;
                case "2":
                    String slot = getInput("Enter product code: ");
                    vendingMachine.selectProduct(slot);
                    break;
                case "3":
                    vendingMachine.finishTransaction();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
