package com.practice;

import java.io.FileInputStream;
import java.io.IOException;
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
        logger.log(Level.INFO, "Daemon - Exit");

        do {
            try (var configFile = new FileInputStream("programFiles/config/GUI.config")){
                Config.programState = String.valueOf((char) configFile.read());

                Thread.sleep(2000);
            } catch (InterruptedException | IOException ignore) {
            }

        } while (Config.programState.compareTo("0") == 0);

        logger.log(Level.INFO, "Daemon - Start");
    }
}
