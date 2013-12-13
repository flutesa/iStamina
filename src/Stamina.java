
public class Stamina {

    public static int lessonID = 0;
    public static String strResent = "";
    public static String strCurrent = LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[lessonID]);


    public static void main(String[] args) {
        View view = new View();

        view.createUI();

        view.setResentText(strResent);
        view.setActualText(strCurrent);

    }


    public static boolean keyChecker(String key) {
        if (key.equals("space")) key = " ";
        if (key.equals("semicolon")) key = ";";
        if (key.equals("slash")) key = "/";
        return key.equals(strCurrent.substring(0, 1));
    }


    public static String updateActual() {
        if (strCurrent.length() == 0) {
            return "";
        }
        strCurrent = strCurrent.substring(1, strCurrent.length());
        return strCurrent;
    }


    public static String updateResent() {
        strResent = strResent + strCurrent.substring(0, 1);
        return strResent;
    }

    public static String updateActual(String str) {
        strCurrent = str;
        return strCurrent;
    }


    public static String updateResent(String str) {
        strResent = str;
        return strResent;
    }



}
