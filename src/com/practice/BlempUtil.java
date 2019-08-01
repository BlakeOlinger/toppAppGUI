package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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
            return Files.readString(PathsList.userSelectedBlemp);
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    static void getEquations() {
        BlempDO.equationList.clear();

        try {
            var tmpArray = Files.readString(PathsList.userSelectedBlemp).split("!");

            for (var i = 0; i < tmpArray.length; ++i)
                BlempDO.equationList.add(tmpArray[i]);

            BlempDO.equationList.forEach(System.out::println);

            System.out.println(BlempDO.equationList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void getEquationSegments() {
        BlempDO.equationSegmentsList.clear();

        for (String equation: BlempDO.equationList) {
            var tmpArray = new String[equation.split("\\$").length + 1];
            var copyArray = equation.split("\\$");

            for (var i = 0; i < copyArray.length; ++i) {
                if (copyArray[i].contains("#"))
                    tmpArray[tmpArray.length - 1] = String.valueOf(i);

                tmpArray[i] = copyArray[i];
            }

            BlempDO.equationSegmentsList.add(tmpArray);
        }

        for (String[] segments: BlempDO.equationSegmentsList)
            System.out.println(segments[segments.length - 1]);
    }

    static void populateCurrentConfiguration(int index) {
        var currentStringEquationBuffer = "";

        for (var i = 0; i < BlempDO.equationList.size(); ++i) {
            if (index == i) {
                for (var j = 0; j < BlempDO.equationSegmentsList.get(i).length - 1; ++j) {
                    var significantIndex = Integer.parseInt(BlempDO.equationSegmentsList.get(i)[
                            BlempDO.equationSegmentsList.get(i).length - 1
                            ]);
                    if (j == significantIndex)
                        currentStringEquationBuffer += BlempDO.userInputValue;
                    else
                        currentStringEquationBuffer += BlempDO.equationSegmentsList.get(i)[j];
                }
                currentStringEquationBuffer += "!";
            }

            BlempDO.currentEquationBuffer = currentStringEquationBuffer
                    .replace("$", "")
                    .replace("#", "");
        }
    }

    static ArrayList<Path> getAvailableBlempFiles(Path blobDirectory) {
        var paths = new ArrayList<Path>();

        try (var fileListStream = Files.list(blobDirectory)) {
            var tempPaths = new ArrayList<Path>();

            fileListStream.forEach(tempPaths::add);

            for(Path path: tempPaths)
                if (path.toString().contains(".blemp"))
                    paths.add(path);

            return paths.size() == 0 ? null : paths;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
