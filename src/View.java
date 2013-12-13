import apple.laf.JRSUIConstants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class View extends JFrame {

    private JTextArea tArea = new JTextArea();
    private JTextField tAreaResent = new JTextField();
    private JLabel lTimer = new JLabel("Timer:");
    private JLabel lProgress = new JLabel("Progress: ");
    private JButton bStart = new JButton("Старт");
    private static String keyTyped = "";

//    private Stamina stamina = new Stamina();
    final String[] lessons_names = LessonsJSON.getLessonsNames();


    public void createUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(700, 100));
        setLocation(150, 100);
        setResizable(false);
        setName("iStamina");
        setTitle("iStamina (by Burkova A. S.)");
//        setBackground(Color.decode("#fec8ff"));

        getContentPane();
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //text tAreaResent
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        tAreaResent.setBorder(BorderFactory.createEmptyBorder());
        tAreaResent.setHorizontalAlignment(JTextField.RIGHT);
        tAreaResent.setBackground(Color.decode("#d2d2d2"));
        tAreaResent.setFocusable(false);
        tAreaResent.setEditable(false);
        tAreaResent.setPreferredSize(new Dimension(300, 16));
        add(tAreaResent, c);


        //text tArea
        c.insets = new Insets(10, 0, 10, 10);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        tArea.setDisabledTextColor(Color.red);
        tArea.setEditable(false);
        tArea.setCaretPosition(0);
        tArea.setPreferredSize(new Dimension(300, 16));
        tArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER){
//                    tArea.setText("");
//                }
                keyTyped = e.getKeyText(e.getKeyCode()).toLowerCase();
                if (Stamina.strCurrent.length() == 0) endOfLesson();
                if (Stamina.keyChecker(keyTyped)) {
                    setResentText(Stamina.updateResent());
                    setActualText(Stamina.updateActual());
                }
                System.out.println(keyTyped);
            }
        });
        add(tArea, c);


        //button bStart
        //bStart.addActionListener(clientListener);
        bStart.setEnabled(true);
        bStart.setFocusable(false);
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(bStart, c);

        //label lTimer
        c.insets = new Insets(0, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(lTimer, c);

//        http://docs.oracle.com/javase/7/docs/api/java/awt/MenuShortcut.html
        MenuBar menuBar = new MenuBar();
        Menu menuLanguages = new Menu("Language");
        MenuItem menuLanguagesItem_EN = new MenuItem("⟹ English");
        MenuItem menuLanguagesItem_RU = new MenuItem("     Russian");
        menuLanguages.add(menuLanguagesItem_EN);
        menuLanguages.add(menuLanguagesItem_RU);
        menuBar.add(menuLanguages);

        Menu menuLessons = new Menu("Lesson");
        MenuItem menuItemLessonOpen = new MenuItem("Open");

        for (int i = 0; i < lessons_names.length; i++) {
//            menuLessons.add(new MenuItem("⟹ " + lessons_names[i]));
            final int perem = i;
            menuLessons.add(new MenuItem(lessons_names[i])).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setResentText(Stamina.updateResent(""));
                    setActualText(Stamina.updateActual(LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[perem])));

                    setTitle(lessons_names[perem]);

                }
            });
        }
        menuLessons.addSeparator();
        menuLessons.add(menuItemLessonOpen);
        menuBar.add(menuLessons);


        setMenuBar(menuBar);
        menuItemLessonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String newLesson = LessonsJSON.read(openFileDialog());
//                System.out.println(newLesson);
            }
        });

        pack();
        setVisible(true);
    }


    public String openFileDialog() {
        String path = null;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        chooser.setDialogTitle("Выберите текстовый файл");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(txtFilter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION || !path.equals("")) {
            path = ""+chooser.getSelectedFile();
        } else if (chooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(new JFrame(), "No Selection!", "", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return path;
    }


    public void endOfLesson() {
        Object[] options = {"Да!", "Потом..."};
        int n = JOptionPane.showOptionDialog(null, "Оличек - молодец, давай ещё?!", "урок закончен", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (n==1) System.exit(0);
        else {
            Stamina.lessonID++;
            setResentText(Stamina.updateResent(""));
            setActualText(Stamina.updateActual(LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[Stamina.lessonID])));

        }
    }


    public void setActualText(String actualText) {
        tArea.setText(actualText);
        tArea.setCaretPosition(0);
    }


    public void setResentText(String resentText) {
        tAreaResent.setHorizontalAlignment(JTextField.RIGHT);
        tAreaResent.setText(resentText);
        tAreaResent.setHorizontalAlignment(JTextField.RIGHT);
    }

}
