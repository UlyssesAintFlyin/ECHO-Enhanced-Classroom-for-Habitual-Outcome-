package echodraft;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private String username;
    private String surname;
    private String middleName;
    private String firstName;
    private String gender;
    private int studentNumber;
    private Object[][] grades;
    private int age;

    public StudentData(String username, String surname, String middleName,
            String firstName, String gender, int studentNumber, int age) {
        this.username = username;
        this.surname = surname;
        this.middleName = middleName;
        this.firstName = firstName;
        this.gender = gender;
        this.studentNumber = studentNumber;
        this.age = age;
    }

    public String getFullName() {
        String middleInitial = middleName.isEmpty() ? "" : " " + middleName.charAt(0) + ".";
        return surname + ", " + firstName + middleInitial;
    }

    public String getUsername() {
        return username;
    }
    
    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }
    
    public Object[][] getGrades() {
        return grades;
    }

    public void setGrades(Object[][] grades) {
        this.grades = grades;
    }

    public int getAge() {
        return age;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentData(StudentData other) {
        this(other.username, other.surname, other.middleName, other.firstName, other.gender, other.studentNumber, other.age);
        this.grades = other.grades;
    }

}

class StudentDatabase {

    private static final List<ClassroomRoster> allRosters = new ArrayList<>();

    private static class ClassroomRoster {

        String classroomId;
        List<StudentData> students;

        ClassroomRoster(String classroomId) {
            this.classroomId = classroomId;
            this.students = new ArrayList<>();
        }
    }

    private static ClassroomRoster findRoster(String classroomId) {
        for (ClassroomRoster roster : allRosters) {
            if (roster.classroomId.equals(classroomId)) {
                return roster;
            }
        }
        ClassroomRoster newRoster = new ClassroomRoster(classroomId);
        allRosters.add(newRoster);
        return newRoster;
    }

    public static void addStudent(String classroomId, StudentData student) {
        findRoster(classroomId).students.add(student);
    }

    public static List<StudentData> searchStudents(String classroomId, String keyword) {
        List<StudentData> result = new ArrayList<>();
        for (StudentData s : findRoster(classroomId).students) {
            if (s.getUsername().toLowerCase().contains(keyword)
                    || s.getSurname().toLowerCase().contains(keyword)
                    || s.getMiddleName().toLowerCase().contains(keyword)
                    || s.getFirstName().toLowerCase().contains(keyword)
                    || String.valueOf(s.getStudentNumber()).contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }

    public static void removeStudentByNumber(String classroomId, String studentNumber) {
        findRoster(classroomId).students.removeIf(s -> String.valueOf(s.getStudentNumber()).equals(studentNumber));
    }

    public static StudentData getStudentByRow(String classroomId, int index) {
        return findRoster(classroomId).students.get(index);
    }

    public static void sortStudentsBySurname(String classroomId) {
        List<StudentData> list = findRoster(classroomId).students;
        for (int i = 1; i < list.size(); i++) {
            StudentData key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getSurname().compareToIgnoreCase(key.getSurname()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static List<StudentData> getAllStudents(String classroomId) {
        return findRoster(classroomId).students;
    }

    public static void saveStudents(String classroomId) {
        List<StudentData> students = findRoster(classroomId).students;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students_" + classroomId + ".txt"))) {
            for (StudentData s : students) {
                String line = s.getUsername() + "," + s.getSurname() + "," + s.getMiddleName() + ","
                        + s.getFirstName() + "," + s.getGender() + "," + s.getStudentNumber() + "," + s.getAge();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void bubbleSortBySurname(String classroomId) {
        List<StudentData> list = findRoster(classroomId).students;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getSurname().compareToIgnoreCase(list.get(j + 1).getSurname()) > 0) {
                    StudentData temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

}
