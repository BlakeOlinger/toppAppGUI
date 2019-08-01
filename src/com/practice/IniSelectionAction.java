package com.practice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class IniSelectionAction implements ActionListener {
    private final JCheckBox checkBox;
    private final int index;

    IniSelectionAction(JCheckBox checkBox, int index) {
        this.checkBox = checkBox;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkBox.isSelected() != IniFileDO.getFieldValue(
                IniFileDO.keys[index]))
            IniFileDO.setField(IniFileDO.keys[index], checkBox.isSelected());
    }
}
