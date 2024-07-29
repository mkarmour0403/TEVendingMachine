package com.techelevator;


public class Main {
    //Attempt at adding color to the command line interface
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";

//Main class initiates new VendingMachine Object
    public static void main(String[] args) {

//Welcome Message for Command Line interface.
        System.out.println(ANSI_PURPLE + "\u001B[33mWelcome to Vault-Tec's" );
        System.out.println(ANSI_PURPLE + "\u001B[33m<--Vendo-Matic 8000-->" );

        VendingMachine vendingMachine = new VendingMachine("vendingmachine.csv");
        Display display = new Display(vendingMachine);
        display.mainMenu();

    }
}
