package echodraft;

import java.util.ArrayList;
import java.util.List;

public class TeacherData {

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private int accNumber;

    public TeacherData(String firstName, String middleName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase() + ", "
                + firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase() + " "
                + middleName.substring(0, 1).toUpperCase() + ".";
    }

    public String getPassword() {
        return password;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }
    
    public String getFormattedAccNumber() {
        return String.format("%04d", accNumber);
    }

}

class TeacherDatabase {

    public static List<TeacherData> users = new ArrayList<>();
    private static int accCounter = 1;

    public static boolean usernameExists(String username) {
        for (TeacherData user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public static TeacherData getUser(String username) {
        for (TeacherData user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(TeacherData user) {
        user.setAccNumber(accCounter++);
        users.add(user);
    }

    

}
