package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import core.Core;

public class Window {

    private JTextArea ta;
    private File file;

    Core translator;

    public Window() {
        //Creating the frame
        JFrame frame = new JFrame("Circuit Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        //Creating the MenuBar and adding component
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("file");
        JMenu m2 = new JMenu("help");
        mb.add(m1);
        mb.add(m2);

        //Open file button
        JMenuItem m11 = new JMenuItem("Open file...");
        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                choose.setDialogTitle("Choose a circuit to translate");
                choose.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Logisim Circuit (.circ)", "circ", "h");
                choose.addChoosableFileFilter(filter);
                int res = choose.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    file = choose.getSelectedFile();
                    try {
                        translator = new Core(file);
                    } catch (Exception e1) {
                        @SuppressWarnings("unused")
                        OpenFileJDialog d = new OpenFileJDialog();
                    }
                    System.out.println(file.getAbsolutePath());
                }
            }
        });

        //Save as button
        JMenuItem m12 = new JMenuItem("Save as...");
        m1.add(m11);
        m1.add(m12);

        //Text Area at the center
        ta = new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);

        //Creating the panel and adding component
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Press the button to convert");
        JButton converButton = new JButton("Convert");
        //Display the converted file
        converButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta.setText(translator.toString());
            }
        });
        panel.add(label);
        panel.add(converButton);



        //Adding Components to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }

    public File getFile() {
        return this.file;
    }
}
