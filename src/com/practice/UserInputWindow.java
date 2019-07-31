package com.practice;

import javax.swing.*;
import java.awt.*;

class UserInputWindow extends JFrame {

    private UserInputWindow(String label) {super(label);}

    static void createWindow() {
        var window = new UserInputWindow("Input Custom Configuration Values");
        window.setSize(270, 200);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(new FlowLayout());

        BlempUtil.getEquations();

        BlempUtil.getEquationSegments();

        var labels = new JLabel[BlempDO.equationList.size()];
        var textFields = new JTextField[BlempDO.equationList.size()];
        var currentValueLabels = new JLabel[BlempDO.equationList.size()];
        var userActions = new UserInputAction[BlempDO.equationList.size()];

        for (var i = 0; i < labels.length; ++i) {
            labels[i] = new JLabel(BlempDO.equationList
                    .get(i)
                    .split("=")[0]
                    + " = ");
            textFields[i] = new JTextField(5);
            currentValueLabels[i] = new JLabel(
                    " current: " +
                    BlempDO.equationSegmentsList.get(i) [
                            Integer.parseInt(BlempDO.equationSegmentsList.get(i)[
                            BlempDO.equationSegmentsList.get(i).length - 1
                            ])]
                            .replace("#", "")
            );
            userActions[i] = new UserInputAction(currentValueLabels[i],
                    textFields[i]);
            textFields[i].addActionListener(userActions[i]);
            window.add(labels[i]);
            window.add(textFields[i]);
            window.add(currentValueLabels[i]);
        }

        window.setVisible(true);
    }
}
