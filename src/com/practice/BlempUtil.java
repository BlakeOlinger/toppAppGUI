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

    static void getEquations() {
        try {
            var tmpArray = Files.readString(BlempSelectionDO.userSelectedBlemp).split("!");

            for (var i = 0; i < tmpArray.length - 1; ++i)
                BlempDO.equationList.add(tmpArray[i]);

            BlempDO.equationList.forEach(System.out::println);

            System.out.println(BlempDO.equationList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void getEquationSegments() {
        for (String equation: BlempDO.equationList)
            BlempDO.equationSegmentsList.add(equation.split("\\$"));


    }
}
