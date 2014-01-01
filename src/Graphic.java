import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Graphic extends javax.swing.JFrame {

    private int sizeY = 5; //кол-во значений на оси Y, скорость зн/мин
    private int sizeX = 10;//кол-во значений на оси X, секунды прохождения урока

    private int[] x = new int[sizeX]; //координаты точек по оси X
    private int[] y = new int[sizeX]; //координаты точек по оси Y
    private Integer[] xForGraph = new Integer[sizeX]; //метки на координатной оси X
    private Integer[] yForGraph = new Integer[sizeY]; //метки на координатной оси Y
    private Dimension size = new Dimension(400,260); //рабочая область
    private Dimension startPointXoY = new Dimension(40,220); //начало координат

//    private Dimension size = new Dimension(500,250); //рабочая область
//    private Dimension startPointXoY = new Dimension(50,200); //начало координат

    public Graphic(Integer[] speedsArray) {
        buildArrays(speedsArray);
        initInterface();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,size.width,size.height);
        g.setColor(Color.BLACK);

        //отрисовка координатных линий
        for (int i = 0; i < xForGraph.length; i++) { //координаты на оси X, вертикальная сетка
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(xForGraph[i]), x[i], 18 + startPointXoY.height); //подписи к оси
//            g.drawString("sec", x[9] + startPointXoY.width, 18 + startPointXoY.height);   //!!! проверку добавить
//            g.drawString("sec", x[8] + 10 + startPointXoY.width, startPointXoY.height - 10);   //!!! проверку добавить над осью подпись
            g.setColor(Color.GRAY);
            g.drawLine(x[i], startPointXoY.width+40, x[i], startPointXoY.height+3);               //вертикальная сетка
        }

        for (int i = 0; i < yForGraph.length; i++) { //координаты по оси Y, горизонтальная сетка
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(yForGraph[i]),10,startPointXoY.height - (startPointXoY.width - 5) * i);   //подписи к оси
//            g.drawString("cpm",10,startPointXoY.height - (startPointXoY.width - 5) * (xForGraph.length) + 1);
//            g.drawString("зн/м",5,startPointXoY.height - (startPointXoY.width - 10) * xForGraph.length);

            g.setColor(Color.GRAY);
            g.drawLine(startPointXoY.width-3, startPointXoY.height - (startPointXoY.width - 5) * i, startPointXoY.width + x[8]-8, startPointXoY.height - (startPointXoY.width - 5) * i);  //горизонтальная сетка
        }

        g.setColor(Color.BLACK);
        g.drawLine(startPointXoY.width, startPointXoY.width, startPointXoY.width, startPointXoY.height); //ось Y левая
        g.drawLine(startPointXoY.width, startPointXoY.height, size.width - 20, startPointXoY.height);      //ось X нижняя

        g.setColor(Color.RED);
//        System.out.println(Statistics.getAverageSpeed());
        g.drawLine(startPointXoY.width,210,startPointXoY.width + x[8]-8,210);      //средняя скорость
//        g.drawLine(startPointXoY.width,Statistics.getAverageSpeed(),startPointXoY.width + x[8]-8,Statistics.getAverageSpeed());      //средняя скорость
//        g.drawOval(startPointXoY.width, startPointXoY.height - (startPointXoY.width - 5) * 4, 1, 1);

        g.drawPolyline(x,y,10);
    }

    private void buildArrays(Integer[] sp) {
        int timeInterval = Statistics.getTime() / sizeX;

        y = Statistics.getSpeedPerCurrentTimePeriod(timeInterval, sp); // средние значения для координат по оси Х
        for (int i = 0; i < y.length; i++){
            System.out.println("y[" + i + "] = " + y[i]);
        }

//        for (int i = 0; i < y.length; i++){ //ok не средняя скорость за интервал, а локальная в эту секунду
//            y[i] = sp[i * timeInterval];
//            System.out.println("y[" + i + "] = " + y[i]);
//        }

        for (int i = 0; i < x.length; i++) {  //координаты для оси X
            x[i] = i;
            System.out.println("x[" + i + "] = " + x[i]);
        }

        int[] minMax = findMinMax(y); //поиск по выборке из массива (через интервал) //int[] minMax = findMinMax(sp); //поиск по всему массиву
        int min = minMax[0];          //минимальное значение для оси Y
        int max = minMax[1];          //максимальное значение для оси Y

        int delta = (max - min)/4;    //интервал для вычисления промежуточных значений
        int k = 0;
        System.out.println("min = " + min + " max = " + max);
        yForGraph[yForGraph.length-1] = (int)new BigDecimal(max*0.1).setScale(0, RoundingMode.UP).doubleValue()*10; //округляем маx до десятых в бОльшую сторону
        for (int i = 0; i < yForGraph.length-1; i++) {
            yForGraph[i] = (int)new BigDecimal((min + k)*0.1).setScale(0, RoundingMode.DOWN).doubleValue()*10; //округляем min до десятых в меньшую сторону
            k += delta;
        }

        for (Integer aXForGraph : yForGraph) {
            System.out.println("yForGraph = " + aXForGraph);
        }

        for (int i = 0; i < xForGraph.length; i ++) { //ok сохраняем значения для подписей оси Х
            if (timeInterval == 60) xForGraph[i] = i + 1;
            else xForGraph[i] = timeInterval * (i + 1);
            System.out.println("xForGraph = " + xForGraph[i]);
        }


        //коэффициенты для масштабирования и сдвиги для позиционирования
        double koef = computeRatio(min, max);
        System.out.println(koef);

        for (int i = 0; i < y.length; i++) {
            x[i] *= 33;
            x[i] += startPointXoY.width;
            y[i] = (int)(y[i] * koef); //ok
            y[i] = startPointXoY.height - y[i]; //ok для обращения координат
            y[i] = y[i] + (int)(min*koef); //ok для смещения вниз    y[9] - min
            System.out.println(x[i] + " " + y[i]);
        }
    }

    public int[] findMinMax(int[] array) {
        int[] result = new int[2];
        int max = array[0], min = array[0]; // поиск min, max
        for (int aY : array) {
            if (max < aY) max = aY;
            if (min > aY) min = aY;
        }
        result[0] = min;
        result[1] = max;
        return result;
    }

    public double computeRatio(int min, int max) {
        int delta = max - min;
        int hightGraph = (startPointXoY.height - (startPointXoY.width - 5)) - (startPointXoY.height - (startPointXoY.width - 5) * 5);
        double percent = 100 * delta / hightGraph;
        return 100 / percent;
    }

    private void initInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(size);
//        setResizable(false);
        setResizable(true);
        setTitle("График функции");
        setVisible(true);
    }
}