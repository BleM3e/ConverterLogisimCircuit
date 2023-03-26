package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OpenFileJDialog extends JDialog implements ActionListener {
    JButton button;

    public OpenFileJDialog() {
        this.setTitle("Cannot open file");
        button = new JButton("Ok");
        button.addActionListener(this);
        add(button);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
