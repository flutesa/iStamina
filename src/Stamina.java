
public class Stamina {

    private static int lessonID = 0;
    private String whiteSpace = "                         ";
    public String strCurrent = whiteSpace + LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[lessonID]);
    public static String strResent = "";


    public static void main(String[] args) {
        new View();
//        new Graphic();
    }

    public boolean keyChecker(char key) {
        return key == strCurrent.substring(25, 26).toCharArray()[0];
    }

    public String updateActual() {
        if (strCurrent.length() == 0) return "";
        strResent = strResent + strCurrent.substring(25, 26);
        strCurrent = strCurrent.substring(1, strCurrent.length());
        return strCurrent;
    }

    public String updateActualAndResent(String str) {
        strCurrent = whiteSpace + str;
        strResent = "";
        return strCurrent;
    }

    public int setLessonID(int id) {
        lessonID = id;
        return lessonID;
    }

    public static int getLessonID() {
        return lessonID;
    }

    public static String getLessonName() {
        return "";
    }

    public static int getSourceLessonLength() {
        return LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[Stamina.getLessonID()]).length();
    }

    public int getCurrentLessonLength() {
        return strCurrent.length() - 25;
    }

    public static int getTypedLessonLength() {
        return strResent.length();
    }

    public int getNextLessonID() {
        lessonID++;
        String[] arr = LessonsJSON.getLessonsNames();
        if (lessonID == arr.length) lessonID = 0;
        return lessonID;
    }

    public int getPreviousLessonID() {
        lessonID--;
        if (lessonID <= 0) lessonID = 0;
        return lessonID;
    }
}
