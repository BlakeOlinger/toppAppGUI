package com.practice;

/*
Initial menu window - functionally similar to a video game main menu
 */

import javax.swing.*;
import java.awt.*;

final class TopLevelMenu extends JFrame {
    private static boolean semaphore = false;

    private TopLevelMenu(String title) {
        super(title);
    }

    static void createWindow() {
        semaphore = true;

        var window = new TopLevelMenu("TOPP App");
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(170, 190);

        window.add(
                MenuButton.getInstance(
                        "Create",
                        e -> System.out.println("Create Selected")
                )
        );

        window.add(
                MenuButton.getInstance(
                        "Configure",
                        e -> System.out.println("Configure Selected")
                )
        );

        window.add(
                MenuButton.getInstance(
                        "Test",
                        e -> SwingUtilities.invokeLater(BlempSelectionWindow::createWindow)
                )
        );

        window.add(
                MenuButton.getInstance(
                        "Close SolidWorks Part",
                        e -> SWcommand.submitCommand(SWaction.CLOSE_SW_PART)
                )
        );

        window.add(
                MenuButton.getInstance(
                        "Exit SolidWorks Daemon",
                        e -> SWcommand.submitCommand(SWaction.EXIT_SW_DAEMON)
                )
        );

        window.add(
                MenuButton.getInstance(
                        "Settings",
                        e -> SwingUtilities.invokeLater(UserSettingsWindow::createWindow)
                )
        );

        window.setVisible(true);

    }

    static void waitFor() {
        while (semaphore) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dispose() {
        semaphore = false;

        super.dispose();
    }
}
