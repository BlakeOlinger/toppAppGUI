package com.practice;

import javax.swing.*;
import java.awt.event.ActionListener;

class MenuButton extends JButton {
    private MenuButton(String label) {
        super(label);
    }

    static MenuButton getInstance(String label, ActionListener action) {
        var button = new MenuButton(label);
        button.addActionListener(action);

        return button;
    }
}
