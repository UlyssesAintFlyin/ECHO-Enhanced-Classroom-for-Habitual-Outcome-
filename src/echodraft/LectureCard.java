package echodraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class LectureCard extends JPanel {

    private static final int CARD_RADIUS = 20;
    private static final Font TITLE_FONT = new Font("Impact", Font.BOLD, 20);
    private static final Font META_FONT = new Font("SansSerif", Font.PLAIN, 13);

    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel dateLabel;
    private boolean isHovered = false;

    private LectureData lectureData;
    private ClassroomData classroomData;
    private TeacherData teacherData;
    private MainFrame mainFrame;

    public LectureCard(LectureData lectureData, ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
        this.lectureData = lectureData;
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;

        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(220, 160));
        setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        titleLabel = new JLabel(lectureData.getLectureTitle());
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        authorLabel = new JLabel("By: " + lectureData.getAuthorName());
        authorLabel.setFont(META_FONT);
        authorLabel.setForeground(Color.WHITE);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateLabel = new JLabel("Created: " + lectureData.getCreatedAt().toLocalDate().toString());
        dateLabel.setFont(META_FONT);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(Box.createVerticalGlue());
        content.add(titleLabel);
        content.add(Box.createRigidArea(new Dimension(0, 8)));
        content.add(authorLabel);
        content.add(Box.createRigidArea(new Dimension(0, 6)));
        content.add(dateLabel);
        content.add(Box.createVerticalGlue());

        add(content, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setCursor(Cursor.getDefaultCursor());
                repaint();
            }

            public void mouseClicked(MouseEvent e) {
                mainFrame.actualLecture(classroomData, teacherData, mainFrame, lectureData);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color cardColor = ColorUtils.getColorFromName(lectureData.getColorName());
        g2.setColor(cardColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), CARD_RADIUS, CARD_RADIUS);

        if (isHovered) {
            g2.setColor(Color.decode("#EDE7E3"));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, CARD_RADIUS, CARD_RADIUS);
        }

        g2.dispose();
    }
}

class AddLecture extends JPanel implements ActionListener {

    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel toppanel = new JPanel();
    JPanel previewPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JLabel titleLbl = new JLabel("Add Lectures");
    JLabel lectureTitleLbl = new JLabel("Lecture Title: ");
    JTextField titleTxt = new JTextField();
    JLabel colorLbl = new JLabel("Select Color: ");
    String[] colors = {"Blue", "Cyan", "Green", "Orange", "Red", "Purple", "Gold", "Pink"};
    JComboBox colorCbox = new JComboBox<>(colors);
    JPanel dividerPanel = new JPanel();
    JButton addPageBttn = new JButton("Add Page");
    JButton removePageBttn = new JButton("Remove Page");
    JButton backBttn = new JButton("Back");
    JButton nextBttn = new JButton("Next");
    JButton cancelBttn = new JButton("Cancel");
    JButton saveBttn = new JButton("Save");
    JTextArea slideTextArea = new JTextArea("Paste text here...");
    private JLabel pageNumLbl;

    private LectureData lectureData;
    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private Image backgroundImage;

    public AddLecture(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, LectureData lectureData) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        this.lectureData = lectureData;
        if (lectureData.getSlideCount() == 0) {
            lectureData.addSlide("");
        }

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

        centerPanel.add(titleLbl);
        titleLbl.setForeground(Color.decode("#EDE7E3"));
        titleLbl.setFont(new Font("Impact", Font.PLAIN, 40));
        titleLbl.setBounds(30, 10, 250, 50);

        centerPanel.add(sidePanel);
        sidePanel.setLayout(null);
        sidePanel.setBackground(Color.decode("#BA66A4"));
        sidePanel.setBounds(30, 65, 500, 640);

        sidePanel.add(lectureTitleLbl);
        lectureTitleLbl.setForeground(Color.decode("#EDE7E3"));
        lectureTitleLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        lectureTitleLbl.setBounds(40, 20, 150, 30);

        sidePanel.add(titleTxt);
        titleTxt.setBackground(Color.decode("#FAF0E6"));
        titleTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        titleTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        titleTxt.setBounds(40, 55, 420, 40);

        sidePanel.add(colorLbl);
        colorLbl.setForeground(Color.decode("#EDE7E3"));
        colorLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        colorLbl.setBounds(40, 110, 150, 30);

        sidePanel.add(colorCbox);
        colorCbox.setBackground(Color.decode("#FAF0E6"));
        colorCbox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        colorCbox.setFont(new Font("Impact", Font.PLAIN, 20));
        colorCbox.setBounds(40, 145, 420, 40);
        colorCbox.addActionListener(this);

        sidePanel.add(dividerPanel);
        dividerPanel.setBackground(Color.decode("#EDE7E3"));
        dividerPanel.setBounds(40, 205, 420, 3);

        sidePanel.add(addPageBttn);
        addPageBttn.setBackground(Color.decode("#C5AF80"));
        addPageBttn.setForeground(Color.decode("#EDE7E3"));
        addPageBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        addPageBttn.setFocusable(false);
        addPageBttn.setBorderPainted(false);
        addPageBttn.addActionListener(this);
        addPageBttn.setBounds(80, 230, 330, 50);

        sidePanel.add(removePageBttn);
        removePageBttn.setBackground(Color.decode("#C5AF80"));
        removePageBttn.setForeground(Color.decode("#EDE7E3"));
        removePageBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        removePageBttn.setFocusable(false);
        removePageBttn.setBorderPainted(false);
        removePageBttn.addActionListener(this);
        removePageBttn.setBounds(80, 300, 330, 50);

        sidePanel.add(backBttn);
        backBttn.setBackground(Color.decode("#BC9E5C"));
        backBttn.setForeground(Color.decode("#EDE7E3"));
        backBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        backBttn.setFocusable(false);
        backBttn.setBorderPainted(false);
        backBttn.addActionListener(this);
        backBttn.setBounds(80, 370, 150, 50);

        sidePanel.add(nextBttn);
        nextBttn.setBackground(Color.decode("#BC9E5C"));
        nextBttn.setForeground(Color.decode("#EDE7E3"));
        nextBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        nextBttn.setFocusable(false);
        nextBttn.setBorderPainted(false);
        nextBttn.addActionListener(this);
        nextBttn.setBounds(260, 370, 150, 50);

        sidePanel.add(saveBttn);
        saveBttn.setBackground(Color.decode("#FFC343"));
        saveBttn.setForeground(Color.decode("#EDE7E3"));
        saveBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        saveBttn.setFocusable(false);
        saveBttn.setBorderPainted(false);
        saveBttn.addActionListener(this);
        saveBttn.setBounds(320, 550, 130, 50);

        sidePanel.add(cancelBttn);
        cancelBttn.setBackground(Color.decode("#D62246"));
        cancelBttn.setForeground(Color.decode("#EDE7E3"));
        cancelBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        cancelBttn.setFocusable(false);
        cancelBttn.setBorderPainted(false);
        cancelBttn.addActionListener(this);
        cancelBttn.setBounds(210, 560, 100, 40);

        centerPanel.add(previewPanel);
        previewPanel.setBackground(Color.decode("#C5AF80"));
        previewPanel.setBounds(580, 65, 900, 640);

        previewPanel.add(slideTextArea);
        previewPanel.setLayout(null);
        slideTextArea.setBackground(Color.decode("#D9D9D9"));
        slideTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        slideTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        slideTextArea.setBounds(30, 30, 840, 560);
        slideTextArea.setForeground(Color.GRAY);
        slideTextArea.setLineWrap(true);
        slideTextArea.setWrapStyleWord(true);

        JScrollPane questionScrollPane = new JScrollPane(slideTextArea);
        questionScrollPane.setBounds(30, 30, 840, 560);
        questionScrollPane.setOpaque(false);
        questionScrollPane.getViewport().setOpaque(false);
        questionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        previewPanel.add(questionScrollPane);

        slideTextArea.addFocusListener(
                new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                if (slideTextArea.getText().equals("Paste text here...")) {
                    slideTextArea.setText("");
                    slideTextArea.setForeground(Color.BLACK);
                }
            }
        }
        );

        pageNumLbl = new JLabel("Page: 1/1");
        previewPanel.add(pageNumLbl);
        pageNumLbl.setForeground(Color.decode("#4B1D3F"));
        pageNumLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        pageNumLbl.setBounds(30, 600, 100, 30);

    }

    private void updatePageLabel() {
        pageNumLbl.setText("Page: " + (lectureData.getCurrentSlideIndex() + 1) + "/" + lectureData.getSlideCount());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to EXIT lecture Creation?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
            }
        }

        if (e.getSource() == addPageBttn) {
            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            lectureData.updateCurrentSlide(content);
            lectureData.addSlide("");
            slideTextArea.setText(lectureData.getCurrentSlide());
            updatePageLabel();
        }

        if (e.getSource() == nextBttn) {
            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            lectureData.updateCurrentSlide(slideTextArea.getText().trim());
            if (lectureData.hasNextSlide()) {
                lectureData.nextSlide();
            }
            slideTextArea.setText(lectureData.getCurrentSlide());
            updatePageLabel();
        }

        if (e.getSource() == backBttn) {
            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            lectureData.updateCurrentSlide(slideTextArea.getText().trim());
            if (lectureData.hasPreviousSlide()) {
                lectureData.previousSlide();
            }
            slideTextArea.setText(lectureData.getCurrentSlide());
            updatePageLabel();
        }

        if (e.getSource() == removePageBttn) {
            int totalSlides = lectureData.getSlideCount();
            if (totalSlides == 0) {
                JOptionPane.showMessageDialog(this, "No pages to remove.");
                return;
            }

            if (totalSlides == 1) {
                JOptionPane.showMessageDialog(this, "At least one slide must remain.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this slide?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            int indexToRemove = lectureData.getCurrentSlideIndex();

            lectureData.updateCurrentSlide(slideTextArea.getText().trim());
            lectureData.removeSlide(indexToRemove);

            if (lectureData.getSlideCount() == 0) {
                slideTextArea.setText("");
            } else {
                int newIndex = Math.min(indexToRemove, lectureData.getSlideCount() - 1);
                lectureData.setCurrentSlide(newIndex);
                slideTextArea.setText(lectureData.getCurrentSlide());
            }

            updatePageLabel();

        }

        if (e.getSource() == saveBttn) {
            String title = titleTxt.getText().trim();
            String color = colorCbox.getSelectedItem().toString();
            String author = teacherData.getFullName();

            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a lecture title.");
                return;
            }

            if (title.isBlank()) {
                JOptionPane.showMessageDialog(this, "Lecture title cannot be blank.");
                return;
            }
            if (title.length() > 100) {
                JOptionPane.showMessageDialog(this, "Lecture title must not exceed 100 characters.");
                return;
            }
            for (LectureData ld : LectureDatabase.getLecturesByClassroom(classroomData.getClassroomID())) {
                if (ld.getLectureTitle().equalsIgnoreCase(title)) {
                    JOptionPane.showMessageDialog(this, "A lecture with this title already exists in this classroom.");
                    return;
                }
            }

            lectureData.updateCurrentSlide(slideTextArea.getText().trim());
            lectureData.setMetadata(title, author, color);

            if (lectureData.getClassroomID() != classroomData.getClassroomID()) {
                LectureData original = lectureData; // preserve original slides

                lectureData = new LectureData(
                        title,
                        author,
                        original.getCreatedAt(),
                        color,
                        classroomData.getClassroomID()
                );

                for (String slide : original.getAllSlides()) {
                    lectureData.addSlide(slide);
                }

                lectureData.setCurrentSlide(original.getCurrentSlideIndex());
            }

            LectureDatabase.addLecture(lectureData);
            JOptionPane.showMessageDialog(this, "Lecture saved successfully!");
            mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);

        }

        if (e.getSource() == colorCbox) {
            String color = (String) colorCbox.getSelectedItem();

            previewPanel.setBackground(ColorUtils.getColorFromName(color));
        }
    }
}

class EditLecture extends JPanel implements ActionListener {

    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel toppanel = new JPanel();
    JPanel previewPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JLabel titleLbl = new JLabel("Update Lecture");
    JLabel selectLbl = new JLabel("Select Lecture");
    JLabel lectureTitleLbl = new JLabel("Lecture Title: ");
    JTextField titleTxt = new JTextField();
    JLabel colorLbl = new JLabel("Select Color: ");
    String[] colors = {"Blue", "Cyan", "Green", "Orange", "Red", "Purple", "Gold", "Pink"};
    JComboBox colorCbox = new JComboBox<>(colors);
    JPanel dividerPanel = new JPanel();
    JButton addPageBttn = new JButton("Add Page");
    JButton removePageBttn = new JButton("Remove Page");
    JButton backBttn = new JButton("Back");
    JButton nextBttn = new JButton("Next");
    JButton cancelBttn = new JButton("Cancel");
    JButton saveBttn = new JButton("Save");
    JTextArea slideTextArea = new JTextArea("Paste text here...");
    JComboBox<LectureData> lectureSelector;
    private JLabel pageNumLbl;

    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private LectureData lectureData;
    private Image backgroundImage;

    public EditLecture(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, LectureData lectureData) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        this.lectureData = lectureData;

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

        centerPanel.add(titleLbl);
        titleLbl.setForeground(Color.decode("#EDE7E3"));
        titleLbl.setFont(new Font("Impact", Font.PLAIN, 40));
        titleLbl.setBounds(30, 10, 250, 50);

        centerPanel.add(sidePanel);
        sidePanel.setLayout(null);
        sidePanel.setBackground(Color.decode("#BA66A4"));
        sidePanel.setBounds(30, 65, 500, 640);

        sidePanel.add(selectLbl);
        selectLbl.setForeground(Color.decode("#EDE7E3"));
        selectLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        selectLbl.setBounds(40, 20, 150, 30);

        lectureSelector = new JComboBox<>();
        sidePanel.add(lectureSelector);
        lectureSelector.setBackground(Color.decode("#FAF0E6"));
        lectureSelector.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        lectureSelector.setFont(new Font("Impact", Font.PLAIN, 20));
        lectureSelector.setBounds(40, 60, 420, 40);
        lectureSelector.addActionListener(this);

        java.util.List<LectureData> lectures = LectureDatabase.getLecturesByClassroom(classroomData.getClassroomID());
        for (LectureData lecture : lectures) {
            lectureSelector.addItem(lecture);
        }

        lectureSelector.setSelectedIndex(-1);

        sidePanel.add(lectureTitleLbl);
        lectureTitleLbl.setForeground(Color.decode("#EDE7E3"));
        lectureTitleLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        lectureTitleLbl.setBounds(40, 120, 150, 30);

        sidePanel.add(titleTxt);
        titleTxt.setBackground(Color.decode("#FAF0E6"));
        titleTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        titleTxt.setFont(new Font("Impact", Font.PLAIN, 20));
        titleTxt.setBounds(40, 155, 420, 40);

        sidePanel.add(colorLbl);
        colorLbl.setForeground(Color.decode("#EDE7E3"));
        colorLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        colorLbl.setBounds(40, 210, 150, 30);

        sidePanel.add(colorCbox);
        colorCbox.setBackground(Color.decode("#FAF0E6"));
        colorCbox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        colorCbox.setFont(new Font("Impact", Font.PLAIN, 20));
        colorCbox.setBounds(40, 245, 420, 40);
        colorCbox.addActionListener(this);

        sidePanel.add(dividerPanel);
        dividerPanel.setBackground(Color.decode("#EDE7E3"));
        dividerPanel.setBounds(40, 305, 420, 3);

        sidePanel.add(addPageBttn);
        addPageBttn.setBackground(Color.decode("#C5AF80"));
        addPageBttn.setForeground(Color.decode("#EDE7E3"));
        addPageBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        addPageBttn.setFocusable(false);
        addPageBttn.setBorderPainted(false);
        addPageBttn.addActionListener(this);
        addPageBttn.setBounds(80, 330, 330, 50);

        sidePanel.add(removePageBttn);
        removePageBttn.setBackground(Color.decode("#C5AF80"));
        removePageBttn.setForeground(Color.decode("#EDE7E3"));
        removePageBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        removePageBttn.setFocusable(false);
        removePageBttn.setBorderPainted(false);
        removePageBttn.addActionListener(this);
        removePageBttn.setBounds(80, 400, 330, 50);

        sidePanel.add(backBttn);
        backBttn.setBackground(Color.decode("#BC9E5C"));
        backBttn.setForeground(Color.decode("#EDE7E3"));
        backBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        backBttn.setFocusable(false);
        backBttn.setBorderPainted(false);
        backBttn.addActionListener(this);
        backBttn.setBounds(80, 470, 150, 50);

        sidePanel.add(nextBttn);
        nextBttn.setBackground(Color.decode("#BC9E5C"));
        nextBttn.setForeground(Color.decode("#EDE7E3"));
        nextBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        nextBttn.setFocusable(false);
        nextBttn.setBorderPainted(false);
        nextBttn.addActionListener(this);
        nextBttn.setBounds(260, 470, 150, 50);

        sidePanel.add(saveBttn);
        saveBttn.setBackground(Color.decode("#FFC343"));
        saveBttn.setForeground(Color.decode("#EDE7E3"));
        saveBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        saveBttn.setFocusable(false);
        saveBttn.setBorderPainted(false);
        saveBttn.addActionListener(this);
        saveBttn.setBounds(320, 550, 130, 50);

        sidePanel.add(cancelBttn);
        cancelBttn.setBackground(Color.decode("#D62246"));
        cancelBttn.setForeground(Color.decode("#EDE7E3"));
        cancelBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        cancelBttn.setFocusable(false);
        cancelBttn.setBorderPainted(false);
        cancelBttn.addActionListener(this);
        cancelBttn.setBounds(210, 560, 100, 40);

        centerPanel.add(previewPanel);
        previewPanel.setBackground(Color.decode("#C5AF80"));
        previewPanel.setBounds(580, 65, 900, 640);

        previewPanel.add(slideTextArea);
        previewPanel.setLayout(null);
        slideTextArea.setBackground(Color.decode("#D9D9D9"));
        slideTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        slideTextArea.setFont(new Font("COnstantia", Font.PLAIN, 40));
        slideTextArea.setBounds(30, 30, 840, 560);
        slideTextArea.setLineWrap(true);
        slideTextArea.setWrapStyleWord(true);

        JScrollPane questionScrollPane = new JScrollPane(slideTextArea);
        questionScrollPane.setBounds(30, 30, 840, 560);
        questionScrollPane.setOpaque(false);
        questionScrollPane.getViewport().setOpaque(false);
        questionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        previewPanel.add(questionScrollPane);

        pageNumLbl = new JLabel("Page: 1/1");
        previewPanel.add(pageNumLbl);
        pageNumLbl.setForeground(Color.decode("#4B1D3F"));
        pageNumLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        pageNumLbl.setBounds(30, 600, 100, 30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cancelBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to cancel updating this lecture?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
            }
        }

        if (e.getSource() == backBttn) {
            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            if (lectureData.getCurrentSlideIndex() > 0) {
                lectureData.previousSlide();
                slideTextArea.setText(lectureData.getCurrentSlide());
                updatePageLabel();
            }
        }

        if (e.getSource() == nextBttn) {
            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            if (lectureData.getCurrentSlideIndex() < lectureData.getSlideCount() - 1) {
                lectureData.nextSlide();
                slideTextArea.setText(lectureData.getCurrentSlide());
                updatePageLabel();

            } else {
                JOptionPane.showMessageDialog(this, "You're on the last page.");
            }
        }

        if (e.getSource() == addPageBttn) {
            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            lectureData.updateCurrentSlide(slideTextArea.getText());
            int newIndex = lectureData.addSlide("");
            lectureData.setCurrentSlide(newIndex);
            slideTextArea.setText(lectureData.getCurrentSlide());
            updatePageLabel();
        }

        if (e.getSource() == removePageBttn) {
            int totalSlides = lectureData.getSlideCount();
            int currentIndex = lectureData.getCurrentSlideIndex();

            if (totalSlides <= 1) {
                JOptionPane.showMessageDialog(this, "At least one slide must remain.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete the current slide?",
                    "Confirm Slide Removal",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            lectureData.updateCurrentSlide(slideTextArea.getText().trim());
            lectureData.removeSlide(currentIndex);

            int newIndex = Math.min(currentIndex, lectureData.getSlideCount() - 1);
            lectureData.setCurrentSlide(newIndex);
            slideTextArea.setText(lectureData.getCurrentSlide());
            updatePageLabel();

        }

        if (e.getSource() == saveBttn) {
            String content = slideTextArea.getText().trim();
            if (content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slide content cannot be empty.");
                return;
            }

            lectureData.updateCurrentSlide(slideTextArea.getText());
            String newTitle = titleTxt.getText().trim();
            String newColor = (String) colorCbox.getSelectedItem();

            if (newTitle.isBlank()) {
                JOptionPane.showMessageDialog(this, "Lecture title cannot be blank.");
                return;
            }
            if (newTitle.length() > 100) {
                JOptionPane.showMessageDialog(this, "Lecture title must not exceed 100 characters.");
                return;
            }

            if (newTitle.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lecture title cannot be empty.");
                return;
            }

            lectureData.setMetadata(newTitle, teacherData.getFullName(), newColor);
            JOptionPane.showMessageDialog(this, "Lecture updated successfully!");
            mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == lectureSelector) {
            if (lectureSelector.getSelectedItem() == null) {
                return;
            }
            LectureData selectedLecture = (LectureData) lectureSelector.getSelectedItem();
            if (selectedLecture != null) {
                LectureData previousLecture = this.lectureData;
                if (previousLecture != null) {
                    previousLecture.updateCurrentSlide(slideTextArea.getText().trim());
                }

                this.lectureData = selectedLecture;

                if (lectureData.getSlideCount() == 0) {
                    lectureData.addSlide("");
                }

                lectureData.setCurrentSlide(0);

                slideTextArea.setText(lectureData.getCurrentSlide());
                titleTxt.setText(lectureData.getLectureTitle());
                colorCbox.setSelectedItem(lectureData.getColorName());
                previewPanel.setBackground(ColorUtils.getColorFromName(lectureData.getColorName()));
                updatePageLabel();

                revalidate();
                repaint();
            }
        }

        if (e.getSource() == colorCbox) {
            String color = (String) colorCbox.getSelectedItem();

            previewPanel.setBackground(ColorUtils.getColorFromName(color));
        }

    }

    private void updatePageLabel() {
        if (pageNumLbl != null && lectureData != null) {
            int current = lectureData.getCurrentSlideIndex() + 1;
            int total = lectureData.getSlideCount();
            pageNumLbl.setText("Page: " + current + "/" + (total == 0 ? 1 : total));
        }
    }

}

class RemoveLecture extends JFrame implements ActionListener {

    private JComboBox<LectureData> lectureSelector;
    private JButton deleteBttn = new JButton("Delete");
    private JButton cancelBttn = new JButton("Cancel");
    private ClassroomData classroomData;
    private TeacherData teacherData;
    private MainFrame mainFrame;

    public RemoveLecture(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        setTitle("Remove Lecture");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel selectLbl = new JLabel("Select Lecture to Delete:");
        selectLbl.setFont(new Font("Arial", Font.BOLD, 16));
        selectLbl.setBounds(30, 20, 300, 30);
        add(selectLbl);

        lectureSelector = new JComboBox<>();
        lectureSelector.setBounds(30, 60, 320, 30);
        add(lectureSelector);

        List<LectureData> lectures = LectureDatabase.getLecturesByClassroom(classroomData.getClassroomID());
        for (LectureData lecture : lectures) {
            lectureSelector.addItem(lecture);
        }

        deleteBttn.setBounds(60, 110, 100, 30);
        deleteBttn.setBackground(Color.decode("#D62246"));
        deleteBttn.setForeground(Color.WHITE);
        deleteBttn.setFocusable(false);
        deleteBttn.addActionListener(this);
        add(deleteBttn);

        cancelBttn.setBounds(200, 110, 100, 30);
        cancelBttn.setBackground(Color.GRAY);
        cancelBttn.setForeground(Color.WHITE);
        cancelBttn.setFocusable(false);
        cancelBttn.addActionListener(this);
        add(cancelBttn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteBttn) {
            LectureData selected = (LectureData) lectureSelector.getSelectedItem();
            if (selected != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete \"" + selected.getLectureTitle() + "\"?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    LectureDatabase.lectureList.remove(selected);
                    JOptionPane.showMessageDialog(this, "Lecture deleted successfully.");
                    mainFrame.showSpecificDashboardLectures(classroomData, teacherData, mainFrame);;
                    dispose();

                }
            }
        }

        if (e.getSource() == cancelBttn) {
            dispose();
        }
    }
}
