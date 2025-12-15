package echodraft;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

public class ClassroomCard extends JPanel {

    private static final int CARD_RADIUS = 20;
    private static final Color HEADER_COLOR = new Color(0x50CDA0);
    private static final Font TITLE_FONT = new Font("Impact", Font.BOLD, 22);
    private static final Font META_FONT = new Font("SansSerif", Font.PLAIN, 14);

    private JLabel classroomLabel;
    private JLabel subjectLabel;
    private JLabel sectionLabel;
    private boolean isHovered = false;
    private ClassroomData classroomData;
    private MainFrame mainframe;
    private TeacherData teacherData;

    public ClassroomCard(ClassroomData classroomData, MainFrame mainFrame, TeacherData teacherData) {
        this.classroomData = classroomData;
        this.mainframe = mainFrame;
        this.teacherData = teacherData;
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 180));
        setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel header = new JPanel();
        header.setBackground(ColorUtils.getColorFromName(classroomData.getColor()));
        header.setPreferredSize(new Dimension(0, 20));
        add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        classroomLabel = new JLabel(classroomData.getClassroomName());
        classroomLabel.setFont(TITLE_FONT);
        classroomLabel.setForeground(new Color(0x4B1D3F));
        classroomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subjectLabel = new JLabel("Subject: " + classroomData.getSubjectName());
        subjectLabel.setFont(META_FONT);
        subjectLabel.setForeground(Color.GRAY);
        subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sectionLabel = new JLabel("Section: " + classroomData.getSectionName());
        sectionLabel.setFont(META_FONT);
        sectionLabel.setForeground(Color.GRAY);
        sectionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(classroomLabel);
        content.add(Box.createRigidArea(new Dimension(0, 8)));
        content.add(subjectLabel);
        content.add(Box.createRigidArea(new Dimension(0, 6)));
        content.add(sectionLabel);
        content.add(Box.createRigidArea(new Dimension(0, 4)));

        add(content, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setCursor(Cursor.getDefaultCursor());
                repaint();
            }
        });

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), CARD_RADIUS, CARD_RADIUS);

        if (isHovered) {
            g2.setColor(Color.decode("#A23D87")); 
            g2.setStroke(new BasicStroke(3)); 
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, CARD_RADIUS, CARD_RADIUS);
        }

        g2.dispose();
    }

}

class ColorUtils {

    public static Color getColorFromName(String colorName) {
        switch (colorName.toLowerCase()) {
            case "blue":
                return Color.decode("#106FDC");
            case "cyan":
                return Color.decode("#17BEBB");
            case "green":
                return Color.decode("#17BE81");
            case "orange":
                return Color.decode("#E65C19");
            case "red":
                return Color.decode("#D62246");
            case "purple":
                return Color.decode("#92487A");
            case "gold":
                return Color.decode("#F5AD18");
            case "pink":
                return Color.decode("#D622A0");
            default:
                return new Color(0x50CDA0);
        }
    }
}

class AddClassroom extends JFrame implements ActionListener {

    JLabel createLbl = new JLabel();
    JLabel createClassLbl = new JLabel("Create Class");
    JLabel colorNameLbl = new JLabel("Class Color");
    JLabel subjectNameLbl = new JLabel("Subject Name");
    JLabel sectionNameLbl = new JLabel("Section Name");
    JPanel toppanel = new JPanel();
    JLabel classroomNameLbl = new JLabel("Class Name");
    String[] colors = {"Blue", "Cyan", "Green", "Orange", "Red", "Purple", "Gold", "Pink"};
    JComboBox colorCbox = new JComboBox<>(colors);
    JTextField sectionTxt = new JTextField();
    JTextField subjectTxt = new JTextField();
    JTextField ClassroomNameTxt = new JTextField();
    JButton createBttn = new JButton("Create");
    private ClassroomDashboard dashboard;
    private TeacherData teacherdata;

    public AddClassroom(TeacherData teacherdata, ClassroomDashboard dashboard) {
        this.teacherdata = teacherdata;
        this.dashboard = dashboard;
        setSize(530, 660);
        this.setResizable(false);
        JPanel createpanel = new JPanel();
        add(createpanel);
        createpanel.setVisible(true);
        this.setLocationRelativeTo(null);
        createpanel.setSize(530, 660);
        createpanel.setLayout(null);

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Image image = new ImageIcon(getClass().getResource("/echodraft/images/createclass.png")).getImage();
                g2d.drawImage(image, 0, 5, 515, 610, this);
            }
        };
        imagePanel.setPreferredSize(new Dimension(515, 610));
        imagePanel.setBounds(0, 0, 530, 660);
        createpanel.add(imagePanel);
        imagePanel.setLayout(null);

        toppanel.setBackground(Color.decode("#DCDCDB"));
        toppanel.setBounds(10, 20, 460, 100);
        toppanel.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

        toppanel.setLayout(null);
        imagePanel.add(createLbl);
        createLbl.setFont(new Font("Impact", Font.BOLD, 40));
        createLbl.setForeground(Color.decode("#4B1D3F"));
        createLbl.setBounds(40, 15, 400, 40);

        imagePanel.add(classroomNameLbl);
        classroomNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        classroomNameLbl.setForeground(Color.BLACK);
        classroomNameLbl.setBounds(80, 240, 200, 40);

        imagePanel.add(ClassroomNameTxt);
        ClassroomNameTxt.setBackground(Color.decode("#FAF0E6"));
        ClassroomNameTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        ClassroomNameTxt.setFont(new Font("Impact", Font.PLAIN, 15));
        ClassroomNameTxt.setBounds(80, 275, 150, 40);

        createClassLbl.setFont(new Font("Impact", Font.BOLD, 35));
        createClassLbl.setForeground(Color.decode("#4B1D3F"));
        createClassLbl.setBounds(1, 1, 400, 40);

        imagePanel.add(colorNameLbl);
        colorNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        colorNameLbl.setForeground(Color.BLACK);
        colorNameLbl.setBounds(300, 240, 400, 40);

        colorCbox.setFont(new Font("Times new roman", Font.PLAIN, 20));
        colorCbox.setBounds(300, 275, 130, 40);
        colorCbox.setBackground(Color.decode("#FAF0E6"));
        colorCbox.setForeground(Color.decode("#4B1D3F"));
        colorCbox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        imagePanel.add(colorCbox);

        imagePanel.add(subjectNameLbl);
        subjectNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        subjectNameLbl.setForeground(Color.BLACK);
        subjectNameLbl.setBounds(80, 325, 400, 40);

        subjectTxt.setBackground(Color.decode("#FAF0E6"));
        subjectTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        subjectTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        subjectTxt.setBounds(80, 360, 350, 40);
        imagePanel.add(subjectTxt);

        imagePanel.add(sectionNameLbl);
        sectionNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        sectionNameLbl.setForeground(Color.BLACK);
        sectionNameLbl.setBounds(80, 415, 400, 40);

        sectionTxt.setBackground(Color.decode("#FAF0E6"));
        sectionTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        sectionTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        sectionTxt.setBounds(80, 450, 350, 40);
        imagePanel.add(sectionTxt);

        imagePanel.add(createBttn);
        createBttn.setBackground(Color.decode("#FFC343"));
        createBttn.setForeground(Color.decode("#4B1D3F"));
        createBttn.setFont(new Font("Impact", Font.PLAIN, 15));
        createBttn.setBounds(320, 530, 120, 40);
        createBttn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String color = colorCbox.getSelectedItem().toString();
        String name = ClassroomNameTxt.getText().trim();
        String section = sectionTxt.getText().trim();
        String subject = subjectTxt.getText().trim();
        if (e.getSource() == createBttn) {
            
            //It prevents creating duplicate classrooms with the same infomrmation.
            for (ClassroomData cd : ClassDatabase.getClassesByTeacher(teacherdata.getUsername())) {
                if (cd.getClassroomName().equalsIgnoreCase(name)
                        && cd.getSectionName().equalsIgnoreCase(section)
                        && cd.getSubjectName().equalsIgnoreCase(subject)) {
                    JOptionPane.showMessageDialog(this, "A class with the same name, section, and subject already exists.", "Duplicate Class", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            //Ensures inputs don’t exceed 50 characters.
            if (name.length() > 50 || section.length() > 50 || subject.length() > 50) {
                JOptionPane.showMessageDialog(this, "Fields must not exceed 50 characters.", "Input Too Long", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (name.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill the class name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (section.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill the section name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (subject.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill the subject name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.isBlank() || section.isBlank() || subject.isBlank()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be blank or whitespace only.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //after all the input validation, it would proceed to save the classroom
            if (!name.isEmpty() && !subject.isEmpty() && !section.isEmpty()) {
                ClassDatabase.addClass(name, subject, section, color, teacherdata.getUsername());
                JOptionPane.showMessageDialog(this, "Classroom created successfully!");

                if (dashboard != null) {
                    dashboard.refreshWorkbench();
                }
                dispose();
            }
        }
    }

}

class EditClassroomCard extends JFrame implements ActionListener {

    JLabel createLbl = new JLabel();
    JLabel colorNameLbl = new JLabel("Class Color");
    JLabel subjectNameLbl = new JLabel("Subject Name");
    JLabel sectionNameLbl = new JLabel("Section Name");
    JPanel toppanel = new JPanel();
    String[] colors = {"Blue", "Cyan", "Green", "Orange", "Red", "Purple", "Gold", "Pink"};
    JComboBox colorCbox = new JComboBox<>(colors);
    JLabel selectClassLbl = new JLabel("Select Class");
    private JComboBox<ClassItem> classSelector;
    JLabel classNameLbl = new JLabel("Classroom Name");
    JTextField ClassroomNameTxt = new JTextField();
    JTextField sectionTxt = new JTextField();
    JTextField subjectTxt = new JTextField();

    JButton updateBttn = new JButton("Update");
    private ClassroomCardListener listener;
    private TeacherData teacherData;
    private ClassroomDashboard dashboard;
    private ClassroomData classroomData;

    public EditClassroomCard(TeacherData teacherData, ClassroomCardListener listener, ClassroomDashboard dashboard, ClassroomData classroomData) {
        this.teacherData = teacherData;
        this.listener = listener;
        this.dashboard = dashboard;
        this.classroomData = classroomData;
        List<ClassroomData> teacherClasses = ClassDatabase.getClassesByTeacher(teacherData.getUsername());

        classSelector = new JComboBox<>();
        for (ClassroomData cd : teacherClasses) {
            classSelector.addItem(new ClassItem(cd.getClassroomID(), cd.getClassroomName()));
        }

        setSize(530, 660);
        this.setResizable(false);
        JPanel createpanel = new JPanel();
        add(createpanel);
        createpanel.setVisible(true);
        this.setLocationRelativeTo(null);
        createpanel.setSize(530, 660);
        createpanel.setLayout(null);

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Image image = new ImageIcon(getClass().getResource("/echodraft/images/editClass.png")).getImage();
                g2d.drawImage(image, 0, 5, 515, 610, this);
            }
        };
        imagePanel.setPreferredSize(new Dimension(515, 610));
        imagePanel.setBounds(0, 0, 530, 660);
        createpanel.add(imagePanel);
        imagePanel.setLayout(null);

        toppanel.setBackground(Color.decode("#DCDCDB"));
        toppanel.setBounds(10, 20, 460, 100);
        toppanel.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

        toppanel.setLayout(null);
        imagePanel.add(createLbl);
        createLbl.setFont(new Font("Impact", Font.BOLD, 40));
        createLbl.setForeground(Color.decode("#4B1D3F"));
        createLbl.setBounds(40, 15, 400, 40);

        imagePanel.add(selectClassLbl);
        selectClassLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        selectClassLbl.setForeground(Color.BLACK);
        selectClassLbl.setBounds(80, 230, 200, 40);

        imagePanel.add(classSelector);
        classSelector.setBackground(Color.decode("#FAF0E6"));
        classSelector.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        classSelector.setFont(new Font("Impact", Font.PLAIN, 15));
        classSelector.setBounds(80, 265, 150, 40);
        classSelector.addActionListener(this);

        imagePanel.add(classNameLbl);
        classNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        classNameLbl.setForeground(Color.BLACK);
        classNameLbl.setBounds(80, 300, 285, 40);

        ClassroomNameTxt.setBackground(Color.decode("#FAF0E6"));
        ClassroomNameTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        ClassroomNameTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        ClassroomNameTxt.setBounds(80, 330, 350, 40);
        imagePanel.add(ClassroomNameTxt);

        imagePanel.add(colorNameLbl);
        colorNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        colorNameLbl.setForeground(Color.BLACK);
        colorNameLbl.setBounds(300, 230, 400, 40);

        colorCbox.setFont(new Font("Times new roman", Font.PLAIN, 20));
        colorCbox.setBounds(300, 265, 130, 40);
        colorCbox.setBackground(Color.decode("#FAF0E6"));
        colorCbox.setForeground(Color.decode("#4B1D3F"));
        colorCbox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        imagePanel.add(colorCbox);

        imagePanel.add(subjectNameLbl);
        subjectNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        subjectNameLbl.setForeground(Color.BLACK);
        subjectNameLbl.setBounds(80, 365, 400, 40);

        subjectTxt.setBackground(Color.decode("#FAF0E6"));
        subjectTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        subjectTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        subjectTxt.setBounds(80, 400, 350, 40);
        imagePanel.add(subjectTxt);

        imagePanel.add(sectionNameLbl);
        sectionNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        sectionNameLbl.setForeground(Color.BLACK);
        sectionNameLbl.setBounds(80, 435, 400, 40);

        sectionTxt.setBackground(Color.decode("#FAF0E6"));
        sectionTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        sectionTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        sectionTxt.setBounds(80, 470, 350, 40);
        imagePanel.add(sectionTxt);

        imagePanel.add(updateBttn);
        updateBttn.setBackground(Color.decode("#FFC343"));
        updateBttn.setForeground(Color.decode("#4B1D3F"));
        updateBttn.setFont(new Font("Impact", Font.PLAIN, 15));
        updateBttn.setBounds(320, 530, 120, 40);
        updateBttn.addActionListener(this);

    }

    public interface ClassroomCardListener {

        void onClassroomCreated(String className, String subject, String section, String color);

    }

    private static class ClassItem {

        private final int id;
        private final String name;

        public ClassItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == classSelector) {
            ClassItem selectedItem = (ClassItem) classSelector.getSelectedItem();
            if (selectedItem == null) {
                return;
            }

            ClassroomData selectedClass = ClassDatabase.getClassById(selectedItem.getId());

            if (selectedClass != null) {
                subjectTxt.setText(selectedClass.getSubjectName());
                sectionTxt.setText(selectedClass.getSectionName());
                ClassroomNameTxt.setText(selectedClass.getClassroomName());
                colorCbox.setSelectedItem(selectedClass.getColor());
            }
        }
        
        if (e.getSource() == updateBttn) {
            ClassItem selectedItem = (ClassItem) classSelector.getSelectedItem();
            String className = ClassroomNameTxt.getText().trim();
            String subjectName = subjectTxt.getText().trim();
            String sectionName = sectionTxt.getText().trim();
            String colorName = (String) colorCbox.getSelectedItem();
            
            for (ClassroomData cd : ClassDatabase.getClassesByTeacher(teacherData.getUsername())) {
                if (cd.getClassroomID() != selectedItem.getId()
                        && cd.getClassroomName().equalsIgnoreCase(className)) {
                    JOptionPane.showMessageDialog(this, "Another class with the same name already exists.", "Duplicate Class Name", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (className.length() > 50 || subjectName.length() > 50 || sectionName.length() > 50) {
                JOptionPane.showMessageDialog(this, "Fields must not exceed 50 characters.", "Input Too Long", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (selectedItem == null) {
                JOptionPane.showMessageDialog(this, "Please select a class to update.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (className.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill the class name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sectionName.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill the section name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (subjectName.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill the subject name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (colorName.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill the subject name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //after passing the validation, it saves the data based on the selected classroon
            ClassroomData cd = ClassDatabase.getClassById(selectedItem.getId());
            if (cd != null) {
                cd.setClassroomName(className);
                cd.setSubjectName(subjectName);
                cd.setSectionName(sectionName);
                cd.setColor(colorName);

                JOptionPane.showMessageDialog(this, "Classroom updated successfully!");
                if (dashboard != null) {
                    dashboard.refreshWorkbench();
                }
                dispose();
            }
        }
    }
}

class DeleteClassroomCard extends JFrame implements ActionListener {

    JLabel createLbl = new JLabel();
    JLabel colorNameLbl = new JLabel("Class Color");
    JLabel subjectNameLbl = new JLabel("Subject Name");
    JLabel sectionNameLbl = new JLabel("Section Name");
    JPanel toppanel = new JPanel();
    String[] colors = {"Blue", "Cyan", "Green", "Orange", "Red", "Purple", "Gold", "Pink"};
    JComboBox colorCbox = new JComboBox<>(colors);
    JLabel selectClassLbl = new JLabel("Select Class");
    private JComboBox<ClassItem> classSelector;
    JLabel classNameLbl = new JLabel("Classroom Name");
    JTextField ClassroomNameTxt = new JTextField();
    JTextField sectionTxt = new JTextField();
    JTextField subjectTxt = new JTextField();

    JButton deleteBttn = new JButton("DELETE");
    private ClassroomCardListener listener;
    private TeacherData teacherData;
    private ClassroomDashboard dashboard;

    public DeleteClassroomCard(TeacherData teacherData, ClassroomCardListener listener, ClassroomDashboard dashboard) {
        this.teacherData = teacherData;
        this.listener = listener;
        this.dashboard = dashboard;
        List<ClassroomData> teacherClasses = ClassDatabase.getClassesByTeacher(teacherData.getUsername());

        classSelector = new JComboBox<>();
        for (ClassroomData cd : teacherClasses) {
            classSelector.addItem(new ClassItem(cd.getClassroomID(), cd.getClassroomName()));
        }

        setSize(530, 660);
        this.setResizable(false);
        JPanel createpanel = new JPanel();
        add(createpanel);
        createpanel.setVisible(true);
        this.setLocationRelativeTo(null);
        createpanel.setSize(530, 660);
        createpanel.setLayout(null);

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Image image = new ImageIcon(getClass().getResource("/echodraft/images/deleteClass.png")).getImage();
                g2d.drawImage(image, 0, 5, 515, 610, this);
            }
        };
        imagePanel.setPreferredSize(new Dimension(515, 610));
        imagePanel.setBounds(0, 0, 530, 660);
        createpanel.add(imagePanel);
        imagePanel.setLayout(null);

        toppanel.setBackground(Color.decode("#DCDCDB"));
        toppanel.setBounds(10, 20, 460, 100);
        toppanel.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

        toppanel.setLayout(null);
        imagePanel.add(createLbl);
        createLbl.setFont(new Font("Impact", Font.BOLD, 40));
        createLbl.setForeground(Color.decode("#4B1D3F"));
        createLbl.setBounds(40, 15, 400, 40);

        imagePanel.add(selectClassLbl);
        selectClassLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        selectClassLbl.setForeground(Color.BLACK);
        selectClassLbl.setBounds(80, 230, 200, 40);

        imagePanel.add(classSelector);
        classSelector.setBackground(Color.decode("#FAF0E6"));
        classSelector.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        classSelector.setFont(new Font("Impact", Font.PLAIN, 15));
        classSelector.setBounds(80, 265, 150, 40);
        classSelector.addActionListener(this);

        imagePanel.add(classNameLbl);
        classNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        classNameLbl.setForeground(Color.BLACK);
        classNameLbl.setBounds(80, 300, 285, 40);

        ClassroomNameTxt.setBackground(Color.decode("#FAF0E6"));
        ClassroomNameTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        ClassroomNameTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        ClassroomNameTxt.setBounds(80, 330, 350, 40);
        ClassroomNameTxt.setEditable(false);
        imagePanel.add(ClassroomNameTxt);

        imagePanel.add(colorNameLbl);
        colorNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        colorNameLbl.setForeground(Color.BLACK);
        colorNameLbl.setBounds(300, 230, 400, 40);

        colorCbox.setFont(new Font("Times new roman", Font.PLAIN, 20));
        colorCbox.setBounds(300, 265, 130, 40);
        colorCbox.setBackground(Color.decode("#FAF0E6"));
        colorCbox.setForeground(Color.decode("#4B1D3F"));
        colorCbox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        colorCbox.setEnabled(false);
        imagePanel.add(colorCbox);

        imagePanel.add(subjectNameLbl);
        subjectNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        subjectNameLbl.setForeground(Color.BLACK);
        subjectNameLbl.setBounds(80, 365, 400, 40);

        subjectTxt.setBackground(Color.decode("#FAF0E6"));
        subjectTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        subjectTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        subjectTxt.setBounds(80, 400, 350, 40);
        subjectTxt.setEditable(false);
        imagePanel.add(subjectTxt);

        imagePanel.add(sectionNameLbl);
        sectionNameLbl.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        sectionNameLbl.setForeground(Color.BLACK);
        sectionNameLbl.setBounds(80, 435, 400, 40);

        sectionTxt.setBackground(Color.decode("#FAF0E6"));
        sectionTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        sectionTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        sectionTxt.setBounds(80, 470, 350, 40);
        sectionTxt.setEditable(false);
        imagePanel.add(sectionTxt);

        imagePanel.add(deleteBttn);
        deleteBttn.setBackground(Color.decode("#FFC343"));
        deleteBttn.setForeground(Color.decode("#4B1D3F"));
        deleteBttn.setFont(new Font("Impact", Font.PLAIN, 15));
        deleteBttn.setBounds(320, 530, 120, 40);
        deleteBttn.addActionListener(this);

    }

    public interface ClassroomCardListener {

        void onClassroomCreated(String className, String subject, String section, String color);

    }
    
    //groups the classroom data used in the JComboBox (classSelector).
    private static class ClassItem {

        private final int id;
        private final String name;

        public ClassItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == classSelector) {
            ClassItem selectedItem = (ClassItem) classSelector.getSelectedItem();
            if (selectedItem == null) {
                return;
            }

            ClassroomData selectedClass = ClassDatabase.getClassById(selectedItem.getId());
            if (selectedClass != null) {
                subjectTxt.setText(selectedClass.getSubjectName());
                sectionTxt.setText(selectedClass.getSectionName());
                ClassroomNameTxt.setText(selectedClass.getClassroomName());
                colorCbox.setSelectedItem(selectedClass.getColor());
            }

        }

        if (e.getSource() == deleteBttn) {
            ClassItem selectedItem = (ClassItem) classSelector.getSelectedItem();
            if (selectedItem == null) {
                return;
            }

            ClassroomData selectedClass = ClassDatabase.getClassById(selectedItem.getId());
            if (selectedClass != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete \"" + selectedClass.getClassroomName() + "\"?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean removed = ClassDatabase.removeClassById(selectedClass.getClassroomID());
                    if (removed) {
                        JOptionPane.showMessageDialog(this, "Class deleted successfully.");
                        if (dashboard != null) {
                            dashboard.refreshWorkbench();
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete class.");
                    }
                }
            }

        }

    }
}
