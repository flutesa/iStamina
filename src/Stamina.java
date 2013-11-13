import javax.swing.*;

public class Stamina {

    private static StringBuilder strCurrent = new StringBuilder();
    private static StringBuilder strResent = new StringBuilder();


    public static void main(String[] args) {
        Lessons lesson = new Lessons();
        View view = new View();
        view.createUI();

        strCurrent.append("bbbnnn bbnbn nbbnn bnbnb nnbnb nan bab blab lan ban jan bnbbn nbnbn abba anna jab nab bass bask bad fan lab fab dab nb dan bland blank and bank vvvnnn vvnvn nvvnn vnvnv nnvnv val van vnvvn nvnvn java vs vb vandal vas vj lava naval dvd mmmvvv mmvmv vmmvv mvmvm vvmvm mak malm mvmmv vmvmv am lam mass mamma small mask jam sms jvm bbmbm mbbmm bmbmb mmbmb amb lamb bmbbm mbmbm bam jamb mamba vvbvb bvvbb vbvbv bvds vnnmnm mnnmm man mmnmn manna nam vnvbm nbmmv nvnmb bnvvm bmbvn mvnnb mbmnv vmbbn band van kvass lambda dvd nan jams small bank vandal");
        strCurrent.append(" "); //пробел для корректного отображения по завершению урока
        strResent.append(" "); //пробел для корректного отображения по завершению урока
//        strResent.append("                                          "); //пробел для корректного отображения в начале урока
//        strResent.append("                                            "); //пробел для корректного отображения в начале урока
        view.setResentText(strResent.toString());
        view.setActualText(strCurrent.toString());
    }


    public static boolean keyChecker(String key) {
        if (key.equals("space")) key = " ";
        if (key.equals(strCurrent.substring(0,1))) return true;
        else return false;
    }


    public static String updateActual() {
        strCurrent.replace(0, strCurrent.length() - 1, strCurrent.substring(1, strCurrent.length() - 1));
        return strCurrent.toString();
    }


    public static String updateResent() {
        if (strCurrent.length() == 1) endOfLesson(); //end of line
//        strResent.deleteCharAt(0);
        strResent.append(strCurrent.substring(0,1));
        return strResent.toString();
    }


    public static void endOfLesson() {
        Object[] options = {"Да!", "Потом..."};
        int n = JOptionPane.showOptionDialog(null, "Оличек - молодец, давай ещё?!", "урок закончен", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (n==1) System.exit(0);
        else {}
    }

}
