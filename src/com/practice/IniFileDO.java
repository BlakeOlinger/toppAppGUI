package com.practice;

import com.lib.PathsList;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

class IniFileDO {
    static final String ON_EXIT_CLOSE_SW = "onExit_CloseSWdaemon";
    static final String ON_START_START_SW = "onStart_StartSWdaemon";
    static final String ON_EXIT_CLOSE_SW_PART = "onExit_CloseSWpart";
    static final String USE_SW_INSTANCE_PREVIEW = "solidWorksInstancePreview";
    private static HashMap<String, Boolean> initFields = new HashMap<>();
    static private String userIniConfig = "";
    static final String[] keys = new String[] {
            ON_EXIT_CLOSE_SW,
            ON_START_START_SW,
            ON_EXIT_CLOSE_SW_PART,
            USE_SW_INSTANCE_PREVIEW
    };

    static void setField(String field, Boolean value) {
        initFields.put(field, value);

        userIniConfig = "";

        for (var i = 0; i < keys.length; ++i)
            userIniConfig += initFields.get(keys[i]) ? "0" : "1";

//        ToppFiles.writeFile(
//                FileNames.GUI_INI,
//                PathsList.userIni,
//                userIniConfig
//        );
    }

    static boolean getFieldValue(String field) {
        return initFields.get(field);
    }

    private static boolean[] checkBool(String input, int endIndex) {
        var boolArray = new boolean[endIndex + 1];

        for (var i = 0; i <= endIndex; ++i)
            boolArray[i] = input.substring(i, i + 1).compareTo("0") == 0;

        return boolArray;
    }

    // FIXME - if current user ini file character length differs from
    //  - keys array length setUserIniConfig() will throw an
    //  - StringIndexOutOfBoundsException
    //  -- FIX - have check string length then if doesn't match the
    //  -- keys array length write '0's for the difference then
    //  -- then continue method
    static void setUserIniConfig() {
        try {
            if (Files.exists(PathsList.userIni) && !Files.readString(PathsList.userIni).isEmpty()) {
                var rawIniFile = Files.readString(PathsList.userIni);

                var boolArray = checkBool(rawIniFile, keys.length - 1);

                for (var i = 0; i < keys.length; ++i)
                    initFields.put(keys[i], boolArray[i]);

                for (var i = 0; i < keys.length; ++i)
                    userIniConfig += initFields.get(keys[i]) ? "0" : "1";
            } else {
                initialize();

//                ToppFiles.writeFile(
//                        FileNames.GUI_INI,
//                        PathsList.userIni,
//                        userIniConfig
//                );
            }
            System.out.println(" - GUI.ini - Settings Applied");
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

//        ToppFiles.writeFile(
//                FileNames.GUI_INI,
//                PathsList.userIni,
//                userIniConfig
//        );
    }

}
