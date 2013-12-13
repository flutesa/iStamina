public class LessonProcessing {

    private StringBuilder strCurrent = new StringBuilder();
    private StringBuilder strResent = new StringBuilder();


    public LessonProcessing() {
        updateStrCurrent(LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[0]));
        updateStrResent("");
    }


    public void updateStrCurrent(String str) {
        strCurrent.replace(0, str.length(), str);
    }


    public void updateStrResent(String str) {
        strResent.replace(0, str.length(), str);
    }


    public StringBuilder getStrCurrent() {
        return strCurrent;
    }


    public StringBuilder getStrResent() {
        return strResent;
    }


    public int getStrCurrentLength() {
        return strCurrent.length();
    }


    public int getStrResentLength() {
        return strResent.length();
    }

}
