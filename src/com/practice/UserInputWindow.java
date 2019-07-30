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
