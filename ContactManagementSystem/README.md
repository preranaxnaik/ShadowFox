# Contact Management System

A professional console-based Contact Management System built using Java. This project demonstrates Object-Oriented Programming (OOP), clean architecture, input validation, exception handling, and modular code organization.

It allows users to add, search, update, view, and delete contacts using an in-memory `ArrayList` without relying on a database or external libraries.

---

## Features

- Add a new contact
- View all contacts
- Search contacts by name
- Update existing contacts
- Delete contacts with confirmation
- Auto-generated Contact IDs
- Duplicate phone number prevention
- Duplicate email prevention
- Name, phone, and email validation
- Alphabetically sorted contact list
- Professional table-formatted output
- Exception handling for invalid inputs
- Menu-driven console interface

---

## Technologies Used

- Java 17 (Compatible with Java 11+)
- Object-Oriented Programming (OOP)
- Java Collections (ArrayList)
- Exception Handling
- Regular Expressions (Regex)
- Scanner
- Switch-Case

---

## Project Structure

```
ContactManagementSystem/
│
├── src/
│   ├── app/
│   │   └── Main.java
│   │
│   ├── model/
│   │   └── Contact.java
│   │
│   ├── service/
│   │   └── ContactService.java
│   │
│   └── util/
│       └── ValidationUtil.java
│
├── README.md
└── .gitignore
```

---

## Validation Rules

### Name

- Cannot be empty
- Minimum 3 characters

### Phone Number

- Exactly 10 digits
- Digits only
- Must be unique

### Email Address

- Valid email format
- Must be unique

---

## Sample Menu

```
========================================
      CONTACT MANAGEMENT SYSTEM
========================================

1. Add Contact
2. View Contacts
3. Search Contact
4. Update Contact
5. Delete Contact
6. Exit

Enter your choice:
```

---

## How to Run

### Clone the Repository

```bash
git clone https://github.com/your-username/ContactManagementSystem.git
```

### Open in Your IDE

- IntelliJ IDEA
- Eclipse
- VS Code

### Compile

```bash
javac app/Main.java
```

### Run

```bash
java app.Main
```

---

## Learning Outcomes

This project demonstrates:

- Java Fundamentals
- Object-Oriented Programming
- Encapsulation
- Modular Project Structure
- Collection Framework
- Exception Handling
- Input Validation
- Clean Code Practices
- Console Application Development

---

## Future Enhancements

- File-based storage
- Database integration (MySQL)
- Login authentication
- Import/Export contacts
- Search by phone number
- Contact groups
- Favorites
- JavaFX desktop version
- Spring Boot REST API
- Unit testing with JUnit

---

## Author

**Prerana Naik**

Computer Engineering Student

Java Developer | Learning Data Structures & Software Development

---

## License

This project is open-source and available for learning and educational purposes.