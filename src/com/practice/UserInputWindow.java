package com.practice;

import javax.swing.*;
import java.awt.*;

class UserInputWindow extends JFrame {

    private UserInputWindow(String label) {super(label);}

    static void createWindow() {
        UserInputDO.semaphore = true;

        var window = new UserInputWindow("Input Custom Configuration Values");
        window.setSize(700, 400);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(new FlowLayout());

        BlempUtil.getEquations();

        BlempUtil.getEquationSegments();

        var labels = new JLabel[BlempDO.equationList.size()];
        var textFields = new JTextField[BlempDO.equationList.size()];

        for (var i = 0; i < labels.length; ++i) {
            labels[i] = new JLabel(BlempDO.equationList
                    .get(i)
                    .split("=")[0]
                    + " = ");
            textFields[i] = new JTextField(5);
            window.add(labels[i]);
            window.add(textFields[i]);
        }

        window.setVisible(true);
    }

    static void waitFor() {
        while (UserInputDO.semaphore) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void dispose() {
        UserInputDO.semaphore = false;

        ToppFiles.writeFile("SWmicroservice.config",
                SWdaemonCommandDO.SWdaemonConfigPath, "111!");

        super.dispose();
    }
}
