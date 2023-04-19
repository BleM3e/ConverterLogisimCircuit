package gui;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import core.Core;
import translator.Translator;

public class Window {

    private JTextArea ta;
    private File file;

    Core core;
    Translator translator;

    public Window() {
        //Creating the frame
        JFrame frame = new JFrame("Circuit core");
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
                        core = new Core(file);
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
        converButton.setEnabled(false);
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton isArduinoRadioButton = new JRadioButton("Is Arduino");
        JRadioButton isLegacyRadioButton = new JRadioButton("Is Legacy Project");
        buttonGroup.add(isArduinoRadioButton);
        buttonGroup.add(isLegacyRadioButton);
        // isArduinoRadioButton.addItemListener();

        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                converButton.setEnabled(true);
            }
        };

        //Display the converted file
        converButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (translator != null) translator = null;
                    translator = new Translator(file, isArduinoRadioButton.isSelected());
                } catch (Exception e1) {
                    @SuppressWarnings("unused")
                    OpenFileJDialog d = new OpenFileJDialog();
                }
                String text = translator.toString();
                ta.setText(text);
                StringSelection stringSelection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        isArduinoRadioButton.addActionListener(radioButtonListener);
        isLegacyRadioButton.addActionListener(radioButtonListener);

        panel.add(label);
        panel.add(converButton);
        panel.add(isArduinoRadioButton);
        panel.add(isLegacyRadioButton);



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
