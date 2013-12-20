import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Timer;
import java.awt.event.KeyEvent;
import java.util.*;

public class Statistics {
    private Integer mistake = 0;
    private Integer time = 0;
    private ArrayList<Integer> times = new ArrayList<Integer>();
    private ArrayList<Integer> speeds = new ArrayList<Integer>();
    private ArrayList<Integer> mistakes = new ArrayList<Integer>();
    private Timer timer;

    //Mistakes
    public void setMistake(int count) {
        mistake = count;
    }

    public void incMistake() {
        mistake++;
    }

    public void incMistake(KeyEvent ke) { //для статистики ошибочных клавиш
        mistake++;
        mistakes.add(ke.getKeyCode());
    }

    public int getMistake() {
        return mistake;
    }

    public int getMistakePercentage() {
        return getMistake()/Stamina.getSourceLessonLength()*100;
    }

    //Timers
    public void setTime(int tm) {
        time = tm;
    }

    public void startTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                times.add(time);
                speeds.add(Stamina.getTypedLessonLength()/time); //Speed in SECONDS
            }
        };
        timer = new Timer(1000, taskPerformer);
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getTime() {
        return time;
    }

    //Speed in SECONDS
    public String getSpeed() {
        return speeds.toString();
    }

    public int getAverageSpeed() {   //средняя арифметическая скорость за 100 секунд
        int sumSpeed = 0;
        Iterator iter = speeds.iterator();
        while (iter.hasNext()) sumSpeed += (Integer) iter.next();
        return sumSpeed/getTime();
    }

    public int getAverageSpeed_v2() { //средняя скорость за 100 секунд
        return Stamina.getTypedLessonLength()/getTime();
    }

    public int getAverageSpeed_v3() { //!!! //средняя арифметическая скорость за 60 секунд
        int sumSpeed = 0;
        Iterator iter = speeds.iterator();
        while (iter.hasNext()) sumSpeed += (Integer) iter.next();
        return sumSpeed/(60/getTime());
    }

    public int getAverageSpeed_v4() { //средняя скорость за 60 секунд
        return Stamina.getTypedLessonLength()/(60/getTime());
    }
}
