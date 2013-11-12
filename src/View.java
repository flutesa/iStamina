import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public final class View extends JFrame {

//    public JTextArea tArea = new JTextArea("", 0, 50);
//    public JTextArea tAreaResent = new JTextArea("", 0, 50);
//    public JTextArea tArea = new JTextArea("", 0, 50);
//    public JTextArea tAreaResent = new JTextArea("", 0, 50);
//    public JTextPane tArea = new JTextPane();          //не нашла где размеры задавать, но ок
//    public JTextPane tAreaResent = new JTextPane();
    public JTextField tArea = new JTextField("", 50);
    public JTextField tAreaResent = new JTextField("", 50);
    private JLabel lTimer = new JLabel("Timer:");
    private JLabel lProgress = new JLabel("Progress: ");
    private JButton bOpenLesson = new JButton("Open Lesson");
    private static String keyTyped = "";


    public void createUI() {
        bOpenLesson.setEnabled(true);
        bOpenLesson.setFocusable(false);
        Container cp = getContentPane();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        cp.setLayout(gbl);
        setTitle("iStamina (by Burkova A. S.)");
        setName("iStamina");
//        cp.setBackground(Color.decode("#fec8ff"));
//        setSize(640, 480);
        //setLocation(150, 100);
        setResizable(false);
//        cp.setBackground(Color.LIGHT_GRAY);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //text tAreaResent
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(4, 4, 4, 0);
        gbc.gridwidth = 1;
        tAreaResent.setBackground(Color.decode("#d2d2d2"));
//        tAreaResent.setBackground(Color.decode("#bf8fec"));
//        tAreaResent.setAlignmentX(Component.LEFT_ALIGNMENT);
//        tAreaResent.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        tAreaResent.setHorizontalAlignment(JTextField.RIGHT);  //не нашла поля выравнивания в JTextArea
        tAreaResent.setSize(new Dimension(350, 300));
        tAreaResent.setFocusable(false);
        tAreaResent.setEditable(false);
        cp.add(tAreaResent, gbc);

        //text tArea
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(4, -1, 4, 0);
        gbc.gridwidth = 1;
//        tArea.setLineWrap(true); //не нужно
        tArea.setSize(new Dimension(350, 300));
        tArea.setEditable(false);
        tArea.setCaretPosition(0);
        tArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    tArea.setText("");
                }
                keyTyped = e.getKeyText(e.getKeyCode()).toLowerCase();
                tAreaResent.setText(Stamina.updateResent(keyTyped));
                tArea.setText(Stamina.updateActual(keyTyped));
                tArea.setCaretPosition(0);
                System.out.println(keyTyped);
            }
        });
        cp.add(tArea, gbc);

        //button find
        //bOpenLesson.addActionListener(clientListener);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cp.add(bOpenLesson, gbc);

        //label lTimer
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cp.add(lTimer, gbc);

//        http://docs.oracle.com/javase/7/docs/api/java/awt/MenuShortcut.html
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuLesson = new Menu("Lesson");
        MenuItem menuFileItem = new MenuItem("Open");
        MenuItem menuLessonsItem = new MenuItem("En");
        menuFile.add(menuFileItem);
        menuLesson.add(menuLessonsItem);
        menuBar.add(menuFile);
        menuBar.add(menuLesson);
        setMenuBar(menuBar);
        menuFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                String fileName = OpenFileDialog();
                //read();
            }
        });

        pack();
        setVisible(true);
    }


    public static String OpenFileDialog() {
        String path = null;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Only Text Files", "txt");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        chooser.setDialogTitle("Выберите текстовый файл");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(txtFilter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION || !path.equals("")) {
            path = "" + chooser.getSelectedFile();
        }

        if (JFileChooser.CANCEL_OPTION == 1) {
            JOptionPane.showMessageDialog(new JFrame(), "No Selection!", "", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return path;
    }


    public void setActualText(String actualText) { //прикрутить дробилку текста, или использовать JTextField
        tArea.setCaretPosition(0);
        tArea.setText(actualText);
    }


    public void setResentText(String resentText) {
        tAreaResent.setText(resentText);
    }

}
