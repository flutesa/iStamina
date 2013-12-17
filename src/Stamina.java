
public class Stamina {

    public int lessonID = 0;
    public String whiteSpace = "                         ";
    public String strCurrent = whiteSpace + LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[lessonID]);


    public static void main(String[] args) {
        new View();
    }


    public boolean keyChecker(char key) {
        return key == strCurrent.substring(25, 26).toCharArray()[0];
    }


    public String updateActual() {
        if (strCurrent.length() == 0) return "";
        strCurrent = strCurrent.substring(1, strCurrent.length());
        return strCurrent;
    }


    public String updateActual(String str) {
        strCurrent = whiteSpace + str;
        return strCurrent;
    }


    public int setLessonID(int id) {
        lessonID = id;
        return lessonID;
    }


    public int getNextLessonID() {
        lessonID++;
        String[] arr = LessonsJSON.getLessonsNames();
        if (lessonID == arr.length) lessonID = 0;
        return lessonID;
    }


}
