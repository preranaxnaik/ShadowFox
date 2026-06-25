package service;

import model.Contact;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ContactService {

    // Stores all contacts
    private final ArrayList<Contact> contacts;

    // Scanner for user input
    private final Scanner scanner;

    // Auto-increment ID
    private int nextId;

    // Constructor
    public ContactService() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
    }

    /**
     * Generates the next contact ID.
     */
    private int generateId() {
        return nextId++;
    }

    /**
     * Checks whether a phone number already exists.
     */
    public boolean isPhoneExists(String phone) {

        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether an email already exists.
     */
    public boolean isEmailExists(String email) {

        for (Contact contact : contacts) {
            if (contact.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a contact using its ID.
     */
    private Contact findById(int id) {

        for (Contact contact : contacts) {

            if (contact.getId() == id) {
                return contact;
            }

        }

        return null;
    }

    /**
     * Sort contacts alphabetically.
     */
    private void sortContacts() {

        contacts.sort(
                Comparator.comparing(Contact::getName,
                        String.CASE_INSENSITIVE_ORDER)
        );

    }


/**
 * Adds a new contact.
 */
    public void addContact() {

        try {

            String name;
            String phone;
            String email;

            // ---------------- Name ----------------
            while (true) {

                System.out.print("Enter Full Name: ");
                name = scanner.nextLine().trim();

                if (!ValidationUtil.validateName(name)) {
                    System.out.println("❌ Invalid name! Name must contain at least 3 characters.");
                    continue;
                }

                break;
            }

            // ---------------- Phone ----------------
            while (true) {

                System.out.print("Enter Phone Number: ");
                phone = scanner.nextLine().trim();

                if (!ValidationUtil.validatePhone(phone)) {
                    System.out.println("❌ Phone number must contain exactly 10 digits.");
                    continue;
                }

                if (isPhoneExists(phone)) {
                    System.out.println("❌ Phone number already exists.");
                    continue;
                }

                break;
            }

            // ---------------- Email ----------------
            while (true) {

                System.out.print("Enter Email Address: ");
                email = scanner.nextLine().trim();

                if (!ValidationUtil.validateEmail(email)) {
                    System.out.println("❌ Invalid email address.");
                    continue;
                }

                if (isEmailExists(email)) {
                    System.out.println("❌ Email address already exists.");
                    continue;
                }

                break;
            }

            // Create Contact
            Contact contact = new Contact(
                    generateId(),
                    name,
                    phone,
                    email
            );

            contacts.add(contact);

            // Keep contacts sorted
            sortContacts();

            System.out.println("\n==================================");
            System.out.println(" Contact added successfully!");
            System.out.println("Generated Contact ID : " + contact.getId());
            System.out.println("==================================");

        } catch (Exception e) {

            System.out.println("An unexpected error occurred.");
            System.out.println(e.getMessage());

        }

    }
    /**
     * Displays all contacts.
     */
    public void viewContacts() {

        if (contacts.isEmpty()) {
            System.out.println("\n=================================");
            System.out.println("No contacts available.");
            System.out.println("=================================\n");
            return;
        }

        System.out.println("\n==========================================================================");
        System.out.printf("%-5s %-25s %-15s %-30s%n",
                "ID", "NAME", "PHONE", "EMAIL");
        System.out.println("--------------------------------------------------------------------------");

        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Total Contacts : " + contacts.size());
        System.out.println("==========================================================================\n");

    }

    /**
     * Searches contact by name.
     */
    public void searchContact() {

        if (contacts.isEmpty()) {

            System.out.println("\nNo contacts available.\n");
            return;

        }

        System.out.print("Enter name to search: ");
        String keyword = scanner.nextLine().trim();

        boolean found = false;

        System.out.println("\nSearch Results");
        System.out.println("==========================================================================");
        System.out.printf("%-5s %-25s %-15s %-30s%n",
                "ID", "NAME", "PHONE", "EMAIL");
        System.out.println("--------------------------------------------------------------------------");

        for (Contact contact : contacts) {

            if (contact.getName().toLowerCase()
                    .contains(keyword.toLowerCase())) {

                System.out.println(contact);
                found = true;

            }

        }

        if (!found) {

            System.out.println("No contact found.");

        }

        System.out.println("==========================================================================");

    }

    /**
     * Checks if a phone number exists for another contact.
     */
    private boolean isPhoneExistsExcept(String phone, int contactId) {

        for (Contact contact : contacts) {

            if (contact.getPhone().equals(phone)
                    && contact.getId() != contactId) {

                return true;
            }
        }

        return false;
    }

    /**
     * Checks if an email exists for another contact.
     */
    private boolean isEmailExistsExcept(String email, int contactId) {

        for (Contact contact : contacts) {

            if (contact.getEmail().equalsIgnoreCase(email)
                    && contact.getId() != contactId) {

                return true;
            }
        }

        return false;
    }
    /**
     * Updates an existing contact.
     */
    public void updateContact() {

        if (contacts.isEmpty()) {

            System.out.println("\nNo contacts available.\n");
            return;

        }

        try {

            System.out.print("Enter Contact ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            Contact contact = findById(id);

            if (contact == null) {

                System.out.println("Contact not found.");
                return;

            }

            // ---------- Name ----------
            while (true) {

                System.out.print("New Name (" + contact.getName() + "): ");
                String name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    break;
                }

                if (!ValidationUtil.validateName(name)) {

                    System.out.println("Invalid name.");
                    continue;

                }

                contact.setName(name);
                break;
            }

            // ---------- Phone ----------
            while (true) {

                System.out.print("New Phone (" + contact.getPhone() + "): ");
                String phone = scanner.nextLine().trim();

                if (phone.isEmpty()) {
                    break;
                }

                if (!ValidationUtil.validatePhone(phone)) {

                    System.out.println("Invalid phone number.");
                    continue;

                }

                if (isPhoneExistsExcept(phone, id)) {

                    System.out.println("Phone number already exists.");
                    continue;

                }

                contact.setPhone(phone);
                break;
            }

            // ---------- Email ----------
            while (true) {

                System.out.print("New Email (" + contact.getEmail() + "): ");
                String email = scanner.nextLine().trim();

                if (email.isEmpty()) {
                    break;
                }

                if (!ValidationUtil.validateEmail(email)) {

                    System.out.println("Invalid email.");
                    continue;

                }

                if (isEmailExistsExcept(email, id)) {

                    System.out.println("Email already exists.");
                    continue;

                }

                contact.setEmail(email);
                break;
            }

            sortContacts();

            System.out.println("\n Contact updated successfully.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid Contact ID.");

        } catch (Exception e) {

            System.out.println("Unexpected error: " + e.getMessage());

        }

    }

    /**
     * Deletes a contact by ID.
     */
    public void deleteContact() {

        if (contacts.isEmpty()) {

            System.out.println("\nNo contacts available.\n");
            return;

        }

        try {

            System.out.print("Enter Contact ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            Contact contact = findById(id);

            if (contact == null) {

                System.out.println("Contact not found.");
                return;

            }

            System.out.println("\nContact Details");
            System.out.println("-----------------------------------------------");
            System.out.println("ID    : " + contact.getId());
            System.out.println("Name  : " + contact.getName());
            System.out.println("Phone : " + contact.getPhone());
            System.out.println("Email : " + contact.getEmail());
            System.out.println("-----------------------------------------------");

            System.out.print("Are you sure you want to delete this contact? (Y/N): ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("Y")) {

                contacts.remove(contact);

                System.out.println("\n Contact deleted successfully.");

            } else {

                System.out.println("\nDeletion cancelled.");

            }

        } catch (NumberFormatException e) {

            System.out.println("Invalid Contact ID.");

        } catch (Exception e) {

            System.out.println("Unexpected error: " + e.getMessage());

        }

    }
}