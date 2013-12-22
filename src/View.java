import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;


public class View extends JFrame {

    private JTextArea tArea = new JTextArea();
    private JLabel lTimer = new JLabel("Timer:");
    //    private JLabel lProgress = new JLabel("Progress: ");
    private MenuBar menuBar = new MenuBar();
    private Menu menuLanguages = new Menu("Language"); //maybe "Options"
    private CheckboxMenuItem menuLanguagesItem_EN = new CheckboxMenuItem("English", true);
    private CheckboxMenuItem menuLanguagesItem_RU = new CheckboxMenuItem("Russian", false);
    private Menu menuLessons = new Menu("Lesson");
    private MenuItem menuItemLessonOpen = new MenuItem("Open", new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('o'), false));
    private Menu menuHelp = new Menu("?");
    private MenuItem menuHelpItemUsers = new MenuItem("Users", new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('u'), false));
    private MenuItem menuHelpItemStatistics = new MenuItem("Statistics", new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('s'), false));
    private MenuItem menuHelpItemAbout = new MenuItem("About", new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('a'), false));

    private JToolTip n = new JToolTip();

    private Stamina stamina = new Stamina();
    private Statistics stat = new Statistics();
    private char keyTyped;


    public View() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 150));
        setLocation(150, 150);
        setResizable(false);
        setName("iStamina");
        setTitle("iStamina (by Burkova A. S.)");
//        setBackground(Color.decode("#fec8ff"));

        getContentPane();
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;


        //text tArea
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        tArea.setSelectionColor(Color.decode("#d2d2d2"));
        tArea.setEditable(false);
        tArea.setCaretPosition(0);
        tArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        tArea.setSelectedTextColor(Color.decode("#505050"));
//        tArea.setBackground(Color.decode("#fec8ff"));
        tArea.setPreferredSize(new Dimension(850, 33));
        tArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER){
//                    tArea.setText("");
//                }
                if (e.getKeyCode() == 39) updateViewWithNewLesson(stamina.getNextLessonID());
                if (e.getKeyCode() == 37) updateViewWithNewLesson(stamina.getPreviousLessonID());
                if (stamina.getCurrentLessonLength() == 0) {
                    stat.stopTimer();
                    endOfLessonDialog();
                }
                keyTyped = e.getKeyChar();
                if (stamina.keyChecker(keyTyped)) setActualText(stamina.updateActual());
                else stat.incMistake();
                if (stamina.getCurrentLessonLength()+1 == stamina.getSourceLessonLength()) stat.startTimer();

                System.out.println(keyTyped);
            }
        });
        tArea.setToolTipText("asdf");
        add(tArea, c);

        updateViewWithNewLesson(0);
        tArea.select(0, 25);
        tArea.grabFocus();

        tArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tArea.select(0, 25);
                tArea.grabFocus();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                tArea.select(0, 25);
                tArea.grabFocus();
            }
        });
        tArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                tArea.select(0, 25);
                tArea.grabFocus();
            }
        });


        //label lTimer
        c.insets = new Insets(0, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(lTimer, c);


        menuLanguages.add(menuLanguagesItem_EN);
        menuLanguages.add(menuLanguagesItem_RU);
        menuBar.add(menuLanguages);

        menuHelp.add(menuHelpItemUsers);
        menuHelp.add(menuHelpItemStatistics);
        menuHelp.addSeparator();
        menuHelp.add(menuHelpItemAbout);

        menuItemLessonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String path = openFileDialog();
                if (!path.equals("")) {
                    String newLesson = LessonsJSON.read(path);
                    newLesson = newLesson.replace("\n", "");
                    setActualText(stamina.updateActualAndResent(newLesson));
                    setTitle("iStamina (by Burkova A. S.)");
                }
            }
        });

        menuLanguagesItem_EN.setShortcut(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('e'), false));
        menuLanguagesItem_RU.setShortcut(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('r'), false));
        menuLanguagesItem_EN.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateViewWithAnotherLanguage('e');
            }
        });
        menuLanguagesItem_RU.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateViewWithAnotherLanguage('r');
            }
        });

        repaintMenuLessons();
        setMenuBar(menuBar);

        pack();
        setVisible(true);
    }


    public String openFileDialog() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setDialogTitle("Select text file");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(txtFilter);
        return (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) ? "" + chooser.getSelectedFile() : "";
    }


    public void endOfLessonDialog() {
        Object[] options = {"Да!", "Потом..."};
        int n = JOptionPane.showOptionDialog(null, "ошибок: " + stat.getMistake()
                + " штук\nошибок: " + stat.getMistakePercentage() + "%\nвремя: " + stat.getTime() + " секунд\nскорость: " + stat.getAverageSpeed() + " зн/мин", "урок закончен", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


        if (n == 1) System.exit(0);
        else updateViewWithNewLesson(stamina.getNextLessonID());


    }


    public void setActualText(String actualText) {
        tArea.setText(actualText);
        tArea.select(0, 25);
        tArea.grabFocus();
    }


    public void updateViewWithAnotherLanguage(char l) {
        if (l == 'e') {
            LessonsJSON.setLanguage("src/lessons_en.json");
            menuLanguagesItem_EN.setState(true);
            menuLanguagesItem_RU.setState(false);
        }
        if (l == 'r') {
            LessonsJSON.setLanguage("src/lessons_ru.json");
            menuLanguagesItem_EN.setState(false);
            menuLanguagesItem_RU.setState(true);
        }
        repaintMenuLessons();
        updateViewWithNewLesson(0);
    }


    public void updateViewWithNewLesson(int lessonID) {
        setActualText(stamina.updateActualAndResent(LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[stamina.setLessonID(lessonID)])));
        setTitle(LessonsJSON.getLessonsNames()[lessonID]);
        stamina.setLessonID(lessonID);
        stat.resetAllStatistics();
        repaintMenuLessons();
    }


    public void repaintMenuLessons() {
        menuLessons.removeAll();
        final CheckboxMenuItem[] cbmi = new CheckboxMenuItem[LessonsJSON.getLessonsNames().length];
        for (int i = 0; i < LessonsJSON.getLessonsNames().length; i++) {
            final int lessonIDforMenu = i;
            cbmi[i] = new CheckboxMenuItem(LessonsJSON.getLessonsNames()[i]);
            if (cbmi[i].getLabel().equals(LessonsJSON.getLessonsNames()[stamina.getLessonID()])) cbmi[i].setState(true);
            else cbmi[i].setState(false);
            cbmi[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    updateViewWithNewLesson(lessonIDforMenu);
                    for (int i = 0; i < LessonsJSON.getLessonsNames().length; i++) { //обработка ручной смены урока через меню
                        if (e.getItem().equals(cbmi[i].getLabel())) continue;
                        else cbmi[i].setState(false);
                    }
                }
            });
            menuLessons.add(cbmi[i]);
        }
        menuLessons.addSeparator();
        menuLessons.add(menuItemLessonOpen);
        menuBar.add(menuLessons);
        menuBar.add(menuHelp);
    }
}
