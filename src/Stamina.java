
public class Stamina {

    public static int lessonID = 0;
    public static String strCurrent = "                                                  " + LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[lessonID]);
//    public static String witeSpace = "                                        ";


    public static void main(String[] args) {
        View view = new View();
        view.createUI();
        view.setActualText(strCurrent);

    }


    public static boolean keyChecker(String key) {
        if (key.equals("space")) key = " ";
        if (key.equals("semicolon")) key = ";";
        if (key.equals("slash")) key = "/";
        return key.equals(strCurrent.substring(50, 51));
    }


    public static String updateActual() {
        if (strCurrent.length() == 0) {
            return "";
        }
        strCurrent = strCurrent.substring(1, strCurrent.length());
        return strCurrent;
    }


    public static String updateActual(String str) {
        strCurrent = "                                                  " + str;
        return strCurrent;
    }


}
