package com.practice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main extends Frame implements ActionListener {
    private final TextField textField;
    private static final Logger logger =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
                var blempCleanUp = new BlempCleanUp();

                blempCleanUp.clearDDO();

                blempCleanUp.join();

                MasterKillCommand.kill();

                Config.programState = "1";

                logger.log(Level.INFO, "Main Thread - GUI - Exit");

                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public static void main(String[] args) {
        logger.log(Level.INFO, "Main Thread - Start");

        loadBlempConfig();

        startDaemon();

        startAppWindow();

        logger.log(Level.INFO, "Main Thread - Exit");
    }

    private static void startAppWindow() {
        logger.log(Level.INFO, "Main Thread - GUI - Start");

        var appWindow = new Main();

        appWindow.setTitle("TOPP App");
        appWindow.setSize(800, 600);
        appWindow.setVisible(true);
    }

    private static void startDaemon() {
        var daemon = new GUIDaemon();

        daemon.start();

        daemon.join();
    }

    private static void loadBlempConfig() {
        var blempConfig = new BlempConfig();

        blempConfig.load();

        blempConfig.join();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Blemp(textField.getText()).start();
    }
}
