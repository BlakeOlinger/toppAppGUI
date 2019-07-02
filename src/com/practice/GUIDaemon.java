package com.practice;

import java.io.FileInputStream;
import java.io.IOException;

class GUIDaemon implements Runnable{
    private final Thread thread;

    GUIDaemon() {
        thread = new Thread(this, "GUI Daemon");
    }

    void start() {
        thread.start();
    }

    @Override
    public void run() {
        do {
            try (var configFile = new FileInputStream("programFiles/config/GUI.config")){
                Config.programState = String.valueOf((char) configFile.read());

                Thread.sleep(2000);
            } catch (InterruptedException | IOException ignore) {
            }

        } while (Config.programState.compareTo("0") == 0);

        System.exit(0);
    }
}
