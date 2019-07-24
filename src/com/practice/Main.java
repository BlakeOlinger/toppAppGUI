package com.practice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main extends Frame implements ActionListener {
    private final TextField textField;
    static final String userRoot = "C:/Users/bolinger/Desktop/test install/";
    private String message = "";

    private Main() {
            setLayout(new FlowLayout());

            var label = new Label("Cover Diameter: ");

            add(label);

            textField = new TextField(8);

            textField.addActionListener(this);

            add(textField);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("TOPP App GUI - Exit");

                    System.exit(0);
                }
            });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(message, 50, 200);
    }

    void onAppKill() {
        /*
        new BlempCleanUp().invoke();

        if (!Config.isUpdate)
            MasterKillCommand.kill();

        Config.programState = "1";

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
    }

    public static void main(String[] args) {
        System.out.println("TOPP App GUI - Start");

        var installRoot = "C:\\Users\\bolinger\\Desktop\\test install\\";

        var configFileName = "GUI.config";

        var configPath = Paths.get(installRoot + configFileName);

        System.out.println(" - GUI Config - Check Installed");

        if (Files.exists(configPath)) {
            System.out.println(" - GUI Config - File Found");
        } else {
            System.out.println(" - WARNING - GUI Config - File Not Found");

            try {
                Files.createFile(configPath);
            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("TOPP App GUI - Exit");

                return;
            }

            System.out.println(" - GUI Config - File Created");
        }

        System.out.println(" - GUI Config - Initializing");

        try {
            Files.writeString(configPath, "00");

            System.out.println(" - GUI Config - Initialized");
        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("TOPP App GUI - Exit");

            return;
        }

        var blempFileName = "C-HSSX.blemp";

        var blempPath = Paths.get(installRoot + blempFileName);

        System.out.println(" - Reading Blemp File");

        if (Files.exists(blempPath)) {
            System.out.println(" - Blemp File Found");

            try {
                var equations = Files.readString(blempPath)
                        .split("!");

                System.out.println((" - Blemp File Equations - Count: " + equations.length));

                System.out.println(" - Blemp File Equations:");

                for(String equation: equations)
                    System.out.println(equation);

                // the last index of a given equation segment array is used to
                // hold the index information of the significant value -
                // - this is the value appended by an @ symbol and this
                // is the value that will change based on user input

                System.out.println(" - Getting Equation Segments");

                var equationSegments = new ArrayList<String[]>();

                for (String equation : equations) {
                    equationSegments.add(equation.split("\\$"));
                }

                System.out.println(" - Displaying Equation Segments:");

                for(var i = 0; i < equations.length; ++i) {
                    for(var j = 0; j < equationSegments.get(i).length; ++j) {
                        System.out.println(equationSegments.get(i)[j]);
                    }
                }

                System.out.println(" - Equation Segments with Significant Value Appended:");

                var appendedEquationSegments = new ArrayList<String[]>();

                for(var i = 0; i < equations.length; ++i) {
                    var appendedSegmentSize = equationSegments.get(i).length + 1;

                    var tempArray = new String[appendedSegmentSize];

                    var significantValueIndex = "";

                    for(var j = 0; j < appendedSegmentSize; ++j) {

                        if (j < appendedSegmentSize - 1) {
                            var copyValue = equationSegments.get(i)[j];

                            if (copyValue.contains("@")) {
                                significantValueIndex = String.valueOf(j);
                            }

                            tempArray[j] = copyValue;
                        } else {
                            tempArray[j] = significantValueIndex;
                        }
                    }

                    appendedEquationSegments.add(tempArray);
                }

                for(var i = 0; i < equations.length; ++i) {
                    for(var j = 0; j < appendedEquationSegments.get(i).length; ++j) {
                        System.out.println(appendedEquationSegments.get(i)[j]);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("TOPP App GUI - Exit");

                return;
            }

        } else {
            System.out.println(" - ERROR - No Blemp File Found");

            System.out.println("TOPP App GUI - Exit");

            return;
        }

        var appWindow = new Main();

        appWindow.setTitle("TOPP App");
        appWindow.setSize(800, 600);
        appWindow.setVisible(true);

//        startDaemon();

//        Config.main.onAppKill();
    }

    private static void startDaemon() {
        var daemon = new GUIDaemon();

        daemon.start();

        daemon.join();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        var userInput = textField.getText();

        System.out.println(" - User Entered - " + userInput);

        if (Pattern.matches("[0-9\\-.]+", userInput)) {
            if (Math.abs(Double.valueOf(userInput)) > 300) {
                message = " * Value must be between +- 300";

                repaint();
            } else {
                message = "";

                repaint();
            }
        } else {
            message = " * Input Requires Numbers Only";

            repaint();
        }
    }

}
