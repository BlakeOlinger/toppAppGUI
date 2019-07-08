package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

class BlempConfig implements Runnable{
    private final Thread thread;
    private static final Logger logger =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    BlempConfig() {
        thread = new Thread(this, "Config.Blemp Loader");
    }

    void load() {
        thread.start();
    }

    void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error Loading Config.Blemp", e);
        }
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Blemp Config - Start");

        try {
            Config.blempDDO = Files.readString(Config.BLEMP_CONFIG_PATH)
                    .split("\\$");

            if(Config.blempDDO.length > 0)
                logger.log(Level.INFO, "Blemp Config - Loaded");
            else
                logger.log(Level.INFO, "Blemp Config - Error: Failed to Load");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error Failed to Read Blemp Config File", e);
        }

        logger.log(Level.INFO, "Blemp Config - Exit");
    }
}
