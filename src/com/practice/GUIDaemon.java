package com.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class GUIDaemon implements Runnable{
    private final Thread thread;

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
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        var countdown = 3;

        do {
            try (var configFile = new FileInputStream(Main.userRoot +
                    "programFiles/config/GUI.config")){
                Config.programState = String.valueOf((char) configFile.read());

                if (countdown-- == 0) {
                    var path =
                            Paths.get(Main.userRoot +
                                    "programFiles/config/GUI.config");

                    checkUpdateState(path);

                    if (Config.isUpdate) {
                        softKill();
                    }

                    countdown = 3;
                }

                Thread.sleep(2000);
            } catch (InterruptedException | IOException ignore) {
            }

        } while (Config.programState.compareTo("0") == 0);
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
                e.printStackTrace();
            }
        }
    }
}
