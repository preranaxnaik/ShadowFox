package ui;

import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentTablePanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private static final String[] COLUMNS = {
            "ID",
            "Name",
            "Age",
            "Gender",
            "Department",
            "Semester",
            "CGPA",
            "Email"
    };

    public StudentTablePanel() {

        initializeTable();

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void initializeTable() {

        tableModel = new DefaultTableModel(COLUMNS, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(32);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));

        // Selection
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Enable Sorting
        table.setAutoCreateRowSorter(true);

        // Disable Column Reordering
        table.getTableHeader().setReorderingAllowed(false);

        // Fill Empty Space
        table.setFillsViewportHeight(true);

        // Grid Appearance
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

        // Cell Spacing
        table.setIntercellSpacing(new Dimension(5, 5));
    }
    public void loadStudents(List<Student> students) {

    clearTable();

    for (Student student : students) {

        tableModel.addRow(new Object[]{

                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGender(),
                student.getDepartment(),
                student.getSemester(),
                student.getCgpa(),
                student.getEmail()

        });
    }
}

    private void clearTable() {

        tableModel.setRowCount(0);
    }

    public int getSelectedStudentId() {

        int row = table.getSelectedRow();

        if (row == -1)
            return -1;

        int modelRow = table.convertRowIndexToModel(row);

        return (Integer) tableModel.getValueAt(modelRow, 0);
    }

    public JTable getTable() {
        return table;
    }

    public boolean isEmpty() {

        return tableModel.getRowCount() == 0;
    }

}