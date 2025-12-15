package echodraft;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private ClassroomData classroomData;

    public MainFrame() {
        setTitle("E.C.H.O Portal");
        ImageIcon icon = new ImageIcon(getClass().getResource("/echodraft/images/Logo1.png"));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        SetupPage setuppage = new SetupPage(this);
        LoginPage loginpage = new LoginPage(this);

        cardPanel.add(loginpage, "login");
        cardPanel.add(setuppage, "setup");

        setContentPane(cardPanel);
        setVisible(true);

    }

    public void setDashboard(TeacherData user) {
        ClassroomDashboard cDashboard = new ClassroomDashboard(this, user, classroomData);
        cardPanel.add(cDashboard, "classroomDashboard");
        showCard("classroomDashboard");
    }

    public void showCard(String name) {
        cardLayout.show(cardPanel, name);
    }

    public void showSpecificDashboardLectures(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
        LectureData newLecture = new LectureData("Untitled", teacherData.getFullName(), LocalDateTime.now(), "Blue", classroomData.getClassroomID());
        SpecificDashboardLecture dashboard = new SpecificDashboardLecture(classroomData, teacherData, mainFrame, newLecture);
        cardPanel.add(dashboard, "specificDashboard_" + classroomData.getClassroomID());
        showCard("specificDashboard_" + classroomData.getClassroomID());
    }

    public void showSpecificDashboardClasswork(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
        FlashcardData newFlashcard = new FlashcardData("Untitled","", "", LocalDateTime.now(),"Blue",classroomData.getClassroomID());
        Specific_db_Classwork dashboard = new Specific_db_Classwork(classroomData, teacherData, mainFrame, newFlashcard);
        cardPanel.add(dashboard, "specificDashboardClasswork_" + classroomData.getClassroomID());
        showCard("specificDashboardClasswork_" + classroomData.getClassroomID());
    }

    public void showSpecificDashboardPeople(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
        Specific_db_People dashboard = new Specific_db_People(classroomData, teacherData, mainFrame);
        cardPanel.add(dashboard, "specificDashboard_" + classroomData.getClassroomID());
        showCard("specificDashboard_" + classroomData.getClassroomID());
    }   
    
    public void showSpecificDashboardGrades(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame) {
        StudentData newStudent = new StudentData("untitledUser", "Doe", "", "John", "Male", 12345, 18);
        Specific_db_Grades dashboard = new Specific_db_Grades(classroomData, teacherData, mainFrame, newStudent);
        cardPanel.add(dashboard, "specificDashboard_" + classroomData.getClassroomID());
        showCard("specificDashboard_" + classroomData.getClassroomID());
    }
    
    public void addLecture(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, LectureData lectureData) {
        AddLecture addLec = new AddLecture(classroomData, teacherData, mainFrame, lectureData);
        cardPanel.add(addLec, "addLectureCard" + classroomData.getClassroomID());
        showCard("addLectureCard" + classroomData.getClassroomID());
    }
    
    public void editLecture(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, LectureData lectureData) {
        EditLecture editLEc = new EditLecture(classroomData, teacherData, mainFrame, lectureData);
        cardPanel.add(editLEc, "addLectureCard" + classroomData.getClassroomID());
        showCard("addLectureCard" + classroomData.getClassroomID());
    }
    
    public void actualLecture(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, LectureData lectureData) {
        ActualLecture actualLec = new ActualLecture(classroomData, teacherData, mainFrame, lectureData);
        cardPanel.add(actualLec, "ActualLect" + classroomData.getClassroomID());
        showCard("ActualLect" + classroomData.getClassroomID());
    }
    
    public void addFlashcard(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardData flashcardData) {
        AddFlashcard addFlashcard = new AddFlashcard(classroomData, teacherData, mainFrame, flashcardData);
        cardPanel.add(addFlashcard, "addFlashcard" + classroomData.getClassroomID());
        showCard("addFlashcard" + classroomData.getClassroomID());
    }
    
    public void editFlashcard(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardData flashcardData) {
        EditFlashcard editFlashcard = new EditFlashcard(classroomData, teacherData, mainFrame, flashcardData);
        cardPanel.add(editFlashcard, "editFlashcard" + classroomData.getClassroomID());
        showCard("editFlashcard" + classroomData.getClassroomID());
    }
    
    public void actualFlashcard(ClassroomData classroomData, TeacherData teacherData, MainFrame mainFrame, FlashcardSet flashcardSet) {
        ActualFlashcard actualflash = new ActualFlashcard(classroomData, teacherData, mainFrame, flashcardSet);
        cardPanel.add(actualflash, "actualflash" + classroomData.getClassroomID());
        showCard("actualflash" + classroomData.getClassroomID());
    }

}
