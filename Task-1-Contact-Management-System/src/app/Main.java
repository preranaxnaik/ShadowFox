package app;

import service.ContactService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ContactService contactService = new ContactService();

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("      CONTACT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");

            try {

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        contactService.addContact();
                        break;

                    case 2:
                        contactService.viewContacts();
                        break;

                    case 3:
                        contactService.searchContact();
                        break;

                    case 4:
                        contactService.updateContact();
                        break;

                    case 5:
                        contactService.deleteContact();
                        break;

                    case 6:
                        running = false;
                        System.out.println("\nThank you for using Contact Management System!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please select between 1 and 6.");

                }

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid numeric choice.");

            } catch (Exception e) {

                System.out.println("Unexpected error: " + e.getMessage());

            }

        }

        scanner.close();

    }
}