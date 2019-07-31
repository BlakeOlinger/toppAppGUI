package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

class IniFileDO {
    static Path path;
    static final String ON_EXIT_CLOSE_SW = "onExit_CloseSWdaemon";
    static final String ON_START_START_SW = "onStart_StartSWdaemon";
    static final String ON_EXIT_CLOSE_SW_PART = "onExit_CloseSWpart";
    static final String[] keys = new String[] {
            ON_EXIT_CLOSE_SW,
            ON_START_START_SW,
            ON_EXIT_CLOSE_SW_PART
    };
    private static HashMap<String, Boolean> initFields = new HashMap<>();
    static private String userIniConfig = "";

    static void getCurrentUserIniSettings() {
        setUserIniConfig();

        System.out.println(userIniConfig);
    }

    static void setField(String field, Boolean value) {
        initFields.put(field, value);

        userIniConfig = "";

        for (var i = 0; i < keys.length; ++i)
            userIniConfig += initFields.get(keys[i]) ? "0" : "1";

        ToppFiles.writeFile(
                "User Ini File",
                path,
                userIniConfig
        );
    }

    static boolean getFieldValue(String field) {
        return initFields.get(field);
    }

    private static boolean[] checkBool(String input, int endIndex) {
        var boolArray = new boolean[endIndex + 1];

        System.out.println(" Raw GUI.ini string - " + input);

        for (var i = 0; i <= endIndex; ++i) {
            boolArray[i] = input.substring(i, i + 1).compareTo("0") == 0;
            System.out.println(" bool array at " + i + " - " +boolArray[i]);
        }

        return boolArray;
    }

    private static void setUserIniConfig() {
        try {
            if (Files.exists(path) && !Files.readString(path).isEmpty()) {
                var rawIniFile = Files.readString(path);

                var boolArray = checkBool(rawIniFile, keys.length - 1);

                for (var i = 0; i < keys.length; ++i) {
                    System.out.println("Key: " + keys[i] + " Value: " + boolArray[i]);
                    initFields.put(keys[i], boolArray[i]);
                }

                System.out.println("Map contents:");
                for (var i = 0; i < keys.length; ++i) {
                    System.out.println("Key: " + keys[i] + " Value: " + initFields.get(keys[i]));

                    userIniConfig += initFields.get(keys[i]) ? "0" : "1";
                }

                System.out.println("User Config: " + userIniConfig);
            } else {
                initialize();

                ToppFiles.writeFile(
                        "User Ini File",
                        path,
                        userIniConfig
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initialize() {
        userIniConfig = "";



        for (String key: keys) {
            userIniConfig += "0";

            initFields.put(key, true);
        }
    }

    static void setIniToDefaults() {
        initialize();

        ToppFiles.writeFile(
                "User Ini File",
                path,
                userIniConfig
        );

        try {
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
