import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Graphic {

    public static void main(String[] args) {
        Integer[] y = {360, 360, 360, 300, 300, 240, 240, 240, 240, 240, 240, 240, 240, 240, 180, 180, 180, 180, 180, 180, 180, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//  183?
//        Integer[] y = {300, 180, 240, 180, 180, 240, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 180, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 180, 180, 180, 180, 120, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120};
//  147
//        Integer[] y = {360, 330, 300, 285, 288, 270, 274, 255, 260, 258, 245, 230, 231, 223, 224, 221, 226, 230, 234, 237, 240, 243, 243, 243, 240, 240, 240, 238, 234, 234, 230, 231, 227, 226, 226, 227, 229, 227, 229, 230, 228, 227, 226, 224, 223, 219, 214, 211, 209, 205, 205, 207, 207, 207, 204, 204, 202, 201, 199, 200, 198, 197, 196, 198, 199, 197, 197, 198, 199, 201, 202, 203, 204, 202, 202, 201, 200, 199, 200, 201, 200, 198, 199, 199, 199, 199, 199, 199, 200, 199, 200, 200, 200, 199, 198, 197, 196, 196, 195, 194, 194, 192, 191, 191, 190, 189, 188, 188, 187, 185, 185, 185, 185, 184, 183, 183, 182, 180, 179, 179, 179, 178, 177, 176, 175, 174, 174, 173, 173, 172, 172, 171, 170, 170, 169, 169, 169, 168, 168, 167, 167, 166, 165, 165, 164, 164, 163};
        new Dr(y);
    }
}

class Draw extends javax.swing.JFrame {

    int getTime;

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

    public Draw(Integer[] speedsArray) {
        getTime = speedsArray.length;
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
//        g.drawLine(startPointXoY.width,Statistics.getAverageSpeed(),startPointXoY.width + x[8]-8,Statistics.getAverageSpeed());      //ось X нижняя


//        g.drawLine(startPointXoY.width,startPointXoY.height,size.width-20,startPointXoY.height);      //ось X нижняя
        g.setColor(Color.RED);
        g.drawLine(startPointXoY.width,206,startPointXoY.width + x[8]-8,206);      //средняя скорость

        g.drawPolyline(x,y,10);
    }

    private void buildArrays(Integer[] sp) {
//        int timeInterval = Statistics.getTime() / sizeX;
        int timeInterval = getTime / sizeX;

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


        System.out.println(max + " " + min);
        double koeff = 140.0/max;   //140 - координата максимума по Y
        System.out.println(koeff);

        //коэффициенты для корректного масштабирования
        for (int i = 0; i < x.length; i++) {
//            System.out.println(x[i] + " " + y[i]);
            x[i] *= 33;                                  //ok
            x[i] += startPointXoY.width;                 //ok
//            y[i] = (int)(1.35 * y[i]);                 //ok for 183
//            y[i] = (int)(1.13 * y[i]);

            y[i] = (int)(y[i] * koeff); //ok
            y[i] = startPointXoY.height - y[i] + y[9]; //ok для смещения вниз    y[9] - min
//            y[i] = startPointXoY.height - y[i] + findMinMax(y)[0]; //ok для смещения вниз
            System.out.println(x[i] + " " + y[i]);
        }
//        System.out.println(startPointXoY.height - (startPointXoY.width - 5) * 4);
//        System.out.println(startPointXoY.height - 80);
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

    private void initInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(size);
//        setResizable(false);
        setResizable(true);
        setTitle("График функции");
        setVisible(true);
    }
}

