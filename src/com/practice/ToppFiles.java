package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ToppFiles {

    static boolean validateFile(String fileName, Path path) {
        Logger.cout(Level.INFO, fileName, "Validating");

                if (Files.exists(path)) {
            Logger.cout(Level.INFO, fileName, "File Found");
        } else {
            Logger.cout(Level.WARNING, fileName, "File Not Found");

            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();

                Logger.cout(Level.ERROR, fileName, "Could Not Create File");

                return false;
            }

            Logger.cout(Level.INFO, fileName, "File Created");
        }
        return true;
    }

    static boolean validateDirectory(String fileName, Path path) {
        Logger.cout(Level.INFO, fileName, "Directory - Validating");

        if (Files.exists(path)) {
            System.out.println(" - " + fileName + " Directory - Found");
            Logger.cout(Level.INFO, fileName, "Directory - Found");

            return true;
        } else {
            Logger.cout(Level.WARNING, fileName, "Directory - Not Found");

            try {
                Files.createDirectory(path);

                Logger.cout(Level.INFO, fileName, "Directory - Created");

                return true;
            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("TOPP App GUI - Exit");
                Logger.cout(Level.ERROR, fileName, "Could Not Create Directory");

                return false;
            }
        }
    }

    static boolean writeFile(String fileName, Path path, String writeMessage,) {
        try {
            Files.writeString(path, writeMessage);

            Logger.cout(Level.INFO, fileName, "Write - Success");

            return true;
        } catch (IOException e) {
            e.printStackTrace();

            Logger.cout(Level.ERROR, fileName, "Could Not Write to File");

            return false;
        }
    }
}
