package echodraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Specific_db_Grades extends JPanel implements ActionListener {

    int actNum = 0;
    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel background = new JPanel();
    JPanel backgroundSide = new JPanel();
    JPanel workbenchcenter = new JPanel();
    JPanel workbenchtop = new JPanel();
    JPanel topSidePanel = new JPanel();
    JPanel toppanel = new JPanel();
    JPanel sidepanel = new JPanel();
    JPanel UpperPanel = new JPanel();
    JLabel nameLbl = new JLabel("  " + "Instructor:");
    JButton dashboardBttn = new JButton();
    JButton lectureBttn = new JButton();
    JButton classworkBttn = new JButton();
    JButton peopleBttn = new JButton();
    JButton gradesBttn = new JButton();
    JButton logoutBttn = new JButton("Log-out");

    JTextField searchtxt = new JTextField();
    JButton search = new JButton("Search");
    JButton addactBttn = new JButton("Add Activity");
    JButton updateBttn = new JButton("Update");

    DefaultTableModel actmod = new DefaultTableModel();
    JTable table = new JTable(actmod);

    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private StudentData studentData;
    private Image backgroundImage;

    public Specific_db_Grades(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, StudentData studentData) {
        this.studentData = studentData;
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/echodraft/images/desk.png"));
        backgroundImage = bgIcon.getImage();

        toppanel.setBackground(Color.decode("#4B1D3F"));
        toppanel.setPreferredSize(new Dimension(0, 60));
        toppanel.setLayout(new BorderLayout());

        topSidePanel.setBackground(Color.decode("#FFF5DD"));
        topSidePanel.setPreferredSize(new Dimension(220, 60));
        topSidePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.decode("#C5AF80")));
        topSidePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLbl = new JLabel("E  C  H  O");
        titleLbl.setFont(new Font("Impact", Font.PLAIN, 40));
        titleLbl.setForeground(Color.decode("#4B1D3F"));
        topSidePanel.add(titleLbl, gbc);

        toppanel.add(topSidePanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.setOpaque(false);

        JLabel dashboardLbl = new JLabel(" " + classroomData.getClassroomName().toUpperCase() + ": ");
        dashboardLbl.setFont(new Font("Impact", Font.PLAIN, 30));
        dashboardLbl.setForeground(ColorUtils.getColorFromName(classroomData.getColor()));
        centerPanel.add(dashboardLbl);

        JLabel dashboardLbl1 = new JLabel(" " + classroomData.getSubjectName());
        dashboardLbl1.setFont(new Font("Impact", Font.PLAIN, 30));
        dashboardLbl1.setForeground(Color.decode("#EDE7E3"));
        centerPanel.add(dashboardLbl1);

        JLabel dashboardLbl2 = new JLabel("    " + classroomData.getSectionName());
        dashboardLbl2.setFont(new Font("Impact", Font.PLAIN, 30));
        dashboardLbl2.setForeground(Color.decode("#EDE7E3"));
        centerPanel.add(dashboardLbl2);

        toppanel.add(centerPanel, BorderLayout.CENTER);

        appNameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        appNameLbl.setForeground(Color.decode("#F1FAEE"));
        toppanel.add(appNameLbl, BorderLayout.EAST);
        add(toppanel, BorderLayout.NORTH);

        sidepanel.setBackground(Color.decode("#4B1D3F"));
        sidepanel.setPreferredSize(new Dimension(220, 0));
        sidepanel.setLayout(new BorderLayout());
        add(sidepanel, BorderLayout.WEST);

        UpperPanel.setBackground(Color.decode("#FFF5DD"));
        UpperPanel.setPreferredSize(new Dimension(220, 80)); 
        UpperPanel.setLayout(new BoxLayout(UpperPanel, BoxLayout.Y_AXIS));
        sidepanel.add(UpperPanel, BorderLayout.NORTH);

        UpperPanel.add(nameLbl);
        nameLbl.setForeground(Color.decode("#4B1D3F"));
        nameLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel usernameLbl = new JLabel("  " + teacherData.getUsername());
        UpperPanel.add(usernameLbl);
        usernameLbl.setForeground(Color.decode("#4B1D3F"));
        usernameLbl.setFont(new Font("Constantia", Font.PLAIN, 20));
        usernameLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel fullNameLbl = new JLabel("  " + teacherData.getFullName() + "   (" + teacherData.getFormattedAccNumber() + ")");
        UpperPanel.add(fullNameLbl);
        fullNameLbl.setForeground(Color.decode("#4B1D3F"));
        fullNameLbl.setFont(new Font("Constantia", Font.PLAIN, 15));
        fullNameLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        logoutBttn.setBackground(Color.decode("#D62246"));
        logoutBttn.setForeground(Color.decode("#F1FAEE"));
        logoutBttn.setFont(new Font("Constantia", Font.BOLD, 25));
        logoutBttn.setMaximumSize(new Dimension(300, 50));
        logoutBttn.setBorderPainted(false);
        logoutBttn.setFocusPainted(false);
        logoutBttn.addActionListener(this);
        sidepanel.add(logoutBttn, BorderLayout.SOUTH);

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));
        ButtonPanel.setBackground(Color.decode("#4B1D3F"));

        dashboardBttn = createSidebarButton("Dashboard");
        lectureBttn = createSidebarButton("Lecture");
        classworkBttn = createSidebarButton("Classwork");
        peopleBttn = createSidebarButton("People");
        gradesBttn = createSidebarButton("Grades");
        ButtonPanel.add(dashboardBttn);
        ButtonPanel.add(lectureBttn);
        ButtonPanel.add(classworkBttn);
        ButtonPanel.add(peopleBttn);
        ButtonPanel.add(gradesBttn);

        gradesBttn.setContentAreaFilled(true);
        gradesBttn.setBackground(Color.decode("#5E2A51"));

        dashboardBttn.addActionListener(this);
        lectureBttn.addActionListener(this);
        classworkBttn.addActionListener(this);
        peopleBttn.addActionListener(this);
        gradesBttn.addActionListener(this);

        sidepanel.add(ButtonPanel, BorderLayout.CENTER);

        background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        background.setLayout(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(background, BorderLayout.CENTER);

        backgroundSide.setBackground(Color.decode("#BA66A4"));
        backgroundSide.setPreferredSize(new Dimension(400, 500));
        backgroundSide.setLayout(new BorderLayout());
        backgroundSide.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        workbenchcenter.setBackground(Color.decode("#4B1D3F"));
        workbenchcenter.setLayout(new BorderLayout());
        workbenchcenter.setOpaque(false);
        background.add(workbenchcenter, BorderLayout.CENTER);

        workbenchtop.setPreferredSize(new Dimension(220, 100));
        workbenchtop.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        workbenchtop.setOpaque(false);
        workbenchcenter.add(workbenchtop, BorderLayout.NORTH);

        searchtxt.setPreferredSize(new Dimension(600, 40));
        searchtxt.setBackground(Color.decode("#FAF0E6"));
        searchtxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        searchtxt.setFont(new Font("Impact", Font.PLAIN, 15));
        workbenchtop.add(searchtxt);

        search.setBackground(Color.decode("#C5AF80"));
        search.setForeground(Color.decode("#EDE7E3"));
        search.setFont(new Font("Impact", Font.ITALIC, 20));
        search.setBorderPainted(false);
        search.setFocusPainted(false);
        search.addActionListener(this);
        workbenchtop.add(search);

        addactBttn.setBackground(Color.decode("#BA66A4"));
        addactBttn.setForeground(Color.decode("#EDE7E3"));
        addactBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        addactBttn.setBorderPainted(false);
        addactBttn.setFocusPainted(false);
        workbenchtop.add(addactBttn);
        addactBttn.addActionListener(this);

        updateBttn.setBackground(Color.decode("#BA66A4"));
        updateBttn.setForeground(Color.decode("#EDE7E3"));
        updateBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        updateBttn.setBorderPainted(false);
        updateBttn.setFocusPainted(false);
        workbenchtop.add(updateBttn);
        updateBttn.addActionListener(this);

        String classroomId = String.valueOf(classroomData.getClassroomID());
        List<StudentData> students = StudentDatabase.getAllStudents(classroomId);
        Object[][] savedGrades = studentData.getGrades();
        int activityCount = classroomData.getActivityCount();

        DefaultTableModel actmod = new DefaultTableModel();
        actmod.addColumn("Name");

        List<String> activityNames = classroomData.getActivityNames();
        if (activityNames != null && activityNames.size() == activityCount) {
            for (String name : activityNames) {
                actmod.addColumn(name);
            }
        } else {
            for (int i = 1; i <= activityCount; i++) {
                actmod.addColumn("Activity " + i); 
            }
        }

        actmod.addColumn("Average");

        for (StudentData s : students) {
            Object[] row = new Object[activityCount + 2];
            row[0] = s.getFullName();

            if (savedGrades != null) {
                for (Object[] oldRow : savedGrades) {
                    if (oldRow[0].equals(s.getFullName())) {
                        System.arraycopy(oldRow, 1, row, 1, Math.min(oldRow.length - 2, activityCount));
                        break;
                    }
                }
            }

            for (int c = 1; c <= activityCount; c++) {
                if (row[c] == null) {
                    row[c] = "";
                }
            }

            row[row.length - 1] = 0; 
            actmod.addRow(row);
        }

        table = new JTable(actmod);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.decode("#C5AF80"));
        table.getTableHeader().setForeground(Color.decode("#4B1D3F"));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        scrollPane.setPreferredSize(new Dimension(1270, 630));
        workbenchcenter.add(scrollPane, BorderLayout.CENTER);

        if (studentData.getGrades() == null) {
            studentData.setGrades(new Object[0][0]);
        }

    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        Dimension size = new Dimension(300, 60);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setMinimumSize(size);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        button.setForeground(Color.decode("#D9D9D9"));
        button.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        button.setUI(new BasicButtonUI());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setOpaque(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String classroomId = String.valueOf(classroomData.getClassroomID());
        if (e.getSource() == dashboardBttn) {
            saveGradesToFile();
            mainFrame.setDashboard(teacherData);
        }

        if (e.getSource() == logoutBttn) {
            saveGradesToFile();
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to LOG-OUT of this account?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                mainFrame.showCard("login");
            }
        }

        if (e.getSource() == classworkBttn) {
            saveGradesToFile();
            mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == peopleBttn) {
            saveGradesToFile();
            mainFrame.showSpecificDashboardPeople(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == lectureBttn) {
            saveGradesToFile();
            mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == addactBttn) {
            String newActivityName = JOptionPane.showInputDialog(
                    null,
                    "Enter the name of the new activity:",
                    "New Activity",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (newActivityName == null || newActivityName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Activity was not added.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            newActivityName = newActivityName.trim();
            int newActCount = classroomData.getActivityCount() + 1;
            classroomData.setActivityCount(newActCount);

            DefaultTableModel newModel = new DefaultTableModel();
            newModel.addColumn("Name");

            List<String> activityNames = classroomData.getActivityNames();
            if (activityNames == null) {
                activityNames = new ArrayList<>();
            }
            for (String name : activityNames) {
                newModel.addColumn(name);
            }

            newModel.addColumn(newActivityName);
            activityNames.add(newActivityName);
            classroomData.setActivityNames(activityNames);

            newModel.addColumn("Average");

            Object[][] oldGrades = studentData.getGrades();
            List<StudentData> students = StudentDatabase.getAllStudents(classroomId);

            for (StudentData s : students) {
                Object[] newRow = new Object[newActCount + 2];
                newRow[0] = s.getFullName();

                if (oldGrades != null) {
                    for (Object[] oldRow : oldGrades) {
                        if (oldRow[0].equals(s.getFullName())) {
                            System.arraycopy(oldRow, 1, newRow, 1, Math.min(oldRow.length - 2, newRow.length - 2));
                            break;
                        }
                    }
                }

                for (int c = 1; c < newRow.length - 1; c++) {
                    if (newRow[c] == null) {
                        newRow[c] = "";
                    }
                }

                newRow[newRow.length - 1] = 0; 
                newModel.addRow(newRow);
            }

            actmod = newModel;
            table.setModel(actmod);

            int rowCount = actmod.getRowCount();
            int colCount = actmod.getColumnCount();
            Object[][] updatedGrades = new Object[rowCount][colCount];

            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    updatedGrades[r][c] = actmod.getValueAt(r, c);
                }
            }

            studentData.setGrades(updatedGrades);

        }

        if (e.getSource() == updateBttn) {
            int rowCount = table.getRowCount();
            int colCount = table.getColumnCount();

            for (int r = 0; r < rowCount; r++) {
                double sum = 0;
                int count = 0;

                for (int c = 1; c < colCount - 1; c++) {
                    String val = table.getValueAt(r, c).toString().trim();
                    if (!val.isEmpty()) {
                        try {
                            sum += Double.parseDouble(val);
                            count++;
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }

                double average = count > 0 ? sum / count : 0;
                table.setValueAt(String.format("%.2f", average), r, colCount - 1);
            }

            Object[][] updatedGrades = new Object[rowCount][colCount];
            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    updatedGrades[r][c] = table.getValueAt(r, c);
                }
            }

            Object[][] finalGrades = new Object[rowCount][colCount];
            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    finalGrades[r][c] = table.getValueAt(r, c);
                }
            }

            studentData.setGrades(finalGrades);
            saveGradesToFile();
            JOptionPane.showMessageDialog(null, "Grades updated and averages recalculated!", "Success", JOptionPane.INFORMATION_MESSAGE);

        }

        if (e.getSource() == search) {
            String searchText = searchtxt.getText().trim().toLowerCase();

            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input the name you want to search!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            boolean found = false;

            for (int row = 0; row < table.getRowCount(); row++) {
                String studentName = table.getValueAt(row, 0).toString().toLowerCase();

                if (studentName.contains(searchText)) {
                    table.setRowSelectionInterval(row, row);
                    table.scrollRectToVisible(new Rectangle(table.getCellRect(row, 0, true)));
                    found = true;
                    break; 
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void refreshGradesTable() {
        String classroomId = String.valueOf(classroomData.getClassroomID());
        StudentDatabase.bubbleSortBySurname(classroomId);
        List<StudentData> students = StudentDatabase.getAllStudents(classroomId);

        List<String> activityNames = classroomData.getActivityNames();
        Object[][] savedGrades = studentData.getGrades();

        DefaultTableModel newModel = new DefaultTableModel();
        newModel.addColumn("Name");

        for (String actName : activityNames) {
            newModel.addColumn(actName);
        }

        newModel.addColumn("Average");

        for (StudentData s : students) {
            Object[] row = new Object[activityNames.size() + 2];
            row[0] = s.getFullName();

            for (int i = 1; i < row.length; i++) {
                row[i] = "";
            }

            if (savedGrades != null) {
                for (Object[] oldRow : savedGrades) {
                    if (oldRow[0].equals(s.getFullName())) {

                        for (int col = 0; col < activityNames.size(); col++) {
                            String actName = activityNames.get(col);

                            for (int oldCol = 1; oldCol < oldRow.length - 1; oldCol++) {
                                if (actName.equals(classroomData.getActivityNames().get(oldCol - 1))) {
                                    row[col + 1] = oldRow[oldCol];
                                    break;
                                }
                            }
                        }

                        row[row.length - 1] = oldRow[oldRow.length - 1];
                        break;
                    }
                }
            }

            newModel.addRow(row);
        }

        actmod = newModel;
        table.setModel(actmod);
    }

    private void saveGradesToFile() {
        String classroomId = String.valueOf(classroomData.getClassroomID());
        Object[][] grades = studentData.getGrades();

        if (grades == null || grades.length == 0) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("grades_" + classroomId + ".txt"))) {
            for (Object[] row : grades) {
                for (int c = 0; c < row.length; c++) {
                    writer.write(row[c].toString());
                    if (c < row.length - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
