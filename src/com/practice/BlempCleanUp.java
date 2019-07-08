package com.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

class BlempCleanUp implements Runnable{
    private final Thread thread;
    private static final Logger logger =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    BlempCleanUp() {
        thread = new Thread(this, "Tmp DDO File Clean Up");
    }

    void clearDDO() {
        thread.start();
    }

    void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error Blemp Clean Up Thread Interrupted", e);
        }
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Blemp DDO Clean Up - Start");

        try {
            Files.writeString(Config.BLEMP_DDO_PATH, "");
        } catch (IOException ignore) {
        }

        logger.log(Level.INFO, "Blemp DDO Clean Up - Exit");
    }
}
