package com.practice;

import java.io.IOException;
import java.nio.file.Files;

final class BlempUtil {
    static void populateDefaultConfiguration() {
        var rawString = readBlempFile();

        if (rawString != null) {
            System.out.println(" - raw .blemp data - ");

            System.out.println(rawString);

            BlempDO.defaultConfigurationBuffer = filterDefault(rawString);

            System.out.println(" - default filtered .blemp data -");

            System.out.println(BlempDO.defaultConfigurationBuffer);
        }
    }

    private static String filterDefault(String rawString) {
        return rawString.replace("$", "").replace("#", "");
    }

    private static String readBlempFile() {
        try {
            return Files.readString(BlempSelectionDO.userSelectedBlemp);
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
