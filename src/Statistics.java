import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class Statistics {
    private Integer mistake = 0;
    private Integer time = 0;
    private ArrayList<Integer> times = new ArrayList<Integer>();
    private ArrayList<Integer> speeds = new ArrayList<Integer>();
    private ArrayList<Integer> mistakes = new ArrayList<Integer>(); //для глобальной статистики ошибочных клавиш
    private Timer timer;

    //Mistakes
    public void resetAllStatistics() {
        mistake = 0;
        time = 0;
        times.clear();
        speeds.clear();
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
        double mis = getMistake();
        double len = Stamina.getSourceLessonLength();
        double ans = (mis / len) * 100;
        return (int)ans;
    }

    //Timers
    public void startTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                times.add(time);
                speeds.add(Stamina.getTypedLessonLength() / time);
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
    public String getSpeed() {   //для отображения в окне текущей скорости во время урока
        return speeds.toString();
    }

    public int getAverageSpeed() { //средняя скорость за 60 секунд, считается только по правильно набранным клавишам
        return Stamina.getTypedLessonLength()/getTime()*60;
    }

    public int getAverageSpeed_v2() { //средняя арифметическая скорость за 60 секунд, считается только по правильно набранным клавишам
        int sumSpeed = 0;
        for (Integer speed : speeds) sumSpeed += speed;
        double time = getTime();
        double len = sumSpeed;
        double ans = len/time*60;
        return (int)ans;
    }
}
