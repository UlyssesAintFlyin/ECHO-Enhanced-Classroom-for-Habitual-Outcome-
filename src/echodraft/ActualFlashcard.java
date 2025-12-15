package echodraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class ActualFlashcard extends JPanel implements ActionListener {

    JLabel appNameLbl = new JLabel("Enhance Classroom for Habitual Outcome     ");
    JPanel toppanel = new JPanel();
    JPanel previewPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JButton backBttn = new JButton("Back");
    JButton nextBttn = new JButton("Next");
    JButton exitBttn = new JButton("Exit");
    JTextArea slideQuestionArea = new JTextArea(3, 16);
    JTextArea slideAnswerArea = new JTextArea(3, 10);
    private JPanel flashcardPanel;

    private JLabel questionNumLbl;

    private MainFrame mainFrame;
    private TeacherData teacherData;
    private ClassroomData classroomData;
    private FlashcardSet flashcardSet;
    private Image backgroundImage;

    private int currentPageIndex = 0;
    private boolean isShowingAnswer = false;

    public ActualFlashcard(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardSet flashcardSet) {
        this.classroomData = classroomData;
        this.teacherData = teacherData;
        this.mainFrame = mainFrame;
        this.flashcardSet = flashcardSet;

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

        JLabel titleLbl = new JLabel(flashcardSet.getTitle());
        centerPanel.add(titleLbl);
        titleLbl.setForeground(Color.decode("#EDE7E3"));
        titleLbl.setFont(new Font("Impact", Font.PLAIN, 40));
        titleLbl.setBounds(30, 10, 250, 50);

        centerPanel.add(sidePanel);
        sidePanel.setLayout(null);
        sidePanel.setBackground(ColorUtils.getColorFromName(flashcardSet.getColorName()));
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

        questionNumLbl = new JLabel("Question: 1/1");
        sidePanel.add(questionNumLbl);
        questionNumLbl.setForeground(Color.decode("#D9D9D9"));
        questionNumLbl.setFont(new Font("Constantia", Font.PLAIN, 25));
        questionNumLbl.setBounds(30, 615, 200, 30);

        slideQuestionArea.setBackground(Color.decode("#D9D9D9"));
        slideQuestionArea.setFont(new Font("Times New Roman", Font.PLAIN, 80));
        slideQuestionArea.setForeground(Color.BLACK);
        slideQuestionArea.setEditable(false);
        slideQuestionArea.setLineWrap(true);
        slideQuestionArea.setWrapStyleWord(true);
        slideQuestionArea.setEditable(false);
        slideQuestionArea.setFocusable(false);

        centerPanel.add(exitBttn);
        exitBttn.setBackground(Color.decode("#D62246"));
        exitBttn.setForeground(Color.decode("#EDE7E3"));
        exitBttn.setFont(new Font("Impact", Font.ITALIC, 20));
        exitBttn.setFocusable(false);
        exitBttn.setBorderPainted(false);
        exitBttn.addActionListener(this);
        exitBttn.setBounds(1430, 660, 80, 40);

        slideAnswerArea.setBackground(Color.decode("#D9D9D9"));
        slideAnswerArea.setFont(new Font("Georgia", Font.BOLD, 150)); 
        slideAnswerArea.setForeground(ColorUtils.getColorFromName(flashcardSet.getColorName()));
        slideAnswerArea.setEditable(false);
        slideAnswerArea.setLineWrap(true);
        slideAnswerArea.setWrapStyleWord(true);
        slideAnswerArea.setEditable(false);
        slideAnswerArea.setFocusable(false);

        JScrollPane questionScroll = new JScrollPane(slideQuestionArea);
        questionScroll.setBorder(null);
        questionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        questionScroll.getViewport().setOpaque(false); 
        questionScroll.setOpaque(false);

        JScrollPane answerScroll = new JScrollPane(slideAnswerArea);
        answerScroll.setBorder(null);
        answerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        answerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        answerScroll.getViewport().setOpaque(false);
        answerScroll.setOpaque(false);

        flashcardPanel = new JPanel(new CardLayout());
        flashcardPanel.setBounds(30, 35, 1190, 565);
        flashcardPanel.setOpaque(false);
        flashcardPanel.add(questionScroll, "question");
        flashcardPanel.add(answerScroll, "answer");
        sidePanel.add(flashcardPanel);

        ((CardLayout) flashcardPanel.getLayout()).show(flashcardPanel, "question");
        updatePageDisplay();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBttn) {
            int x = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit this flashcard?", "WARNING",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
            }
        }

        if (e.getSource() == nextBttn) {
            if (flashcardSet.getCards().isEmpty()) {
                return;
            }

            if (!isShowingAnswer) {
                isShowingAnswer = true;
            } else {
                if (currentPageIndex < flashcardSet.getCards().size() - 1) {
                    currentPageIndex++;
                    isShowingAnswer = false;
                } else {
                    int choice = JOptionPane.showConfirmDialog(
                            this,
                            "You've reached the end of " + flashcardSet.getTitle() + ".\nDo you want to return to the dashboard?",
                            "End of Set",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        mainFrame.showSpecificDashboardClasswork(classroomData, teacherData, mainFrame);
                        return;
                    }
                }
            }

            updatePageDisplay();
        }

        if (e.getSource() == backBttn) {
            if (flashcardSet.getCards().isEmpty()) {
                return;
            }

            if (isShowingAnswer) {
                isShowingAnswer = false;
            } else {
                if (currentPageIndex > 0) {
                    currentPageIndex--;
                    isShowingAnswer = true;
                }
            }
            updatePageDisplay();

        }

    }

    private void updatePageDisplay() {
        if (flashcardSet.getCards().isEmpty()) {
            return;
        }

        FlashcardData card = flashcardSet.getCards().get(currentPageIndex);

        slideQuestionArea.setText(card.getQuestionContent());
        slideAnswerArea.setText(card.getAnswerContent());

        CardLayout cl = (CardLayout) flashcardPanel.getLayout();
        if (isShowingAnswer) {
            cl.show(flashcardPanel, "answer");
        } else {
            cl.show(flashcardPanel, "question");
        }

        questionNumLbl.setText("Question: " + (currentPageIndex + 1) + "/" + flashcardSet.getCards().size());
    }

}
