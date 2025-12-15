package echodraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

//Each slide is a node with content and a pointer to the next slide. 
class SlideNode {

    String content;
    SlideNode next;

    public SlideNode(String content) {
        this.content = content;
        this.next = null;
    }
}

public class LectureData {

    private String lectureTitle;
    private String authorName;
    private SlideNode head;
    private SlideNode current;
    private LocalDateTime createdAt;
    private String colorName;
    private int classroomID;

    public LectureData(String lectureTitle, String authorName, LocalDateTime createdAt, String colorName, int classroomID) {
        this.lectureTitle = lectureTitle;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.colorName = colorName;
        this.classroomID = classroomID;
        this.head = null;
        this.current = null;
    }

    public int addSlide(String content) {
        SlideNode newNode = new SlideNode(content);
        if (head == null) {
            head = newNode;
            current = head;
            return 0;
        } else {
            SlideNode temp = head;
            int index = 0;
            while (temp.next != null) {
                temp = temp.next;
                index++;
            }
            temp.next = newNode;
            current = newNode;
            return index + 1;
        }

    }

    public void removeCurrentSlide() {
        if (head == null || current == null) {
            return;
        }

        if (current == head) {
            head = head.next;
            current = head;
            return;
        }

        SlideNode prev = head;
        while (prev.next != null && prev.next != current) {
            prev = prev.next;
        }

        if (prev.next == current) {
            prev.next = current.next;
            current = prev.next != null ? prev.next : head;
        }
    }

    public void updateCurrentSlide(String newContent) {
        if (current != null) {
            current.content = newContent;
        }
    }

    public String getCurrentSlide() {
        return current != null ? current.content : "";
    }

    public void nextSlide() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    public void previousSlide() {
        if (head == null || current == null || current == head) {
            return;
        }

        SlideNode temp = head;
        while (temp.next != null && temp.next != current) {
            temp = temp.next;
        }

        // If we found the previous node, update current
        if (temp.next == current) {
            current = temp;
        }
    }

    public List<String> getAllSlides() {
        List<String> all = new ArrayList<>();
        SlideNode temp = head;
        while (temp != null) {
            all.add(temp.content);
            temp = temp.next;
        }
        return all;
    }

    public int getCurrentSlideIndex() {
        if (current == null) {
            return -1;
        }
        int index = 0;
        SlideNode temp = head;
        while (temp != null && temp != current) {
            index++;
            temp = temp.next;
        }
        return temp == null ? -1 : index;
    }

    public int getSlideCount() {
        int count = 0;
        SlideNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getColorName() {
        return colorName;
    }

    public boolean hasNextSlide() {
        return current != null && current.next != null;
    }

    public boolean hasPreviousSlide() {
        return current != null && current != head;
    }

    //Groups the slide in order to store it in a single lecture
    public void setMetadata(String title, String author, String color) {
        this.lectureTitle = title;
        this.authorName = author;
        this.colorName = color;
    }

    public void removeSlide(int index) {
        if (index < 0 || index >= getSlideCount()) {
            return;
        }

        if (index == 0) {
            head = head.next;
            current = head;
            return;
        }

        SlideNode prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        SlideNode toRemove = prev.next;
        prev.next = toRemove.next;

        if (current == toRemove) {
            current = prev.next != null ? prev.next : head;
        }
    }

    public void setCurrentSlide(int index) {
        if (index < 0 || index >= getSlideCount()) {
            return;
        }

        SlideNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        current = temp;
    }

    public int getClassroomID() {
        return classroomID;
    }

    @Override
    public String toString() {
        return lectureTitle;
    }

}

class LectureDatabase {

    public static List<LectureData> lectureList = new ArrayList<>();

    public static void addLecture(LectureData lecture) {
        lectureList.add(lecture);
    }

    public static List<LectureData> getAllLectures() {
        return lectureList;
    }

    public static List<LectureData> getLecturesByClassroom(int classroomID) {
        List<LectureData> result = new ArrayList<>();
        for (LectureData lecture : lectureList) {
            if (lecture.getClassroomID() == (classroomID)) {
                result.add(lecture);
            }
        }
        return result;
    }

}
