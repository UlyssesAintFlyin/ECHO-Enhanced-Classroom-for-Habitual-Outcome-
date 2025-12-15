package echodraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassroomData {

    private int classroomID;
    private String classroomName;
    private String subjectName;
    private String sectionName;
    private String color;
    private int teacherAccNum;
    private int activityCount = 0;

    public ClassroomData(int classroomID, String classroomName, String subjectName, String sectionName, String color, int teacherAccNum) {
        this.classroomID = classroomID;
        this.classroomName = classroomName;
        this.subjectName = subjectName;
        this.sectionName = sectionName;
        this.color = color;
        this.teacherAccNum = teacherAccNum;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getColor() {
        return color;
    }

    public int getTeacherAccNum() {
        return teacherAccNum;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int count) {
        this.activityCount = count;
    }

    private List<String> activityNames = new ArrayList<>();

    public List<String> getActivityNames() {
        return activityNames;
    }

    public void setActivityNames(List<String> names) {
        this.activityNames = names;
    }

}

class ClassDatabase {

    public static List<ClassroomData> classes = new ArrayList<>();
    private static int classCounter = 1;

    public static void addClass(String classroomName, String subjectName, String sectionName, String color, String teacherUsername) {

        TeacherData teacher = TeacherDatabase.getUser(teacherUsername);

        ClassroomData newClass = new ClassroomData(classCounter, classroomName, subjectName, sectionName, color, teacher.getAccNumber());
        newClass.setClassroomID(classCounter++);
        classes.add(newClass);
    }
    
    //We use this to populate the dashboard by filtering out the classroom that the teacher creates
    public static List<ClassroomData> getClassesByTeacher(String teacherUsername) { 
        TeacherData teacher = TeacherDatabase.getUser(teacherUsername);

        List<ClassroomData> result = new ArrayList<>();
        for (ClassroomData cd : classes) {
            if (cd.getTeacherAccNum() == teacher.getAccNumber()) {
                result.add(cd);
            }
        }
        return result;
    }
    
    //used for retrieving the classroom for manipulation
    public static ClassroomData getClassById(int id) {
        for (ClassroomData cd : classes) {
            if (cd.getClassroomID() == id) {
                return cd;
            }
        }
        return null;
    }
    
    //Deletes a classroom using the ID as a primary key
    public static boolean removeClassById(int id) {
        return classes.removeIf(cd -> cd.getClassroomID() == id);
    }

}
