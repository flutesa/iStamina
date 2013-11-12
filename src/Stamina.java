import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Stamina {

    private static StringBuilder strCurrent = new StringBuilder();
    private static StringBuilder strResent = new StringBuilder();


    public static void main(String[] args) {
        View view = new View();
        view.createUI();

        strCurrent.append("fffjjj ffjfj jffjj fjfjf jjfjf fj fjf fjffj jfjfj jf jfj dddjjj ddjdj jddjj djdjd jjdjd dj df jd djddj jdjdj dj dfd fd jd jfd jdf kkkddd kkdkd dkkdd kdkdk ddkdk kf jdk kj kdkkd dkdkd kj dk jdk fkjd ffkfk kffkk fkfkf kf jfk kkfkf fkffk kfkfk fk fdk fkj dfk ddfdf fddff kf dfd ffdfd jd fdd fkd jjkjk kjjkk jk jdk jkjkj kj fkj djdfk jfkkd jdjkf fjddk fkfdj kdjjf kfkjd dkffj dj fdd jk dfd jdk jd kf djfk");
//        strCurrent.append("fffjjj ffjfj jffjj fjfjf jjfjf fj fjf fjffj jfjfj jf jfj");
//        strCurrent.append("fffjjj ffjfj");

        view.setResentText("test-test");
        view.setActualText(strCurrent.toString());
    }

    public static boolean keyChecker(String key) {
        if (key.equals("space")) key = " ";
        if (key.equals(strCurrent.substring(0,1))) return true;
        else return false;
    }

    public static String updateActual(String key) {
//        if (strCurrent.length() == 1) endOfLesson(); //end of line
        if (strCurrent.length() == 1) return " "; //end of line
        if (keyChecker(key)) {
            strCurrent.replace(0, strCurrent.length()-1, strCurrent.substring(1, strCurrent.length()-1));
            return strCurrent.toString();
        } else return strCurrent.toString();
    }

    public static String updateResent(String key) {
        if (keyChecker(key)) {
            strResent.append(strCurrent.substring(0,1));
            return strResent.toString();
        } else return strResent.toString();

    }


    public static void endOfLesson() {

    }
}
