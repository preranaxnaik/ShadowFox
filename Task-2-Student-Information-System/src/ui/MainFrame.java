package ui;
import model.Student;
import service.StudentService;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final StudentService studentService;

    private StudentTablePanel tablePanel;

    private JTextField txtSearch;

    private JLabel lblStatus;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSearch;
    private JButton btnRefresh;

    public MainFrame() {
        studentService = new StudentService();
        initializeComponents();
        buildMenuBar();
        buildUI();
        refreshTable();
        setTitle("Student Information System");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {

        tablePanel = new StudentTablePanel();

        txtSearch = new JTextField(20);

        lblStatus = new JLabel("Total Students : 0");

        btnAdd = new JButton("Add");

        btnUpdate = new JButton("Update");

        btnDelete = new JButton("Delete");

        btnSearch = new JButton("Search");

        btnRefresh = new JButton("Refresh");
    }

    private void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> dispose());
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private void buildUI() { 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnAdd);
        topPanel.add(btnUpdate);
        topPanel.add(btnDelete);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(new JLabel("Search"));
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);
        topPanel.add(btnRefresh);
        JPanel statusPanel = new JPanel(new BorderLayout());

        statusPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10));

        statusPanel.add(lblStatus,BorderLayout.WEST);
        add(topPanel,BorderLayout.NORTH);
        add(tablePanel,BorderLayout.CENTER);
        add(statusPanel,BorderLayout.SOUTH);
        registerEvents();

    }

    private void refreshTable() {
        tablePanel.loadStudents(studentService.getAllStudents());
        lblStatus.setText("Total Students : "+ studentService.getStudentCount());
    }

    private void registerEvents() {
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnSearch.addActionListener(e -> searchStudent());
        btnRefresh.addActionListener(e -> refreshTable());
        tablePanel.getTable().addMouseListener(
        new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(
                    java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {

                    updateStudent();
                }
            }
        });
        txtSearch.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(
                    javax.swing.event.DocumentEvent e) {

                searchStudent();
            }
            @Override
            public void removeUpdate(
                    javax.swing.event.DocumentEvent e) {

                searchStudent();
            }
            @Override
            public void changedUpdate(
                    javax.swing.event.DocumentEvent e) {

                searchStudent();
            }
        });
        tablePanel.getTable().getInputMap(JComponent.WHEN_FOCUSED)
        .put(KeyStroke.getKeyStroke("DELETE"),"deleteStudent");
        tablePanel.getTable().getActionMap()
        .put("deleteStudent",new AbstractAction() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) 
                    {
                        deleteStudent();
                    }
        });
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("control N"),"newStudent");
        getRootPane().getActionMap()
        .put("newStudent",new AbstractAction() {
                    @Override
                    public void actionPerformed(
                        java.awt.event.ActionEvent e) {
                        addStudent();
                    }
        });
        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("control F"),"search");
        getRootPane().getActionMap()
        .put("search",new AbstractAction() {
                    @Override
                    public void actionPerformed(
                            java.awt.event.ActionEvent e) {
                            txtSearch.requestFocus();
                    }
        });
    }

    private void addStudent() {
        StudentFormDialog dialog =
                new StudentFormDialog(
                        this,
                        studentService,
                        null);

        dialog.setVisible(true);

        if (dialog.isSaved()) {

            studentService.addStudent(
                    dialog.getStudent());

            refreshTable();
        }
    }
    private void updateStudent() {

        int id = tablePanel.getSelectedStudentId();

        if (id == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student first.");

            return;
        }

        Student student =
                studentService.searchStudentById(id);

        StudentFormDialog dialog =
                new StudentFormDialog(
                        this,
                        studentService,
                        student);

        dialog.setVisible(true);

        if (dialog.isSaved()) {

            studentService.updateStudent(
                    dialog.getStudent());

            refreshTable();
        }
    }
    private void deleteStudent() {

        int id = tablePanel.getSelectedStudentId();

        if (id == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student.");

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {

            studentService.deleteStudent(id);

            refreshTable();
        }
    }

    private void searchStudent() {

        String keyword =
                txtSearch.getText().trim();

        if (keyword.isEmpty()) {
            refreshTable();
            return;
        }

        tablePanel.loadStudents(studentService.searchStudentByName(keyword));
        lblStatus.setText("Search Results : "+ studentService.searchStudentByName(keyword).size());
    }
}