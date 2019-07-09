package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

class MasterKillCommand {
    static void kill() {
        var masterConfigPath = Paths.get(Main.userRoot +
                "programFiles/config/master.config");
        final Logger logger =
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        var command = "01";

        logger.log(Level.INFO, "Master Kill Command - Start");

        do {
            try {
                Files.writeString(masterConfigPath, "11");
                command = Files.readString(masterConfigPath).substring(0, 1);

                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                logger.log(Level.SEVERE, "Error Master Kill Thread", e);
            }
        } while (command.compareTo("0") == 0);

        logger.log(Level.INFO, "Master Kill Command - Exit");
    }
}
