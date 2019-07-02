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

        var confirmButton = new Button("Modify");

        confirmButton.addActionListener(this);

        add(confirmButton);

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                BlempCleanUp.clearDDO();
                MasterKillCommand.kill();
                Config.programState = "1";
                System.out.println(" Ending GUI Microservice...");
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public static void main(String[] args) {
        // rename load blemp.config();
        new Config().start();
        new GUIDaemon().start();

        var appWindow = new Main();

        appWindow.setTitle("TOPP App");
        appWindow.setSize(800, 600);
        appWindow.setVisible(true);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        var userInput = textField.getText();
        new Blemp(userInput).start();
    }
}
