package com.practice;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

final class BlempSelectionAction implements ListSelectionListener {
    private final JList<String> nameList;
    private String blempName;
    private final JFrame window;
    private JLabel label;

    BlempSelectionAction(JList<String> nameList, String blempName, JFrame window, JLabel label) {
        this.nameList = nameList;
        this.blempName = blempName;
        this.window = window;
        this.label = label;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        blempName = nameList.getSelectedValue();

        label.setText("You selected: " + blempName + ". Use this template?");
    }
}
