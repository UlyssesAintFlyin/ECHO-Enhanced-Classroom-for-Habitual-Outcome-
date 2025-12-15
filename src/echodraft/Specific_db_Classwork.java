package echodraft;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class Specific_db_Classwork extends JPanel implements ActionListener {

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
    JLabel centerTitle = new JLabel("   FLashcards:");
    JPanel centerTop = new JPanel();
    JButton addFlashcardBttn = new JButton("Add Flashcard");
    JButton editFlashcardBttn = new JButton("Edit Flashcard");
    JButton deleteFlashcardBttn = new JButton("Delete Flashcard");

    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private FlashcardData flashcardData;
    private Image backgroundImage;

    public Specific_db_Classwork(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardData flashcardData) {
        this.classroomData = classroomData;
        this.flashcardData = flashcardData;
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

        classworkBttn.setContentAreaFilled(true);
        classworkBttn.setBackground(Color.decode("#5E2A51"));

        dashboardBttn.addActionListener(this);
        lectureBttn.addActionListener(this);
        classworkBttn.addActionListener(this);
        peopleBttn.addActionListener(this);
        gradesBttn.addActionListener(this);

        sidepanel.add(ButtonPanel, BorderLayout.CENTER);

        centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());

        centerTop.setPreferredSize(new Dimension(220, 80)); 
        centerTop.setOpaque(false);
        centerTop.setLayout(new BorderLayout()); 

        centerPanel.add(centerTop, BorderLayout.NORTH);

        centerTitle.setForeground(Color.decode("#EDE7E3"));
        centerTitle.setFont(new Font("Impact", Font.PLAIN, 40));
        centerTop.add(centerTitle, BorderLayout.WEST);

        JPanel buttonRowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        buttonRowPanel.setOpaque(false);

        buttonRowPanel.add(addFlashcardBttn);
        buttonRowPanel.add(editFlashcardBttn);
        buttonRowPanel.add(deleteFlashcardBttn);

        for (JButton bttn : new JButton[]{addFlashcardBttn, editFlashcardBttn, deleteFlashcardBttn}) {
            bttn.setBackground(Color.decode("#BA66A4"));
            bttn.setForeground(Color.decode("#EDE7E3"));
            bttn.setFont(new Font("Impact", Font.ITALIC, 20));
            bttn.setBorderPainted(false);
            bttn.setFocusPainted(false);
        }

        addFlashcardBttn.addActionListener(this);
        editFlashcardBttn.addActionListener(this);
        deleteFlashcardBttn.addActionListener(this);

        centerTop.add(buttonRowPanel, BorderLayout.EAST);

        workbenchPanel.setLayout(new GridLayout(0, 3, 30, 20));
        workbenchPanel.setOpaque(false);
        workbenchPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JScrollPane scrollPane = new JScrollPane(workbenchPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setViewportBorder(null);

        centerPanel.add(scrollPane, BorderLayout.CENTER); 
        add(centerPanel, BorderLayout.CENTER);

        List<String> titles = FlashcardDatabase.getDistinctTitlesByClassroom(classroomData.getClassroomID());

        for (String title : titles) {
            List<FlashcardData> cards = FlashcardDatabase.getFlashcardsByTitleAndClassroom(title, classroomData.getClassroomID());

            if (!cards.isEmpty()) {
                FlashcardData firstCard = cards.get(0);
                FlashcardSet set = new FlashcardSet(
                        title,
                        firstCard.getColorName(),
                        classroomData.getClassroomID(),
                        firstCard.getCreationDate()
                );

                set.getCards().addAll(cards);

                FlashcardDisplay card = new FlashcardDisplay(classroomData, teacherData, mainFrame, set);
                workbenchPanel.add(card);
            }
        }

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
        if (e.getSource() == dashboardBttn) {
            mainFrame.setDashboard(teacherData);
        }

        if (e.getSource() == logoutBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to LOG-OUT of this account?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                mainFrame.showCard("login");
            }
        }

        if (e.getSource() == lectureBttn) {
            mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == peopleBttn) {
            mainFrame.showSpecificDashboardPeople(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == gradesBttn) {
            mainFrame.showSpecificDashboardGrades(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == addFlashcardBttn) {
            mainFrame.addFlashcard(classroomData, teacherData, mainFrame, flashcardData);
        }

        if (e.getSource() == editFlashcardBttn) {
            mainFrame.editFlashcard(classroomData, teacherData, mainFrame, flashcardData);
        }
        if (e.getSource() == deleteFlashcardBttn) {
            new RemoveFlashcard(classroomData, teacherData, mainFrame);

        }

    }

}
