package com.practice;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

final class Main {
    public static void main(String[] args) {
        System.out.println("TOPP App GUI - Start");

        var installRoot = InstallRoot.getInstallRoot("SolidWorks Daemon");

        var installDirectory = Paths.get(installRoot);

        if (!ToppFiles.validateDirectory(
                "Install",
                installDirectory
                )) {
            return;
        }

        if (!BlobDirectory.validateLocalBlobDatabaseInstance(installDirectory))
            return;

        var GUIconfigFileName = "GUI.config";

        var GUIconfigPath = Paths.get(installRoot + GUIconfigFileName);

        if (!ToppFiles.validateFile(GUIconfigFileName, GUIconfigPath)) {
            return;
        }

        if (!ToppFiles.writeFile(GUIconfigFileName, GUIconfigPath, "00")) {
            return;
        }

        var SWexePath = Paths.get(installRoot + "NuSWDaemon.exe");

        if (!Files.exists(SWexePath)) {
            var swConfigFileName = "SWmicroservice.config";

            var swConfigPath = Paths.get(installRoot + swConfigFileName);

            if (!ToppFiles.validateFile(swConfigFileName, swConfigPath))
                return;

            if (!ToppFiles.writeFile(swConfigFileName, swConfigPath, "01!"))
                return;
        }

        var DDTOfileName = "DDTO.blemp";

        var DDTOpath = Paths.get(installRoot + DDTOfileName);

        if (!ToppFiles.validateFile(DDTOfileName, DDTOpath)) return;

        var blobDirectory = Paths.get(installDirectory + "\\blob\\");

        var blempFiles = BlobDirectory.getAvailableBlempFiles(blobDirectory);

        if (blempFiles == null) {
            System.out.println(" - ERROR - No .blemp files found");

            return;
        }

        var blempName = "";

        // FIXME - have to use a god damned data class object - can't pass things to the thread
        try {
            SwingUtilities.invokeAndWait(new RunEnvironment(blempFiles, blempName));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(blempName);
//        var equations = Files.readString(blempName)
//                .split("!");
/*
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
                configPath, swConfigPath, textFields, labels);
*/
    }
/*
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

 */
}
