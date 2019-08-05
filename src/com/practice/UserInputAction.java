package com.practice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class UserInputAction implements ActionListener {
    private final JLabel outputLabel;
    private final JTextField textField;
    private final int index;

    UserInputAction(JLabel outputLabel, JTextField textField, int index) {
        this.outputLabel = outputLabel;
        this.textField = textField;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BlempDO.userInputValue = textField.getText();

        System.out.println(textField.getText());

        System.out.println(index);

        textField.setText("");

        outputLabel.setText(" current: " + BlempDO.userInputValue);

        BlempUtil.populateCurrentConfiguration(index);

        System.out.println(BlempDO.currentEquationBuffer);

//        SWcommand.writeCurrentEquationsToDDTO();

//        SWcommand.submitCommandOnBlob(Commands.SWDaemon.USER_ACTION_SET);

    }
}
