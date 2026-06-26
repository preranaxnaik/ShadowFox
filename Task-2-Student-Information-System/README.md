# 🎓 Student Information System (Java Swing)

A professional desktop application built using **Java Swing** that enables users to manage student records through an intuitive graphical interface. This project demonstrates Object-Oriented Programming (OOP), MVC-inspired architecture, input validation, exception handling, and clean coding practices.

---

## 📌 Project Overview

The Student Information System is a desktop CRUD application that allows users to add, update, search, and delete student records. All data is stored in an in-memory `ArrayList<Student>` without using a database or file handling, making it ideal for learning Java collections and Swing application development.

---

## ✨ Features

* Add Student
* Display Students in JTable
* Search Student by Name
* Update Student Details
* Delete Student
* Auto-generated Student IDs
* Duplicate Email Validation
* Input Validation using `ValidationUtil`
* Status Bar displaying Total Students
* Live Search
* Double-click Row to Edit
* Confirmation Dialog before Deletion
* Keyboard Shortcuts
* Professional Swing GUI

---

## 📚 Student Information

Each student record contains:

* Student ID (Auto-generated)
* Full Name
* Age
* Gender
* Department
* Semester
* CGPA
* Email Address

---

## 🛠 Technologies Used

* Java 17
* Java Swing
* Object-Oriented Programming
* Collections (`ArrayList`)
* MVC-inspired Architecture
* Exception Handling
* JTable
* GridBagLayout
* IntelliJ IDEA / Eclipse / VS Code

---

## 📂 Project Structure

```text
StudentInformationSystem/
│
├── src/
│   ├── app/
│   │     Main.java
│   ├── model/
│   │     Student.java
│   ├── service/
│   │     StudentService.java
│   ├── util/
│   │     ValidationUtil.java
│   └── ui/
│         MainFrame.java
│         StudentFormDialog.java
│         StudentTablePanel.java
│
├── screenshots/
├── README.md
└── .gitignore
```

---

## ✅ Validation Rules

| Field    | Rule                 |
| -------- | -------------------- |
| Name     | Minimum 3 characters |
| Age      | Between 16 and 35    |
| Semester | Between 1 and 8      |
| CGPA     | Between 0.0 and 10.0 |
| Email    | Valid email format   |

---

## 🖥 GUI

Main Window includes:

* Menu Bar
* Toolbar
* Student Table (`JTable`)
* Search Box
* Status Bar
* Add / Update / Delete / Search Buttons

Place screenshots inside the `screenshots/` folder and reference them here:

```text
screenshots/main-window.png
screenshots/add-student.png
screenshots/update-student.png
```

---

## ▶️ How to Run

1. Clone the repository.

```bash
git clone https://github.com/your-username/StudentInformationSystem.git
```

2. Open the project in your preferred Java IDE.

3. Ensure Java 17 (or Java 11+) is installed.

4. Run:

```text
src/app/Main.java
```

---

## 📖 Concepts Demonstrated

* Java Swing
* OOP Principles
* Encapsulation
* Event Handling
* Exception Handling
* Collections Framework
* MVC-inspired Design
* Input Validation
* JTable Management

---

## 🚀 Future Enhancements

* File Storage
* Database Integration (MySQL)
* Export to CSV
* Import Student Records
* Dark Mode
* Student Photo Support
* Dashboard Analytics
* Sorting & Advanced Filters

---

## 👩‍💻 Author

**Prerana Naik**

Computer Engineering Student

This project was developed as part of Java desktop application practice to strengthen Java, Swing, OOP, and software design skills for internships and technical interviews.
