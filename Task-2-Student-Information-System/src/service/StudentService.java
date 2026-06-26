package service;

import model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private int nextId = 1001;

    public Student addStudent(Student student) {

        student.setId(nextId++);
        students.add(student);

        return student;
    }

    public boolean updateStudent(Student updatedStudent) {

        Student existing = searchStudentById(updatedStudent.getId());

        if (existing == null)
            return false;

        existing.setName(updatedStudent.getName());
        existing.setAge(updatedStudent.getAge());
        existing.setGender(updatedStudent.getGender());
        existing.setDepartment(updatedStudent.getDepartment());
        existing.setSemester(updatedStudent.getSemester());
        existing.setCgpa(updatedStudent.getCgpa());
        existing.setEmail(updatedStudent.getEmail());

        return true;
    }

    public boolean deleteStudent(int id) {

        Student student = searchStudentById(id);

        if (student == null)
            return false;

        students.remove(student);
        return true;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student searchStudentById(int id) {

        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }

        return null;
    }

    public List<Student> searchStudentByName(String keyword) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getName()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                result.add(student);
            }
        }

        return result;
    }

    public boolean isEmailExists(String email) {

        for (Student student : students) {

            if (student.getEmail()
                    .equalsIgnoreCase(email))

                return true;
        }

        return false;
    }

    public int getStudentCount() {
        return students.size();
    }

    public void sortByName() {

        students.sort(
                Comparator.comparing(Student::getName));
    }

    public void sortByCGPA() {

        students.sort(
                Comparator.comparing(Student::getCgpa)
                        .reversed());
    }

    public List<Student> filterByDepartment(String department) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getDepartment()
                    .equalsIgnoreCase(department)) {

                result.add(student);
            }
        }

        return result;
    }
    public boolean isEmailExists(String email, int excludeId) {

        for (Student student : students) {

            if (student.getId() != excludeId &&
                student.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }
}