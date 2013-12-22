import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Statistics {
    private Integer mistake = 0;
    private static Integer time = 0;
    private int speed;
    private static ArrayList<Integer> speeds = new ArrayList<Integer>();
    private ArrayList<Integer> mistakes = new ArrayList<Integer>(); //для глобальной статистики ошибочных клавиш
    private Timer timer;

    //Mistakes
    public void resetAllStatistics() {
        mistake = 0;
        time = 0;
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
                speed = (int)Math.round(Stamina.getTypedLessonLength() * 1.0 / time * 60);
                speeds.add(speed);
                System.out.println(speed);
                System.out.println(speeds);
            }
        };
        timer = new Timer(1000, taskPerformer);
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public static int getTime() {
        return time;
    }

    public static int getTimePerCurrentTimePeriod(int seconds) {
        return time/seconds;
    }

    //Speed in SECONDS
    public String getSpeed() {   //для отображения в окне текущей скорости во время урока
        return speeds.toString();
    }

    public static int[] getSpeedPerCurrentTimePeriod(int intervalInSeconds, Integer[] array) {
        int tI = intervalInSeconds;
        Integer[] x = array;
        int[] sum = new int[10];
        int sumLocal;
        int q = 0;
        for (int i = 0; i < x.length; i += tI) {
            sumLocal = 0;
            for (int j = i; j < tI + i; j++) {
                if (tI + i >= x.length) break;
                sumLocal += x[j];
            }
            if (q >= sum.length) break;
            sum[q] = sumLocal/tI;
//            System.out.println("sum[" + q + "] = " + sum[q]);
            q++;
        }
        return sum;
    }

    public static int getAverageSpeed() { //средняя арифметическая скорость за 60 секунд, считается только по правильно набранным клавишам
        int sumSpeed = 0;
        for (Integer speed : speeds) sumSpeed += speed;
        double time = getTime();
        double len = sumSpeed;
        double ans = len/time;
        return (int)ans;
    }

    public int getAverageSpeed_v2() { //средняя скорость за 60 секунд, считается только по правильно набранным клавишам
        return Stamina.getTypedLessonLength()/getTime();
    }
}
