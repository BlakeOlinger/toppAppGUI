package com.practice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Main extends Frame implements ActionListener {
    private String message = "";
    private ArrayList<String[]> DDTOequations;
    private Path DDTOpath;
    private Path configPath;
    private Path swConfig;
    private ArrayList<TextField> textFields;
    private ArrayList<Label> labels;

    private Main(ArrayList<String[]> DDTOequations, Path DDTOpath, Path configPath, Path swConfig, ArrayList<TextField> textFields, ArrayList<Label> labels) {
        this.DDTOequations = DDTOequations;
        this.DDTOpath = DDTOpath;
        this.configPath = configPath;
        this.swConfig = swConfig;
        this.textFields = textFields;
        this.labels = labels;

        setLayout(new FlowLayout());

        var index = 0;

        for(TextField textField: textFields) {
            textField.addActionListener(this);
            add(labels.get(index++));
            add(textField);
        }

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("TOPP App GUI - Exit");

                    try {
                        Files.writeString(swConfig, "11");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    System.exit(0);
                }
            });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(message, 50, 200);
    }

    // TODO - Get app to generate a dropdown list from C-HSSX.blemp
    //  - equation list - have an input be available for that selection
    //  - have the index used in the action performed function correlate
    //  - to the input selection
    public static void main(String[] args) {
        System.out.println("TOPP App GUI - Start");

        var startTime = System.currentTimeMillis();

        // FIXME - to check if installed and if not get install
        //  - location from user without having to alter code
        //  -- check for a given expected file like the config file
        //  -- in the current directory - if not then prompt user
        //  -- with file selector - directory only - set title to
        //  -- set install directory - set that as the install root
        //  -- variable
        var installRoot = InstallRoot.getInstallRoot();

        var installRootPath = Paths.get(installRoot);

        if (!ToppFiles.validateDirectory("Install", installRootPath))
            return;

        var GUIconfigFileName = "GUI.config";

        var GUIconfigPath = Paths.get(installRoot + GUIconfigFileName);

        if (!ToppFiles.validateFile(GUIconfigFileName, GUIconfigPath))
            return;

        Logger.cout(Level.INFO, GUIconfigFileName, "Initializing");
    // FIXME - initializing was redundant - add argument to parameter
        if (!ToppFiles.writeFile(
                GUIconfigFileName,
                GUIconfigPath,
                "00",
                "Initializing"))
            return;

        Logger.cout(Level.INFO, GUIconfigFileName, "Initialized");

        var DDTOfileName = "DDTO.blemp";

        var DDTOpath = Paths.get(installRoot + DDTOfileName);

        if (!ToppFiles.validateFile(DDTOfileName, DDTOpath)) return;

        Logger.cout(Level.INFO, DDTOfileName, "Initializing");

        if (!ToppFiles.writeFile(DDTOfileName, DDTOpath, ""))
            return;

        Logger.cout(Level.INFO, DDTOfileName, "Initialized");

        var blempFileName = "C-HSSX.blemp";

        var blempPath = Paths.get(installRoot + blempFileName);

        var appendedEquationSegments = new ArrayList<String[]>();

        if (!ToppFiles.validateFile(blempFileName, blempPath))
            return;

        if (Files.exists(blempPath)) {
            System.out.println(" - Blemp File Found");
        } else {
            System.out.println(" - WARNING - No Blemp File Found");

            try {

                Files.createFile(blempPath);

                if (Files.exists(blempPath)) {
                    var equations = "\"OD@sketch1\"=$40#$in$!";

                    Files.writeString(blempPath, equations);

                    System.out.println(" - Blemp File - Created and Initialized");
                }
            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("TOPP App GUI - Exit");

                return;
            }
        }

        var swConfigFileName = "SWmicroservice.config";

        var swConfig = Paths.get(installRoot + swConfigFileName);

        if (checkForFile(swConfig, swConfigFileName)) return;

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

            for(var i = 0; i < equations.length; ++i) {
                var appendedSegmentSize = equationSegments.get(i).length + 1;

                var tempArray = new String[appendedSegmentSize];

                var significantValueIndex = "";

                for(var j = 0; j < appendedSegmentSize; ++j) {

                    if (j < appendedSegmentSize - 1) {
                        var copyValue = equationSegments.get(i)[j];

                        if (copyValue.contains("#")) {
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


        var textFields = new ArrayList<TextField>();

        var fieldNames = new ArrayList<String>();

        for(String[] equation : appendedEquationSegments) {
            fieldNames.add(equation[0]);
        }

        var labels = new ArrayList<Label>();

        for (String name : fieldNames) {
            labels.add(new Label(name));
            textFields.add(new TextField(8));
        }

        var appWindow = new Main(appendedEquationSegments, DDTOpath,
                configPath, swConfig, textFields, labels);

        appWindow.setTitle("TOPP App");
        appWindow.setSize(800, 600);
        appWindow.setVisible(true);
*/
        var stopTime = System.currentTimeMillis();

        System.out.printf(" - App Start Time - %,d ms%n", stopTime - startTime);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var canWrite = false;
        var waitCursor = new Cursor(Cursor.WAIT_CURSOR);
        var defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

        setCursor(waitCursor);

        System.out.println(" - User Input Detected - Checking for Write Access to DDTO.blemp");

        try {
            canWrite = Files.readString(configPath).substring(1, 2).compareTo("0") == 0;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (canWrite) {
            System.out.println(" - DDTO.blemp - Write Access Available");

            var textEvent = "";
            var textEventIndex = 0;

            for(var i = 0; i < DDTOequations.size(); ++i) {
                if (textFields.get(i).getText().compareTo("") != 0) {
                    textEvent = textFields.get(i).getText();
                    textEventIndex = i;
                }
            }

            var userInput = textEvent;

            System.out.println(" - Action Event - " + e.paramString());

            var DDTOequationsCopy = new ArrayList<>(DDTOequations);

            System.out.println(" - User Entered - " + userInput);

            if (Pattern.matches("[0-9\\-.]+", userInput)) {
                if (Math.abs(Double.valueOf(userInput)) > 300) {
                    message = " * Value must be between +- 300";

                    repaint();
                } else {
                    System.out.println(" - DDTO.blemp - Clean Up");

                    try {
                        Files.writeString(DDTOpath, "");
                    } catch (IOException ex) {
                        ex.printStackTrace();

                        System.out.println(" - WARNING - Could Not Clean Up DDTO.blemp File");
                    }

                    System.out.println(" - DDTO.blemp - Clean Up - Success");

                    message = "";

                    var significantIndex = DDTOequationsCopy
                            .get(textEventIndex)[DDTOequationsCopy
                            .get(textEventIndex).length - 1];

                    DDTOequationsCopy
                            .get(textEventIndex)[Integer.valueOf(significantIndex)] = userInput;

                    var DDTOequation = "";

                    // FIXME - current config with DDTOequation only allows a single
                    //  - equation to be entered at a time
                    //  - forcing to clear user input
                    //  -- add a check to see if value changed otherwise don't write to DDTO
                    //  -- write new equations used to a buffer that lists the equations
                    //  - in a similar format to the C-HSS.blemp file - this will be use
                    //  - for creating the custom .blemp file if the user saves it
                    for (var i = 0; i < DDTOequationsCopy
                            .get(textEventIndex).length - 1; ++i)
                        DDTOequation += DDTOequationsCopy
                                .get(textEventIndex)[i];

                    message += " write equation - " + DDTOequation;

                    System.out.println(" - DDTO.blemp - Writing Equation");

                    try {
                        Files.writeString(DDTOpath, DDTOequation + "!");
                    } catch (IOException ex) {
                        ex.printStackTrace();

                        System.out.println(" - WARNING - DDTO.blemp - Writing Equation - Failed");

                        message += " - command failed - try again";
                    }

                    try {
                        Files.writeString(swConfig, "00");
                        Files.writeString(configPath, "01");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println(" - DDTO.blemp - Writing Equation - Success");

                    message += " - command success";

                    repaint();
                }
            } else {
                message = " * Input Requires Numbers Only";

                repaint();
            }

        } else {
            System.out.println(" - WARNING - DDTO.blemp - Write Access Denied");

            message = " * Unable to Write Command to Solid Works Daemon - Please wait and enter again";

            repaint();
        }

        setCursor(defaultCursor);
    }

}
