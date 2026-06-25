package model;

public class Contact {

    private int id;
    private String name;
    private String phone;
    private String email;

    // Default Constructor
    public Contact() {
    }

    // Parameterized Constructor
    public Contact(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "%-5d %-25s %-15s %-30s",
                id,
                name,
                phone,
                email
        );
    }
}