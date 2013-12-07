public class LessonProcessing {

    private StringBuilder strCurrent = new StringBuilder();
    private StringBuilder strResent = new StringBuilder();

    //temporary strings
    private String lessonContent =    new String("wwwooo wwowo owwoo wowow oowow wow low now owe woe wowwo owowo row owner wood woke worm wolf world how work qqqooo qqoqo oqqoo qoqoq ooqoq qua iq qoqqo oqoqo equal quad quake quiet quit unique queen quay quod quoin quorum quota quotha pppqqq ppqpq qppqq pqpqp qqpqp equip up pqppq qpqpq pal pig pen pan pap pepper pepsi rap vip tape pair palp opaque wwpwp pwwpp wpwpw ppwpw paw pow wpwwp pwpwp wrap wipe weep whip wimp qqwqw wqqww sow qwqwq wwqwq query oopop poopp pop opopo ppopo opel prop qoqwp owppq oqopw woqqp wpwqo pqoow pwpoq qpwwo quid pro quo equator whore purple equivoke whole power opaque");
    private String lessonResContent = new String("");       //^ норм отображение напечатанного начинается


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
