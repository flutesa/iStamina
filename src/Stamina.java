public class Stamina {

    private static StringBuilder strCurrent = new StringBuilder();
    private static StringBuilder strResent = new StringBuilder();


    public static void main(String[] args) {
        View view = new View();
        Lessons lesson = new Lessons();
        view.createUI();

//        strCurrent.append("fffjjj ffjfj jffjj fjfjf jjfjf fj fjf fjffj jfjfj jf jfj dddjjj ddjdj jddjj djdjd jjdjd dj df jd djddj jdjdj dj dfd fd jd jfd jdf kkkddd kkdkd dkkdd kdkdk ddkdk kf jdk kj kdkkd dkdkd kj dk jdk fkjd ffkfk kffkk fkfkf kf jfk kkfkf fkffk kfkfk fk fdk fkj dfk ddfdf fddff kf dfd ffdfd jd fdd fkd jjkjk kjjkk jk jdk jkjkj kj fkj djdfk jfkkd jdjkf fjddk fkfdj kdjjf kfkjd dkffj dj fdd jk dfd jdk jd kf djfk");
//        strCurrent.append("fffjjj ffjfj jffjj fjfjf jjfjf fj fjf fjffj jfjfj jf jfj");
        strCurrent.append("fffjjj ffjfj");
//        strCurrent.append("bbbnnn bbnbn nbbnn bnbnb nnbnb nan bab blab lan ban jan bnbbn nbnbn abba anna jab nab bass bask bad fan lab fab dab nb dan bland blank and bank vvvnnn vvnvn nvvnn vnvnv nnvnv val van vnvvn nvnvn java vs vb vandal vas vj lava naval dvd mmmvvv mmvmv vmmvv mvmvm vvmvm mak malm mvmmv vmvmv am lam mass mamma small mask jam sms jvm bbmbm mbbmm bmbmb mmbmb amb lamb bmbbm mbmbm bam jamb mamba vvbvb bvvbb vbvbv bvds vnnmnm mnnmm man mmnmn manna nam vnvbm nbmmv nvnmb bnvvm bmbvn mvnnb mbmnv vmbbn band van kvass lambda dvd nan jams small bank vandal");
//        strCurrent.append("tttyyy ttyty yttyy tytyt yytyt my tad tytty ytyty talk fat yak sat mat nat yam yd nasty navy tasty may tatty lastly gggyyy ggygy yggyy gygyg yygyg baggy gyggy ygygy gad slang lag jaggy gadfly mangy gabby hhhggg hhghg ghhgg hghgh gghgh hash ghat hghhg ghghg has handy dash aghast tthth htthh ththt hhtht that bath thtth hthth thank myth ggtgt tggtt gat gtgtg ttgtg tang yyhyh hyyhh shy yhyhy hhyhy hymn gygth ythhg ygyht tyggh thtgy hgyyt hthyg ghtty gyttja ghastly shaggy laystall fatly tansy gym lay by taffy sly yank tat many thanks");
//        strResent.append("                                                                                                                                                                                                        ");
        strResent.append("");
        view.setResentText(strResent.toString());
        view.setActualText(strCurrent.toString());
    }

    public static boolean keyChecker(String key) {
        if (key.equals("space")) key = " ";
        if (key.equals(strCurrent.substring(0,1))) return true;
        else return false;
    }

    public static String updateActual() {
//        if (strCurrent.length() == 1) endOfLesson(); //end of line
        if (strCurrent.length() == 1) return ""; //end of line
        strCurrent.replace(0, strCurrent.length()-1, strCurrent.substring(1, strCurrent.length()-1));
        return strCurrent.toString();
    }

    public static String updateResent() {
//        if (strCurrent.length() == 1) endOfLesson(); //end of line
        if (strCurrent.length() == 1) return ""; //end of line
        strResent.append(strCurrent.substring(0,1));
        return strResent.toString();
    }


    public static void endOfLesson() {

    }
}
