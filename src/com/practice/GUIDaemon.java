package com.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

class GUIDaemon implements Runnable{
    private final Thread thread;
    private static final Logger logger =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    GUIDaemon() {
        thread = new Thread(this, "GUI Daemon");
    }

    void start() {
        thread.start();
    }

    void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error " + thread.getName() +
                    " Could Not Join", e);
        }
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Daemon - Start");

        var countdown = 3;

        do {
            try (var configFile = new FileInputStream(Main.userRoot +
                    "programFiles/config/GUI.config")){
                Config.programState = String.valueOf((char) configFile.read());

                if (countdown-- == 0) {
                    logger.log(Level.INFO, "Daemon - Checking for Update - Start");

                    var path =
                            Paths.get(Main.userRoot +
                                    "programFiles/config/GUI.config");

                    checkUpdateState(path);

                    if (Config.isUpdate) {
                        softKill();
                    }

                    countdown = 3;

                    logger.log(Level.INFO, "Daemon - Checking for Update - Exit");
                }

                Thread.sleep(2000);
            } catch (InterruptedException | IOException ignore) {
            }

        } while (Config.programState.compareTo("0") == 0);

        logger.log(Level.INFO, "Daemon - Exit");
    }

    private void softKill() {
        Config.main.onAppKill();
    }

    void checkUpdateState(Path path) {

        if (Files.exists(path)) {
            try {
                Config.isUpdate =
                        Files.readString(path).substring(2,3).compareTo("0") == 0;
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error Could Not Read GUI.config", e);
            }
        }
    }
}
