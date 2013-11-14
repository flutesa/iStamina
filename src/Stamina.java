import javax.swing.*;

public class Stamina {

    static LessonProcessing lessonProcessing = new LessonProcessing();

    public static void main(String[] args) {
        View view = new View();
        view.createUI();

        view.setResentText(lessonProcessing.getStrResent().toString());
        view.setActualText(lessonProcessing.getStrCurrent().toString());
    }


    public boolean keyChecker(String key) {
        if (key.equals("space")) key = " ";
        if (key.equals(lessonProcessing.getStrCurrent().substring(0, 1))) return true;
        else return false;
    }


    public String updateActual() {
        StringBuilder tmp = lessonProcessing.getStrCurrent();
        tmp.replace(0, lessonProcessing.getStrCurrentLength() - 1, lessonProcessing.getStrCurrent().substring(1, lessonProcessing.getStrCurrentLength() - 1));
        lessonProcessing.setStrCurrent(tmp.toString());
        return tmp.toString();
    }


    public String updateResent() {
        if (lessonProcessing.getStrCurrentLength() == 1) endOfLesson(); //end of line
        StringBuilder tmp = lessonProcessing.getStrResent();
        tmp.deleteCharAt(0);
        tmp.append(lessonProcessing.getStrCurrent().substring(0, 1));
        lessonProcessing.setStrResent(tmp.toString());
        return tmp.toString();
    }


    public static void endOfLesson() {
        Object[] options = {"Да!", "Потом..."};
        int n = JOptionPane.showOptionDialog(null, "Оличек - молодец, давай ещё?!", "урок закончен", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (n==1) System.exit(0);
        else {}
    }

}
