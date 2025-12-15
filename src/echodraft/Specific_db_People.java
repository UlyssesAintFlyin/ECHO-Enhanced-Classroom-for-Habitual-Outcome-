package echodraft;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;

public class Specific_db_People extends JPanel implements ActionListener {

    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel topSidePanel = new JPanel();
    JPanel toppanel = new JPanel();
    JPanel sidepanel = new JPanel();
    JPanel UpperPanel = new JPanel();
    JLabel nameLbl = new JLabel("  " + "Instructor:");
    JPanel workbenchPanel = new JPanel();
    JButton dashboardBttn = new JButton();
    JButton lectureBttn = new JButton();
    JButton classworkBttn = new JButton();
    JButton peopleBttn = new JButton();
    JButton gradesBttn = new JButton();
    JButton logoutBttn = new JButton("Log-out");
    
    JPanel background = new JPanel();
    JPanel backgroundSide = new JPanel();
    JPanel workbenchcenter = new JPanel();
    JPanel workbenchtop = new JPanel();

    JLabel searchLbl = new JLabel("Search");
    JButton searchBttn = new JButton("Search");
    JButton restoreBttn = new JButton("Refresh");

    JButton addStudBttn = new JButton("Add Student");
    JButton removeStudBttn = new JButton("Remove Student");
    JTextField searchField = new JTextField();
    public static List<StudentData> students = new ArrayList<>();

    JPanel centerTop = new JPanel();
    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private Image backgroundImage;
    private DefaultTableModel tableModel;
    private JTable studentTable;

    public Specific_db_People(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
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

        peopleBttn.setContentAreaFilled(true);
        peopleBttn.setBackground(Color.decode("#5E2A51"));

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

        workbenchcenter.setBackground(Color.decode("#4B1D3F"));
        workbenchcenter.setLayout(new BorderLayout());
        workbenchcenter.setOpaque(false);
        background.add(workbenchcenter, BorderLayout.CENTER);

        workbenchtop.setPreferredSize(new Dimension(220, 100));
        workbenchtop.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        workbenchtop.setOpaque(false);
        workbenchcenter.add(workbenchtop, BorderLayout.NORTH);
        
        searchField.setBackground(Color.decode("#FAF0E6"));
        searchField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        searchField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        searchField.setPreferredSize(new Dimension(600, 40));
        workbenchtop.add(searchField);

        JPanel buttonRowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        buttonRowPanel.setOpaque(false);

        workbenchtop.add(searchBttn);
        searchBttn.setBounds(670, 20, 110, 40);
        searchBttn.setBackground(Color.decode("#C5AF80"));
        searchBttn.setForeground(Color.decode("#EDE7E3"));
        searchBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        searchBttn.setBorderPainted(false);
        searchBttn.setFocusPainted(false);

        searchBttn.addActionListener(this);

        workbenchtop.add(restoreBttn);
        restoreBttn.setBounds(810, 20, 110, 40);
        workbenchtop.add(addStudBttn);
        addStudBttn.setBounds(950, 20, 140, 40);
        workbenchtop.add(removeStudBttn);
        removeStudBttn.setBounds(1120, 20, 170, 40);

        for (JButton bttn : new JButton[]{addStudBttn, removeStudBttn, restoreBttn}) {
            bttn.setBackground(Color.decode("#BA66A4"));
            bttn.setForeground(Color.decode("#EDE7E3"));
            bttn.setFont(new Font("Impact", Font.ITALIC, 20));
            bttn.setBorderPainted(false);
            bttn.setFocusPainted(false);
        }

        restoreBttn.addActionListener(this);
        addStudBttn.addActionListener(this);
        removeStudBttn.addActionListener(this);

        String[] columns = {"Username", "Surname", "Middle Name", "First Name", "Gender", "Student Number", "Age"};

        String classroomId = String.valueOf(classroomData.getClassroomID());

        students = new ArrayList<>();
        for (StudentData s : StudentDatabase.getAllStudents(classroomId)) {
            students.add(new StudentData(s));
        }
        
        Object[][] data = new Object[students.size()][columns.length];
        for (int i = 0; i < students.size(); i++) {
            StudentData s = students.get(i);
            data[i][0] = s.getUsername();
            data[i][1] = s.getSurname();
            data[i][2] = s.getMiddleName();
            data[i][3] = s.getFirstName();
            data[i][4] = s.getGender();
            data[i][5] = s.getStudentNumber();
            data[i][6] = s.getAge();
        }

        tableModel = new DefaultTableModel(data, columns);
        studentTable = new JTable(tableModel);
        studentTable.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        studentTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 18));
        studentTable.getTableHeader().setBackground(Color.decode("#C5AF80"));
        studentTable.getTableHeader().setForeground(Color.decode("#4B1D3F"));

        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        tableScrollPane.setBounds(20, 80, 1270, 630);
        tableScrollPane.setOpaque(false);
        workbenchcenter.add(tableScrollPane);

        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (row >= 0 && column >= 0) {
                Object newValue = tableModel.getValueAt(row, column);
                StudentData student = StudentDatabase.getStudentByRow(classroomId, row);

                switch (column) {
                    case 0 ->
                        student.setUsername(newValue.toString());
                    case 1 ->
                        student.setSurname(newValue.toString());
                    case 2 ->
                        student.setMiddleName(newValue.toString());
                    case 3 ->
                        student.setFirstName(newValue.toString());
                    case 4 ->
                        student.setGender(newValue.toString());
                    case 5 ->
                        student.setStudentNumber(Integer.parseInt(newValue.toString()));
                    case 6 ->
                        student.setAge(Integer.parseInt(newValue.toString()));
                }
            }
        });


    }
    
    public static List<StudentData> getStudents(String classroomId) {
    return StudentDatabase.getAllStudents(classroomId);
    }

    public List<StudentData> extractStudentsFromTable() {
        List<StudentData> extracted = new ArrayList<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String username = tableModel.getValueAt(i, 0).toString();
            String surname = tableModel.getValueAt(i, 1).toString();
            String middleName = tableModel.getValueAt(i, 2).toString();
            String firstName = tableModel.getValueAt(i, 3).toString();
            String gender = tableModel.getValueAt(i, 4).toString();
            int studentNumber = Integer.parseInt(tableModel.getValueAt(i, 5).toString());
            int age = Integer.parseInt(tableModel.getValueAt(i, 6).toString());

            extracted.add(new StudentData(username, surname, middleName, firstName, gender, studentNumber, age));
        }

        return extracted;
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
            StudentDatabase.saveStudents(classroomId);
            mainFrame.setDashboard(teacherData);
        }

        if (e.getSource() == logoutBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to LOG-OUT of this account?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                StudentDatabase.saveStudents(classroomId);
                mainFrame.showCard("login");
            }
        }

        if (e.getSource() == classworkBttn) {
            StudentDatabase.saveStudents(classroomId);
            mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == lectureBttn) {
            StudentDatabase.saveStudents(classroomId);
            mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == gradesBttn) {
            StudentDatabase.saveStudents(classroomId);
            mainFrame.showSpecificDashboardGrades(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == addStudBttn) {
            AddStudentLayout addstud = new AddStudentLayout();
            addstud.setVisible(true);
            StudentDatabase.sortStudentsBySurname(classroomId);
            refreshStudentTable();
        }

        if (e.getSource() == removeStudBttn) {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student to remove.");
                return;
            }

            String studentNumber = tableModel.getValueAt(selectedRow, 5).toString();
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to remove student #" + studentNumber + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                StudentDatabase.removeStudentByNumber(classroomId, studentNumber);
                tableModel.removeRow(selectedRow);
                refreshStudentTable();     
            }

            StudentDatabase.sortStudentsBySurname(classroomId);
            refreshStudentTable();
        }

        if (e.getSource() == searchBttn) {
            String keyword = searchField.getText().trim().toLowerCase();
            if (!keyword.isEmpty()) {
                List<StudentData> filtered = StudentDatabase.searchStudents(classroomId, keyword);
                tableModel.setRowCount(0);
                for (StudentData s : filtered) {
                    tableModel.addRow(new Object[]{
                        s.getUsername(), s.getSurname(), s.getMiddleName(),
                        s.getFirstName(), s.getGender(), s.getStudentNumber(), s.getAge()
                    });
                }
            }
        }

        if (e.getSource() == restoreBttn) {
            refreshStudentTable();
        }
    }

    public class AddStudentLayout extends JFrame implements ActionListener {

        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        JPanel formPanel = new JPanel();
        JTextField usernameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField middleNameField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField studentNumberField = new JTextField();
        JTextField ageField = new JTextField();
        JLabel user = new JLabel("Username:");
        JLabel sur = new JLabel("Surname:");
        JLabel mid = new JLabel("Middle Name:");
        JLabel fir = new JLabel("First Name:");
        JLabel gen = new JLabel("Gender:");
        JLabel stud = new JLabel("Student Number:");
        JLabel age1 = new JLabel("Age:");

        AddStudentLayout() {
            setSize(530, 660);
            this.setResizable(false);
            add(formPanel);
            formPanel.setVisible(true);
            this.setLocationRelativeTo(null);
            formPanel.setSize(530, 660);
            formPanel.setLayout(null);

            JPanel imagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;

                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    Image image = new ImageIcon(getClass().getResource("/echodraft/images/add student.png")).getImage();
                    g2d.drawImage(image, 0, 5, 515, 610, this);
                }
            };
            imagePanel.setPreferredSize(new Dimension(515, 610));
            imagePanel.setBounds(0, 0, 530, 660);
            formPanel.add(imagePanel);
            imagePanel.setLayout(null);

            usernameField.setBackground(Color.decode("#FAF0E6"));
            usernameField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            usernameField.setFont(new Font("Impact", Font.PLAIN, 15));
            usernameField.setBounds(200, 200, 250, 30);
            System.out.println("");

            surnameField.setBackground(Color.decode("#FAF0E6"));
            surnameField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            surnameField.setFont(new Font("Impact", Font.PLAIN, 15));
            surnameField.setBounds(200, 250, 250, 30);

            middleNameField.setBackground(Color.decode("#FAF0E6"));
            middleNameField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            middleNameField.setFont(new Font("Impact", Font.PLAIN, 15));
            middleNameField.setBounds(200, 300, 250, 30);

            firstNameField.setBackground(Color.decode("#FAF0E6"));
            firstNameField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            firstNameField.setFont(new Font("Impact", Font.PLAIN, 15));
            firstNameField.setBounds(200, 350, 250, 30);

            genderField.setBackground(Color.decode("#FAF0E6"));
            genderField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            genderField.setFont(new Font("Impact", Font.PLAIN, 15));
            genderField.setBounds(200, 400, 250, 30);

            studentNumberField.setBackground(Color.decode("#FAF0E6"));
            studentNumberField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            studentNumberField.setFont(new Font("Impact", Font.PLAIN, 15));
            studentNumberField.setBounds(200, 450, 250, 30);

            ageField.setBackground(Color.decode("#FAF0E6"));
            ageField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            ageField.setFont(new Font("Impact", Font.PLAIN, 15));
            ageField.setBounds(200, 500, 250, 30);

            user.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            user.setForeground(Color.decode("#4B1D3F"));
            user.setBounds(80, 200, 120, 40);

            sur.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            sur.setForeground(Color.decode("#4B1D3F"));
            sur.setBounds(80, 250, 120, 40);

            mid.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            mid.setForeground(Color.decode("#4B1D3F"));
            mid.setBounds(80, 300, 120, 40);

            fir.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            fir.setForeground(Color.decode("#4B1D3F"));
            fir.setBounds(80, 350, 120, 40);

            gen.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            gen.setForeground(Color.decode("#4B1D3F"));
            gen.setBounds(80, 400, 120, 40);

            stud.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            stud.setForeground(Color.decode("#4B1D3F"));
            stud.setBounds(80, 450, 120, 40);

            age1.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            age1.setForeground(Color.decode("#4B1D3F"));
            age1.setBounds(80, 500, 120, 40);

            imagePanel.add(user);
            imagePanel.add(usernameField);
            imagePanel.add(sur);
            imagePanel.add(surnameField);
            imagePanel.add(mid);
            imagePanel.add(middleNameField);
            imagePanel.add(fir);
            imagePanel.add(firstNameField);
            imagePanel.add(gen);
            imagePanel.add(genderField);
            imagePanel.add(stud);
            imagePanel.add(studentNumberField);
            imagePanel.add(age1);
            imagePanel.add(ageField);

            imagePanel.add(confirm);

            confirm.setBackground(Color.decode("#FFC343"));
            confirm.setForeground(Color.decode("#4B1D3F"));
            confirm.setFont(new Font("Impact", Font.PLAIN, 15));
            confirm.setBounds(320, 560, 120, 40);
            confirm.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirm) {
                try {
                    String username = usernameField.getText().trim();
                    String surname = surnameField.getText().trim();
                    String middleName = middleNameField.getText().trim();
                    String firstName = firstNameField.getText().trim();
                    String gender = genderField.getText().trim();
                    int studentNumber = Integer.parseInt(studentNumberField.getText().trim());
                    int age = Integer.parseInt(ageField.getText().trim());

                    StudentData newStudent = new StudentData(username, surname, middleName, firstName, gender, studentNumber, age);
                    String classroomId = String.valueOf(classroomData.getClassroomID());
                    StudentDatabase.addStudent(classroomId, newStudent);
                    StudentDatabase.sortStudentsBySurname(classroomId);

                    refreshStudentTable();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers for Student Number and Age.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                this.setVisible(false);
                usernameField.setText("");
                surnameField.setText("");
                middleNameField.setText("");
                firstNameField.setText("");
                genderField.setText("");
                studentNumberField.setText("");
                ageField.setText("");
            }
        }

    }


    
    private void refreshStudentTable() {
        String classroomId = String.valueOf(classroomData.getClassroomID());
        List<StudentData> students = StudentDatabase.getAllStudents(classroomId);
        tableModel.setRowCount(0);

        for (StudentData s : students) {
            tableModel.addRow(new Object[]{
                s.getUsername(), s.getSurname(), s.getMiddleName(),
                s.getFirstName(), s.getGender(), s.getStudentNumber(), s.getAge()
            });
        }
    }


}
