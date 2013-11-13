import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public final class View extends JFrame {

//    public JTextArea tArea = new JTextArea();
//    public JTextArea tAreaResent = new JTextArea();

    public JTextField tArea = new JTextField();
    public JTextField tAreaResent = new JTextField();
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
//        cp.setBackground(Color.decode("#fec8ff"));
//        setSize(640, 480);
        setLocation(150, 100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //text tAreaResent
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        tAreaResent.setBackground(Color.decode("#d2d2d2"));
        tAreaResent.setHorizontalAlignment(JTextField.RIGHT);  //нет выравнивания для JTextArea
        tAreaResent.setFocusable(false);
        tAreaResent.setEditable(false);
        tAreaResent.setPreferredSize(new Dimension(550, 16));
        cp.add(tAreaResent, gbc);

        //text tArea
        gbc.insets = new Insets(4, 0, 4, 4);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        tArea.setEditable(false);
        tArea.setPreferredSize(new Dimension(550, 16));
//        tAreaResent.setMaximumSize(new Dimension(50, 10));
//        tAreaResent.setSize(50, 10);
        tArea.setCaretPosition(0);
        tArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    tArea.setText("");
                }
                keyTyped = e.getKeyText(e.getKeyCode()).toLowerCase();
                if (Stamina.keyChecker(keyTyped)) {
                    tAreaResent.setText(Stamina.updateResent());
                    tArea.setText(Stamina.updateActual());
                    tArea.setCaretPosition(0);
                }
                System.out.println(keyTyped);
            }
        });
        cp.add(tArea, gbc);

        //button find
        //bOpenLesson.addActionListener(clientListener);
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        cp.add(bOpenLesson, gbc);

        //label lTimer
//        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
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
