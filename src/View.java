import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class View extends JFrame {

    public JTextField tField = new JTextField("", 50);
    public JTextArea tArea = new JTextArea("", 1, 50);
    private JLabel lTimer = new JLabel("Timer:");
    private JLabel lProgress = new JLabel("Progress: ");
    private JButton bOpenLesson = new JButton("Open Lesson");

    public void createUI() {
        bOpenLesson.setEnabled(true);
        Container cp = getContentPane();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        cp.setLayout(gbl);
        setTitle("iStamina (by Burkova A. S.)");
        //setSize(640, 480);
        //setLocation(150, 100);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //label tField
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridwidth = 1;
        cp.add(tField, gbc);

        //text tArea
        gbc.fill = GridBagConstraints.BOTH;
        //tArea.getDocument().addDocumentListener(clientListener);
        cp.add(tArea, gbc);

        //button find
        //bOpenLesson.addActionListener(clientListener);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cp.add(bOpenLesson, gbc);

        //label lTimer
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cp.add(lTimer, gbc);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuItem = new MenuItem("Open");
        menuFile.add(menuItem);
        menuBar.add(menuFile);
        setMenuBar(menuBar);
        menuItem.addActionListener(new ActionListener() {
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
}
