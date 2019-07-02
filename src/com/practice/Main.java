package com.practice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Main extends Frame implements ActionListener {
    private final TextField textField;

    private Main() {
        setLayout(new FlowLayout());

        var label = new Label("Cover Diameter: ");

        add(label);

        textField = new TextField(8);

        textField.addActionListener(this);

        add(textField);

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                new BlempCleanUp().clearDDO();

                MasterKillCommand.kill();

                Config.programState = "1";

                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public static void main(String[] args) {
        loadBlempConfig();

        // Doesn't actually do anything ATM
        // Except monitor and keep up to date
        // the program running state
        startDaemon();

        startAppWindow();

    }

    private static void startAppWindow() {
        var appWindow = new Main();

        appWindow.setTitle("TOPP App");
        appWindow.setSize(800, 600);
        appWindow.setVisible(true);
    }

    private static void startDaemon() {
        new GUIDaemon().start();
    }

    private static void loadBlempConfig() {
        new BlempConfig().load();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Blemp(textField.getText()).start();
    }
}
