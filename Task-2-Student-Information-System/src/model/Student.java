package model;

public class Student {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String department;
    private int semester;
    private double cgpa;
    private String email;

    public Student() {
    }

    public Student(int id, String name, int age, String gender,
                   String department, int semester,
                   double cgpa, String email) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.semester = semester;
        this.cgpa = cgpa;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:%d | %s | Age:%d | %s | %s | Sem:%d | CGPA:%.2f | %s",
                id, name, age, gender, department,
                semester, cgpa, email
        );
    }
}