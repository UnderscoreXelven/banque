package org.example.utils;

import java.util.Scanner;

public class ConsoleIHM {
    //Attributs
    private static Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    /**
     * Start an IHM
     */
    public static void start() {
        while (running) {
            System.out.println("[0] Quitter");
            System.out.println("[1] Création de client et de compte");
            System.out.println("[2] Dépôt");
            System.out.println("[3] Retrait");
            System.out.println("[4] Afficher les détails du compte");
            System.out.print("Choix : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> stop();
                case 1 -> createCustomer();
                case 2 -> deposit();
                case 3 -> withdrawal();
                case 4 -> showOperations();
                default -> System.out.println("Choix invalide !");
            }
        }
        System.out.println("Au revoir !");
    }

    /**
     * Stop the IHM (HCI)
     */
    private static void stop(){
        running = false;
    }

    /**
     * Create a customer
     */
    private static void createCustomer(){

    }

    /**
     * Make a deposit in a bank account
     */
    private static void deposit(){

    }

    /**
     * Make a withdrawal from a bank account
     */
    private static void withdrawal(){

    }

    /**
     * Show Operations from a bank account
     */
    private static void showOperations(){

    }
}
