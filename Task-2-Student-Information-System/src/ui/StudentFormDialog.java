package ui;

import model.Student;
import service.StudentService;
import util.ValidationUtil;

import javax.swing.*;
import java.awt.*;

public class StudentFormDialog extends JDialog {

    private final StudentService studentService;
    private Student student;
    private boolean saved = false;

    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtCGPA;
    private JTextField txtEmail;

    private JComboBox<String> cmbGender;
    private JComboBox<String> cmbDepartment;
    private JComboBox<Integer> cmbSemester;

    private JButton btnSave;
    private JButton btnCancel;

    public StudentFormDialog(Frame parent,
                             StudentService studentService,
                             Student student) {

        super(parent, true);

        this.studentService = studentService;
        this.student = student;

        initializeComponents();
        buildUI();

        if (student != null) {
            fillStudentData();
            setTitle("Update Student");
        } else {
            setTitle("Add Student");
        }

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initializeComponents() {

        txtName = new JTextField(20);
        txtAge = new JTextField(20);
        txtCGPA = new JTextField(20);
        txtEmail = new JTextField(20);

        cmbGender = new JComboBox<>(
                new String[]{
                        "Male",
                        "Female",
                        "Other"
                });

        cmbDepartment = new JComboBox<>(
                new String[]{
                        "Computer Engineering",
                        "Information Technology",
                        "Mechanical Engineering",
                        "Civil Engineering",
                        "Electronics Engineering",
                        "Electrical Engineering"
                });

        cmbSemester = new JComboBox<>();

        for (int i = 1; i <= 8; i++) {
            cmbSemester.addItem(i);
        }

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
    }

    private void buildUI() {

        JPanel formPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtName, gbc);

        // Age
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Age"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtAge, gbc);

        // Gender
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Gender"), gbc);

        gbc.gridx = 1;
        formPanel.add(cmbGender, gbc);

        // Department
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Department"), gbc);

        gbc.gridx = 1;
        formPanel.add(cmbDepartment, gbc);

        // Semester
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Semester"), gbc);

        gbc.gridx = 1;
        formPanel.add(cmbSemester, gbc);

        // CGPA
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("CGPA"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtCGPA, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Email"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        setLayout(new BorderLayout());

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnSave);

        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> saveStudent());
    }

        public boolean isSaved() {
        return saved;
    }

    public Student getStudent() {
        return student;
    }

    private void fillStudentData() {

        txtName.setText(student.getName());
        txtAge.setText(String.valueOf(student.getAge()));

        cmbGender.setSelectedItem(student.getGender());

        cmbDepartment.setSelectedItem(
                student.getDepartment());

        cmbSemester.setSelectedItem(
                student.getSemester());

        txtCGPA.setText(
                String.valueOf(student.getCgpa()));

        txtEmail.setText(student.getEmail());
    }

    private void saveStudent() {

    try {

        String name = txtName.getText().trim();
        int age = Integer.parseInt(txtAge.getText().trim());

        String gender = (String) cmbGender.getSelectedItem();

        String department =
                (String) cmbDepartment.getSelectedItem();

        int semester =
                (Integer) cmbSemester.getSelectedItem();

        double cgpa =
                Double.parseDouble(txtCGPA.getText().trim());

        String email = txtEmail.getText().trim();

        // Validation
        ValidationUtil.validateName(name);
        ValidationUtil.validateAge(age);
        ValidationUtil.validateSemester(semester);
        ValidationUtil.validateCGPA(cgpa);
        ValidationUtil.validateEmail(email);

        // Duplicate Email Check
        if (student == null) {

            if (studentService.isEmailExists(email)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Email already exists.",
                        "Duplicate Email",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            student = new Student(
                    0,
                    name,
                    age,
                    gender,
                    department,
                    semester,
                    cgpa,
                    email
            );

        } else {

            if (!student.getEmail().equalsIgnoreCase(email)
                    && studentService.isEmailExists(email)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Email already exists.",
                        "Duplicate Email",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            student.setName(name);
            student.setAge(age);
            student.setGender(gender);
            student.setDepartment(department);
            student.setSemester(semester);
            student.setCgpa(cgpa);
            student.setEmail(email);
        }

        saved = true;

        JOptionPane.showMessageDialog(
                this,
                "Student details saved successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Age and CGPA must be valid numeric values.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);

    } catch (IllegalArgumentException ex) {

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Unexpected Error : " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}

}