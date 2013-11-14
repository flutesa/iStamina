import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class View extends JFrame {

    private JTextArea tArea = new JTextArea();
    private JTextPane tAreaResent = new JTextPane();
    private JLabel lTimer = new JLabel("Timer:");
    private JLabel lProgress = new JLabel("Progress: ");
    private JButton bStart = new JButton("Старт");
    private static String keyTyped = "";

    private Stamina stamina = new Stamina();


    public void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // выравнивание по правой стороне для tAreaResent
        String es = tAreaResent.getText();
        StyleContext ct = new StyleContext();
        StyledDocument dc = new DefaultStyledDocument(ct);

        Style style = ct.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
//        StyleConstants.setBackground(style, Color.decode("#fec8ff")); //цветной фон букв
        StyleConstants.setFirstLineIndent(style, 0);
        try {
            dc.insertString(dc.getLength(), es, style);
//            dc.setCharacterAttributes(0, 5, style, false); //цветной фон букв
        } catch (BadLocationException badLocationException) {
            System.err.println("Exception:" + badLocationException);
        }
        tAreaResent.setStyledDocument(dc);


        //text tAreaResent
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
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
        tArea.setEditable(false);
        tArea.setCaretPosition(0);
        tArea.setPreferredSize(new Dimension(300, 16));
        tArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    tArea.setText("");
                }
                keyTyped = e.getKeyText(e.getKeyCode()).toLowerCase();
                if (stamina.keyChecker(keyTyped)) {
                    setResentText(stamina.updateResent());
                    setActualText(stamina.updateActual());
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
                String newLesson = read(openFileDialog());
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


    public String read(String fileName) { // читаем данные из файла
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(
                    fileName).getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }


    public void setActualText(String actualText) {
        tArea.setCaretPosition(0);
        tArea.setText(actualText);
    }


    public void setResentText(String resentText) {
        tAreaResent.setText(resentText);
    }

}
