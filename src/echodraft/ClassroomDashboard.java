package echodraft;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class ClassroomDashboard extends JPanel implements ActionListener {

    JLabel dashboardLbl = new JLabel("   Classroom Dashboard");
    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel topSidePanel = new JPanel();
    JPanel toppanel = new JPanel();
    JPanel sidepanel = new JPanel();
    JPanel UpperPanel = new JPanel();
    JLabel nameLbl = new JLabel("  " + "Instructor:");
    JPanel workbenchPanel = new JPanel();
    JButton dashboardBttn = new JButton("Dashboard");
    JButton addClassBttn = new JButton("ADD CLASSROOM");
    JButton editClassBttn = new JButton("EDIT CLASSROOM");
    JButton deleteClassBttn = new JButton("DELETE CLASSROOM");
    JButton logoutBttn = new JButton("Log-out");

    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private Image backgroundImage;
    
    public ClassroomDashboard(MainFrame mainFrame, TeacherData teacherData, ClassroomData classroomData) {
        this.mainFrame = mainFrame;
        this.teacherData = teacherData;
        this.teacherData = teacherData;
        this.classroomData = classroomData;

        setLayout(new BorderLayout());

        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/echodraft/images/ClassroomBG.png"));
        backgroundImage = bgIcon.getImage();

        toppanel.setBackground(Color.decode("#4B1D3F"));
        toppanel.setPreferredSize(new Dimension(0, 60));
        toppanel.setLayout(new BorderLayout());
        add(toppanel, BorderLayout.NORTH);

        topSidePanel.setBackground(Color.decode("#FFF5DD")); 
        topSidePanel.setPreferredSize(new Dimension(220, 60)); 
        topSidePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topSidePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.decode("#C5AF80")));
        toppanel.add(topSidePanel, BorderLayout.WEST);
        JLabel titleLbl = new JLabel("E  C  H  O");
        titleLbl.setFont(new Font("Impact", Font.PLAIN, 40));
        titleLbl.setForeground(Color.decode("#4B1D3F"));
        topSidePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        topSidePanel.add(titleLbl, gbc);
        topSidePanel.add(titleLbl);

        dashboardLbl.setFont(new Font("Impact", Font.PLAIN, 30));
        dashboardLbl.setForeground(Color.decode("#F1FAEE"));
        toppanel.add(dashboardLbl, BorderLayout.CENTER);

        appNameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        appNameLbl.setForeground(Color.decode("#F1FAEE"));
        toppanel.add(appNameLbl, BorderLayout.EAST);

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

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));
        ButtonPanel.setBackground(Color.decode("#4B1D3F"));

        dashboardBttn = createSidebarButton("Dashboard");
        addClassBttn = createSidebarButton("Add Classroom");
        editClassBttn = createSidebarButton("Edit Classroom");
        deleteClassBttn = createSidebarButton("Delete Classroom");
        ButtonPanel.add(dashboardBttn);
        ButtonPanel.add(addClassBttn);
        ButtonPanel.add(editClassBttn);
        ButtonPanel.add(deleteClassBttn);

        dashboardBttn.addActionListener(this);
        addClassBttn.addActionListener(this);
        editClassBttn.addActionListener(this);
        deleteClassBttn.addActionListener(this);
        
        dashboardBttn.setContentAreaFilled(true);
        dashboardBttn.setBackground(Color.decode("#5E2A51"));

        sidepanel.add(ButtonPanel, BorderLayout.CENTER);

        logoutBttn.setBackground(Color.decode("#D62246"));
        logoutBttn.setForeground(Color.decode("#F1FAEE"));
        logoutBttn.setFont(new Font("Constantia", Font.BOLD, 25));
        logoutBttn.setMaximumSize(new Dimension(300, 50));
        logoutBttn.setBorderPainted(false);
        logoutBttn.setFocusPainted(false);
        logoutBttn.addActionListener(this);
        sidepanel.add(logoutBttn, BorderLayout.SOUTH);

        workbenchPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        workbenchPanel.setLayout(new GridLayout(0, 3, 20, 20)); 
        workbenchPanel.setOpaque(false);
        add(workbenchPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(workbenchPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        List<ClassroomData> teacherClasses = ClassDatabase.getClassesByTeacher(teacherData.getUsername());
        for (ClassroomData cd : teacherClasses) {
            ClassroomCard card = new ClassroomCard(cd, mainFrame, teacherData); 
            workbenchPanel.add(card);
        }

        add(scrollPane, BorderLayout.CENTER);
        workbenchPanel.revalidate();
        workbenchPanel.repaint();
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
        if (e.getSource()==logoutBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to LOG-OUT of this account?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                mainFrame.showCard("login");
            }
        }
        
        if (e.getSource() == addClassBttn) {
            AddClassroom form = new AddClassroom(teacherData, this); 
            form.setVisible(true);

        }
        
        if (e.getSource() == editClassBttn) {
            EditClassroomCard editForm = new EditClassroomCard(teacherData, null, this, classroomData);

            editForm.setVisible(true);

        }

        if (e.getSource() == deleteClassBttn) {
            DeleteClassroomCard deleteForm = new DeleteClassroomCard(teacherData, null, this);

            deleteForm.setVisible(true);
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void refreshWorkbench() {
        workbenchPanel.removeAll(); 

        List<ClassroomData> teacherClasses = ClassDatabase.getClassesByTeacher(teacherData.getUsername());
        for (ClassroomData cd : teacherClasses) {
            ClassroomCard card = new ClassroomCard(cd, mainFrame, teacherData); 
            workbenchPanel.add(card);
        }

        workbenchPanel.revalidate();
        workbenchPanel.repaint();
    }

}
