import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {

    private JTextArea tArea = new JTextArea();
    private JLabel lTimer = new JLabel("Timer:");
//    private JLabel lProgress = new JLabel("Progress: ");
    private char keyTyped;

    private Stamina stamina = new Stamina();
    final String[] lessons_names = LessonsJSON.getLessonsNames();


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
                keyTyped = e.getKeyChar();
                if (stamina.strCurrent.length() == 25) endOfLesson();
                if (stamina.keyChecker(keyTyped)) {
                    setActualText(stamina.updateActual());
                }
                System.out.println(keyTyped);
            }
        });
        add(tArea, c);

        setActualText(stamina.updateActual(LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[stamina.setLessonID(0)])));
        tArea.select(0, 25);
        tArea.grabFocus();
        setTitle(lessons_names[0]);

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
            final int lessonIDforMenu = i;
            menuLessons.add(new MenuItem(lessons_names[i])).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setActualText(stamina.updateActual(LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[lessonIDforMenu])));
                    setTitle(lessons_names[lessonIDforMenu]);
                    stamina.setLessonID(lessonIDforMenu);
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
                newLesson = newLesson.replace("\n", "");
                setActualText(stamina.updateActual(newLesson));
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

        chooser.setDialogTitle("Select text file");
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
        else setActualText(stamina.updateActual(LessonsJSON.getLesson(LessonsJSON.getLessonsNames()[stamina.getNextLessonID()])));
    }


    public void setActualText(String actualText) {
        tArea.setText(actualText);
        tArea.select(0, 25);
        tArea.grabFocus();
    }

}
