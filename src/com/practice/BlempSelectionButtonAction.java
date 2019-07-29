package com.practice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class BlempSelectionButtonAction implements ActionListener {
    private final JFrame window;

    BlempSelectionButtonAction(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        BlempSelectionDO.userBlempSelectionSemaphore = false;

        window.dispose();
    }
}
