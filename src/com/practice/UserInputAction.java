package com.practice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class UserInputAction implements ActionListener {
    private final JLabel outputLabel;
    private final JTextField textField;

    UserInputAction(JLabel outputLabel, JTextField textField) {
        this.outputLabel = outputLabel;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BlempDO.userInputValue = textField.getText();

        outputLabel.setText(" current: " + BlempDO.userInputValue);

        textField.setText("");

        BlempUtil.populateCurrentConfiguration();

        SWcommand.writeCurrentEquationsToDDTO();

        SWcommand.submitCommandOnBlob(SWaction.ACTION_SENT);
    }
}
