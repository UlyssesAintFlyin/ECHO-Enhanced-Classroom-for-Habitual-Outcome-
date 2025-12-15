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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class FlashcardDisplay extends JPanel {

    private static final int CARD_RADIUS = 20;
    private static final Font TITLE_FONT = new Font("Impact", Font.BOLD, 20);
    private static final Font META_FONT = new Font("SansSerif", Font.PLAIN, 13);

    private JLabel titleLabel;
    private JLabel countLabel;
    private JLabel authorLabel;
    private JLabel dateLabel;
    private boolean isHovered = false;

    private FlashcardSet flashcardSet;
    private ClassroomData classroomData;
    private TeacherData teacherData;
    private MainFrame mainFrame;

    public FlashcardDisplay(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardSet flashcardSet) {
        this.flashcardSet = flashcardSet;
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

        Color textColor = ColorUtils.getColorFromName(flashcardSet.getColorName());

        titleLabel = new JLabel(flashcardSet.getTitle());
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(textColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        countLabel = new JLabel(flashcardSet.getCards().size() + " flashcards");
        countLabel.setFont(META_FONT);
        countLabel.setForeground(textColor);
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        authorLabel = new JLabel("By: " + teacherData.getFullName());
        authorLabel.setFont(META_FONT);
        authorLabel.setForeground(textColor);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String dateText = (flashcardSet.getCreationDate() != null)
                ? flashcardSet.getCreationDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                : "Unknown";

        dateLabel = new JLabel("Created: " + dateText);
        dateLabel.setFont(META_FONT);
        dateLabel.setForeground(textColor);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(Box.createVerticalGlue());
        content.add(titleLabel);
        content.add(Box.createRigidArea(new Dimension(0, 8)));
        content.add(countLabel);
        content.add(Box.createRigidArea(new Dimension(0, 6)));
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
                mainFrame.actualFlashcard(classroomData, teacherData, mainFrame, flashcardSet);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color cardColor = Color.WHITE;
        g2.setColor(cardColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), CARD_RADIUS, CARD_RADIUS);

        Color borderColor = ColorUtils.getColorFromName(flashcardSet.getColorName());
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, CARD_RADIUS, CARD_RADIUS);

        if (isHovered) {
            g2.setColor(Color.decode("#FFEE8C")); 
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, CARD_RADIUS, CARD_RADIUS);
        }

        g2.dispose();
    }
}

class AddFlashcard extends JPanel implements ActionListener {

    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel toppanel = new JPanel();
    JPanel previewPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JLabel titleLbl = new JLabel("Add Flashcard");
    JLabel flaschcardTitleLbl = new JLabel("Flashcard Title: ");
    JTextField titleTxt = new JTextField();
    JLabel colorLbl = new JLabel("Select Color: ");
    String[] colors = {"Blue", "Cyan", "Green", "Orange", "Red", "Purple", "Gold", "Pink"};
    JComboBox colorCbox = new JComboBox<>(colors);
    JPanel dividerPanel = new JPanel();
    JButton addBttn = new JButton("Add Question");
    JButton removeBttn = new JButton("Remove Question");
    JButton backBttn = new JButton("Back");
    JButton nextBttn = new JButton("Next");
    JButton cancelBttn = new JButton("Cancel");
    JButton saveBttn = new JButton("Save");
    JLabel questionLbl = new JLabel("Insert Question");
    JTextArea questionTextArea = new JTextArea("Paste text here...");
    JLabel answerLbl = new JLabel("Insert Answer");
    JTextField answerTxt = new JTextField();
    private JLabel questionNumLbl;

    private FlashcardData flashcardData;
    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private Image backgroundImage;

    private FlashcardSet flashcardSet;
    private int currentPageIndex = 0;

    public AddFlashcard(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardData flashcardData) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        this.flashcardData = flashcardData;

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
        titleLbl.setBounds(30, 10, 380, 50);

        centerPanel.add(sidePanel);
        sidePanel.setLayout(null);
        sidePanel.setBackground(Color.decode("#722F60"));
        sidePanel.setBounds(30, 65, 500, 640);

        sidePanel.add(flaschcardTitleLbl);
        flaschcardTitleLbl.setForeground(Color.decode("#EDE7E3"));
        flaschcardTitleLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        flaschcardTitleLbl.setBounds(40, 20, 380, 30);

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

        sidePanel.add(addBttn);
        addBttn.setBackground(Color.decode("#C5AF80"));
        addBttn.setForeground(Color.decode("#EDE7E3"));
        addBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        addBttn.setFocusable(false);
        addBttn.setBorderPainted(false);
        addBttn.addActionListener(this);
        addBttn.setBounds(80, 230, 330, 50);

        sidePanel.add(removeBttn);
        removeBttn.setBackground(Color.decode("#C5AF80"));
        removeBttn.setForeground(Color.decode("#EDE7E3"));
        removeBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        removeBttn.setFocusable(false);
        removeBttn.setBorderPainted(false);
        removeBttn.addActionListener(this);
        removeBttn.setBounds(80, 300, 330, 50);

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
        previewPanel.setBackground(Color.decode("#CAB1C3"));
        previewPanel.setBounds(580, 65, 900, 640);
        previewPanel.setLayout(null);

        previewPanel.add(questionLbl);
        questionLbl.setForeground(Color.decode("#4B1D3F"));
        questionLbl.setFont(new Font("Impact", Font.PLAIN, 23));
        questionLbl.setBounds(30, 20, 250, 30);

        previewPanel.add(questionTextArea);
        questionTextArea.setBackground(Color.decode("#D9D9D9"));
        questionTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        questionTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 80));
        questionTextArea.setBounds(30, 50, 840, 440);
        questionTextArea.setForeground(Color.BLACK);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);

        JScrollPane questionScrollPane = new JScrollPane(questionTextArea);
        questionScrollPane.setBounds(30, 50, 840, 440);
        questionScrollPane.setOpaque(false);
        questionScrollPane.getViewport().setOpaque(false);
        questionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        previewPanel.add(questionScrollPane);

        questionTextArea.addFocusListener(
                new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                if (questionTextArea.getText().equals("Paste text here...")) {
                    questionTextArea.setText("");
                    questionTextArea.setForeground(Color.BLACK);
                }
            }
        }
        );

        previewPanel.add(answerLbl);
        answerLbl.setForeground(Color.decode("#4B1D3F"));
        answerLbl.setFont(new Font("Impact", Font.PLAIN, 15));
        answerLbl.setBounds(30, 500, 250, 30);

        previewPanel.add(answerTxt);
        answerTxt.setBackground(Color.decode("#EDE7E3"));
        answerTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        answerTxt.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        answerTxt.setBounds(30, 530, 300, 40);
        answerTxt.setForeground(Color.BLACK);

        questionNumLbl = new JLabel("Question: 1/1");
        previewPanel.add(questionNumLbl);
        questionNumLbl.setForeground(Color.decode("#4B1D3F"));
        questionNumLbl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        questionNumLbl.setBounds(30, 590, 250, 30);

        flashcardSet = new FlashcardSet(
                titleTxt.getText().trim(),
                (String) colorCbox.getSelectedItem(),
                classroomData.getClassroomID(),
                LocalDateTime.now()
        );

        if (flashcardSet.getCards().isEmpty()) {
            flashcardSet.addCard("", "");
            currentPageIndex = 0;
            updatePageDisplay();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to EXIT Flashcard creation?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
            }
        }

        if (e.getSource() == addBttn) {
            String question = questionTextArea.getText().trim();
            String answer = answerTxt.getText().trim();

            if (question.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both question and answer before adding.");
                return;
            }

            FlashcardData newCard = new FlashcardData(
                    flashcardSet.getTitle(),
                    question,
                    answer,
                    LocalDateTime.now(),
                    flashcardSet.getColorName(),
                    flashcardSet.getClassroomID()
            );

            if (flashcardSet.getCards().isEmpty()) {
                flashcardSet.getCards().add(newCard);
            } else if (currentPageIndex >= 0 && currentPageIndex < flashcardSet.getCards().size()) {
                flashcardSet.getCards().set(currentPageIndex, newCard);
            }

            flashcardSet.addCard("", "");
            currentPageIndex = flashcardSet.getCards().size() - 1;

            questionTextArea.setText("");
            answerTxt.setText("");
            questionNumLbl.setText("Question: " + (currentPageIndex + 1) + "/" + flashcardSet.getCards().size());
            questionTextArea.requestFocus();

        }

        if (e.getSource() == removeBttn) {
            if (flashcardSet.getCards().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No pages to remove.");
                return;
            }

            if (flashcardSet.getCards().size() == 1) {
                JOptionPane.showMessageDialog(this, "Cannot remove the only page. At least one flashcard must remain.");
                return;
            }

            if (!saveCurrentPage()) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this flashcard?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            flashcardSet.getCards().remove(currentPageIndex);
            currentPageIndex = Math.max(0, currentPageIndex - 1);
            updatePageDisplay();
        }

        if (e.getSource() == nextBttn) {
            if (!saveCurrentPage()) {
                return;
            }

            if (currentPageIndex < flashcardSet.getCards().size() - 1) {
                currentPageIndex++;
                updatePageDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "You're on the last page.");
            }
        }

        if (e.getSource() == backBttn) {
            if (!saveCurrentPage()) {
                return;
            }

            if (currentPageIndex > 0) {
                currentPageIndex--;
                updatePageDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "You're on the first page.");
            }
        }


        if (e.getSource() == nextBttn) {
            if (!saveCurrentPage()) {
                return;
            }

            if (currentPageIndex < flashcardSet.getCards().size() - 1) {
                currentPageIndex++;
                updatePageDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "You're on the last page.");
            }
        }

        if (e.getSource() == backBttn) {
            if (!saveCurrentPage()) {
                return;
            }

            if (currentPageIndex > 0) {
                currentPageIndex--;
                updatePageDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "You're on the first page.");
            }
        }

        if (e.getSource() == saveBttn) {
            if (flashcardSet.getCards().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No flashcards to save.");
                return;
            }

            String title = titleTxt.getText().trim();
            String color = (String) colorCbox.getSelectedItem();

            if (title.isBlank()) {
                JOptionPane.showMessageDialog(this, "Flashcard title cannot be blank.");
                return;
            }
            if (title.length() > 100) {
                JOptionPane.showMessageDialog(this, "Flashcard title must not exceed 100 characters.");
                return;
            }
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Flashcard title cannot be empty.");
                return;
            }
            if (color == null || color.isBlank()) {
                JOptionPane.showMessageDialog(this, "Please select a color.");
                return;
            }

            List<String> existingTitles = FlashcardDatabase.getDistinctTitlesByClassroom(classroomData.getClassroomID());
            if (existingTitles.contains(title)) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "A flashcard set with this title already exists. Overwrite?",
                        "Confirm Overwrite",
                        JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            if (!saveCurrentPage()) {
                return;
            }

            flashcardSet.setTitle(title);
            flashcardSet.setColorName(color);
            flashcardSet.saveToDatabase();

            JOptionPane.showMessageDialog(this, flashcardSet.getCards().size() + " flashcard(s) saved successfully!");
            mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
        }

        if (e.getSource() == colorCbox) {
            String color = (String) colorCbox.getSelectedItem();

            previewPanel.setBackground(ColorUtils.getColorFromName(color));
        }

    }

    private boolean saveCurrentPage() {
        String question = questionTextArea.getText().trim();
        String answer = answerTxt.getText().trim();

        if (question.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Give this flashcard some content first!");
            return false;
        }

        flashcardSet.getCards().set(currentPageIndex, new FlashcardData(
                flashcardSet.getTitle(),
                question,
                answer,
                LocalDateTime.now(),
                flashcardSet.getColorName(),
                flashcardSet.getClassroomID()
        ));
        return true;
    }


    private void updatePageDisplay() {
        if (flashcardSet == null || flashcardSet.getCards().isEmpty()) {
            questionTextArea.setText("Paste text here...");
            answerTxt.setText("");
            questionNumLbl.setText("Question: 0/0");
            return;
        }

        FlashcardData current = flashcardSet.getCards().get(currentPageIndex);
        questionTextArea.setText(current.getQuestionContent());
        answerTxt.setText(current.getAnswerContent());
        questionNumLbl.setText("Question: " + (currentPageIndex + 1) + "/" + flashcardSet.getCards().size());
    }

}

class EditFlashcard extends JPanel implements ActionListener {

    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel toppanel = new JPanel();
    JPanel previewPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JLabel titleLbl = new JLabel("Edit Flashcard");
    JLabel flaschcardTitleLbl = new JLabel("Flashcard Title: ");
    JTextField titleTxt = new JTextField();
    JLabel colorLbl = new JLabel("Select Color: ");
    String[] colors = {"Blue", "Cyan", "Green", "Orange", "Red", "Purple", "Gold", "Pink"};
    JComboBox colorCbox = new JComboBox<>(colors);
    JPanel dividerPanel = new JPanel();
    JButton addBttn = new JButton("Add Question");
    JButton removeBttn = new JButton("Remove Question");
    JButton backBttn = new JButton("Back");
    JButton nextBttn = new JButton("Next");
    JButton cancelBttn = new JButton("Cancel");
    JButton saveBttn = new JButton("Save");
    JLabel questionLbl = new JLabel("Insert Question");
    JTextArea questionTextArea = new JTextArea("Paste text here...");
    JLabel answerLbl = new JLabel("Insert Answer");
    JTextField answerTxt = new JTextField();
    private JLabel questionNumLbl;
    JLabel selectLbl = new JLabel("Select Flashcard");
    JComboBox<String> flashcardSelector;

    private FlashcardData flashcardData;
    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private Image backgroundImage;

    private FlashcardSet flashcardSet;
    private int currentPageIndex = 0;

    public EditFlashcard(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardData flashcardData) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        this.flashcardData = flashcardData;

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
        titleLbl.setBounds(30, 10, 380, 50);

        questionNumLbl = new JLabel("Question: 1/1");
        previewPanel.add(questionNumLbl);
        questionNumLbl.setForeground(Color.decode("#4B1D3F"));
        questionNumLbl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        questionNumLbl.setBounds(30, 590, 250, 30);

        centerPanel.add(sidePanel);
        sidePanel.setLayout(null);
        sidePanel.setBackground(Color.decode("#722F60"));
        sidePanel.setBounds(30, 65, 500, 640);

        sidePanel.add(selectLbl);
        selectLbl.setForeground(Color.decode("#EDE7E3"));
        selectLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        selectLbl.setBounds(40, 20, 380, 30);

        flashcardSelector = new JComboBox<>();
        sidePanel.add(flashcardSelector);
        flashcardSelector.setBackground(Color.decode("#FAF0E6"));
        flashcardSelector.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        flashcardSelector.setFont(new Font("Impact", Font.PLAIN, 20));
        flashcardSelector.setBounds(40, 60, 420, 40);
        flashcardSelector.addActionListener(this);

        List<String> titles = FlashcardDatabase.getDistinctTitlesByClassroom(classroomData.getClassroomID());
        for (String title : titles) {
            flashcardSelector.addItem(title);
        }

        sidePanel.add(flaschcardTitleLbl);
        flaschcardTitleLbl.setForeground(Color.decode("#EDE7E3"));
        flaschcardTitleLbl.setFont(new Font("Impact", Font.PLAIN, 25));
        flaschcardTitleLbl.setBounds(40, 120, 380, 30);

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

        sidePanel.add(addBttn);
        addBttn.setBackground(Color.decode("#C5AF80"));
        addBttn.setForeground(Color.decode("#EDE7E3"));
        addBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        addBttn.setFocusable(false);
        addBttn.setBorderPainted(false);
        addBttn.addActionListener(this);
        addBttn.setBounds(80, 330, 330, 50);

        sidePanel.add(removeBttn);
        removeBttn.setBackground(Color.decode("#C5AF80"));
        removeBttn.setForeground(Color.decode("#EDE7E3"));
        removeBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        removeBttn.setFocusable(false);
        removeBttn.setBorderPainted(false);
        removeBttn.addActionListener(this);
        removeBttn.setBounds(80, 400, 330, 50);

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
        previewPanel.setBackground(Color.decode("#CAB1C3"));
        previewPanel.setBounds(580, 65, 900, 640);
        previewPanel.setLayout(null);

        previewPanel.add(questionLbl);
        questionLbl.setForeground(Color.decode("#4B1D3F"));
        questionLbl.setFont(new Font("Impact", Font.PLAIN, 23));
        questionLbl.setBounds(30, 20, 250, 30);

        previewPanel.add(questionTextArea);
        questionTextArea.setBackground(Color.decode("#D9D9D9"));
        questionTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        questionTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 80));
        questionTextArea.setBounds(30, 50, 840, 440);
        questionTextArea.setForeground(Color.BLACK);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);

        JScrollPane questionScrollPane = new JScrollPane(questionTextArea);
        questionScrollPane.setBounds(30, 50, 840, 440);
        questionScrollPane.setOpaque(false);
        questionScrollPane.getViewport().setOpaque(false);
        questionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        previewPanel.add(questionScrollPane);

        questionTextArea.addFocusListener(
                new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                if (questionTextArea.getText().equals("Paste text here...")) {
                    questionTextArea.setText("");
                    questionTextArea.setForeground(Color.BLACK);
                }
            }
        }
        );

        previewPanel.add(answerLbl);
        answerLbl.setForeground(Color.decode("#4B1D3F"));
        answerLbl.setFont(new Font("Impact", Font.PLAIN, 15));
        answerLbl.setBounds(30, 500, 250, 30);

        previewPanel.add(answerTxt);
        answerTxt.setBackground(Color.decode("#EDE7E3"));
        answerTxt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        answerTxt.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        answerTxt.setBounds(30, 530, 300, 40);
        answerTxt.setForeground(Color.BLACK);

        if (flashcardSet == null || flashcardSet.getCards().isEmpty()) {
            flashcardSet = new FlashcardSet(
                    titleTxt.getText().trim(),
                    (String) colorCbox.getSelectedItem(),
                    classroomData.getClassroomID(),
                    LocalDateTime.now()
            );
            flashcardSet.addCard("", "");
            currentPageIndex = 0;
            updatePageDisplay();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to cancel updating this flashcard?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
            }
        }

        if (e.getSource() == addBttn) {
            if (!saveCurrentPage()) {
                return;
            }

            flashcardSet.addCard("", "");
            currentPageIndex = flashcardSet.getCards().size() - 1;
            updatePageDisplay();
        }


        if (e.getSource() == removeBttn) {
            if (flashcardSet.getCards().size() <= 1) {
                JOptionPane.showMessageDialog(this, "At least one flashcard must remain.");
                return;
            }

            if (!saveCurrentPage()) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this flashcard?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            flashcardSet.getCards().remove(currentPageIndex);
            currentPageIndex = Math.max(0, currentPageIndex - 1);
            updatePageDisplay();
        }


        if (e.getSource() == saveBttn) {
            if (flashcardSet.getCards().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No flashcards to save.");
                return;
            }

            if (!saveCurrentPage()) {
                return;
            }

            String title = titleTxt.getText().trim();
            String color = (String) colorCbox.getSelectedItem();

            if (title.isBlank()) {
                JOptionPane.showMessageDialog(this, "Flashcard title cannot be blank.");
                return;
            }
            if (title.length() > 100) {
                JOptionPane.showMessageDialog(this, "Flashcard title must not exceed 100 characters.");
                return;
            }
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Flashcard title cannot be empty.");
                return;
            }

            flashcardSet.setTitle(title);
            flashcardSet.setColorName(color);
            flashcardSet.saveToDatabase();

            JOptionPane.showMessageDialog(this, flashcardSet.getCards().size() + " flashcard(s) saved successfully!");
            mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
        }


        if (e.getSource() == flashcardSelector) {

            String selectedTitle = (String) flashcardSelector.getSelectedItem();
            if (selectedTitle != null) {
                List<FlashcardData> cards = FlashcardDatabase.getFlashcardsByTitleAndClassroom(selectedTitle, classroomData.getClassroomID());
                if (!cards.isEmpty()) {
                    FlashcardData firstCard = cards.get(0);
                    flashcardSet = new FlashcardSet(
                            selectedTitle,
                            firstCard.getColorName(),
                            classroomData.getClassroomID(),
                            firstCard.getCreationDate()
                    );

                    flashcardSet.getCards().addAll(cards);
                    previewPanel.setBackground(ColorUtils.getColorFromName(firstCard.getColorName()));

                    currentPageIndex = 0;
                    updatePageDisplay();
                    titleTxt.setText(selectedTitle);
                    colorCbox.setSelectedItem(firstCard.getColorName());
                }
            }
        }

        if (e.getSource() == nextBttn) {
            if (!saveCurrentPage()) {
                return;
            }

            if (currentPageIndex < flashcardSet.getCards().size() - 1) {
                currentPageIndex++;
                updatePageDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "You're on the last page.");
            }
        }

        if (e.getSource() == backBttn) {
            if (!saveCurrentPage()) {
                return;
            }

            if (currentPageIndex > 0) {
                currentPageIndex--;
                updatePageDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "You're on the first page.");
            }
        }


        if (e.getSource() == colorCbox) {
            String color = (String) colorCbox.getSelectedItem();

            previewPanel.setBackground(ColorUtils.getColorFromName(color));
        }

    }

    private boolean saveCurrentPage() {
        String question = questionTextArea.getText().trim();
        String answer = answerTxt.getText().trim();

        if (question.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Both question and answer must be filled before saving.");
            return false;
        }

        FlashcardData updated = new FlashcardData(
                flashcardSet.getTitle(),
                question,
                answer,
                LocalDateTime.now(),
                flashcardSet.getColorName(),
                flashcardSet.getClassroomID()
        );

        if (flashcardSet.getCards().isEmpty()) {
            flashcardSet.getCards().add(updated);
            currentPageIndex = 0;
        } else if (currentPageIndex >= 0 && currentPageIndex < flashcardSet.getCards().size()) {
            flashcardSet.getCards().set(currentPageIndex, updated);
        }

        return true;
    }

    private void updatePageDisplay() {
        if (flashcardSet == null || flashcardSet.getCards().isEmpty()) {
            questionTextArea.setText("Paste text here...");
            answerTxt.setText("");
            questionNumLbl.setText("Question: 0/0");
            return;
        }

        FlashcardData current = flashcardSet.getCards().get(currentPageIndex);
        questionTextArea.setText(current.getQuestionContent());
        answerTxt.setText(current.getAnswerContent());
        questionNumLbl.setText("Question: " + (currentPageIndex + 1) + "/" + flashcardSet.getCards().size());
    }

}

class RemoveFlashcard extends JFrame implements ActionListener {

    private JComboBox<String> flashcardSelector;
    private JButton deleteBttn = new JButton("Delete");
    private JButton cancelBttn = new JButton("Cancel");
    private ClassroomData classroomData;
    private TeacherData teacherData;
    private MainFrame mainFrame;

    public RemoveFlashcard(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;

        setTitle("Remove Flashcard Set");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel selectLbl = new JLabel("Select Flashcard Set to Delete:");
        selectLbl.setFont(new Font("Arial", Font.BOLD, 16));
        selectLbl.setBounds(30, 20, 300, 30);
        add(selectLbl);

        flashcardSelector = new JComboBox<>();
        flashcardSelector.setBounds(30, 60, 320, 30);
        add(flashcardSelector);

        List<String> titles = FlashcardDatabase.getDistinctTitlesByClassroom(classroomData.getClassroomID());
        for (String title : titles) {
            flashcardSelector.addItem(title);
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
            String selectedTitle = (String) flashcardSelector.getSelectedItem();
            if (selectedTitle != null) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete the flashcard set \"" + selectedTitle + "\"?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    FlashcardDatabase.removeFlashcardsByTitleAndClassroom(selectedTitle, classroomData.getClassroomID());
                    JOptionPane.showMessageDialog(this, "Flashcard set deleted successfully.");
                    mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
                    dispose();
                }
            }
        }

        if (e.getSource() == cancelBttn) {
            dispose();
        }
    }
}
