import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileListerGUI extends JFrame implements ActionListener {

    private JButton startButton, quitButton;
    private JLabel titleLabel;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public FileListerGUI() {
        super("File Lister");

        // create components
        startButton = new JButton("Select Directory");
        quitButton = new JButton("Quit");
        titleLabel = new JLabel("File Lister");
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);

        // set layout
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(titleLabel);
        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(startButton);
        southPanel.add(quitButton);
        add(southPanel, BorderLayout.SOUTH);

        // add listeners
        startButton.addActionListener(this);
        quitButton.addActionListener(this);

        // set window properties
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File directory = chooser.getSelectedFile();
                listFiles(directory);
            }
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }

    private void listFiles(File directory) {
        textArea.setText("");
        listFilesHelper(directory, "");
    }

    private void listFilesHelper(File directory, String indent) {
        File[] files = directory.listFiles();
        for (File file : files) {
            textArea.append(indent + file.getName() + "\n");
            if (file.isDirectory()) {
                listFilesHelper(file, indent + "  ");
            }
        }
    }

    public static void main(String[] args) {
        new FileListerGUI();
    }
}
