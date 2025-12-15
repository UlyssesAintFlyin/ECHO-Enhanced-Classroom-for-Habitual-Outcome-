package echodraft;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlashcardData {

    private String flashcardTitle;
    private String questionContent;
    private String answerContent;
    private LocalDateTime creationDate;
    private String colorName;
    private int classroomID;
    private static int nextId = 1;
    private int cardID;

    public FlashcardData(String flashcardTitle, String questionContent, String answerContent, LocalDateTime creationDate, String colorName, int classroomID) {
        this.flashcardTitle = flashcardTitle;
        this.questionContent = questionContent;
        this.answerContent = answerContent;
        this.creationDate = creationDate;
        this.colorName = colorName;
        this.classroomID = classroomID;
        this.cardID = nextId++;

    }

    public String getFlashcardTitle() {
        return flashcardTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getColorName() {
        return colorName;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public int getCardId() {
        return cardID;
    }

    public void setFlashcardTitle(String flashcardTitle) {
        this.flashcardTitle = flashcardTitle;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return questionContent;
    }

}

class FlashcardDatabase {

    public static List<FlashcardData> flashcardList = new ArrayList<>();

    public static void addFlashcard(FlashcardData flashcard) {
        flashcardList.add(flashcard);
    }

    public static List<FlashcardData> getFlashcards() {
        return flashcardList;
    }

    public static List<FlashcardData> getFlashcardsByTitleAndClassroom(String title, int classroomID) {
        List<FlashcardData> result = new ArrayList<>();
        for (int i = 0; i < flashcardList.size(); i++) {
            FlashcardData flashcard = flashcardList.get(i);
            if (flashcard.getFlashcardTitle().equals(title) && flashcard.getClassroomID() == classroomID) {
                result.add(flashcard);
            }
        }
        return result;
    }

    public static void removeFlashcardsByTitleAndClassroom(String title, int classroomID) {
        for (int i = 0; i < flashcardList.size(); i++) {
            FlashcardData flashcard = flashcardList.get(i);
            if (flashcard.getFlashcardTitle().equals(title) && flashcard.getClassroomID() == classroomID) {
                flashcardList.remove(i);
                i--; 
            }
        }
    }

    public static List<String> getDistinctTitlesByClassroom(int classroomID) {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < flashcardList.size(); i++) {
            FlashcardData flashcard = flashcardList.get(i);
            if (flashcard.getClassroomID() == classroomID) {
                String title = flashcard.getFlashcardTitle();
                if (!titles.contains(title)) {
                    titles.add(title);
                }
            }
        }
        return titles;
    }

    public static List<FlashcardData> getFlashcardsByClassroom(int classroomID) {
        List<FlashcardData> result = new ArrayList<>();
        for (FlashcardData flashcard : flashcardList) {
            if (flashcard.getClassroomID() == (classroomID)) {
                result.add(flashcard);
            }
        }
        return result;
    }

    public static void removeAllFlashcards() {
        flashcardList.clear();
    }

}

//This class represents a collection of flashcards (like a deck or set)
class FlashcardSet {

    private String title;
    private String colorName;
    private int classroomID;
    private LocalDateTime creationDate;
    private List<FlashcardData> cards = new ArrayList<>();
    private List<Integer> cardOrder;

    public FlashcardSet(String title, String colorName, int classroomID, LocalDateTime creationDate) {
        this.title = title;
        this.colorName = colorName;
        this.classroomID = classroomID;
        this.creationDate = creationDate;

    }

    public void addCard(String question, String answer) {
        FlashcardData card = new FlashcardData(title, question, answer, creationDate, colorName, classroomID);
        cards.add(card);
        if (cardOrder == null) {
            cardOrder = new ArrayList<>();
        }
        cardOrder.add(card.getCardId());

    }

    public List<FlashcardData> getCards() {
        return cards;
    }

    public String getTitle() {
        return title;
    }

    public String getColorName() {
        return colorName;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
        for (FlashcardData card : cards) {
            card.setFlashcardTitle(newTitle);
        }
    }

    public void setColorName(String newColor) {
        this.colorName = newColor;
        for (FlashcardData card : cards) {
            card.setColorName(newColor);
        }
    }

    public void saveToDatabase() {
        FlashcardDatabase.removeFlashcardsByTitleAndClassroom(title, classroomID);
        for (FlashcardData card : cards) {
            if (!card.getQuestionContent().trim().isEmpty() && !card.getAnswerContent().trim().isEmpty()) {
                FlashcardDatabase.addFlashcard(card);
            }
        }
    }

}
