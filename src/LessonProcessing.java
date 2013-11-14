public class LessonProcessing {

    private StringBuilder strCurrent = new StringBuilder();
    private StringBuilder strResent = new StringBuilder();

    //temporary strings
    private String lessonContent =    new String("bbbnnn bbnbn nbbnn bnbnb nnbnb nan bab blab lan ban jan bnbbn nbnbn abba anna jab nab bass bask bad fan lab fab dab nb dan bland blank and bank vvvnnn vvnvn nvvnn vnvnv nnvnv val van vnvvn nvnvn java vs vb vandal vas vj lava naval dvd mmmvvv mmvmv vmmvv mvmvm vvmvm mak malm mvmmv vmvmv am lam mass mamma small mask jam sms jvm bbmbm mbbmm bmbmb mmbmb amb lamb bmbbm mbmbm bam jamb mamba vvbvb bvvbb vbvbv bvds vnnmnm mnnmm man mmnmn manna nam vnvbm nbmmv nvnmb bnvvm bmbvn mvnnb mbmnv vmbbn band van kvass lambda dvd nan jams small bank vandal");
    private String lessonResContent = new String("                                            ");       //^ норм отображение напечатанного начинается


    public LessonProcessing() {
        setStrCurrent(lessonContent);
        setStrResent(lessonResContent);
    }


    public void setStrCurrent(String str) {
        strCurrent.replace(0, str.length(), str);
    }


    public void setStrResent(String str) {
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
