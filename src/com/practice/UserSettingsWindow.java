package com.practice;

import javax.swing.*;
import java.awt.*;

final class UserSettingsWindow extends JFrame {
    private UserSettingsWindow(String title) {
        super(title);
    }

    static void createWindow() {
        var window = new UserSettingsWindow("User Settings");
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(300, 130);
        window.setLocation(0, 200);

        var labels = new JLabel[IniFileDO.keys.length];
        var checkBoxes = new JCheckBox[IniFileDO.keys.length];

        for (var i = 0; i < IniFileDO.keys.length; ++i) {
            labels[i] = new JLabel(IniFileDO.keys[i]);
            checkBoxes[i] = new JCheckBox();
            checkBoxes[i].setSelected(IniFileDO.getFieldValue(IniFileDO.keys[i]));

            checkBoxes[i].addActionListener(new IniSelectionAction(checkBoxes[i], i));

            window.add(labels[i]);
            window.add(checkBoxes[i]);
        }

        var label = new JLabel("Reset to Defaults:");
        var checkBox = new JCheckBox();
        // TODO - when settings are reset put checks back how they're supposed to be
        // FIXME - use a restore defaults button instead of a check box
        checkBox.addActionListener(e -> IniFileDO.setIniToDefaults());

        window.add(label);
        window.add(checkBox);

        window.setVisible(true);
    }
}
