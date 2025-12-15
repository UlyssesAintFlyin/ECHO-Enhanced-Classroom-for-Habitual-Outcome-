package echodraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class ActualLecture extends JPanel implements ActionListener {

    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel toppanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JButton backBttn = new JButton("Back");
    JButton nextBttn = new JButton("Next");
    JButton exitBttn = new JButton("Exit");
    JTextArea slideTextArea = new JTextArea(3, 16);
    private JLabel pageNumLbl;

    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private LectureData lectureData;
    private Image backgroundImage;

    public ActualLecture(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, LectureData lectureData) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        this.lectureData = lectureData;
        lectureData.setCurrentSlide(0);

        setLayout(new BorderLayout());

        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/echodraft/images/desk.png"));
        backgroundImage = bgIcon.getImage();

        toppanel.setBackground(Color.decode("#4B1D3F"));
        toppanel.setPreferredSize(new Dimension(0, 60));
        toppanel.setLayout(new BorderLayout());

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

        centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        centerPanel.setBackground(Color.decode("#FFF5DD"));
        centerPanel.setOpaque(false);
        centerPanel.setLayout(null);
        add(centerPanel, BorderLayout.CENTER);

        JLabel titleLbl = new JLabel(lectureData.getLectureTitle());
        centerPanel.add(titleLbl);
        titleLbl.setForeground(Color.decode("#EDE7E3"));
        titleLbl.setFont(new Font("Impact", Font.PLAIN, 40));
        titleLbl.setBounds(30, 10, 600, 50);

        centerPanel.add(sidePanel);
        sidePanel.setLayout(null);
        sidePanel.setBackground(ColorUtils.getColorFromName(lectureData.getColorName()));
        sidePanel.setBounds(150, 60, 1250, 645);

        centerPanel.add(nextBttn);
        nextBttn.setBackground(Color.decode("#BC9E5C"));
        nextBttn.setForeground(Color.decode("#EDE7E3"));
        nextBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        nextBttn.setFocusable(false);
        nextBttn.setBorderPainted(false);
        nextBttn.addActionListener(this);
        nextBttn.setBounds(30, 450, 80, 40);

        centerPanel.add(backBttn);
        backBttn.setBackground(Color.decode("#BC9E5C"));
        backBttn.setForeground(Color.decode("#EDE7E3"));
        backBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        backBttn.setFocusable(false);
        backBttn.setBorderPainted(false);
        backBttn.addActionListener(this);
        backBttn.setBounds(30, 510, 80, 40);

        pageNumLbl = new JLabel("Page: 1/1");
        centerPanel.add(pageNumLbl);
        pageNumLbl.setForeground(Color.decode("#D9D9D9"));
        pageNumLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        pageNumLbl.setBounds(30, 600, 100, 30);

        slideTextArea.setBackground(Color.decode("#D9D9D9"));
        slideTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        slideTextArea.setBounds(30, 20, 1190, 585);
        slideTextArea.setForeground(Color.BLACK);
        slideTextArea.setEditable(false);
        slideTextArea.setLineWrap(true);
        slideTextArea.setWrapStyleWord(true);
        slideTextArea.setFocusable(false);
        
        JScrollPane questionScrollPane = new JScrollPane(slideTextArea);
        questionScrollPane.setBounds(30, 20, 1190, 585);
        questionScrollPane.setOpaque(false);
        questionScrollPane.getViewport().setOpaque(false);
        questionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sidePanel.add(questionScrollPane);

        slideTextArea.setText(lectureData.getCurrentSlide());
        pageNumLbl.setText("Page: " + (lectureData.getCurrentSlideIndex() + 1) + "/" + lectureData.getSlideCount());
        
        centerPanel.add(exitBttn);
        exitBttn.setBackground(Color.decode("#D62246"));
        exitBttn.setForeground(Color.decode("#EDE7E3"));
        exitBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        exitBttn.setFocusable(false);
        exitBttn.setBorderPainted(false);
        exitBttn.addActionListener(this);
        exitBttn.setBounds(1430, 660, 80, 40);
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBttn) {
            int currentIndex = lectureData.getCurrentSlideIndex();
            int totalSlides = lectureData.getSlideCount();

            if (currentIndex < totalSlides - 1) {
                lectureData.nextSlide();
                slideTextArea.setText(lectureData.getCurrentSlide());
                pageNumLbl.setText("Page: " + (lectureData.getCurrentSlideIndex() + 1) + "/" + totalSlides);
            } else {
                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "You've reached the end of the lecture.\nDo you want to return to the Lecture Dashboard?",
                        "End of Lecture",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
                }
            }

        }

        if (e.getSource() == backBttn) {
            if (lectureData.getCurrentSlideIndex() > 0) {
                lectureData.previousSlide();
                slideTextArea.setText(lectureData.getCurrentSlide());
                pageNumLbl.setText("Page: " + (lectureData.getCurrentSlideIndex() + 1) + "/" + lectureData.getSlideCount());
            }

        }
        
        if (e.getSource() == exitBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit this flashcard?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
            }
        }
        
        
    }

}


