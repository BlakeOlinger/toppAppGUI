package com.practice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class BlempSelectionButtonAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (SemaphoreList.is_SW_MS_preview_Open())
            SwingUtilities.invokeLater(UserInputWindow::createWindow);
    }
}
